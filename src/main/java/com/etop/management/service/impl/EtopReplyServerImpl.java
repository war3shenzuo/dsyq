package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopReply;
import com.etop.management.dao.EtopReplyDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.service.EtopReplyService;
import com.etop.management.util.UserInfoUtil;



@Service
public class EtopReplyServerImpl implements EtopReplyService{

	@Resource
	EtopReplyDao etopReplyDao;
	
	@Resource
	EtopCompanyDao etopCompanyDao;


// 普通回复
	@Override
	public int addReply(EtopReply etopReply) {
		etopReply.setId(UUID.randomUUID().toString());
		String user ="";
		if("1".equals(UserInfoUtil.getUserInfo().getUserType())){
			user = etopCompanyDao.getCompanyName(UserInfoUtil.getUserInfo().getId());
		}else{
			if(UserInfoUtil.getUserInfo().getName() != null){
				user =UserInfoUtil.getUserInfo().getName();
			}else{
				user =UserInfoUtil.getUserInfo().getUserName();
			}
		}
		etopReply.setReplyer(UserInfoUtil.getUserInfo().getUserName());
		etopReply.setUser(user);
		etopReply.setReplyTime(new Date());
		etopReply.setReplyType("0");
		return etopReplyDao.addReply(etopReply);
	}


	@Override
	public List<EtopReply> getReply(String noticeId) {
		return etopReplyDao.getReply(noticeId);
//		List<EtopReply>  list= etopReplyDao.getReply(noticeId);
		
	}


	@Override
	public List<EtopReply> getReplyByReplyer(EtopReply etopReply) {
		return etopReplyDao.getReplyByReplyer(etopReply);
	}





}