package com.etop.management.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;


/**
 * 
 * <br>
 * <b>功能：</b>EtopContractLeaseEntity<br>
 */
public class ContractItem extends TrackableBean{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -1563738426014833359L;
	public String refContractId;//   关联合同ID
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date startDate;//   开始日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date endDate;//   结束日期
	public double dailyUnitPrice;//   日单价
	public double monthlyUnitPrice;//   月单价
	public double totalAmount;//   总价
	public String content;//   面积或服务内容
//	public int billDateAhead;//提前出帐日
	public int billType;//出帐日方式0自定义1统一规则
	public String billDates;//自定义出帐日，逗号分隔
	public String billDate;//   出账日
	public int paymentDate;//   缴费日
	public String billPeriod;//   计费周期
	public int balanceMonthly; //是否按月结算
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
//	public Date lastBillDate; //初始为startDate前一天
	
	
	//public Date lastFeeDate;//最后结算日
	
	@NotDatabaseField
	private Date periods;

	public Date getPeriods() {
		return periods;
	}

	public void setPeriods(Date periods) {
		this.periods = periods;
	}

	public String getRefContractId() {
	    return this.refContractId;
	}
//	public int getBillDateAhead() {
//		return billDateAhead;
//	}
//	public void setBillDateAhead(int billDateAhead) {
//		this.billDateAhead = billDateAhead;
//	}
	public void setRefContractId(String refContractId) {
	    this.refContractId=refContractId;
	}
	public Date getStartDate() {
	    return this.startDate;
	}
	public void setStartDate(Date startDate) {
	    this.startDate=startDate;
	}
	public Date getEndDate() {
	    return this.endDate;
	}
	public void setEndDate(Date endDate) {
	    this.endDate=endDate;
	}
	public double getDailyUnitPrice() {
	    return this.dailyUnitPrice;
	}
	public void setDailyUnitPrice(double dailyUnitPrice) {
	    this.dailyUnitPrice=dailyUnitPrice;
	}
	public double getMonthlyUnitPrice() {
	    return this.monthlyUnitPrice;
	}
	public void setMonthlyUnitPrice(double monthlyUnitPrice) {
	    this.monthlyUnitPrice=monthlyUnitPrice;
	}
	public double getTotalAmount() {
	    return this.totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
	    this.totalAmount=totalAmount;
	}

	public int getPaymentDate() {
	    return this.paymentDate;
	}
	public void setPaymentDate(int paymentDate) {
	    this.paymentDate=paymentDate;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	
	public String getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}
//	public Date getLastBillDate() {
//		return lastBillDate;
//	}
//	public void setLastBillDate(Date lastBillDate) {
//		this.lastBillDate = lastBillDate;
//	}
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public String getBillDates() {
		return billDates;
	}
	public void setBillDates(String billDates) {
		this.billDates = billDates;
	}
//	public Date getLastFeeDate() {
//		return lastFeeDate;
//	}
//	public void setLastFeeDate(Date lastFeeDate) {
//		this.lastFeeDate = lastFeeDate;
//	}

	public int getBalanceMonthly() {
		return balanceMonthly;
	}

	public void setBalanceMonthly(int balanceMonthly) {
		this.balanceMonthly = balanceMonthly;
	}

	@Override
	public String toString(){
		
		Gson gson = new Gson();
		
		String str=gson.toJson(this);
		
		return String.format("[ContractItem]:%s",str);
		
		
	}

	
}

