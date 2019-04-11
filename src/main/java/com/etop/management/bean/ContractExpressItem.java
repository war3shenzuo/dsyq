package com.etop.management.bean;
/**
 * 
 * <br>
 * <b>功能：</b>EtopContractExpressItemEntity<br>
 */
public class ContractExpressItem extends TrackableBean{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 741179680145084861L;
	private String refContractId;//   合同ID	private String destination;//   目的地	private String blockId;//   区域ID	private String firstHeavy;//   首重	private String firstHeavyPrice;//   首重价格	private String forwardingHeavy;//   续重	private String forwardingHeavyPrice;//   续重价格 	public String getRefContractId() {	    return this.refContractId;	}	public void setRefContractId(String refContractId) {	    this.refContractId=refContractId;	}	public String getDestination() {	    return this.destination;	}	public void setDestination(String destination) {	    this.destination=destination;	}	public String getBlockId() {	    return this.blockId;	}	public void setBlockId(String blockId) {	    this.blockId=blockId;	}	public String getFirstHeavy() {	    return this.firstHeavy;	}	public void setFirstHeavy(String firstHeavy) {	    this.firstHeavy=firstHeavy;	}	public String getFirstHeavyPrice() {	    return this.firstHeavyPrice;	}	public void setFirstHeavyPrice(String firstHeavyPrice) {	    this.firstHeavyPrice=firstHeavyPrice;	}	public String getForwardingHeavy() {	    return this.forwardingHeavy;	}	public void setForwardingHeavy(String forwardingHeavy) {	    this.forwardingHeavy=forwardingHeavy;	}	public String getForwardingHeavyPrice() {	    return this.forwardingHeavyPrice;	}	public void setForwardingHeavyPrice(String forwardingHeavyPrice) {	    this.forwardingHeavyPrice=forwardingHeavyPrice;	}	
}

