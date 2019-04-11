package com.etop.website.bean;

public class FloorStorey {

    private String id;//主键
    
    private String buildName; //楼 层 区域的名称
    
    private String buildType; //类型
    
    private String parentId;//父节点
    
    private String remark;//备注
    
    private String status;//状态
    
    private String buildImg;//图片
    
    private String parkId;//园区ID

    private String refFloorId;

    private String refStoreyId;
    
    private String refAreaId;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuildImg() {
		return buildImg;
	}

	public void setBuildImg(String buildImg) {
		this.buildImg = buildImg;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getRefFloorId() {
		return refFloorId;
	}

	public void setRefFloorId(String refFloorId) {
		this.refFloorId = refFloorId;
	}

	public String getRefStoreyId() {
		return refStoreyId;
	}

	public void setRefStoreyId(String refStoreyId) {
		this.refStoreyId = refStoreyId;
	}

	public String getRefAreaId() {
		return refAreaId;
	}

	public void setRefAreaId(String refAreaId) {
		this.refAreaId = refAreaId;
	}



}
