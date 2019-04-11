package com.etop.management.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.bean.Rents;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.RentsDao;
import com.etop.management.service.RentsService;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/8
 */
@Service
public class RentsServiceImpl implements RentsService {

    @Autowired
    private RentsDao rentsDao;

    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<Rents> search(Rents rents, String type) {

        Page<Rents> list = new Page<Rents>();
        if(("all").equals(type)){
            for(int i=0;i<rents.getParkIds().size();i++){
                DecimalFormat df = new DecimalFormat("##%");
                rents.setParkId(rents.getParkIds().get(i));
                Rents rentEnty = rentsDao.select(rents);//建筑面积
                if(rentEnty.getParkName() == null){
                    Park park = parkDao.getParkInfo(rents.getParkIds().get(i));
                    rentEnty.setParkName(park.getParkName());
                }
                Double userArea = rentsDao.selectUserAreaByParkId(rents.getParkIds().get(i));//激活状态房间使用面积
                Double rentArea = rentsDao.selectRentArea(rents.getParkIds().get(i));//出租面积
                Double vacantArea = rentsDao.selectVacatArea(rents.getParkIds().get(i));//空置面积

                if(rentEnty.getBuildArea() == 0){//得房率
                    rentEnty.setRoomRate("0");
                }else {
                    rentEnty.setRoomRate(df.format(userArea / rentEnty.getBuildArea()));//得房率
                    rentEnty.setRents(df.format(rentArea / rentEnty.getBuildArea()));//出租率
                    rentEnty.setVacancyRate(df.format(vacantArea / rentEnty.getBuildArea()));//空置率
                }
                rentEnty.setUserArea(userArea);
                rentEnty.setRentArea(rentArea);
                rentEnty.setVacantArea(vacantArea);
                list.add(rentEnty);
            }
        }else {
//            DecimalFormat df = new DecimalFormat("##%");
            DecimalFormat df = new DecimalFormat("#.00%");
            Rents rentEnty = rentsDao.select(rents);
            if(rentEnty.getParkName() == null){
                Park park = parkDao.getParkInfo(rents.getParkId());
                rentEnty.setParkName(park.getParkName());
            }
            Double userArea = rentsDao.selectUserAreaByParkId(rents.getParkId());//激活状态房间使用面积
            Double rentArea = rentsDao.selectRentArea(rents.getParkId());//出租面积
            Double vacantArea = rentsDao.selectVacatArea(rents.getParkId());//空置面积

            if(rentEnty.getBuildArea() == 0){//得房率
                rentEnty.setRoomRate("0");
                rentEnty.setRents("0");
                rentEnty.setVacancyRate("0");
            }else {
                rentEnty.setRoomRate(df.format(userArea / rentEnty.getBuildArea()));//得房率
                rentEnty.setRents(df.format(rentArea / rentEnty.getBuildArea()));//出租率
                rentEnty.setVacancyRate(df.format(vacantArea / rentEnty.getBuildArea()));//空置率
            }
            rentEnty.setUserArea(userArea);
            rentEnty.setRentArea(rentArea);
            rentEnty.setVacantArea(vacantArea);
            list.add(rentEnty);

            //楼
            Page<Rents> floorList = rentsDao.selectByFloorIds(rents);
            for(Rents _rents : floorList){
                Double userAreaFloor = rentsDao.selectUserArea(_rents.getId(), rents.getParkId());//激活状态房间使用面积
                Double rentAreaFloor = rentsDao.selectRentAreaFloorId(_rents.getId(), rents.getParkId());//出租面积
                Double vacantAreaFloor = rentsDao.selectVacatAreaFloorId(_rents.getId(), rents.getParkId());//空置面积

                if(_rents.getBuildArea() == 0){
                    _rents.setRoomRate("0");
                    _rents.setRents("0");
                    _rents.setVacancyRate("0");
                }else {
                    _rents.setRoomRate(df.format(userAreaFloor / _rents.getBuildArea()));//得房率
                    _rents.setRents(df.format(rentAreaFloor / _rents.getBuildArea()));//出租率
                    _rents.setVacancyRate(df.format(vacantAreaFloor / _rents.getBuildArea()));//空置率
                }
                _rents.setUserArea(userAreaFloor);
                _rents.setRentArea(rentAreaFloor);
                _rents.setVacantArea(vacantAreaFloor);
                list.add(_rents);
            }

            //层
            Page<Rents> storeyList = rentsDao.selectByStoreyIds(rents);
            for(Rents _rents : storeyList){
                Double userAreaStorey = rentsDao.selectUserAreaByStoreyId(_rents.getId(), rents.getParkId());//激活状态房间使用面积
                Double rentAreaStorey = rentsDao.selectRentAreaStoreyId(_rents.getId(), rents.getParkId());//出租面积
                Double vacantAreaStorey = rentsDao.selectVacatAreaStoreyId(_rents.getId(), rents.getParkId());//空置面积
                if(_rents.getBuildArea() == 0){
                    _rents.setRoomRate("0");
                    _rents.setRents("0");
                    _rents.setVacancyRate("0");
                }else {
                    _rents.setRoomRate(df.format(userAreaStorey / _rents.getBuildArea()));//得房率
                    _rents.setRents(df.format(rentAreaStorey / _rents.getBuildArea()));//出租率
                    _rents.setVacancyRate(df.format(vacantAreaStorey / _rents.getBuildArea()));//空置率
                }
                _rents.setUserArea(userAreaStorey);
                _rents.setRentArea(rentAreaStorey);
                _rents.setVacantArea(vacantAreaStorey);
                list.add(_rents);
            }

            //区
            Page<Rents> areaList = rentsDao.selectByAreaIds(rents);
            for(Rents _rents : areaList){
                Double userAreaArea = rentsDao.selectUserAreaByAreaId(_rents.getId(), rents.getParkId());//激活状态房间使用面积
                Double rentAreaArea = rentsDao.selectRentAreaAreaId(_rents.getId(), rents.getParkId());//出租面积
                Double vacantAreaArea = rentsDao.selectVacatAreaAreaId(_rents.getId(), rents.getParkId());//空置面积
                if(_rents.getBuildArea() == 0){
                    _rents.setRoomRate("0");
                    _rents.setRents("0");
                    _rents.setVacancyRate("0");
                }else {
                    _rents.setRoomRate(df.format(userAreaArea / _rents.getBuildArea()));//得房率
                    _rents.setRents(df.format(rentAreaArea / _rents.getBuildArea()));//出租率
                    _rents.setVacancyRate(df.format(vacantAreaArea / _rents.getBuildArea()));//空置率
                }
                _rents.setUserArea(userAreaArea);
                _rents.setRentArea(rentAreaArea);
                _rents.setVacantArea(vacantAreaArea);
                list.add(_rents);
            }
        }

        return new EtopPage<Rents>(list);
    }

}
