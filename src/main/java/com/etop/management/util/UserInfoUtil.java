package com.etop.management.util;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Func;
import com.etop.management.bean.Park;
import com.etop.management.bean.Role;



public class UserInfoUtil {
	
	/** 需在web.xml增加监听
	*<listener>  
	* <listener-class>  
	*      org.springframework.web.context.request.RequestContextListener  
	*  </listener-class>  
	*</listener>
	*/
	
	private static HttpServletRequest request ;

	private static HttpSession session ;
	
	private static HttpSession getSession () {

		request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		session = request.getSession();
		
		return session;
		
	}
	
	public static  EtopUser getUserInfo(){
		
		return (EtopUser) getSession().getAttribute("userInfo");
	}
	
	@SuppressWarnings("unchecked")
	public static  List<Role> getUserRoleInfo(){
		
		return (List<Role>) getSession().getAttribute("roleInfo");
	}
	
	public static Func getUserFuncInfo(){
		
		
		return (Func) getSession().getAttribute("funcInfo");
	}
	
	public static Park getUserParkInfo(){
		
		return (Park) getSession().getAttribute("parkInfo");
	}
	

	
}
