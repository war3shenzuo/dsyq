package com.etop.management.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {
	
	private static ConcurrentMap<String, Integer> countMap;
	private static ConcurrentMap<String, String> lastDateMap;
	private static ConcurrentMap<String, Lock> lockMap; 

	private static SimpleDateFormat dateFormat;
	
	private Sequence() {}
	
	static {
		dateFormat = new SimpleDateFormat("yyMMdd");
		countMap = new ConcurrentHashMap<>();
		lastDateMap = new ConcurrentHashMap<>();
		lockMap = new ConcurrentHashMap<>();
	}

	public static String nextId(String clubId, String type) {
		Lock lock = getLock(clubId);
		try {
			lock.lock();
			String id = null;
			String dateStr = dateFormat.format(new Date());
			int count = 0;
			if(dateStr.equals(getLastDate(clubId))) {
				count = getCount(clubId);
				++ count;
				countMap.put(clubId, count);
			}
			else {
				countMap.put(clubId, 0);
				lastDateMap.put(clubId, dateStr);
			}
			id = String.format("%s_%s_%s_%06d", clubId, type, dateStr, count);
			return id;
		}
		finally {
			lock.unlock();
		}
	}
	
	private static synchronized Lock getLock(String clubId) {
		Lock lock = lockMap.get(clubId);
		if(lock == null) {
			lock = new ReentrantLock();
			lockMap.put(clubId, lock);
		}
		return lock;
	}
	
	private static String getLastDate(String clubId) {
		String lastDate = lastDateMap.get(clubId);
		if(lastDate == null) {
			lastDate = dateFormat.format(new Date());
			lastDateMap.put(clubId, lastDate);
		}
		return lastDate;
	}
	
	private static int getCount(String clubId) {
		Integer count = countMap.get(clubId);
		if(count == null) {
			count = 0;
			countMap.put(clubId, count);
		}
		return count;
	}
}