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
            newEtopFloorService.addFloor(floor);
            return ResultType.getSuccess("新增成功");

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
    public ResultType getRoomList(String areaId) {

        if (StringUtils.isNotBlank(areaId)) {
            try {
                List<EtopFloorRoom> floor = newEtopFloorService.getRoomListByAreaId(areaId);
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
    @RequestMapping(value = "/room/count.do", method = RequestMethod.GET)
    public ResultType roomNum(String areaId, String storeyId) {

        if (StringUtils.isNotBlank(areaId)) {
            List<Map> floor = newEtopFloorService.getRoomCount(areaId, EtopFloor.TYPE_AREA);
            return ResultType.getSuccess("获取成功", floor);
        } else {
            if (StringUtils.isNotBlank(storeyId)) {
                List<Map> floor = newEtopFloorService.getRoomCount(storeyId, EtopFloor.TYPE_STOREY);
                return ResultType.getSuccess("获取成功", floor);
            } else {
                return ResultType.getFail("参数错误");
            }
        }
    }

}
