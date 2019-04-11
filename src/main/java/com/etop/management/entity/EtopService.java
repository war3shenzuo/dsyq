package com.etop.management.entity;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.bean.NotDatabaseField;
import com.etop.management.bean.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceEntity<br>
 */
public class EtopService extends PageParam{
	
	/*服务类型：
	1.快递服务
	2.物业保修
	3.人员代招
	4.执照办理
	5.商标注册
	6.代理会计
	7.法务咨询
	8.装修申请
	9.会议室预约
	10.摄影棚预约*/	private java.lang.String serviceId;//   id	private java.lang.String serviceNo;//   服务编号	private java.lang.String clubId;//   园区id	private java.lang.String serviceType;//   服务类型：	private String serviceTypeName;	private java.lang.Integer serviceStatus;//   处理状态：101，待审批；102，待回执；201，已撤销；202，已审批；203，已派工；204，已完工；300，完结	private java.lang.String companyId;//   申请公司id	private java.lang.String companyName;//   申请公司名称
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	private java.util.Date applyTime;//   申请时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date applyTime2;//   申请时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	private java.util.Date completeTime;//   完成时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")	private java.util.Date completeTime2;//   完成时间	private java.lang.String buildingNo;//   楼号	private java.lang.String zoneNo;//   分区号	private java.lang.String storey;//   楼层	private java.lang.String roomNo;//   房间号	private java.lang.String applicant;//   申请人	private java.lang.String applicantPhone;//   申请人联系方式	private java.lang.String applicantDepartment;//   部门	private java.lang.String applicantPosition;//   职位	private java.lang.Integer isfree;//   是否免费
	private java.lang.String description;//   服务描述
	private java.lang.String changes;//   服务描述
	
	private long currentTime;//当前时间戳
	
	private String parkId;//园区Id
	
	private Long beginTime;//预约开始时间
	private String beginStr;
	
	private Long endTime;//预约结束时间
	private String endStr;
	
	private String facilityName;//设施名称
	private String facilityType;//类别
	private String expirationTime;//是否过期 1 ，0
	private String facilityPrice;
	
	private String facilityId;
	
	private java.lang.String goodName;//   
	private java.lang.String unit;//   
	private BigDecimal totalPrice;//   
	private BigDecimal totalPrices;//   
	private java.lang.String category;
	private java.lang.Integer number;//   数量
	private java.lang.String subject;
	private java.lang.String roomId;//   房间号
	private BigDecimal finalPrice;//最终核实总价
	
	public java.lang.String getRoomId() {
		return roomId;
	}
	public void setRoomId(java.lang.String roomId) {
		this.roomId = roomId;
	}
	public java.lang.String getGoodName() {
		return goodName;
	}
	public void setGoodName(java.lang.String goodName) {
		this.goodName = goodName;
	}
	public java.lang.String getUnit() {
		return unit;
	}
	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.String getCategory() {
		return category;
	}
	public void setCategory(java.lang.String category) {
		this.category = category;
	}
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getBeginStr() {
		return beginStr;
	}
	public void setBeginStr(String beginStr) {
		this.beginStr = beginStr;
	}
	public String getEndStr() {
		return endStr;
	}
	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	public long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(beginTime));
		this.beginTime = beginTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(endTime));
		this.endTime = endTime;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	@NotDatabaseField	private Integer serviceNum;	@NotDatabaseField	private Integer serviceCompleteNum;	public Integer getServiceNum() {		return serviceNum;	}	public Integer getServiceCompleteNum() {		return serviceCompleteNum;	}	public void setServiceCompleteNum(Integer serviceCompleteNum) {		this.serviceCompleteNum = serviceCompleteNum;	}	public void setServiceNum(Integer serviceNum) {		this.serviceNum = serviceNum;	}	public String getServiceTypeName() {		return serviceTypeName;	}	public void setServiceTypeName(String serviceTypeName) {		this.serviceTypeName = serviceTypeName;	}
	public java.lang.String getServiceId() {	    return this.serviceId;	}	public void setServiceId(java.lang.String serviceId) {	    this.serviceId=serviceId;	}	public java.lang.String getServiceNo() {	    return this.serviceNo;	}	public void setServiceNo(java.lang.String serviceNo) {	    this.serviceNo=serviceNo;	}	public java.lang.String getClubId() {	    return this.clubId;	}	public void setClubId(java.lang.String clubId) {	    this.clubId=clubId;	}	public java.lang.String getServiceType() {	    return this.serviceType;	}	public void setServiceType(java.lang.String serviceType) {	    this.serviceType=serviceType;	}	public java.lang.Integer getServiceStatus() {	    return this.serviceStatus;	}	public void setServiceStatus(java.lang.Integer serviceStatus) {	    this.serviceStatus=serviceStatus;	}	public java.lang.String getCompanyId() {	    return this.companyId;	}	public void setCompanyId(java.lang.String companyId) {	    this.companyId=companyId;	}	public java.lang.String getCompanyName() {	    return this.companyName;	}	public void setCompanyName(java.lang.String companyName) {	    this.companyName=companyName;	}	public java.util.Date getApplyTime() {	    return this.applyTime;	}	public void setApplyTime(java.util.Date applyTime) {	    this.applyTime=applyTime;	}	public java.util.Date getCompleteTime() {	    return this.completeTime;	}	public void setCompleteTime(java.util.Date completeTime) {	    this.completeTime=completeTime;	}	public java.lang.String getBuildingNo() {	    return this.buildingNo;	}	public void setBuildingNo(java.lang.String buildingNo) {	    this.buildingNo=buildingNo;	}	public java.lang.String getZoneNo() {	    return this.zoneNo;	}	public void setZoneNo(java.lang.String zoneNo) {	    this.zoneNo=zoneNo;	}	public java.lang.String getStorey() {	    return this.storey;	}	public void setStorey(java.lang.String storey) {	    this.storey=storey;	}	public java.lang.String getRoomNo() {	    return this.roomNo;	}	public void setRoomNo(java.lang.String roomNo) {	    this.roomNo=roomNo;	}	public java.lang.String getApplicant() {	    return this.applicant;	}	public void setApplicant(java.lang.String applicant) {	    this.applicant=applicant;	}	public java.lang.String getApplicantPhone() {	    return this.applicantPhone;	}	public void setApplicantPhone(java.lang.String applicantPhone) {	    this.applicantPhone=applicantPhone;	}	public java.lang.String getApplicantDepartment() {	    return this.applicantDepartment;	}	public void setApplicantDepartment(java.lang.String applicantDepartment) {	    this.applicantDepartment=applicantDepartment;	}	public java.lang.String getApplicantPosition() {	    return this.applicantPosition;	}	public void setApplicantPosition(java.lang.String applicantPosition) {	    this.applicantPosition=applicantPosition;	}	public java.lang.Integer getIsfree() {	    return this.isfree;	}	public void setIsfree(java.lang.Integer isfree) {	    this.isfree=isfree;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}
	public java.lang.String getChanges() {
		return changes;
	}
	public void setChanges(java.lang.String changes) {
		this.changes = changes;
	}
	public java.lang.Integer getNumber() {
		return number;
	}
	public void setNumber(java.lang.Integer number) {
		this.number = number;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public java.lang.String getSubject() {
		return subject;
	}
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getFacilityPrice() {
		return facilityPrice;
	}
	public void setFacilityPrice(String facilityPrice) {
		this.facilityPrice = facilityPrice;
	}
	public BigDecimal getTotalPrices() {
		return totalPrices;
	}
	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}
	public java.util.Date getApplyTime2() {
		return applyTime2;
	}
	public void setApplyTime2(java.util.Date applyTime2) {
		this.applyTime2 = applyTime2;
	}
	public java.util.Date getCompleteTime2() {
		return completeTime2;
	}
	public void setCompleteTime2(java.util.Date completeTime2) {
		this.completeTime2 = completeTime2;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
}