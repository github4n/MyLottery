package com.csy.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
//	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	public static final String PATTERN_SIMPLE_DATE = "yyyy-MM-dd";
	public static final String PATTERN_SIMPLE_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_SIMPLE_DATETIME_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_PART_YEAR = "yyyy";
	public static final String DATE_PART_MONTH = "MM";
	public static final String DATE_PART_DATE = "dd";
	public static final String DATE_PART_HOUR = "HH";
	public static final String DATE_PART_MIN = "mm";
	public static final String DATE_PART_SECOND = "ss";
	public static final String DATE_PART_MILLISECOND = "SSS";

	/**根据时间戳获取时间
	 * @param millis
	 * @return
	 */
	public static Date getTime(Long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal.getTime();
	}

	public static Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal.getTime();
	}

	public static Date getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal.getTime();
	}

	public static Date getTime(int year, int month, int date, int hour, int min, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hour, min, sec);
		cal.set(14, 0);
		return cal.getTime();
	}

	public static Integer getDatePart(Date date, String part) {
		if (date != null && !StringUtils.isBlank(part)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Integer ret = null;
			byte var5 = -1;
			switch (part.hashCode()) {
			case 2304:
				if (part.equals("HH")) {
					var5 = 3;
				}
				break;
			case 2464:
				if (part.equals("MM")) {
					var5 = 1;
				}
				break;
			case 3200:
				if (part.equals("dd")) {
					var5 = 2;
				}
				break;
			case 3488:
				if (part.equals("mm")) {
					var5 = 4;
				}
				break;
			case 3680:
				if (part.equals("ss")) {
					var5 = 5;
				}
				break;
			case 3724864:
				if (part.equals("yyyy")) {
					var5 = 0;
				}
			}

			switch (var5) {
			case 0:
				ret = cal.get(1);
				break;
			case 1:
				ret = cal.get(2) + 1;
				break;
			case 2:
				ret = cal.get(5);
				break;
			case 3:
				ret = cal.get(11);
				break;
			case 4:
				ret = cal.get(12);
				break;
			case 5:
				ret = cal.get(13);
			}

			return ret;
		} else {
			return null;
		}
	}

	public static Date getNowTime() {
		return Calendar.getInstance().getTime();
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return null;
		} else {
			DateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
	}

	public static Date parserDate(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		} else {
			Integer len = StringUtils.length(dateStr);
			if (len != "yyyy-MM-dd".length() && len != "yyyy-MM-dd HH:mm:ss".length()
					&& len != "yyyy-MM-dd HH:mm:ss.SSS".length()) {
				throw new RuntimeException(
						"source[" + dateStr + "] is wrong date format, can't parse to Date! right format should like ["
								+ "yyyy-MM-dd" + "/" + "yyyy-MM-dd HH:mm:ss" + "/" + "yyyy-MM-dd HH:mm:ss.SSS" + "]");
			} else {
				String pattern = "";
				pattern = len == "yyyy-MM-dd".length() && StringUtils.isBlank(pattern) ? "yyyy-MM-dd" : pattern;
				pattern = len == "yyyy-MM-dd HH:mm:ss".length() && StringUtils.isBlank(pattern) ? "yyyy-MM-dd HH:mm:ss"
						: pattern;
				pattern = len == "yyyy-MM-dd HH:mm:ss.SSS".length() && StringUtils.isBlank(pattern)
						? "yyyy-MM-dd HH:mm:ss.SSS"
						: pattern;
				return parserDate(dateStr, pattern);
			}
		}
	}

	public static Date parserDate(String dateStr, String pattern) {
		if (StringUtils.isAnyBlank(new CharSequence[] { dateStr, pattern })) {
			return null;
		} else {
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			try {
				Date date = df.parse(dateStr);
				return date;
			} catch (Exception var4) {
//				log.error("parser date from dateStr[" + dateStr + "]@format[" + pattern + "] error:", var4);
				throw new RuntimeException(var4);
			}
		}
	}

	public static Integer dateDiff(Date date1, Date date2, String datePart) {
		if (date1 != null && date2 != null) {
			if (datePart == null) {
				throw new RuntimeException("getInterval param error, datePart is null.");
			} else {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date1);
				cal1.set(14, 0);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);
				cal2.set(14, 0);
				Integer factor = 1;
				byte var7 = -1;
				switch (datePart.hashCode()) {
				case 2304:
					if (datePart.equals("HH")) {
						var7 = 3;
					}
					break;
				case 2464:
					if (datePart.equals("MM")) {
						var7 = 1;
					}
					break;
				case 3200:
					if (datePart.equals("dd")) {
						var7 = 2;
					}
					break;
				case 3488:
					if (datePart.equals("mm")) {
						var7 = 4;
					}
					break;
				case 3680:
					if (datePart.equals("ss")) {
						var7 = 5;
					}
					break;
				case 82419:
					if (datePart.equals("SSS")) {
						var7 = 6;
					}
					break;
				case 3724864:
					if (datePart.equals("yyyy")) {
						var7 = 0;
					}
				}

				switch (var7) {
				case 0:
					return cal1.get(1) - cal2.get(1);
				case 1:
					Integer year1 = cal1.get(1);
					Integer year2 = cal2.get(1);
					Integer month1 = cal1.get(2);
					Integer month2 = cal2.get(2);
					if (year1.compareTo(year2) > 0) {
						return (year1 - year2) * 12 + (month1 - month2);
					}

					if (year1.compareTo(year2) == 0) {
						return month1 - month2;
					}

					return -dateDiff(date2, date1, "MM");
				case 2:
					factor = factor * 24 * 60 * 60 * 1000;
					break;
				case 3:
					factor = factor * 60 * 60 * 1000;
					break;
				case 4:
					factor = factor * 60 * 1000;
					break;
				case 5:
					factor = factor * 1000;
				case 6:
					break;
				default:
					throw new RuntimeException("getInterval param error, datePart[" + datePart + "] not supported.");
				}

				Long interval = (cal1.getTimeInMillis() - cal2.getTimeInMillis()) / (long) factor;
				return interval.intValue();
			}
		} else {
			throw new RuntimeException("getInterval param error, arg date1 or date2 is null.");
		}
	}

	public static Integer compare(Date t1, Date t2) {
		if (t1 != null && t2 != null) {
			return t1.compareTo(t2);
		} else {
			throw new RuntimeException("two compare date object neither can be null.");
		}
	}

	public static Date getDateAfterIDay(Date date, int i) {
		return dateAdd("dd", date, i);
	}

	public static Date dateAdd(String part, Date date, Integer value) {
		if (!StringUtils.isBlank(part) && date != null && value != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Integer interval = null;
			byte var6 = -1;
			switch (part.hashCode()) {
			case 2304:
				if (part.equals("HH")) {
					var6 = 3;
				}
				break;
			case 2464:
				if (part.equals("MM")) {
					var6 = 1;
				}
				break;
			case 3200:
				if (part.equals("dd")) {
					var6 = 2;
				}
				break;
			case 3488:
				if (part.equals("mm")) {
					var6 = 4;
				}
				break;
			case 3680:
				if (part.equals("ss")) {
					var6 = 5;
				}
				break;
			case 82419:
				if (part.equals("SSS")) {
					var6 = 6;
				}
				break;
			case 3724864:
				if (part.equals("yyyy")) {
					var6 = 0;
				}
			}

			switch (var6) {
			case 0:
				interval = 1;
				break;
			case 1:
				interval = 2;
				break;
			case 2:
				interval = 5;
				break;
			case 3:
				interval = 11;
				break;
			case 4:
				interval = 12;
				break;
			case 5:
				interval = 13;
				break;
			case 6:
				interval = 14;
				break;
			default:
				throw new RuntimeException("dateAdd param error, part:" + part);
			}

			cal.add(interval, value);
			return cal.getTime();
		} else {
			throw new RuntimeException("dateAdd param error, part:" + part + ", date:" + date + ", value:" + value);
		}
	}

	public static Date getDateBeginTime(Integer year, Integer month, Integer date) {
		if (year != null && year >= 1 && month != null && month >= 1 && month <= 12 && date != null && date >= 1
				&& date <= 31) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, date, 0, 0, 0);
			cal.set(14, 0);
			return cal.getTime();
		} else {
			throw new RuntimeException(
					"getDateBeginTime[year:" + year + ", month:" + month + ", date:" + date + "] error");
		}
	}

	public static Date getDateBeginTime(Date date) {
		if (date == null) {
			throw new RuntimeException("getDateBeginTime[date:" + date + "] error");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(11, 0);
			cal.set(12, 0);
			cal.set(13, 0);
			cal.set(14, 0);
			return cal.getTime();
		}
	}

	public static Date getDateEndTime(Integer year, Integer month, Integer date) {
		if (year != null && year >= 1 && month != null && month >= 1 && month <= 12 && date != null && date >= 1
				&& date <= 31) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, date, 23, 59, 59);
			cal.set(14, 999);
			return cal.getTime();
		} else {
			throw new RuntimeException(
					"getDateEndTime[year:" + year + ", month:" + month + ", date:" + date + "] error");
		}
	}

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

	public static Date getMonthBeginTime(Integer year, Integer month) {
		if (year != null && year >= 1 && month != null && month >= 1 && month <= 12) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1, 0, 0, 0);
			cal.set(14, 0);
			return cal.getTime();
		} else {
			throw new RuntimeException("getFirstDate[year:" + year + ", month:" + month + "] error");
		}
	}

	public static Date getMonthEndTime(Integer year, Integer month) {
		Date date = getMonthBeginTime(year, month);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, 1);
		cal.add(5, -1);
		cal.set(11, 23);
		cal.set(12, 59);
		cal.set(13, 59);
		cal.set(14, 999);
		return cal.getTime();
	}

	public static Integer getDateField(Date date, Integer field) {
		if (date == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(field);
		}
	}

	public static void main(String[] args) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
//		System.out.println(dateFormat.format(DateUtil.getNowTime()));
//		System.out.println(dateFormat.format(DateUtil.dateAdd("dd", DateUtil.getNowTime(), -1)));
//		System.out.println(dateFormat.format(DateUtil.dateAdd("MM", DateUtil.getNowTime(), -1)));
		System.out.println(DateUtil.getDatePart(new Date(), "yyyy"));
		System.out.println(DateUtil.parserDate("2018-08-27"));
//		System.out.println(DateUtil.parserDate("20180827"));


//		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_SIMPLE_DATETIME_FULL);
//		System.out.println(dateFormat.format(DateUtil.dateAdd("MM", DateUtil.getNowTime(), -1)));
//		System.out.println(dateFormat.format(DateUtil.dateAdd("dd", DateUtil.getNowTime(), -1)));

	}


	/**获取每天的开奖/结束时间
	 * @param timer
	 * @return
	 */
	public  static   Long getLotteryTimes(Date date ,String timer){
		Long openTimes =0L;
		String temp = DateUtil.getDatePart(date,"yyyy")+"-"+DateUtil.getDatePart(date,"MM")
				+"-"+DateUtil.getDatePart(date,"dd")+" "+timer;
		System.out.println(temp);
		try {
			//每天开奖时间的时间戳
			Date openTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(temp);
			openTimes = openTime.getTime();//如今天的早上九点
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  openTimes;
	}

}
