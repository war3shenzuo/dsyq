package com.etop.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewContractModel extends TrackableModel {

    public  final static int DTJ= 0;//待提交
    public final static int YTJ= 1;//已提交  园长审批
    public final static int YZSHTG = 2;//园长审核通过  财务审批
    public final static int CWSHTG = 3;//财务审核通过 正常
    public final static int ZZ = 4;//终止
    public final static int YZWTG = 5;//园长未通过
    public final static int CWWTG = 6;//财务未通过

    public String contractNo;

    public String paperNo;

    public String refCompanyId;

    public String companyName;

    public String companyContactsName;

    public String companyContactsPhone;

    public String companyRegistrationDate;

    public String refRoomId;

    public String refParkId;

    public String parkCode;

    public String parkName;

    public String park;

    public String building;

    public String floor;

    public String block;

    public String room;

    public String contractNotes;//合同备注

    public Integer contractCategory;

    public Integer contractType;

    public Integer contractStatus;

    public BigDecimal deposit;

    public Double builarea; //建筑面积


    public String contractStatusStr;

    public String terminateReason;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date terminateDate;

    public Date getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date contractStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date contractEndDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date contractSignDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date jfDate;//交房日期


    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo;
    }

    public String getRefCompanyId() {
        return refCompanyId;
    }

    public void setRefCompanyId(String refCompanyId) {
        this.refCompanyId = refCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyRegistrationDate() {
        return companyRegistrationDate;
    }

    public void setCompanyRegistrationDate(String companyRegistrationDate) {
        this.companyRegistrationDate = companyRegistrationDate;
    }

    public String getRefRoomId() {
        return refRoomId;
    }

    public void setRefRoomId(String refRoomId) {
        this.refRoomId = refRoomId;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
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



    public String getTerminateReason() {
        return terminateReason;
    }

    public void setTerminateReason(String terminateReason) {
        this.terminateReason = terminateReason;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Date getContractSignDate() {
        return contractSignDate;
    }

    public void setContractSignDate(Date contractSignDate) {
        this.contractSignDate = contractSignDate;
    }


    public String getCompanyContactsName() {
        return companyContactsName;
    }

    public void setCompanyContactsName(String companyContactsName) {
        this.companyContactsName = companyContactsName;
    }

    public String getCompanyContactsPhone() {
        return companyContactsPhone;
    }

    public void setCompanyContactsPhone(String companyContactsPhone) {
        this.companyContactsPhone = companyContactsPhone;
    }


    public String getContractNotes() {
        return contractNotes;
    }

    public void setContractNotes(String contractNotes) {
        this.contractNotes = contractNotes;
    }


    public String getRefParkId() {
        return refParkId;
    }

    public void setRefParkId(String refParkId) {
        this.refParkId = refParkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getContractStatusStr() {
        return contractStatusStr;
    }

    public void setContractStatusStr(String contractStatusStr) {
        this.contractStatusStr = contractStatusStr;
    }

    public Date getJfDate() {
        return jfDate;
    }

    public void setJfDate(Date jfDate) {
        this.jfDate = jfDate;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(Integer contractCategory) {
        this.contractCategory = contractCategory;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Double getBuilarea() {
        return builarea;
    }

    public void setBuilarea(Double builarea) {
        this.builarea = builarea;
    }
}
