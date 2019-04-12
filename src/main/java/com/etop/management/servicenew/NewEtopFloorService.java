package com.etop.management.servicenew;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;

import java.util.List;


public interface NewEtopFloorService {
	
	//楼
	List<EtopFloor> floorList(EtopFloor floor ) throws Exception;
	
	EtopFloor floorInfo(String floorId);
	
	void addFloor(EtopFloor floor) throws Exception;

	void updateFloor(EtopFloor floor);

    //层
    void addStorey(EtopFloor storey) throws Exception;

    //区
    void addArea(EtopFloor storey) throws Exception;


	//层
	/**
	 * 取子类
	 * @param refParkId
	 * @param refParentId
	 * @param status
	 * @return
	 */
	List<EtopFloor> getFloorListByParent(String refParkId, String refParentId, String status);

	/**
	 * 取房间列表，
	 * @param room floor,storage,area
	 * @return
	 */
	List<EtopFloorRoom> getFloorRoomListByParent(EtopFloorRoom room);

	//房间

	EtopPage<EtopFloorRoom> getRoomList(EtopFloorRoom room);

	EtopPage<EtopFloorRoom> getRoomList2(EtopFloorRoom room);

	EtopFloorRoom getRoomInfo(String roomId);

	void addRoom(EtopFloorRoom room);

	void updateRoom(EtopFloorRoom room);

	//对合同的相关接口
	boolean bindRoom(String roomId, String companyId, String constractId);

	boolean unbindRoom(String roomId);

    //对意向的接口
	boolean updateRoomStatus(String roomId, String status);


	List<EtopFloorRoom> getRooms(String refBuildingId, String areaId, String floorStatus, String activated);

	List<EtopFloorRoom> getRoomsList(EtopFloor floor);

	/**
	 * 取楼房间数量
	 * @param refBuildingId
	 * @param activated
	 * @return
	 */
	int getRoomCountByBuilding(String refBuildingId, String recordDate, int activated);

	void activeOrCloseRoom(EtopFloorRoom room);

	void deleteRoom(EtopFloorRoom room);

	void deleteFloor(EtopFloor floor);

	/**
	 * 根据父类取房间ID列表
	 * @param refParentId
	 * @param parentType
	 * @return
	 */
	List<String> getRoomIdsByParent(String refParentId, int parentType);


}
