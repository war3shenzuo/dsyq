package com.etop.management.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

/**
 * 
 * <br>
 * <b>功能：</b>EtopContractEnergyEntity<br>
 */
public class ContractEnergy extends TrackableBean{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = -6847105741152924364L;
	private String refContractId;//   关联合同ID	private float powerPrice;//   单价
	private float waterPrice;//   单价
	private float gasPrice;//   单价
	private float acPrice;//   单价
	
	private double powerRecord;
	
	private double waterRecord;
	
	private double gasRecord;
	
	private double acRecord;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date powerRecordDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date waterRecordDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date gasRecordDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date acRecordDate;
	
	
//	private int billType;//出帐方式0自定义1统一规则
//	private String billDates;//自定义出帐方式，日期逗号分隔//	private String billDate;//   出账日期//	private int paymentDate;//   缴费日期//	private int billPeriod;//   计费周期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	//private Date lastFeeDate;//   
	
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
//	private Date startDate;//   开始日期
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
//	private Date endDate;//   结束日期	
	public String getRefContractId() {	    return this.refContractId;	}	public void setRefContractId(String refContractId) {	    this.refContractId=refContractId;	}	//	//	public int getPaymentDate() {//	    return this.paymentDate;//	}//	public void setPaymentDate(int paymentDate) {//	    this.paymentDate=paymentDate;//	}
	public float getPowerPrice() {
		return powerPrice;
	}
	public void setPowerPrice(float powerPrice) {
		this.powerPrice = powerPrice;
	}
	public float getWaterPrice() {
		return waterPrice;
	}
	public void setWaterPrice(float waterPrice) {
		this.waterPrice = waterPrice;
	}
	public float getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(float gasPrice) {
		this.gasPrice = gasPrice;
	}
	public float getAcPrice() {
		return acPrice;
	}
	public void setAcPrice(float acPrice) {
		this.acPrice = acPrice;
	}
//	public Date getLastFeeDate() {
//		return lastFeeDate;
//	}
//	public void setLastFeeDate(Date lastFeeDate) {
//		this.lastFeeDate = lastFeeDate;
//	}


	public Date getPowerRecordDate() {
		return powerRecordDate;
	}
	public double getPowerRecord() {
		return powerRecord;
	}
	public void setPowerRecord(double powerRecord) {
		this.powerRecord = powerRecord;
	}
	public double getWaterRecord() {
		return waterRecord;
	}
	public void setWaterRecord(double waterRecord) {
		this.waterRecord = waterRecord;
	}
	public double getGasRecord() {
		return gasRecord;
	}
	public void setGasRecord(double gasRecord) {
		this.gasRecord = gasRecord;
	}
	public double getAcRecord() {
		return acRecord;
	}
	public void setAcRecord(double acRecord) {
		this.acRecord = acRecord;
	}
	public void setPowerRecordDate(Date powerRecordDate) {
		this.powerRecordDate = powerRecordDate;
	}
	public Date getWaterRecordDate() {
		return waterRecordDate;
	}
	public void setWaterRecordDate(Date waterRecordDate) {
		this.waterRecordDate = waterRecordDate;
	}
	public Date getGasRecordDate() {
		return gasRecordDate;
	}
	public void setGasRecordDate(Date gasRecordDate) {
		this.gasRecordDate = gasRecordDate;
	}
	public Date getAcRecordDate() {
		return acRecordDate;
	}
	public void setAcRecordDate(Date acRecordDate) {
		this.acRecordDate = acRecordDate;
	}
	
//	public String getBillDate() {
//		return billDate;
//	}
//	public void setBillDate(String billDate) {
//		this.billDate = billDate;
//	}
//	public int getBillPeriod() {
//		return billPeriod;
//	}
//	public void setBillPeriod(int billPeriod) {
//		this.billPeriod = billPeriod;
//	}
//	public Date getLastBillDate() {
//		return lastBillDate;
//	}
//	public void setLastBillDate(Date lastBillDate) {
//		this.lastBillDate = lastBillDate;
//	}
//	public String getBillDates() {
//		return billDates;
//	}
//	public void setBillDates(String billDates) {
//		this.billDates = billDates;
//	}
//	public int getBillType() {
//		return billType;
//	}
//	public void setBillType(int billType) {
//		this.billType = billType;
//	}
//	public Date getStartDate() {
//		return startDate;
//	}
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//	public Date getEndDate() {
//		return endDate;
//	}
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}	
	public float getPrice(int energyType)
	{
		switch (energyType){
			case 0:return this.powerPrice;
			case 1:return this.waterPrice;
			case 2:return this.gasPrice;
			case 3:return this.acPrice;
			default:return 0;
		}
	}
	
	@Override
	public String toString(){
		
		Gson gson = new Gson();
		
		String str=gson.toJson(this);
		
		return String.format("[ContractEnergy]:%s",str);
		
		
	}	
}

