package com.csy.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tools {
    
    public static String timestamp2date_short(String timestamp) {
    	//时间格式,HH是24小时制，hh是AM PM12小时制
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	//比如timestamp=1449210225945；
    	long date_temp = Long.valueOf(timestamp)*1000L;  
    	String date_string = sdf.format(new Date(date_temp)); 
    	//至于取10位或取13位，date_temp*1000L就是这种截取作用。如果是和服务器传值的，就和后台商量好就可以了
    	
    	return date_string;
    }
	
    public static long date2timestamp(String dateStr) {
    	//yyyyMMdd或yyyy-MM-dd
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date parse = null;
    	try {
    		parse = sdf.parse(dateStr);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    	return parse.getTime();
    }
    
    
    /**
     * 将字符串的日期格式转为Date对象
     * @param dateStr
     * @return
     */
	public static Date dateFormat(String dateStr) {
		//yyyyMMdd或yyyy-MM-dd
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date parse = null;
		try {
			parse = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return parse;
	}
	
	
	/**
	 * 计算并返回给定日期最后的时间
	 * @param date
	 * @return
	 */
	public static Date getDateEndTime(Date date) {
		if (date == null) {
			throw new RuntimeException("getDateEndTime[date:" + date + "] error");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(11, 23);
			cal.set(12, 59);
			cal.set(13, 59);
			cal.set(14, 999);
			return cal.getTime();
		}
	}
	
	/**
	 * 对日期格式进行校验
	 *
	 * @param strDate 传递过来的日期字符串
	 * @return true 正确 | false 错误
	 */
	public static boolean validateDateTime(String strDate) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.PATTERN_SIMPLE_DATE);
		try {
			Date parse = simpleDateFormat.parse(strDate);
			String nDate = simpleDateFormat.format(parse);
			
			if(nDate.equals(strDate)) {
//				System.out.println("格式正确");
				return true;
			}else{
//				System.out.println("格式错误");
				return false;
			}
		} catch (ParseException e) {
			throw new RuntimeException("参数异常");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println(Tools.date2timestamp("2018-08-15"));
		
//		System.out.println(new Date().getTime());
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = dateFormat.parse("2018-08-15");
//		System.out.println("From:"+date);
//		System.out.println("End :"+Tools.getDateEndTime(date));
		
		
		System.out.println(Tools.validateDateTime("20-11201"));
		
	}
}
