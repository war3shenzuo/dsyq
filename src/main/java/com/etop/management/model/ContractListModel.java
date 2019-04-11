package com.etop.management.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContractListModel extends TrackableModel{

	public String refContractLeaseId;
	
	public String contractNo;
	
	public String refRoomId;
	
	public String building;
	
	public String floor;
	
	public String block;
	
	public String room;
	
	public String refCompanyId;
	
	public String companyName;
	
	public int contractStatus;
	
	public BigDecimal deposit;
	
	public int depositBillStatus;
	
//	public int auditStatus;
	
	public String contractStatusStr;
	
	public int contractCategory;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date contractStartDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date contractEndDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date terminateDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	public Date lastBalanceDate;

	public String getContractRefLeaseId() {
		return refContractLeaseId;
	}

	public void setRefLeaseId(String refLeaseId) {
		this.refContractLeaseId = refLeaseId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractStatusStr() {
		
		String result="<label class=\"label label-danger\">中止</label>";
		
		if(this.contractStatus==7)
		{
			result="<label class=\"label label-default\">已删除</label>";
		}
		if(this.contractStatus==-11)
		{
			result="<label class=\"label label-warning\">删除审核中</label>";
		}
		
		if(this.contractStatus==-9)
		{
			result="<label class=\"label label-warning\">园长拒绝</label>";
		}
		if(this.contractStatus==-7)
		{
			result="<label class=\"label label-warning\">财务拒绝</label>";
		}
		if(this.contractStatus==-5)
		{
			result="<label class=\"label label-warning\">终止审批中</label>";
		}
		if(this.contractStatus==-3)
		{
			result="<label class=\"label label-warning\">园长审批中</label>";
		}
		if(this.contractStatus==-1)
		{
			result="<label class=\"label label-warning\">财务审批中</label>";
		}
		if(this.contractStatus==1)
		{
			result="<label class=\"label label-warning\">初始</label>";
		}
		
		if(this.contractStatus==3)
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
				result="<label class=\"label label-info\">未开始</label>";
			}
			
			if(now.getTime()>=this.contractStartDate.getTime() && now.getTime()<=this.contractEndDate.getTime())
			{
				result="<label class=\"label label-success\">正常</label>";
			}
			
			if(now.getTime()>this.contractEndDate.getTime())
			{
				result="<label class=\"label label-warning\">到期</label>";
			}
			
		}
		if(this.contractStatus==1)
		{
			result="<label class=\"label label-warning\">初始</label>";
		}
		
		if(this.contractStatus==5)
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
						
			if(now.getTime()>=this.contractStartDate.getTime() && now.getTime()<=this.contractEndDate.getTime())
			{
				result="<label class=\"label label-success\">终止(未结束)</label>";
			}
			
			if(now.getTime()>this.contractEndDate.getTime())
			{
				result="<label class=\"label label-warning\">终止(已结束)</label>";
			}
			
		}		
		
		return result;
	}

	public void setContractStatusStr(String contractStatusStr) {
		this.contractStatusStr = contractStatusStr;
	}

	public int getContractCategory() {
		return contractCategory;
	}

	public void setContractCategory(int contractCategory) {
		this.contractCategory = contractCategory;
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
	
	public Date getTerminateDate() {
		return terminateDate;
	}
	
	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}



	public String getRefContractLeaseId() {
		return refContractLeaseId;
	}

	public void setRefContractLeaseId(String refContractLeaseId) {
		this.refContractLeaseId = refContractLeaseId;
	}


	public String getRefRoomId() {
		return refRoomId;
	}

	public void setRefRoomId(String refRoomId) {
		this.refRoomId = refRoomId;
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
