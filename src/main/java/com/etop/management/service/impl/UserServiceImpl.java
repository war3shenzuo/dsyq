package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopCompEmployeesDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.RoleService;
import com.etop.management.service.UserService;
import com.etop.management.util.EncoderHandler;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 用户服务
 * @author shixianjie
 * 下午2:11:25
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Resource
	UserDao userDao;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	RoleDao roleDao;

	@Resource
     ParkGroupDao parkGroupDao;
	
	@Resource
	EtopCompEmployeesDao etopCompEmployeesDao;

	@Resource
	private RoleService roleService;
	
	@Override
	public EtopPage<EtopUser> getUserList(EtopUser user) throws Exception {
		
 		user.setParkGroupId(UserInfoUtil.getUserParkInfo().getParkGroupId());
		
		EtopPage<EtopUser> page = new EtopPage<EtopUser>();
		
		int BTablePageNum = (user.getOffset()/user.getLimit());
		
		//设置分页
		PageHelper.startPage(BTablePageNum+1, user.getLimit());
		
		List<EtopUser> list =  new ArrayList<EtopUser>();
		
		list = userDao.getUserList(user);
		
		PageInfo<EtopUser> table =new PageInfo<EtopUser>(list);
		
		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public EtopPage<EtopUser> getReceiverByParkId(String receiverType, String companyName, String parkId, Integer offset, Integer limit) {

		PageHelper.offsetPage(offset, limit, "t1.update_at DESC");
		Page<EtopUser> list = null;
		if(("1").equals(receiverType)){
			list = userDao.selectComp(parkId, receiverType, companyName);
		}else if(("2").equals(receiverType)){
			list = userDao.selectStaff(parkId, receiverType, companyName);
		}else if(("3").equals(receiverType)){
			list = userDao.selectByAdminOrStaff(parkId, receiverType, companyName);
		}else if(("4").equals(receiverType)){
			list = userDao.selectByAdminOrStaff(parkId, receiverType, companyName);
		}
		return new EtopPage<EtopUser>(list);
	}

	@Override
	public EtopUser getUserInfo(String userId) throws Exception {
		
		EtopUser user  = userDao.getUserInfo( userId);
		
		return user;
	}
	
	@Override
	public  ResultType addUserInfo(EtopUser user,String roleId) throws Exception {
		
		
		if(userDao.getUserInfoByUserName(user.getUserName())!=null){
			
			return ResultType.getFail("用户已存在");
		}
		
		//新增角色
		String passWordSalt = EncoderHandler.getRandomString(32);
		user.setPassWord(EncoderHandler.getSHA1( user.getPassWord() + passWordSalt));
		user.setPassWordSalt(passWordSalt);
		user.setId(UUID.randomUUID().toString());
		user.setCreateAt(new Date());
		
		if(parkGroupDao.getParkGroupInfo(user.getParkId())!=null){
			user.setUserType(EtopUser.TYPE_GROUP);
		}else{
			user.setUserType(EtopUser.TYPE_PARK);
		}
		
		userDao.addUserInfo(user);
		
		//绑定角色
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",UUID.randomUUID().toString());
		map.put("userId", user.getId());
		map.put("roleId", roleId);
		
		roleDao.addUserForRole(map);
		
		return ResultType.getSuccess("添加用户成功");
		
	}

	@Override
	public void updateUserInfo(EtopUser user) throws Exception {
		
		user.setUpdateAt(new Date());
		
		if(user.getPassWord()!=null){
			
			String passWordSalt = EncoderHandler.getRandomString(32);
			
			String newPassWord = EncoderHandler.getSHA1( user.getPassWord() + passWordSalt);
			
			user.setPassWord(newPassWord);
			
			user.setPassWordSalt(passWordSalt);
			
		}
		
		userDao.updateUserInfo(user);

	}
	
	@Override
	public void updateUser(EtopUser user,EtopCompanyEmployees Employees ) {
		
		user.setUpdateAt(new Date());
		
		userDao.updateUserInfo(user);
		
		etopCompEmployeesDao.update(Employees);
		
	}

	@Override
	public void activeOrCloseUser(EtopUser user) throws Exception {
		
		user.setUpdateAt(new Date());
		userDao.activeOrCloseUser(user);

	}

	@Override
	public ResultType changePassword(EtopUser user,String type) throws Exception {
		
		ResultType result = null;
		
		synchronized(this){
			
			String passWordSalt = EncoderHandler.getRandomString(32);
			String newPassWord = null;
			
			if("reset".equals(type)){
				
				EtopUser etopUser =userDao.getUserInfo(user.getId());
				user.setUserName(etopUser.getUserName());
				//重置密码
				// 明文密码+盐值,进行加密操作
				newPassWord = EncoderHandler.getSHA1( "123456" + passWordSalt);
				
			}else if("forget".equals(type)){
				
				newPassWord = EncoderHandler.getSHA1( user.getPassWord() + passWordSalt);
				
			}else{
				
				EtopUser userPassWord =  userDao.getUserPassword(user.getId());
				
				// 判断密码是否正确
				if(!EncoderHandler.getSHA1(user.getPassWord() + userPassWord.getPassWordSalt()).equals(userPassWord.getPassWord())){
				
					return ResultType.getFail("密码错误！");
				}
				
				newPassWord = EncoderHandler.getSHA1( user.getNewPassWord() + passWordSalt);
				
			}
			
			user.setPassWord(newPassWord);
			user.setPassWordSalt(passWordSalt);
			user.setUpdateAt(new Date());
			userDao.changePassword(user);
		}
		
		result = ResultType.getSuccess("修改密码成功");
		
		return result;

	}

	@Override
	public List<EtopUser> getUserListByRoleIds(List<String> roleIds) throws Exception {
		
		return userDao.getUserListByRoleIds(roleIds);
	}

	@Override
	public EtopUser searchByAccount(String account) {

		return userDao.searchByAccount(account);
	}

	@Override
	public EtopUser getUserInfoByUserName(String userName) throws Exception {
		
		return userDao.getUserInfoByUserName(userName);
		
	}
	
	@Override
	public EtopUser getUserInfoByCompanyId(String companyId) throws Exception {
		
		return userDao.getUserInfoByCompanyId(companyId);
		
	}
	
	@Override
	public ResultType registerUser(EtopUser newUser,String acode) throws Exception{
		
		if(!"tongguo".equals(acode)){
			if(EtopUser.PERSONAL.equals(newUser.getUserType())){
				
				Map<String,String> map = new HashMap<String, String>();
				map.put("mobile", newUser.getMobile());
				map.put("vcode", acode);
				map.put("vtype", "register");
				
				Date createAt = userDao.getAcode(map);
				
				if(createAt==null) {
					return  ResultType.getFail("验证码不正确");
				}
				
				//超时
				Date date = new Date();
				Long aolstime = date.getTime()-createAt.getTime();
				
				if(aolstime>30*60*1000){
					return  ResultType.getFail("验证码超时");
				}
			}
		}
		
		//判断用户是否已被注册
/*		if(userDao.getUserInfoByMobile(newUser.getMobile())!=null){
			
			return ResultType.getFail("手机号码已被注册");
			
		}*/
		
		//判断用户是否已被注册
		if(userDao.getUserInfoByUserName(newUser.getUserName())!=null){
			
			return ResultType.getFail("该用户已被注册");
			
		}
		
		String passWordSalt = EncoderHandler.getRandomString(32);
		String newPassWord = EncoderHandler.getSHA1( newUser.getPassWord() + passWordSalt);
		newUser.setPassWord(newPassWord);
		newUser.setPassWordSalt(passWordSalt);
		
		if(EtopUser.COMPANY.equals(newUser.getUserType())){
			newUser.setParkId(UserInfoUtil.getUserInfo().getParkId());			
		}
		
		newUser.setId(UUID.randomUUID().toString());
		newUser.setCreateAt(new Date());
		newUser.setUpdateAt(new Date());
		newUser.setActivated("1");
		//如果是个人帐号，在个人用户信息补充表里建一条数据,方便用户更新个人资料
		if(EtopUser.PERSONAL.equals(newUser.getUserType())){
			newUser.setCompanyId(UUID.randomUUID().toString());
			
			EtopCompanyEmployees employess = new EtopCompanyEmployees();
			employess.setId(UUID.randomUUID().toString());
			employess.setCreatedAt(new Date());
			employess.setUpdatedAt(new Date());
			employess.setEmployeesStatus(1);
			employess.setEmployeesId(newUser.getCompanyId());
			etopCompEmployeesDao.inserEmployees(employess);
			
		}
		
		userDao.addUserInfo(newUser);
		
		return ResultType.getSuccess("注册成功",newUser);
		
	}

	@Override
	public List<EtopNotice> checkAllIds(String parkId) {
		return userDao.selectUser(parkId);
	}

	@Override
	public Boolean getUserPer() {
		return roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_HR);
	}

}
