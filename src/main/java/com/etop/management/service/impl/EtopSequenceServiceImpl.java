package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.dao.EtopSequenceDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopSequence;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.util.UserInfoUtil;

/**
 * 获取格式化编号
 * @author lmc
 * @date 2016年8月23日
 */
@Service("etopSequenceService")
public class  EtopSequenceServiceImpl  implements EtopSequenceService {

	@Inject
	private EtopSequenceDao etopSequenceDao;
	@Inject
    private ParkDao parkDao;
    
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	@Resource 
	private ParkGroupDao parkGroupDao;
	@Override
	public int getAndIncrease(String parkId) {
		int count = 0;
		EtopSequence sequence = etopSequenceDao.findByParkId(parkId);
		if(sequence == null) {
			sequence = new EtopSequence();
			sequence.setParkId(parkId);
			sequence.setCount(0);
			sequence.setUpdateTime(new Date());
			etopSequenceDao.add(sequence);
		}
		else {
			String now = dateFormat.format(new Date());
			if(now.equals(dateFormat.format(sequence.getUpdateTime()))) {
				count = sequence.getCount() + 1;
				etopSequenceDao.increaseCount(parkId);
			}
			else {
				etopSequenceDao.clearCount(parkId);
			}
		}
		return count;
	}

	@Override
	public String getFormatId(String parkId, String type) {int count = 0;
		String dateStr = dateFormat.format(new Date());
		try {
			count = etopSequenceDao.nextSeq(parkId);
		}catch (Exception e){
			e.printStackTrace();
		}

//		EtopSequence sequence = etopSequenceDao.findByParkId(parkId);
//		if(sequence == null) {
//			sequence = new EtopSequence();
//			sequence.setParkId(parkId);
//			sequence.setCount(0);
//			sequence.setUpdateTime(new Date());
//			etopSequenceDao.add(sequence);
//		}
//		else {
//			if(dateStr.equals(dateFormat.format(sequence.getUpdateTime()))) {
//				count = sequence.getCount() + 1;
//				etopSequenceDao.increaseCount(parkId);
//			}
//			else {
//				etopSequenceDao.clearCount(parkId);
//			}
//		}
		return String.format("%s%s%s%06d", parkId, type, dateStr, count);
	}
	
	@Override
	public String getFormatId2(String type) {
		int count = 0;
	String system = "";
	String dateStr = dateFormat.format(new Date());
	if("5".equals(UserInfoUtil.getUserInfo().getUserType()) ){
		 system = "sys";
	}else if("4".equals(UserInfoUtil.getUserInfo().getUserType()) ){
//		 system = parkDao.getGroupCode(UserInfoUtil.getUserInfo().getId());
		 system = parkGroupDao.getParkGroupInfo(UserInfoUtil.getUserInfo().getParkId()).getParkGroupCode();
	}else if("3".equals(UserInfoUtil.getUserInfo().getUserType()) ){
		 system = UserInfoUtil.getUserParkInfo().getParkCode();
	}
	
	try {
		count = etopSequenceDao.nextSeq(system);
	}catch (Exception e){
		e.printStackTrace();
	}
	return String.format("%s%s%s%04d",system, type, dateStr, count);
	}
}
