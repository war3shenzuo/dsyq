package com.etop.management.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopReply;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.EtopVote;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.page.NoticePage;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopNoticeService;
import com.etop.management.service.EtopReplyService;
import com.etop.management.service.EtopVoteService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;

/**
 * 
 * <br>
 * <b>功能：</b>EtopNoticeController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopNotice")
public class EtopNoticeController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopNoticeController.class);

	@Autowired 
	private EtopNoticeService etopNoticeService;

	@Autowired
	private UserService userService;

	@Autowired
	private EtopVoteService etopVoteService;
	
	@Resource
	EtopReplyService etopReplyService;
	
	@RequestMapping("/etopNoticeList.do")
	public Object etopNoticeList(Model model){
		model.addAttribute("readonly", isReadOnly("/etopNotice/etopNoticeList.do"));
		return "etopNotice/etopNoticeList";
	}

	@RequestMapping("/addPage.do")
	public String addPage() {
		return "etopNotice/addEtopNotice";
	}
	
	@RequestMapping("/addVote.do")
	public String addVote() {
		return "etopNotice/addVoteNotice";
	}
	
	@RequestMapping("/selectReceiverPage.do")
	public Object selectReceiverPage(Model model){
		model.addAttribute("parkId", UserInfoUtil.getUserInfo().getParkId());
		return "etopNotice/selectReceiver";
	}

	/**
	 * 通知列表
	 *
	 * @param etopNotice
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEtopNoticeList.do")
	public Object getEtopNoticeList(EtopNotice etopNotice,
									@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
									@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		EtopPage<EtopNotice> page = etopNoticeService.search(etopNotice, offset, limit);
		return page;
	}

	/**
	 * 获取当前园区下接收者列表（企业、员工、园区管理员、园区组管理员）
	 *
	 * @param
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReceiverByParkId.do")
	public Object getReceiverByParkId(String receiverType,String companyName, String parkId,
									 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
									 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit) {
		EtopPage<EtopUser> etopPage = userService.getReceiverByParkId(receiverType, companyName, parkId, offset, limit);
		return etopPage;
	}

	/**
	 * 新增通知
	 *
	 * @param etopNotice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopNotice.do")
	public ResultType addEtopNotice(EtopNotice etopNotice) {

		ResultType result = null;

		try {

			etopNoticeService.add(etopNotice);

			result = ResultType.getSuccess("添加通知成功! ");

		} catch (Exception e) {

			result = ResultType.getFail("添加通知失败! ");

			LOGGER.error("添加通知失败! ");

			e.printStackTrace();
		}

		return result;

	}
	
	/**
	 * 新增通知
	 *
	 * @param etopNotice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopNoticeVote.do")
	public ResultType addEtopNoticeVote(EtopNotice etopNotice) {
		
		ResultType result = null;
		
		try {
			
			etopNoticeService.addVote(etopNotice);
			
			result = ResultType.getSuccess("添加通知成功! ");
			
		} catch (Exception e) {
			
			result = ResultType.getFail("添加通知失败! ");
			
			LOGGER.error("添加通知失败! ");
			
			e.printStackTrace();
		}
		
		return result;
		
	}

	@RequestMapping(value = "/getNoticeInfo.do")
	public String getNoticeInfo(EtopNotice etopNotice, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			List<EtopNotice> notice = etopNoticeService.getInfoListByNoticeId(etopNotice);
			model.addAttribute("notice", notice.get(0));
			List<EtopReply> reply = etopReplyService.getReply(etopNotice.getNoticeId());
			if(reply.size() == 0){
				model.addAttribute("reply", null);
			}else{
			model.addAttribute("reply", reply);
			}
			List<EtopVote> vote = etopVoteService.selectOption(etopNotice.getNoticeId(),null);
			if(vote.size() == 0){
				model.addAttribute("vote", null);
			}else{
			model.addAttribute("vote", vote);
			}
		} catch (Exception e) {

			LOGGER.error("查询通知详细信息失败");

			e.printStackTrace();

		}
		return "etopNotice/etopNoticeInfo";
	}

	@ResponseBody
	@RequestMapping(value = "/checkAllIds.do")
	public List<EtopNotice> checkAllIds(String parkId) {

		List<EtopNotice> etopNotice = userService.checkAllIds(parkId);

		return etopNotice;

	}

	/**
	 * 收件人列表
	 *
	 * @param noticePage
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMessageList.do")
	public String getMessageList(NoticePage noticePage, Model model) {
		try {
			model.addAttribute("receiverNum", etopNoticeService.getReceiverNum(UserInfoUtil.getUserInfo().getId()));
			List<EtopNotice> list = etopNoticeService.findByPage(noticePage, UserInfoUtil.getUserInfo().getId());
			model.addAttribute("list", list);
			noticePage.setRows(etopNoticeService.getReceiverNum(UserInfoUtil.getUserInfo().getId()));
			model.addAttribute("page", noticePage);
		} catch (Exception e) {

			LOGGER.error("查询通知详细信息失败");

			e.printStackTrace();

		}
		return "etopNotice/notificationList";
	}

	@RequestMapping(value = "/getMessageInfo.do")
	public String getMessageInfo(EtopNotice etopNotice, Model model) {
		try {
			EtopNotice notice = etopNoticeService.getNoticeInfoById(etopNotice);
			model.addAttribute("notice",notice);
			List<EtopVote> vote = etopVoteService.selectOption(notice.getNoticeId(),UserInfoUtil.getUserInfo().getId());
			model.addAttribute("vote", vote);
//			List<EtopReply> reply = etopReplyService.getReply(notice.getNoticeId());
			EtopReply etopReply = new EtopReply();
			etopReply.setNoticeId(notice.getNoticeId());
			etopReply.setReplyer(notice.getUserName());
			List<EtopReply> reply = etopReplyService.getReplyByReplyer(etopReply);
			if(reply.size() == 0){
				model.addAttribute("reply", null);
			}else{
			model.addAttribute("reply", reply);
			}
		} catch (Exception e) {

			LOGGER.error("查询通知详细信息失败");

			e.printStackTrace();

		}
		return "etopNotice/notificationDetail";
	}

	/**
	 * 更新通知
	 *
	 * @param etopNotice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNotice.do")
	public ResultType updateNotice(EtopNotice etopNotice) {

		ResultType result = null;

		try {

			etopNoticeService.updateNoticeById(etopNotice);

			result = ResultType.getSuccess("更新新闻信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新新闻信息失败! ");

			result = ResultType.getFail("更新新闻信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除通知
	 * @param etopNotice
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteEtopNoticeInfo.do")
	public Object delete(EtopNotice etopNotice){

		ResultType result = null;

		try {

			Integer size = etopNotice.getIds().size();
			etopNoticeService.deleteById(etopNotice);

			result = ResultType.getSuccess("删除拜访记录成功", size);

		} catch (Exception e) {

			result = ResultType.getFail("删除拜访记录失败");

			LOGGER.error("删除拜访记录失败");

			e.printStackTrace();
		}

		return result;

	}



}
