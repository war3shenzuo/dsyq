package com.etop.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopOnlineTraining;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.dao.EtopOfflineTrainingDao;
import com.etop.management.dao.EtopOnlineTrainingDao;
import com.etop.management.dao.EtopParticipantDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.EtopOnlineTrainingService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/26
 */
@Service
public class EtopOnlineTrainingServiceImpl implements EtopOnlineTrainingService {

    @Autowired
    private EtopOnlineTrainingDao etopOnlineTrainingDao;

    @Autowired
    private EtopParticipantDao etopParticipantDao;
    
    @Autowired
    private EtopOfflineTrainingDao etopOfflineTrainingDao;
    
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	
	@Resource 
	private ParkGroupDao parkGroupDao;
	
	@Resource 
	private ParkDao parkDao;
	
	@Resource 
	private UserDao userDao;
	
    @Override
    public EtopPage<EtopOnlineTraining> search(EtopOnlineTraining etopOnlineTraining, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t.updated_at DESC");
        
        
        Page<EtopOnlineTraining> list = etopOnlineTrainingDao.select(etopOnlineTraining);
        for(EtopOnlineTraining etopOnlineTrainings : list){
        	int questionNum =etopOnlineTrainingDao.CountQuestionBank(etopOnlineTrainings.getId());
        	etopOnlineTrainings.setQuestionNum(questionNum);
        	int testNum =etopOnlineTrainingDao.CountTestNum(etopOnlineTrainings.getId());
        	etopOnlineTrainings.setTestNum(testNum);
        	EtopOnlineTraining Training = etopOnlineTrainingDao.getAvgScore(etopOnlineTrainings.getId());
        	etopOnlineTrainings.setAvgScore(Training.getAvgScore());
        	String[] params=etopOnlineTrainings.getTarget().split(",");
        	if("all".equals(params[0])){
        		String Usertype = userDao.getUserInfo(etopOnlineTrainings.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			Integer targetNumber =parkDao.getAllParkNum();
        			etopOnlineTrainings.setTargetNumber(targetNumber);
        		}else{
	        		Integer targetNumber = etopOfflineTrainingDao.countPark(etopOnlineTrainings.getCreatedBy());
	        		etopOnlineTrainings.setTargetNumber(targetNumber);
        		}
        	}else{
        		String Usertype = userDao.getUserInfo(etopOnlineTrainings.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			int targetNumber = 0;
	        		String[] targetArray = etopOnlineTrainings.getTarget().split(",");
	        		for(String parkGroupName :targetArray){
	        			int parkNum =parkDao.getParkNumByParkGroupName(parkGroupName);
	        			 targetNumber += parkNum;
	        		}
	        		etopOnlineTrainings.setTargetNumber(targetNumber);
        		}else{
        			String[] targetArray = etopOnlineTrainings.getTarget().split(",");
	        		etopOnlineTrainings.setTargetNumber(targetArray.length);
        		}
        	}
        }
        return new EtopPage<EtopOnlineTraining>(list);
    }

    @Override
    public void add(EtopOnlineTraining etopOnlineTraining) {
        etopOnlineTraining.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOnlineTraining.setCourseId(etopSequenceSevice.getFormatId2("ol"));
        }else if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOnlineTraining.setCourseId(etopSequenceSevice.getFormatId2("ol"));
        }else{
            etopOnlineTraining.setCourseId(etopSequenceSevice.getFormatId2("ol"));
        }
        if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOnlineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkGroupId());
        }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
            	etopOnlineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkId());
        }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOnlineTraining.setChoosePark("all");
        }
        etopOnlineTrainingDao.insert(etopOnlineTraining);
    }

    @Override
    public EtopOnlineTraining getOnlineTrainingInfoById(String id) {
        EtopOnlineTraining etopOnlineTraining = etopOnlineTrainingDao.getOnlineTrainingInfoById(id);
        etopOnlineTraining.setTestNum(etopParticipantDao.selectTestNum(id));
        return etopOnlineTraining;
    }

    @Override
    public void updateOnlineTraining(EtopOnlineTraining etopOnlineTraining) {
        etopOnlineTraining.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopOnlineTrainingDao.updateOnlineTraining(etopOnlineTraining);
    }

    @Override
    public void deleteById(EtopOnlineTraining etopOnlineTraining) {
        etopOnlineTrainingDao.deleteByIds(etopOnlineTraining.getIds());
    }

    @Override
    public List<EtopOnlineTraining> selectOnlineTraining(EtopOnlineTraining etopOnlineTraining) {
        return etopOnlineTrainingDao.selectOnlineTraining(etopOnlineTraining);
    }

    @Override
    public List<EtopOnlineTraining> selectOnlineInfo(String choosePark, String courseType) {
        return etopOnlineTrainingDao.selectOnlineInfo(choosePark, courseType);
    }
}
