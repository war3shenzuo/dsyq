package com.etop.management.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopOfflineTraining;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainApplication;
import com.etop.management.bean.EtopTrainPlan;
import com.etop.management.dao.EtopOfflineTrainingDao;
import com.etop.management.dao.EtopTrainApplicationDao;
import com.etop.management.dao.EtopTrainPlanDao;
import com.etop.management.dao.EtopTrainScheduleDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.EtopOfflineTrainingService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopOfflineTrainingService<br>
 */
@Service
public class  EtopOfflineTrainingServiceImpl implements EtopOfflineTrainingService {

    @Autowired
    private EtopOfflineTrainingDao etopOfflineTrainingDao;

    @Autowired
    private EtopTrainApplicationDao etopTrainApplicationDao;

    @Autowired
    private EtopTrainScheduleDao etopTrainScheduleDao;

    @Autowired
    private EtopTrainPlanDao etopTrainPlanDao;
    
    @Autowired
    private ParkDao parkDao;
    
    @Autowired
    private UserDao userDao;

	@Resource 
	private EtopSequenceService etopSequenceSevice;
	
    @Override
    public EtopPage<EtopOfflineTraining> demand(EtopOfflineTraining etopOfflineTraining, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t.createdAt DESC");
        Page<EtopOfflineTraining> list = etopOfflineTrainingDao.search(etopOfflineTraining);
        DecimalFormat df = new DecimalFormat("#.00");
        for(EtopOfflineTraining offlineTraining : list){
        	String[] params=offlineTraining.getTarget().split(",");
        	if("all".equals(params[0])){
        		String Usertype = userDao.getUserInfo(offlineTraining.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			Integer targetNumber =parkDao.getAllParkNum();
        			offlineTraining.setTargetNumber(targetNumber);
	    		}else{
	        		Integer targetNumber = etopOfflineTrainingDao.countPark(offlineTraining.getCreatedBy());
	        		offlineTraining.setTargetNumber(targetNumber);
	    		}
        	}else{
        		String Usertype = userDao.getUserInfo(offlineTraining.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			int targetNumber = 0;
	        		String[] targetArray = offlineTraining.getTarget().split(",");
	        		for(String parkGroupName :targetArray){
	        			int parkNum =parkDao.getParkNumByParkGroupName(parkGroupName);
	        			 targetNumber += parkNum;
	        		}
	        		offlineTraining.setTargetNumber(targetNumber);
        		}else{
        			String[] targetArray = offlineTraining.getTarget().split(",");
        			offlineTraining.setTargetNumber(targetArray.length);
        		}
 
        	}
            EtopTrainApplication etopTrainApplication = etopTrainApplicationDao.select(offlineTraining.getId());//学员信息数据
            Integer scheduleNum = etopTrainScheduleDao.selectNum(offlineTraining.getId());//开课数量(用于判断页面显示确认开课按钮)
            EtopOfflineTraining entity = etopOfflineTrainingDao.selectTime(offlineTraining.getId());
            if(entity.getStartDate() != null && entity.getEndDate() != null ){
                offlineTraining.setCourseTime(DateUtil.formatDate(entity.getStartDate()));
            }else {
                offlineTraining.setCourseTime("无");
            }
            offlineTraining.setJoinPeopleNum(offlineTraining.getJoinPeopleNum()!=null?offlineTraining.getJoinPeopleNum():0);
            offlineTraining.setScheduleNum(scheduleNum);
            offlineTraining.setRegistrationNum(etopTrainApplication.getTotalPeople());
            offlineTraining.setStudentsComprehensiveScore(df.format(etopTrainApplication.getAvgScore()));//学员综合评分
            offlineTraining.setOverallScore(df.format(0.8*(etopTrainApplication.getAvgScore()) + 0.2*(offlineTraining.getLv())));//总体评分：0.8*学员综合评分+0.2*负责人评分
//            offlineTraining.setCity(parkDao.getCity(offlineTraining.getCreatedBy()));
           
        }
        return new EtopPage<EtopOfflineTraining>(list);
    }
    
    @Override
    public EtopPage<EtopOfflineTraining> searchPrograms(EtopOfflineTraining etopOfflineTraining, Integer offset, Integer limit) {
    	PageHelper.offsetPage(offset, limit, "t.createdAt DESC");
    	Page<EtopOfflineTraining> list = etopOfflineTrainingDao.searchPrograms(etopOfflineTraining);
    	DecimalFormat df = new DecimalFormat("#.00");
    	for(EtopOfflineTraining offlineTraining : list){
    		String[] params=offlineTraining.getTarget().split(",");
        	if("all".equals(params[0])){
        		String Usertype = userDao.getUserInfo(offlineTraining.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			Integer targetNumber =parkDao.getAllParkNum();
	    			offlineTraining.setTargetNumber(targetNumber);
	    		}else{
	        		Integer targetNumber = etopOfflineTrainingDao.countPark(offlineTraining.getCreatedBy());
	        		offlineTraining.setTargetNumber(targetNumber);
	    		}
    		}else{
    			String Usertype = userDao.getUserInfo(offlineTraining.getCreatedBy()).getUserType();
        		if("5".equals(Usertype)){
        			int targetNumber = 0;
	        		String[] targetArray = offlineTraining.getTarget().split(",");
	        		for(String parkGroupName :targetArray){
	        			int parkNum =parkDao.getParkNumByParkGroupName(parkGroupName);
	        			 targetNumber += parkNum;
	        		}
	        		offlineTraining.setTargetNumber(targetNumber);
        		}else{
        			String[] targetArray = offlineTraining.getTarget().split(",");
        			offlineTraining.setTargetNumber(targetArray.length);
        		}
    		}
//            if(offlineTraining.getSumsContent()!= null && !("").equals(offlineTraining.getSumsContent())){
//                etopOfflineTrainingDao.updateStatus(2, offlineTraining.getId());
//                
//            }
    		EtopTrainApplication etopTrainApplication = etopTrainApplicationDao.select(offlineTraining.getId());//学员信息数据
    		Integer scheduleNum = etopTrainScheduleDao.selectNum(offlineTraining.getId());//开课数量(用于判断页面显示确认开课按钮)
    		EtopOfflineTraining entity = etopOfflineTrainingDao.selectTime(offlineTraining.getId());
    		if(entity.getStartDate() != null && entity.getEndDate() != null ){
    			offlineTraining.setCourseTime(DateUtil.formatDate(entity.getStartDate()));
    		}else {
    			offlineTraining.setCourseTime("无");
    		}
    		offlineTraining.setJoinPeopleNum(offlineTraining.getJoinPeopleNum()!=null?offlineTraining.getJoinPeopleNum():0);
    		offlineTraining.setScheduleNum(scheduleNum);
    		offlineTraining.setRegistrationNum(etopTrainApplication.getTotalPeople());
    		offlineTraining.setStudentsComprehensiveScore(df.format(etopTrainApplication.getAvgScore()));//学员综合评分
    		offlineTraining.setOverallScore(df.format(0.8*(etopTrainApplication.getAvgScore()) + 0.2*(offlineTraining.getLv())));//总体评分：0.8*学员综合评分+0.2*负责人评分
//    		offlineTraining.setCity(parkDao.getCity(offlineTraining.getCreatedBy()));
    		
    	}
    	return new EtopPage<EtopOfflineTraining>(list);
    }

    @Override
    public List<EtopOfflineTraining> selectEtopOfflinePrograms(EtopOfflineTraining etopOfflineTraining) {
        List<EtopOfflineTraining> list = etopOfflineTrainingDao.selectEtopOfflinePrograms(etopOfflineTraining);
        DecimalFormat df = new DecimalFormat("#.00");
        for(EtopOfflineTraining offlineTraining : list){
            EtopTrainApplication etopTrainApplication = etopTrainApplicationDao.select(offlineTraining.getId());
            offlineTraining.setRegistrationNum(etopTrainApplication.getTotalPeople());
            offlineTraining.setStudentsComprehensiveScore(df.format(etopTrainApplication.getAvgScore()));//学员综合评分
            offlineTraining.setOverallScore(df.format(0.8*(etopTrainApplication.getAvgScore()) + 0.2*(offlineTraining.getLv())));//总体评分：0.8*学员综合评分+0.2*负责人评分
        }
        return list;
    }

    @Override
    public void add(EtopOfflineTraining etopOfflineTraining) {
//        etopOfflineTraining.setOfflineType("3");
    	if("0".equals(etopOfflineTraining.getOfflineType())){
    		etopOfflineTraining.setCourseId(etopSequenceSevice.getFormatId2("le"));
    	}else if("1".equals(etopOfflineTraining.getOfflineType())){
    		etopOfflineTraining.setCourseId(etopSequenceSevice.getFormatId2("tr"));
    	}else if("2".equals(etopOfflineTraining.getOfflineType())){
    		etopOfflineTraining.setCourseId(etopSequenceSevice.getFormatId2("pa"));
    	}else if("3".equals(etopOfflineTraining.getOfflineType())){
    		etopOfflineTraining.setCourseId(etopSequenceSevice.getFormatId2("expa"));
    	}else if("4".equals(etopOfflineTraining.getOfflineType())){
    		etopOfflineTraining.setCourseId(etopSequenceSevice.getFormatId2("extr"));
    	}

        if(etopOfflineTraining.getSeries() == null){
        	etopOfflineTraining.setSeries("无");
        }
        etopOfflineTraining.setCourseStatus(4);
        etopOfflineTraining.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        if("3".equals(UserInfoUtil.getUserInfo().getUserType())){
        etopOfflineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkGroupId());
        }else if("4".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOfflineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkId());
        }else if("5".equals(UserInfoUtil.getUserInfo().getUserType())){
        	etopOfflineTraining.setChoosePark("all");
        }
        etopOfflineTrainingDao.insert(etopOfflineTraining);
//        EtopTrainPlan etopTrainPlan = new EtopTrainPlan();
//        etopTrainPlan.setStatus(0);
//        etopTrainPlan.setCourseId(etopOfflineTraining.getId());
//        etopTrainPlanDao.update(etopTrainPlan);
    }

    @Override
    public void unpublish(String id, Integer type) {
        Integer couresStatus = null;
        if(type == 1){
            couresStatus = 3;//取消
        }else if(type == 4){
        		couresStatus = 0;//报名
        }else if(type == 3){
            couresStatus = 2;//完结
        }else if(type == 2){
            couresStatus = 1;//进行
        }
        etopOfflineTrainingDao.updateStatus(couresStatus, id);
    }

    @Override
    public void deleteById(EtopOfflineTraining etopOfflineTraining) {
        etopOfflineTrainingDao.deleteByIds(etopOfflineTraining.getIds());
    }

    @Override
    public EtopOfflineTraining getOfflineProgramsInfoById(String id) {
    	EtopOfflineTraining etopOfflineTraining = etopOfflineTrainingDao.getOfflineProgramsInfoById(id);
    	etopOfflineTraining.setJoinPeopleNum(etopTrainApplicationDao.select(id).getTotalPeople());
    	return etopOfflineTraining;
    }

    @Override
    public void updateOfflinePrograms(EtopOfflineTraining etopOfflineTraining) {
        etopOfflineTraining.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopOfflineTraining.setUpdatedAt(new Date());
        etopOfflineTraining.setChoosePark(UserInfoUtil.getUserInfo().getParkGroupId());
        etopOfflineTrainingDao.updateOfflinePrograms(etopOfflineTraining);
    }

    @Override
    public List<EtopOfflineTraining> selectProgramsInfo(String target, String courseType, Integer offlineType) {
        return etopOfflineTrainingDao.selectProgramsInfo(target, courseType, offlineType);
    }

    @Override
    public List<EtopOfflineTraining> selectSeries() {
        return etopOfflineTrainingDao.selectSeries();
    }
    
//    @Override
//    public List<EtopOfflineTraining> selectCity() {
//    	return etopOfflineTrainingDao.selectCity();
//    }
    @Override
	public Map<String, Object> selectCity(Map<String,String> m) {
		
		List<Map<String,Object>> res=etopOfflineTrainingDao.selectCity(m);
		List<Map<String,Object>> citys=new  ArrayList<Map<String,Object>>();

		List<Map<String,Object>> hots=new  ArrayList<Map<String,Object>>();
		if(res!=null && res.size()>0 ){
			hots.add(getCityMap((String)res.get(0).get("city")));
			
			res.forEach(bean->{
				citys.add(getCityMap((String)bean.get("city")));
			});
		}
		
		Map<String, Object> result= new HashMap<String, Object>();
		result.put("hot", hots);
		result.put("province", citys);
		return result;
	}

    private Map<String,Object> getCityMap(String name){
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("pid", 1);
		map.put("pname", name);
		map.put("id", 1);
		map.put("name",name);
		return map;
	}
	
    
	@Override
	public int delete(String id) {
		return etopOfflineTrainingDao.delete(id);
	}

	@Override
	public String proveContent(String id) {
		// TODO Auto-generated method stub
		return etopOfflineTrainingDao.proveContent(id);
	}
}
