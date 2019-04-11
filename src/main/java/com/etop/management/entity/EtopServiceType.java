package com.etop.management.entity;
import java.math.BigDecimal;

import com.etop.management.bean.PageParam;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceTypeEntity<br>
 */
public class EtopServiceType extends PageParam{
	
		private java.lang.String title;//   服务大类名	private java.lang.String descript;//   大类服务介绍	private java.lang.String serviceCode;//   服务编号	private java.lang.String serviceName;//   服务名称	private java.lang.String activated;//   是否激活	private java.lang.String item;//   条款
	private String imgUrl;
	
	
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public EtopServiceType() {
		super();
	}
	public EtopServiceType(String serviceCode) {
		super();
		this.serviceCode = serviceCode;
	}
	private String parkgroupId;
	
		public String getParkgroupId() {
		return parkgroupId;
	}
	public void setParkgroupId(String parkgroupId) {
		this.parkgroupId = parkgroupId;
	}
	public java.lang.String getTitle() {	    return this.title;	}	public void setTitle(java.lang.String title) {	    this.title=title;	}	public java.lang.String getDescript() {	    return this.descript;	}	public void setDescript(java.lang.String descript) {	    this.descript=descript;	}	public java.lang.String getServiceCode() {	    return this.serviceCode;	}	public void setServiceCode(java.lang.String serviceCode) {	    this.serviceCode=serviceCode;	}	public java.lang.String getServiceName() {	    return this.serviceName;	}	public void setServiceName(java.lang.String serviceName) {	    this.serviceName=serviceName;	}	public java.lang.String getActivated() {	    return this.activated;	}	public void setActivated(java.lang.String activated) {	    this.activated=activated;	}	public java.lang.String getItem() {	    return this.item;	}	public void setItem(java.lang.String item) {	    this.item=item;	}
}

