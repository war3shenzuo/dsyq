package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by lvxiwei.
 *
 * @author lvxiwei
 * @DATE 2016/10/17
 */
public class PersonalInfomation extends Workexperience {

    private static final long serialVersionUID = -3461493499016074072L;

   /* 员工基础信息*/
    private String id;
    private String employeesId;//员工id
    private String employeesName;//员工姓名
    private Integer employeesSex;//性别 1 男 ，2 女
    private Integer employeesStatus;//员工状态 1 正常 ，2 离职 ，3 禁用

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;//出生日期

    private String address;//户籍所在地
    private String card;//证件号码
    private String mobile;//手机号码
    private String email;//邮箱
    private String wechat;//微信号
    private String otherContact;//其他联系方式

    /*就职信息*/
    private String companyName;//公司名称
    private String companyId;
    private String department;//部门
    private String jobs;//岗位

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date hiredate;//入职时间

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;//劳动合同开始时间

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;//劳动合同结束时间

    /*教育背景*/
    private String graduate;//毕业院校
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;//开始日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date overDate;//结束日期
    private String schoolName;//学校名称
    private String professional;//专业
    private String degree;//学位
    private String remark;//备注
    private String documents;//证件上传

    /*工作经历*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date workTime;//工作开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date overTime;//工作结束时间
    private String company;//公司
    private String dapart;//部门
    private String position;//职位
    private String note;//备注

    private String cardImg;//证件图片
    @NotDatabaseField
    List<String> employeesIds;

    @NotDatabaseField
    private List<String> company_id;

    @NotDatabaseField
    private List<String> parkIds;

    @NotDatabaseField
    private String account;

    @NotDatabaseField
    private String userName;

    @NotDatabaseField
    private String parkId;
    
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date updatedAt;

	public String createdBy;

	public String updatedBy;

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


	

    
    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public List<String> getCompany_id() {
        return company_id;
    }

    public void setCompany_id(List<String> company_id) {
        this.company_id = company_id;
    }

    public List<String> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<String> employeesIds) {
        this.employeesIds = employeesIds;
    }

    public Integer getEmployeesStatus() {
        return employeesStatus;
    }

    public void setEmployeesStatus(Integer employeesStatus) {
        this.employeesStatus = employeesStatus;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(String employeesId) {
        this.employeesId = employeesId;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    public Integer getEmployeesSex() {
        return employeesSex;
    }

    public void setEmployeesSex(Integer employeesSex) {
        this.employeesSex = employeesSex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDapart() {
        return dapart;
    }

    public void setDapart(String dapart) {
        this.dapart = dapart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}




	public enum employeesSexType {
		man(1, "男"),
		woman(2, "女");
		public final int value;
		public final String desc;
		employeesSexType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public static employeesSexType valueOf(int value) {
			switch (value) {
			case 1:
				return man;
			case 2:
				return woman;
			default:
				return null;
			}
		}
	}

}
