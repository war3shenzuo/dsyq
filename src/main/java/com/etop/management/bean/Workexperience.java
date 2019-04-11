package com.etop.management.bean;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by lvxiwei.
 *
 * @author lvxiwei
 * @DATE 2016/10/17
 */
public class Workexperience implements Serializable{

    private static final long serialVersionUID = -3461493499016074072L;

   /* 员工基础信息*/
    private String id;
    private String employeesId;//员工id
    private String workName;//工作名称
    private String depart;//部门
    private String positions;//职位

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date workStart;//入职时间

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date workEnd;//离职时间

    
    
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getEmployeesId() {
		return employeesId;
	}



	public void setEmployeesId(String employeesId) {
		this.employeesId = employeesId;
	}



	public String getWorkName() {
		return workName;
	}



	public void setWorkName(String workName) {
		this.workName = workName;
	}



	public Date getWorkStart() {
		return workStart;
	}



	public void setWorkStart(Date workStart) {
		this.workStart = workStart;
	}



	public Date getWorkEnd() {
		return workEnd;
	}



	public void setWorkEnd(Date workEnd) {
		this.workEnd = workEnd;
	}



	public String getDepart() {
		return depart;
	}



	public void setDepart(String depart) {
		this.depart = depart;
	}



	public String getPositions() {
		return positions;
	}



	public void setPositions(String positions) {
		this.positions = positions;
	}





}
