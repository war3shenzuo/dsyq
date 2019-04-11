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
 * @DATE 2016/11/28
 */
public class BalanceStatistics implements Serializable{

    private static final long serialVersionUID = -581169237099994368L;

    private String id;
    private String parkId;//园区id
    private String parkName;//园区名称
    private String building;//楼号
    private String floor;//楼层
    private String block;//分区
    private String room;//房号
    private String billNoOut;//账单编号
    private String companyId;//客户id
    private String companyName;//客户名称
    private Integer billSource;//账单来源
    private Double leaseAmount;//租赁合同金额
    private Double serviceAmount;// 服务合同金额
    private Double energyAmount;//  能源合同金额
    private Double propertyAmount;// 物业合同金额
    private Double parkServiceAmount;//园区服务金额
    private Double otherAmount;//其他
    private Double amount;// 账单金额
    private Double amountPaid;// 实际付款
    private Double arrears;// 欠款
    private Double overdueFine;// 滞纳金

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date createdTime ;//出账日期

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline ;//缴费日期

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

    private Integer isAllPark;

    public Integer getIsAllPark() {
        return isAllPark;
    }

    public void setIsAllPark(Integer isAllPark) {
        this.isAllPark = isAllPark;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Double getLeaseAmount() {
        return leaseAmount;
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

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBillNoOut() {
        return billNoOut;
    }

    public void setBillNoOut(String billNoOut) {
        this.billNoOut = billNoOut;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getBillSource() {
        return billSource;
    }

    public void setBillSource(Integer billSource) {
        this.billSource = billSource;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Double getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(Double overdueFine) {
        this.overdueFine = overdueFine;
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
}
