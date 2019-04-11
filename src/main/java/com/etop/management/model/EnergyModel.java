package com.etop.management.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 录入能源记录表
 * @author Administrator
 *
 */
public class EnergyModel {

	private String id;
	
	private String refItemId;
	
	private String itemName;
	
	private double record;
	
	private Date recordDate;//当前录入日期，计算得来,或根据当前已有记录设置
	
	private double lastRecord;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date lastRecordDate;
	
	//楼用
	private int energyEnterType;
	
	private int energyEnterDay;
	
	private Date lastFeeDate;//最后结算日，从楼中 取出，若为空则需要根据规则重新计算上一次录入日
	
	private int energyType;//能源类型
	
	private String currRecordDate;//当前录入日期，计算得来
	
	private int shareType;
	
	private int roomAmountUsed;
	
	private int isBilled;//本次录入数据用，上次为结算读数 isBilled=1
	
	private List<EnergySupplyModel> supplyList;//补登能源列表
	

	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
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

	public int getEnergyType() {
		return energyType;
	}

	public void setEnergyType(int energyType) {
		this.energyType = energyType;
	}

	public int getEnergyEnterType() {
		return energyEnterType;
	}

	public void setEnergyEnterType(int energyEnterType) {
		this.energyEnterType = energyEnterType;
	}

	public int getEnergyEnterDay() {
		return energyEnterDay;
	}

	public void setEnergyEnterDay(int energyEnterDay) {
		this.energyEnterDay = energyEnterDay;
	}

	public String getRefItemId() {
		return refItemId;
	}

	public void setRefItemId(String refItemId) {
		this.refItemId = refItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(double lastRecord) {
		this.lastRecord = lastRecord;
	}

	public Date getLastRecordDate() {
		return lastRecordDate;
	}

	public void setLastRecordDate(Date lastRecordDate) {
		this.lastRecordDate = lastRecordDate;
	}

	public int getIsBilled() {
		return isBilled;
	}

	public void setIsBilled(int isBilled) {
		this.isBilled = isBilled;
	}

	public double getRecord() {
		return record;
	}

	public void setRecord(double record) {
		this.record = record;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getCurrRecordDate() {
		return currRecordDate;
	}

	public void setCurrRecordDate(String currRecordDate) {
		this.currRecordDate = currRecordDate;
	}

	public List<EnergySupplyModel> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<EnergySupplyModel> supplyList) {
		this.supplyList = supplyList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
