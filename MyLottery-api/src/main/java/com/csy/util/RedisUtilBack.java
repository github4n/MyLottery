package com.csy.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import com.csy.domain.GamePeriod;

//@Component
public class RedisUtilBack {
	//每个键的存活时间
	private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
	
	@Value("${config.redis.gameId}")
	private String gameID;
	
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
	 * 返回指定彩种最新n条数据
	 * @param gameId
	 * @param count
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> newlyCount(long gameId, int count) {
		//检索指定gameId彩种的所有数据
		String regx = gameID+gameId+":*:*";
		
		List<String> result = new ArrayList<>();
		List<String> tmp = new ArrayList<>();
		
		redisTemplate.execute(new RedisCallback<Iterable<byte[]>>() {
			@Override
			public Iterable<byte[]> doInRedis(RedisConnection conn) throws DataAccessException {
				Cursor<byte[]> cursor = conn.scan(ScanOptions.scanOptions().match(regx).count(1000).build());
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
		
		Collections.sort(tmp);
		Collections.reverse(tmp);
		
		if(count>tmp.size()) {//一般不会发生
			System.out.println("请求数量大于元素个数！");
			count = tmp.size();
		}
		
		result = tmp.subList(0, count);
		List<String> multiGet = stringRedisTemplate.opsForValue().multiGet(result);
		System.out.println(multiGet);
		
		return multiGet;
	}
	
	/**
	 * 返回指定彩种某天的数据
	 * @param gameId
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<String> dailyCount(long gameId, String date) {
		//检索指定gameId某一天的所有数据
		String regx = gameID+gameId+":"+date+":*";
		
		List<String> result = new ArrayList<>();
		
    	redisTemplate.execute(new RedisCallback<Iterable<byte[]>>() {
			@Override
			public Iterable<byte[]> doInRedis(RedisConnection conn) throws DataAccessException {
				Cursor<byte[]> cursor = conn.scan(ScanOptions.scanOptions().match(regx).count(1000).build());
				while(cursor.hasNext()) {
					result.add(new String(cursor.next(),StandardCharsets.UTF_8));
				}
				try {
                    cursor.close();
                } catch (IOException e) {
                }
				return null;
			}
    	});
    	
    	Collections.sort(result);
    	Collections.reverse(result);
    	
    	List<String> multiGet = stringRedisTemplate.opsForValue().multiGet(result);
    	System.out.println(multiGet);
    	
    	return result;
    }
	
	/**
	 * --------------------------------------------
	 */
	
	/**
	 * 每条记录对应一个key
	 * @param gamePeriod
	 */
	public void put(GamePeriod gamePeriod) {
		//9:698808
		String key = formatKey(gamePeriod);
		
		/**map中key的值（不同于gamePeriod的get/set）
		 * [istatus, iopen, iopennum4, iopennum3, sgameperiod, iopennum6, iopennum5, iopennum10, iopennum8, igameid, iopennum7, dopentime, drealopen, iopennum9, sopennum, iopennum2, iopennum1, skey, imanualopen]
		 */
		JSONObject parseObject = JSONObject.parseObject(JSON.toJSONString(gamePeriod));
		
        Map<String, Object> gamePeriodData = JSONObject.toJavaObject(parseObject, Map.class);
        
        redisTemplate.opsForHash().putAll(key, gamePeriodData);
        redisTemplate.expire(key, ONE_WEEK_IN_SECONDS, TimeUnit.SECONDS);
	}
	
	/**
	 * 返回key对应的GamePeriod对象
	 * @param key
	 * @return
	 */
	public GamePeriod getHash(String key) {
		Map<String, Object> entries = redisTemplate.boundHashOps(key).entries();
		//Map转GamePeriod对象
		GamePeriod gamePeriod = JSON.parseObject(JSON.toJSONString(entries), GamePeriod.class);
		
		return gamePeriod;
	}
	
	/**
	 *通过set列表获取key对应的数据
	 *List<Map> list存放的Map对象
	 */
	public List<GamePeriod> multiGetHash(Set<String> coll) {
		List<GamePeriod> result = new ArrayList<>();
		for(String s:coll) {
			Map entries = redisTemplate.boundHashOps(s).entries();
			//Map转GamePeriod对象
			GamePeriod gamePeriod = JSON.parseObject(JSON.toJSONString(entries), GamePeriod.class);
			result.add(gamePeriod);
		}
		
		return result;
	}
	
	/**
	 * 	gameID9->9:698809
		gameID9->9:698808
		gameID5->5:20180327059
		gameID5->5:20180327058
	 */
	public void gameIDZSetAdd(GamePeriod gamePeriod) {
		String key = formatKey(gamePeriod);
		
//		ZSetOperations zset = redisTemplate.opsForZSet();
//		zset.add(gameID+gamePeriod.getIgameid(), key, Double.parseDouble(gamePeriod.getSgameperiod()));
		
		stringRedisTemplate.opsForZSet().add(gameID+gamePeriod.getIgameid(), key, Double.parseDouble(gamePeriod.getSgameperiod()));
	}
	
	/**
	 * 通过GameId 和 count检索出数据
	 */
	public List<GamePeriod> zsetLimit(int gameId, int count) {
		ZSetOperations<String, String> strzset = stringRedisTemplate.opsForZSet();
		String key = gameID+gameId;
		
		//倒序检索，最新的在最前面
		Set<String> result = strzset.reverseRange(key, 0, count-1);
//		System.out.println(result);
		
		return multiGetHash(result);
	}
	
	/**
	 * key格式->id+期数
	 * 如：9:698808
	 */
	public static String formatKey(GamePeriod gamePeriod) {
		return gamePeriod.getIgameid()+":"+gamePeriod.getSgameperiod();
	}
	
	public void tt() {
		stringRedisTemplate.opsForHash().putAll("", null);
		redisTemplate.opsForSet().intersect(null, null);
		redisTemplate.opsForSet().intersect("", "");
		
		RedisConnection conn = null;
		conn.scan(new ScanOptions.ScanOptionsBuilder().match("*").count(1000).build());
		
	}
	
    @SuppressWarnings("unchecked")
	public void scanDbKeys(){
    	Object execute = redisTemplate.execute(new RedisCallback<Iterable<byte[]>>() {
            @Override
            public Iterable<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {

                List<byte[]> binaryKeys = new ArrayList<byte[]>();

                Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("GameID9:20180829:*").count(1000).build());
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
				Cursor<byte[]> cursor = conn.scan(ScanOptions.scanOptions().match(defaultRegx).count(1000).build());
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
