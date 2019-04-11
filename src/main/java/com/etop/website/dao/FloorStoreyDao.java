package com.etop.website.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Floor;
import com.etop.website.bean.FloorStorey;




@Repository
public interface FloorStoreyDao {

	 
	 List<Floor> searchFloorId(String id);
	 
	 List<Floor> searchStoreyId(String refFloorId);
	 
	 List<Floor> searchAreaId(String refStoreyId);
	 
	 FloorStorey findAreaId(String refStoreyId);
}
