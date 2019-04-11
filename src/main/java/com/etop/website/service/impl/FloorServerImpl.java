package com.etop.website.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.Floor;
import com.etop.website.service.FloorService;
import com.etop.website.dao.FloorDao;





@Named("FoolService")
@Service
public class FloorServerImpl implements FloorService {

	
	@Resource
	private FloorDao floorDao;




	@Override
	public List<Floor> searchFloor(Floor floor) throws Exception {
		List<Floor> list =floorDao.searchFloor(floor);
		return list;
	}




	@Override
	public List<Floor> searchFloorName(String refFloorId) {
		List<Floor> list= floorDao.searchFloorName(refFloorId);
		return list;
	}




	@Override
	public List<Floor> searchRoom(
			String refAreaId) {
		List<Floor> list= floorDao.searchRoom(refAreaId);
		return list;
	}




	@Override
	public Floor searchImg(String roomid) {
		
		return floorDao.searchImg(roomid);
	}



	

	
	










	
	
	

}