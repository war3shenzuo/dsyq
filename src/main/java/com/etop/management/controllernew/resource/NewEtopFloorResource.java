package com.etop.management.controllernew.resource;

import com.etop.management.bean.ResultType;
import com.etop.management.controller.BaseAppController;
import com.etop.management.entity.EtopFloor;
import com.etop.management.servicenew.NewEtopFloorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
    @RequestMapping(value = "/addstorey.do", method = RequestMethod.POST)
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
    @RequestMapping(value = "/addFloor.do", method = RequestMethod.POST)
    public ResultType getFloorList(EtopFloor floor) {
        try {
            newEtopFloorService.addFloor(floor);
            return ResultType.getSuccess("新增成功");

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
            return ResultType.getFail("服务器出错");
        }
    }

}
