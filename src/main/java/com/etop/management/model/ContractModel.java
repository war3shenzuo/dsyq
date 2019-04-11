package com.etop.management.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractModel extends TrackableModel {

	
	public String refContractLeaseId;
	
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
	
	public String roomStructureArea;
	
	public String roomUsableArea;
	
	public String roomMonthlyPrice;
	
	public String roomDailyPrice;
	
	public String roomOrientation;
	
	public String roomLocated;
	
	public String roomWindows;
	
	public String roomDecoration;
	
	
	public String contractNotes;//合同备注
		
	public String contractPayMethod;//付款方式
	
	
	public int contractCategory;
	
	public int contractType;
	
	public int contractStatus;
	
	public BigDecimal deposit;
	
	public int depositBillStatus;
	
//	public int auditStatus;
	
	public String contractStatusStr;
	
	public String terminateReason;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date terminateDate;
	
	public Date getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date contractStartDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date contractEndDate;
	
	//12.13当合同为物业、能源时，需要与相关租赁合同日期进行比较
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date leaseContractStartDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date leaseContractEndDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date contractSignDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date lastBalanceDate;

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

	public int getContractCategory() {
		return contractCategory;
	}

	public void setContractCategory(int contractCategory) {
		this.contractCategory = contractCategory;
	}

	public int getContractType() {
		return contractType;
	}

	public void setContractType(int contractType) {
		this.contractType = contractType;
	}

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
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
	
	
	public String getRoomStructureArea() {
		return roomStructureArea;
	}

	public void setRoomStructureArea(String roomStructureArea) {
		this.roomStructureArea = roomStructureArea;
	}

	public String getRoomUsableArea() {
		return roomUsableArea;
	}

	public void setRoomUsableArea(String roomUsableArea) {
		this.roomUsableArea = roomUsableArea;
	}

	public String getRoomMonthlyPrice() {
		return roomMonthlyPrice;
	}

	public void setRoomMonthlyPrice(String roomMonthlyPrice) {
		this.roomMonthlyPrice = roomMonthlyPrice;
	}

	public String getRoomDailyPrice() {
		return roomDailyPrice;
	}

	public void setRoomDailyPrice(String roomDailyPrice) {
		this.roomDailyPrice = roomDailyPrice;
	}

	public String getRoomOrientation() {
		return roomOrientation;
	}

	public void setRoomOrientation(String roomOrientation) {
		this.roomOrientation = roomOrientation;
	}

	public String getRoomLocated() {
		return roomLocated;
	}

	public void setRoomLocated(String roomLocated) {
		this.roomLocated = roomLocated;
	}

	public String getRoomWindows() {
		return roomWindows;
	}

	public void setRoomWindows(String roomWindows) {
		this.roomWindows = roomWindows;
	}

	public String getRoomDecoration() {
		return roomDecoration;
	}

	public void setRoomDecoration(String roomDecoration) {
		this.roomDecoration = roomDecoration;
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

	public String getContractPayMethod() {
		return contractPayMethod;
	}

	public void setContractPayMethod(String contractPayMethod) {
		this.contractPayMethod = contractPayMethod;
	}

	public String getContractStatusStr() {
		
		String result="合同中止";
		
		if(this.contractStatus==-1)
		{
			result="无效或审批中";
		}
		if(this.contractStatus==2)
		{
			result="终止审批中";
		}
		
		
		if(this.contractStatus==0)
		{
			if (this.contractStartDate == null)
			{
				return null;
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			String nows=df.format(new Date());
			
			Date now =null ;
			try {
				now = df.parse(nows);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(now.getTime()<this.contractStartDate.getTime())
			{
				result="合同未开始";
			}
			
			if(now.getTime()>=this.contractStartDate.getTime() && now.getTime()<=this.contractEndDate.getTime())
			{
				result="合同正常";
			}
			
			if(now.getTime()>this.contractEndDate.getTime())
			{
				result="合同已到期";
			}
			
		}
		
		if(this.contractStatus==-1)
		{
			result="合同无效";
		}
				
		
		return result;
	}

	public Date getLeaseContractStartDate() {
		return leaseContractStartDate;
	}

	public void setLeaseContractStartDate(Date leaseContractStartDate) {
		this.leaseContractStartDate = leaseContractStartDate;
	}

	public Date getLeaseContractEndDate() {
		return leaseContractEndDate;
	}

	public void setLeaseContractEndDate(Date leaseContractEndDate) {
		this.leaseContractEndDate = leaseContractEndDate;
	}

	public void setContractStatusStr(String contractStatusStr) {
		this.contractStatusStr = contractStatusStr;
	}

	public String getRefContractLeaseId() {
		return refContractLeaseId;
	}

	public void setRefContractLeaseId(String refContractLeaseId) {
		this.refContractLeaseId = refContractLeaseId;
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

//	public int getAuditStatus() {
//		return auditStatus;
//	}
//
//	public void setAuditStatus(int auditStatus) {
//		this.auditStatus = auditStatus;
//	}

	public Date getLastBalanceDate() {
		return lastBalanceDate;
	}

	public void setLastBalanceDate(Date lastBalanceDate) {
		this.lastBalanceDate = lastBalanceDate;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public int getDepositBillStatus() {
		return depositBillStatus;
	}

	public void setDepositBillStatus(int depositBillStatus) {
		this.depositBillStatus = depositBillStatus;
	}
 
	
}
