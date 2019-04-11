package com.etop.management.bean;

import java.util.List;

public class PageEnergyBill extends Page {
	

	
	private String refId;
	
	private int idType;
	
	private List<String> refRoomIds;
	
	private String energyType;
	
	private String enterStartDate;
	
	private String enterEndDate;
	
	private String billStartDate;
	
	private String billEndDate;







	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public List<String> getRefRoomIds() {
		return refRoomIds;
	}

	public void setRefRoomIds(List<String> refRoomIds) {
		this.refRoomIds = refRoomIds;
	}

	public String getEnterStartDate() {
		return enterStartDate;
	}

	public void setEnterStartDate(String enterStartDate) {
		this.enterStartDate = enterStartDate;
	}

	public String getEnterEndDate() {
		return enterEndDate;
	}

	public void setEnterEndDate(String enterEndDate) {
		this.enterEndDate = enterEndDate;
	}

	public String getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(String billStartDate) {
		this.billStartDate = billStartDate;
	}

	public String getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(String billEndDate) {
		this.billEndDate = billEndDate;
	}

	public String getEnergyType() {
		return energyType;
	}

	public void setEnergyType(String energyType) {
		this.energyType = energyType;
	}
	
	

}
