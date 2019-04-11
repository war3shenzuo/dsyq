package com.etop.management.service;


import java.util.List;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Settled;

/**
 * 
 * 园区组服务
 * @author shixianjie
 * 下午2:30:38
 */
public interface ParkGroupService {
	
	/**
	 * 获得园区组列表
	 * @return
	 */
	public EtopPage<ParkGroup> getParkGroupList(ParkGroup group) throws Exception;
	
	/**
	 * 获取园区组详细信息
	 * @param parkGroupId 园区组Id
	 * @return
	 * @throws Exception
	 */
	public ParkGroup getParkGroupInfo(String parkGroupId) throws Exception;
	
	/**
	 * 新增园区组
	 * @param param 园区组信息
	 * @param settled 
	 * @return 
	 * @throws Exception
	 */
	public ResultType addParkGroup(ParkGroup param, Settled settled) throws Exception;
	
	/**
	 * 停用园区组
	 * @param param 园区组Id
	 * @throws Exception
	 */
	public void stopParkGroup(ParkGroup param) throws Exception;
	
	/**
	 * 园区组控件
	 * @return
	 */
	public List<ParkGroup> getParkGroupListForControl();
	
	/**
	 * 查询入驻申请列表
	 * @param settled
	 * @return
	 */
	public EtopPage<Settled> getSettledList(Settled settled);
	
	/**
	 * 查询入驻申请详细信息
	 * @param settledId
	 * @return
	 */
	public Settled getSettledInfo(String settledId);
	
	/**
	 * 更新园区组
	 * @param param
	 * @return 
	 */
	public ResultType updateParkGroup(ParkGroup param);
	
	
    EtopPage<ParkGroupPresentation> getPresentationByParkGroupId(String parkGroupId, Integer offset, Integer limit);
    
	int deletePresentation(String id);

	void updatePresentation(ParkGroupPresentation presentation);
	
    ParkGroupPresentation getPresentationInfoById(String id);
    
    int add(ParkGroupPresentation presentation);
    
	public List<ParkGroup> getParkGroupListForControl2();
}
