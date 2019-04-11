package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.page.NoticePage;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/21
 */
public interface EtopNoticeDao {

    Page<EtopNotice> select(EtopNotice etopNotice);
    
    Page<EtopNotice> selectTotal(EtopNotice etopNotice);

    Integer insert(List<EtopNotice> list);

    EtopNotice getNoticeInfoById(@Param("id") String id);
    
    List<EtopNotice> getInfoListByNoticeId(@Param("noticeId") String noticeId);

    List<EtopNotice> getMessageListById(@Param("addressee") String addressee);

    Integer updateNoticeById(EtopNotice etopNotice);

    Integer deleteById(@Param("ids") List<String> ids);

    Integer getReceiverNum(NoticePage noticePage);

    List<EtopNotice> findByPage(NoticePage noticePage);

}

