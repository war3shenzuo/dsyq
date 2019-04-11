package com.etop.management.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;

/**
 * 
 * 登录服务
 * @author shixianjie
 * 下午2:41:35
 */
public interface LoginService {
	
	
	/**
	 * 登录检查
	 */
	public ResultType loginCheck(EtopUser user,HttpServletRequest request);
	
	/**
	 * 设置seesion
	 */
	public void setSession(EtopUser etopUser,HttpServletRequest request);
	
	/**
	 * 保存验证码
	 * @param mobile
	 * @param code
	 */
	public void saveVcode(String mobile, String code,String type);
//
//	/**
//	 * 发送验证码
//	 * @param mobile
//	 * @param type
//	 * @return
//	 */
//	public ResultType sendSmsCode(String mobile, String type,String username);

	public boolean acodeIsTure(Map<String, String> map);
	

}
