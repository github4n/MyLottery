package com.csy.service;

import java.util.List;

import com.csy.domain.GameReveal;


//玩彩技巧
public interface IGameRevealService {
	/**
	 * 根据彩种id获取彩种对应的技巧文章,如果id为空则为首页的玩彩技巧
	 * @param iGameId
	 * @return
	 */
	List<GameReveal> getGameReveal(Long iGameId);
}
