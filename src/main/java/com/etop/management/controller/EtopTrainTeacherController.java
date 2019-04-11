package com.etop.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainTeacher;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopTrainTeacherService;
import com.etop.management.util.UploadUtil;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainTeacherController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopTrainTeacher")
public class EtopTrainTeacherController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopTrainTeacherController.class);

	@Autowired 
	private EtopTrainTeacherService etopTrainTeacherService;

	@RequestMapping("/addTrainTeacherPage.do")
	public String addTrainTeacherPage(Model model, String courseId) {
		model.addAttribute("courseId", courseId);
		return "etopOfflinePrograms/addEtopTrainTeacher";
	}
	
	@RequestMapping("/addTrainTeacherPage2.do")
	public String addTrainTeacherPage2(Model model, String courseId) {
		model.addAttribute("courseId", courseId);
		return "etopOfflineTraining/addEtopTrainTeacher";
	}

	/**
	 * 讲师列表
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopTrainTeacherList.do")
	public Object getEtopTrainTeacherList(EtopTrainTeacher etopTrainTeacher,
											@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopTrainTeacher> page = etopTrainTeacherService.search(etopTrainTeacher, offset, limit);
		return page;
	}

	/**
	 * 新增讲师信息
	 *
	 * @param etopTrainTeacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopTrainSchedule.do")
	public ResultType addEtopTrainTeacher(EtopTrainTeacher etopTrainTeacher) {

		ResultType result = null;

		try {

			etopTrainTeacherService.add(etopTrainTeacher);

			result = ResultType.getSuccess("新增讲师信息成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("新增讲师信息失败! ");

			LOGGER.error("新增讲师信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/getEtopTrainTeacherInfo.do")
	public String getEtopTrainTeacherInfo(EtopTrainTeacher etopTrainTeacher, Model model) {
		try {
			model.addAttribute("info", etopTrainTeacherService.getEtopTrainTeacherInfoById(etopTrainTeacher.getId()));
		} catch (Exception e) {

			LOGGER.error("查询讲师信息失败");

			e.printStackTrace();

		}
		return "etopOfflinePrograms/updateEtopTrainTeacher";
	}
	
	@RequestMapping(value = "/getEtopTrainTeacherInfo2.do")
	public String getEtopTrainTeacherInfo2(EtopTrainTeacher etopTrainTeacher, Model model) {
		try {
			model.addAttribute("info", etopTrainTeacherService.getEtopTrainTeacherInfoById(etopTrainTeacher.getId()));
		} catch (Exception e) {
			
			LOGGER.error("查询讲师信息失败");
			
			e.printStackTrace();
			
		}
		return "etopOfflineTraining/updateEtopTrainTeacher";
	}

	/**
	 * 更新讲师信息
	 *
	 * @param etopTrainTeacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEtopTrainTeacher.do")
	public ResultType updateEtopTrainTeacher(EtopTrainTeacher etopTrainTeacher) {

		ResultType result = null;

		try {

			etopTrainTeacherService.updateEtopTrainTeacher(etopTrainTeacher);

			result = ResultType.getSuccess("更新讲师信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新讲师信息失败! ");

			result = ResultType.getFail("更新讲师信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除讲师信息
	 *
	 * @param etopTrainTeacher
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEtopTrainScheduleInfo.do")
	public Object delete(EtopTrainTeacher etopTrainTeacher){

		ResultType result = null;

		try {

			Integer size = etopTrainTeacher.getIds().size();
			etopTrainTeacherService.deleteById(etopTrainTeacher);

			result = ResultType.getSuccess("删除讲师信息成功 !", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除讲师信息失败 !");

			LOGGER.error("删除讲师信息失败 !");

			e.printStackTrace();
		}

		return result;

	}

	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request,String courseId, String parkId) {
		return UploadUtil.upLoad(request, courseId, "");
	}

}
