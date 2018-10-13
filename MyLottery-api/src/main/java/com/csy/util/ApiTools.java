package com.csy.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.csy.domain.GamePeriod;
import com.csy.response.DataObject;

public class ApiTools {
	
	/**
	 * 将GamePeriod对象转换为DataObject对象
	 * @param gamePeriods
	 * @return
	 */
	public static List<DataObject> gamePeriod2DataObject(List<GamePeriod> gamePeriods){
		List<DataObject> dataList = new ArrayList<>();
		for(GamePeriod gamePeriod:gamePeriods) {
			DataObject dataObject = new DataObject();
			dataObject.setExpect(gamePeriod.getSgameperiod());
			dataObject.setOpencode(gamePeriod.getSopennum().replace('|', ','));
			dataObject.setOpentime(gamePeriod.getDopentime());
			dataObject.setOpentimestamp(gamePeriod.getDopentime().getTime());
			dataObject.setProperty(JSONObject.toJavaObject(JSONObject.parseObject((String)gamePeriod.getProperty()), Map.class));
			
			dataList.add(dataObject);
		}
		return dataList;
	}

}
