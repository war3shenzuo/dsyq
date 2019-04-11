package com.etop.management.properties;

import com.etop.management.util.ProUtil;


//import com.etop.management.util.ProUtil;


/**
 * 内部成员名单
 * @author Administrator
 *
 */
/**
 * 
 * @author   hulibin
 * 
 * @Time 2015年5月8日 下午2:49:18
 * 
 * @Description 二联小二配置获取类
 */
public class ImgProperties {
	//保存地址
	public static String SAVE_PATH;
	//下载地址
	public static String LOAD_PATH;
	
	static {
		ProUtil pro=new  ProUtil(ProUtil.PROPERTIESPATH,"img.properties");
		SAVE_PATH=pro.getPropertiesValue("savePath");
		LOAD_PATH=pro.getPropertiesValue("loadPath");
	}
	
}
