package com.etop.management.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EtopBillDelay {

	private String delayId;	//id

	private String billId;	//账单id

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date delayTime;	//延迟时间

	private String reason;	//延迟理由

	private String applicant;	//申请人

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date applyTime;	//申请时间
	
	private int auditStatus;		//审核状态
	
	private String auditDesc;	//审核描述

	private String auditor;	//审批人

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date auditTime;	//审批时间
	
	private boolean deleted;

	public String getDelayId() {
		return delayId;
	}

	public void setDelayId(String delayId) {
		this.delayId = delayId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Date getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public enum AuditStatus {
		UNCHECK(0, "未审核"),
		ACCEPT(1, "通过"),
		REFUSE(2, "拒绝");
		public final int value;
		public final String desc;
		AuditStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public AuditStatus valueOf(int value) {
			switch (value) {
			case 0 :
				return UNCHECK;
			case 1 :
				return ACCEPT;
			case 2 :
				return REFUSE;
			default :
				return null;
			}
		}
	}
}