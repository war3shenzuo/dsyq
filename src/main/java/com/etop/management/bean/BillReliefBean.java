package com.etop.management.bean;

import java.math.BigDecimal;

import com.etop.management.entity.EtopBillRelief;

public class BillReliefBean extends EtopBillRelief {

	private String companyName;
	
	private BigDecimal amount;
	
	private BigDecimal amountRelieved;
	
	private BigDecimal overdueFine;
	
	private BigDecimal overdueRelieved;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountRelieved() {
		return amountRelieved;
	}

	public void setAmountRelieved(BigDecimal amountRelieved) {
		this.amountRelieved = amountRelieved;
	}

	public BigDecimal getOverdueRelieved() {
		return overdueRelieved;
	}

	public void setOverdueRelieved(BigDecimal overdueRelived) {
		this.overdueRelieved = overdueRelived;
	}

	public BigDecimal getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(BigDecimal overdueFine) {
		this.overdueFine = overdueFine;
	}
}
