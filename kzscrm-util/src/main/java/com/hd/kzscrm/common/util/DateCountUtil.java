package com.hd.kzscrm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateCountUtil {
	
	//获取当前时间前length天列表（yyyy-MM-dd）
	public static List<String> getDays(int length){
		List<String> days=new ArrayList<String>();
		for(int i=0;i<length;i++){
			days.add(getDay(i));
		}
		return days;
	}
	//获取当前时间的前past天时间
	public static String getDay(int past){
		Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       return format.format(today);
	}
	
	
	
	
	//获取当前时间前的length周列表（201711 表示2017年11周）
	public static List<String> getWeeks(int length) throws ParseException{
		Calendar c = Calendar.getInstance();
		int week=c.get(Calendar.WEEK_OF_YEAR);
		int year=c.get(Calendar.YEAR);
		List<String> weeks=new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<length;i++){
			int n=week-i;
			if(n>9){
				weeks.add(year+""+n);
			}else if(n>0){
				weeks.add(year+"0"+n);
			}else{
				int yestWeeks=getWeekOfYear(sdf.parse(year-1+"12-31"));
				if(yestWeeks+n>9){
					weeks.add(year-1+""+(yestWeeks+n));
				}else{
					weeks.add(year-1+"0"+(yestWeeks+n));
				}
			}
		}
		return weeks;
	}
	
	//获取某年有多少周
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    
    
    
    //获取当前月份前length个月的列表(YYYY-MM)
    public static List<String> getMonths(int length){
    	Calendar c = new GregorianCalendar();
    	int year=c.get(Calendar.YEAR);
    	int month=c.get(Calendar.MONTH)+1;
    	List<String> months=new ArrayList<String>();
    	for(int i=0;i<length;i++){
    		int n=month-i;
    		if(n>9){
    			months.add(year+"-"+n);
    		}else if(n>0){
    			months.add(year+"-0"+n);
    		}else{
    			if(12+n>9){
    				months.add(year-1+"-"+(12+n));
    			}else{
    				months.add(year-1+"-0"+(12+n));
    			}
    		}
    	}
    	return months;
    }
    
    //获取当前年份前length年的列表（YYYY）
    public static List<String> getYears(int length){
    	Calendar c = new GregorianCalendar();
    	int year=c.get(Calendar.YEAR);
    	List<String> years=new ArrayList<String>();
    	for(int i=0;i<length;i++){
    		years.add(year-i+"");
    	}
    	return years;
    }
    //显示时间段列表(YYYY-MM-dd)
    public static List<String> getDateTime(String startTime,String endTime){
    	List<String> lDate = new ArrayList<String>();  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	try {
			Date beginDate=sdf.parse(startTime);
			Date endDate=sdf.parse(endTime);
			lDate.add(sdf.format(beginDate));// 把开始时间加入集合  
			
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(beginDate);  
	        
	        boolean bContinue = true;
	        while (bContinue) {
	            cal.add(Calendar.DAY_OF_YEAR,1);
	            if (endDate.after(cal.getTime())) {  
	                 lDate.add(sdf.format(cal.getTime()));
	            } else {  
	                break;  
	            }  
	        }  
	        lDate.add(sdf.format(endDate));// 把结束时间加入集合  
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return lDate;  
    }
    
    public static void main(String[] args) {
		/*String startTime="2017-04-01";
		String endTime="2017-04-07";
		List<String> strs=getDateTime(startTime,endTime);
		for(String str:strs){
			System.out.println(str);
		}*/
    	
    	String  time =getApplyTime("2017年06月");
    	System.out.println(time);
	}
    
   //2017年6月 转化成2017-06 
    public static String getApplyTime(String  time){
    	SimpleDateFormat sdf = new  SimpleDateFormat("yyyy年MM日");
    	SimpleDateFormat form = new SimpleDateFormat("yyyy-MM");
    	try {
			Date d = sdf.parse(time);
			if(d!=null){
				String t = form.format(d);
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return  null;
    }
    
}
