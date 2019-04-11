package com.etop.management.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.constant.SysStatus;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.ContractService;
import com.etop.management.service.RemindService;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;

public class ContractBillJob {
	
	@Resource
	ContractService contractService;
	
	@Resource
	ContractEnergyService contractEnergyService;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	RemindService remindService;
	
	
	
	
	public void billForAllContract()
	{
		//int count=0;
		
		long start=System.currentTimeMillis();
		
		//System.out.println("bill contract job start...");
		
		LoggerUtil.info("bill contract job start...");
		
		List<Park> parkList=this.parkDao.getParkNameList();	
		
		int success=0;
		
		int failed=0;
		
		for(Park park:parkList)
		{
			ResultType result=this.contractService.generateBillByPark(park.getId(),Util.formatDate(new Date()));
			
			@SuppressWarnings("unchecked")
			Map<String,Integer> map=(Map<String,Integer>)result.getData();
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("success")>0 )
			{
				String content=String.format("园区(%s)合同已出帐(%d)，请审核。",park.getParkName(),map.get("success"));
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "合同出帐", content);
				
				success+=map.get("success");
				
				LoggerUtil.info(content);
			}
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("failed")>0)
			{
				String content=String.format("园区(%s)合同自动出帐部分失败，数量(%d)，请管理员查看。",park.getParkName(),map.get("failed"));
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				failed+=map.get("failed");
				
				LoggerUtil.info(content);
			}
			
			if(result.getStatus()==SysStatus.EXCEPTION.getStatus())
			{
				String content=String.format("园区(%s)合同自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				LoggerUtil.info(content);
			}
			
		}
		
		
		LoggerUtil.info(String.format("bill contract job finished. spend time %d, success:%d,failed:%d",
				System.currentTimeMillis()-start,success,failed));
//		System.out.println(String.format("bill contract job finished. spend time %d, success:%d,failed:%d",
//				System.currentTimeMillis()-start,success,failed));
		
	}
	
	//能源出帐
	
	public void billForEnergy()
	{
		
		long start=System.currentTimeMillis();
		
		LoggerUtil.info("bill energy job start...");
		
		
		int count=0;
		
		List<Park> parkList=this.parkDao.getParkNameList();
		
		for(Park park : parkList)
		{
			if("0".equals(park.getActivated()))
			{
				continue;
			}
			
			count=this.contractEnergyService.generateBillByPark(park.getId(), Util.formatDate(new Date()));
			
			if(count>=0)
			{
				String content=String.format("园区(%s)能源已出帐(%d)，请审核。",park.getParkName(),count);
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "能源出帐", content);
				
				LoggerUtil.info(content);
			}
			if(count==-3)
			{
				String content=String.format("园区(%s)能源自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "能源出帐", content);
				
				LoggerUtil.info(content);
			}
			
		}
		
		
		LoggerUtil.info(String.format("bill energy job finished. spend time %d,count:%d",
				System.currentTimeMillis()-start
				,count)			
				);
		
	}
}
