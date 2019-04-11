package com.etop.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.service.EtopNoticeService;
import com.etop.management.service.LoginService;
import com.etop.management.service.UserService;
import com.etop.management.service.WebIndexService;
import com.etop.management.util.LogUtil;
import com.etop.management.util.ProUtil;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.StringUtil;

/**
 * 
 * 登陆
 * 
 * @author shixianjie 下午3:16:32
 */

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	LoginService loginService;

	@Resource
	UserService userService;

	@Resource
	WebIndexService webIndexService;

	@Autowired
	private EtopNoticeService etopNoticeService;

	@RequestMapping(value = "/login.do")
	public String loginPage(String fromurl, Model model) {
		model.addAttribute("fromurl", fromurl);
		return "login";
	}

	@RequestMapping(value = "/managerlogin.do")
	public String managerlogin(String fromurl, Model model) {
		model.addAttribute("fromurl", fromurl);
		return "login1";
	}

	@RequestMapping(value = "/webIndex.do")
	public String index(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		ProUtil pro = new ProUtil(ProUtil.PROPERTIESPATH, "path.properties");

		session.setAttribute("headerValue", webIndexService.getWebIndex("header"));

		String footer = webIndexService.getWebIndex("footer");

		footer = footer.replaceAll("%path%", pro.getPropertiesValue("path"));

		session.setAttribute("footerValue", footer);

		String value = webIndexService.getWebIndex("index");

		value = value.replaceAll("%path%", pro.getPropertiesValue("path"));

		model.addAttribute("value", value);

		return "index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/managerIndex.do")
	public String ManagerIndex(Model model, HttpServletRequest request, String url) {

		HttpSession session = request.getSession();

		if (StringUtil.isEmpty(url)) {

			EtopUser etopUser = ((EtopUser) session.getAttribute("userInfo"));

			model.addAttribute("userInfo", etopUser);

			if (EtopUser.TYPE_PARK.equals(etopUser.getUserType()) || EtopUser.TYPE_GROUP.equals(etopUser.getUserType())
					|| EtopUser.TYPE_SYSTEM.equals(etopUser.getUserType())) {

				model.addAttribute("roleInfo", ((List<Role>) session.getAttribute("roleInfo")).get(0).getRoleName());

			} else if ("1".equals(etopUser.getUserType())) {

				model.addAttribute("roleInfo", "企业");
			} else {

				model.addAttribute("roleInfo", "员工");

			}

			model.addAttribute("func", session.getAttribute("funcInfo"));

			model.addAttribute("url", url);

			model.addAttribute("receiverNum", etopNoticeService.getReceiverNum(UserInfoUtil.getUserInfo().getId()));

			return "managerIndex";

		}

		return "redirect:" + url;
	}

	@ResponseBody
	@RequestMapping(value = "/loginCheck.do")
	public ResultType loginMain(EtopUser user, HttpServletRequest request) {

		ResultType result = null;

		try {
			if (user != null) {

				result = loginService.loginCheck(user, request);

			}
		} catch (Exception e) {

			result = ResultType.getFail("登陆异常");

			logger.error("登录异常", e);

			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "/logout.do")
	public String Logout(HttpServletRequest request) {

		request.getSession().removeAttribute("userInfo");

		request.getSession().removeAttribute("roleInfo");

		request.getSession().removeAttribute("parkInfo");

		request.getSession().removeAttribute("funcInfo");

		return "redirect:webIndex.do";

	}

	@ResponseBody
	@RequestMapping(value = "/forgetPassword.do")
	public ResultType forgetPassword(EtopUser user, String acode) {

		ResultType result = null;

		try {
			
			EtopUser u = userService.getUserInfoByUserName(user.getUserName());
			
			Map<String,String> map = new HashMap<String, String>();
			map.put("mobile", u.getMobile());
			map.put("vcode", acode);
			map.put("vtype", "back");
			
			if (loginService.acodeIsTure(map)) {

				result = userService.changePassword(user, "forget");

			} else {

				return ResultType.getFail("验证码错误或者过期");
			}

		} catch (Exception e) {

			logger.error("忘记密码更改失败");

			result = ResultType.getFail("忘记密码更改失败！");

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/forgetPass.do")
	public String forgetPass() {
		return "forgetPass";
	}

	@RequestMapping(value = "/accountManage.do")
	public String accountManaget(Model model) {
		
		String userId = UserInfoUtil.getUserInfo().getId();

		model.addAttribute("id", userId);
		
		EtopUser user =null;
		
		try {
			user = userService.getUserInfo(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("mobile",user.getMobile());
		
		return "accountManage";
	}
	
//	@RequestMapping(value = "/sendSmsCode.do")
//	@ResponseBody
//	public ResultType sendSmsCode(String type,String mobile,String username) {
//		EtopUser u =null ;
//
//		if("back".equals(type)){
//			try {
//				u = userService.getUserInfoByUserName(username);
//
//				if(u==null){
//					return ResultType.getFail("发送失败,找不到用户");
//				}
//
//				mobile = u.getMobile();
//
//				if(mobile==null){
//					return ResultType.getFail("发送失败,帐号未绑定联系电话");
//				}
//
//
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//
//		}
//
//		return loginService.sendSmsCode(mobile,type,username);
//
//	}


}
