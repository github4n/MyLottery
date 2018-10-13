package com.csy.controller;

import com.csy.domain.GameLive;
import com.csy.domain.GamePeriod;
import com.csy.domain.GameReveal;
import com.csy.domain.Navigation;
import com.csy.domain.generate.LatestAward;
import com.csy.respones.JsonResult;
import com.csy.service.IGameRevealService;
import com.csy.service.IHomePageService;
import com.csy.service.imp.GamePeriodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/homePage")
public class HomepageController {
	@Autowired
	private IHomePageService homePageService;
	@Autowired
	private IGameRevealService gameRevealServiceImpl;
	@Autowired
	private GamePeriodServiceImpl gamePeriodServiceImpl;

	
	/**
	 * 获取各个彩种下一次开奖时间
	 * 
	 * @param iGameIds
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/nextShowData")
	@ResponseBody
	public JsonResult nextShowData(Long[] iGameIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> nextShowData = gamePeriodServiceImpl.nextShowData(iGameIds);
			jsonResult.setResult(nextShowData);			
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}
	
	/**
	 * 首页导航栏彩种数据
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/navigation")
	@ResponseBody
	public JsonResult navigation() {
		JsonResult jsonResult = new JsonResult();
		try {
			List<Navigation> navs = homePageService.getHomePageNavigation();
			jsonResult.setResult(navs);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}

	
	
	
	
	/**
	 * 首页开奖动画数据
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameLive")
	@ResponseBody
	public JsonResult gameLive() {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GameLive> gameLives = homePageService.getHomePageGameLive();
			jsonResult.setResult(gameLives);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}

	
	
	
	
	/**
	 * 通过彩种id获取彩种最新一期数据
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameNewData")
	@ResponseBody
	public JsonResult gameNewData(Long[] iGameIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> gameNewData = homePageService.getGameNewData(iGameIds);
			jsonResult.setResult(gameNewData);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}

	
	
	
	
	/**
	 * 通过彩种id获取关联玩彩技巧
	 * 
	 * @param iGameIds
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameSkill")
	@ResponseBody
	public JsonResult gameSkill(Long iGameId) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GameReveal> gameReveals = gameRevealServiceImpl.getGameReveal(iGameId);
			jsonResult.setResult(gameReveals);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}
 
	
	
	
	
	/**
	 * 走势图,根据彩种id获取最近30期的开奖数据,只有开奖数据,没有属性
	 * 
	 * @param iGameId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameTendency")
	@ResponseBody
	public JsonResult gameTendency(Long iGameId) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> gamePeriods = gamePeriodServiceImpl.getGamePeriod30CountByIGameId(iGameId);
			jsonResult.setResult(gamePeriods);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}

	
	
	
	
	
	/**
	 * 根据彩种id和需要的条数获取近期开奖数据,包含属性(开奖历史数据)
	 * 
	 * @param iGameId
	 * @param count
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameHistory")
	@ResponseBody
	public JsonResult gameHistory(Long iGameId, Integer count) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> gamePeriods = gamePeriodServiceImpl.getGamePeriodAndPropertyByIGameIdAndCount(iGameId,
					count);
			jsonResult.setResult(gamePeriods);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}
	
	@CrossOrigin
	@RequestMapping("/gameTimeRemain")
	@ResponseBody
	public JsonResult gameTime(Long iGameIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			LatestAward times = gamePeriodServiceImpl.getGamePeriodCountDownTime(iGameIds);
			jsonResult.setResult(times);
		} catch (Exception e) {
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
	}
}
