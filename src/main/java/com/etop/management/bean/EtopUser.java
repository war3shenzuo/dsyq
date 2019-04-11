package com.etop.management.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EtopUser extends PageParam {
	
	public static final String COMPANY = "1";
	public static final String PERSONAL = "2";
	public static final String TYPE_PARK = "3";
	public static final String TYPE_GROUP = "4";
	public static final String TYPE_SYSTEM = "5";

	public static final String INITIAL_PASSWORD = "123456";
	public static final String ACODE = "888888";
	
	
	private java.lang.String id;          //   账户
	private java.lang.String userName;    //   用户名称
	private java.lang.String name;        //   用户真实名称
	private java.lang.String passWord;    //   密码
	private java.lang.String passWordSalt;//   加密密码
	private java.lang.String userType;    //   1企业,2个人,3园区,4园区系统管理员,5系统管理员
	private java.lang.String companyId;   //   所属公司
	private java.lang.String parkId;      //   园区Id
	private String parkGroupId;           //   园区组Id
	private java.lang.String mobile;      //   联系电话
	private java.lang.String email;       //   电子邮箱
	private java.lang.String activated;   //   是否激活
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createAt;      		   //   创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateAt;      	       //   修改时间
	
	private java.lang.String parkName;      //   园区名称
	private String roleId;//
	
	private String newPassWord;
	
	private EtopCompanyEmployees employees;//个人账户关联表

	@NotDatabaseField
	private String companyName;

	@NotDatabaseField
	private Integer userNum;

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public java.lang.String getId() {
	    return this.id;
	}
	public void setId(java.lang.String id) {
	    this.id=id;
	}
	public java.lang.String getUserName() {
	    return this.userName;
	}
	public void setUserName(java.lang.String userName) {
	    this.userName=userName;
	}
	public java.lang.String getPassWord() {
	    return this.passWord;
	}
	public void setPassWord(java.lang.String passWord) {
	    this.passWord=passWord;
	}
	public java.lang.String getPassWordSalt() {
	    return this.passWordSalt;
	}
	public void setPassWordSalt(java.lang.String passWordSalt) {
	    this.passWordSalt=passWordSalt;
	}
	public java.lang.String getUserType() {
	    return this.userType;
	}
	public void setUserType(java.lang.String userType) {
	    this.userType=userType;
	}
	public java.lang.String getCompanyId() {
	    return this.companyId;
	}
	public void setCompanyId(java.lang.String companyId) {
	    this.companyId=companyId;
	}
	public java.lang.String getParkId() {
	    return this.parkId;
	}
	public void setParkId(java.lang.String parkId) {
	    this.parkId=parkId;
	}
	public java.lang.String getMobile() {
	    return this.mobile;
	}
	public void setMobile(java.lang.String mobile) {
	    this.mobile=mobile;
	}
	public java.lang.String getEmail() {
	    return this.email;
	}
	public void setEmail(java.lang.String email) {
	    this.email=email;
	}
	public java.lang.String getActivated() {
	    return this.activated;
	}
	public void setActivated(java.lang.String activated) {
	    this.activated=activated;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public String getParkGroupId() {
		return parkGroupId;
	}
	public void setParkGroupId(String parkGroupId) {
		this.parkGroupId = parkGroupId;
	}
	public java.lang.String getParkName() {
		return parkName;
	}
	public void setParkName(java.lang.String parkName) {
		this.parkName = parkName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public String getNewPassWord() {
		return newPassWord;
	}
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	public EtopCompanyEmployees getEmployees() {
		return employees;
	}
	public void setEmployees(EtopCompanyEmployees employees) {
		this.employees = employees;
	}
	
	

}
