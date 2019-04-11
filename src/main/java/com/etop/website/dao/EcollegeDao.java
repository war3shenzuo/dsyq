package com.etop.website.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Ecollege;





@Repository
public interface EcollegeDao {

	 List<Ecollege> searchList(Ecollege ecollege);

	 Ecollege searchInfo(String id);
	 
	 List<Ecollege> selectTraining(Ecollege ecollege);
	 
	 List<Ecollege> selectOnlineInfo(Ecollege ecollege);
	 
	 List<Ecollege> selectOfflineInfo(Ecollege ecollege);
	 
	 List<Ecollege> selectOffCourseInfo(Ecollege ecollege);
	 
	 List<Map<String,Object>> getCity(Map<String, String> m);
	 
	 List<Map<String,Object>> getCitys(Map<String, String> m);
	 
//	 List<Ecollege> getCitys(String offlineType);

}
