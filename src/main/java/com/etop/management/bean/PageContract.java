package com.etop.management.bean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public class PageContract extends Page {
	

	private String refParkId;
	
	private List<String> refParkIds;
	
	private String refBuildingId;
	
	private String refFloorId;
	
	private String refBlockId;
	
	private String category;
	
	private String room;
	
	private String startDate;
	
	private String endDate;
	
	private String contractStatus;


	public String getRefBuildingId() {
		return refBuildingId;
	}

	public void setRefBuildingId(String refBuildingId) {
		this.refBuildingId = refBuildingId;
	}

	public String getRefFloorId() {
		return refFloorId;
	}

	public void setRefFloorId(String refFloorId) {
		this.refFloorId = refFloorId;
	}

	public String getRefBlockId() {
		return refBlockId;
	}

	public void setRefBlockId(String refBlockId) {
		this.refBlockId = refBlockId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getRefParkId() {
		return refParkId;
	}

	public void setRefParkId(String refParkId) {
		this.refParkId = refParkId;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public List<String> getRefParkIds() {
		return refParkIds;
	}

	public void setRefParkIds(List<String> refParkIds) {
		this.refParkIds = refParkIds;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	

}
