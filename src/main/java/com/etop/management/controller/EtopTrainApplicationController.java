package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainApplication;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopTrainApplicationService;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainApplicationController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopTrainApplication")
public class EtopTrainApplicationController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopTrainApplicationController.class);

	@Autowired 
	private EtopTrainApplicationService etopTrainApplicationService;

	@RequestMapping("/addTrainApplicationPage.do")
	public String addTrainApplicationPage(Model model, String trainingId) {
		model.addAttribute("trainingId",trainingId);
		return "etopTrainApplication/addEtopTrainApplication";
	}
	
	@RequestMapping("/TrainApplicationPage.do")
	public String TrainApplicationPage(Model model) {
		return "personal/TrainApplicationList";
	}
	
	@RequestMapping("/TrainApplicationActivity.do")
	public String TrainApplicationActivity(Model model) {
		return "personal/TrainApplicationListActivity";
	}
	
	@ResponseBody
	@RequestMapping("/getTrainApplicationList.do")
	public Object getTrainApplicationList(EtopTrainApplication etopTrainApplication,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		etopTrainApplication.setUserId(UserInfoUtil.getUserInfo().getId());
		EtopPage<EtopTrainApplication> page = etopTrainApplicationService.search(etopTrainApplication, offset, limit);
		return page;
	}
	/**
	 * 学员信息列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopTrainApplicationList.do")
	public Object getEtopTrainApplicationList(EtopTrainApplication etopTrainApplication,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopTrainApplication> page = etopTrainApplicationService.search(etopTrainApplication, offset, limit);
		return page;
	}

	/**
	 * 新增学员信息
	 *
	 * @param etopTrainApplication
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopTrainApplication.do")
	public ResultType addEtopTrainApplication(EtopTrainApplication etopTrainApplication) {

		ResultType result = null;

		try {

			etopTrainApplicationService.add(etopTrainApplication);

			result = ResultType.getSuccess("新增学员信息成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增学员信息失败! ");

			LOGGER.error("新增学员信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getTrainApplicationInfo.do")
	public String getTrainApplicationInfo(EtopTrainApplication etopTrainApplication, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			model.addAttribute("id",etopTrainApplication.getId());
			model.addAttribute("info", etopTrainApplicationService.getTrainApplicationInfoById(etopTrainApplication.getId()));
		} catch (Exception e) {

			LOGGER.error("查询学员信息失败");

			e.printStackTrace();

		}
		return "etopTrainApplication/updateEtopTrainApplication";
	}
	//个人
	@RequestMapping(value = "/getTrainApplicationInfo2.do")
	public String getTrainApplicationInfo2(EtopTrainApplication etopTrainApplication, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			model.addAttribute("id",etopTrainApplication.getId());
			model.addAttribute("info", etopTrainApplicationService.getTrainApplicationInfoById(etopTrainApplication.getId()));
		} catch (Exception e) {
			
			LOGGER.error("查询学员信息失败");
			
			e.printStackTrace();
			
		}
		return "personal/updateTrainApplication";
	}

	/**
	 * 更新学员信息
	 *
	 * @param etopTrainApplication
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTrainApplication.do")
	public ResultType updateTrainApplication(EtopTrainApplication etopTrainApplication) {

		ResultType result = null;

		try {

			etopTrainApplicationService.updateTrainApplication(etopTrainApplication);

			result = ResultType.getSuccess("更新学员信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新学员信息失败! ");

			result = ResultType.getFail("更新学员信息训失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除学员信息
	 *
	 * @param etopTrainApplication
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTrainApplicationInfo.do")
	public Object delete(EtopTrainApplication etopTrainApplication){

		ResultType result = null;

		try {

			Integer size = etopTrainApplication.getIds().size();
			etopTrainApplicationService.deleteById(etopTrainApplication);

			result = ResultType.getSuccess("删除学员信息成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除学员信息失败");

			LOGGER.error("删除学员信息失败");

			e.printStackTrace();
		}

		return result;

	}
}
