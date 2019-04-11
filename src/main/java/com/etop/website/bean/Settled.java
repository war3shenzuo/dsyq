package com.etop.website.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Settled {

    private String id;
    
    private String open_time;
    
    private String park_name;
    
    private String address;
    
    private String belong_unit;
    
    private String operate_unit;
    
    private String park_type;
    
    private String park_size;
    
    private String rental_rate;
    
    private String contacts;
    
    private String mobile;
    
    private String email;
    
    private String qq;
    
    private String wechat;
    
    private String city;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date apply_time;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBelong_unit() {
		return belong_unit;
	}

	public void setBelong_unit(String belong_unit) {
		this.belong_unit = belong_unit;
	}

	public String getOperate_unit() {
		return operate_unit;
	}

	public void setOperate_unit(String operate_unit) {
		this.operate_unit = operate_unit;
	}

	public String getPark_type() {
		return park_type;
	}

	public void setPark_type(String park_type) {
		this.park_type = park_type;
	}

	public String getPark_size() {
		return park_size;
	}

	public void setPark_size(String park_size) {
		this.park_size = park_size;
	}

	public String getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(String rental_rate) {
		this.rental_rate = rental_rate;
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

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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
