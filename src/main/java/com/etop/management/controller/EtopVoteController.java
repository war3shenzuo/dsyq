package com.etop.management.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopQuestionBank;
import com.etop.management.bean.EtopVote;
import com.etop.management.bean.ResultType;
import com.etop.management.service.EtopVoteService;

/**
 * Created by Alan.
 *
 * @author 吕錫威
 * @DATE 2017/03/14
 */
@Controller
@RequestMapping("/etopVote")
public class EtopVoteController extends BaseAppController{

	private final static Logger LOGGER = Logger.getLogger(EtopVoteController.class);

	@Autowired
	private EtopVoteService etopVoteService;
	


	@RequestMapping("/addPage.do")
	public String addPage(Model model) {
		return "vote/addVote";
	}
	@RequestMapping("/CompanyInfo.do")
	public String etopCompany(Model model) {
		return "etopCompany/updateAdditionalEtopCompanyInfo";
	}


	/**
	 * 新增投票通知
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add.do")
	public Object updateAdditional(String etopVote){

		ResultType result = null;

		try {

			etopVoteService.add(etopVote);

			result = ResultType.getSuccess("新增投票成功");

		} catch (Exception e) {

			result = ResultType.getFail("新增投票失败");

			LOGGER.error("新增投票失败");

			e.printStackTrace();
		}

		return result;

	}
	
    /**
     * 页面显示选项
     *
     * @param selectOption
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectOption.do")
    public List<EtopVote> selectOption(String noticeId,String addressee){
    	
          return etopVoteService.selectOption(noticeId,addressee);
 
    }
    
	/**
	 * 进行投票
	 *
	 * @param etopNotice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateBynoticeId.do")
	public ResultType updateBynoticeId(EtopVote etopVote) {

		ResultType result = null;

		try {

			etopVoteService.updateBynoticeId(etopVote);

			result = ResultType.getSuccess("投票成功! ");

		} catch (Exception e) {

			LOGGER.error("投票失败! ");

			result = ResultType.getFail("投票失败! ");

			e.printStackTrace();
		}

		return result;

	}

}
