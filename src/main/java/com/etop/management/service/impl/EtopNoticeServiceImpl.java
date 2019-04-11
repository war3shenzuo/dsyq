package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Remind;
import com.etop.management.bean.page.NoticePage;
import com.etop.management.dao.EtopNoticeDao;
import com.etop.management.dao.RemindDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.EtopNoticeService;
import com.etop.management.service.EtopVoteService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/21
 */
@Service
public class EtopNoticeServiceImpl implements EtopNoticeService {

    @Autowired
    private EtopNoticeDao etopNoticeDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private EtopVoteService etopVoteService;
    
    @Autowired
    private RemindDao remindDao;

    @Override
    public EtopPage<EtopNotice> search(EtopNotice etopNotice, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");

        etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
        Page<EtopNotice> list = etopNoticeDao.selectTotal(etopNotice);
        for(EtopNotice notice : list){
        	
        	String userNames = "";
        	etopNotice.setNoticeId(notice.getNoticeId());
        	Page<EtopNotice> list2 = etopNoticeDao.select(etopNotice);
        	 for(EtopNotice notice2 : list2){
        		 
        		String userName =notice2.getReceiver();
        		userNames += userName + ",";
        	 }
        	 String name = userNames.substring(0,userNames.length() - 1);
        	 if(userNames.length()>30){
        	   name = userNames.substring(0,30) +"……";
        	 }
        	 notice.setUserName(name); 
        }
        
        return new EtopPage<EtopNotice>(list);
    }

    @Override
    public void add(EtopNotice etopNotice) {
        List<EtopNotice> list = new ArrayList<>();
        List<Remind> remindList = new ArrayList<>();
        EtopNotice _etopNotice = null;
        Remind remind = null;
        String noticeId= UUID.randomUUID().toString();
        if(etopNotice.getAddressee().contains(",")){

            List<String> ids = Arrays.asList(etopNotice.getAddressee().split(","));
            List<String> receivers = Arrays.asList(etopNotice.getReceiver().split(","));

            if(ids != null && ids.size() >0 ){
                for(int i=0; i<ids.size(); i++){
                    _etopNotice = new EtopNotice();
                    _etopNotice.setNoticeId(noticeId);
                    _etopNotice.setAddressee(ids.get(i));
                    _etopNotice.setReceiver(receivers.get(i));
                    _etopNotice.setId(UUID.randomUUID().toString());
                    _etopNotice.setNoticeType("园区通知");
                    _etopNotice.setTitle(etopNotice.getTitle());
                    _etopNotice.setContent(etopNotice.getContent());
                    _etopNotice.setState(etopNotice.getState());
                    _etopNotice.setCreatedBy(etopNotice.getCreatedBy());
                    _etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
                    _etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
                    
                    remind =new Remind();
                    remind.setId(UUID.randomUUID().toString());
                    remind.setRemindType(Remind.NOTICE);
                    remind.setTitle("通知");
                    remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的通知，请查阅！");
                    remind.setTarget(ids.get(i));
                    remind.setStatus("0");
            		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
            		remind.setTop("0");
                    
                    list.add(_etopNotice);
                    remindList.add(remind);
                }
                etopNoticeDao.insert(list);
                remindDao.insert(remindList);
            }
        }else if(("allIds").equals(etopNotice.getAllIds())){
            List<EtopNotice> etopNotices = userDao.selectUser(UserInfoUtil.getUserInfo().getParkId());//员工全选
            if(etopNotices != null && etopNotices.size() >0 ){
                for(int i=0; i<etopNotices.size(); i++){
                    _etopNotice = new EtopNotice();
                    _etopNotice.setAddressee(etopNotices.get(i).getId());
                    _etopNotice.setReceiver(etopNotices.get(i).getUserName());
                    _etopNotice.setId(UUID.randomUUID().toString());
                    _etopNotice.setNoticeId(noticeId);
                    _etopNotice.setNoticeType("园区通知");
                    _etopNotice.setTitle(etopNotice.getTitle());
                    _etopNotice.setContent(etopNotice.getContent());
                    _etopNotice.setState(etopNotice.getState());
                    _etopNotice.setCreatedBy(etopNotice.getCreatedBy());
                    _etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
                    _etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
                    
                    remind =new Remind();
                    remind.setId(UUID.randomUUID().toString());
                    remind.setRemindType(Remind.NOTICE);
                    remind.setTitle("通知");
                    remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的通知，请查阅！");
                    remind.setTarget(etopNotices.get(i).getId());
                    remind.setStatus("0");
            		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
            		remind.setTop("0");
                    list.add(_etopNotice);
                    remindList.add(remind);
                }
                etopNoticeDao.insert(list);
                remindDao.insert(remindList);
            }
        }else {
            etopNotice.setId(UUID.randomUUID().toString());
            etopNotice.setNoticeId(UUID.randomUUID().toString());
            etopNotice.setNoticeType("园区通知");
            etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
            etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
            list.add(etopNotice);
            etopNoticeDao.insert(list);
            
            remind =new Remind();
            remind.setId(UUID.randomUUID().toString());
            remind.setRemindType(Remind.NOTICE);
            remind.setTitle("通知");
            remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的通知，请查阅！");
            remind.setTarget(etopNotice.getAddressee());
            remind.setStatus("0");
    		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
    		remind.setTop("0");
    		remindList.add(remind);
    		remindDao.insert(remindList);
        }

    }
    
    @Override
    public void addVote(EtopNotice etopNotice) {
    	 List<EtopNotice> list = new ArrayList<>();
         List<Remind> remindList = new ArrayList<>();
         EtopNotice _etopNotice = null;
         Remind remind = null;
    	if(etopNotice.getAddressee().contains(",")){
    		
    		List<String> ids = Arrays.asList(etopNotice.getAddressee().split(","));
    		List<String> receivers = Arrays.asList(etopNotice.getReceiver().split(","));
    		
    		if(ids != null && ids.size() >0 ){
    			for(int i=0; i<ids.size(); i++){
    				_etopNotice = new EtopNotice();
    				_etopNotice.setNoticeId(etopNotice.getNoticeId());
    				_etopNotice.setAddressee(ids.get(i));
    				_etopNotice.setReceiver(receivers.get(i));
    				_etopNotice.setId(UUID.randomUUID().toString());
    				_etopNotice.setNoticeType("投票通知");
    				_etopNotice.setTitle(etopNotice.getTitle());
    				_etopNotice.setContent(etopNotice.getContent());
    				_etopNotice.setState(etopNotice.getState());
    				_etopNotice.setCreatedBy(etopNotice.getCreatedBy());
    				_etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
    				_etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    				
    				remind =new Remind();
                    remind.setId(UUID.randomUUID().toString());
                    remind.setRemindType(Remind.NOTICE);
                    remind.setTitle("通知");
                    remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的投票通知，请查阅！");
                    remind.setTarget(ids.get(i));
                    remind.setStatus("0");
            		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
            		remind.setTop("0");
                    
                    list.add(_etopNotice);
                    remindList.add(remind);
    			}
    			etopNoticeDao.insert(list);
    			etopVoteService.add(etopNotice.getEtopVote());
    			remindDao.insert(remindList);
    		}
    	}else if(("allIds").equals(etopNotice.getAllIds())){
    		List<EtopNotice> etopNotices = userDao.selectUser(UserInfoUtil.getUserInfo().getParkId());//员工全选
    		if(etopNotices != null && etopNotices.size() >0 ){
    			for(int i=0; i<etopNotices.size(); i++){
    				_etopNotice = new EtopNotice();
    				_etopNotice.setAddressee(etopNotices.get(i).getId());
    				_etopNotice.setReceiver(etopNotices.get(i).getUserName());
    				_etopNotice.setId(UUID.randomUUID().toString());
    				_etopNotice.setNoticeId(etopNotice.getNoticeId());
    				_etopNotice.setNoticeType("投票通知");
    				_etopNotice.setTitle(etopNotice.getTitle());
    				_etopNotice.setContent(etopNotice.getContent());
    				_etopNotice.setState(etopNotice.getState());
    				_etopNotice.setCreatedBy(etopNotice.getCreatedBy());
    				_etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
    				_etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    				remind =new Remind();
                    remind.setId(UUID.randomUUID().toString());
                    remind.setRemindType(Remind.NOTICE);
                    remind.setTitle("通知");
                    remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的投票通知，请查阅！");
                    remind.setTarget(etopNotices.get(i).getId());
                    remind.setStatus("0");
            		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
            		remind.setTop("0");
                    list.add(_etopNotice);
                    remindList.add(remind);
                }
                etopNoticeDao.insert(list);
                remindDao.insert(remindList);
    			etopNoticeDao.insert(list);
    			etopVoteService.add(etopNotice.getEtopVote());
    			remindDao.insert(remindList);
    		}
    	}else {
    		etopNotice.setId(UUID.randomUUID().toString());
    		etopNotice.setNoticeId(UUID.randomUUID().toString());
    		etopNotice.setNoticeType("投票通知");
    		etopNotice.setParkId(UserInfoUtil.getUserInfo().getParkId());
    		etopNotice.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    		list.add(etopNotice);
    		etopNoticeDao.insert(list);
    		etopVoteService.add(etopNotice.getEtopVote());
    		 remind =new Remind();
             remind.setId(UUID.randomUUID().toString());
             remind.setRemindType(Remind.NOTICE);
             remind.setTitle("通知");
             remind.setContent("您有一份来自"+UserInfoUtil.getUserInfo().getUserName()+"的投票通知，请查阅！");
             remind.setTarget(etopNotice.getAddressee());
             remind.setStatus("0");
     		remind.setCreateTime(DateUtil.formatDateTime(new Date()));
     		remind.setTop("0");
     		remindList.add(remind);
     		remindDao.insert(remindList);
    	}
    	
    }

    @Override
    public EtopNotice getNoticeInfoById(EtopNotice etopNotice) {
        return etopNoticeDao.getNoticeInfoById(etopNotice.getId());
    }

    @Override
    public List<EtopNotice> getInfoListByNoticeId(EtopNotice etopNotice) {
    	List<EtopNotice> list = etopNoticeDao.getInfoListByNoticeId(etopNotice.getNoticeId());
    	for(EtopNotice notice : list){
        	
        	String userNames = "";
        	List<EtopNotice> list2 = etopNoticeDao.getInfoListByNoticeId(etopNotice.getNoticeId());
        	 for(EtopNotice notice2 : list2){
        		 
        		String userName =notice2.getReceiver();
        		userNames += userName + ",";
        	 }
        	 String name = userNames.substring(0,userNames.length() - 1);
        	 notice.setUserName(name); 
        }
    	return list;
//      return etopNoticeDao.getInfoListByNoticeId(etopNotice.getNoticeId());
    }
    @Override
    public List<EtopNotice> getMessageListById() {
    	return etopNoticeDao.getMessageListById(UserInfoUtil.getUserInfo().getId());
    }

    @Override
    public void updateNoticeById(EtopNotice etopNotice) {
        etopNotice.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopNoticeDao.updateNoticeById(etopNotice);
    }

    @Override
    public void deleteById(EtopNotice etopNotice) {
        List<String> ids = etopNotice.getIds();
        etopNoticeDao.deleteById(ids);
    }

    @Override
    public Integer getReceiverNum(String addressee) {
        NoticePage noticePage = new NoticePage();
        noticePage.setAddressee(addressee);
        return etopNoticeDao.getReceiverNum(noticePage);
    }

    @Override
    public List<EtopNotice> findByPage(NoticePage noticePage, String addressee) {
        /*Integer begin = noticePage.getBegin();
        Integer pageSize = noticePage.getPageSize();*/
        noticePage.setAddressee(addressee);
        return etopNoticeDao.findByPage(noticePage);
    }

}
