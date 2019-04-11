package com.etop.website.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Parkservice extends ServiceBusiness{

    private String service_id;
    
    private String service_no;

    private String club_id;
    
    private String service_type;
    
    private String service_status;
    
    private String company_id;
    
    private String company_name;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date apply_time;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date complete_time;
    
    private String building_no;
    
    private String zone_no;
    
    private String storey;
    
    private String room_no;
    
    private String room_id;

    private String applicant;
    
    private String applicant_phone;

    private String description;
      
    private String isfree;

    private String park_code;
    
    private String service_code;
    
    private String service_name;
    
    private String applicant_department;
    
    private String applicant_position;

    private String changes;
    
	private BigDecimal total_price;//申请预计的总价
	private BigDecimal final_price;//最终核实总价

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_no() {
		return service_no;
	}

	public void setService_no(String service_no) {
		this.service_no = service_no;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getClub_id() {
		return club_id;
	}

	public void setClub_id(String club_id) {
		this.club_id = club_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplicant_phone() {
		return applicant_phone;
	}

	public void setApplicant_phone(String applicant_phone) {
		this.applicant_phone = applicant_phone;
	}



	public String getIsfree() {
		return isfree;
	}

	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}
    
   
	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getPark_code() {
		return park_code;
	}

	public void setPark_code(String park_code) {
		this.park_code = park_code;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getApplicant_department() {
		return applicant_department;
	}

	public void setApplicant_department(String applicant_department) {
		this.applicant_department = applicant_department;
	}

	public String getApplicant_position() {
		return applicant_position;
	}

	public void setApplicant_position(String applicant_position) {
		this.applicant_position = applicant_position;
	}

	public String getBuilding_no() {
		return building_no;
	}

	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}

	public String getZone_no() {
		return zone_no;
	}

	public void setZone_no(String zone_no) {
		this.zone_no = zone_no;
	}

	public String getStorey() {
		return storey;
	}

	public void setStorey(String storey) {
		this.storey = storey;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public Date getComplete_time() {
		return complete_time;
	}

	public void setComplete_time(Date complete_time) {
		this.complete_time = complete_time;
	}

	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public BigDecimal getFinal_price() {
		return final_price;
	}

	public void setFinal_price(BigDecimal final_price) {
		this.final_price = final_price;
	}
	

}
