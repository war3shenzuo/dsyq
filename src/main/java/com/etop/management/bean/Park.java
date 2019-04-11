package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * <br>
 * <b>功能：</b>EtopParkEntity<br>
 */
public class Park extends PageParam{
	
	private java.lang.String id;//  
	private java.lang.String parkCode;//	private java.lang.String parkName;//   园区名称	private java.lang.String parkTitle;//   园区简写	private java.lang.String parkDescribe;//   园区简介	private java.lang.String address;//   地址	private java.lang.String addressImg;//   地址图片	private java.lang.String contacts;//   联系人	private java.lang.String mobile;//   联系人电话	private java.lang.String spareMobile;//   联系人备用电话	private java.lang.String qq;//   QQ
	private java.lang.String city;//   城市	private java.lang.String wechat;//   微信	private java.lang.String wechatQr;//   微信二维码	private java.lang.String policy;//   招商政策	private java.lang.String parkImg;//   园区图片
	private List<String> parkImgList;//   园区图片集合	private java.lang.String approval;//   审批
	private java.lang.String financeApproval;//财务审批	private java.lang.String parkGroupId;//   园区组id
	private java.lang.String parkGroupName;//   园区组id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	private Date createAt;//   创建时间	private java.lang.String activated;//   激活
	
		public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getParkCode() {
		return parkCode;
	}
	public void setParkCode(java.lang.String parkCode) {
		this.parkCode = parkCode;
	}
	public java.lang.String getParkName() {	    return this.parkName;	}	public void setParkName(java.lang.String parkName) {	    this.parkName=parkName;	}	public java.lang.String getParkTitle() {	    return this.parkTitle;	}	public void setParkTitle(java.lang.String parkTitle) {	    this.parkTitle=parkTitle;	}	public java.lang.String getParkDescribe() {	    return this.parkDescribe;	}	public void setParkDescribe(java.lang.String parkDescribe) {	    this.parkDescribe=parkDescribe;	}	public java.lang.String getAddress() {	    return this.address;	}	public void setAddress(java.lang.String address) {	    this.address=address;	}	public java.lang.String getAddressImg() {	    return this.addressImg;	}	public void setAddressImg(java.lang.String addressImg) {	    this.addressImg=addressImg;	}	public java.lang.String getContacts() {	    return this.contacts;	}	public void setContacts(java.lang.String contacts) {	    this.contacts=contacts;	}	public java.lang.String getMobile() {	    return this.mobile;	}	public void setMobile(java.lang.String mobile) {	    this.mobile=mobile;	}	public java.lang.String getSpareMobile() {	    return this.spareMobile;	}	public void setSpareMobile(java.lang.String spareMobile) {	    this.spareMobile=spareMobile;	}	public java.lang.String getQq() {	    return this.qq;	}	public void setQq(java.lang.String qq) {	    this.qq=qq;	}	public java.lang.String getWechat() {	    return this.wechat;	}	public void setWechat(java.lang.String wechat) {	    this.wechat=wechat;	}	public java.lang.String getWechatQr() {	    return this.wechatQr;	}	public void setWechatQr(java.lang.String wechatQr) {	    this.wechatQr=wechatQr;	}	public java.lang.String getPolicy() {	    return this.policy;	}	public void setPolicy(java.lang.String policy) {	    this.policy=policy;	}	public java.lang.String getParkImg() {	    return this.parkImg;	}	public void setParkImg(java.lang.String parkImg) {	    this.parkImg=parkImg;	}	public java.lang.String getApproval() {	    return this.approval;	}	public void setApproval(java.lang.String approval) {	    this.approval=approval;	}	public java.lang.String getParkGroupId() {	    return this.parkGroupId;	}	public void setParkGroupId(java.lang.String parkGroupId) {	    this.parkGroupId=parkGroupId;	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public java.lang.String getActivated() {	    return this.activated;	}	public void setActivated(java.lang.String activated) {	    this.activated=activated;	}
	public java.lang.String getFinanceApproval() {
		return financeApproval;
	}
	public void setFinanceApproval(java.lang.String financeApproval) {
		this.financeApproval = financeApproval;
	}
	public java.lang.String getParkGroupName() {
		return parkGroupName;
	}
	public void setParkGroupName(java.lang.String parkGroupName) {
		this.parkGroupName = parkGroupName;
	}
	public List<String> getParkImgList() {
		return parkImgList;
	}
	public void setParkImgList(List<String> parkImgList) {
		this.parkImgList = parkImgList;
	}
	public java.lang.String getCity() {
		return city;
	}
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	
}

