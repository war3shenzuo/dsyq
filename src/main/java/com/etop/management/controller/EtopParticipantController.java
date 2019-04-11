package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopParticipant;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopParticipantService;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopParticipantController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopParticipant")
public class EtopParticipantController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopParticipantController.class);

	@Autowired 
	private EtopParticipantService etopParticipantService;

	@RequestMapping("/ParticipantListPage.do")
	public String TrainApplicationPage(Model model) {
		return "personal/ParticipantList";
	}
	
	
	/**
	 * 获取参与人列表
	 *
	 * @param etopParticipant
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEtopParticipantList.do")
	public Object getEtopParticipantList(EtopParticipant etopParticipant,
										  @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
										  @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		if("2".equals(UserInfoUtil.getUserInfo().getUserType())){
			etopParticipant.setUserId(UserInfoUtil.getUserInfo().getId());
		}
		EtopPage<EtopParticipant> page = etopParticipantService.search(etopParticipant, offset, limit);
		return page;

	}

	@RequestMapping(value = "/getEtopParticipantInfo.do")
	public String getEtopParticipantInfo(EtopParticipant etopParticipant, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			model.addAttribute("titleId",etopParticipant.getCourseId());
			model.addAttribute("info", etopParticipantService.getEtopParticipantById(etopParticipant));
		} catch (Exception e) {

			LOGGER.error("查询题库信息失败");

			e.printStackTrace();

		}
		return "personal/updateParticipantList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAvg.do")
	public Object getAvg(EtopParticipant etopParticipant,
										  @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
										  @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopParticipant> page = etopParticipantService.getAvg(etopParticipant, offset, limit);
		return page;

	}
	
	@ResponseBody
	@RequestMapping(value = "/updateFeedback.do")
	public ResultType updateFeedback(EtopParticipant etopParticipant) {

		ResultType result = null;

		try {

			etopParticipantService.updateFeedback(etopParticipant);

			result = ResultType.getSuccess("更新信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新信息失败! ");

			result = ResultType.getFail("更新信息训失败! ");

			e.printStackTrace();
		}

		return result;

	}
}
