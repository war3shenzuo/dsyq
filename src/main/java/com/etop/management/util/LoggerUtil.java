package com.etop.management.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

	private static Logger logger;
	private static Logger logger2;
	
	static {
		logger = LogManager.getLogger("accessLogger");
		logger2 = LogManager.getLogger("errorLogger");
	}
	
	public static void error(String msg) {
		logger2.error(msg);
	}
	
	public static void error(Exception e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(out));
		try {
			logger2.error(out.toString("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void warn(String msg) {
		logger.warn(msg);
	}
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	public static void trace(String msg){
		logger.trace(msg);
	}
}
