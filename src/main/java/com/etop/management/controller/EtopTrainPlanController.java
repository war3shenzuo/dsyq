package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainPlan;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopTrainPlanService;
import com.etop.management.service.ParkService;
import com.etop.management.util.UserInfoUtil;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainPlanController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopTrainPlan")
public class EtopTrainPlanController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopTrainPlanController.class);

	@Autowired 
	private EtopTrainPlanService etopTrainPlanService;

	@Autowired
	private ParkService parkService;

	@RequestMapping("/etopTrainPlanList.do")
	public Object etopOfflineTrainingList(Model model){
		model.addAttribute("readonly", isReadOnly("/etopTrainPlan/etopTrainPlanList.do"));
		return "etopTrainPlan/etopTrainPlanList";
	}

	@RequestMapping("/addPage.do")
	public Object addPage(Model model) {
		if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
			model.addAttribute("userType", 4);
			model.addAttribute("parks", parkService.getParkName(getParkIds()));
		}else {
			model.addAttribute("parks", UserInfoUtil.getUserParkInfo().getParkName());
			model.addAttribute("parkId", UserInfoUtil.getUserParkInfo().getId());
		}
		return "etopTrainPlan/addEtopTrainPlan";
	}

	/**
	 * 培训计划列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopTrainPlanList.do")
	public Object getEtopTrainPlanList(EtopTrainPlan etopTrainPlan,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopTrainPlan> page = etopTrainPlanService.search(etopTrainPlan, offset, limit);
		return page;
	}

	/**
	 * 新增线下计划
	 *
	 * @param etopTrainPlan
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopTrainPlan.do")
	public ResultType addEtopTrainPlan(EtopTrainPlan etopTrainPlan) {

		ResultType result = null;

		try {

			etopTrainPlanService.add(etopTrainPlan);

			result = ResultType.getSuccess("新增线下计划成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增线下计划失败! ");

			LOGGER.error("新增线下计划失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getEtopTrainPlanInfo.do")
	public String getEtopTrainPlanInfo(EtopTrainPlan etopTrainPlan, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
				model.addAttribute("userType", 4);
				model.addAttribute("parks", parkService.getParkName(getParkIds()));
			}else {
				model.addAttribute("parks", UserInfoUtil.getUserParkInfo().getParkName());
				model.addAttribute("parkId", UserInfoUtil.getUserParkInfo().getId());
			}
			model.addAttribute("info", etopTrainPlanService.getEtopTrainPlanInfoById(etopTrainPlan.getCourseId()));
		} catch (Exception e) {

			LOGGER.error("查询线下计划信息失败");

			e.printStackTrace();

		}
		return "etopTrainPlan/updateEtopTrainPlan";
	}

	/**
	 * 更新线下计划
	 *
	 * @param etopTrainPlan
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEtopTrainPlan.do")
	public ResultType updateEtopTrainPlan(EtopTrainPlan etopTrainPlan) {

		ResultType result = null;

		try {

			etopTrainPlanService.updateEtopTrainPlan(etopTrainPlan);

			result = ResultType.getSuccess("更新线下计划成功! ");

		} catch (Exception e) {

			LOGGER.error("更新线下计划失败! ");

			result = ResultType.getFail("更新线下计划失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除线下计划
	 *
	 * @param etopTrainPlan
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteEtopTrainPlan.do")
	public Object delete(EtopTrainPlan etopTrainPlan){

		ResultType result = null;

		try {

			etopTrainPlanService.deleteById(etopTrainPlan);

			result = ResultType.getSuccess("删除线下计划成功");

		} catch (Exception e) {

			result = ResultType.getFail("删除线下计划失败");

			LOGGER.error("删除线下计划失败");

			e.printStackTrace();
		}

		return result;

	}

}
