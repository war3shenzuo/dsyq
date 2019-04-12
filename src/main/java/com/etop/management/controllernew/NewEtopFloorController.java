package com.etop.management.controllernew;

import com.etop.management.controller.BaseAppController;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 新的房源管理接口
 */
@Controller
@RequestMapping("/newfloor")
public class NewEtopFloorController extends BaseAppController {

    private final static Logger LOGGER = Logger.getLogger(NewEtopFloorController.class);



}
