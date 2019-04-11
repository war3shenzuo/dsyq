package com.etop.management.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** 
 * @author zoushaohua
 * @version 创建时间：2016年2月23日 下午3:47:00 
 * 类说明 字符串方法
 */
public class StrUtils {
	
	/** 判断对象是否为空 */
	public static boolean checkObjectIsEmpty(Object object) {
		return object == null ? true : false;
	}

	/** */
	public static String removeLastChar(String message) {
		message = message.substring(0, message.length() - 1);
		return message;
	}

	/** List转Array */
	public static String[] listToArray(List<String> list) {
		return list.toArray(new String[list.size()]);
	}

	/** Array转String */
	public static String arrayToString(String[] targets) {

		if (targets == null || targets.length == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < targets.length; i++) {
			if (i == targets.length - 1) {
				builder.append(targets[i].trim());
			} else {
				builder.append(targets[i]).append(",");
			}
		}
		return builder.toString();
	}

	/** List转String */
	public static String listToString(List<String> targets) {
		
		if (targets == null || targets.size() == 0)
			return "";
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < targets.size(); i++) {
			if (i == targets.size() - 1) {
				builder.append(targets.get(i));
			} else {
				builder.append(targets.get(i)).append(",");
			}
		}
		return builder.toString();
	}
	
	/** Set转String */
	public static String setToString(Set<String> targets) {
		if (targets == null || targets.size() == 0)
			return "";
		StringBuilder builder = new StringBuilder();
		targets.forEach(target->{			
				builder.append(target).append(",");
		});
			
		return builder.toString();
	}

	/** 遍历字符串target在str中的位置 */
	public static List<Integer> foreach(String target, String str) {

		if (null == str || null == target || "".equals(str.trim())
				|| "".equals(target.trim()))
			return null;

		if (str.length() < target.length())
			return null;

		boolean match = false;
		int index = 0;

		List<Integer> list = new ArrayList<Integer>();
		int targetLen = target.length();
		while (true) {
			if (index + targetLen > str.length())
				break;

			match = str.regionMatches(false, index++, target, 0, targetLen);
			if (match) {
				list.add(index - 1);
			}
		}

		if (list == null || list.size() == 0)
			return null;

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> StringToList(String str,String separator){
		if(isEmpty(str))
			return null ;
		
		T[] arr = (T[]) str.split(separator);
		
		List<T> list = new ArrayList<T>();
		for(T obj:arr){
			list.add(obj);
		}
		
		return list ;
	}

	/** 字符串是否为空 */
	public static boolean isEmpty(String str) {

		if (str == null || "".equals(str.trim()))
			return true;

		return false;
	}

	/** 字符串是否不为空 */
	public static boolean isNotEmpty(String str) {

		if (str == null || "".equals(str.trim()))
			return false;

		return true;
	}

	/** 检查obj中columnArr的列是否为空，为空则返回true */
	public static boolean paramIncomplete(Object obj, String[] cloumnArr) throws Exception {
		
		Set<String> colSet = new HashSet<String>();
		for (String colName : cloumnArr) {
			colSet.add(colName);
		}

		// 得到类中的所有属性集合
		Field[] fs = obj.getClass().getDeclaredFields();
		
		// 验证参数是否齐全，不全则返回提示
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的

			if (colSet.contains(f.getName()) && f.get(obj) == null) {
				return true ;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @param str 目标字符串
	 * @param start 从第start个分隔符开始
	 * @param end 从第end个分隔符结束
	 * @param sepator 分隔符
	 * @return
	 * @throws Exception
	 */
	public static String getSubStr(String str, Integer start, Integer end,String sepator) throws Exception {

		if (str == null || "".equals(str.trim()))
			return null;

		String[] arr = str.split(sepator);
		int len = arr.length;

		if (start < 0) {
			start = len + start;
		} else if (start >= len) {
			throw new Exception("start参数越界！");
		}

		if (end == null) {
			end = len;
		} else if (end < 0) {
			end = len + end;
		} else if (end > len) {
			throw new Exception("end参数越界！");
		}

		if (start < 0 || end < 0 || start == end) {
			throw new Exception("参数错误，请检查！");
		}

		int index = 0;

		StringBuilder sb = new StringBuilder(64);

		char[] ches = str.toCharArray();

		char sep = sepator.charAt(0);

		for (char c : ches) {

			if (index >= end)
				break;

			if (c == sep) {
				index++;
			}

			if (index >= start && index < end) {
				sb.append(c);
			}
		}

		return sb.substring(start == 0 ?0:1);
	}
	
	/** Map转Bean */
	public static void transMapToBean(Map<String, String> valMap,Object obj) throws Exception {
		
		if(valMap == null || obj == null)
			return ;

		// 得到类中的所有属性集合
		Field[] fs = obj.getClass().getDeclaredFields();

		// 验证参数是否齐全，不全则返回提示
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的

			String filedName = f.getName();
			String type = f.getType().getSimpleName();

			String val = valMap.get(filedName);

			if (val == null || "".equals(val.trim()))
				continue;

			switch (type) {
			case "String":
				f.set(obj, String.valueOf(val));
				break;
			case "Byte":
				f.set(obj, Byte.valueOf(val));
				break;
			case "Short":
				f.set(obj, Short.valueOf(val));
				break;
			case "Integer":
				f.set(obj, Integer.valueOf(val));
				break;
			case "Long":
				f.set(obj, Long.valueOf(val));
				break;
			case "Float":
				f.set(obj, Float.valueOf(val));
				break;
			case "Double":
				f.set(obj, Double.valueOf(val));
				break;
			default:
				break;
			}
		}

	}
	
	/** 判断字符串是否为数字类型 */
	public final static boolean isNumber(String str){
		
		if(str == null || "".equals(str.trim()))
			return false ;
		
		boolean b = true  ;
		
		try{
			Double.valueOf(str);
		}catch(Exception e){
			b = false ;
		}
		
		return b ;
	}
	
	 

}
