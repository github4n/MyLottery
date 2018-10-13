package com.csy.service;

import java.util.List;

import com.csy.domain.GameLive;
import com.csy.domain.GamePeriod;
import com.csy.domain.Navigation;

//首页相关
public interface IHomePageService {

	/**
	 * 获取首页导航所有彩种id,图标,名称信息
	 * @return
	 */
	List<Navigation> getHomePageNavigation();  
	
	
	/**
	 * 获取首页开奖动画所有彩种id,静态图片地址,动态图片地址
	 * @return
	 */
	List<GameLive> getHomePageGameLive();
	
	
	/**
	 * 根据彩种id获取彩种开奖最新一期数据
	 * @param iGameIds   彩种id集合
	 * @return
	 */
	List<GamePeriod> getGameNewData(Long[] iGameIds);
}
