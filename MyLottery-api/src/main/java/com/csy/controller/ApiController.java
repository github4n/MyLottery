package com.csy.controller;

import com.alibaba.fastjson.JSON;
import com.csy.common.util.RedisUtil;
import com.csy.common.util.Tools;
import com.csy.domain.GamePeriod;
import com.csy.domain.generate.LatestAward;
import com.csy.response.JsonResult;
import com.csy.service.IHomePageService;
import com.csy.service.imp.GamePeriodServiceImpl;
import com.csy.util.ApiTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Value("${token}")
	private String token;
	
	@Autowired
	private GamePeriodServiceImpl gamePeriodServiceImpl;
	@Autowired
	private IHomePageService homePageService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	private static Map<String, Long> code_gameId= new HashMap<>();
	
	static {
		code_gameId.put("fc3d", 1L);		//福彩3d
		code_gameId.put("pl3", 2L);		    //排列3
		code_gameId.put("shssl", 3L);		//上海时时乐
		code_gameId.put("tjssc", 4L);		//天津时时彩
		code_gameId.put("cqssc", 5L);		//重庆时时彩
		code_gameId.put("xjssc", 7L);		//新疆时时彩
		code_gameId.put("bjpk10", 9L);		//北京pk10
		code_gameId.put("jsk3", 10L);		//江苏快3
		code_gameId.put("ahk3", 11L);		//安徽快3
		code_gameId.put("jx11v5", 14L);		//江西11选5
		code_gameId.put("gd11x5", 15L);		//廣東11选5
		code_gameId.put("gxk3", 17L);		//廣西快3
		code_gameId.put("bjkl8", 23L);		//北京快乐8
		code_gameId.put("gdklsf", 27L);		//广东快乐十分
		code_gameId.put("xync", 28L);		//重庆幸运农场
		code_gameId.put("azxy10", 33L);		//澳洲幸运10
		code_gameId.put("jssc", 37L);		//极速赛车
		code_gameId.put("mlaft", 38L);		//幸运飞艇
		code_gameId.put("jsft", 41L);		//极速飞艇
		code_gameId.put("pcdd", 42L);		//pc蛋蛋
	}
	
	
	/**
	 * 通过彩种id获取彩种最新一期数据
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/gameNewData",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult gameNewData(Long[] iGameIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> gameNewData = homePageService.getGameNewData(iGameIds);
			jsonResult.setData(gameNewData);
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}
	/**
	 * 获取各个彩种下一期开奖时间
	 * 
	 * @param iGameIds
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/nextShowData",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult nextShowData(Long[] iGameIds) {
		JsonResult jsonResult = new JsonResult();
		try {
			List<GamePeriod> nextShowTime = gamePeriodServiceImpl.nextShowData(iGameIds);
			jsonResult.setData(nextShowTime);			
		} catch (RuntimeException e) {
			jsonResult.setErrorMsg(e.getMessage());
		} catch (Exception e) {
			jsonResult.setErrorMsg("数据异常");
		}
		return jsonResult;
	}
	
	/**
	 * 返回指定彩种指定条数的结果
	 * @param token		用户校验的凭证
	 * @param code		对应彩种的编码
	 * @param format	json
	 * @param rows		开奖数据的条数
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/newly")
	@ResponseBody
	public JsonResult gameNewly(String token, String code, String format, String rows)  {
		JsonResult jsonResult = new JsonResult();
		try {
			if (token == null || (!token.equals(this.token))) {// 非法token，验证不通过
				jsonResult.setErrorMsg("无效账号！");
			} else if(code == null || code == ""){
				throw new RuntimeException("参数异常");
			} else {
				if (rows == null) {// 默认将rows定义为5行
					rows = "5";
				}
				if(Integer.parseInt(rows) > 30) {//最多返回30条数据
					rows = "30";
				}
				jsonResult.setRows(Integer.parseInt(rows));
				jsonResult.setCode(code);
				List<GamePeriod> gamePeriods = gamePeriodServiceImpl.getByIGameIdAndCount(code_gameId.get(code), Integer.parseInt(rows));
				jsonResult.setRows(gamePeriods.size());//当刚开奖的时候，期数少于5时起作用！
				jsonResult.setData(ApiTools.gamePeriod2DataObject(gamePeriods));
				
			}
		} catch (Exception e) {
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
	}
	/**
	 * 根据指定的彩种id和开奖日期，返回一天的开奖数据
	 * @param token		用户校验的凭证
	 * @param code		对应彩种的编码
	 * @param format	json
	 * @param date 		开奖日期
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/daily")
	@ResponseBody
	public JsonResult gameDaily(String token, String code, String format, String date)  {
		JsonResult jsonResult = new JsonResult();
		try {
			if (token == null || (!token.equals(this.token))) {// 非法token，验证不通过
				jsonResult.setErrorMsg("无效账号！");
				return jsonResult;
			}
			
			if(code == null || code == "") {
				throw new RuntimeException("参数异常");
			}
			
			if(StringUtils.isBlank(date) || !(Tools.validateDateTime(date))) {
				throw new RuntimeException("参数异常");
			}
			
			List<GamePeriod> gamePeriods = gamePeriodServiceImpl.getByIGameIdAndOpenTime(code_gameId.get(code), date);
			jsonResult.setRows(gamePeriods.size());
			jsonResult.setCode(code);
			jsonResult.setData(ApiTools.gamePeriod2DataObject(gamePeriods));
			
		} catch (Exception e) {
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
	}

	/**获取倒计时所需要的数据
	 * @param iGameId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping("/gameTimeRemain")
	@ResponseBody
	public JsonResult gameTime(Long iGameId) {
		JsonResult jsonResult = new JsonResult();
		try {
			LatestAward gamePeriodCountDownTime = gamePeriodServiceImpl.getGamePeriodCountDownTime(iGameId);
			jsonResult.setData(JSON.toJSON(gamePeriodCountDownTime));
		} catch (Exception e) {
			jsonResult.setErrorMsg(e.getMessage());
		}
		return jsonResult;
	}

}
