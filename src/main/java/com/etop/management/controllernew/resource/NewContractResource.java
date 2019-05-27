package com.etop.management.controllernew.resource;

import com.etop.management.bean.NewContractItem;
import com.etop.management.bean.ResultType;
import com.etop.management.controller.BaseAppController;
import com.etop.management.model.ContractModel;
import com.etop.management.model.NewContractModel;
import com.etop.management.servicenew.NewContractService;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 新的合同接口
 */
@Controller
@RequestMapping("/newContract")
public class NewContractResource extends BaseAppController {

    private final static Logger LOGGER = Logger.getLogger(NewContractResource.class);
    @Autowired
    private NewContractService newContractService;

    /**
     * 新增合同
     *
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public ResultType save(NewContractModel model) {
        ResultType result;

        try {
            Map map;
            if (StringUtils.isNotBlank(model.getId())) {
                map = this.newContractService.updateContract(model);
            } else {
                map = this.newContractService.addContract(model);
            }
            result = ResultType.getSuccess("保存成功", map);
        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail("生成合同失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 提交合同
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/submit.do", method = RequestMethod.POST)
    public ResultType submit(NewContractModel model) {
        ResultType result;

        try {
            this.newContractService.submitContract(model);
            result = ResultType.getSuccess("提交成功");
        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail("提交失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 园长审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/yzCheck.do", method = RequestMethod.PUT)
    public ResultType yzCheck(String id ,String param) {
        ResultType result;

        try {
            this.newContractService.yzCheck(id,param);
            result = ResultType.getSuccess("审核成功");
        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail("审核失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 财务审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cwCheck.do", method = RequestMethod.PUT)
    public ResultType cwCheck(String id ,String param) {
        ResultType result;

        try {
            this.newContractService.cwCheck(id,param);
            result = ResultType.getSuccess("审核成功");
        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail("审核失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 新增合同分期
     *
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveItem.do", method = RequestMethod.POST)
    public ResultType saveItem(NewContractItem item) {
        ResultType result;

        try {
            this.newContractService.saveItem(item);
            result = ResultType.getSuccess("生成合同分期成功");

        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除合同分期
     *
     * @param itemId 分期id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delItem.do", method = RequestMethod.DELETE)
    public ResultType delItem(String itemId, String contractId) {
        ResultType result;

        try {
            this.newContractService.delItem(itemId, contractId);
            result = ResultType.getSuccess("删除合同分期成功");

        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }


    /**
     * 修改合同分期
     *
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateItem.do", method = RequestMethod.POST)
    public ResultType updateItem(NewContractItem item) {
        ResultType result;

        try {
            this.newContractService.updateItem(item);
            result = ResultType.getSuccess("修改合同分期成功");

        } catch (Exception e) {
            LoggerUtil.error(e);
            result = ResultType.getFail(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }


}
