package com.etop.management.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class EtopBillPay {

	private String payId;	//id

	private String billId;	//订单号

	private BigDecimal amount;	//金额

	private int payType;	//支付方式：银行、支票、支付宝、微信、现金等

	private String payNoOut;	//外部支付凭证号

	private String attachment;	//附件

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;	//时间

	private String recorder;	//记录员

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getPayNoOut() {
		return payNoOut;
	}

	public void setPayNoOut(String payNoOut) {
		this.payNoOut = payNoOut;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	
	public enum PayType {
		UNKNOWN(0, "未知"),
		BANK(1, "银行转账"),
		CHECKING(2, "支票"),
		ALIPAY(3, "支付宝"),
		WXPAY(4, "微信"),
		CASH(5, "现金"),
		OTHER(9, "其他");
		public final int value;
		public final String desc;
		PayType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
	}
}