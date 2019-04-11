package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.EtopCompany;
import com.etop.management.entity.EtopFloorRoom;

/**
 * 
 * <br>
 * <b>功能：</b>EtopFloorRoomDao<br>
 */
public interface EtopFloorRoomDao {
	/**添加*/
	public int add(EtopFloorRoom etopFloorRoom);
	/**批量添加*/
	public int adds(List<EtopFloorRoom> list);
	/**全修改*/
	public int update(EtopFloorRoom etopFloorRoom);
	/**条件修改*/
	public int updateBySelective(EtopFloorRoom etopFloorRoom); 	
	/**根据Id删除*/
	public int delete(EtopFloorRoom etopFloorRoom);
	/**根据Id查询*/
	public EtopFloorRoom queryById(String id);
	/**根据类条件查询*/
	public EtopFloorRoom queryObject(EtopFloorRoom etopFloorRoom);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopFloorRoom> queryByList(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopFloorRoom> queryByList2(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopFloorRoom> myDefinedSelect(Criteria criteria);
	/**根据区Id查询 楼层id*/
	public  Map<String,String> getFloorIdByAreaId(String areaId);
	
	/**
	 * 取分区房间列表
	 * @param areaId
	 * @return
	 */
	List<EtopFloorRoom> queryRooms(@Param("refBuildingId")String refBuildingId,@Param("areaId")String areaId,@Param("floorStatus")String floorStatus,@Param("activated")String activated);

	List<EtopFloorRoom> getRooms(@Param("areaIds") List<String> areaIds, @Param("parkId") String parkId);

	float getSumOfBuildArea(@Param("refBuildingId")String refBuildingId);

	List<EtopFloorRoom> getFloorRoomsListByparkId(String parkId);
	
	List<EtopFloorRoom> getFloorRoomsList();

	int getRoomCountByBuilding(@Param("refBuildingId")String refBuildingId,@Param("recordDate")String recordDate,@Param("activated") int activated);

	public int checkRoom(List<String> ids);
	
	public void activeOrCloseRoom(EtopFloorRoom room);
	
	public void deleteAll(List<String> ids);
	
	List<String> getRoomIdsByParent(@Param("refParentId")String refParentId,@Param("parentType") int parentType);
		
	EtopFloorRoom getRoomsNum(@Param("parkId") String parkId);
	
    List<EtopFloorRoom> selectRoomsList();
    
    int updateForLet(String id);
    
    int updateToLet(String id);
}
