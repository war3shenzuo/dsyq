package com.etop.website.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.dao.ParkDao;
import com.etop.website.bean.Inpark;
import com.etop.website.service.InparkService;
import com.etop.website.dao.InparkDao;





@Named("InparkService")
@Service
public class InparkServerImpl implements InparkService {
	
	@Resource
	private InparkDao InparkDao;
    @Resource
    private ParkDao parkDao;
	

	@Override
	public Inpark searchInfo(String parkGroupId) {
		
		return InparkDao.searchInfo(parkGroupId);
	}



	@Override
	public List<Inpark> getCity(String city) {
	return InparkDao.getCity(city);
	}



	@Override
	public Map<String, Object> getCity2(Map<String,String> m) {
		
		List<Map<String,Object>> res=parkDao.getParkCitys(m);
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
	public List<ParkGroupPresentation> getCompanyByparkGroupId(String parkGroupId) {

		return InparkDao.getCompanyByparkGroupId(parkGroupId);
	}



	@Override
	public List<ParkGroupPresentation> getServiceyByparkGroupId(String parkGroupId) {

		return InparkDao.getServiceByparkGroupId(parkGroupId);
	}



	










	
	
	

}