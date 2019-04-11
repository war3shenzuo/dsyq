package com.etop.management.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.dao.RemindDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.RemindService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;

/** 
 * @author lvxiwei 

 * @time   2016年9月19日 下午5:30:36 
 */

@Service
public class RemindServiceImpl implements RemindService{

	@Resource
	RemindDao remindDao;
	
	@Resource
	UserDao userDao;
	
	@Resource
	RoleDao roleDao;
	


	//显示查询列表
	public List<Remind> searchbytarget(String target,HttpServletRequest request, String beforeTime) throws ParseException {
		
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		target=etopUser.getId();
         
        Date date = new Date();
 		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		
 		List<Remind> list=remindDao.searchbytarget(target);
 		 
 		 for(Remind r:list){
 			 String createdTime = r.getCreateTime();
 			 date = simple.parse(createdTime);
 				beforeTime = DateUtil.fromToday(date);				
 				r.setBeforeTime(beforeTime);
 		 }
        		 
		return list;
	}
	
	




	//新增查询
	public void addRemind(Remind remind) {
		remind.setId(UUID.randomUUID().toString());
		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
		remind.setStatus("0");
		remind.setTop("0");
		remindDao.addRemind(remind);
	}






	@Override
	public Remind searchbyId(String id) {
		
		return remindDao.searchbyId(id);	
		
	}


//已读
	@Override
	public void upadteStatus(String id) {
		remindDao.upadteStatus(id);
		
	}






//置顶
	@Override
	public void upadteTop(Remind remind, String id) {
		

		remind.setUpdateTime( DateUtil.formatDateTime(new Date()));
		
		remindDao.upadteTop(remind);
		
	}


	@Override
	public void finalTop(Remind remind, String id) {
		remind.setUpdateTime( DateUtil.formatDateTime(new Date()));
		remind.setTop("1");
		remindDao.finalTop(remind);
		
	}
	
	/**
	 * 根据园区ID和权限编号 获得对应角色下的所有用户
	 * @param parkId 园区ID
	 * @param juriId 权限编号
	 * @return 用户集合（如果没有返回null）
	 */
	@Override
	public List<EtopUser> getJurisdiction(String parkId,String juriId){
		
		Map<String,String> mapParam = new HashMap<String,String>();
		mapParam.put("parkId", parkId);
		mapParam.put("juriId", juriId);
		
		List<String> roles =  roleDao.getJurisdiction(mapParam);
		
		if(roles!=null && roles.size()>0){
			
			return userDao.getUserListByRoleIds(roles);
		}
		
		return null;
		
	}

	
	@Override
	public void remind(String parkId,String remindType,String title,String content)
	{
		//send notify
		List<EtopUser> users=this.getJurisdiction(parkId, remindType);
		
		if(users!=null)
		{
			for(EtopUser u:users){
							
				Remind r=new Remind();
				
				r.setTitle(title);
				
				r.setContent(content);
				
				r.setTarget(u.getId());
				
				r.setRemindType(remindType);
				
				this.addRemind(r);
			
			}
		}
	}

}
