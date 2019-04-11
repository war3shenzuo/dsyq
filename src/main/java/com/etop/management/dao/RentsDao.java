package com.etop.management.dao;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Rents;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 * 出租率报表
 *
 * @author 何利庭
 * @DATE 2016/10/8
 */
public interface RentsDao {

    //园区
    Rents select(Rents rents);//园区总建筑面积

    Double selectUserAreaByParkId(@Param("parkId") String parkId);//激活状态房间使用面积

    Double selectRentArea(@Param("parkId") String parkId);//出租面积

    Double selectVacatArea(@Param("parkId") String parkId);//空置面积

    Double selectByParkId(@Param("parkId") String parkId);//园区总房间数(现在没用)

    //楼
    Page<Rents> selectByFloorIds(Rents rents);//楼建筑面积

    Double selectUserArea(@Param("id") String id, @Param("parkId") String parkId);//激活状态房间使用面积

    Double selectRentAreaFloorId(@Param("id") String id, @Param("parkId") String parkId);//出租面积

    Double selectVacatAreaFloorId(@Param("id") String id, @Param("parkId") String parkId);//空置面积

    Double selectByFloorId(@Param("floorId") String floorId);//楼下面已出租的房间数(现在没用)


    //层
    Page<Rents> selectByStoreyIds(Rents rents);//层建筑面积

    Double selectUserAreaByStoreyId(@Param("id") String id, @Param("parkId") String parkId);//激活状态房间使用面积

    Double selectRentAreaStoreyId(@Param("id") String id, @Param("parkId") String parkId);//出租面积

    Double selectVacatAreaStoreyId(@Param("id") String id, @Param("parkId") String parkId);//空置面积

    Double selectByStoreyId(@Param("storeyId") String storeyId);//(现在没用)

    //区
    Page<Rents> selectByAreaIds(Rents rents);//区建筑面积

    Double selectUserAreaByAreaId(@Param("id") String id, @Param("parkId") String parkId);//激活状态房间使用面积

    Double selectRentAreaAreaId(@Param("id") String id, @Param("parkId") String parkId);//出租面积

    Double selectVacatAreaAreaId(@Param("id") String id, @Param("parkId") String parkId);//空置面积

    Double selectByAreaId(@Param("areaId") String areaId);//(现在没用)

    /*Page<Rents> selectByFloor(Rents rents);

    Page<Rents> selectByStorey(Rents rents);

    Page<Rents> selectByArea(Rents rents);

    Page<Rents> selectByRoom(Rents rents);

    Rents selectById(@Param("id") String id);

    Double selectRentNumByArea(@Param("id") String id);//区下面已出租的房间数

    Rents selectByParentIdStorey(@Param("id") String id);

    Double selectRentNumByStorey(@Param("id") String id);//层下面已出租的房间数

    Rents selectByParentIdFloor(@Param("id") String id);

    Double selectRentNumByFloor(@Param("id") String id);//楼下面已出租的房间数*/


}
