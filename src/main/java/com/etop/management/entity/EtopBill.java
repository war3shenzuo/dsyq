package com.etop.management.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class EtopBill {

	private String billId;	//账单编号

	private String parkId;	//园区id

	private int billType;	//账单类型：1，收入；2，支出

	private int billStatus;	//账单状态：1，未付款；2，未付清；3，已付款

	private String companyId;	//公司id（客户id）
	
	private String companyName;	//公司名称

	private int billSource;	//账单来源：租赁合同、快递合同、服务合同

	private String billNoOut;	//外部账单号

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createdTime;	//创建时间

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date deadline;	//缴费期限

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;	//支付时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime;	//起始覆盖时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;	//截止覆盖时间

	private BigDecimal amount;	//金额

	private BigDecimal amountPaid;	//已付金额

	private BigDecimal overdueFine;	//滞纳金
	
	private BigDecimal overdueRate;	// 滞纳金比例

	private BigDecimal accountRemission;	//本金减免

	private BigDecimal overdueRemission;	//滞纳金减免

	private String description;	//说明
	
	private String comment;	//备注

	private String attachment;	//说明附件，图片，文档等地址
	
	private int auditStatus;	//账单审核状态
	
	private int auditLevel;	// 审核级别

	private String auditor;	//审核人

	private Date auditTime;	//审核时间

	private String finance;	//财务审核人

	private Date financeTime;	//财务审核时间

	private boolean deleted;	//是否删除
	
	private String number;	
	
    private BigDecimal totalPrice; 
    
    private String category; 
    
    private String subject; 
    
    private String building; 
    
    private String floor; 
    
    private String block; 
    
    private String room; 
    
    private String buildingNo; 
    
    private String storey; 
    
    private String zoneNo; 
    
    private String roomNo; 
    
    private BigDecimal unitPrice;
    
    
	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
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

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public int getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getBillSource() {
		return billSource;
	}

	public void setBillSource(int billSource) {
		this.billSource = billSource;
	}

	public String getBillNoOut() {
		return billNoOut;
	}

	public void setBillNoOut(String billNoOut) {
		this.billNoOut = billNoOut;
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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public BigDecimal getOverdueFine() {
		return overdueFine;
	}

	public void setOverdueFine(BigDecimal overdueFine) {
		this.overdueFine = overdueFine;
	}

	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	public BigDecimal getAccountRemission() {
		return accountRemission;
	}

	public void setAccountRemission(BigDecimal accountRemission) {
		this.accountRemission = accountRemission;
	}

	public BigDecimal getOverdueRemission() {
		return overdueRemission;
	}

	public void setOverdueRemission(BigDecimal overdueRemission) {
		this.overdueRemission = overdueRemission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getAuditLevel() {
		return auditLevel;
	}

	public void setAuditLevel(int auditLevel) {
		this.auditLevel = auditLevel;
	}

	public String getFinance() {
		return finance;
	}

	public void setFinance(String finance) {
		this.finance = finance;
	}

	public Date getFinanceTime() {
		return financeTime;
	}

	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isPaid() {
		BigDecimal amneed = amount.subtract(accountRemission);
		if(amneed.compareTo(BigDecimal.ZERO) < 0)
			amneed = BigDecimal.ZERO;
		BigDecimal ovneed = overdueFine.subtract(overdueRemission);
		if(ovneed.compareTo(BigDecimal.ZERO) < 0)
			ovneed = BigDecimal.ZERO;
		if(amneed.add(ovneed).compareTo(amountPaid) > 0)
			return false;
		else
			return true;
	}
	
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
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


	/**
	 * 账单类型
	 */
	public enum BillType {
		INCOME(1, "收入"),
		OUTLAY(2, "支出");
		public final int value;
		public final String desc;
		BillType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public static BillType valueOf(int value) {
			switch (value) {
			case 1:
				return INCOME;
			case 2:
				return OUTLAY;
			default:
				return null;
			}
		}
	}
	
	/**
	 * 账单状态
	 */
	public enum BillStatus {
		UNPAID(0, "未付款"),
		NOTENOUGH(1, "未付清"),
		PAID(2, "已支付");
		public final int value;
		public final String desc;
		BillStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public static BillStatus valueOf(int value) {
			switch (value) {
			case 0 :
				return UNPAID;
			case 1 :
				return NOTENOUGH;
			case 2 :
				return PAID;
			default :
				return null;
			}
		}
	}
	
	/**
	 * 账单来源
	 */
	public enum BillSource {
		UNKNOWN(0, "无",""),
		LEASE_CONTRACT(1, "租赁合同","zl"),
		SUBCONTRACT_CONTRACT(2, "外包合同","wb"),
		ENERGY_CONTRACT(3, "能源合同","ny"),
		PROPERTY_CONTRACT(4, "物业合同","wy"),
		SERVICE_CONTRACT(5, "服务合同","fw"),
		PARK_SERVICE(6, "园区服务", "yf"),
		OTHER(9, "其他","");
		public final int value;
		public final String desc;
		public final String code;
		BillSource(int value, String desc,String code) {
			this.value = value;
			this.desc = desc;
			this.code = code;
		}
		public static BillSource valueOf(int value) {
			switch (value) {
			case 0 :
				return UNKNOWN;
			case 1 :
				return LEASE_CONTRACT;
			case 2 :
				return SUBCONTRACT_CONTRACT;
			case 3 :
				return ENERGY_CONTRACT;
			case 4 :
				return PROPERTY_CONTRACT;
			case 5 :
				return SERVICE_CONTRACT;
			case 6 :
				return PARK_SERVICE;
			case 9 :
				return OTHER;
			default :
				return null;
			}
		}
	}
	
	/**
	 * 审核状态
	 */
	public enum AuditStatus {
		UNCHECK(0, "未审核"),
		CHECK_ACCEPT(2, "园长通过"),
		CHECK_REFUSE(-2, "园长拒绝"),
		FINANCE_ACCEPT(1, "财务通过"),
		FINANCE_REFUSE(-1, "财务拒绝");
		public final int value;
		public final String desc;
		AuditStatus(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public static AuditStatus valueOf(int value) {
			switch (value) {
			case 0 :
				return UNCHECK;
			case 2 :
				return CHECK_ACCEPT;
			case -2 :
				return CHECK_REFUSE;
			case 1 :
				return FINANCE_ACCEPT;
			case -1 :
				return FINANCE_REFUSE;
			default :
				return null;
			}
		}
	}
}