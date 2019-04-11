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
 * @DATE 2016/12/8
 */
public class ServiceReport implements Serializable{
    private static final long serialVersionUID = 2715433637381016210L;

    private String id;
    private String parkId;
    private String parkName;
    private String serviceype;//服务类型
    private Integer serviceStatus;
    private String building;//楼号
    private String storey;//楼层
    private String zoneNo;//分区
    private Double statusNum;//各个状态服务数量
    private Double applicationsNum;//申请数量
    private Double receiptNum;//待回执
    private Double revocationNum;//已撤销
    private Double approvalNum;//已审批
    private Double workersNum;//已派工
    private Double completedNum;//已完工
    private Double confirmCompletedNum;//确认完工
    private Double refuseNum;//拒绝
    private String approvalProportion;//审批比例
    private String workersProportion;//派工比例
    private String completedProportion;//完工比例
    private Double totalApplications;//申请总数

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date start;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date end;

    private List<String> parkIds;

    public Double getRefuseNum() {
        return refuseNum;
    }

    public void setRefuseNum(Double refuseNum) {
        this.refuseNum = refuseNum;
    }

    public Double getConfirmCompletedNum() {
        return confirmCompletedNum;
    }

    public void setConfirmCompletedNum(Double confirmCompletedNum) {
        this.confirmCompletedNum = confirmCompletedNum;
    }

    public Integer getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Integer serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceype() {
        return serviceype;
    }

    public void setServiceype(String serviceype) {
        this.serviceype = serviceype;
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

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getZoneNo() {
        return zoneNo;
    }

    public void setZoneNo(String zoneNo) {
        this.zoneNo = zoneNo;
    }

    public Double getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Double statusNum) {
        this.statusNum = statusNum;
    }

    public Double getApplicationsNum() {
        return applicationsNum;
    }

    public void setApplicationsNum(Double applicationsNum) {
        this.applicationsNum = applicationsNum;
    }

    public Double getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(Double receiptNum) {
        this.receiptNum = receiptNum;
    }

    public Double getRevocationNum() {
        return revocationNum;
    }

    public void setRevocationNum(Double revocationNum) {
        this.revocationNum = revocationNum;
    }

    public Double getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(Double approvalNum) {
        this.approvalNum = approvalNum;
    }

    public Double getWorkersNum() {
        return workersNum;
    }

    public void setWorkersNum(Double workersNum) {
        this.workersNum = workersNum;
    }

    public Double getCompletedNum() {
        return completedNum;
    }

    public void setCompletedNum(Double completedNum) {
        this.completedNum = completedNum;
    }

    public Double getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Double totalApplications) {
        this.totalApplications = totalApplications;
    }

    public String getApprovalProportion() {
        return approvalProportion;
    }

    public void setApprovalProportion(String approvalProportion) {
        this.approvalProportion = approvalProportion;
    }

    public String getWorkersProportion() {
        return workersProportion;
    }

    public void setWorkersProportion(String workersProportion) {
        this.workersProportion = workersProportion;
    }

    public String getCompletedProportion() {
        return completedProportion;
    }

    public void setCompletedProportion(String completedProportion) {
        this.completedProportion = completedProportion;
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

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }
}
