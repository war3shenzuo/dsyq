package com.etop.management.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Companyservice extends ServiceBusiness{

    private String serviceId;
    
    private String serviceNo;

    private String clubId;
    
    private String serviceType; 
    
    private String serviceStatus;
      
    private String companyId;
    
    private String companyName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
      
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date completeTime;
    
    private String applicant;
    
    private String applicantPhone;

    private String description;
      
    private String isfree;
  
    private String parkCode;
    
    private String serviceCode;
    
    private String serviceName;
    
    private String changes;//修改信息
    
    private String busnumber;
    
    private String purnumber;   
    
    private BigDecimal busprice;
    
    private BigDecimal purprice;   
    
    private String categories;
    
    private String duration;
    
    private BigDecimal busunitPrice;
    
    private BigDecimal purunitPrice;
    
    
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplicantPhone() {
		return applicantPhone;
	}

	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsfree() {
		return isfree;
	}

	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getBusnumber() {
		return busnumber;
	}

	public void setBusnumber(String busnumber) {
		this.busnumber = busnumber;
	}

	public String getPurnumber() {
		return purnumber;
	}

	public void setPurnumber(String purnumber) {
		this.purnumber = purnumber;
	}

	public BigDecimal getBusprice() {
		return busprice;
	}

	public void setBusprice(BigDecimal busprice) {
		this.busprice = busprice;
	}

	public BigDecimal getPurprice() {
		return purprice;
	}

	public void setPurprice(BigDecimal purprice) {
		this.purprice = purprice;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public BigDecimal getBusunitPrice() {
		return busunitPrice;
	}

	public void setBusunitPrice(BigDecimal busunitPrice) {
		this.busunitPrice = busunitPrice;
	}

	public BigDecimal getPurunitPrice() {
		return purunitPrice;
	}

	public void setPurunitPrice(BigDecimal purunitPrice) {
		this.purunitPrice = purunitPrice;
	}




}
