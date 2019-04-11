package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.bean.Settled;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>ParkGroupDao<br>
 */
public interface ParkGroupDao {
	
	/**
	 * 获取园区组数据
	 * @return
	 */
	List<ParkGroup> getParkGroupList(ParkGroup group);
	
	/**
	 * 获取详细信息
	 * @param parkGroupId 园区组ID
	 * @return
	 */
	ParkGroup getParkGroupInfo(String parkGroupId);
	
	/**
	 * 新增园区组
	 * @param param
	 */
	void addParkGroup(ParkGroup param);
	
	/**
	 * 暂停全区组
	 * @param param 园区组ID
	 */
	void stopParkGroup(ParkGroup param);
	
	/**
	 * 查询控件的园区组
	 * @return
	 */
	List<ParkGroup> getParkGroupListForControl();
	
	List<ParkGroup> getParkGroupListForControl2();
	
	/**
	 * 获取入驻列表
	 * @param settled
	 * @return
	 */
	List<Settled> getSettledList(Settled settled);
	
	/**
	 * 获取入驻列表详情
	 * @param settledId
	 * @return
	 */
	Settled getSettledInfo(String settledId);
	
	/**
	 * 更新园区组
	 * @param param
	 */
	void updateParkGroup(ParkGroup param);
	
	/**
	 * 停掉所有园区组的账号
	 * @param param
	 */
	void stopGroupUser(Map<String,Object> param);
	/**
	 * 停掉园区组下所有园区
	 * @param param
	 */
	void stopPark(Map<String,Object> param);
	
	/**
	 * 添加入驻信息
	 * @param settled
	 */
	void addSettled(Settled settled);
	
	/**
	 * 查询园区组介绍
	 * @return
	 */
	Page<ParkGroupPresentation> getPresentationByParkGroupId (String parkGroupId);
	
	int deletePresentation(String id);
	
	void updatePresentation(ParkGroupPresentation presentation);
	
    ParkGroupPresentation getPresentationInfoById(String id);
    
    int add(ParkGroupPresentation presentation);

	ParkGroup getParkGroupInfoByCode(String id);
}
