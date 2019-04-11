package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopReply;


/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceService<br>
 */
public interface EtopReplyService{
	
	

	   public int addReply(EtopReply etopReply);

	   public List<EtopReply> getReply(String noticeId);
	   
	   public List<EtopReply> getReplyByReplyer(EtopReply etopReply);
	

}
