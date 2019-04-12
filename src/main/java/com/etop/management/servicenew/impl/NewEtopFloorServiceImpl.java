package com.etop.management.servicenew.impl;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopFloorDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.servicenew.NewEtopFloorService;
import com.etop.management.service.impl.EtopFloorServiceImpl;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


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
    public List<EtopFloor> floorList(EtopFloor floor) throws Exception {

        Criteria c = new Criteria().put("status", floor.getStatus()).put("parkId", floor.getParkId()).put("parkIds", floor.getParkIds());

        c.setOrderByClause(" build_name,created_at desc ");

        List<EtopFloor> floorList = etopFloorDao.queryByList(c);

        return floorList;
    }


    @Override
    public EtopFloor floorInfo(String floorId) {

        EtopFloor etopFloor = new EtopFloor();

        etopFloor.setId(floorId);

        List<Map<String, Object>> map = etopFloorDao.getFloorEnergyInfo(floorId);

        EtopFloor ef = etopFloorDao.queryObject(etopFloor);

        ef.setNy(map);

        return ef;
    }


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
    public void addStorey(EtopFloor storey) throws Exception{
        addfloor(storey);
    }

    @Override
    public void addArea(EtopFloor storey) throws Exception {
        addfloor(storey);
    }


    private void addfloor(EtopFloor data) throws Exception{
        data.setParkId(UserInfoUtil.getUserInfo().getParkId());
        data.setId(UUID.randomUUID().toString());
        data.setEnergyLastBillDate(new Date());
        data.setCreatedAt(new Date());
        etopFloorDao.add(data);
    }

    @Override
    public void updateFloor(EtopFloor floor) {


        etopFloorDao.updateBySelective(floor);

        EtopFloorRoom room = new EtopFloorRoom();

        if ("0".equals(floor.getStatus())) {
            if ("floor".equals(floor.getBuildType())) {
                room.setRefFloorId(floor.getId());
            } else if ("storey".equals(floor.getBuildType())) {
                room.setRefStoreyId(floor.getId());
            } else {
                room.setRefAreaId(floor.getId());
            }

            List<String> roomIds = etopFloorRoomDao.queryByList(room.getCriteria()).stream().map(bean -> {
                return bean.getId();
            }).collect(Collectors.toList());

            int checkRow = 0;
            if (roomIds.size() > 0) {
                checkRow = etopFloorRoomDao.checkRoom(roomIds);
            }
            if (checkRow > 0) {
                throw new RuntimeException("有房间正在合同使用中不能停用");
            } else {
                if ("floor".equals(floor.getBuildType())) { //当停用的是楼的时候
                    floor.setStatus("0");
                    etopFloorDao.updateByParentId(floor);//停用楼
//					etopFloorDao.updateByParentId(floor.getId(),"0");//停用楼
                    room.setRefFloorId(floor.getId());
                    List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                    for (int i = 0; i < rooms.size(); i++) {
                        room.setActivated("0");
                        room.setId(rooms.get(i).getId());
                        etopFloorRoomDao.activeOrCloseRoom(room);
                    }//停用房间
                    List<EtopFloor> storey = etopFloorDao.queryByParentId(floor.getId());
                    for (int i = 0; i < storey.size(); i++) {
                        floor.setId(storey.get(i).getId());
                        floor.setStatus("0");
                        etopFloorDao.updateByParentId(floor);
//							etopFloorDao.updateByParentId(storey.get(i).getId(),"0");//停用层
                        List<EtopFloor> Area = etopFloorDao.queryByParentId(storey.get(i).getId());//停用层
                        for (int j = 0; j < Area.size(); j++) {
                            floor.setId(Area.get(j).getId());
                            floor.setStatus("0");
                            etopFloorDao.updateByParentId(floor);//停用区
//							etopFloorDao.updateByParentId(Area.get(i).getId(),"0");//停用区
                        }
                    }

                } else if ("storey".equals(floor.getBuildType())) {//当停用的是层的时候
                    floor.setStatus("0");
                    etopFloorDao.updateByParentId(floor);//停用层
                    room.setRefStoreyId(floor.getId());
                    List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                    for (int i = 0; i < rooms.size(); i++) {
                        room.setActivated("0");
                        room.setId(rooms.get(i).getId());
                        etopFloorRoomDao.activeOrCloseRoom(room);
                    }//停用房间
                    List<EtopFloor> Area = etopFloorDao.queryByParentId(floor.getId());
                    for (int i = 0; i < Area.size(); i++) {
                        floor.setId(Area.get(i).getId());
                        floor.setStatus("0");
                        etopFloorDao.updateByParentId(floor);//停用区
                    }
                } else {//当停用的是区的时候
                    floor.setStatus("0");
                    etopFloorDao.updateByParentId(floor);//停用区
                    room.setRefAreaId(floor.getId());
                    List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                    for (int i = 0; i < rooms.size(); i++) {
                        room.setActivated("0");
                        room.setId(rooms.get(i).getId());
                        etopFloorRoomDao.activeOrCloseRoom(room);//停用房间
                    }
                }
            }
        }
        if ("1".equals(floor.getStatus())) {
            if ("floor".equals(floor.getBuildType())) {//当停用的是楼的时候
                floor.setStatus("1");
                etopFloorDao.updateByParentId(floor);//激活楼
                room.setRefFloorId(floor.getId());
                List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                for (int i = 0; i < rooms.size(); i++) {
                    room.setActivated("1");
                    room.setId(rooms.get(i).getId());
                    etopFloorRoomDao.activeOrCloseRoom(room);
                }//激活房间
                List<EtopFloor> storey = etopFloorDao.queryByParentId(floor.getId());
                for (int i = 0; i < storey.size(); i++) {
                    floor.setId(storey.get(i).getId());
                    floor.setStatus("1");
                    etopFloorDao.updateByParentId(floor);
//						etopFloorDao.updateByParentId(storey.get(i).getId(),"1");
                    List<EtopFloor> Area = etopFloorDao.queryByParentId(storey.get(i).getId());//激活层
                    for (int j = 0; j < Area.size(); j++) {
                        floor.setId(Area.get(j).getId());
                        floor.setStatus("1");
                        etopFloorDao.updateByParentId(floor);
//						etopFloorDao.updateByParentId(Area.get(j).getId(),"1");//激活区
                    }
                }

            } else if ("storey".equals(floor.getBuildType())) {//当停用的是层的时候，激活层，区，房间
                EtopFloor storey = etopFloorDao.queryById(floor.getId());
                EtopFloor floors = etopFloorDao.queryById(storey.getParentId());//查询所在楼
                if ("0".equals(floors.getStatus())) {
                    throw new RuntimeException("请先激活该层所在的楼！");
                } else {
                    floor.setStatus("1");
                    etopFloorDao.updateByParentId(floor);//激活层
//					etopFloorDao.updateByParentId(floor.getId(),"1");//激活层
                    room.setRefStoreyId(floor.getId());
                    List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                    for (int i = 0; i < rooms.size(); i++) {
                        room.setActivated("1");
                        room.setId(rooms.get(i).getId());
                        etopFloorRoomDao.activeOrCloseRoom(room);
                    }//激活房间
                    List<EtopFloor> Area = etopFloorDao.queryByParentId(floor.getId());
                    for (int i = 0; i < Area.size(); i++) {
                        floor.setId(Area.get(i).getId());
                        floor.setStatus("1");
                        etopFloorDao.updateByParentId(floor);
//						etopFloorDao.updateByParentId(Area.get(i).getId(),"1");//激活区
                    }
                }
            } else {//当停用的是区的时候，激活房间
                EtopFloor Area = etopFloorDao.queryById(floor.getId());
                EtopFloor storey = etopFloorDao.queryById(Area.getParentId());
                if ("0".equals(storey.getStatus())) {
                    throw new RuntimeException("请先激活该区所在的层！");
                } else {
                    floor.setStatus("1");
                    etopFloorDao.updateByParentId(floor);
//					etopFloorDao.updateByParentId(floor.getId(),"1");//激活区
                    room.setRefAreaId(floor.getId());
                    List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());
                    for (int i = 0; i < rooms.size(); i++) {
                        room.setActivated("1");
                        room.setId(rooms.get(i).getId());
                        etopFloorRoomDao.activeOrCloseRoom(room);//激活房间
                    }
                }
            }
        }


        if ("floor".equals(floor.getBuildType())) {

            if (floor.getDian().length() <= 3) {

                //添加房源能源
                for (int i = 0; i < 4; i++) {
                    String s = null;
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


            } else {

                //添加房源能源
                for (int i = 0; i < 4; i++) {
                    String s = null;
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

                    m.put("id", s.substring(0, 36));
                    m.put("shareType", s.substring(36, 37));
                    m.put("roomAmountUsed", s.substring(37, 38));

                    etopFloorDao.updateFloorEnergy(m);

                }

            }
        }

    }


    @Override
    public EtopPage<EtopFloorRoom> getRoomList(EtopFloorRoom room) {

        EtopPage<EtopFloorRoom> page = new EtopPage<EtopFloorRoom>();


        int BTablePageNum = (room.getOffset() / room.getLimit()) + 1;

        // 设置分页
        PageHelper.startPage(BTablePageNum, room.getLimit());

        List<EtopFloorRoom> list = etopFloorRoomDao.queryByList(room.getCriteria().put("refAreaId", room.getRefAreaId()));


        PageInfo<EtopFloorRoom> table = new PageInfo<EtopFloorRoom>(list);

        page.setRows(table.getList());
        page.setTotal(table.getTotal());

        return page;
    }

    @Override
    public EtopPage<EtopFloorRoom> getRoomList2(EtopFloorRoom room) {

        EtopPage<EtopFloorRoom> page = new EtopPage<EtopFloorRoom>();


        int BTablePageNum = (room.getOffset() / room.getLimit()) + 1;

        // 设置分页
        PageHelper.startPage(BTablePageNum, room.getLimit());

        List<EtopFloorRoom> list = etopFloorRoomDao.queryByList2(room.getCriteria());


        PageInfo<EtopFloorRoom> table = new PageInfo<EtopFloorRoom>(list);

        page.setRows(table.getList());
        page.setTotal(table.getTotal());

        return page;
    }

    @Override
    public EtopFloorRoom getRoomInfo(String roomId) {

        EtopFloorRoom etopFloorRoom = new EtopFloorRoom();

        etopFloorRoom.setId(roomId);

        EtopFloorRoom room = etopFloorRoomDao.queryObject(etopFloorRoom);

        if (room.getCompanyId() != null && room.getCompanyId().length() > 0) {

            EtopCompany company = ecompanyDao.getCompInfoById(room.getCompanyId());

            if (company != null) {

                room.setCompanyName(company.getCompanyName());

            }
        }

        return room;
    }

    @Override
    public void addRoom(EtopFloorRoom room) {


        room.setId(UUID.randomUUID().toString());

        room.setCreatedAt(new Date());

        Map<String, String> res = etopFloorRoomDao.getFloorIdByAreaId(room.getRefAreaId());

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
    public void updateRoom(EtopFloorRoom room) {

        etopFloorRoomDao.updateBySelective(room);

    }

    private static final String ROOM_STATUS_USE = "1";//房间状态已出租

    private static final String ROOM_STATUS_UNUSE = "0";//房间状态代出租

    @Override
    public boolean bindRoom(String roomId, String companyId, String constractId) {
        try {
            EtopFloorRoom room = new EtopFloorRoom();
            room.setCompanyId(companyId);
            room.setConstractId(constractId);
            room.setFloorStatus(ROOM_STATUS_USE);
            room.setId(roomId);
            etopFloorRoomDao.updateBySelective(room);
            return true;
        } catch (Exception e) {
            LOGGER.error("绑定房间失败", e);
            return false;
        }

    }

    @Override
    public boolean unbindRoom(String roomId) {
        try {
            EtopFloorRoom room = new EtopFloorRoom();
            room.setCompanyId("");
            room.setConstractId("");
            room.setFloorStatus(ROOM_STATUS_UNUSE);
            room.setId(roomId);
            etopFloorRoomDao.updateBySelective(room);
            return true;
        } catch (Exception e) {
            LOGGER.error("解绑房间失败", e);
            return false;
        }
    }

    @Override
    public boolean updateRoomStatus(String roomId, String status) {
        try {
            EtopFloorRoom room = new EtopFloorRoom();
            room.setFloorStatus(status);
            room.setId(roomId);
            etopFloorRoomDao.updateBySelective(room);
            return true;
        } catch (Exception e) {
            LOGGER.error("修改状态失败", e);
            return false;
        }
    }

    @Override
    public List<EtopFloorRoom> getRooms(String refBuildingId, String areaId, String floorStatus, String activated) {
        // xxx Auto-generated method stub
        return this.etopFloorRoomDao.queryRooms(refBuildingId, areaId, floorStatus, activated);
    }

    @Override
    public List<EtopFloorRoom> getRoomsList(EtopFloor floor) {

        List<EtopFloor> floorList = etopFloorDao.queryByList(new Criteria().put("parkId", floor.getParkId()).put("parkIds", floor.getParkIds()));

        List<String> areaIds = new ArrayList<>();

        for (EtopFloor room : floorList) {
            if (("area").equals(room.getBuildType())) {
                areaIds.add(room.getId());
            }
        }

        List<EtopFloorRoom> roomList = etopFloorRoomDao.getRooms(areaIds, floor.getParkId());

        return roomList;
    }


    @Override
    public int getRoomCountByBuilding(String refBuildingId, String recordDate, int activated) {

        return this.etopFloorRoomDao.getRoomCountByBuilding(refBuildingId, recordDate, activated);
    }


    @Override
    public void activeOrCloseRoom(EtopFloorRoom room) {

        //etopFloorRoomDao.queryByList(room.getCriteria())
        //返回有正常合同关联的房间数
        int checkRow = etopFloorRoomDao.checkRoom(Arrays.asList(new String[]{room.getId()}));
        if (checkRow > 0 && "0".equals(room.getActivated())) {
            throw new RuntimeException("该房间正在合同使用中不能停用");
        }
        EtopFloorRoom rooms = etopFloorRoomDao.queryById(room.getId());
        EtopFloor Area = etopFloorDao.queryById(rooms.getRefAreaId());
        if ("0".equals(Area.getStatus())) {
            throw new RuntimeException("请先激活该区所在的区！");
        }

        etopFloorRoomDao.activeOrCloseRoom(room);

    }

    @Override
    public void deleteRoom(EtopFloorRoom room) {
        int checkRow = etopFloorRoomDao.checkRoom(Arrays.asList(new String[]{room.getId()}));
        if (checkRow > 0) {
            throw new RuntimeException("该房间正在合同使用中不能停用");
        }

        etopFloorRoomDao.delete(room);

    }

    @Override
    public void deleteFloor(EtopFloor floor) {

        EtopFloorRoom room = new EtopFloorRoom();

        if ("floor".equals(floor.getBuildType())) {
            room.setRefFloorId(floor.getId());
        } else if ("storey".equals(floor.getBuildType())) {
            room.setRefStoreyId(floor.getId());
        } else {
            room.setRefAreaId(floor.getId());
        }

        List<EtopFloorRoom> rooms = etopFloorRoomDao.queryByList(room.getCriteria());

        List<String> roomIds = rooms.stream().map(bean -> {
            return bean.getId();
        }).collect(Collectors.toList());

        if (roomIds.size() > 0) {
            int checkRow = etopFloorRoomDao.checkRoom(roomIds);
            if (checkRow > 0) {
                throw new RuntimeException("有房间正在合同使用中不能停用");
            } else {

                etopFloorRoomDao.deleteAll(roomIds);
            }

        }
        etopFloorDao.deleteAll(Arrays.asList(new String[]{floor.getId()}));

    }

    @Override
    public List<String> getRoomIdsByParent(String refParentId, int parentType) {

        return this.etopFloorRoomDao.getRoomIdsByParent(refParentId, parentType);
    }

    @Override
    public List<EtopFloor> getFloorListByParent(String refParkId,
                                                String refParentId, String status) {

        Criteria c = new Criteria().put("status", status).put("parkId", refParkId).put("parentId", refParentId);

        c.setOrderByClause(" build_name asc ");

        List<EtopFloor> floorList = etopFloorDao.queryListByParent(c);

        return floorList;
    }

    @Override
    public List<EtopFloorRoom> getFloorRoomListByParent(EtopFloorRoom room) {
        Criteria c = new Criteria().put("activated", room.getActivated())
                .put("refFoorId", room.getRefFloorId())
                .put("refStoreyId", room.getRefStoreyId())
                .put("refAreaId", room.getRefAreaId());

        c.setOrderByClause(" room_num desc ");

        List<EtopFloorRoom> floorRoomList = etopFloorRoomDao.queryByList(c);

        return floorRoomList;
    }


}
