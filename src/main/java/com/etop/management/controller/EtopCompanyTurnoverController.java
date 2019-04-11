package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyTurnover;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.EtopCompanyTurnoverService;
/**
 * 
 * <br>
 * <b>功能：</b>EtopCompanyTurnoverController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopCompanyTurnover")
public class EtopCompanyTurnoverController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopCompanyTurnoverController.class);

	@Autowired 
	private EtopCompanyTurnoverService etopCompanyTurnoverService;

	@Autowired
	private EtopCompanyIntentionService etopCompanyIntentionService;

	@Autowired
	private EtopCompanyService etopCompanyService;

	@RequestMapping("/etopCompanyTurnoverList.do")
	public Object etopCompanyTurnoverList(Model model){
		model.addAttribute("readonly",isReadOnly("/etopCompanyMaintain/etopCompanyMaintainList.do"));
		return "etopCompanyTurnover/etopCompanyTurnoverList";
	}

	@RequestMapping("/addVisitPage.do")
	public Object addPage(String companyId , Model model){
		EtopCompany etopCompany = etopCompanyService.getCompInfoById(companyId);
		model.addAttribute("etopCompanyInfo",etopCompany);
		model.addAttribute("companyId", companyId);
		return "etopCompanyTurnover/addEtopCompanyTurnoverInfo";
	}

	/**
	 * 获取营业额记录
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEtopCompanyTurnoverList.do")
	public Object getEtopCompanyTurnoverList(EtopCompanyTurnover etopCompanyTurnover,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopCompanyTurnover> page = etopCompanyTurnoverService.search(etopCompanyTurnover, offset, limit);
		return page;

	}

	/**
	 * 删除营业额记录
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEtopCompanyTurnoverInfo.do")
	public Object delete(EtopCompanyTurnover etopCompanyTurnover){

		ResultType result = null;

		try {

			Integer size = etopCompanyTurnover.getIds().size();
			etopCompanyTurnoverService.deleteById(etopCompanyTurnover);

			result = ResultType.getSuccess("删除营业额记录成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除营业额记录失败");

			LOGGER.error("删除营业额记录失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 新增营业额记录
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopCompanyTurnoverInfo.do")
	public Object add(EtopCompanyTurnover etopCompanyTurnover, Model model){

		ResultType result = null;

		try {
			// 判断该平台在此月是否有记录
			EtopCompanyTurnover companyTurnover = etopCompanyTurnoverService.selectTurnover(etopCompanyTurnover);
			if(companyTurnover != null){
				result = new ResultType(8888,"该平台在此月已有营业额记录!","");
				return result;
			}else {
				etopCompanyTurnoverService.add(etopCompanyTurnover);
				result = ResultType.getSuccess("新增营业额记录成功! ");
			}
		} catch (Exception e) {

			result = ResultType.getFail("新增营业额记录失败! ");

			LOGGER.error("新增营业额记录失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 获取营业额记录
	 *
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getTurnoverInfo.do")
	public Object getTurnoverInfo(EtopCompanyTurnover etopCompanyTurnover, Model model, String readonly){

		try{
			model.addAttribute("readonly",readonly);
			model.addAttribute("turnoverInfo", etopCompanyTurnoverService.getTurnoverInfo(etopCompanyTurnover));
		} catch (Exception e) {

			LOGGER.error("获取营业额记录失败");

			e.printStackTrace();

		}
		return "etopCompanyTurnover/updateEtopCompanyTurnoverInfo";
	}

	/**
	 * 修改营业额记录
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEtopCompanyTurnoverInfo.do")
	public Object update(EtopCompanyTurnover etopCompanyTurnover){

		ResultType result = null;

		try {
			EtopCompanyTurnover companyTurnover = etopCompanyTurnoverService.selectTurnover(etopCompanyTurnover);
			if(companyTurnover != null){
				result = new ResultType(8888,"该平台在此月已有营业额记录!","");
				return result;
			}else {
				etopCompanyTurnoverService.updateById(etopCompanyTurnover);
				result = ResultType.getSuccess("修改营业额记录成功");
			}

		} catch (Exception e) {

			result = ResultType.getFail("修改营业额记录失败");

			LOGGER.error("修改营业额记录失败");

			e.printStackTrace();
		}

		return result;

	}

}
