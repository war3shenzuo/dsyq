package com.etop.management.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopQuestionBank;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopOnlineTrainingService;
import com.etop.management.service.EtopQuestionBankService;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/31
 */
@Controller
@RequestMapping("/etopQuestionBank")
public class EtopQuestionBankController {

    private final static Logger LOGGER= Logger.getLogger(EtopQuestionBankController.class);

    @Autowired
    private EtopQuestionBankService etopQuestionBankService;

    @Autowired
    private EtopOnlineTrainingService etopOnlineTrainingService;

    @RequestMapping("/addQuestionBankPage.do")
    public String addPage(String titleId, Model model) {
        model.addAttribute("titleId", titleId);
        model.addAttribute("questionBank", etopOnlineTrainingService.getOnlineTrainingInfoById(titleId));
        return "etopQuestionBank/addQuestionBank";
    }

    /**
     * 获取题库列表
     *
     * @param etopQuestionBank
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEtopQuestionBankList.do")
    public Object getEtopQuestionBankList(EtopQuestionBank etopQuestionBank,
                                          @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
                                          @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
        EtopPage<EtopQuestionBank> page = etopQuestionBankService.search(etopQuestionBank, offset, limit);
        return page;

    }

    @RequestMapping(value = "/getEtopQuestionBankInfo.do")
    public String getEtopQuestionBankInfo(EtopQuestionBank etopQuestionBank, Model model, String readonly) {
        try {
            model.addAttribute("readonly", readonly);
            model.addAttribute("id",etopQuestionBank.getId());
            model.addAttribute("questionBank", etopQuestionBankService.getEtopQuestionBankById(etopQuestionBank));
        } catch (Exception e) {

            LOGGER.error("查询题库信息失败");

            e.printStackTrace();

        }
        return "etopQuestionBank/updateQuestionBankInfo";
    }

    /**
     * 修改题目内容
     *
     * @param etopQuestionBank
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateEtopQuestionBankInfo.do")
    public Object update(EtopQuestionBank etopQuestionBank){

        ResultType result = null;

        try {

            etopQuestionBankService.updateById(etopQuestionBank);

            result = ResultType.getSuccess("修改题目内容成功");

        } catch (Exception e) {

            result = ResultType.getFail("修改题目内容失败");

            LOGGER.error("修改题目内容失败");

            e.printStackTrace();
        }

        return result;

    }

    /**
     * 新增题目
     *
     * @param etopQuestionBank
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addEtopQuestionBankInfo.do")
    public Object add(EtopQuestionBank etopQuestionBank){

        ResultType result = null;

        try {

            etopQuestionBankService.add(etopQuestionBank);

            result = ResultType.getSuccess("新增题目成功");

        } catch (Exception e) {

            result = ResultType.getFail("新增题目失败");

            LOGGER.error("新增题目失败");

            e.printStackTrace();
        }

        return result;

    }

    /**
     * 删除题目
     * @param etopQuestionBank
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEtopQuestionBankInfo.do")
    public Object delete(EtopQuestionBank etopQuestionBank){

        ResultType result = null;

        try {

            Integer size = etopQuestionBank.getIds().size();
            etopQuestionBankService.deleteById(etopQuestionBank);

            result = ResultType.getSuccess("删除题目成功", size);

        } catch (Exception e) {

            result = ResultType.getFail("删除题目失败");

            LOGGER.error("删除题目失败");

            e.printStackTrace();
        }

        return result;

    }

    /**
     * 前端页面显示题目内容
     *
     * @param etopQuestionBank
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEtopQuestionBank.do")
    public List<EtopQuestionBank> getEtopQuestionBank(EtopQuestionBank etopQuestionBank){
        try {
            return etopQuestionBankService.getEtopQuestionBank(etopQuestionBank);
        } catch (Exception e) {

            LOGGER.error("获取题目失败");

            e.printStackTrace();

        }
        return null;
    }

    /**
     * 计算答题分数
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/calculateScore.do")
    public Object calculateScore(String titleId, String ids){
        ResultType result = null;

        try {

            result = ResultType.getSuccess("答题成功",etopQuestionBankService.calculateScore(titleId, ids));

        } catch (Exception e) {

            result = ResultType.getFail("答题失败");

            LOGGER.error("答题失败");

            e.printStackTrace();
        }

        return result;
    }

}
