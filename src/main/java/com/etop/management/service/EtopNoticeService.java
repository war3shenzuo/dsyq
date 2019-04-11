package com.etop.management.service;


import java.util.List;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.page.NoticePage;

/**
 * 
 * <br>
 * <b>功能：</b>EtopNoticeService<br>
 */
public interface EtopNoticeService{

    EtopPage<EtopNotice> search(EtopNotice etopNotice, Integer offset, Integer limit);

    void add(EtopNotice etopNotice);
    
    void addVote(EtopNotice etopNotice);

    EtopNotice getNoticeInfoById(EtopNotice etopNotice);
    
    List<EtopNotice> getInfoListByNoticeId(EtopNotice etopNotice);
    
    List<EtopNotice> getMessageListById();

    void updateNoticeById(EtopNotice etopNotice);

    void deleteById(EtopNotice etopNotice);

    Integer getReceiverNum(String addressee);

    List<EtopNotice> findByPage(NoticePage noticePage, String addressee);

}
