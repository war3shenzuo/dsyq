package com.etop.management.entity;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.PageParam;
/**
 * 
 * <br>
 * <b>功能：</b>EtopGoodsEntity<br>
 */
public class EtopGoods extends PageParam{
	
		private java.lang.String id;//   	private java.lang.String goodName;//   	private java.lang.String unit;//   	private BigDecimal unitPrice;//   	private java.lang.Long createdTime;//   	private java.lang.String activated;//   	private java.lang.String parkId;//   
	private String createdStr;
	private java.lang.String category;
	private java.lang.String categories;
	private java.lang.String description;
	private java.lang.String remark;
	
		public String getCreatedStr() {
		return createdStr;
	}
	public void setCreatedStr(String createdStr) {
		this.createdStr = createdStr;
	}
	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getGoodName() {	    return this.goodName;	}	public void setGoodName(java.lang.String goodName) {	    this.goodName=goodName;	}	public java.lang.String getUnit() {	    return this.unit;	}	public void setUnit(java.lang.String unit) {	    this.unit=unit;	}	public BigDecimal getUnitPrice() {	    return this.unitPrice;	}	public void setUnitPrice(BigDecimal unitPrice) {	    this.unitPrice=unitPrice;	}	public java.lang.Long getCreatedTime() {	    return this.createdTime;	}	public void setCreatedTime(java.lang.Long createdTime) {
		this.setCreatedStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new  Date(createdTime)));	    this.createdTime=createdTime;	}	public java.lang.String getActivated() {	    return this.activated;	}	public void setActivated(java.lang.String activated) {	    this.activated=activated;	}	public java.lang.String getParkId() {	    return this.parkId;	}	public void setParkId(java.lang.String parkId) {	    this.parkId=parkId;	}
	

	public Criteria getCriteria(){
		Criteria c=new Criteria();
		if(this.goodName!=null){
			c.put("goodName", this.goodName);
		}
		if(this.category!=null){
			c.put("category", category);
		}
		if(this.categories!=null){
			c.put("categories", categories);
		}
		if(this.parkId!=null){
			c.put("parkId", parkId);
		}
		if(this.activated!=null){
			c.put("activated", activated);
		}
		return c;
	}
	public java.lang.String getCategory() {
		return category;
	}
	public void setCategory(java.lang.String category) {
		this.category = category;
	}
	public java.lang.String getDescription() {
		return description;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	public java.lang.String getCategories() {
		return categories;
	}
	public void setCategories(java.lang.String categories) {
		this.categories = categories;
	}
}

