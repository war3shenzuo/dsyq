package com.etop.management.model;

import com.etop.management.bean.EnergyCost;

/**
 * 单个能源项，出帐用
 * @author Administrator
 *
 */
public class EnergyItemForBillModel {

	private int energyType; //能源类型0123
	
	private float price;//单价
	
	private String feeStartDate;//计费开始日期，即上次结算日
	
	private String feeEndDate;//计费结束日期
	
	private int feeDates;//本期计费天数
	
	private double firstRecord;//计费开始读数，即上次结算读数
	
	private double lastRecord;//计费结束读数
	
	private double usedRecords;//本期使用量
	
	private double roomsUsedRecords;//本期楼内所有房间使用量
		
	private double roomShareRecords;//本期公摊量
	
	private double roomsShareRecords;//本期总公摊量
	
	private double usedAmount;//本期使用费用
	
	private double shareAmount;//本期公摊费用
	
	private int shareType;//公摊方式0使用量1面积
	
	private int roomAmountUsed;//房间使用量
	
	private float buildArea;//建筑面积
	
	private String itemContent;//能源项帐单内容
	
	

	public int getEnergyType() {
		return energyType;
	}

	public void setEnergyType(int energyType) {
		this.energyType = energyType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public double getFirstRecord() {
		return firstRecord;
	}

	public void setFirstRecord(double firstRecord) {
		this.firstRecord = firstRecord;
	}

	public double getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(double lastRecord) {
		this.lastRecord = lastRecord;
	}

	public double getUsedRecords() {
		return usedRecords;
	}

	public void setUsedRecords(double usedRecords) {
		this.usedRecords = usedRecords;
	}



	public double getRoomShareRecords() {
		return roomShareRecords;
	}

	public void setRoomShareRecords(double roomShareRecords) {
		this.roomShareRecords = roomShareRecords;
	}

	public double getRoomsShareRecords() {
		return roomsShareRecords;
	}

	public void setRoomsShareRecords(double roomsShareRecords) {
		this.roomsShareRecords = roomsShareRecords;
	}

	public double getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(double usedAmount) {
		this.usedAmount = usedAmount;
	}

	public double getShareAmount() {
		return shareAmount;
	}

	public void setShareAmount(double shareAmount) {
		this.shareAmount = shareAmount;
	}

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
	}

	public float getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(float buildArea) {
		this.buildArea = buildArea;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public int getFeeDates() {
		return feeDates;
	}

	public void setFeeDates(int feeDates) {
		this.feeDates = feeDates;
	}

	public double getRoomsUsedRecords() {
		return roomsUsedRecords;
	}

	public void setRoomsUsedRecords(double roomsUsedRecords) {
		this.roomsUsedRecords = roomsUsedRecords;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s:单价:%.2f,使用量:%.2f,公摊:%.2f,总额：%.2f", 
				EnergyCost.EnergyType.valueOf((this.energyType)).desc,
				this.price,
				this.usedRecords,
				this.roomShareRecords,
				this.usedAmount+this.shareAmount
				
				);
	}
	
	public String desc()
	{
		return String.format("%s:单价:%.2f,面积:%f,计费开始日:%s,结束日:%s,开始读数:%.2f,结束读数:%.2f,使用量:%.2f,本期公摊量:%.2f,楼总公摊量：%.2f,公摊方式:%s,房间使用量:%s,总额：%.2f", 
				EnergyCost.EnergyType.valueOf((this.energyType)).desc,
				this.price,this.buildArea,
				this.feeStartDate,this.feeEndDate,this.firstRecord,this.lastRecord,this.usedRecords,
				this.roomShareRecords,this.roomsShareRecords,
				EnergyCost.ShareType.valueOf(this.getShareType()).desc,
				this.getRoomAmountUsed()==1?"使用":"不用",
				this.usedAmount+this.shareAmount
				
				);
	}

	public int getRoomAmountUsed() {
		return roomAmountUsed;
	}

	public void setRoomAmountUsed(int roomAmountUsed) {
		this.roomAmountUsed = roomAmountUsed;
	}
	

	
}
