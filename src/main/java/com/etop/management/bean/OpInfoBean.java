package com.etop.management.bean;

public class OpInfoBean {

	private String oper;
	
	private String opMobile;

	public OpInfoBean(){
		this.oper="anonymous";
		this.opMobile="123456";
	}
	
	public String getOpMobile() {
		return opMobile;
	}

	public void setOpMobile(String opMobile) {
		this.opMobile = opMobile;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
	
	
}
