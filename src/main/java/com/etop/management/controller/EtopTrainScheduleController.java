package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainSchedule;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopTrainScheduleService;
/**
 * 开课信息
 *
 * <br>
 * <b>功能：</b>EtopTrainScheduleController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopTrainSchedule")
public class EtopTrainScheduleController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopTrainScheduleController.class);

	@Autowired 
	private EtopTrainScheduleService etopTrainScheduleService;

	@RequestMapping("/addTrainSchedulePage.do")
	public String addTrainSchedulePage(Model model, String courseId) {
		model.addAttribute("courseId", courseId);
		return "etopOfflinePrograms/addEtopTrainSchedule";
	}
	@RequestMapping("/addTrainSchedulePage2.do")
	public String addTrainSchedulePage2(Model model, String courseId) {
		model.addAttribute("courseId", courseId);
		return "etopOfflineTraining/addEtopTrainSchedule";
	}

	/**
	 * 开课信息列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopTrainScheduleList.do")
	public Object getEtopTrainScheduleList(EtopTrainSchedule etopTrainSchedule,
											@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopTrainSchedule> page = etopTrainScheduleService.search(etopTrainSchedule, offset, limit);
		return page;
	}

	/**
	 * 新增开课信息
	 *
	 * @param etopTrainSchedule
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopTrainSchedule.do")
	public ResultType addEtopTrainSchedule(EtopTrainSchedule etopTrainSchedule) {

		ResultType result = null;

		try {

			etopTrainScheduleService.add(etopTrainSchedule);

			result = ResultType.getSuccess("新增开课信息成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增开课信息失败! ");

			LOGGER.error("新增开课信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getEtopTrainScheduleInfo.do")
	public String getEtopTrainScheduleInfo(EtopTrainSchedule etopTrainSchedule, Model model) {
		try {
			model.addAttribute("info", etopTrainScheduleService.getEtopTrainScheduleInfoById(etopTrainSchedule.getId()));
		} catch (Exception e) {

			LOGGER.error("查询线上培训信息失败");

			e.printStackTrace();

		}
		return "etopOfflinePrograms/updateEtopTrainSchedule";
	}
	@RequestMapping(value = "/getEtopTrainScheduleInfo2.do")
	public String getEtopTrainScheduleInfo2(EtopTrainSchedule etopTrainSchedule, Model model) {
		try {
			model.addAttribute("info", etopTrainScheduleService.getEtopTrainScheduleInfoById(etopTrainSchedule.getId()));
		} catch (Exception e) {
			
			LOGGER.error("查询线上培训信息失败");
			
			e.printStackTrace();
			
		}
		return "etopOfflineTraining/updateEtopTrainSchedule";
	}

	/**
	 * 更新开课信息
	 *
	 * @param etopTrainSchedule
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTrainSchedule.do")
	public ResultType updateTrainSchedule(EtopTrainSchedule etopTrainSchedule) {

		ResultType result = null;

		try {

			etopTrainScheduleService.updateTrainSchedule(etopTrainSchedule);

			result = ResultType.getSuccess("更新开课信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新开课信息失败! ");

			result = ResultType.getFail("更新开课信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除开课信息
	 *
	 * @param etopTrainSchedule
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEtopTrainScheduleInfo.do")
	public Object delete(EtopTrainSchedule etopTrainSchedule){

		ResultType result = null;

		try {

			Integer size = etopTrainSchedule.getIds().size();
			etopTrainScheduleService.deleteById(etopTrainSchedule);

			result = ResultType.getSuccess("删除线上培训成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除线上培训失败");

			LOGGER.error("删除线上培训失败");

			e.printStackTrace();
		}

		return result;

	}

}
