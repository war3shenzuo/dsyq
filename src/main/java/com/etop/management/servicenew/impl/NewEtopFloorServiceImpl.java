package com.etop.management.servicenew.impl;

import com.etop.management.bean.Criteria;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopFloorDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.servicenew.NewEtopFloorService;
import com.etop.management.service.impl.EtopFloorServiceImpl;
import com.etop.management.util.UserInfoUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 新房源服务
 *
 * @author shixianjie
 * @date 2019/4/15
 */
@Service
@Transactional
public class NewEtopFloorServiceImpl implements NewEtopFloorService {

    @Resource
    private EtopFloorDao etopFloorDao;
    @Resource
    private EtopFloorRoomDao etopFloorRoomDao;
    @Resource
    private EtopCompanyDao ecompanyDao;

    private final static Logger LOGGER = Logger.getLogger(EtopFloorServiceImpl.class);

    @Override
    public void addFloor(EtopFloor floor) throws Exception {
        addfloor(floor);
        floor.setFloormj(floor.getBuildArea());
        etopFloorDao.insertFloorFz(floor);

        for (int i = 0; i < 4; i++) {
            String s;
            Map<String, String> m = new HashMap<String, String>();
            if (i == 0) {
                s = floor.getDian();
            } else if (i == 1) {
                s = floor.getShui();
            } else if (i == 2) {
                s = floor.getRanqi();
            } else {
                s = floor.getKongtiao();
            }
            m.put("id", UUID.randomUUID().toString());
            m.put("floorId", floor.getId());
            m.put("energyType", s.substring(0, 1));
            m.put("shareType", s.substring(1, 2));
            m.put("roomAmountUsed", s.substring(2, 3));
            etopFloorDao.addFloorEnergy(m);
        }
    }

    @Override
    public void addStorey(EtopFloor storey) throws Exception {
        addfloor(storey);
    }

    @Override
    public void addArea(EtopFloor storey) throws Exception {
        addfloor(storey);
    }


    private void addfloor(EtopFloor data) throws Exception {
        data.setParkId(UserInfoUtil.getUserInfo().getParkId());
        data.setId(UUID.randomUUID().toString());
        data.setEnergyLastBillDate(new Date());
        data.setCreatedAt(new Date());
        etopFloorDao.add(data);
    }


    @Override
    public List<EtopFloor> getFloorList() {
        Criteria c = new Criteria().put("parkId", UserInfoUtil.getUserInfo().getParkId());
        c.setOrderByClause(" build_name asc ");
        List<EtopFloor> floorList = etopFloorDao.queryFloorList(c);

        //计算房间数量
        for (EtopFloor floor : floorList) {
            floor.setRoomNum(String.valueOf(etopFloorRoomDao.queryByCount(new Criteria().put("refFloorId", floor.getId()))));
        }

        return floorList;
    }

    @Override
    public List<EtopFloor> getFloorListByPid(String pid) throws Exception {
        Criteria c = new Criteria().put("parentId", pid);
        c.setOrderByClause(" build_name asc ");
        List<EtopFloor> floorList = etopFloorDao.queryByList(c);

        return floorList;
    }

    @Override
    public List<EtopFloorRoom> getRoomListByAreaId(String areaId) {
        return etopFloorRoomDao.queryRooms(null, areaId, null, "1");
    }

    @Override
    public Map getRoomCount(String id, String type) {
        Map map = new HashMap();
        map.put("0", 0);
        map.put("1", 0);
        map.put("2", 0);
        map.put("3", 0);

        List<Map> list = etopFloorRoomDao.getRoomCount(new Criteria().put("id", id).put("idType", type));
        for (Map m : list) {
            map.put(m.get("floorStatus"), m.get("count"));
        }
        return map;
    }

    @Override
    public EtopFloor getFloorInfo(String floorId) {

        EtopFloor etopFloor = new EtopFloor();
        etopFloor.setId(floorId);
        List<Map<String, Object>> map = etopFloorDao.getFloorEnergyInfo(floorId);
        EtopFloor ef = etopFloorDao.queryObject(etopFloor);
        ef.setNy(map);
        Map fz = etopFloorDao.queryFloorFz(floorId);
        if (fz != null) {
            ef.setFloormj((String) fz.get("floormj"));
            ef.setRoomdj((String) fz.get("roomdj"));
            ef.setRoomyj((String) fz.get("roomyj"));
        }
        return ef;

    }

    @Override
    public void addRoom(EtopFloorRoom room) throws Exception {
        room.setId(UUID.randomUUID().toString());
        room.setCreatedAt(new Date());

        Map<String, String> res = etopFloorRoomDao.getFloorIdByAreaId(room.getRefAreaId());
        if (res == null) {
            throw new RuntimeException("没有找到对应的区域");
        }
        room.setRefStoreyId(res.get("id"));
        room.setRefFloorId(res.get("parentId"));
        //根据区id查楼和层id
        EtopFloorRoom checkRoom = new EtopFloorRoom();
        checkRoom.setRoomNum(room.getRoomNum());
        checkRoom.setRefStoreyId(res.get("id"));
        EtopFloorRoom check = etopFloorRoomDao.queryObject(checkRoom);
        if (check != null) {
            throw new RuntimeException("房间号已经存在");
        }
        etopFloorRoomDao.add(room);
    }

    @Override
    public void delArea(String areaId) throws Exception {

        //判断是否还有房间没有删除
        int count = etopFloorRoomDao.queryByCount(new Criteria().put("refAreaId", areaId));
        if (count > 0) {
            throw new RuntimeException("此区域还有房间!请先删除房间");
        }
        EtopFloor ef = new EtopFloor();
        ef.setId(areaId);
        //删掉区的楼房
        etopFloorDao.delete(ef);
    }

    @Override
    public void delStorey(String storeyId) {
        //判断楼层是否还有区
        int count = etopFloorDao.queryByCount(new Criteria().put("parentId", storeyId));
        if (count > 0) {
            throw new RuntimeException("此楼层还有区域!请先删除区域");

        }
        EtopFloor ef = new EtopFloor();
        ef.setId(storeyId);
        //删掉区的楼房
        etopFloorDao.delete(ef);

    }

    @Override
    public void updateFloor(EtopFloor floor) throws Exception {
        updateAllFloor(floor);
        etopFloorDao.updateFloorFz(floor);
    }

    @Override
    public void updateStorey(EtopFloor floor) {
        updateAllFloor(floor);
    }

    @Override
    public void updateRoom(EtopFloorRoom room) throws Exception {

        //楼设置的阀值检测
        checkFz(room);

        etopFloorRoomDao.updateBySelective(room);

    }


    public void updateAllFloor(EtopFloor floor) {
        etopFloorDao.updateBySelective(floor);
    }


    public void checkFz(EtopFloorRoom room) throws Exception {
        //获取阀值
        Map<String, Integer> fz = etopFloorDao.queryFloorFz(room.getRefFloorId());
        if(fz==null){
            throw new RuntimeException("找不到房间所在的楼");
        }
        //房间单价是否合规
        if (Integer.parseInt(room.getDayPrice()) < fz.get("roomdj")) {
            throw new RuntimeException("房间单价不能小于楼房设定的单价");
        }
        //房间面积是否大于楼的总面积
        int total = etopFloorRoomDao.getRoomAreaSumTotal(room.getRefFloorId());

        if (total + room.getBuildArea() <= fz.get("floormj")) {
            throw new RuntimeException("所有房间总面积啊小于楼房设定的面积");
        }
        //房间押金是否大于规定
        if (Integer.parseInt(room.getYj()) < fz.get("roomyj")) {
            throw new RuntimeException("房间押金不能小于楼房设定的押金");

        }
    }


}
