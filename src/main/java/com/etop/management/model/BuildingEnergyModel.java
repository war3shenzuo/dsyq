package com.etop.management.model;

import java.util.Date;

/**
 * 楼能源model
 * @author Administrator
 *
 */
public class BuildingEnergyModel {

	private String refBuildingId;
	
	private int energyType;
	
	private String feeStartDate; //计费开始，即上期公摊结算日
	
	private String feeEndDate;//计费结束，最后有效记录日期
	
	private int feeDates;//结算天数
	
	private double startRecord;//开始读数
	
	private double endRecord;//结束读数
	
	private double usedRecords;//楼使用量
	
	private int roomCount;//房间总数
	
	private double roomsUsedRecords;//所有房间总使用量
	
	private double shareRecords;//本期公摊量
	
	//来自etop_floor_energy
	private int shareType;//公摊方式
	
	private int roomAmountUsed;//房间是否使用使用量
	
	private Date lastFeeDate;//最后结算日

	public int getEnergyType() {
		return energyType;
	}

	public void setEnergyType(int energyType) {
		this.energyType = energyType;
	}

	public String getFeeStartDate() {
		return feeStartDate;
	}

	public void setFeeStartDate(String feeStartDate) {
		this.feeStartDate = feeStartDate;
	}

	public String getFeeEndDate() {
		return feeEndDate;
	}

	public void setFeeEndDate(String feeEndDate) {
		this.feeEndDate = feeEndDate;
	}

	public int getFeeDates() {
		return feeDates;
	}

	public void setFeeDates(int feeDates) {
		this.feeDates = feeDates;
	}

	public double getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(double startRecord) {
		this.startRecord = startRecord;
	}

	public double getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(double endRecord) {
		this.endRecord = endRecord;
	}

	public double getUsedRecords() {
		return usedRecords;
	}

	public void setUsedRecords(double usedRecords) {
		this.usedRecords = usedRecords;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public double getRoomsUsedRecords() {
		return roomsUsedRecords;
	}

	public void setRoomsUsedRecords(double roomsUsedRecords) {
		this.roomsUsedRecords = roomsUsedRecords;
	}

	public double getShareRecords() {
		return shareRecords;
	}

	public void setShareRecords(double shareRecords) {
		this.shareRecords = shareRecords;
	}

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
	}

	public String getRefBuildingId() {
		return refBuildingId;
	}

	public void setRefBuildingId(String refBuildingId) {
		this.refBuildingId = refBuildingId;
	}
	
	@Override
	public String toString()
	{
		return String.format("BuildingEnergyModel:energyType:%d,feeStartDate:%s,feeEndDate:%s,usedRecords:%.2f,roomsUsedRecords:%.2f,roomCount:%d,shareRecords:%.2f",
				
				this.energyType,this.feeStartDate,this.feeEndDate,this.usedRecords,this.roomsUsedRecords,this.roomCount,this.shareRecords
				
				
				);
	}

	public int getRoomAmountUsed() {
		return roomAmountUsed;
	}

	public void setRoomAmountUsed(int roomAmountUsed) {
		this.roomAmountUsed = roomAmountUsed;
	}

	public Date getLastFeeDate() {
		return lastFeeDate;
	}

	public void setLastFeeDate(Date lastFeeDate) {
		this.lastFeeDate = lastFeeDate;
	}


	
	
	
	
}
