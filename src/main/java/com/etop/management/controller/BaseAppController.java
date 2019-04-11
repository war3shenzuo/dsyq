package com.etop.management.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.service.FuncService;
import com.etop.management.service.ParkService;
import com.etop.management.util.UserInfoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.StringUtil;

public class BaseAppController {

	
	@Resource
	FuncService funcService;
	
	@Resource
	ParkService mangerParkService;
	
	@Resource
	RequestMappingHandlerMapping rmhp;
//	@Resource
//	RemindService remindService;
	
	
	@ExceptionHandler
	public String handle(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
		e.printStackTrace();

		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
		for (RequestMappingInfo info : map.keySet()) {
			if (info.getMatchingCondition(request) != null) {
				if (map.get(info).getMethodAnnotation(ResponseBody.class) != null) {
					ResultType result;
					if (e instanceof IllegalArgumentException || e instanceof TypeMismatchException
							|| e instanceof ServletRequestBindingException) {
						result = ResultType.getParamIllegal();
					} else {
						result = ResultType.getException(e.getMessage());
					}
					ObjectMapper om = new ObjectMapper();
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(om.writeValueAsString(result));
					return null;
				}
				break;
			}
		}
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "/500";
	}

	protected String getUserId() {
		return UserInfoUtil.getUserInfo().getId();
	}

	protected String getParkId(String parkId) {
		if(StringUtil.isEmpty(parkId))
			return UserInfoUtil.getUserParkInfo().getId();
		else
			return parkId;
	}
	
	/**
	 * 返回当前角色的园区
	 * @return
	 */
	protected List<String> getParkIds() {
		
		List<String> list = new ArrayList<>();
		
		if(EtopUser.TYPE_GROUP.equals(UserInfoUtil.getUserInfo().getUserType())){
			
			list = mangerParkService.getAllParkId();
			
		}else{
			
			list.add(UserInfoUtil.getUserParkInfo().getId());
		}
		
		return list;
		
	}
	
	protected String getParkCode() {
		return UserInfoUtil.getUserParkInfo().getParkCode();
	}
	
	/**
	 * 判断角色在当前页面是否只读
	 * @param url
	 * @return
	 */
	protected boolean isReadOnly(String url) {
		
		return funcService.isReadOnly(url);
		
	}
	
	
}
