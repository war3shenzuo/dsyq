package com.etop.management.dao;

import java.util.List;

import com.etop.management.bean.EtopReply;

/**
 * Created by lvxiwei.
 *
 * @author lvxiwei
 * @DATE 2016/11/21
 */
public interface EtopReplyDao {

   public int addReply(EtopReply etopReply);
   
   public List<EtopReply> getReply(String noticeId);
   
   public List<EtopReply> getReplyByReplyer(EtopReply etopReply);

}

