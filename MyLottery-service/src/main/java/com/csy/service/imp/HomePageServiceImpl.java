package com.csy.service.imp;

import com.csy.domain.GameLive;
import com.csy.domain.GamePeriod;
import com.csy.domain.Navigation;
import com.csy.mapper.GameLiveMapper;
import com.csy.mapper.GamePeriodMapper;
import com.csy.mapper.NavigationMapper;
import com.csy.service.IGamePropertyService;
import com.csy.service.IHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageServiceImpl implements IHomePageService {
	@Autowired
	private NavigationMapper navigationMapper;
	@Autowired
	private GameLiveMapper gameLiveMapper;
	@Autowired
	private GamePeriodMapper gamePeriodMapper; //彩种开奖信息
	@Autowired
	private IGamePropertyService gamePropertyServiceImpl;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	@SuppressWarnings("unchecked")
	public List<Navigation> getHomePageNavigation() {
		List<Navigation> navs = (List<Navigation>) redisTemplate.opsForValue().get("homePageNavigation");
		if (navs == null) {
			navs = navigationMapper.getAll();
			redisTemplate.opsForValue().set("homePageNavigation", navs);
		}
		return navs;
	}


	@SuppressWarnings("unchecked")
	public List<GameLive> getHomePageGameLive() {
		List<GameLive> gameLives = (List<GameLive>) redisTemplate.opsForValue().get("homePageGameLive");
		if (gameLives == null) {
			gameLives = gameLiveMapper.getAll();
			redisTemplate.opsForValue().set("homePageGameLive", gameLives);
		}
		return gameLives;
	}
	
	
	
//	@SuppressWarnings("unused")
	public List<GamePeriod> getGameNewData(Long[] iGameIds) {
		if (iGameIds == null) {
			throw new RuntimeException("数据有误");
		}
		ArrayList<GamePeriod> gamePeriods = new ArrayList<>();
		for (Long id : iGameIds) {
			if (id < 1 || id > 43) {
				throw new RuntimeException("参数异常");
			}
			GamePeriod gamePeriod = (GamePeriod) redisTemplate.opsForValue().get("lastOpenDataGameId"+id);
			if (gamePeriod == null) {
//				gamePeriod = gamePeriodMapper.getNewDate(id);
				//设置彩种对应的属性
				gamePeriod = gamePropertyServiceImpl.getGamePropertyClassByGameId(gamePeriod);
				gamePeriods.add(gamePeriod);
			}
			gamePeriods.add(gamePeriod);
		}
		return gamePeriods;
	}
}
