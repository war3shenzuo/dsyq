package com.etop.management.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 能源帐单表
 * @author Administrator
 *
 */
public class EnergyBillModel {

	private String billId;
	
	private String companyName;
	
	private String description;
	
	private float amount;
	
	private float overdueFine;
	
	private int billStatus;
	
	private int auditStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date deadline;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date createdTime;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(float overdueFine) {
		this.overdueFine = overdueFine;
	}

	public int getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
