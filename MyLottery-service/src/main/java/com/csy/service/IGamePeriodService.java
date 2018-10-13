package com.csy.service;

import java.util.List;

import com.csy.domain.GamePeriod;

//彩种开奖信息
public interface IGamePeriodService {
	
	/**
	 * 通过彩种id获取对应彩种近30期的开奖数据,不包括彩种对应的属性
	 * @param iGameId  彩种id
	 * @return  对应彩种近30期开奖数据
	 */
	List<GamePeriod> getGamePeriod30CountByIGameId(Long iGameId);
	
	
	/**
	 * 通过彩种id和需要的条数获取对应彩种近期的开奖数据,包括彩种对应的属性
	 * @param iGameId    彩种id
	 * @param count      需要获取的条数
	 * @return
	 */
	List<GamePeriod> getGamePeriodAndPropertyByIGameIdAndCount(Long iGameId,Integer count);
}
