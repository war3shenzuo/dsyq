package com.etop.management.bean;

import com.etop.management.entity.EtopBill;

public class BillWithCompanyItem extends EtopBill {

	private String companyName;	//公司名称（客户）
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
