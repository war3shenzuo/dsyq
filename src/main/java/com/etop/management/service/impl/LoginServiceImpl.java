package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Func;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopCompEmployeesDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.dao.UserDao;
import com.etop.management.dao.FuncDao;
import com.etop.management.service.LoginService;
import com.etop.management.util.EncoderHandler;
import com.etop.management.util.TreeDataHandle;
import com.etop.management.util.Util;

/**
 * 
 * 登陆服务
 * @author shixianjie
 * 上午10:22:53
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LoginServiceImpl implements LoginService {
	
	@Resource
	UserDao userDao;
	
	@Resource
	RoleDao roleDao;
	
	@Resource
	FuncDao funcDao;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	EtopCompEmployeesDao employessdao;

	@Override
	public ResultType loginCheck(EtopUser user ,HttpServletRequest request) {
		
			EtopUser etopUser = userDao.getUserInfoByUserName(user.getUserName());
			
 			if(etopUser == null){
				
				return new ResultType(101, "用户不存在");
			}
			
			if("0".equals(etopUser.getActivated())){
				
				return new ResultType(103, "用户已被停用");
			}
			
			// 明文密码+盐值,进行加密操作
			if(!EncoderHandler.getSHA1(user.getPassWord() + etopUser.getPassWordSalt()).equals(etopUser.getPassWord())){
				
				return new ResultType(102, "密码错误");
			
			}
			
			if(EtopUser.TYPE_GROUP.equals(etopUser.getUserType()) || EtopUser.TYPE_PARK.equals(etopUser.getUserType())){
				if(Util.isNullOrEmpty(etopUser.getRoleId())){
					
					return new ResultType(104, "帐号未分配任何岗位");
				}
			}
			
			//往session里添加用户信息
			setSession(etopUser,request);
			
			return  new ResultType(100, "验证通过");
	}

	@Override
	public void setSession(EtopUser etopUser,HttpServletRequest request) {
		
		//如果是个人用户 把他个人资料加进来
		if(EtopUser.PERSONAL.equals(etopUser.getUserType())){
			
			EtopCompanyEmployees employees = employessdao.getEtopCompEmployeesById(etopUser.getCompanyId());
			
			etopUser.setEmployees(employees);
			
		}
		
		request.getSession().setAttribute("userInfo", etopUser);
		
		if(EtopUser.TYPE_PARK.equals(etopUser.getUserType()) || EtopUser.TYPE_GROUP.equals(etopUser.getUserType()) || EtopUser.TYPE_SYSTEM.equals(etopUser.getUserType()) ){
			
			//往session里添加角色信息
			List<Role> roles = roleDao.getUserRoleList(etopUser.getId());
			
			request.getSession().setAttribute("roleInfo", roles);
			
			//往session里添加园区信息
			if(EtopUser.TYPE_GROUP.equals(etopUser.getUserType())){
				
				Park park = new Park();
				
				park.setParkGroupId(etopUser.getParkId());
				
				request.getSession().setAttribute("parkInfo",park);
			}else{
				
				request.getSession().setAttribute("parkInfo", parkDao.getParkInfo(etopUser.getParkId()));
			
			}
			
			//往session里添加功能信息
			List<String> roleIds = new ArrayList<String>();
			
			for(Role role :roles){
				roleIds.add(role.getId());
			}
			
			List<Func> Funclist = funcDao.getFuncListByRoleId(roleIds);
			
			request.getSession().setAttribute("funcInfo",TreeDataHandle.HandleIndexFunc(Funclist));

		}else if(EtopUser.COMPANY.equals(etopUser.getUserType())){
			
			List<Func> Funclist = funcDao.getFuncListByCompany();
			
			request.getSession().setAttribute("funcInfo",TreeDataHandle.HandleIndexFunc(Funclist));
			
			request.getSession().setAttribute("parkInfo", parkDao.getParkInfo(etopUser.getParkId()));
			
		}else if(EtopUser.PERSONAL.equals(etopUser.getUserType())){
			
			List<Func> Funclist = funcDao.getFuncListByPersonal();
			
			request.getSession().setAttribute("funcInfo",TreeDataHandle.HandleIndexFunc(Funclist));
			
		}else{
			
			request.getSession().setAttribute("funcInfo",TreeDataHandle.HandleIndexFunc(new ArrayList<Func>()));
		}
	
        request.getSession().setMaxInactiveInterval(60*30);
		
	}
	
//	@Override
//	public ResultType sendSmsCode(String mobile, String type,String username) {
//
//
//		String code = SmsUtil.createRandomVcode();
//
//		ResultType result = null;
//
//		synchronized (this) {
//			if("register".equals(type)){
//
//				EtopUser u = userDao.getUserInfoByMobile(mobile);
//
//				if(u!=null){
//
//					return ResultType.getFail("该手机号码已使用");
//				}
//
//				result= SmsUtil.sendRegisterSms(mobile, "{\"code\":\""+code+"\",\"product\":\"驿拓园区在线\"}");
//
//				if(10001==(result.getStatus())){
//
//					saveVcode(mobile,code,"register");
//
//				}
//			}else if("back".equals(type)){
//
//				try {
//					EtopUser u = userDao.getUserInfoByUserName(username);
//
//					if(u==null){
//						return ResultType.getFail("发送失败,找不到用户");
//					}
//
//					mobile = u.getMobile();
//
//					if(mobile==null){
//						return ResultType.getFail("发送失败,帐号未绑定联系发誓");
//					}
//
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//
//					return ResultType.getFail("用户查找失败");
//
//				}
//
//				result= SmsUtil.sendBackSms(mobile, "{\"code\":\""+code+"\",\"product\":\"驿拓园区在线\"}");
//
//				if(10001==result.getStatus()){
//
//					saveVcode(mobile,code,"back");
//
//				}
//			}
//		}
//
//		return result;
//	}

	@Override
	public void saveVcode(String mobile, String code,String type) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("mobile", mobile);
		map.put("vcode", code);
		map.put("createAt",  new Date());
		map.put("vtype", type);
		
		userDao.saveVcode(map);
		
	}

	@Override
	public boolean acodeIsTure(Map<String, String> map) {
		Date createAt = userDao.getAcode(map);
		
		if(createAt==null) {
			return  Boolean.FALSE;
		}
		
		//超时
		Date date = new Date();
		Long aolstime = date.getTime()-createAt.getTime();
		
		if(aolstime>30*60*1000){
			return  Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}



}
