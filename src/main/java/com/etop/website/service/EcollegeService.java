package com.etop.website.service;

import java.util.List;
import java.util.Map;

import com.etop.website.bean.Ecollege;





public interface EcollegeService {



    
    public List<Ecollege> searchList(Ecollege ecollege) throws Exception;
   
	public Ecollege searchInfo(String id);;

	public List<Ecollege> selectTraining(Ecollege ecollege) throws Exception;
	
	public List<Ecollege> selectOnlineInfo(Ecollege ecollege) throws Exception;
	
	public List<Ecollege> selectOfflineInfo(Ecollege ecollege) throws Exception;
	
	public List<Ecollege> selectOffCourseInfo(Ecollege ecollege) throws Exception;
	
	public Map<String, Object> getCity(Map<String, String> m);
	
	public List<Map<String, Object>> getCitys(Map<String, String> m);
	
//	List<Ecollege> getCitys(String offlineType);

}
