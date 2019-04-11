package com.etop.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.dao.EtopThresholdDao;
import com.etop.management.entity.EtopThreshold;
import com.etop.management.service.EtopThresholdService;
import com.github.pagehelper.Page;
/**
 * 
 * <br>
 * <b>功能：</b>EtopBillPayService<br>
 */
@Service("etopThresholdService")
public class EtopThresholdServiceImpl implements EtopThresholdService {

	@Inject
	private EtopThresholdDao etopThresholdDao;
	
	@Override
	public int add(EtopThreshold threshold) {
		return etopThresholdDao.add(threshold);
	}

	@Override
	public int adds(List<EtopThreshold> list) {
		return etopThresholdDao.adds(list);
	}

	@Override
	public int update(EtopThreshold threshold) {
		return etopThresholdDao.update(threshold);
	}

	@Override
	public int delete(String thresholdId) {
		return etopThresholdDao.delete(thresholdId);
	}

	@Override
	public int deletes(String[] thresholdIds) {
		return etopThresholdDao.deletes(thresholdIds);
	}

	@Override
	public EtopThreshold findById(String thresholdId) {
		return etopThresholdDao.findById(thresholdId);
	}

	@Override
	public Page<EtopThreshold> list(Map<String, Object> condition) {
		return etopThresholdDao.list(condition);
	}

	@Override
	public double getValue(String parkId, String key) {

		Map<String,Object> condition=new HashMap<String,Object>();
		
		condition.put("parkId", parkId);
		
		condition.put("thresholdKey", key);
		
		List<EtopThreshold> list=this.etopThresholdDao.list(condition);
		
		if(list!=null && list.size()>0)
		{
			return list.get(0).getValue();
		}
		else
		{				
			return 0;
		}
	}

}
