package com.etop.management.entity;

import com.etop.management.bean.IdentifiableBean;

public class EtopBillRule extends IdentifiableBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -829630572919617955L;

	private String refParkId;
	
	//出帐日期
	private String billDate;
	
	//缴费天数
	private int paymentDate;
	
	//计费周期
	private String billPeriod;


	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public int getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}





	public String getBillPeriod() {
		return billPeriod;
	}

	public void setBillPeriod(String billPeriod) {
		this.billPeriod = billPeriod;
	}

	public String getRefParkId() {
		return refParkId;
	}

	public void setRefParkId(String refParkId) {
		this.refParkId = refParkId;
	}
	
	
	
}
