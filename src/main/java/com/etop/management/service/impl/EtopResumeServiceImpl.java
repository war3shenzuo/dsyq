package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopResumeDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.entity.EtopResume;
import com.etop.management.service.EtopResumeService;
import com.etop.management.service.EtopServiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopResumeService<br>
 */
@Service("etopResumeService")
public class  EtopResumeServiceImpl  implements EtopResumeService {
	
	@Resource
	private EtopResumeDao etopResumeDao;
	@Autowired 
	private EtopServiceService etopServiceService; 
	@Autowired 
	private EtopServiceDao etopServiceDao; 
	@Override
	public EtopPage<EtopResume> getEtopResumeList(EtopResume etopResume) {
		
		EtopPage<EtopResume> page = new EtopPage<EtopResume>();
		
		int BTablePageNum = (etopResume.getOffset()/etopResume.getLimit());
		
		//设置分页
		PageHelper.startPage(BTablePageNum+1, etopResume.getLimit());
		
		List<EtopResume> list =  new ArrayList<EtopResume>();
		
		list = etopResumeDao.queryByList(etopResume.getCriteria());
		
		PageInfo<EtopResume> table =new PageInfo<EtopResume>(list);
		
		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
		
	}

	@Override
	public EtopResume getEtopResumeInfo(String etopResumeId) {
		return etopResumeDao.queryById(etopResumeId);
	}

	@Override
	public void addEtopResume(EtopResume param) {
		param.setId(UUID.randomUUID().toString());
		etopResumeDao.add(param);
		int	status2 = etopServiceDao.queryById(param.getServiceId()).getServiceStatus();
		if(status2 == 202)
		{
			 etopServiceService.updateStatus(param.getServiceId(),param.getStatus(), null, null);
		}
	}

	@Override
	public void updateEtopResume(EtopResume etopResume) {
		etopResumeDao.updateBySelective(etopResume);
	}

	@Override
	public void deleteEtopResumeInfo(EtopResume etopResume) {
		etopResumeDao.delete(etopResume);
		
	}
  
	
}
