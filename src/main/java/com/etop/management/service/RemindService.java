package com.etop.management.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;

/** 
 * @author lvxiwei 

 * @time   2016年9月19日 下午5:29:14 
 */
public interface RemindService {

	
	public List<Remind> searchbytarget(String target,HttpServletRequest request, String beforeTime) throws ParseException;
	
	public void addRemind(Remind remind);
	
	public Remind searchbyId(String id);
	
	public void upadteStatus (String id);	
	
	void upadteTop(Remind remind, String id);

	void finalTop(Remind remind, String id);
	
	public List<EtopUser> getJurisdiction(String parkId,String juriId);
	
	/**
	 * 发送消息提醒 
	 * @param parkId
	 * @param remindType
	 * @param title
	 * @param content
	 */
	void remind(String parkId,String remindType,String title,String content);

}
