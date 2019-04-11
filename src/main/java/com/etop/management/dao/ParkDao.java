package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Park;

/**
 * 
 * @author shixianjie
 * 下午4:10:33
 */
public interface ParkDao {

	List<Park> getParkList(Park park);

	Park getParkInfo(String parkId);

	void addPark(Park park);

	void updatePark(Park park);

	void activeOrClosePark(Park park);

	List<Park> getAllParkForParkGroupId(String parkGroupId);

	List<Map> getParkListForControl(String parkGroupId);
	
	String getParkCode(String parkId);

	List<Park> getParkName(@Param("parkIds") List<String> parkIds);

	void updateCode(String parkCode);
	
	List<Map<String,Object>> getParkCitys(Map<String, String> m);

	List<String> getParkIdList();

	List<Park> getParkInfoByCode(String parkCode);

	List<Park> getParkNameList();
	
	List<Park> getParkInfoList(String parkId);
	
	List<Park> getParkNameList2(String parkGroupId);
	
	String getCity(String id);
	
	String getParkTitle(String id);
	
	String getGroupCode(String id);
	
	int getAllParkNum();
	
	int getParkNumByParkGroupName(String parkGroupName);
	
}
