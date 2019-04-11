package com.etop.management.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopOfflineTraining;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.dao.EtopOfflineTrainingDao;
import com.etop.management.dao.EtopTrainScheduleDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopGoods;
import com.etop.management.service.EtopOfflineTrainingService;
import com.etop.management.service.ParkGroupService;
import com.etop.management.service.ParkService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;

/**
 * 线下培训
 *
 * <br>
 * <b>功能：</b>EtopOfflineTrainingController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopOfflineTraining")
public class EtopOfflineTrainingController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopOfflineTrainingController.class);

	@Autowired 
	private EtopOfflineTrainingService etopOfflineTrainingService;

	@Autowired
	private ParkService parkService;

    @Autowired
    private EtopTrainScheduleDao etopTrainScheduleDao;
    
	@Resource
	private EtopOfflineTrainingDao etopOfflineTrainingDao;
	
	@Autowired
	private ParkGroupService parkGroupService;
	
	@Autowired
	private ParkGroupDao parkGroupDao;
	
	@RequestMapping("/etopOfflineTrainingList.do")
	public Object etopOfflineTrainingList(Model model){
		model.addAttribute("readonly", isReadOnly("/etopOfflineTraining/etopOfflineTrainingList.do"));
		model.addAttribute("user",UserInfoUtil.getUserInfo().getId());
		return "etopOfflineTraining/etopOfflineTrainingList";
	}

	@RequestMapping("/addPage.do")
	public Object addPage(Model model, String courseId) {
		//区分是直接新建或者线下计划发布
		if(courseId != null){
			model.addAttribute("id", courseId);
		}else {
			model.addAttribute("id", UUID.randomUUID().toString());
		}
//		model.addAttribute("list",etopOfflineTrainingService.selectSeries("=2"));
		model.addAttribute("userType", UserInfoUtil.getUserInfo().getUserType());
		 if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkService.getParkInfoList(UserInfoUtil.getUserInfo().getParkId()));
		 }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkService.getParkNameList2(UserInfoUtil.getUserInfo().getParkId()));
		 }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
			 model.addAttribute("parks", parkGroupService.getParkGroupListForControl2());
		 }
		return "etopOfflineTraining/addEtopOfflineTrainingInfo";
	}

	/**
	 * 线下培训列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopOfflineTrainingList.do")
	public Object getEtopOfflineTrainingList(EtopOfflineTraining etopOfflineTraining,
											@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
//		etopOfflineTraining.setOfflineType("=2");
/*		if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
		etopOfflineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkId());
		}*/
		if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
			etopOfflineTraining.setTarget(UserInfoUtil.getUserParkInfo().getParkName());
		}else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
			etopOfflineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkId());
			etopOfflineTraining.setTarget(parkGroupDao.getParkGroupInfo(UserInfoUtil.getUserInfo().getParkId()).getParkGroupName());
		}
		EtopPage<EtopOfflineTraining> page = etopOfflineTrainingService.demand(etopOfflineTraining, offset, limit);
		return page;
	}

	/**
	 * 新增线下培训
	 *
	 * @param etopOfflineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopOfflineTraining.do")
	public ResultType addEtopOfflineTraining(EtopOfflineTraining etopOfflineTraining) {

		ResultType result = null;

		try {

			etopOfflineTrainingService.add(etopOfflineTraining);

			result = ResultType.getSuccess("新增线下培训成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增线下培训失败! ");

			LOGGER.error("新增线下培训失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getOfflineTrainingInfo.do")
	public String getOfflineTrainingInfo(EtopOfflineTraining etopOfflineTraining, Model model, String readonly) {
		try {
				model.addAttribute("userType", UserInfoUtil.getUserInfo().getUserType());
				model.addAttribute("user",UserInfoUtil.getUserInfo().getId());
				model.addAttribute("readonly", readonly);
				model.addAttribute("courseId",etopOfflineTraining.getId());
				model.addAttribute("info", etopOfflineTrainingService.getOfflineProgramsInfoById(etopOfflineTraining.getId()));
			 if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
				model.addAttribute("parks", parkService.getParkInfoList(UserInfoUtil.getUserInfo().getParkId()));
			 }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
				model.addAttribute("parks", parkService.getParkNameList2(UserInfoUtil.getUserInfo().getParkId()));
			 }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
			    model.addAttribute("parks", parkGroupService.getParkGroupListForControl2());
			 }
		} catch (Exception e) {

			LOGGER.error("查询园区活动信息失败");

			e.printStackTrace();

		}
		return "etopOfflineTraining/updateEtopOfflineTraining";
	}

	/**
	 * 更新线下培训
	 *
	 * @param etopOfflineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOfflineTraining.do")
	public ResultType updateOfflineTraining(EtopOfflineTraining etopOfflineTraining) {

		ResultType result = null;

		try {

			etopOfflineTrainingService.updateOfflinePrograms(etopOfflineTraining);

			result = ResultType.getSuccess("更新线下培训成功! ");

		} catch (Exception e) {

			LOGGER.error("更新线下培训失败! ");

			result = ResultType.getFail("更新线下培训失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除线下培训
	 *
	 * @param etopOfflineTraining
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteOfflineTrainingInfo.do")
	public Object delete(EtopOfflineTraining etopOfflineTraining){

		ResultType result = null;

		try {

			Integer size = etopOfflineTraining.getIds().size();
			etopOfflineTrainingService.deleteById(etopOfflineTraining);

			result = ResultType.getSuccess("删除线下培训成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除线下培训失败");

			LOGGER.error("删除线下培训失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 确认(取消)发布课程
	 *
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unpublish.do")
	public ResultType unpublish(String id,Integer type) {

		ResultType result = null;

		try {

			etopOfflineTrainingService.unpublish(id,type);

			result = ResultType.getSuccess("取消发布成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("取消发布失败! ");

			LOGGER.error("取消发布失败! ");

			e.printStackTrace();
		}

		return result;

	}


	/**
	 * 前台页面获取培训列表
	 *
	 * @param etopOfflineTraining
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEtopOfflineTraining.do")
	public List<EtopOfflineTraining> getEtopOfflineTraining(EtopOfflineTraining etopOfflineTraining) {
		try {
			etopOfflineTraining.setOfflineType("1");
			return etopOfflineTrainingService.selectEtopOfflinePrograms(etopOfflineTraining);
		} catch (Exception e) {

			LOGGER.error("查询线下培训信息失败");

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
	@RequestMapping(value = "/getTrainingInfo.do")
	public List<EtopOfflineTraining> getTrainingInfo(String target, String courseType) {
		try {
			Integer offlineType = 1;//0 线下活动  1线下培训
			return etopOfflineTrainingService.selectProgramsInfo(target, courseType, offlineType);
		} catch (Exception e) {

			LOGGER.error("查询线下培训信息失败");

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 前台页面获取线下培训详情页信息
	 *
	 * @param etopOfflineTraining
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOfflineTrainingInformation.do")
	public EtopOfflineTraining getOfflineTrainingInformation(EtopOfflineTraining etopOfflineTraining) {
		try {
			return etopOfflineTrainingService.getOfflineProgramsInfoById(etopOfflineTraining.getId());
		} catch (Exception e) {

			LOGGER.error("查询线下培训信息失败");

			e.printStackTrace();

		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request,String id, String parkId) {
		return UploadUtil.upLoad(request, id, "");
	}

	
	@ResponseBody
	@RequestMapping("/deleteTrain.do")
	public ResultType delete(String id)  throws Exception{
		ResultType result= null;
		try{
			etopOfflineTrainingService.delete(id);
			result =ResultType.getSuccess("删除成功！");
		}catch(Exception e){
			LOGGER.error("删除失败",e);
		result = ResultType.getFail("删除失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	@ResponseBody
	@RequestMapping("/proveContent.do")
	public ResultType proveContent(String id){
		ResultType result= null;

		if(!"".equals(etopOfflineTrainingService.proveContent(id))){
			result =ResultType.getSuccess("验证成功！");
		}
		else{
			result = ResultType.getFail("验证失败！");
		}
		return result;
		
	}
	@ResponseBody
	@RequestMapping("/provescheduleNum.do")
	public ResultType provescheduleNum(String id){
		ResultType result= null;

		if(etopTrainScheduleDao.selectNum(id) > 0){
			result =ResultType.getSuccess("验证成功！");
		}
		else{
			result = ResultType.getFail("验证失败！");
		}
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("/TrainStatus.do")
	public void TrainStatus(){

		System.out.println("Training status job start...");
//		Calendar.getInstance().getTimeInMillis()


		 List<EtopOfflineTraining> TrainingList=etopOfflineTrainingDao.getAll();
		
		for(EtopOfflineTraining etopOfflineTraining : TrainingList)
		{

			if( 0 == etopOfflineTraining.getCourseStatus()  && (new Date().getTime()) > etopOfflineTraining.getCloseTime().getTime())
			{
				etopOfflineTrainingDao.updateStatus(1, etopOfflineTraining.getId());
			}
		}
	
		
	}
}
