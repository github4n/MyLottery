package com.csy.service.imp;

import com.alibaba.fastjson.JSON;
import com.csy.Constants.ConstantsPoll;
import com.csy.common.util.DateUtil;
import com.csy.common.util.RedisUtil;
import com.csy.common.util.Tools;
import com.csy.domain.GamePeriod;
import com.csy.domain.generate.LatestAward;
import com.csy.dto.GamePeriodDTO;
import com.csy.mapper.GamePeriodMapper;
import com.csy.service.IGamePeriodService;
import com.csy.service.IGamePropertyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//彩种开奖信息
@Service
public class GamePeriodServiceImpl implements IGamePeriodService {


	@Autowired
	private GamePeriodMapper gamePeriodMapper;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private IGamePropertyService gamePropertyserviceImpl;

	/**
	 * 通过彩种id获取对应彩种近30期的开奖数据
	 * @param iGameId  彩种id
	 * @return  对应彩种近30期开奖数据
	 */
	@SuppressWarnings("unchecked")
	public List<GamePeriod> getGamePeriod30CountByIGameId(Long iGameId) {
		GamePeriodDTO dto = new GamePeriodDTO();
		if (iGameId == null || iGameId < 1 || iGameId > 43) {
			throw new RuntimeException("参数异常");
		}
		List<GamePeriod> gamePeriods = (List<GamePeriod>) redisTemplate.opsForList().range("lastOpenDataCount30GameId"+iGameId, 0, 29);
		if (gamePeriods.size() == 0) {
			dto.setiGameId(iGameId);
			dto.setCount(30);
			gamePeriods = gamePeriodMapper.getByIGameIdAndCount(dto);
		}
		return gamePeriods;
	}


	/**
	 * 通过彩种id和需要的条数获取对应彩种近期的开奖数据,包括彩种对应的属性
	 * @param iGameId    彩种id
	 * @param count      需要获取的条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GamePeriod> getGamePeriodAndPropertyByIGameIdAndCount(Long iGameId, Integer count) {
		GamePeriodDTO dto = new GamePeriodDTO();
		List<GamePeriod> gamePeriods = null;
		if (count == 25) {  //开奖纪录25条
			List<?> range = redisTemplate.opsForList().range("lastOpenDataAndPropertyGameId"+iGameId, 0, 24);
			System.out.println(range);
			gamePeriods = (List<GamePeriod>) redisTemplate.opsForList().range("lastOpenDataAndPropertyGameId"+iGameId, 0, 24);
		}else if (count == 179) {  //历史开奖数据179条
			gamePeriods = (List<GamePeriod>) redisTemplate.opsForList().range("lastOpenDataAndPropertyGameId"+iGameId, 0, 178);
		}else {
			throw new RuntimeException("参数异常");
		}
		if (gamePeriods.size() == 0) {
			dto.setiGameId(iGameId);
			dto.setCount(count);
			gamePeriods = gamePeriodMapper.getByIGameIdAndCount(dto);
			for (GamePeriod gp : gamePeriods) {
				gp = gamePropertyserviceImpl.getGamePropertyClassByGameId(gp);
			}
		}
		return gamePeriods;
	}


	/**
	 * 通过彩种id和字段count，获取指定开奖数据
	 * @param iGameId    彩种id
	 * @param count      需要获取的条数
	 * @return
	 */
	public List<GamePeriod> getByIGameIdAndCount(Long iGameId, Integer count) {
		if (iGameId == null || iGameId < 1 || iGameId > 43) {
			throw new RuntimeException("参数异常");
		}
		GamePeriodDTO dto = new GamePeriodDTO();
		//从redis检索n条数据
		List<String> jsons = redisUtil.newlyCountByIndex(iGameId, count);
		List<GamePeriod> gamePeriods = JSON.parseArray(jsons.toString(), GamePeriod.class);

		//redis中没有数据，从数据库中查询
		if (gamePeriods.size() == 0) {
			dto.setiGameId(iGameId);
			dto.setCount(count);
			gamePeriods = gamePeriodMapper.getByIGameIdAndCount(dto);
		}

		return gamePeriods;
	}

	/**
	 * 根据指定的彩种id和开奖日期，返回一天的开奖数据
	 * @param iGameId
	 * @param date
	 * @return
	 */
	public List<GamePeriod> getByIGameIdAndOpenTime(Long iGameId, String date) {
		if (iGameId == null || iGameId < 1 || iGameId > 43) {
			throw new RuntimeException("参数异常");
		}

		GamePeriodDTO dto = new GamePeriodDTO();
		//从redis检索某天数据
		List<String> jsons = redisUtil.dailyCountByIndex(iGameId, date);
		List<GamePeriod> gamePeriods = JSON.parseArray(jsons.toString(), GamePeriod.class);

		if(gamePeriods.size() == 0) {//redis中没有某天的数据，则从数据库中查询
			dto.setiGameId(iGameId);
			Date dOpenTime = Tools.dateFormat(date);
			dto.setdOpenTimeFrom(dOpenTime);
			dto.setdOpenTimeTo(Tools.getDateEndTime(dOpenTime));
			dto.setDesc("DESC");
			gamePeriods = gamePeriodMapper.getByIGameIdAndOpenTime(dto);
		}

		return gamePeriods;
	}

	/**
	 * 生成对应彩种的倒计时数据(下一期的)
	 * @param iGameId
	 * @return
	 */
	public LatestAward getGamePeriodCountDownTime(Long iGameId) {
		if (iGameId == null) {
			throw new RuntimeException("数据有误");
		}
		LatestAward gameGenerate = new LatestAward();
		GamePeriod finalResult = null;//用于存放最终的结果
		//查詢最新的開獎數據
		List<String> periodString = redisUtil.dailyCountByIndex(iGameId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (periodString.size()!=0){
			for (String period: periodString){
				GamePeriod gamePeriod = JSON.parseObject(period, GamePeriod.class);
				String sgameperiod = gamePeriod.getSgameperiod();
				long aLong = Long.parseLong(sgameperiod)+1;//最新期号
				Date openTime = getNewlyTime(gamePeriod,aLong);//redis中取最新的时间
				//判断是否今日的最后一期--->如果取不出时间数据,说明已经是最后一期了,给出明天第一期的
				if (openTime==null || openTime.getTime() < DateUtil.getLotteryTimes(new Date(),ConstantsPoll.OpentimeSet.get(iGameId)) ){
                    GamePeriod minPeriodData = gamePeriodMapper.selectMinPeriodData(iGameId);
                    openTime = getNewlyTime(minPeriodData, Long.parseLong(minPeriodData.getSgameperiod()));
				} else {
					//判断是否含有数据丢失的情况
					//当前时间与开奖时间的间隔 如果是 该彩种的每期时间间隔的 1+ 倍,说明有数据丢失
					Long lostTimes = (new Date().getTime() - openTime.getTime()) / ConstantsPoll.gameIdTimeInterval.get(iGameId);
					if (lostTimes>0){
						aLong = aLong +lostTimes;
						openTime = getNewlyTime(gamePeriod,aLong);
					}
				}
				//判断是不是在开奖时间范围内,如果不是,就给出明天第一期的开奖时间
				if (	gamePeriod.getDstarttime()!=null
						&& gamePeriod.getDclosetime()!=null&&
						DateUtil.compare(gamePeriod.getDstarttime(),new Date()) <= 0
						&& DateUtil.compare(new Date(),gamePeriod.getDclosetime()) >= 0 ){
					openTime = gamePeriod.getDstarttime();
					DateUtil.getDateAfterIDay(openTime,1);
				}
				//設置屬性
				gameGenerate.setNextOpenTime(openTime.getTime());
				gameGenerate.setNextPeriod(aLong);
				gameGenerate.setGamePeriod((aLong-1)+"");
				finalResult = gamePeriod;
				break;
			}
		}else {
			//如果今日没有数据就从数据库查询一条数据(已经开奖的最后一期)
			GamePeriod minPeriodData = gamePeriodMapper.selectMinPeriodData(iGameId);
			//設置屬性
			Long aLong = Long.parseLong(minPeriodData.getSgameperiod());
			gameGenerate.setNextPeriod(aLong);
			gameGenerate.setGamePeriod(aLong-1+"");
			finalResult = minPeriodData;
			Date newlyTime = minPeriodData.getDstarttime();
			gameGenerate.setNextOpenTime(newlyTime ==null ? null:newlyTime.getTime());//开奖时间
		}

		//调用本类方法把结果设置到redis中
		gameGenerate.setLastContext((String) finalResult.getProperty());
		gameGenerate.setCurrentTime(new Date().getTime());
		gameGenerate.setTotalPeriodCount(ConstantsPoll.totalCountSet.get(iGameId));//总条数
		//把今天的第一条的期号放入redis中
		//1.根据当天的第一期开奖时间查出第一条数据
		Integer overPeriodCount=null;
		String firstPeriodString = (String) redisTemplate.opsForValue().get(MessageFormat.format(ConstantsPoll.FIRST_PERIOD,
				new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		if (firstPeriodString ==null){
			GamePeriod firstPeriod =  gamePeriodMapper.getFirstPeriodEveryDay(iGameId,
				DateUtil.getTime(DateUtil.getLotteryTimes(new Date(),ConstantsPoll.OpentimeSet.get(iGameId))));
			if (firstPeriod!=null && firstPeriod.getSopennum() !=null){
				firstPeriodString =firstPeriod.getSgameperiod();
				redisUtil.setValue(MessageFormat.format(ConstantsPoll.FIRST_PERIOD,
						new SimpleDateFormat("yyyy-MM-dd").format(new Date())),firstPeriodString, RedisUtil.ONE_DAY_IN_SECONDS);
				overPeriodCount =Integer.parseInt((Long.parseLong(finalResult.getSgameperiod())-Long.parseLong(firstPeriodString))+"")+1;
			}else {
                //第一期没有开,即已开为0
                overPeriodCount =0;
			}
		}
		gameGenerate.setOverPeriodCount(overPeriodCount);
		LatestAward latestAward = setResultInRedis(gameGenerate, finalResult);
		//返回结果
		return latestAward;
	}


	public List<GamePeriod> nextShowData(Long[] iGameIds) {
		List<GamePeriod> nextShowTime = new ArrayList<>();
		for(Long iGameId : iGameIds ) {
			GamePeriod gamePeriod = gamePeriodMapper.nextShowData(iGameId);
			nextShowTime.add(gamePeriod);
		}
		return nextShowTime;
	}
	/**把结果集最后形态GameGenerate对象放入Redis中
	 * @param gameGenerate
	 * @param result
	 */
	private LatestAward setResultInRedis(LatestAward gameGenerate,GamePeriod result) {
		int i =1;
		//设置属性
		gameGenerate.setCode(1);
		gameGenerate.setDesc("OK");
		gameGenerate.setError("false");
		gameGenerate.setGameId(Integer.valueOf(result.getIgameid()+""));
		gameGenerate.setResult("TRUE");
		gameGenerate.setOpenNum(result.getSopennum());
		//设置开奖结果 1~n号开奖数据
		try {
			Long iopennum = 1L;
			String[] split = StringUtils.split(result.getSopennum(), "|");
			while ( split!=null && iopennum != null && i<= split.length){
				//读取result中的开奖数据字段
				Short iopennumtemp = (Short) FieldUtils.readField(result, "iopennum" + i, true);
				iopennum = Long.valueOf(iopennumtemp+"");
				//然后设置到返回对象中
				FieldUtils.writeDeclaredField(gameGenerate,"openNum"+i,iopennum ,true);
				i++;
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//以String类型放入redis中
		String key = ConstantsPoll.gameSetInRedis.get(result.getIgameid());
		redisTemplate.opsForValue().set(key,JSON.toJSONString(gameGenerate));
		return gameGenerate;
	}



	/**从redis中获取最新的开奖时间
	 * @param gamePeriod
	 * @param aLong
	 * @return
	 */
	private Date getNewlyTime(GamePeriod gamePeriod , Long aLong){
		String openTimeString = redisUtil.getValue("sechdule:" + gamePeriod.getIgameid() + "." + aLong);//查询缓存时间列表
		Date openTime = null;
		try {
				if (StringUtils.isNotBlank(openTimeString)){
					openTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(JSON.parseObject(openTimeString,String.class));
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(openTime);
		return openTime;
	}

}
