package com.etop.management.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Settled  extends PageParam {

    private String id;
    
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date openTime;//开园时间
    
    private String parkName;//名称
    
    private String address;//地址
    
    private String belongUnit;//所属单位
    
    private String operateUnit;//运营单位
    
    private String parkType;//园区类型
    
    private String parkSize;//园区面积
    
    private String rentalRate;//园区出租率
    
    private String contacts;//联系人
    
    private String mobile;//联系电话
    
    private String email;//电子邮箱
    
    private String qq;
    
    private String wechat;
    
    private String city;
    
    private String openTimeStr;
    
    
    
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    
	
	
    public String getOpenTimeStr() {
		return openTimeStr;
	}

	public void setOpenTimeStr(String openTimeStr) {
		this.openTimeStr = openTimeStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBelongUnit() {
		return belongUnit;
	}

	public void setBelongUnit(String belongUnit) {
		this.belongUnit = belongUnit;
	}

	public String getOperateUnit() {
		return operateUnit;
	}

	public void setOperateUnit(String operateUnit) {
		this.operateUnit = operateUnit;
	}

	public String getParkType() {
		return parkType;
	}

	public void setParkType(String parkType) {
		this.parkType = parkType;
	}

	public String getParkSize() {
		return parkSize;
	}

	public void setParkSize(String parkSize) {
		this.parkSize = parkSize;
	}

	public String getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(String rentalRate) {
		this.rentalRate = rentalRate;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
