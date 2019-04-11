package com.etop.management.entity;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceFacilityEntity<br>
 */
public class EtopServiceFacility extends EtopFacility{
	
		private java.lang.String id;//   	private java.lang.String serviceId;//   	private java.lang.String facilityId;//   
	private String beginTimestamp;
	private String endTimestamp;
	private java.lang.Long appointmentBeginTime;//   	private java.lang.Long appointmentEndTime;// 
	private BigDecimal totalPrices;// 
	private java.lang.Long duration;// 
	
	private Integer num;
	
	private BigDecimal money;
	
	
	
		public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getBeginTimestamp() {
		return beginTimestamp;
	}
	public void setBeginTimestamp(String beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
		try {
			this.appointmentBeginTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTimestamp).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
		try {
			this.appointmentEndTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimestamp).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public java.lang.String getId() {	    return this.id;	}	public void setId(java.lang.String id) {	    this.id=id;	}	public java.lang.String getServiceId() {	    return this.serviceId;	}	public void setServiceId(java.lang.String serviceId) {	    this.serviceId=serviceId;	}	public java.lang.String getFacilityId() {	    return this.facilityId;	}	public void setFacilityId(java.lang.String facilityId) {	    this.facilityId=facilityId;	}	public java.lang.Long getAppointmentBeginTime() {	    return this.appointmentBeginTime;	}	public void setAppointmentBeginTime(java.lang.Long appointmentBeginTime) {	    this.appointmentBeginTime=appointmentBeginTime;	}	public java.lang.Long getAppointmentEndTime() {	    return this.appointmentEndTime;	}	public void setAppointmentEndTime(java.lang.Long appointmentEndTime) {	    this.appointmentEndTime=appointmentEndTime;	}

	public java.lang.Long getDuration() {
		return duration;
	}
	public void setDuration(java.lang.Long duration) {
		this.duration = duration;
	}
	public BigDecimal getTotalPrices() {
		return totalPrices;
	}
	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}

}

