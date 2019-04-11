package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * <br>
 * <b>功能：</b>EtopParkGroupEntity<br>
 */
public class ParkGroup{
	private java.lang.String id;//   园区组ID	private java.lang.String parkGroupName;//   园区组名称
	private java.lang.String parkGroupCode;//	private java.lang.String parkGroupDescribe;//    园区组简介
	private java.lang.String introduce;//    园区组介绍
	private java.lang.String parkCount;//    园区组底下的园区数量
	private java.lang.String parkGroupImg;//    园区组图片
	private java.lang.String approval;//   审批人员	private java.lang.String parkGroupType;// 园区类型 0 临时 1正式
	private java.lang.String activated;//  是否激活
	private String account;//默认生产的账号
	private String settledId; //入住详细信息
	private String sld;//二级域名
	private List<String> imgList;
	private String logo;
	private String link;
	
	
	
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	private Date createAt;//   创建时间	
		public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getParkGroupName() {	    return this.parkGroupName;	}	public void setParkGroupName(java.lang.String parkGroupName) {	    this.parkGroupName=parkGroupName;	}	public java.lang.String getParkGroupDescribe() {	    return this.parkGroupDescribe;	}	public void setParkGroupDescribe(java.lang.String parkGroupDescribe) {	    this.parkGroupDescribe=parkGroupDescribe;	}	public java.lang.String getApproval() {	    return this.approval;	}	public void setApproval(java.lang.String approval) {	    this.approval=approval;	}	public java.lang.String getActivated() {	    return this.activated;	}	public void setActivated(java.lang.String activated) {	    this.activated=activated;	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public java.lang.String getParkGroupCode() {
		return parkGroupCode;
	}
	public void setParkGroupCode(java.lang.String parkGroupCode) {
		this.parkGroupCode = parkGroupCode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSettledId() {
		return settledId;
	}
	public void setSettledId(String settledId) {
		this.settledId = settledId;
	}
	public java.lang.String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(java.lang.String introduce) {
		this.introduce = introduce;
	}
	public java.lang.String getParkCount() {
		return parkCount;
	}
	public void setParkCount(java.lang.String parkCount) {
		this.parkCount = parkCount;
	}
	public java.lang.String getParkGroupImg() {
		return parkGroupImg;
	}
	public void setParkGroupImg(java.lang.String parkGroupImg) {
		this.parkGroupImg = parkGroupImg;
	}
	public java.lang.String getParkGroupType() {
		return parkGroupType;
	}
	public void setParkGroupType(java.lang.String parkGroupType) {
		this.parkGroupType = parkGroupType;
	}
	public String getSld() {
		return sld;
	}
	public void setSld(String sld) {
		this.sld = sld;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
	}

