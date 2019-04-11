package com.etop.website.service;

import java.util.List;

import com.etop.website.bean.Floor;


public interface FloorService {

	public List<Floor> searchFloor(Floor floor) throws  Exception;

	public List<Floor> searchFloorName(String refFloorId);
	
	public List<Floor> searchRoom(String refAreaId);

	public Floor searchImg(String roomid);
	
	
}
