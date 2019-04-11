package com.etop.website.service;

import java.util.List;

import com.etop.website.bean.Floor;
import com.etop.website.bean.FloorStorey;


public interface FloorStoreyService {


	
	public List<Floor> searchFloorId(String id);
	
	public List<Floor> searchStoreyId(String refFloorId);
	
	public List<Floor> searchAreaId(String refStoreyId);
	
	public FloorStorey findAreaId(String refStoreyId);
	
}
