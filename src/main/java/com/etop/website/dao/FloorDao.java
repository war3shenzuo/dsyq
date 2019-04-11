package com.etop.website.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Floor;




@Repository
public interface FloorDao {

	 List<Floor> searchFloor(Floor floor);

	 List<Floor> searchFloorName(String refFloorId);

	 List<Floor> searchRoom(String refAreaId);
	 
	 public Floor searchImg(String roomid);

}
