package com.etop.management.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class EtopBillRelief {

	private String reliefId; // id

	private String billId; // 账单id

	private BigDecimal amountRemission; // 本金减免

	private BigDecimal overdueRemission; // 滞纳金减免

	private String reason; // 减免原因

	private String applicant; // 申请人

	private boolean deleted;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date applyTime; // 申请时间

	private int auditStatus; // 审核状态

	private String auditor; // 审批人

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date auditTime; // 审批时间

	public String getReliefId() {
		return reliefId;
	}

	public void setReliefId(String reliefId) {
		this.reliefId = reliefId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public BigDecimal getAmountRemission() {
		return amountRemission;
	}

	public void setAmountRemission(BigDecimal amountRemission) {
		this.amountRemission = amountRemission;
	}

	public BigDecimal getOverdueRemission() {
		return overdueRemission;
	}

	public void setOverdueRemission(BigDecimal overdueRemission) {
		this.overdueRemission = overdueRemission;
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
		UNCHECK(0, "未审核"), ACCEPT(1, "通过"), REFUSE(2, "拒绝");
		public final int value;
		public final String desc;

		AuditStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public AuditStatus valueOf(int value) {
			switch (value) {
			case 0:
				return UNCHECK;
			case 1:
				return ACCEPT;
			case 2:
				return REFUSE;
			default:
				return null;
			}
		}
	}
}