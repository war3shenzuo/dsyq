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
 * @DATE 2016/10/12
 */
public class ContractReport implements Serializable{

    private static final long serialVersionUID = 6270734608835604926L;

    private String id;
    private String parkId;
    private String parkName;
    private String building;//楼号
    private String floor;//楼层
    private String block;//分区
    private Double leaseAmount;//租赁合同金额
    private Double serviceAmount;// 服务合同金额
    private Double energyAmount;//  能源合同金额
    private Double propertyAmount;// 物业合同金额
    private Double parkServiceAmount;//园区服务金额
    private Double otherAmount;//其他
    private Double overdueFine;// 滞纳金
    private String recoveryRate;// 回收率
    private Double amount;//金额
    private Double amountPaid;// 已付金额
    private Double arrearsAmount;//欠款统计
    private Date createdTime;// 出账日期
    private Integer billSource; //账单来源
    private Double accountRemission; //本金减免
    private Double overdueRemission; //滞纳金减免

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date start;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date end;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    private List<String> parkIds;

    private String refFloorId;//楼
    private String refStoreyId;//层
    private String refAreaId;//区

    public Double getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(Double arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
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

    public Double getParkServiceAmount() {
        return parkServiceAmount;
    }

    public void setParkServiceAmount(Double parkServiceAmount) {
        this.parkServiceAmount = parkServiceAmount;
    }

    public Double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(Double otherAmount) {
        this.otherAmount = otherAmount;
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

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public Integer getBillSource() {
        return billSource;
    }

    public void setBillSource(Integer billSource) {
        this.billSource = billSource;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Double getLeaseAmount() {
        return leaseAmount;
    }

    public void setLeaseAmount(Double leaseAmount) {
        this.leaseAmount = leaseAmount;
    }

    public Double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Double getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        this.energyAmount = energyAmount;
    }

    public Double getPropertyAmount() {
        return propertyAmount;
    }

    public void setPropertyAmount(Double propertyAmount) {
        this.propertyAmount = propertyAmount;
    }

    public Double getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(Double overdueFine) {
        this.overdueFine = overdueFine;
    }

    public void setRecoveryRate(String recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public String getRecoveryRate() {
        return recoveryRate;
    }

	public Double getAccountRemission() {
		return accountRemission;
	}

	public void setAccountRemission(Double accountRemission) {
		this.accountRemission = accountRemission;
	}

	public Double getOverdueRemission() {
		return overdueRemission;
	}

	public void setOverdueRemission(Double overdueRemission) {
		this.overdueRemission = overdueRemission;
	}
}
