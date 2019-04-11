package com.etop.management.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public class EtopCompany implements Serializable {

    private static final long serialVersionUID = 8604157775755097973L;

    private String companyType;//  公司类型
    private String companyCode;// 公司编码
    private String companyStatus;//   公司状态  1 正式,2 合同过期, 3合同终止, 4 已迁出
    private String companyStatus2;//   
    private String companyCapital; // 注册资金
    private String  businessType;//   经营类目
    private String beforeseat;//  前经营所在地
    private String companyName;//  公司名称
    private String contact;//   联系人
    private String mobile;//  电话
    private String companyMobile;//   公司电话
    private String companyId;// 公司id
    private String companyFax;//   公司传真
    private String spareContact;//   备用联系人
    private String spareMobile;//   备用联系人电话
    private String email;//   电子邮件
    private String remarks;//   备注
    private Double aveMonthTurnover;//   月均营业额
    @NotDatabaseField
    private Double aveMonthTurnover1;//   月均营业额
    @NotDatabaseField
    private Double aveMonthTurnover2;//   月均营业额

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date inAt;//   迁入时间

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date outAt;//   迁出时间
    private String parkId;//   园区Id

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    private String updatedBy;

    @NotDatabaseField
    private List<String> companyIds;

    @NotDatabaseField
    private List<String> parkIds;

    @NotDatabaseField
    private String parkName;

    @NotDatabaseField
    private Integer companyNum;

    @NotDatabaseField
    private String userName;

    @NotDatabaseField
    private String businessPractice;

    @NotDatabaseField
    private Integer employeesNums1;

    @NotDatabaseField
    private Integer employeesNums2;

    public Integer getEmployeesNums1() {
        return employeesNums1;
    }

    public void setEmployeesNums1(Integer employeesNums1) {
        this.employeesNums1 = employeesNums1;
    }

    public Integer getEmployeesNums2() {
        return employeesNums2;
    }

    public void setEmployeesNums2(Integer employeesNums2) {
        this.employeesNums2 = employeesNums2;
    }

    public String getBusinessPractice() {
        return businessPractice;
    }

    public void setBusinessPractice(String businessPractice) {
        this.businessPractice = businessPractice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Integer companyNum) {
        this.companyNum = companyNum;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyCapital() {
        return companyCapital;
    }

    public void setCompanyCapital(String companyCapital) {
        this.companyCapital = companyCapital;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBeforeseat() {
        return beforeseat;
    }

    public void setBeforeseat(String beforeseat) {
        this.beforeseat = beforeseat;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getSpareContact() {
        return spareContact;
    }

    public void setSpareContact(String spareContact) {
        this.spareContact = spareContact;
    }

    public String getSpareMobile() {
        return spareMobile;
    }

    public void setSpareMobile(String spareMobile) {
        this.spareMobile = spareMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getInAt() {
        return inAt;
    }

    public void setInAt(Date inAt) {
        this.inAt = inAt;
    }

    public Date getOutAt() {
        return outAt;
    }

    public void setOutAt(Date outAt) {
        this.outAt = outAt;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

	public String getCompanyStatus2() {
		return companyStatus2;
	}

	public void setCompanyStatus2(String companyStatus2) {
		this.companyStatus2 = companyStatus2;
	}

	public Double getAveMonthTurnover() {
		return aveMonthTurnover;
	}

	public Double getAveMonthTurnover1() {
		return aveMonthTurnover1;
	}

	public void setAveMonthTurnover1(Double aveMonthTurnover1) {
		this.aveMonthTurnover1 = aveMonthTurnover1;
	}

	public Double getAveMonthTurnover2() {
		return aveMonthTurnover2;
	}

	public void setAveMonthTurnover2(Double aveMonthTurnover2) {
		this.aveMonthTurnover2 = aveMonthTurnover2;
	}

	public void setAveMonthTurnover(Double aveMonthTurnover) {
		this.aveMonthTurnover = aveMonthTurnover;
	}
}
