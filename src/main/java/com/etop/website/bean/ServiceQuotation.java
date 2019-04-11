package com.etop.website.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 维修服务
 * @author lvxiwei
 * 
 */
public class ServiceQuotation {
	
	private String quotationId;//维修ID
	private String quotationName;//维修项目
	private String price;//报价
	private String capitalPrice;//大写金额
	private String description;//描述
	private String units;//单位
	private String type;
	private java.lang.String category;
	private java.lang.String categories;
	private String remark;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private String parkId;
	private String activated;
	
	public String getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	public String getQuotationName() {
		return quotationName;
	}
	public void setQuotationName(String quotationName) {
		this.quotationName = quotationName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCapitalPrice() {
		return capitalPrice;
	}
	public void setCapitalPrice(String capitalPrice) {
		this.capitalPrice = capitalPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public String getCategory() {
//		return category;
//	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public java.lang.String getCategory() {
		return category;
	}
	public void setCategory(java.lang.String category) {
		this.category = category;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public java.lang.String getCategories() {
		return categories;
	}
	public void setCategories(java.lang.String categories) {
		this.categories = categories;
	}


	
	
}
