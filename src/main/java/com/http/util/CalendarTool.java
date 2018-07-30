package com.http.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.aspectj.weaver.ast.Var;

public class CalendarTool {
	

	/*
	 * n分钟之前
	 */
	public static String getBeforeMinutes(String timeStr,Integer num) throws ParseException{
		timeStr = timeStr+"00";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, -num);// n分钟之前的时间
		return sdf.format(calendar.getTime());
	}
	/*
	 * n分钟之前
	 */
	public static String getBeforeMinutes2(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, -num);// n分钟之前的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * n分钟之后
	 */
	public static String getAfterMinutes(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, +num);// n分钟之前的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * n分钟之前四位时间
	 */
	public static String getBeforeMinutes1(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("HHmm");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, -num);// n分钟之前的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * n天之前
	 */
	public static String getBeforeDays(String timeStr,Integer num) throws ParseException{
		timeStr = timeStr+"00";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, -num);// n天之前的时间
		return sdf.format(calendar.getTime());
	}
	/*
	 * n天之前-8位参数
	 */
	public static String getBeforeDays1(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, -num);// n天之前的时间
		return sdf.format(calendar.getTime());
	}
	/*
	 * n天之前
	 */
	public static String getBeforeDays2(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, -num);// n天之前的时间
		return sdf.format(calendar.getTime());
	}
	/*
	 * n天之后
	 */
	public static String getAfterDays(String timeStr,Integer num) throws ParseException{
		timeStr = timeStr+"00";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, +num);// n天之后的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * n天之后
	 */
	public static String getAfterDays1(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, +num);// n天之后的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * n天之后保留天数
	 */
	public static String getAfterDays2(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
	    Date date =sdf.parse(timeStr);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, +num);// n天之后的时间
		return sdf.format(calendar.getTime());
	}
	
	/*
	 * 获取今年从当天之后的每周的这一天
	 */
	public static List<String> getAfterWeekForYear(String timeStr) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfYear= new SimpleDateFormat("yyyy");
		Integer year = Integer.parseInt(sdfYear.format(new Date())+"1231");
		List<String> timeList = new ArrayList<String>();
		timeList.add(timeStr);
		for(int i=0;i<60;i++){
			Date date =sdf.parse(timeStr);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE, +7);// n天之后的时间
		    timeStr = sdf.format(calendar.getTime());
		    if(Integer.parseInt(timeStr)<=year){
		    	timeList.add(timeStr);
		    }
		}
		return timeList;
	}
	
	/*
	 * 根据次数当天之后的每周的这一天
	 */
	public static List<String> getAfterWeekByNum(String timeStr,Integer num) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		List<String> timeList = new ArrayList<String>();
		timeList.add(timeStr);
		for(int i=0;i<num-1;i++){
			Date date =sdf.parse(timeStr);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE, +7);// n天之后的时间
		    timeStr = sdf.format(calendar.getTime());
		    timeList.add(timeStr);
		}
		return timeList;
	}
	
	/*
	 * 根据次数当天之后的每月的这一天
	 */
	public static List<String> getAfterMonthByNum(String timeStr,Integer num) throws ParseException{
		String str = timeStr.substring(timeStr.length()-2,timeStr.length());
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		List<String> timeList = new ArrayList<String>();
		if("31".equals(str)){
			String yearStr = timeStr.substring(0,4);
			String monthStr = timeStr.substring(4,6);
			if(!"10".equals(monthStr)&&!"11".equals(monthStr)&&!"12".equals(monthStr)){
				monthStr = monthStr.substring(1, 2);
			}
			int monthNum = Integer.parseInt(monthStr);
			//需要的数目
			int i = 0;
		    while(i<num){
	    	 Calendar cal = Calendar.getInstance();
	         //设置年份
	         cal.set(Calendar.YEAR,Integer.parseInt(yearStr));
	         //设置月份
	         cal.set(Calendar.MONTH, monthNum-1);
	         //获取某月最大天数
	         int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	         //设置日历中月份的最大天数
	         cal.set(Calendar.DAY_OF_MONTH, lastDay);
	         //格式化日期
	         SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	         String lastDayOfMonth = sdf2.format(cal.getTime());
	         if(lastDayOfMonth.substring(6,8).equals("31")){
	        	 timeList.add(lastDayOfMonth);
	        	 i++;
	         }
	         monthNum++;
		    }
			return timeList;
		}else if("29".equals(str)) {
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    String monthStr = sdf.format(calendar.getTime()).substring(6, 8);
			    if("29".equals(monthStr)){
			    	 timeStr = sdf.format(calendar.getTime());
					 timeList.add(timeStr);
			    }else{
			    	String yearStr = sdf.format(calendar.getTime()).substring(0, 4);
			    	timeStr = yearStr+"0329";
					timeList.add(timeStr);
			    }
			}
			return timeList;
		}else if("30".equals(str)) {
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    String monthStr = sdf.format(calendar.getTime()).substring(6, 8);
			    if("30".equals(monthStr)){
			    	 timeStr = sdf.format(calendar.getTime());
					 timeList.add(timeStr);
			    }else{
			    	String yearStr = sdf.format(calendar.getTime()).substring(0, 4);
			    	timeStr = yearStr+"0330";
					timeList.add(timeStr);
			    }
			}
			return timeList;
		}else{
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    timeStr = sdf.format(calendar.getTime());
			    timeList.add(timeStr);
			}
			return timeList;
		}
	
	}
	
	/*
	 * 根据年份当天之后的每月的这一天
	 */
	public static List<String> getAfterMonthByYear(String timeStr) throws ParseException{
		Integer num = 20;
		String str = timeStr.substring(timeStr.length()-2,timeStr.length());
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		List<String> timeList = new ArrayList<String>();
		if("31".equals(str)){
			String yearStr = timeStr.substring(0,4);
			String monthStr = timeStr.substring(4,6);
			if(!"10".equals(monthStr)&&!"11".equals(monthStr)&&!"12".equals(monthStr)){
				monthStr = monthStr.substring(1, 2);
			}
			int monthNum = Integer.parseInt(monthStr);
			//需要的数目
			int i = 0;
		    while(i<num){
	    	 Calendar cal = Calendar.getInstance();
	         //设置年份
	         cal.set(Calendar.YEAR,Integer.parseInt(yearStr));
	         //设置月份
	         cal.set(Calendar.MONTH, monthNum-1);
	         //获取某月最大天数
	         int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	         //设置日历中月份的最大天数
	         cal.set(Calendar.DAY_OF_MONTH, lastDay);
	         //格式化日期
	         SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	         String lastDayOfMonth = sdf2.format(cal.getTime());
	         if(lastDayOfMonth.substring(6,8).equals("31")){
	        	 if(lastDayOfMonth.substring(0,4).equals(yearStr)){
	        		 timeList.add(lastDayOfMonth);
	        	 }
	        	 i++;
	         }
	         monthNum++;
		    }
			return timeList;
		}else if("30".equals(str)) {
			String year = timeStr.substring(0,4);
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    String monthStr = sdf.format(calendar.getTime()).substring(6, 8);
			    if("30".equals(monthStr)){
			    	 timeStr = sdf.format(calendar.getTime());
			    	 if(timeStr.substring(0,4).equals(year)){
		        		 timeList.add(timeStr);
		        	 }
			    }else{
			    	String yearStr = sdf.format(calendar.getTime()).substring(0, 4);
			    	timeStr = yearStr+"0330";
		    	    if(timeStr.substring(0,4).equals(year)){
	        		     timeList.add(timeStr);
	        	    }
			    }
			}
			return timeList;
		}else if("29".equals(str)) {
			String year = timeStr.substring(0,4);
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    String monthStr = sdf.format(calendar.getTime()).substring(6, 8);
			    if("29".equals(monthStr)){
			    	 timeStr = sdf.format(calendar.getTime());
			    	 if(timeStr.substring(0,4).equals(year)){
		        		 timeList.add(timeStr);
		        	 }
			    }else{
			    	String yearStr = sdf.format(calendar.getTime()).substring(0, 4);
			    	timeStr = yearStr+"0329";
		    	    if(timeStr.substring(0,4).equals(year)){
	        		     timeList.add(timeStr);
	        	    }
			    }
			}
			return timeList;
		}else{
			String year = timeStr.substring(0,4);
			timeList.add(timeStr);
			for(int i=0;i<num-1;i++){
				Date date =sdf.parse(timeStr);
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(date);
			    calendar.add(Calendar.MONTH, +1);// 1个月之后的时间
			    timeStr = sdf.format(calendar.getTime());
			    if(timeStr.substring(0,4).equals(year)){
       		       timeList.add(timeStr);
       	        }
			}
			return timeList;
		}
	
	}
	
	/*
	 * 根据次数当天之后的每隔多少天的这一天
	 */
	public static List<String> getAfterDayByNum(String timeStr,Integer num,Integer dayNum) throws ParseException{
		dayNum = dayNum+1;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		List<String> timeList = new ArrayList<String>();
		timeList.add(timeStr);
		for(int i=0;i<num-1;i++){
			Date date =sdf.parse(timeStr);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE, +dayNum);// 1个月之后的时间
		    timeStr = sdf.format(calendar.getTime());
		    timeList.add(timeStr);
		}
		return timeList;
	}
	
	/*
	 * 根据次数当天之后的每隔多少天的这一天
	 */
	public static List<String> getAfterDayByYear(String timeStr,Integer dayNum) throws ParseException{
		dayNum = dayNum+1;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		List<String> timeList = new ArrayList<String>();
		String year = timeStr.substring(0,4);
		timeList.add(timeStr);
		for(int i=0;i<400;i++){
			Date date =sdf.parse(timeStr);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE, +dayNum);
		    timeStr = sdf.format(calendar.getTime());
		    if(timeStr.substring(0,4).equals(year)){
		    	timeList.add(timeStr);
		    }
		}
		return timeList;
	}
	  /**
     * 日期转星期
     * 
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	
	public static void main(String[] args) throws ParseException {
		/*String time = getBeforeMinutes("201805110056",57);*/
		//String time = getBeforeDays("201805090056",60);
		/*String time = getAfterDays("201805090056",60);*/
		//String time = getAfterWeekForYear("201805110056");
		/*List<String> timeList = getAfterWeekForYear("20180521");
		for(int i=0;i<timeList.size();i++){
			System.out.println(timeList.get(i));
		}*/
		/*String failureTime = CalendarTool.getAfterDays2(DateTool.parseDates4(new Date()),1);
		String time =  CalendarTool.getAfterMinutes(DateTool.parseDates2(new Date()), 30);*/
		List<String> timer = getAfterMonthByNum("20200130",5);
	    for(String time : timer){
	    	System.out.println(time);
	    }
		// System.out.println(dateToWeek("20180617"));
	/*	List<String> strings = CalendarTool.getAfterMonthByNum("20180231", 50);
		for(int i=0;i<strings.size();i++){
			System.out.println(strings.get(i));
		}*/
	    TestUtil testUtil = new TestUtil() {
		};
	}
}
