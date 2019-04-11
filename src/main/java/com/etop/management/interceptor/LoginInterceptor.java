package com.etop.management.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.etop.management.bean.EtopUser;
import com.github.pagehelper.StringUtil;

/**
 * sprincMvc拦截器
 * 
 * @author shixianjie
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	public String[] allowUrls;// 不拦截url

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 获取当前链接
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

		String queryString = request.getQueryString();

		if (StringUtil.isNotEmpty(queryString))
			requestUrl += "?" + queryString;

		// 判断当前链接是否不需要拦截
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}
		// 从session获取用户
		EtopUser user = (EtopUser) request.getSession().getAttribute("userInfo");

		if (user != null) {
			return true; // 结束后 继续调用postHandle(), afterCompletion()
		} else {
			// session中没用户则跳转到登录页面
			// 传入当前链接，以跳转用
			if (StringUtil.isEmpty(requestUrl)) {
				response.sendRedirect(request.getContextPath() + "/login.do?");
			} else {
				response.sendRedirect(
						request.getContextPath() + "/login.do?fromurl=" + URLEncoder.encode(requestUrl, "utf-8"));
			}
			// request.getRequestDispatcher("/login/loginPage.do").forward(request,
			// response);
			return false;
		}
	}

}
