/*package com.csy;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csy.domain.GamePeriod;
import com.csy.mq.JsftGamePeriodSender;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApp.class)
public class ApiTest {
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	private Set<String> recode = new HashSet<>();
	private SortedSet<String> sortRecode = new TreeSet<>();
	
//	@Before
	public void setup() {
		recode.add("GameID9:20180827:678910");
		recode.add("GameID9:20180827:678913");
		recode.add("GameID9:20180827:678912");
		recode.add("GameID9:20180827:678915");
		recode.add("GameID9:20180827:678911");
		
		sortRecode.add("GameID9:20180827:678910");
		sortRecode.add("GameID9:20180827:678913");
		sortRecode.add("GameID9:20180827:678912");
		sortRecode.add("GameID9:20180827:678915");
		sortRecode.add("GameID9:20180827:678911");
		sortRecode.add("GameID9:20180826:678811");
		sortRecode.add("GameID9:20180830:679911");
	}
	
	@Before
	public void setup1() {
		recode.add("GameID9:20180827:678910");
		recode.add("GameID9:20180827:678913");
		recode.add("GameID9:20180827:678912");
		recode.add("GameID9:20180827:678915");
		recode.add("GameID9:20180827:678911");
		
		sortRecode.add("GameID9:20180827:678910");
		sortRecode.add("GameID9:20180827:678913");
		sortRecode.add("GameID9:20180827:678912");
		sortRecode.add("GameID9:20180827:678915");
		sortRecode.add("GameID9:20180827:678911");
		sortRecode.add("GameID9:20180826:678811");
		sortRecode.add("GameID9:20180830:679911");
	}

	@Test
	public void test() {
		System.out.println(recode);
		System.out.println(sortRecode);
		System.out.println("------order-----");
		
	}
	
	@Test
	public void jedis() {
		final String regx = "GameID9:20180829:*";
		
//		Jedis jedis = new Jedis("192.168.9.131",6379);
		Jedis jedis = new Jedis(host,port);
		ScanParams params = new ScanParams();
		params.match(regx);
		params.count(1000);
//		jedis.select(1);//指定RedisDB
		ScanResult<String> result = jedis.scan("0", params);
		System.out.println(result.getCursor());
		System.out.println(result.getCursorAsBytes());
		List<String> noSortResult = result.getResult();
		System.out.println(result.getResult());
		Collections.sort(noSortResult);
		System.out.println(noSortResult);
		Collections.reverse(noSortResult);
		System.out.println(noSortResult);
		
		
	}
	
	@Autowired
	private JsftGamePeriodSender sender;
	
	@Test
	public void object() {
		GamePeriod gamePeriod = new GamePeriod();//		
//		gamePeriod.setSopennum("9|5|6|7|4|3|2|1|8|10"); //10位//
//		gamePeriod.setSopennum("1|6|3");//3位//	
//		gamePeriod.setSopennum("6|3|10|20|70|8|80|75|68|55|49|35|66|34|77|38|76|58|39|12");//20位
		gamePeriod.setSopennum("7|8|11|20|15|6|2|16");//5位
		gamePeriod.setIgameid(29L);	
		gamePeriod.setSkey("29.601291");	
		gamePeriod.setSgameperiod("601291");	
		gamePeriod.setDopentime(new Date());
		gamePeriod.setDrealopen(new Date());	
		sender.send(gamePeriod);
	}

}
*/