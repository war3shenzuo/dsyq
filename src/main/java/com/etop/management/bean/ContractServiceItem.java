package com.etop.management.bean;

import com.google.gson.Gson;

public class ContractServiceItem extends IdentifiableBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5651087310003459715L;

	private String refParkId;
	
	private String serviceName;
	
	private String serviceDesc;
	
	private boolean isValid;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getRefParkId() {
		return refParkId;
	}

	public void setRefParkId(String refParkId) {
		this.refParkId = refParkId;
	}
	
	@Override
	public String toString(){
		
		Gson gson = new Gson();
		
		String str=gson.toJson(this);
		
		return String.format("[ContractServiceItem]:%s",str);
		
		
	}
	
}
