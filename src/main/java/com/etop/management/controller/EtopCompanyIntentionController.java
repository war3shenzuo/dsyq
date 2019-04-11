package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopCompanyBusinessService;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.EtopFloorService;
import com.etop.management.service.ParkService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/23
 */
@Controller
@RequestMapping("/etopCompanyIntention")
public class EtopCompanyIntentionController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopCompanyIntentionController.class);

	@Autowired 
	private EtopCompanyIntentionService etopCompanyIntentionService;

	@Autowired
	private EtopCompanyService etopCompanyService;

	@Autowired
	private EtopFloorService etopFloorService;

	@Autowired
	private ParkService parkService;

	@Autowired
	private EtopCompanyBusinessService etopCompanyBusinessService;

	@RequestMapping("/etopCompanyIntentionList.do")
	public Object etopCompanyIntentionList(Model model){
		model.addAttribute("readonly",isReadOnly("/etopCompanyIntention/etopCompanyIntentionList.do"));
		model.addAttribute("genre",0);//公司类型  0：意向  1：正式
		if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
			model.addAttribute("userType", 4);
			model.addAttribute("parks", parkService.getParkName(getParkIds()));
		}
		return "etopCompanyIntention/etopCompanyIntentionList";
	}

	@RequestMapping("/addPage.do")
	public Object addPage(){
		return "etopCompanyIntention/addEtopCompanyIntentionInfo";
	}

	/**
	 * 获取意向公司列表信息
	 * @param etopCompanyIntention
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getEtopCompanyIntentionList.do")
	public Object getEtopCompanyIntentiontList(EtopCompanyIntention etopCompanyIntention,
											   @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
											   @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		if(etopCompanyIntention.getParkId() == null){
			etopCompanyIntention.setParkId(UserInfoUtil.getUserInfo().getParkId());
		}
		EtopPage<EtopCompanyIntention> page = etopCompanyIntentionService.search(etopCompanyIntention, offset, limit);
		return page;
	}


	/**
	 * 新增意向公司信息
	 * @param etopCompanyIntention
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addEtopCompanyIntentionInfo.do")
	public Object add(EtopCompanyIntention etopCompanyIntention){

		ResultType result = null;

		try {
			EtopCompanyIntention intention = etopCompanyIntentionService.selectCompanyIntention(etopCompanyIntention.getCompanyName(),null,UserInfoUtil.getUserInfo().getParkId());
			if(intention != null){
				result = new ResultType(9999,"公司名字已存在,请重新输入!");
				return result;
			}

			etopCompanyIntentionService.add(etopCompanyIntention);
			etopFloorService.updateRoomStatus(etopCompanyIntention.getRoomId(),"2");

			result = ResultType.getSuccess("添加公司信息成功",etopCompanyIntention.getId());

		} catch (Exception e) {

			result = ResultType.getFail("添加公司信息失败");

			LOGGER.error("添加公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除意向公司信息
	 * @param etopCompanyIntention
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteEtopCompanyIntentionInfo.do")
	public Object delete(EtopCompanyIntention etopCompanyIntention){

		ResultType result = null;

		try {

			Integer size = etopCompanyIntention.getIds().size();
			etopCompanyIntentionService.deleteById(etopCompanyIntention);

			result = ResultType.getSuccess("删除公司信息成功",size);

		} catch (Exception e) {

			result = ResultType.getFail("删除公司信息失败");

			LOGGER.error("删除公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "getCompInfoById.do")
	public Object getCompInfoById(EtopCompanyIntention etopCompanyIntention, Model model,String companyId, String readonly){

		try{
			model.addAttribute("companyId",companyId);
			model.addAttribute("companyType",0);//区分0：意向公司 1：正式公司
			model.addAttribute("compInfo",etopCompanyIntentionService.getCompInfoById(etopCompanyIntention.getIds()));
			model.addAttribute("readonly",readonly);
		} catch (Exception e) {

			LOGGER.error("查询公司详细信息失败");

			e.printStackTrace();

		}
		return "etopCompanyIntention/updateEtopCompanyIntentionInfo";
	}

	/**
	 * 修改意向公司信息
	 * @param etopCompanyIntention
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateEtopCompanyIntentionInfo.do")
	public Object update(EtopCompanyIntention etopCompanyIntention){

		ResultType result = null;

		try {

			EtopCompanyIntention intention = etopCompanyIntentionService.selectCompanyIntention(etopCompanyIntention.getCompanyName(),null,UserInfoUtil.getUserInfo().getParkId());
//			if(intention != null){
			if(intention != null && !intention.getId().equals(etopCompanyIntention.getId())){
				result = new ResultType(9999,"公司名字已存在,请重新输入!");
				return result;
			}
			etopCompanyIntention.setReviewStatus(0);//审核状态 0未审核,1审核中,2园长不通过,3财务不通过
			etopCompanyIntentionService.updateById(etopCompanyIntention);

			result = ResultType.getSuccess("修改公司信息成功");

		} catch (Exception e) {

			result = ResultType.getFail("修改公司信息失败");

			LOGGER.error("修改公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 转正意向公司
	 * @param etopCompanyIntention
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "positiveEtopCompanyIntentionInfo.do")
	public Object positive(EtopCompanyIntention etopCompanyIntention){

		ResultType result = null;

		try {

			EtopCompanyIntention info = etopCompanyIntentionService.getCompInfoById(etopCompanyIntention.getIds());
			//添加正式公司信息
			etopCompanyService.addInfo(info);

			//删除意向公司信息
			etopCompanyIntentionService.deleteById(etopCompanyIntention);

			result = ResultType.getSuccess("转正意向公司成功");

		} catch (Exception e) {

			result = ResultType.getFail("转正意向公司失败");

			LOGGER.error("转正意向公司失败");

			e.printStackTrace();
		}

		return result;

	}
	
	
	@ResponseBody
	@RequestMapping("/proveCompanyName.do")
	public ResultType proveCompanyName(String companyName,String companyId) {
		ResultType result= null;
//		if("1".equals(etopCompanyIntentionService.proveCompanyName(companyName)) || "1".equals(etopCompanyIntentionService.proveIntentionCompanyName(companyName))){
//			result =ResultType.getSuccess("公司名存在！");
//		}
//		else{
//			result = ResultType.getFail("公司名不存在！");
//		}
//		return result;

			String  id = etopCompanyIntentionService.proveCompanyName(companyName,UserInfoUtil.getUserInfo().getParkId());
			String  id2 = etopCompanyIntentionService.proveIntentionCompanyName(companyName,UserInfoUtil.getUserInfo().getParkId());
			if((id != null && !id.equals(companyId)) || (id2 != null && !id2.equals(companyId))){
				result = ResultType.getSuccess("公司名存在!");
			}else{
				result = ResultType.getFail("公司名不存在!");
			}

		return result;

	}
}
