package com.etop.management.controller;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopOnlineTraining;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.service.EtopOnlineTrainingService;
import com.etop.management.service.ParkGroupService;
import com.etop.management.service.ParkService;
import com.etop.management.util.UserInfoUtil;

/**
 * 
 * <br>
 * <b>功能：</b>EtopOnlineTrainingController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopOnlineTraining")
public class EtopOnlineTrainingController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopOnlineTrainingController.class);

	@Autowired
	private EtopOnlineTrainingService etopOnlineTrainingService;

	@Autowired
	private ParkService parkService;
	
	@Autowired
	private ParkGroupService parkGroupService;
	
	@Autowired
	private ParkGroupDao parkGroupDao;

	@RequestMapping("/etopOnlineTrainingList.do")
	public Object etopOnlineTrainingList(Model model){
		model.addAttribute("readonly", isReadOnly("/etopOnlineTraining/etopOnlineTrainingList.do"));
		model.addAttribute("user",UserInfoUtil.getUserInfo().getId());
		return "etopOnlineTraining/etopOnlineTrainingList";
	}

	@RequestMapping("/addPage.do")
	public String addPage(Model model) {
		model.addAttribute("id", UUID.randomUUID().toString());
		model.addAttribute("userType", UserInfoUtil.getUserInfo().getUserType());
//		model.addAttribute("parks", parkService.getParkNameList());
		if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkService.getParkInfoList(UserInfoUtil.getUserInfo().getParkId()));
		 }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkService.getParkNameList2(UserInfoUtil.getUserInfo().getParkId()));
		 }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkGroupService.getParkGroupListForControl2());
		 }
		return "etopOnlineTraining/addEtopOnlineTraining";
	}

	/**
	 * 线上培训列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopOnlineTrainingList.do")
	public Object getEtopOnlineTrainingList(EtopOnlineTraining etopOnlineTraining,
									@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
									@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
			etopOnlineTraining.setTarget(UserInfoUtil.getUserParkInfo().getParkName());
		}else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
			etopOnlineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkId());
			etopOnlineTraining.setTarget(parkGroupDao.getParkGroupInfo(UserInfoUtil.getUserInfo().getParkId()).getParkGroupName());
		}
		EtopPage<EtopOnlineTraining> page = etopOnlineTrainingService.search(etopOnlineTraining, offset, limit);
		return page;
	}

	/**
	 * 新增线上培训
	 *
	 * @param etopOnlineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopOnlineTraining.do")
	public ResultType addEtopOnlineTraining(EtopOnlineTraining etopOnlineTraining) {

		ResultType result = null;

		try {

			etopOnlineTrainingService.add(etopOnlineTraining);

			result = ResultType.getSuccess("新增线上培训成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增线上培训失败! ");

			LOGGER.error("新增线上培训失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getOnlineTrainingInfo.do")
	public String getOnlineTrainingInfo(EtopOnlineTraining etopOnlineTraining, Model model, String readonly) {
		try {
			model.addAttribute("user",UserInfoUtil.getUserInfo().getId());
			model.addAttribute("userType", UserInfoUtil.getUserInfo().getUserType());
			model.addAttribute("readonly", readonly);
			 if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
				 model.addAttribute("parks", parkService.getParkInfoList(UserInfoUtil.getUserInfo().getParkId()));
			 }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
				 model.addAttribute("parks", parkService.getParkNameList2(UserInfoUtil.getUserInfo().getParkId()));
			 }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
				 model.addAttribute("parks", parkGroupService.getParkGroupListForControl2());
			 }
			model.addAttribute("id",etopOnlineTraining.getId());
			model.addAttribute("notice", etopOnlineTrainingService.getOnlineTrainingInfoById(etopOnlineTraining.getId()));
		} catch (Exception e) {

			LOGGER.error("查询线上培训信息失败");

			e.printStackTrace();

		}
		return "etopOnlineTraining/updateEtopOnlineTraining";
	}

	/**
	 * 更新线上培训
	 *
	 * @param etopOnlineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOnlineTraining.do")
	public ResultType updateOnlineTraining(EtopOnlineTraining etopOnlineTraining) {

		ResultType result = null;

		try {

			etopOnlineTrainingService.updateOnlineTraining(etopOnlineTraining);

			result = ResultType.getSuccess("更新线上培训成功! ");

		} catch (Exception e) {

			LOGGER.error("更新线上培训失败! ");

			result = ResultType.getFail("更新线上培训失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除线上培训
	 *
	 * @param etopOnlineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteOnlineTrainingInfo.do")
	public Object delete(EtopOnlineTraining etopOnlineTraining){

		ResultType result = null;

		try {

			Integer size = etopOnlineTraining.getIds().size();
			etopOnlineTrainingService.deleteById(etopOnlineTraining);

			result = ResultType.getSuccess("删除线上培训成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除线上培训失败");

			LOGGER.error("删除线上培训失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 前台页面获取培训列表
	 *
	 * @param etopOnlineTraining
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOnlineTraining.do")
	public List<EtopOnlineTraining> getOnlineTraining(EtopOnlineTraining etopOnlineTraining) {
		try {
			 return etopOnlineTrainingService.selectOnlineTraining(etopOnlineTraining);
		} catch (Exception e) {

			LOGGER.error("查询线上培训信息失败");

			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 前台页面获取培训列表（园区、培训类型）带参数
	 *
	 * @param
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOnlineInfo.do")
	public List<EtopOnlineTraining> getOnlineInfo(String target, String courseType) {
		try {
			return etopOnlineTrainingService.selectOnlineInfo(target, courseType);
		} catch (Exception e) {

			LOGGER.error("查询线上培训信息失败");

			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 前台页面获取培训详情页信息
	 *
	 * @param etopOnlineTraining
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOnlineInformation.do")
	public EtopOnlineTraining getOnlineInformation(EtopOnlineTraining etopOnlineTraining) {
		try {
			return etopOnlineTrainingService.getOnlineTrainingInfoById(etopOnlineTraining.getId());
		} catch (Exception e) {

			LOGGER.error("查询线上培训信息失败");

			e.printStackTrace();

		}
		return null;
	}

}
