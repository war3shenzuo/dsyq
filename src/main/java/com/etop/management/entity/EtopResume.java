package com.etop.management.entity;
import java.math.BigDecimal;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.PageParam;
/**
 * 
 * <br>
 * <b>功能：</b>EtopResumeEntity<br>
 */
public class EtopResume extends PageParam{
	
		private java.lang.String id;//   	private java.lang.String serviceId;//   服务Id	private java.lang.String name;//   姓名	private java.lang.String mobile;//   手机	private java.lang.String email;//   邮箱	private java.lang.String fileUrl;//   简历地址	private java.lang.String isAffirm;//   
	private int status;//   
		public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getServiceId() {	    return this.serviceId;	}	public void setServiceId(java.lang.String serviceId) {	    this.serviceId=serviceId;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getMobile() {	    return this.mobile;	}	public void setMobile(java.lang.String mobile) {	    this.mobile=mobile;	}	public java.lang.String getEmail() {	    return this.email;	}	public void setEmail(java.lang.String email) {	    this.email=email;	}	public java.lang.String getFileUrl() {	    return this.fileUrl;	}	public void setFileUrl(java.lang.String fileUrl) {	    this.fileUrl=fileUrl;	}	public java.lang.String getIsAffirm() {	    return this.isAffirm;	}	public void setIsAffirm(java.lang.String isAffirm) {	    this.isAffirm=isAffirm;	}
	public Criteria getCriteria(){
		Criteria c=new Criteria();
		if(this.name!=null){
			c.put("name", this.name);
		}
		if(this.serviceId!=null){
			c.put("serviceId", serviceId);
		}
		return c;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}

