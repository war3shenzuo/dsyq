package com.etop.management.servicenew;

import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;

import java.util.List;
import java.util.Map;


public interface NewEtopFloorService {

    void addFloor(EtopFloor floor) throws Exception;

    //层
    void addStorey(EtopFloor storey) throws Exception;

    //区
    void addArea(EtopFloor storey) throws Exception;

    /**
     * 获取楼的集合
     *
     * @return
     */
    List<EtopFloor> getFloorList() throws Exception;

    List<EtopFloor> getFloorListByPid(String pid) throws Exception;

    /**
     * 获取房间
     *
     * @param areaId
     * @return
     */
    List<EtopFloorRoom> getRoomListByAreaId(String areaId);

    /**
     * 查询房间统计
     *
     * @param id
     * @param type
     * @return
     */
    List<Map> getRoomCount(String id, String type);

    /**
     * 楼的详情
     * @param floorId
     * @return
     */
    EtopFloor getFloorInfo(String floorId);
}
