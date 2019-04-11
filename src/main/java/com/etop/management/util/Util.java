package com.etop.management.util;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.model.CalendarDifferenceModel;



/**
 * 
 * @author zoushaohua
 *
 * @time 2015年6月6日 上午10:45:22
 *
 * @description 工具类
 */
public class Util {
	
	/**
	 * @description 遍历字符串target在str中的位置
	 */
	public static List<Integer> foreach(String target,String str){
		
		if(null == str || null == target || "".equals(str.trim()) || "".equals(target.trim()))
			return null ;
		
		if(str.length() < target.length())
			return null ;
		
	    boolean match = false ;
	    int index = 0 ;
	    
	    List<Integer> list = new ArrayList<Integer>();
	    int targetLen = target.length() ;
	    while(true){
	    	if(index+targetLen>str.length())
	    		break;
	    	
	    	match =str.regionMatches(false,index++, target, 0, targetLen);
	    	if(match){
	    		list.add(index-1);
	    	}
	    }
		
	    if(list == null || list.size() == 0)
	    	return null ;
	    
		return list ;
	}
	
	/**
	 * String[]转为String
	 * @param strings
	 * @param seprate
	 * @return
	 */
	public static String Strings2Str(String [] strings,String seprate)
	{
		StringBuffer sb=new StringBuffer();
		
		for(String s:strings)
		{
			sb.append(s).append(seprate);
		}
		
		String str=new String(sb);
		
		str=str.substring(0,str.length()-1);
		
		return str;
		
	}
	
	public static String List2Str(List<String> list,String seperate)
	{
		StringBuffer sb=new StringBuffer();
		
		for(String s:list)
		{
			sb.append(s).append(seperate);
		}
		
		String str=new String(sb);
		
		str=str.substring(0,str.length()-seperate.length());
		
		return str;
	}
	
	/**
	 * 判断String 是否为null，或为空
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str)
	{
		boolean result=false;
		
		if(str==null || "".equals(str.trim())|| "null".equals(str))
		{
			result=true;
		}
		
		return result;
	}
	/**
	 * 列表是否有效(不为NULL且不为空)
	 * @param list
	 * @return
	 */
	public static boolean validList(List<Object> list)
	{
		return list!=null && list.size()>0;
	}

	 public static Map<String, Object> ConvertObjToMap(Object obj){
		  Map<String,Object> reMap = new HashMap<String,Object>();
		  if (obj == null) 
		   return null;
		  Field[] fields = obj.getClass().getDeclaredFields();
		  try {
		   for(int i=0;i<fields.length;i++){
		    try {
		     Field f = obj.getClass().getDeclaredField(fields[i].getName());
		     f.setAccessible(true);
		           Object o = f.get(obj);
		           reMap.put(fields[i].getName(), o);
		    } catch (NoSuchFieldException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IllegalArgumentException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    } catch (IllegalAccessException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		   }
		  } catch (SecurityException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } 
		  return reMap;
		 }
	 
	 
	 public static String generateMessageBody(String type,String value,String title){
			StringBuilder builder=new StringBuilder();
			builder.append("<EasyLink Type=\"");
			builder.append(type);
			builder.append("\" ");
			builder.append("Value=\"");
			builder.append(value);
			builder.append("\" ");
			builder.append("Title=\"");
			builder.append(title);
			builder.append("\"/> ");
			return builder.toString();
		}
	 
	 public static String generateSid()
	 {
		 int max=900000000;
			
		 int min=100000000;
		
		 Random random = new Random();
		
		 int randomNumber =  random.nextInt(max) + min;
					
		 return randomNumber+"";
	 }
	 
	 public static String formatSecond(Object second){  
         String  html="0秒";  
         if(second!=null){  
             Double s=(Double) second;  
             String format;  
             Object[] array;  
             Integer hours =(int) (s/(60*60));  
             Integer minutes = (int) (s/60-hours*60);  
             Integer seconds = (int) (s-minutes*60-hours*60*60);  
             if(hours>0){  
                 format="%1$,d时%2$,d分%3$,d秒";  
                 array=new Object[]{hours,minutes,seconds};  
             }else if(minutes>0){  
                 format="%1$,d分%2$,d秒";  
                 array=new Object[]{minutes,seconds};  
             }else{  
                 format="%1$,d秒";  
                 array=new Object[]{seconds};  
             }  
             html= String.format(format, array);  
         }  
          
        return html;  
    }
	 
	 
	 
	 /** 
	     * 时间戳转换成日期格式字符串 
	     * @param milliSeconds 精确到毫秒的字符串 
	     * @param format
	     * @return 
	     */  
	    public static String timeStamp2Date(String milliSeconds,String format) {  
	    	
	        if(isNullOrEmpty(milliSeconds)){  
	        	
	            return "";  
	            
	        }  
	        
	        if(isNullOrEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";  
	        
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        
	        return sdf.format(new Date(Long.valueOf(milliSeconds)));  
	        
	    }  
	    /** 
	     * 日期格式字符串转换成时间戳(毫秒) 
	     * @param date_str 字符串日期
	     * @param format 如：yyyy-MM-dd HH:mm:ss 
	     * @return 
	     */  
	    public static String date2TimeStamp(String date_str,String format){
	    	
	        try {
	        	
	            SimpleDateFormat sdf = new SimpleDateFormat(format); 
	            
	            return String.valueOf(sdf.parse(date_str).getTime()); 
	            
	        } catch (Exception e) { 
	        	
	            e.printStackTrace(); 
	            
	        } 
	        
	        return "";  
	    }  
	    
	    /**
	     * 生成更新map
	     * @param id
	     * @param field
	     * @param value
	     * @return
	     */
	    public static Map<String, Object> generateUpdateMap(String id,String field,Object value)
	    {
	    	Map<String,Object> map=new HashMap<String,Object>();
			
			map.put("id", id);
			
			map.put("field", field);
			
			map.put("value", value);
			
			map.put("updatedAt", System.currentTimeMillis());
			
			return map;
	    }
	    
	    /**
	     * 生成读取map
	     * @param field
	     * @param value
	     * @return
	     */
	    public static Map<String, Object> generateReadMap(String field,String value)
	    {
	    	Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("field", field);
			
			map.put("value", value);
			
			return map;
	    }
	    
	    /**
	     * 猎取oper信息
	     * @param request
	     * @return
	     */
	    public static OpInfoBean getOpInfo()
	    {
	    	OpInfoBean op=new OpInfoBean();
			
	    	EtopUser u=UserInfoUtil.getUserInfo();
	    	
	    	if(u!=null)
	    	{
	    		op.setOper(u.getUserName());
	    	
	    		op.setOpMobile(u.getMobile());
	    	}
//			op.setOper((String) request.getSession().getAttribute("loginUserName"));
//			
//			op.setOpMobile((String) request.getSession().getAttribute("loginUser"));
			
			return op;
	    }

	    /**
	     * 手机号正则测试
	     * @param mobiles
	     * @return
	     */
	    public static boolean isMobileNumber(String mobiles){  
	    	  
	    	Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
    	  
	    	Matcher m = p.matcher(mobiles);  
    	 
	    	//System.out.println(m.matches()+"---");  
    	  
	    	return m.matches();  
    	  
    	}
	    
	    
	    public static void translate(Object source,Object destination)
	    {
	    	MethodAccess dMethodAccess = MethodAccess.get(destination.getClass());
	        if (dMethodAccess == null) {  
	            //descMethodAccess = cache(m2);  
	        }  
	        MethodAccess sMethodAccess = MethodAccess.get(source.getClass());
	        if (sMethodAccess == null) {  
	           // orgiMethodAccess = cache(m);  
	        }  
			
	        
	        Class<?> dclassType = destination.getClass();
	        
	        
	        
	        Field[] dfields =dclassType.getFields(); 
	        		//dclassType.getDeclaredFields();
	        
	        Class<?> sclassType = source.getClass();
	        
	        Field[] sfields = sclassType.getFields();
	        		//.getDeclaredFields();
	        
	        Field[] fields=sfields;
	        
	        if (sfields.length>dfields.length)
	        {
	        	fields=dfields;
	        }
	        
	        
	        for (int i = 0; i < fields.length; i++)
	        {
		        // 属性对象
		        Field field = fields[i];
		        // 属性名
		        String fieldName = field.getName();
		        // 获取属性首字母
		        String firstLetter = fieldName.substring(0, 1).toUpperCase();
		        // 拼接get方法名如getName
		        String getMethodName = "get" + firstLetter + fieldName.substring(1);
		        
		        String setMethodName = "set" + firstLetter + fieldName.substring(1);
		        
		        int dIndex=-1;
		        
		        int sIndex=-1;
		        
		        
		         try {
		        	 
					dIndex = dMethodAccess.getIndex(setMethodName);
					
					sIndex=sMethodAccess.getIndex(getMethodName);
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	         	if(dIndex!=-1 && sIndex!=-1)
	         	{
	         		dMethodAccess.invoke(destination, dIndex, sMethodAccess.invoke(source, sIndex));
	         	}
	        
	        }
	    }
	    
	    
	    public static String formatDateTime(Date date)
  		{
  			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  			
  			return sdf.format(date);	
  		}
  		
  		public static String formatDate(Date date)
  		{
  			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  			
  			return sdf.format(date);	
  		}
  		
  		public static int getWeekDay(Date date)
  		{
  			Calendar now = Calendar.getInstance();  
  			
  			now.setTime(date);
  			
	  		//一周第一天是否为星期天  
	  		boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);  
	  		//获取周几  
	  		int weekDay = now.get(Calendar.DAY_OF_WEEK);  
	  		//若一周第一天为星期天，则-1  
	  		if(isFirstSunday){  
	  		    weekDay = weekDay - 1;  
	  		    if(weekDay == 0){  
	  		        weekDay = 7;  
	  		    }  
	  		}  
  		
  		return weekDay;
  		}
  		
  		/**
  		 * 增加天数
  		 * @param date
  		 * @param num
  		 * @return
  		 */
  		public static String increaseDate(String date,int num)
  		{
//  			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			
//			Date result =null ;
//			
//			try {
//				result = df.parse(date);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
//			long lresult=result.getTime()+24*60*60*1000*num;
//			
//			String dates=df.format(lresult);
//			
//			
//			
//			return dates;
			
			Calendar cal = Calendar.getInstance();  
	        cal.setTime(Util.str2Date(date));  
	        cal.add(Calendar.DATE, num);  
			
	        return formatDate(cal.getTime());
			
			

  		}
  		
  		public static String increaseMonth(String date,int num)
  		{
  			Calendar cal = Calendar.getInstance();  
	        
  			cal.setTime(Util.str2Date(date));  
	        
  			cal.add(Calendar.MONTH, num);  
			
	        return formatDate(cal.getTime());
	        
	        
//  			String[] arr=date.split("-");
//  			
//  			int month=Integer.valueOf(arr[1])+num;
//  			
//  			int year=Integer.valueOf(arr[0]);
//  			
//  			if(month>12)
//  			{
//  				year+=Math.floor(month/12);
//  				
//  				month=(int) (month-12*Math.floor(month/12));
//  			}
//  			
//  			return year+"-"+String.format("%02d", month)+"-"+arr[2];
  			
  		}
  		
  		public static Date str2Date(String date)
  		{
  			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			Date result =null ;
			
			try {
				result = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
  		} 		
  		
  		
  		/**
  		 * 日期差,d1-d2
  		 * @param date1
  		 * @param date2
  		 * @return
  		 */
  		public static int dateDiff(String date1,String date2)
  		{
  			int result=0;
  			
  			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date11 =null ;
			
			Date date22=null;
			
			try {
				
				date11 = df.parse(date1);
				
				date22=df.parse(date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  			result=(int) ((date11.getTime()-date22.getTime())/(24*60*60*1000));
  			
  			return result;
  		}

  		public static CalendarDifferenceModel getCalendarDifference(String startDate,String endDate)
  		{
  			CalendarDifferenceModel model=new CalendarDifferenceModel();
  			
  			String[] ds=startDate.split("-");
  			
  			String[] de=endDate.split("-");
  			
  			String a=startDate;
  			
  			String b=endDate;
  			
  			if(Integer.valueOf(ds[2])!=1)
  			{
  				a=increaseDate(startDate,-1);
  			}
  			
  			if(Integer.valueOf(ds[2])==1)
  			{
  				b=increaseDate(endDate,1);
  			}
  			
  			ds=a.split("-");
  			
  			de=b.split("-");
  			
  			int a1=Integer.valueOf(ds[0]);
  			
  			int a2=Integer.valueOf(ds[1]);
  			
  			int a3=Integer.valueOf(ds[2]);
  			
  			int b1=Integer.valueOf(de[0]);
  			
  			int b2=Integer.valueOf(de[1]);
  			
  			int b3=Integer.valueOf(de[2]);
  			
  			int e=0;
  			
  			if(b3<a3)
  			{
  				e=1;
  			}
  			
  			int c=(b1-a1)*12+(b2-a2)-e;
  			
  			String dd=increaseMonth(a,c);
  			
  			int d=dateDiff(b,dd);
  			
  			model.setDays(d);
  			
  			model.setMonths(c);
  			
  			return model;
  			
  			
  		}
  		  		

		 private static Pattern linePattern = Pattern.compile("_(\\w)");  
        /**下划线转驼峰*/  
        public static String lineToHump(String str){  
            str = str.toLowerCase();  
            Matcher matcher = linePattern.matcher(str);  
            StringBuffer sb = new StringBuffer();  
            while(matcher.find()){  
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
            }  
            matcher.appendTail(sb);  
            return sb.toString();  
        }  
		
		
		private static Pattern humpPattern = Pattern.compile("[A-Z]");  
        /**驼峰转下划线*/  
        public static String humpToLine(String str){  
            Matcher matcher = humpPattern.matcher(str);  
            StringBuffer sb = new StringBuffer();  
            while(matcher.find()){  
                matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());  
            }  
            matcher.appendTail(sb);  
            return sb.toString();  
        }
        
        /**
    	 * 根据上次出帐日，算出本次出帐日
    	 * @param lastBillDate
    	 * @param billDate
    	 * @param billIntervalMonth
    	 * @return
    	 */
    	public static String generateCurrentBillDate(String lastBillDate,int billDate,int billIntervalMonth)
    	{
    		String[] arr=lastBillDate.split("-");
    		
    		int month=Integer.valueOf(arr[1]);
    		
    		int year=Integer.valueOf(arr[0]);
    		
    		int day=Integer.valueOf(arr[2]);
    		
    		lastBillDate=year+"-"+String.format("%02d", month)+"-"+String.format("%02d", billDate);
    		
    		int im=0;
    		
    		//出帐日大于上次出帐日，主要针对首次
//    		if(billDate>day)
//    		{
//    			//month=month+billIntervalMonth-1;
//    			im=billIntervalMonth-1;
//    		}
//    		else
//    		{
//    			//month+=billIntervalMonth;
//    			im=billIntervalMonth;
//    			
//    		}
    		im=billIntervalMonth;
//    		if(month>12)
//    		{
//    			int ym=(int)Math.floor(month/12);
//    			
//    			year+=ym;
//    			
//    			month=month-12*ym;
//    		}
    		
    		Calendar cal = Calendar.getInstance();  
            cal.setTime(str2Date(lastBillDate));  
            cal.add(Calendar.MONDAY, im);  
    		
    		return formatDate(cal.getTime());
    		
    		//return year+"-"+String.format("%02d", month)+"-"+String.format("%02d", billDate);
    		
    	}
    	
    	/**
    	 * 取得最后一个应出帐日
    	 * @param lastBillDate
    	 * @param billDate
    	 * @param billIntervalMonth
    	 * @param today
    	 * @return
    	 */
    	public static String getLastCurrentBillDate(String lastBillDate,int billDate,int billIntervalMonth,String today)
    	{
    		String currBillDate="";
    		
    		do{
    			currBillDate=lastBillDate;
    			
    			lastBillDate=Util.generateCurrentBillDate(currBillDate, billDate, billIntervalMonth);			
    			
    		}
    		while(Util.dateDiff(today,lastBillDate)>=0);
    		
    		return currBillDate;
    	}
		
    	
    	/**
    	 * 自定义出帐日
    	 * @param billDatesStr
    	 * @param date:today
    	 * @return
    	 */
    	public static String getBillDate(String billDatesStr,String date)
    	{
    		
    		List<String> billDates=new ArrayList<String>(Arrays.asList(billDatesStr.split(",")));
    		
    		//先asc排序
    		Collections.sort(billDates);

    		
    		String[] darr=date.split("-");
    		
    		String y=darr[0];
    		
    		String d0=y+"-"+billDates.get(0);
    		
    		//今天小于最小出帐日，返回上一年的最大出帐日
    		if(Util.dateDiff(date, d0)<0)
    		{
    			return Integer.valueOf(y)-1+"-"+billDates.get(billDates.size()-1);
    		}
    		
    		String d1=y+"-"+billDates.get(billDates.size()-1);
    		//今天大于最大出帐日，返回今年最大出帐日
    		if(Util.dateDiff(date,d1)>=0)
    		{
    			return d1;
    		}
    		
    		int index=0;
    		
    		//取出小于今天的最大值
    		for(int i=0;i<billDates.size();i++)
    		{			

    			String dd=y+"-"+billDates.get(i);
    			
    			if(Util.dateDiff(date,dd)<0)
    			{
    				index=i-1;
    				
    				break;
    			}
    						
    		}		
    		
    		return y+"-"+billDates.get(index);
    		
    	}
  		
}
