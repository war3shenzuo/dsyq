package com.etop.management.entity;
import java.util.Date;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.NotDatabaseField;
import com.etop.management.bean.PageParam;
/**
 * 
 * <br>
 * <b>功能：</b>EtopFloorRoomEntity<br>
 */
public class EtopFloorRoom extends PageParam{
	
	
	private java.lang.String id;//   
	private java.lang.String roomNum;//   房间号
	private java.lang.String orientation;//   朝向
	private Double buildArea;//   建筑面积
	private Double userArea;//   使用面积
	private java.lang.String layerHigh;//   层高
	private java.lang.String located;//   坐落
	private java.lang.String renovation;//   装修
	private java.lang.String monthPrice;//   月单价
	private java.lang.String dayPrice;//   日单价
	private java.lang.String floorStatus;//   楼状态 :0待租（无人） 1已出租  2预留中 3 待租（有人）
	private java.lang.String remark;//   备注
	private java.lang.Integer contractNum;//   关联的合同协议
	private java.lang.String roomImg;//   房间平面图
	private java.lang.String roomImg2;//   房间平面图
	private java.lang.String companyId;//   入住用户
	private java.lang.String constractId;//   关联相关合同
	private java.lang.String refFloorId;//   楼Id
	private java.lang.String refStoreyId;//   层Id
	private java.lang.String refAreaId;//   区id
	private String floorName;
	private String storeyName;
	private String areaName;
	private String buildArea1;
	private String buildArea2;
	private String layerHigh1;
	private String layerHigh2;
	private String dayPrice1;
	private String dayPrice2;
	private String monthPrice1;
	private String monthPrice2;
	private String activated;
	private Date createdAt;
	private java.lang.String roomId;//   房间号
	private java.lang.String showOut;//   对外公开与否

	@NotDatabaseField
	private Integer roomsNum;//房间数量

	public Integer getRoomsNum() {
		return roomsNum;
	}

	public void setRoomsNum(Integer roomsNum) {
		this.roomsNum = roomsNum;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public java.lang.String getRoomId() {
		return roomId;
	}
	public void setRoomId(java.lang.String roomId) {
		this.roomId = roomId;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getLayerHigh1() {
		return layerHigh1;
	}
	public void setLayerHigh1(String layerHigh1) {
		this.layerHigh1 = layerHigh1;
	}
	public String getLayerHigh2() {
		return layerHigh2;
	}
	public void setLayerHigh2(String layerHigh2) {
		this.layerHigh2 = layerHigh2;
	}
	public String getDayPrice1() {
		return dayPrice1;
	}
	public void setDayPrice1(String dayPrice1) {
		this.dayPrice1 = dayPrice1;
	}
	public String getDayPrice2() {
		return dayPrice2;
	}
	public void setDayPrice2(String dayPrice2) {
		this.dayPrice2 = dayPrice2;
	}
	public String getMonthPrice1() {
		return monthPrice1;
	}
	public void setMonthPrice1(String monthPrice1) {
		this.monthPrice1 = monthPrice1;
	}
	public String getMonthPrice2() {
		return monthPrice2;
	}
	public void setMonthPrice2(String monthPrice2) {
		this.monthPrice2 = monthPrice2;
	}

	private String parkId;
	
	public String getBuildArea1() {
		return buildArea1;
	}
	public void setBuildArea1(String buildArea1) {
		this.buildArea1 = buildArea1;
	}
	public String getBuildArea2() {
		return buildArea2;
	}
	public void setBuildArea2(String buildArea2) {
		this.buildArea2 = buildArea2;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getStoreyName() {
		return storeyName;
	}
	public void setStoreyName(String storeyName) {
		this.storeyName = storeyName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	private String companyName;
	
	
	public java.lang.String getRoomImg2() {
		return roomImg2;
	}
	public void setRoomImg2(java.lang.String roomImg2) {
		this.roomImg2 = roomImg2;
	}

	private Criteria criteria;
	
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
	public java.lang.String getRoomNum() {
	    return this.roomNum;
	}
	public void setRoomNum(java.lang.String roomNum) {
	    this.roomNum=roomNum;
	}
	public java.lang.String getOrientation() {
	    return this.orientation;
	}
	public void setOrientation(java.lang.String orientation) {
	    this.orientation=orientation;
	}

	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public Double getUserArea() {
		return userArea;
	}
	public void setUserArea(Double userArea) {
		this.userArea = userArea;
	}
	public java.lang.String getLayerHigh() {
	    return this.layerHigh;
	}
	public void setLayerHigh(java.lang.String layerHigh) {
	    this.layerHigh=layerHigh;
	}
	public java.lang.String getLocated() {
	    return this.located;
	}
	public void setLocated(java.lang.String located) {
	    this.located=located;
	}
	public java.lang.String getRenovation() {
	    return this.renovation;
	}
	public void setRenovation(java.lang.String renovation) {
	    this.renovation=renovation;
	}
	public java.lang.String getMonthPrice() {
	    return this.monthPrice;
	}
	public void setMonthPrice(java.lang.String monthPrice) {
	    this.monthPrice=monthPrice;
	}
	public java.lang.String getDayPrice() {
	    return this.dayPrice;
	}
	public void setDayPrice(java.lang.String dayPrice) {
	    this.dayPrice=dayPrice;
	}
	public java.lang.String getFloorStatus() {
	    return this.floorStatus;
	}
	public void setFloorStatus(java.lang.String floorStatus) {
	    this.floorStatus=floorStatus;
	}
	public java.lang.String getRemark() {
	    return this.remark;
	}
	public void setRemark(java.lang.String remark) {
	    this.remark=remark;
	}
	public java.lang.Integer getContractNum() {
	    return this.contractNum;
	}
	public void setContractNum(java.lang.Integer contractNum) {
	    this.contractNum=contractNum;
	}
	public java.lang.String getRoomImg() {
	    return this.roomImg;
	}
	public void setRoomImg(java.lang.String roomImg) {
	    this.roomImg=roomImg;
	}
	public java.lang.String getCompanyId() {
	    return this.companyId;
	}
	public void setCompanyId(java.lang.String companyId) {
	    this.companyId=companyId;
	}
	public java.lang.String getConstractId() {
	    return this.constractId;
	}
	public void setConstractId(java.lang.String constractId) {
	    this.constractId=constractId;
	}
	public java.lang.String getRefFloorId() {
	    return this.refFloorId;
	}
	public void setRefFloorId(java.lang.String refFloorId) {
	    this.refFloorId=refFloorId;
	}
	public java.lang.String getRefStoreyId() {
	    return this.refStoreyId;
	}
	public void setRefStoreyId(java.lang.String refStoreyId) {
	    this.refStoreyId=refStoreyId;
	}
	public java.lang.String getRefAreaId() {
	    return this.refAreaId;
	}
	public void setRefAreaId(java.lang.String refAreaId) {
	    this.refAreaId=refAreaId;
	}
	
	public Criteria getCriteria(){
		Criteria c=new Criteria();
		if(this.roomNum!=null){
			c.put("roomNum", this.roomNum);
		}
		if(this.activated!=null){
			c.put("activated", this.activated);
		}
		if(this.floorStatus!=null && this.floorStatus.length()>0){
			c.put("floorStatus", this.floorStatus);
		}
		if(this.refAreaId!=null && this.refAreaId.length()>0){
			c.put("refAreaId", this.refAreaId);
		}
		if(this.refStoreyId!=null && this.refStoreyId.length()>0){
			c.put("refStoreyId", this.refStoreyId);
		}
		if(this.refFloorId!=null && this.refFloorId.length()>0){
			c.put("refFloorId", this.refFloorId);
		}
		if(this.parkId!=null && this.parkId.length()>0){
			c.put("parkId", this.parkId);
		}
		if(this.orientation!=null && this.orientation.length()>0){
			c.put("orientation", this.orientation);
		}
		if(this.buildArea1!=null && this.buildArea1.length()>0){
			c.put("buildArea1",  this.buildArea1);
		}
		if(this.buildArea2!=null && this.buildArea2.length()>0){
			c.put("buildArea2", this.buildArea2);
		}
		if(this.layerHigh1!=null && this.layerHigh1.length()>0){
			c.put("layerHigh1", this.layerHigh1);
		}
		if(this.layerHigh2!=null && this.layerHigh2.length()>0){
			c.put("layerHigh2", this.layerHigh2);
		}
		if(this.dayPrice1!=null && this.dayPrice1.length()>0){
			c.put("dayPrice1",  this.dayPrice1);
		}
		if(this.dayPrice2!=null && this.dayPrice2.length()>0){
			c.put("dayPrice2",  this.dayPrice2);
		}
		if(this.monthPrice1!=null && this.monthPrice1.length()>0){
			c.put("monthPrice1", this.monthPrice1);
		}
		if(this.monthPrice2!=null && this.monthPrice2.length()>0){
			c.put("monthPrice2", this.monthPrice2);
		}
		return c;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public java.lang.String getShowOut() {
		return showOut;
	}

	public void setShowOut(java.lang.String showOut) {
		this.showOut = showOut;
	}

}

