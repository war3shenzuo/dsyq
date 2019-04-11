package com.etop.website.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.Floor;
import com.etop.website.bean.FloorStorey;
import com.etop.website.service.FloorStoreyService;
import com.etop.website.dao.FloorStoreyDao;





@Named("FloorStoreyService")
@Service
public class FloorStoreyServerImpl implements FloorStoreyService {

	
	@Resource
	private FloorStoreyDao floorStoreyDao;



	@Override
	public List<Floor> searchFloorId(String id) {
		List<Floor> list= floorStoreyDao.searchFloorId(id);
		return list;
	}




	@Override
	public  List<Floor> searchStoreyId(String refFloorId) {
		
		return floorStoreyDao.searchStoreyId(refFloorId);
	}




	@Override
	public List<Floor> searchAreaId(String refStoreyId) {
		
		return floorStoreyDao.searchAreaId(refStoreyId);
	}




	@Override
	public FloorStorey findAreaId(String refStoreyId) {
		
		return floorStoreyDao.findAreaId(refStoreyId);
	}



	

	
	










	
	
	

}