package com.etop.management.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.entity.EtopBillDelay;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BillDelayBean extends EtopBillDelay {

	private String companyName;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date deadline;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
