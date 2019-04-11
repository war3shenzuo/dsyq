package com.etop.management.bean;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.entity.EtopBill.BillSource;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
/**
 * 
 * <br>
 * <b>功能：</b>EtopEnergyCostEntity<br>
 */
public class EnergyCost extends TrackableBean{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = -9071078137179575396L;
	
	private String refBillId;//关联帐单
	
	private int energyCategory;//   能源类别：0房间1楼
	
	private String refBuildingId;
	
	private int activeRoomCount;//楼能源录入时，当时的激活房间数量
		private String refItemId;//   关联房间or楼ID
		private int energyType;//   能源种类：0电1水2气3空调
		private double record;//   记录值
	
	private double amount;
	
	private int days;
	
	private double dailyAmount;
	
	private double shareAmount;
	
	private int isBilled;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")	private Date recordDate;//   记录日期    private Date recordEndDate;//   记录日期		public int getEnergyCategory() {	    return this.energyCategory;	}	public void setEnergyCategory(int energyCategory) {	    this.energyCategory=energyCategory;	}	public String getRefItemId() {	    return this.refItemId;	}	public void setRefItemId(String refItemId) {	    this.refItemId=refItemId;	}	public int getEnergyType() {	    return this.energyType;	}	public void setEnergyType(int energyType) {	    this.energyType=energyType;	}	public double getRecord() {	    return this.record;	}	public void setRecord(double record) {	    this.record=record;	}	public Date getRecordDate() {	    return this.recordDate;	}	public void setRecordDate(Date recordDate) {	    this.recordDate=recordDate;	}
	public String getRefBuildingId() {
		return refBuildingId;
	}
	public void setRefBuildingId(String refBuildingId) {
		this.refBuildingId = refBuildingId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getShareAmount() {
		return shareAmount;
	}
	public void setShareAmount(double shareAmount) {
		this.shareAmount = shareAmount;
	}
	public String getRefBillId() {
		return refBillId;
	}
	public void setRefBillId(String refBillId) {
		this.refBillId = refBillId;
	}	
	
	public int getIsBilled() {
		return isBilled;
	}
	public void setIsBilled(int isBilled) {
		this.isBilled = isBilled;
	}


	public int getActiveRoomCount() {
		return activeRoomCount;
	}
	public void setActiveRoomCount(int activeRoomCount) {
		this.activeRoomCount = activeRoomCount;
	}


	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}


	public double getDailyAmount() {
		return dailyAmount;
	}
	public void setDailyAmount(double dailyAmount) {
		this.dailyAmount = dailyAmount;
	}

	@Override
	public String toString()
	{
		Gson gson = new Gson();
		
		String str=gson.toJson(this);
		
		return String.format("[EnergyCost]:%s",str);
	}

	public Date getRecordEndDate() {
		return recordEndDate;
	}
	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	/**
	 * 能源类型
	 */
	public enum EnergyType {
		POWER(0, "电费","power"),
		WATER(1, "水费","water"),
		GAS(2, "燃气费","gas"),
		AC(3, "空调费","ac");
		public final int value;
		public final String desc;
		public final String code;
		EnergyType(int value, String desc,String code) {
			this.value = value;
			this.desc = desc;
			this.code = code;
		}
		public static EnergyType valueOf(int value) {
			switch (value) {
			case 0 :
				return POWER;
			case 1 :
				return WATER;
			case 2 :
				return GAS;
			case 3 :
				return AC;			
			default :
				return null;
			}
		}
	}
	
	public enum ShareType {
		USED(0, "使用量","used"),
		BUILDAREA(1, "建筑面积","buildarea"),
		NONE(2,"不公摊","none");
		
		public final int value;
		public final String desc;
		public final String code;
		ShareType(int value, String desc,String code) {
			this.value = value;
			this.desc = desc;
			this.code = code;
		}
		public static ShareType valueOf(int value) {
			switch (value) {
			case 0 :
				return USED;
			case 1 :
				return BUILDAREA;
			case 2 :
				return NONE;
					
			default :
				return null;
			}
		}
	}
	
	
	public enum EnergyCategory{
		BUILDING(1,"楼能源","building"),
		ROOM(0,"房能源","room");
		public final int value;
		public final String desc;
		public final String code;
		EnergyCategory(int value, String desc,String code) {
			this.value = value;
			this.desc = desc;
			this.code = code;
		}
		public static EnergyCategory valueOf(int value) {
			switch (value) {
			case 1 :
				return BUILDING;
			case 0 :
				return ROOM;					
			default :
				return null;
			}
		}
		
	}
	
	
}

