package com.etop.management.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	private static Logger logger = LoggerFactory.getLogger("Client");
	
	public static void info(String msg ,Throwable t){
		logger.info(msg, t);
	}
	
	public static void info(String msg){
		logger.info(msg);
	}

}
