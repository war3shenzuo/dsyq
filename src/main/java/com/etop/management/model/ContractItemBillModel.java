package com.etop.management.model;

import com.google.gson.Gson;

/**
 * 轮询contract item时，生成需要出帐的model
 * @author Administrator
 *
 */
public class ContractItemBillModel {

	private String refContractId; //bill out no
	
	private String refContractItemId;
	
	private String currBillDate;
		
	private double dailyUnitPrice;
	
	private double monthlyUnitPrice;
			
	private String feeStartDate;
	
	private String feeEndDate;
	
	private String startDate;
	
	private String endDate;
	
	private String area;
	
	private String paymentDate;
	
	private double amount;
	
	private int balanceMonthly;

	public String getRefContractId() {
		return refContractId;
	}

	public void setRefContractId(String refContractId) {
		this.refContractId = refContractId;
	}

	public String getRefContractItemId() {
		return refContractItemId;
	}

	public void setRefContractItemId(String refContractItemId) {
		this.refContractItemId = refContractItemId;
	}

	public String getCurrBillDate() {
		return currBillDate;
	}

	public void setCurrBillDate(String currBillDate) {
		this.currBillDate = currBillDate;
	}

	public double getDailyUnitPrice() {
		return dailyUnitPrice;
	}

	public void setDailyUnitPrice(double dailyUnitPrice) {
		this.dailyUnitPrice = dailyUnitPrice;
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

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getMonthlyUnitPrice() {
		return monthlyUnitPrice;
	}

	public void setMonthlyUnitPrice(double monthlyUnitPrice) {
		this.monthlyUnitPrice = monthlyUnitPrice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String itemDesc()
	{
		return String.format("合同出帐信息：\r\n"
				+ "分期日期：开始:%s,结束:%s\r\n"
				+ "帐单覆盖日期:开始:%s,结束:%s\r\n"
				+ "日单价:%.2f,月单价:%.2f,面积或内容:%s\r\n"
				+ "总金额:%.2f", 
				this.startDate,this.endDate,
				this.feeStartDate,this.feeEndDate,
				this.dailyUnitPrice,this.monthlyUnitPrice,this.area,
				this.amount
				);
	}
	
	@Override
	public String toString()
	{
		Gson gson = new Gson();
		
		String str=gson.toJson(this);
		
		return String.format("[ContractItemBillModel]:%s",str);
	}

	public int getBalanceMonthly() {
		return balanceMonthly;
	}

	public void setBalanceMonthly(int balanceMonthly) {
		this.balanceMonthly = balanceMonthly;
	}


}
