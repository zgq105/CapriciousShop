package com.guoqiang.commonlib.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @Auther: zgq
 * @Date: 2018/6/23 15:15
 * @Description:
 */
public class DateUtils {

	/**
	 * 匹配：yyyy-MM-dd hh:mm:ss 格式： ^\d{4}-\d{1,2}-\d{1,2}\s\d{2}:\d{2}:\d{2}$
	 */
	private static final String REG_DATE_TIME = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}$";
	private static final Pattern REG_DATE_TIME_PAT = Pattern
			.compile(REG_DATE_TIME);

	/**
	 * 匹配：yyyy-MM-dd 格式： ^\d{4}-\d{1,2}-\d{1,2}$
	 */
	private static final String REG_DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
	private static final Pattern REG_DATE_PAT = Pattern.compile(REG_DATE);

	/**
	 * 匹配：Tue Oct 11 2011 00:00:00 GMT+08:00 格式：
	 * ^([A-Z]|[a-z]){3}\s([A-Z]|[a-z])
	 * {3}\s\d{2}\s\d{4}\s\d{2}:\d{2}:\d{2}\sGMT\+08:00$
	 */
	private static final String REG_GMT = "^([A-Z]|[a-z]){3}\\s([A-Z]|[a-z]){3}\\s\\d{2}\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\sGMT\\+08:00$";
	private static final Pattern REG_GMT_PAT = Pattern.compile(REG_GMT);

	private DateUtils() {
	}

	/**
	 * 自动转日期
	 * 
	 * @param s
	 * @return
	 */
	public static Date parse(String s, Date defaultDate) {
		Matcher matcher = REG_DATE_TIME_PAT.matcher(s);
		if (matcher.find()) {
			return parseDateTime(s, defaultDate);
		}
		matcher = REG_DATE_PAT.matcher(s);
		if (matcher.find()) {
			return parseDate(s, defaultDate);
		}
		matcher = REG_GMT_PAT.matcher(s);
		if (matcher.find()) {
			return parseGMT(s, defaultDate);
		}
		return defaultDate;
	}

	/**
	 * 将Tue Oct 11 2011 00:00:00 GMT+08:00字符串，转换成日期 有BUG不推荐使用，某些环境测试成功，某些不成功
	 * 
	 * @param s
	 * @return
	 */
	@Deprecated
	public static Date parseGMT(String s, Date defaultDate) {
		try {
			// s = s.substring(0, s.length()-2) +":"+ s.substring(s.length()-2);
			DateFormat df = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z",
					Locale.US);
			return df.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultDate;
	}

	/**
	 * 将yyyy-MM-dd，转成date类型
	 * 
	 * @param s
	 * @param defaultDate
	 * @return
	 */
	public static Date parseDate(String s, Date defaultDate) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultDate;
	}

	/**
	 * 转换日期
	 * @param d 原始日期
	 * @param f 转换格式，精确到yyyy-MM-dd
	 * @return 返回转换后格式
	 */
	public static Date parse(Date d, String f) {
		String dateStr = parseToString(d, f);
		return parseDate(dateStr, null);
	}
	
	public static Date parseDateTime(Date d, String f) {
		String dateStr = parseToString(d, f);
		return parseDateTime(dateStr, null);
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss，转成date类型
	 * 
	 * @param s
	 * @param defaultDate
	 * @return
	 */
	public static Date parseDateTime(String s, Date defaultDate) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.parse(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultDate;
	}

	/**
	 * 将日期转成指定格式字符串
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String parseToString(Date d, String format) {
		if (d == null)
			return "";
		DateFormat df = new SimpleDateFormat(format);
		return df.format(d);
	}

	/**
	 * 添加天数
	 * 
	 * @param date
	 *            需要添加的时间
	 * @param days
	 *            需要添加的天数
	 * @return 返回添加后的时间
	 */
	public static Date addDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 添加分钟
	 * 
	 * @param date
	 *            时间
	 * @param minue
	 *            分钟
	 * @return 添加分钟后时间
	 */
	public static Date addMinute(Date date, int minue) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minue);
		return calendar.getTime();
	}

	/**
	 * 添加分钟
	 * 
	 * @param date
	 *            时间
	 * @param minue
	 *            分钟
	 * @return 添加分钟后时间
	 */
	public static Timestamp addMinute(Timestamp time, int minue) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.MINUTE, minue);
		return convertTimestamp(calendar.getTime());
	}

	/**
	 * 获取前一天
	 * 
	 * @return
	 */
	public static Date getPrevDay() {
		String f = "yyyy-MM-dd";
		String dateStr = parseToString(new Date(), f);
		return parseDate(dateStr, null);
	}

	/**
	 * 获取当前时间，前一天
	 * 
	 * @return 返回当前时间，前一天
	 */
	public static Date getPrevNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 获取前n天
	 * 
	 * @param n
	 *            前n天
	 * @return 返回前n天
	 */
	public static Date getPrevDay(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -n);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期是星期几，星期一为0
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 获取后n天
	 * 
	 * @param n
	 *            后n天
	 * @return 返回后n天
	 */
	public static Date getNextDay(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	/**
	 * 将date日期转换成timestamp
	 * 
	 * @param date
	 *            date 日期
	 * @return timestamp 格式化时间
	 */
	public static Timestamp convertTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取当前时间以timestamp形式返回
	 * 
	 * @return 返回timestamp
	 */
	public static Timestamp getTimestamp() {
		Date d = new Date();
		return new Timestamp(d.getTime());
	}

	public static void main(String[] args) {
		Date d = parse("2012-11-01 12:00:11", null);
		System.out.println(d);
		d = parse("2012-11-01", null);
		System.out.println(d);
		d = parse("Tue Oct 11 2011 12:10:10 GMT+08:00", null);
		System.out.println(d);
		d = getPrevDay();
		System.out.println(d);
	}
	
	/**
	 * java.util.Date转java.sql.Date
	 * @param utilDate
	 * @return
	 */
	public static java.sql.Date utilDate2sqlDate(java.util.Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}
