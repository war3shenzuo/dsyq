package com.etop.management.bean;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * <br>
 * <b>功能：</b>EtopContractExpressEntity<br>
 */
public class ContractExpress extends TrackableBean{
		private String refContractId;//   关联合同ID	private int billDate;//   出账日期	private int paymentDate;//   缴费日期	private int billPeriod;//   计费周期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date lastBillDate;
	private float lostBillPrice;//   丢失面单价格
	private float incompleteBillPrice;//   残缺面单价格

		public int getBillDate() {
		return billDate;
	}
	public void setBillDate(int billDate) {
		this.billDate = billDate;
	}
	public int getBillPeriod() {
		return billPeriod;
	}
	public void setBillPeriod(int billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getRefContractId() {	    return this.refContractId;	}	public void setRefContractId(String refContractId) {	    this.refContractId=refContractId;	}			public int getPaymentDate() {	    return this.paymentDate;	}	public void setPaymentDate(int paymentDate) {	    this.paymentDate=paymentDate;	}		public float getLostBillPrice() {	    return this.lostBillPrice;	}	public void setLostBillPrice(float lostBillPrice) {	    this.lostBillPrice=lostBillPrice;	}	public float getIncompleteBillPrice() {	    return this.incompleteBillPrice;	}	public void setIncompleteBillPrice(float incompleteBillPrice) {	    this.incompleteBillPrice=incompleteBillPrice;	}
	public Date getLastBillDate() {
		return lastBillDate;
	}
	public void setLastBillDate(Date lastBillDate) {
		this.lastBillDate = lastBillDate;
	}	
}

