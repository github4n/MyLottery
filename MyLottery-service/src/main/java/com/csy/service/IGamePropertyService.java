package com.csy.service;

import com.csy.domain.GamePeriod;

//彩种属性
public interface IGamePropertyService {
	/**
	 * 通过彩种对象获取彩种对应的属性信息
	 * @param gamePeriod  完整的彩种信息包括了属性信息
	 * @return
	 */
	GamePeriod getGamePropertyClassByGameId(GamePeriod gamePeriod);
}
