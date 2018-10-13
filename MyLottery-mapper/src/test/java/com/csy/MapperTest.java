//package com.csy;
//
//
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSONObject;
//import com.csy.domain.GamePeriod;
//import com.csy.domain.GdklsfProperty;
//import com.csy.domain.parse.ParseGdklsf;
//import com.csy.domain.parse.ParseResultJson;
//import com.csy.dto.GamePeriodDTO;
//import com.csy.mapper.GamePeriodMapper;
//import com.csy.mapper.GdklsfPropertyMapper;
//
//
//
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MapperApp.class)
//public class MapperTest {
//	@Autowired
//	private GamePeriodMapper gamePeriodMapper;
//	@Autowired
//	private GdklsfPropertyMapper gdklsfPropertyMapper;
//	@Autowired
//	private RedisTemplate redisTemplate;
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//
//	@org.junit.Test
//	public void save() {
//		String str = new String(
//				"{'rows':5,'code':'gdklsf','remain':'8483hrs',"
//				+ "'data':[{'expect':'20180724007','opencode':'16,14,11,12,07,20,17,04','opentime':'2018-07-24 10:12:20','opentimestamp':1532398340},"
//				+ "{'expect':'20180724006','opencode':'12,08,04,06,13,10,02,15','opentime':'2018-07-24 10:02:04','opentimestamp':1532397724},"
//				+ "{'expect':'20180724005','opencode':'17,06,10,08,02,09,14,13','opentime':'2018-07-24 09:52:16','opentimestamp':1532397136},"
//				+ "{'expect':'20180724004','opencode':'03,14,15,11,10,17,20,09','opentime':'2018-07-24 09:42:12','opentimestamp':1532396532},"
//				+ "{'expect':'20180724003','opencode':'20,05,14,04,06,02,12,18','opentime':'2018-07-24 09:32:21','opentimestamp':1532395941}]}"
//				);
//		ParseResultJson json = JSONObject.parseObject(str,ParseResultJson.class);
//		List<ParseGdklsf> data = json.getData();
//		for (ParseGdklsf parseGdklsf : data) {
//			System.out.println(parseGdklsf.getOpentime());
//			System.out.println(parseGdklsf.getOpencode());
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@org.junit.Test
//	public void get() throws Exception {
//		String urlStr = "http://a.apiplus.net/newly.do?token=83a42856d7e581c4&code=gdklsf&format=json";
//        //链接URL
//        URL url=new URL(urlStr);
//        //返回结果集
//        StringBuffer document = new StringBuffer();
//        //创建链接
//        URLConnection conn = url.openConnection();
//        //读取返回结果集
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
//        String line = null;
//        while ((line = reader.readLine()) != null){
//              document.append(line);
//        }
//        reader.close();
//        String str = new String(document);
//        ParseResultJson json = JSONObject.parseObject(str,ParseResultJson.class);
//
//
//
//        //解析数据
//        GamePeriod gp = new GamePeriod();
//        List<ParseGdklsf> data = json.getData();
//        ParseGdklsf parseGdklsf = data.get(0);
//        String expect = parseGdklsf.getExpect();
//        gp.setSkey("27."+expect);
//        gp.setIgameid(27L);
//        gp.setSgameperiod(parseGdklsf.getExpect());
//        String opencode = parseGdklsf.getOpencode();
//        String newOpencode = opencode.replaceAll(",", "|");
//        gp.setSopennum(newOpencode);
//        gp.setDopentime(parseGdklsf.getOpentime());
//        String[] split = opencode.split(",");
//        gp.setIopennum1(new Short(split[0]));
//        gp.setIopennum2(new Short(split[1]));
//        gp.setIopennum3(new Short(split[2]));
//        gp.setIopennum4(new Short(split[3]));
//        gp.setIopennum5(new Short(split[4]));
//        gp.setIopennum6(new Short(split[5]));
//        gp.setIopennum7(new Short(split[6]));
//        gp.setIopennum8(new Short(split[7]));
//        gamePeriodMapper.save(gp);
//
//
//
//
//        //解析属性
//        GdklsfProperty gdklsf = new GdklsfProperty();
//        gdklsf.setsKey(gp.getSkey());
//        int sum = gp.getIopennum1() + gp.getIopennum2() + gp.getIopennum2()
//        + gp.getIopennum3() + gp.getIopennum4() + gp.getIopennum5() + gp.getIopennum6() + gp.getIopennum7() + gp.getIopennum8();
//        gdklsf.setiSum(sum);
//
//        //总和大小
//        gdklsf.setsSumBigSmall(sumBigSmall1(sum));
//
//
//        //总和单双
//        gdklsf.setsSumSingleDouble(singleDouble(sum));
//
//
//        //总和尾大小
//        gdklsf.setsSumTailBig(tailBig(sum));
//
//
//        //龙虎
//        gdklsf.setsDragonTigerNum1(dragonTiger(gp.getIopennum1(), gp.getIopennum1()));
//        gdklsf.setsDragonTigerNum1(dragonTiger(gp.getIopennum2(), gp.getIopennum7()));
//        gdklsf.setsDragonTigerNum1(dragonTiger(gp.getIopennum3(), gp.getIopennum6()));
//        gdklsf.setsDragonTigerNum1(dragonTiger(gp.getIopennum4(), gp.getIopennum5()));
//
//
//        //存储最近30期开奖数据到redis中,不包括彩种属性
//        Long size30 = redisTemplate.opsForList().size("lastOpenDataCount30GameId27");
//        if (size30 == null || size30 < 30) {
//        	redisTemplate.opsForList().leftPush("lastOpenDataCount30GameId27", gp);
//		}else {
//			redisTemplate.opsForList().rightPop("lastOpenDataCount30GameId27");
//			redisTemplate.opsForList().leftPush("lastOpenDataCount30GameId27", gp);
//		}
//
//
//
//        //存取最新一期开奖数据到redis中包括彩种属性
//        gdklsfPropertyMapper.insert(gdklsf);
//        gp.setProperty(gdklsf);
//        redisTemplate.opsForValue().set("lastOpenDataGameId27", gp);
//
//
//
//        //存储最近179期开奖数据到redis中,包括彩种属性
//        Long size179 = redisTemplate.opsForList().size("lastOpenDataAndPropertyGameId27");
//        if (size179 == null || size179 < 179) {
//        	redisTemplate.opsForList().leftPush("lastOpenDataAndPropertyGameId27", gp);
//		}else {
//			redisTemplate.opsForList().rightPop("lastOpenDataAndPropertyGameId27");
//			redisTemplate.opsForList().leftPush("lastOpenDataAndPropertyGameId27", gp);
//		}
//	}
//
//
//	/**
//	 * 每个开奖号码的鱼虾蟹判断
//	 * @param num
//	 * @return  鱼虾蟹数据
//	 */
//	public String fish(int num) {
//		switch (num) {
//		case 1 : return "鱼";
//		case 2 : return "虾";
//		case 3 : return "葫芦";
//		case 4 : return "铜钱";
//		case 5 : return "蟹";
//		case 6 : return "鸡";
//		default:return null;
//		}
//	}
//
//
//	/**
//	 * 每期的开奖号比较龙虎
//	 * @param num1  两位数比较第一位
//	 * @param num2 两位数比较第二位
//	 * @return  龙虎和数据
//	 */
//	public String dragonTiger(Short num1,Short num2) {
//		if (num1 > num2) {
//			return "龙";
//		}else if (num1 < num2) {
//			return "虎";
//		}else {
//			return "和";
//		}
//	}
//
//
//	/**
//	 * 每期开奖号码总和尾大小判断
//	 * @param sum
//	 * @return 尾大小数据
//	 */
//	public String tailBig(int sum) {
//		if (sum % 10 <= 5) {
//			return "尾小";
//		}else {
//			return "尾大";
//		}
//	}
//
//
//	/**
//	 * 数据单双判断
//	 * @param num
//	 * @return   单双数据
//	 */
//	public String singleDouble(int num) {
//		if (num % 2 == 0) {
//			return "双";
//		}else  {
//			return "单";
//		}
//	}
//
//
//	/**
//	 * 总和大小判断(36-83为小,85-132为大)
//	 * @param sum
//	 * @return   大小数据
//	 */
//	public String sumBigSmall1(int sum) {
//		if (sum >=36 && sum <= 83) {
//			return "小";
//		}else if (sum >=85 && sum <= 132) {
//			return "大";
//		}else {
//			return null;
//		}
//	}
//
//
//
//	/**
//	 * 总和大小判断(11~17为大,4~10 为小)
//	 * @param sum
//	 * @return   大小数据
//	 */
//	public String sumBigSmall2(int sum) {
//		if (sum >=4 && sum <= 10) {
//			return "小";
//		}else if (sum >=11 && sum <= 17) {
//			return "大";
//		}else {
//			return null;
//		}
//	}
//
//
//	@org.junit.Test
//	public void fish() throws Exception {
//		System.out.println(fish(6));
//	}
//
//	@org.junit.Test
//	public void getByIGameIdAndCountTest() {
//		GamePeriodDTO dto = new GamePeriodDTO();
//		dto.setiGameId(9L);
//		dto.setCount(3);
//		List<GamePeriod> byIGameIdAndCount = gamePeriodMapper.getByIGameIdAndCount(dto);
//		System.out.println(byIGameIdAndCount);
//	}
//
//
//	/**
//	 * 生成极速飞艇数据
//	 */
//	@Test
//	public void generaticJSFTCollector() throws Exception {
//		String urlStr = "http://a.apiplus.net/newly.do?token=83a42856d7e581c4&code=bjpk10&format=json&rows=20";
//		// 链接URL
//		URL url = new URL(urlStr);
//		// 返回结果集
//		StringBuffer document = new StringBuffer();
//		// 创建链接
//		URLConnection conn = url.openConnection();
//		// 读取返回结果集
//		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			document.append(line);
//		}
//		reader.close();
//		String str = new String(document);
//		ParseResultJson json = JSONObject.parseObject(str, ParseResultJson.class);
//
//		// 解析数据
//		GamePeriod gp = new GamePeriod();
//		List<ParseGdklsf> data = json.getData();
//		ParseGdklsf parseGdklsf = data.get(0);
//		String expect = parseGdklsf.getExpect();
//		gp.setSkey("41." + expect);
//		gp.setIgameid(41L);
//		gp.setSgameperiod(parseGdklsf.getExpect());
//		String opencode = parseGdklsf.getOpencode();
//		String newOpencode = opencode.replaceAll(",", "|");
//		gp.setSopennum(newOpencode);
//		gp.setDopentime(parseGdklsf.getOpentime());
//		String[] split = opencode.split(",");
//		gp.setIopennum1(new Short(split[0]));
//		gp.setIopennum2(new Short(split[1]));
//		gp.setIopennum3(new Short(split[2]));
//		gp.setIopennum4(new Short(split[3]));
//		gp.setIopennum5(new Short(split[4]));
//		gp.setIopennum6(new Short(split[5]));
//		gp.setIopennum7(new Short(split[6]));
//		gp.setIopennum8(new Short(split[7]));
//		gp.setIopennum9(new Short(split[8]));
//		gp.setIopennum10(new Short(split[9]));
////		gamePeriodMapper.save(gp);
//
//
//		// 存储最近30期开奖数据到redis中,不包括彩种属性
//		Long size30 = redisTemplate.opsForList().size("lastOpenDataCount30GameId41");
//		if (size30 == null || size30 < 30) {
//			redisTemplate.opsForList().leftPush("lastOpenDataCount30GameId41", gp);
//		} else {
//			redisTemplate.opsForList().rightPop("lastOpenDataCount30GameId41");
//			redisTemplate.opsForList().leftPush("lastOpenDataCount30GameId41", gp);
//		}
//
//		// 存取最新一期开奖数据到redis中包括彩种属性
//		redisTemplate.opsForValue().set("lastOpenDataGameId41", gp);
//
//		// 存储最近179期开奖数据到redis中,包括彩种属性
//		Long size179 = redisTemplate.opsForList().size("lastOpenDataAndPropertyGameId27");
//		if (size179 == null || size179 < 179) {
//			redisTemplate.opsForList().leftPush("lastOpenDataAndPropertyGameId27", gp);
//		} else {
//			redisTemplate.opsForList().rightPop("lastOpenDataAndPropertyGameId27");
//			redisTemplate.opsForList().leftPush("lastOpenDataAndPropertyGameId27", gp);
//		}
//	}
//
//
//	@Test
//	public void redisStringTest() {
//		Long size = redisTemplate.opsForList().size("lastOpenDataCount30GameId27");
//		System.out.println("lastOpenDataCount30GameId27->"+size);
//
//		Object object = redisTemplate.opsForValue().get("lastOpenDataGameId27");
//		System.out.println("lastOpenDataGameId27->"+object);
//
//	}
//
//	@org.junit.Test
//	public void getByIGameIdAndOpenTimeTest() {
//		GamePeriodDTO dto = new GamePeriodDTO();
//		try {
////			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////			dOpenTimeFrom = dateFormat.parse("2018-03-27 00:00:00");
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date dOpenTimeFrom = dateFormat.parse("2018-03-27");
//			Date dOpenTimeTo = MapperTest.getDateEndTime(dOpenTimeFrom);
//
//			System.out.println(dOpenTimeFrom);
//			System.out.println(dOpenTimeTo);
//
//			dto.setiGameId(5L);
//			dto.setdOpenTimeFrom(dOpenTimeFrom);
//			dto.setdOpenTimeTo(dOpenTimeTo);
//
//		} catch (ParseException e) {
//			System.out.println("格式不对！");
//			e.printStackTrace();
//		}
//		List<GamePeriod> byIGameIdAndCount = gamePeriodMapper.getByIGameIdAndOpenTime(dto);
//		System.out.println(byIGameIdAndCount);
//	}
//
//
//
//	@org.junit.Test
//	public void getNewDate() {
//		GamePeriod newDate = gamePeriodMapper.getNewDate(9L);
//		System.out.println(newDate);
//	}
//
//	public static Date getDateEndTime(Date date) {
//		if (date == null) {
//			throw new RuntimeException("getDateEndTime[date:" + date + "] error");
//		} else {
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(date);
//			cal.set(11, 23);
//			cal.set(12, 59);
//			cal.set(13, 59);
//			cal.set(14, 999);
//			return cal.getTime();
//		}
//	}
//
//	@Test
//	public void tt() {
//		ValueOperations<String, String> tmp = redisTemplate.opsForValue();
//		tmp.set("New", "new123");
////		tmp.set("中文", "chinese");
//
////		redisTemplate.opsForValue().set("NewTT", "abcd");
//
//		System.out.println("Redis-> 中："+redisTemplate.opsForValue().get("New"));
//		System.out.println("Redis-> \"gameLastQuery38\"："+redisTemplate.opsForValue().get("gameLastQuery38"));
//
//
//		System.out.println("==========");
//		ValueOperations<String, String> stringTemp = stringRedisTemplate.opsForValue();
//		System.out.println(stringTemp.get("gameLastQuery38"));
//		System.out.println(stringTemp.get("gameLastQuery41"));
//
//
//
////		redisTemplate.opsForValue().set("测试", "TestValue2");
////
////
////		System.out.println(redisTemplate.opsForValue().get("key"));
////
////		String gamePeriod = (String)redisTemplate.opsForValue().get("gameLastQuery38");
////		System.out.println(gamePeriod);
//	}
//}
//
