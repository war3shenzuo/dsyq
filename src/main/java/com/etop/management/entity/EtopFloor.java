package com.etop.management.entity;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <br>
 * <b>功能：</b>EtopFloorEntity<br>
 */
public class EtopFloor{
	
	
	private java.lang.String id;//   主键
	private java.lang.String buildName;//   楼 层 区域的名称
	private java.lang.String buildType;//   类型
	private java.lang.String parentId;//   父节点
	private java.lang.String remark;//   备注
	private java.lang.String status;//   状态
	private java.lang.String buildImg;//   图片
	private java.lang.String parkId;//   
	private String buildArea;//建筑面积
	private Integer sourceCharge;//能源公摊方式
	private Date createdAt;//创建时间
	
	private Integer energyEnterType;//能源录入类型0月1周
	
	private Integer energyEnterDay;//能源录入日，周（1－7，一至日）
	
	private Integer energyPaymentDay;//能源缴费天数
	
	private String energyBillDate;//能源出帐日，如3-1 

//	private Date energyPowerFeeDate;
//	private Date energyWaterFeeDate;
//	private Date energyGasFeeDate;
//	private Date energyAcFeeDate;
	private Date energyLastBillDate;//本楼最后出帐日，初始为创建日期
	
	private Date energyLastFeeDate;//本楼最后结算日，第一次录入楼能源时，会被要求补录
	
	private String dian;
	private String shui;
	private String ranqi;
	private String kongtiao;
	
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	private List<Map<String,Object>> ny;//所有能源
	
	
	public Integer getEnergyEnterType() {
		return energyEnterType;
	}
	public void setEnergyEnterType(Integer energyEnterType) {
		this.energyEnterType = energyEnterType;
	}
	public Integer getEnergyEnterDay() {
		return energyEnterDay;
	}
	public void setEnergyEnterDay(Integer energyEnterDay) {
		this.energyEnterDay = energyEnterDay;
	}
	public Integer getEnergyPaymentDay() {
		return energyPaymentDay;
	}
	public void setEnergyPaymentDay(Integer energyPaymentDay) {
		this.energyPaymentDay = energyPaymentDay;
	}
	public String getEnergyBillDate() {
		return energyBillDate;
	}
	public void setEnergyBillDate(String energyBillDate) {
		this.energyBillDate = energyBillDate;
	}
//	public Date getEnergyPowerFeeDate() {
//		return energyPowerFeeDate;
//	}
//	public void setEnergyPowerFeeDate(Date energyPowerFeeDate) {
//		this.energyPowerFeeDate = energyPowerFeeDate;
//	}
//	public Date getEnergyWaterFeeDate() {
//		return energyWaterFeeDate;
//	}
//	public void setEnergyWaterFeeDate(Date energyWaterFeeDate) {
//		this.energyWaterFeeDate = energyWaterFeeDate;
//	}
//	public Date getEnergyGasFeeDate() {
//		return energyGasFeeDate;
//	}
//	public void setEnergyGasFeeDate(Date energyGasFeeDate) {
//		this.energyGasFeeDate = energyGasFeeDate;
//	}
//	public Date getEnergyAcFeeDate() {
//		return energyAcFeeDate;
//	}
//	public void setEnergyAcFeeDate(Date energyAcFeeDate) {
//		this.energyAcFeeDate = energyAcFeeDate;
//	}
	public Integer getSourceCharge() {
		return sourceCharge;
	}
	public void setSourceCharge(Integer sourceCharge) {
		this.sourceCharge = sourceCharge;
	}
	public String getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	private String parkIds;
	
	
	
	public String getParkIds() {
		return parkIds;
	}
	public void setParkIds(String parkIds) {
		this.parkIds = parkIds;
	}
	public java.lang.String getId() {
	    return this.id;
	}
	public void setId(java.lang.String id) {
	    this.id=id;
	}
	public java.lang.String getBuildName() {
	    return this.buildName;
	}
	public void setBuildName(java.lang.String buildName) {
	    this.buildName=buildName;
	}
	public java.lang.String getBuildType() {
	    return this.buildType;
	}
	public void setBuildType(java.lang.String buildType) {
	    this.buildType=buildType;
	}
	public java.lang.String getParentId() {
	    return this.parentId;
	}
	public void setParentId(java.lang.String parentId) {
	    this.parentId=parentId;
	}
	public java.lang.String getRemark() {
	    return this.remark;
	}
	public void setRemark(java.lang.String remark) {
	    this.remark=remark;
	}
	public java.lang.String getStatus() {
	    return this.status;
	}
	public void setStatus(java.lang.String status) {
	    this.status=status;
	}
	public java.lang.String getBuildImg() {
	    return this.buildImg;
	}
	public void setBuildImg(java.lang.String buildImg) {
	    this.buildImg=buildImg;
	}
	public java.lang.String getParkId() {
	    return this.parkId;
	}
	public void setParkId(java.lang.String parkId) {
	    this.parkId=parkId;
	}
	public Date getEnergyLastBillDate() {
		return energyLastBillDate;
	}
	public void setEnergyLastBillDate(Date energyLastBillDate) {
		this.energyLastBillDate = energyLastBillDate;
	}
	
//	public Date getEnergyFeeDate(int energyType)
//	{	
//		switch(energyType)
//		{
//		case 0:
//			return this.energyPowerFeeDate;
//		case 1:
//			return this.energyWaterFeeDate;
//		case 2:
//			return this.energyGasFeeDate;
//		case 3:
//			return this.energyAcFeeDate;
//		default:
//			return null;
//		}
//	}
	public String getDian() {
		return dian;
	}
	public void setDian(String dian) {
		this.dian = dian;
	}
	public String getShui() {
		return shui;
	}
	public void setShui(String shui) {
		this.shui = shui;
	}
	public String getRanqi() {
		return ranqi;
	}
	public void setRanqi(String ranqi) {
		this.ranqi = ranqi;
	}
	public String getKongtiao() {
		return kongtiao;
	}
	public void setKongtiao(String kongtiao) {
		this.kongtiao = kongtiao;
	}
	public List<Map<String, Object>> getNy() {
		return ny;
	}
	public void setNy(List<Map<String, Object>> ny) {
		this.ny = ny;
	}
	public Date getEnergyLastFeeDate() {
		return energyLastFeeDate;
	}
	public void setEnergyLastFeeDate(Date energyLastFeeDate) {
		this.energyLastFeeDate = energyLastFeeDate;
	}
	
	
}

