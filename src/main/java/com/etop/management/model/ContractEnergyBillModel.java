package com.etop.management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.etop.management.bean.EnergyCost;
import com.etop.management.util.Util;

/**
 * 轮询contractEnergy时，生成需要出帐的model
 * @author Administrator
 *
 */
public class ContractEnergyBillModel {

	private String refContractId; //bill out no
	
	private String refContractEnergyId;
	
	private String refBuildingId;
	
	private String refParkId;
	
	private String refRoomId;
	
	private String buildingName;
	
	private String roomName;
	
	private String currBillDate; //当前出帐日
	
	private int shareType; //公摊类型,2:无，0：面积，1：使用量
		
	private float powerPrice;//   单价
	
	private float waterPrice;//   单价
	
	private float gasPrice;//   单价
	
	private float acPrice;//   单价
	
	private float powerLastRecord;//最后计费读数
	
	private float waterLastRecord;
	
	private float gasLastRecord;
	
	private float acLastRecord;

	
	private Date startDate; //合同开始
	
	private Date endDate;//合同结束
	
	private Date terminateDate;//合同终止日
	
	private int contractStatus;//合同状态
	
	private String area;
	
	private String billDate;
	
	private int paymentDate;
	
	private String deadLine;//缴费截止日
	
	private Date energyLastBillDate;
	
	private Date lastPowerFeeDate;
	
	private Date lastWaterFeeDate;
	
	private Date lastGasFeeDate;
	
	private Date lastAcFeeDate;
	
	private List<EnergyItemForBillModel> itemList;
	
	public ContractEnergyBillModel(){
		this.itemList=new ArrayList<EnergyItemForBillModel>();
	}

	public Date getLastPowerFeeDate() {
		return lastPowerFeeDate;
	}

	public void setLastPowerFeeDate(Date lastPowerFeeDate) {
		this.lastPowerFeeDate = lastPowerFeeDate;
	}

	public Date getLastWaterFeeDate() {
		return lastWaterFeeDate;
	}

	public void setLastWaterFeeDate(Date lastWaterFeeDate) {
		this.lastWaterFeeDate = lastWaterFeeDate;
	}

	public Date getLastGasFeeDate() {
		return lastGasFeeDate;
	}

	public void setLastGasFeeDate(Date lastGasFeeDate) {
		this.lastGasFeeDate = lastGasFeeDate;
	}

	public Date getLastAcFeeDate() {
		return lastAcFeeDate;
	}

	public void setLastAcFeeDate(Date lastAcFeeDate) {
		this.lastAcFeeDate = lastAcFeeDate;
	}

	public String getRefContractId() {
		return refContractId;
	}

	public void setRefContractId(String refContractId) {
		this.refContractId = refContractId;
	}


	public String getCurrBillDate() {
		return currBillDate;
	}

	public void setCurrBillDate(String currBillDate) {
		this.currBillDate = currBillDate;
	}


//	public String getFeeStartDate() {
//		return feeStartDate;
//	}
//
//	public void setFeeStartDate(String feeStartDate) {
//		this.feeStartDate = feeStartDate;
//	}
//
//	public String getFeeEndDate() {
//		return feeEndDate;
//	}
//
//	public void setFeeEndDate(String feeEndDate) {
//		this.feeEndDate = feeEndDate;
//	}

//	public float getAmount() {
//		return amount;
//	}
//
//	public void setAmount(float amount) {
//		this.amount = amount;
//	}




	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	

	public String getRefContractEnergyId() {
		return refContractEnergyId;
	}

	public void setRefContractEnergyId(String refContractEnergyId) {
		this.refContractEnergyId = refContractEnergyId;
	}

	public float getPowerPrice() {
		return powerPrice;
	}

	public void setPowerPrice(float powerPrice) {
		this.powerPrice = powerPrice;
	}

	public float getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(float waterPrice) {
		this.waterPrice = waterPrice;
	}

	public float getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(float gasPrice) {
		this.gasPrice = gasPrice;
	}

	public float getAcPrice() {
		return acPrice;
	}

	public void setAcPrice(float acPrice) {
		this.acPrice = acPrice;
	}

//	public String itemDesc()
//	{
//		return String.format("项目信息：分期开始:%s,分期结束:%s,日单价:%f,月单价:%f,帐单覆盖开始日期:%s,帐单覆盖结束日期:%s,面积或内容:%s,金额:%f", 
//				this.startDate,this.endDate,
//				this.dailyUnitPrice,this.monthlyUnitPrice,
//				this.feeStartDate,this.feeEndDate,
//				this.area,this.amount
//				);
//	}
	


	public int getShareType() {
		return shareType;
	}

	public void setShareType(int shareType) {
		this.shareType = shareType;
	}

//	public int getBillType() {
//		return billType;
//	}
//
//	public void setBillType(int billType) {
//		this.billType = billType;
//	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

//	public String getBillDates() {
//		return billDates;
//	}
//
//	public void setBillDates(String billDates) {
//		this.billDates = billDates;
//	}

	public int getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(int paymentDate) {
		this.paymentDate = paymentDate;
	}

//	public int getBillPeriod() {
//		return billPeriod;
//	}
//
//	public void setBillPeriod(int billPeriod) {
//		this.billPeriod = billPeriod;
//	}

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	
//	public Date getLastFeeDate() {
//		return lastFeeDate;
//	}
//
//	public void setLastFeeDate(Date lastFeeDate) {
//		this.lastFeeDate = lastFeeDate;
//	}

	public Date getEnergyLastBillDate() {
		return energyLastBillDate;
	}

	public void setEnergyLastBillDate(Date energyLastBillDate) {
		this.energyLastBillDate = energyLastBillDate;
	}

	public String getRefRoomId() {
		return refRoomId;
	}

	public void setRefRoomId(String refRoomId) {
		this.refRoomId = refRoomId;
	}

//	public float getPowerRecords() {
//		return powerRecords;
//	}
//
//	public void setPowerRecords(float powerRecords) {
//		this.powerRecords = powerRecords;
//	}
//
//	public float getWaterRecords() {
//		return waterRecords;
//	}
//
//	public void setWaterRecords(float waterRecords) {
//		this.waterRecords = waterRecords;
//	}
//
//	public float getGasRecords() {
//		return gasRecords;
//	}
//
//	public void setGasRecords(float gasRecords) {
//		this.gasRecords = gasRecords;
//	}
//
//	public float getAcRecords() {
//		return acRecords;
//	}
//
//	public void setAcRecords(float acRecords) {
//		this.acRecords = acRecords;
//	}
//
//	public float getPowerAmount() {
//		return powerAmount;
//	}
//
//	public void setPowerAmount(float powerAmount) {
//		this.powerAmount = powerAmount;
//	}
//
//	public float getWaterAmount() {
//		return waterAmount;
//	}
//
//	public void setWaterAmount(float waterAmount) {
//		this.waterAmount = waterAmount;
//	}
//
//	public float getGasAmount() {
//		return gasAmount;
//	}
//
//	public void setGasAmount(float gasAmount) {
//		this.gasAmount = gasAmount;
//	}
//
//	public float getAcAmount() {
//		return acAmount;
//	}
//
//	public void setAcAmount(float acAmount) {
//		this.acAmount = acAmount;
//	}
//
//	public String getPowerStartDate() {
//		return powerStartDate;
//	}
//
//	public void setPowerStartDate(String powerStartDate) {
//		this.powerStartDate = powerStartDate;
//	}
//
//	public String getPowerEndDate() {
//		return powerEndDate;
//	}
//
//	public void setPowerEndDate(String powerEndDate) {
//		this.powerEndDate = powerEndDate;
//	}
//
//	public String getWaterStartDate() {
//		return waterStartDate;
//	}
//
//	public void setWaterStartDate(String waterStartDate) {
//		this.waterStartDate = waterStartDate;
//	}
//
//	public String getWaterEndDate() {
//		return waterEndDate;
//	}
//
//	public void setWaterEndDate(String waterEndDate) {
//		this.waterEndDate = waterEndDate;
//	}
//
//	public String getGasStartDate() {
//		return gasStartDate;
//	}
//
//	public void setGasStartDate(String gasStartDate) {
//		this.gasStartDate = gasStartDate;
//	}
//
//	public String getGasEndDate() {
//		return gasEndDate;
//	}
//
//	public void setGasEndDate(String gasEndDate) {
//		this.gasEndDate = gasEndDate;
//	}
//
//	public String getAcStartDate() {
//		return acStartDate;
//	}
//
//	public void setAcStartDate(String acStartDate) {
//		this.acStartDate = acStartDate;
//	}
//
//	public String getAcEndDate() {
//		return acEndDate;
//	}
//
//	public void setAcEndDate(String acEndDate) {
//		this.acEndDate = acEndDate;
//	}
//
//	public float getPowerShareAmount() {
//		return powerShareAmount;
//	}
//
//	public void setPowerShareAmount(float powerShareAmount) {
//		this.powerShareAmount = powerShareAmount;
//	}
//
//	public float getWaterShareAmount() {
//		return waterShareAmount;
//	}
//
//	public void setWaterShareAmount(float waterShareAmount) {
//		this.waterShareAmount = waterShareAmount;
//	}
//
//	public float getGasShareAmount() {
//		return gasShareAmount;
//	}
//
//	public void setGasShareAmount(float gasShareAmount) {
//		this.gasShareAmount = gasShareAmount;
//	}
//
//	public float getAcShareAmount() {
//		return acShareAmount;
//	}
//
//	public void setAcShareAmount(float acShareAmount) {
//		this.acShareAmount = acShareAmount;
//	}
//
//	public float getAcShareRecords() {
//		return acShareRecords;
//	}
//
//	public void setAcShareRecords(float acShareRecords) {
//		this.acShareRecords = acShareRecords;
//	}
//
//	public float getGasShareRecords() {
//		return gasShareRecords;
//	}
//
//	public void setGasShareRecords(float gasShareRecords) {
//		this.gasShareRecords = gasShareRecords;
//	}
//
//	public float getWaterShareRecords() {
//		return waterShareRecords;
//	}
//
//	public void setWaterShareRecords(float waterShareRecords) {
//		this.waterShareRecords = waterShareRecords;
//	}
//
//	public float getPowerShareRecords() {
//		return powerShareRecords;
//	}
//
//	public void setPowerShareRecords(float powerShareRecords) {
//		this.powerShareRecords = powerShareRecords;
//	}

	public float getPowerLastRecord() {
		return powerLastRecord;
	}

	public void setPowerLastRecord(float powerLastRecord) {
		this.powerLastRecord = powerLastRecord;
	}

	public float getWaterLastRecord() {
		return waterLastRecord;
	}

	public void setWaterLastRecord(float waterLastRecord) {
		this.waterLastRecord = waterLastRecord;
	}

	public float getGasLastRecord() {
		return gasLastRecord;
	}

	public void setGasLastRecord(float gasLastRecord) {
		this.gasLastRecord = gasLastRecord;
	}

	public float getAcLastRecord() {
		return acLastRecord;
	}

	public void setAcLastRecord(float acLastRecord) {
		this.acLastRecord = acLastRecord;
	}
	
	public String itemDesc()
	{
		if(this.getItemList()==null || this.getItemList().size()==0)
		{
			return "";
		}
		
		String result=String.format("能源费用帐单明细：楼:%s,房间号:%s,计费开始日:%s,结束日:%s\r\n",
				this.buildingName,this.roomName, 
				this.getItemList().get(0).getFeeStartDate(),
				Util.increaseDate(this.getItemList().get(0).getFeeEndDate(),-1)


				);
		
		for(EnergyItemForBillModel item :this.getItemList())
		{
			result+=item.toString()+"\r\n";
		}
		
		return result;
		
		
		
//		return String.format("能源费用明细：出帐日:%s,覆盖日期:%s,公摊方式:%s,\r\n"
//				+ "电费:最后抄表日:%s,读数:%.2f,使用量:%.2f,公摊量:%.2f,公摊比例:%.4f,总额:%.2f;\r\n"
//				+ "水费:最后抄表日:%s,读数:%.2f,使用量:%.2f,公摊量:%.2f,公摊比例:%.4f,总额:%.2f;\r\n"
//				+ "燃气费:最后抄表日:%s,读数:%.2f,使用量:%.2f,公摊量:%.2f,公摊比例:%.4f,总额:%.2f;\r\n"
//				+ "空调费:最后抄表日:%s,读数:%.2f,使用量:%.2f,公摊量:%.2f,公摊比例:%.4f,总额:%.2f;", 
//
//				Util.formatDate(new Date()),feeStartDate+"-"+feeEndDate,shareType==1?"按面积":"使用量",
//						this.powerEndDate,this.powerLastRecord,this.powerRecords,this.powerShareRecords,(this.powerRecords+this.powerShareRecords)*this.powerPrice,
//						this.waterEndDate,this.waterLastRecord,this.waterRecords,this.waterShareRecords,(this.waterRecords+this.waterShareRecords)*this.waterPrice,
//						this.gasEndDate,this.gasLastRecord,this.gasRecords,this.gasShareRecords,(this.gasRecords+this.gasShareRecords)*this.gasPrice,
//						this.acEndDate,this.acLastRecord,this.acRecords,this.acShareRecords,(this.acRecords+this.acShareRecords)*this.acPrice				
//				);
	}
//	/**
//	 * 总额
//	 * @return
//	 */
//	public float totalAmount()
//	{
//		return (this.powerRecords+this.powerShareRecords)*this.powerPrice+
//				(this.waterRecords+this.waterShareRecords)*this.waterPrice+
//				(this.gasRecords+this.gasShareRecords)*this.gasPrice+
//				(this.acRecords+this.acShareRecords)*this.acPrice;
//	}

	public String getRefBuildingId() {
		return refBuildingId;
	}

	public void setRefBuildingId(String refBuildingId) {
		this.refBuildingId = refBuildingId;
	}

	public String getRefParkId() {
		return refParkId;
	}

	public void setRefParkId(String refParkId) {
		this.refParkId = refParkId;
	}

	public int getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(int contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Date getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}

	public List<EnergyItemForBillModel> getItemList() {
		return itemList;
	}

	public void setItemList(List<EnergyItemForBillModel> itemList) {
		this.itemList = itemList;
	}
	
	public float getEnergyPrice(int energyType)
	{
		switch(energyType)
		{
		case 0:
			return this.powerPrice;
		case 1:
			return this.waterPrice;
		case 2:
			return this.gasPrice;
		case 3:
			return this.acPrice;
			default:
				return 0;
		
		}
	}
	
	public Date getEnergyRecordDate(int energyType)
	{
		switch(energyType)
		{
		case 0:
			return this.lastPowerFeeDate;
		case 1:
			return this.lastWaterFeeDate;
		case 2:
			return this.lastGasFeeDate;
		case 3:
			return this.lastAcFeeDate;
			default:
				return null;
		
		}
	}
	
	public  int getEnergyFeeDates(int energyType)
	{
		int result=0;
		
		for(EnergyItemForBillModel item :this.getItemList())
		{
			if(item.getEnergyType()==energyType)
			{
				result=item.getFeeDates();
				
				break;
			}
		}
		
		return result;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	
	@Override
	public String toString()
	{
		return String.format("ContractEnergyBillModel:\r\nbuild:%s,room:%s,area:%s,shareType:%d,"
				+ "billDate:%s,paymentDay:%d,currBillDate:%s,deadline:%s,"
				+ "contractStatus:%d,startDate:%tF%n,endDate:%tF%n,terminateDate:%tF%n",
				this.buildingName,this.roomName,this.area,this.shareType,
				this.billDate,this.paymentDate,this.currBillDate,this.deadLine,
				this.contractStatus,this.startDate,this.endDate,this.terminateDate

				);
	}
	
}

