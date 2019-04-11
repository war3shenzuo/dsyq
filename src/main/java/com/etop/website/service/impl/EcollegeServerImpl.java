package com.etop.website.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.Ecollege;
import com.etop.website.dao.EcollegeDao;
import com.etop.website.service.EcollegeService;






@Named("EcollegeService")
@Service
public class EcollegeServerImpl implements EcollegeService {
	
	@Resource
	private EcollegeDao ecollegeDao;

	
	public List<Ecollege> searchList(Ecollege Ecollege) throws Exception {
		  List<Ecollege> list=ecollegeDao.searchList(Ecollege);    		    
          return list;
	}




	@Override
	public Ecollege searchInfo(String id) {
		
		return ecollegeDao.searchInfo(id);
	}




	@Override
	public List<Ecollege> selectTraining(Ecollege ecollege) throws Exception {
		 List<Ecollege> list=ecollegeDao.selectTraining(ecollege);    		    
         return list;
	}




	@Override
	public List<Ecollege> selectOnlineInfo(Ecollege ecollege) throws Exception {
		 List<Ecollege> list=ecollegeDao.selectOnlineInfo(ecollege);    		    
         return list;
	}




	@Override
	public List<Ecollege> selectOfflineInfo(Ecollege ecollege) throws Exception {
		 List<Ecollege> list=ecollegeDao.selectOfflineInfo(ecollege);    		    
         return list;
	}
	
	@Override
	public List<Ecollege> selectOffCourseInfo(Ecollege ecollege) throws Exception {
		List<Ecollege> list=ecollegeDao.selectOffCourseInfo(ecollege);    		    
		return list;
	}

	@Override
	public Map<String, Object> getCity(Map<String,String> m) {
		
		List<Map<String,Object>> res=ecollegeDao.getCity(m);
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
	public List<Map<String, Object>> getCitys(Map<String, String> m) {
		
		return ecollegeDao.getCitys(m);
	}




//	@Override
//	public List<Ecollege> getCitys(String offlineType) {
//		
//		return ecollegeDao.getCitys(offlineType);
//	}
	

}