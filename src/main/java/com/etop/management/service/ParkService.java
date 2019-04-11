package com.etop.management.service;


import java.util.List;
import java.util.Map;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.model.TreeModel;

/**
 * 
 * 园区服务
 * @author shixianjie
 * 下午2:30:38
 */
public interface ParkService {
	
	/**
	 * 获得园区列表
	 * @return
	 */
	public EtopPage<Park> getParkList(Park park) throws Exception;
	
	/**
	 * 获取园区详细信息
	 * @param parkGroupId 园区Id
	 * @return
	 * @throws Exception
	 */
	public Park getParkInfo(String parkId) throws Exception;
	
	
	/**
	 * 获取园区详细信息
	 * @param parkGroupId 园区Id
	 * @return
	 * @throws Exception
	 */
	public List<Park> getParkInfoByCode(String parkCode) throws Exception;
	
	/**
	 * 新增园区
	 * @param param 园区信息
	 * @throws Exception
	 */
	public void addPark(Park param) throws Exception;
	
	/**
	 * 停用园区
	 * @param parkGroupId 园区Id
	 * @throws Exception
	 */
	public void activeOrClosePark(Park param) throws Exception;
	
	/**
	 * 修改园区
	 * @param park
	 * @throws Exception 
	 */
	public void updatePark(Park park) throws Exception;
	
	/**
	 * 获取控件中显示的园区信息
	 * @param parkGroupId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getParkListForControl();
	
	/**
	 * 获取当前用户的园区组下的所有园区
	 * @return
	 */
	public List<String> getAllParkId();

	List<Park> getParkName(List<String> parkIds);

	List<String> getParkIdList();

	List<Park> getParkNameList();
	
	List<Park> getParkInfoList(String parkId);
	
	List<Park> getParkNameList2(String parkGroupId);

}
