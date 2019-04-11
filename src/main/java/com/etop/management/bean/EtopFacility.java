package com.etop.management.bean;
import java.math.BigDecimal;

import com.etop.management.bean.Criteria;
/**
 * 
 * <br>
 * <b>功能：</b>EtopFacilityEntity<br>
 */
public class EtopFacility extends EtopGoods{
	
		private java.lang.String id;//   	private java.lang.String parkId;//   园区Id	private java.lang.String facilityName;//   设施名称	private java.lang.String facilityType;//   设施类型	private BigDecimal facilityPrice;//   设施价格	private java.lang.String activated;//   是否激活
	
	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getParkId() {	    return this.parkId;	}	public void setParkId(java.lang.String parkId) {	    this.parkId=parkId;	}	public java.lang.String getFacilityName() {	    return this.facilityName;	}	public void setFacilityName(java.lang.String facilityName) {	    this.facilityName=facilityName;	}	public java.lang.String getFacilityType() {	    return this.facilityType;	}	public void setFacilityType(java.lang.String facilityType) {	    this.facilityType=facilityType;	}/*	public java.lang.String getFacilityPrice() {
		return facilityPrice;
	}
	public void setFacilityPrice(java.lang.String facilityPrice) {
		this.facilityPrice = facilityPrice;
	}*/
	public java.lang.String getActivated() {	    return this.activated;	}	public void setActivated(java.lang.String activated) {	    this.activated=activated;	}
	
	public Criteria getCriteria(){
		Criteria c=new Criteria();
		if(this.facilityName!=null){
			c.put("facilityName", facilityName);
		}
		if(this.facilityType!=null){
			c.put("facilityType", facilityType);
		}
		if(this.parkId!=null){
			c.put("parkId", parkId);
		}
		
		return c;
	}
	public BigDecimal getFacilityPrice() {
		return facilityPrice;
	}
	public void setFacilityPrice(BigDecimal facilityPrice) {
		this.facilityPrice = facilityPrice;
	}
}

