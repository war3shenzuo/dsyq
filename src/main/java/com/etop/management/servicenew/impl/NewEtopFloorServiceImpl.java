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

import javax.annotation.Resource;
import java.util.*;

/**
 * 新房源服务
 *
 * @author shixianjie
 * @date 2019/4/15
 */
@Service
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
    public List<Map> getRoomCount(String id, String type) {
        return etopFloorRoomDao.getRoomCount( new Criteria().put("id", id).put("idType", type));
    }

    @Override
    public EtopFloor getFloorInfo(String floorId) {

        EtopFloor etopFloor=new EtopFloor();
        etopFloor.setId(floorId);
        List<Map<String,Object>> map = etopFloorDao.getFloorEnergyInfo(floorId);
        EtopFloor ef = etopFloorDao.queryObject(etopFloor);
        ef.setNy(map);
        return ef;

    }


}
