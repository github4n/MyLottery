package com.csy.common.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
public class RedisUtil {

	//每个键的存活时间
	public static final long ONE_WEEK_IN_SECONDS = 7 * 86400;
	public static final long ONE_DAY_IN_SECONDS =  86400;
	public static final long TWO_DAY_IN_SECONDS =  2*86400;

	@Value("${config.redis.gameId}")
	private String gameID;
	
	@Value("${config.redis.scan.count}")
	private int scanCount;//scan步进的值
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * --------------------重新封装----------------
	 */
	
	/**
	 * key-value添加记录
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public void setValue(String key, Object value, Long expireTime) {
		stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
		if(expireTime > 0) {
			stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * 通过key获取value
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}
	
	/**
	 * 给定key集合返回对象列表
	 * @param keys
	 * @return
	 */
	public List<String> getMutValue(Collection<String> keys) {
		return stringRedisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 通过key获取指定类型的对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getJson(String key, Class<T> clazz) {
		return JSON.toJavaObject(JSON.parseObject(stringRedisTemplate.boundValueOps(key).get()), clazz);
	}
	
	/**
	 * key-Set集合的存储方式
	 * @param key GameID9:2018-08-28
	 * @param value	sKey的Set集合
	 * @param score	GamePeriod的期数
	 * @param expireTime 失效时间
	 */
	public void setZSet(String key, String value, long score, long expireTime) {
		stringRedisTemplate.opsForZSet().add(key, value, score);
		if(expireTime > 0) {
			stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * 通过key获取指定的Set集合，并返回指定count条数据
	 * @param gameId
	 * @param count
	 */
	public List<String> getZSet(String key, int count) {
		ZSetOperations<String, String> strzset = stringRedisTemplate.opsForZSet();
		if(count == 0) {//按日期检索
			count = Integer.parseInt(""+strzset.zCard(key));
		}
		//倒序检索，最新的在最前面
		Set<String> tmp = strzset.reverseRange(key, 0, count-1);
		List<String> result = new ArrayList<>(tmp); 
		
		return result;
	}
	
	/**
	 * 按指定彩种和日期检索数据
	 * @param gameId
	 * @param date
	 * @return 
	 */
	public List<String> getZSetByDate(String key) {
		return getZSet(key, 0);
	}
	
	
	/**==========索引方式=========
	 * 返回指定彩种最新n条数据
	 * @param gameId
	 * @param count
	 * @return
	 */
	public List<String> newlyCountByIndex(long gameId, int count) {
		//默认获取当天最新的n条数据
		String parserDate = DateUtil.formatDate(new Date(), DateUtil.PATTERN_SIMPLE_DATE);
		//GameID9:2018-08-28
		String key = gameID+gameId+":"+parserDate;
		List<String> tmp = getZSet(key, count);
		
		if(count>tmp.size()) {//一般不会发生
			System.out.println("请求数量大于元素个数！");
			count = tmp.size();
		}
		
		List<String> result = tmp.subList(0, count);
		List<String> multiGet = getMutValue(result);
		
		return multiGet;
	}
	
	/**========索引的方式=======
	 * 返回指定彩种某天的数据
	 * @param gameId
	 * @param date
	 * @return
	 */
	public List<String> dailyCountByIndex(long gameId, String date) {
		//检索指定gameId某一天的所有数据
		String key = gameID+gameId+":"+date;
		List<String> tmp = getZSetByDate(key);
		List<String> result = getMutValue(tmp);//通过key列表查询JSON对象列表
		
		return result;
	}
	
	/**========scan方式=========
	 * 返回指定彩种最新n条数据
	 * @param gameId
	 * @param count
	 * @return
	 */
	public List<String> newlyCount(long gameId, int count) {
		//检索指定gameId彩种的所有数据
		String regx = gameID+gameId+":*:*";
		List<String> tmp = scanByMatchPattern(regx, scanCount);
		
		if(count>tmp.size()) {//一般不会发生
			System.out.println("请求数量大于元素个数！");
			count = tmp.size();
		}
		
		List<String> result = tmp.subList(0, count);
		List<String> multiGet = getMutValue(result);
		
		return multiGet;
	}
	
	/**========scan的方式========
	 * 返回指定彩种某天的数据
	 * @param gameId
	 * @param date
	 * @return
	 */
	public List<String> dailyCount(long gameId, String date) {
		//检索指定gameId某一天的所有数据
		String regx = gameID+gameId+":"+date+":*";
		List<String> result = scanByMatchPattern(regx, scanCount);
		List<String> multiGet = getMutValue(result);//通过key列表查询JSON对象列表
		
		return multiGet;
	}
	
	/**
	 * 通过正则表达式检索redis中的数据
	 * @param match
	 * @param count
	 * @return
	 */
	public List<String> scanByMatchPattern(String match, long count) {
		//Set去重
		Set<String> execute = stringRedisTemplate.execute(new RedisCallback<Set<String>>() {
			@Override
			public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
				Set<String> tmp = new HashSet<>();
				Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(match).count(count).build());
				while (cursor.hasNext()) {
					tmp.add(new String(cursor.next(), StandardCharsets.UTF_8));
				}
				return tmp;// 检索结果
			}
		});
		// Set -> List
		List<String> result = new ArrayList<>(execute);
		Collections.sort(result);// 排序
		Collections.reverse(result);// 反转

		return result;
	}
	
    @SuppressWarnings("unchecked")
	public void scanDbKeys(){
    	Object execute = redisTemplate.execute(new RedisCallback<Iterable<byte[]>>() {
            @Override
            public Iterable<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {

                List<byte[]> binaryKeys = new ArrayList<byte[]>();

                Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("GameID9:20180829:*").count(scanCount).build());
                while (cursor.hasNext()) {
                    byte[] key = cursor.next();
                    binaryKeys.add(key);
                    System.out.println(new String(key, StandardCharsets.UTF_8));
                    System.out.println(stringRedisTemplate.opsForValue().get(new String(key, StandardCharsets.UTF_8)));
//                    System.out.println(stringRedisTemplate.opsForValue().get(key));
                }
                try {
                    cursor.close();
                } catch (IOException e) {
                }

                return binaryKeys;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void redisCallback(long gameId, String date, int count) {
		//检索指定gameId某一天的所有数据
		String defaultRegx = gameID+gameId+":"+date+":*";
		
		List<String> result = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
		
//		if(StringUtils.isBlank(date)) {//检索指定gameId彩种的所有数据
//			defaultRegx = gameID+gameId+":*"+":*";
//		}

    	redisTemplate.execute(new RedisCallback<Iterable<byte[]>>() {
			@Override
			public Iterable<byte[]> doInRedis(RedisConnection conn) throws DataAccessException {
				Cursor<byte[]> cursor = conn.scan(ScanOptions.scanOptions().match(defaultRegx).count(scanCount).build());
				while(cursor.hasNext()) {
					tmp.add(new String(cursor.next(),StandardCharsets.UTF_8));
				}
                try {
                    cursor.close();
                } catch (IOException e) {
                }
				return null;
			}
    	});
    	
    	
    	System.out.println(tmp);
    	Collections.sort(tmp);
    	Collections.reverse(tmp);
    	
    	if(count>tmp.size()) {//一般不会发生
    		System.out.println("请求数量大于元素个数！");
    		count = tmp.size();
    	}
    	
    	result = tmp.subList(0, count);
    	List<String> multiGet = stringRedisTemplate.opsForValue().multiGet(result);
    	System.out.println(multiGet);
    	
    }


}
