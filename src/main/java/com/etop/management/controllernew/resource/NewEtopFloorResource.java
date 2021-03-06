package com.etop.management.controllernew.resource;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.controller.BaseAppController;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.servicenew.NewEtopFloorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 新的房源管理接口
 */
@Controller
@RequestMapping("/newfloor")
public class NewEtopFloorResource extends BaseAppController {

    private final static Logger LOGGER = Logger.getLogger(NewEtopFloorResource.class);
    @Autowired
    private NewEtopFloorService newEtopFloorService;


    @ResponseBody
    @RequestMapping(value = "/addFloor.do", method = RequestMethod.POST)
    public ResultType addFloor(EtopFloor floor) {
        try {
            String floorId = newEtopFloorService.addFloor(floor);
            return ResultType.getSuccess("新增成功", floorId);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }
    }

    /**
     * 新增层
     *
     * @param storey
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStorey.do", method = RequestMethod.POST)
    public ResultType addStorey(EtopFloor storey) {

        try {
            newEtopFloorService.addStorey(storey);
            return ResultType.getSuccess("新增成功");

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }

    }

    /**
     * 新增区
     *
     * @param area
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addArea.do", method = RequestMethod.POST)
    public ResultType addArea(EtopFloor area) {

        try {
            newEtopFloorService.addArea(area);
            return ResultType.getSuccess("新增成功");

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }

    }

    /**
     * 新增区
     *
     * @param room
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRoom.do", method = RequestMethod.POST)
    public ResultType addRoom(EtopFloorRoom room) {

        try {
            newEtopFloorService.addRoom(room);
            return ResultType.getSuccess("新增成功");

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail(e.getMessage());
        }

    }


    @ResponseBody
    @RequestMapping(value = "/floorList.do", method = RequestMethod.GET)
    public ResultType getFloorList() {
        try {
            List<EtopFloor> floor = newEtopFloorService.getFloorList();
            return ResultType.getSuccess("获取成功", floor);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/floorInfo.do", method = RequestMethod.GET)
    public ResultType getFloorInfo(String floorId) {
        try {
            EtopFloor floor = newEtopFloorService.getFloorInfo(floorId);
            return ResultType.getSuccess("获取成功", floor);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }
    }


    //获取层的数据
    @ResponseBody
    @RequestMapping(value = "/storeyList.do", method = RequestMethod.GET)
    public ResultType getStoreyList(String pid) {
        if (StringUtils.isNotBlank(pid)) {
            try {
                List<EtopFloor> floor = newEtopFloorService.getFloorListByPid(pid);
                return ResultType.getSuccess("获取成功", floor);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                e.printStackTrace();
                return ResultType.getFail("服务器出错");
            }
        } else {
            return ResultType.getFail("参数错误");
        }

    }

    //获取层的数据列表
    @ResponseBody
    @RequestMapping(value = "/table/storeyList.do", method = RequestMethod.GET)
    public EtopPage<EtopFloor> getStoreyListTable(String pid) {
        if (StringUtils.isNotBlank(pid)) {
            try {
                List<EtopFloor> floor = newEtopFloorService.getFloorListByPid(pid);
                return new EtopPage<EtopFloor>(floor, floor.size());
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                e.printStackTrace();
                return new EtopPage<EtopFloor>();
            }
        } else {
            return new EtopPage<EtopFloor>();
        }
    }


    //获取层的数据
    @ResponseBody
    @RequestMapping(value = "/areaList.do", method = RequestMethod.GET)
    public ResultType getAreaList(String pid) {

        if (StringUtils.isNotBlank(pid)) {
            try {
                List<EtopFloor> floor = newEtopFloorService.getFloorListByPid(pid);
                return ResultType.getSuccess("获取成功", floor);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                e.printStackTrace();
                return ResultType.getFail("服务器出错");
            }
        } else {
            return ResultType.getFail("参数错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/roomList.do", method = RequestMethod.GET)
    public ResultType getRoomList(String refAreaId, String storeyId, String param) {

        List<EtopFloorRoom> floor = new ArrayList<>();

        try {
            if (StringUtils.isNotBlank(refAreaId)) {
                floor = newEtopFloorService.getRoomListByAreaId(refAreaId, param, EtopFloor.TYPE_AREA);
            }

            if (StringUtils.isNotBlank(storeyId)) {
                floor = newEtopFloorService.getRoomListByAreaId(storeyId, param, EtopFloor.TYPE_STOREY);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }

        return ResultType.getSuccess("获取成功", floor);

    }



    @ResponseBody
    @RequestMapping(value = "/room/count.do", method = RequestMethod.GET)
    public ResultType roomNum(String refAreaId, String storeyId) {

        if (StringUtils.isNotBlank(refAreaId)) {
            Map floor = newEtopFloorService.getRoomCount(refAreaId, EtopFloor.TYPE_AREA);
            return ResultType.getSuccess("获取成功", floor);
        } else {
            if (StringUtils.isNotBlank(storeyId)) {
                Map floor = newEtopFloorService.getRoomCount(storeyId, EtopFloor.TYPE_STOREY);
                return ResultType.getSuccess("获取成功", floor);
            } else {
                return ResultType.getFail("参数错误");
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "/delStorey.do", method = RequestMethod.DELETE)
    public ResultType delStorey(String storeyId) throws Exception {
        if (StringUtils.isNotBlank(storeyId)) {
            try {
                newEtopFloorService.delStorey(storeyId);
                return ResultType.getSuccess("操作成功");

            } catch (Exception e) {
                return ResultType.getFail(e.getMessage());
            }
        } else {
            return ResultType.getFail("参数错误");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/delArea.do", method = RequestMethod.DELETE)
    public ResultType delArea(String areaId) throws Exception {
        if (StringUtils.isNotBlank(areaId)) {
            try {
                newEtopFloorService.delArea(areaId);
                return ResultType.getSuccess("操作成功");

            } catch (Exception e) {
                return ResultType.getFail(e.getMessage());
            }
        } else {
            return ResultType.getFail("参数错误");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/updateFloor.do", method = RequestMethod.PUT)
    public ResultType updateFloor(EtopFloor floor) throws Exception {
        try {
            newEtopFloorService.updateFloor(floor);
            return ResultType.getSuccess("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultType.getFail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateStorey.do", method = RequestMethod.PUT)
    public ResultType updateStorey(EtopFloor floor) throws Exception {
        try {
            newEtopFloorService.updateStorey(floor);
            return ResultType.getSuccess("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultType.getFail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateRoom.do", method = RequestMethod.PUT)
    public ResultType updateRoom(EtopFloorRoom rooom) throws Exception {
        try {
            newEtopFloorService.updateRoom(rooom);
            return ResultType.getSuccess("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultType.getFail(e.getMessage());
        }
    }


}
