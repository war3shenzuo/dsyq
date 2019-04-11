package com.etop.management.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author   hulibin
 * 
 * @Time 2015年5月8日 下午2:51:10
 * 
 * @Description
 */
public class ProUtil {	
	//公共配置文件路径
	public final static String COMMONPATH="config/common/";
	
	public final static String DBPATH="config/db/";
	
	public final static String PROPERTIESPATH="config/properties/";
	
	//配置文件名
	private String propName;
	private Properties prop;
	
	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}
	public ProUtil(String path,String name){
		// 创建配置文件对象
		 prop = new Properties();
		// 设置配置文件名称
		 this.setPropName(name);
		// 加载配置文件
		try {
			prop.load(ProUtil.class.getClassLoader().
					getResourceAsStream(path+name));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 获取配置文件数据ֵ
	 * @param key  键
	 * @return 值ֵ
	 */
	public  String getPropertiesValue(String key) {
		return prop.getProperty(key);
	}

	public Properties getProp() {
		return prop;
	}
	
	
}

