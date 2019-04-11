package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopCompanyMaintain;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyMaintainService;
import com.etop.management.service.EtopCompanyService;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/23
 */
@Controller
@RequestMapping("/etopCompanyMaintain")
public class EtopCompanyMaintainController extends BaseAppController {
	
	private final static Logger LOGGER= Logger.getLogger(EtopCompanyMaintainController.class);

	@Autowired 
	private EtopCompanyMaintainService etopCompanyMaintainService;

	@Autowired
	private EtopCompanyIntentionService etopCompanyIntentionService;

	@Autowired
	private EtopCompanyService etopCompanyService;

	@RequestMapping("/etopCompanyMaintainList.do")
	public Object etopCompanyMaintainList(Model model){
		model.addAttribute("readonly",isReadOnly("/etopCompanyMaintain/etopCompanyMaintainList.do"));
		return "etopCompanyMaintain/etopCompanyMaintainList";
	}

	@RequestMapping("/addVisitPage.do")
	public Object addPage(String companyId ,String companyType, Model model){
		if(companyType.equals("0") && companyType != null){
			EtopCompanyIntention etopCompanyIntention = etopCompanyIntentionService.getCompIntentionInfoById(companyId);
			model.addAttribute("etopCompanyInfo",etopCompanyIntention);
		}else {
			EtopCompany etopCompany = etopCompanyService.getCompInfoById(companyId);
			model.addAttribute("etopCompanyInfo",etopCompany);
		}
		model.addAttribute("companyId", companyId);
		return "etopCompanyMaintain/addEtopCompanyMaintainInfo";
	}

	/**
	 * 获取拜访记录
	 * @param etopCompanyMaintain
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEtopCompanyMaintainList.do")
	public Object getEtopCompanyMaintainList(EtopCompanyMaintain etopCompanyMaintain,
				 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
				 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopCompanyMaintain> page = etopCompanyMaintainService.search(etopCompanyMaintain, offset, limit);
		return page;

	}

	/**
	* 删除拜访记录
	* @param etopCompanyMaintain
	* @return
 	*/
	@ResponseBody
	@RequestMapping(value = "deleteEtopCompanyMaintainInfo.do")
	public Object delete(EtopCompanyMaintain etopCompanyMaintain){

		ResultType result = null;

		try {

			Integer size = etopCompanyMaintain.getIds().size();
			etopCompanyMaintainService.deleteById(etopCompanyMaintain);

			result = ResultType.getSuccess("删除拜访记录成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除拜访记录失败");

			LOGGER.error("删除拜访记录失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 新增公司拜访记录
	 * @param etopCompanyMaintain
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addEtopCompanyMaintainInfo.do")
	public Object add(EtopCompanyMaintain etopCompanyMaintain, Model model){

		ResultType result = null;

		try {

			etopCompanyMaintainService.add(etopCompanyMaintain);

			result = ResultType.getSuccess("新增公司拜访记录成功");

		} catch (Exception e) {

			result = ResultType.getFail("新增公司拜访记录失败");

			LOGGER.error("新增公司拜访记录失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 获取公司拜访记录
	 *
	 * @param etopCompanyMaintain
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getMaintainInfo.do")
	public Object getMaintainInfo(EtopCompanyMaintain etopCompanyMaintain, Model model, String readonly, String companyType){

		try{
			model.addAttribute("readonly",readonly);
			if(companyType.equals("0") && companyType != null){
				model.addAttribute("maintainInfo",etopCompanyMaintainService.getMaintainInfoByIntentionId(etopCompanyMaintain));
			}else {
				model.addAttribute("maintainInfo",etopCompanyMaintainService.getMaintainInfoById(etopCompanyMaintain));
			}
		} catch (Exception e) {

			LOGGER.error("查询公司详细信息失败");

			e.printStackTrace();

		}
		return "etopCompanyMaintain/updateEtopCompanyMaintainInfo";
	}

	/**
	 * 修改公司拜访记录
	 * @param etopCompanyMaintain
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateEtopCompanyMaintainInfo.do")
	public Object update(EtopCompanyMaintain etopCompanyMaintain){

		ResultType result = null;

		try {

			etopCompanyMaintainService.updateById(etopCompanyMaintain);

			result = ResultType.getSuccess("修改公司拜访记录成功");

		} catch (Exception e) {

			result = ResultType.getFail("修改公司拜访记录失败");

			LOGGER.error("修改公司拜访记录失败");

			e.printStackTrace();
		}

		return result;

	}

}
