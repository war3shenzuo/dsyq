package com.etop.management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class EtopThreshold implements Serializable {

	private static final long serialVersionUID = 429079791281464543L;
	
	private String thresholdId;
	
	private String parkId;
	
	private String thresholdKey;
	
	private double value;
	
	private Date updateTime;
	
	private String updateUser;

	public EtopThreshold() {
		thresholdId = UUID.randomUUID().toString();
	}

	public String getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(String thresholdId) {
		this.thresholdId = thresholdId;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getThresholdKey() {
		return thresholdKey;
	}

	public void setThresholdKey(String thresholdKey) {
		this.thresholdKey = thresholdKey;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public enum ThresholdKey {
		OverdueRate("overdue_rate", "每日滞纳金比例"),
		BillAmount("bill_amount", "需要审核的账单金额阈值"),
		deadline("deadline", "缴费日期");
		public final String name;
		public final String desc;
		ThresholdKey(String name, String desc) {
			this.name = name;
			this.desc = desc;
		}
	}
}
