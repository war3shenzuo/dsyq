package com.etop.management.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.Contract;
import com.etop.management.bean.Contract.ContractStatus;
import com.etop.management.bean.ContractEnergy;
import com.etop.management.bean.ContractItem;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.bean.PageContract;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.constant.SysStatus;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ContractEnergyDao;
import com.etop.management.dao.ContractItemDao;
import com.etop.management.dao.EnergyCostDao;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.model.CalendarDifferenceModel;
import com.etop.management.model.ContractItemBillModel;
import com.etop.management.model.ContractListModel;
import com.etop.management.model.ContractModel;
import com.etop.management.service.ContractItemService;
import com.etop.management.service.ContractService;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopCompanyBusinessService;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.EtopFloorService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.service.RemindService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContractServiceImpl implements ContractService {
	
	//private  final static LoggerUtil LoggerUtil =LoggerUtil.getLoggerUtil(ContractServiceImpl.class);
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	ContractItemDao contractItemDao;
	
	@Resource
	ContractEnergyDao contractEnergyDao;
	
	@Resource
	EtopSequenceService etopSequenceService;
	
	@Resource
	EtopFloorService etopFloorService;
	
	@Resource
	EtopCompanyIntentionService etopCompanyIntentionService;
	
	@Resource
	EtopCompanyService etopCompanyService;
	
	@Resource
	EtopBillDao etopBillDao;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	EnergyCostDao energyCostDao;
	
	@Resource
	EtopThresholdService etopThresholdService;
	
	@Resource
	ContractItemService contractItemService;
	
	@Resource
	EnergyCostService energyCostService;
	
	@Resource
	RemindService remindService;

	@Resource
	EtopCompanyBusinessService etopCompanyBusinessService;

	@Override
	public Contract getContractById(String id) {
		
		return this.contractDao.getContractById(id);
	}

	@Override
	public String saveContract(OpInfoBean op, ContractModel model) {
		
		String result=null;
		
		Contract entity=new Contract();
		
		Util.translate(model, entity);
		
		//is created
		if(Util.isNullOrEmpty(model.getId()))
		{
		
			entity.setId(UUID.randomUUID().toString());
			
			entity.setContractNo(this.etopSequenceService.getFormatId(model.getParkCode(), BillSource.valueOf(model.getContractCategory()).code));
			
			//新建合同，先置为无效，审核状态为初始
			entity.setContractStatus(ContractStatus.INIT.value);
			
			entity.setContractType(0);
			
//			entity.setAuditStatus(ContractAuditStatus.INIT.value);
			
			entity.createdAt=new Date();
			
			entity.createdBy=op.getOper();
			
			entity.updatedAt=new Date();
			
			entity.updatedBy=op.getOper();
			
			
			
			int cresult=this.contractDao.createContract(entity);
			
			result=entity.getId();
			
			LoggerUtil.info(String.format("[saveContract]create contract:%s", entity.toString()));
			
			
			//创建时，同时更新意向公司审核状态，如果存在

			EtopCompanyIntention intention=this.etopCompanyIntentionService.getCompIntentionInfoById(entity.getRefCompanyId());
			
			if(intention!=null)
			{
				//审核中
				intention.setReviewStatus(1);
				
				this.etopCompanyIntentionService.updateById(intention);
				
				LoggerUtil.info(String.format("[saveContract]update intention company[%s]'s status to 1", intention.getId()));
				
			}
			
			
			
			//TODO:新建合同后，发送消息至园长，以示审核
			
			
		}
		else
		{

			if(entity.getContractCategory()==BillSource.LEASE_CONTRACT.value)
			{
				//get original
				
				Contract o=this.contractDao.getContractById(entity.getId());
				
				if(o!=null)
				{
					//room changed,如果房间更改了，原房间无论是否已绑定，均解绑
					if(!o.getRefRoomId().equals(entity.getRefRoomId()))
					{
						//unbind original
						this.etopFloorService.unbindRoom(o.getRefRoomId());
						
						LoggerUtil.info(String.format("[saveContract]unbind original room[%s] when room changed", o.getRefRoomId()));
						
					}
					
					//bind current
					//12.12修改，有可能是财务拒绝后，再次保存申请，需要重新绑定
					//this.etopFloorService.bindRoom(entity.getRefRoomId(), entity.getRefCompanyId(), entity.getId());
				
					
				}
			}
			
			//合同终止
//			if(entity.getContractStatus()==1)
//			{
//				entity.setTerminateDate(new Date());
//			}
			
			entity.updatedAt=new Date();
			
			entity.updatedBy=op.getOper();
			
			this.contractDao.updateContract(entity);
			
			result=entity.getId();
			
			LoggerUtil.info(String.format("[saveContract]update contract:%s", entity.toString()));
			
		}
		
		
		return result;
	}

	
	@Override
	public void updateContract(OpInfoBean op, Contract entity) {

		entity.updatedAt=new Date();
		
		entity.updatedBy=op.getOper();
		
		this.contractDao.updateContract(entity);
		
		LoggerUtil.info(String.format("[updateContract]create contract:%s", entity.toString()));
		
	}

	
	
	@Override
	public List<ContractListModel> getContractList(PageContract page) {
				
		return this.contractDao.getContractList(page);
	}

	@Override
	public int getContractListCount(PageContract page) {
		
		return this.contractDao.getContractListCount(page);
	}

	@Override
	public int removeContract(OpInfoBean op, String id) {
				
		Contract contract=this.contractDao.getContractById(id);
		
		if(contract==null) return -2;
		
		int category=contract.getContractCategory();
		
		//先检查合同是否已生成帐单
		
		//若合同是租赁，检查是否已被其他合同引用
		if(category==BillSource.LEASE_CONTRACT.value)
		{
			List<Contract> list=this.contractDao.getContractListByLeaseId(contract.getId());
			
			if(list!=null && list.size()>0)
			{
				return -1;
			}
			
			//delete
			
			this.contractItemDao.deleteContractItemByContractId(id);
			
		}
		else
		{
			//删除其他类型，目前只有能源合同
			
			this.contractEnergyDao.deleteContractEnergyByRefContractId(id);
			
		}
		
		int result=this.contractDao.deleteContract(id);
		
		this.etopFloorService.unbindRoom(contract.getRefRoomId());
		
		return result;
	}

	/**
	 * 为合同项目生成帐单<br>
	 * 租赁合同需要检测是否到期，生成退押金帐单，根据字段deposit_bill_status,低位income,高位outgo
	 * @param model
	 * @return
	 */
	private int generateBillByContractItemBillModel(ContractItemBillModel model) {
		
		//OpInfoBean op=Util.getOpInfo(); 
		int r=0;
		try
		{
		Contract contract=this.contractDao.getContractById(model.getRefContractId());
	
		ContractItem contractItem=this.contractItemDao.getContractItemById(model.getRefContractItemId());
		
		if(contract==null)
		{
			LoggerUtil.warn(String.format("contract is not exist, id (%s)",model.getRefContractId()));
			
			return -1;
		}
			
		if(contractItem==null)
		{
			LoggerUtil.warn(String.format("contractItem is not exist, contractId (%s),contractItemId(%s)",
					model.getRefContractId(),
					model.getRefContractItemId()
					));
			
			return -2;
		}
		
		//这里先要判断最后结算日是否>=分期开始日前一天，是出，不是，意味着前一个分期还未结束，略过
		if(contract.getLastBalanceDate()!=null){
			
			if(Util.dateDiff(Util.formatDate(contractItem.getStartDate()),Util.formatDate(contract.getLastBalanceDate()))>1)
			{	
				return 0;
			}
		}
		
		Park park=this.parkDao.getParkInfo(contract.getRefParkId());
		
		if(park==null)
		{
			LoggerUtil.warn(String.format("park is not exist, id (%s)",contract.getRefParkId()));
			
			return -3;
		}
		
		EtopBill etopBill=new EtopBill();
		
		etopBill.setParkId(contract.getRefParkId());
		
		etopBill.setCompanyId(contract.getRefCompanyId());
		
		etopBill.setBillId(etopSequenceService.getFormatId(park.getParkCode(), "zd"));
		
		
		etopBill.setStartTime(Util.str2Date(model.getFeeStartDate()));
		
		etopBill.setEndTime(Util.str2Date(model.getFeeEndDate()));
		
		etopBill.setBillSource(contract.getContractCategory());
		
		
		etopBill.setBillNoOut(contract.getContractNo());
		
		//amount在这里设置
		
		Float area=1f;
		
		//租赁，物业需要面积
		if(contract.getContractCategory()==BillSource.LEASE_CONTRACT.value || 
				contract.getContractCategory()==BillSource.PROPERTY_CONTRACT.value)
		{
			area=Float.valueOf(model.getArea());
		}
		
		double amount=0;
		
		//1226按月计费
//		总价计算公式：
//		A=分期开始日期，B=分期结束日期+1天
//		a1=分期开始日期的年份，a2=月份
//		b1=B的年份，b2=B的月份
//		C=(（b1-a1）*12+(b2-a2))，这里C意思是有几个足月
//		D=B-（A+C），这里A+C是足月收到的日期
//		最终公式为：C*月单价+D*日单价
		
		if(model.getBalanceMonthly()==1)
		{
			CalendarDifferenceModel cm=Util.getCalendarDifference(model.getFeeStartDate(), model.getFeeEndDate());
			
			amount=model.getMonthlyUnitPrice()*area*cm.getMonths()+
					model.getDailyUnitPrice()*area*cm.getDays();
			
		}
		else
		{
			amount=model.getDailyUnitPrice()*area*(Util.dateDiff(model.getFeeEndDate(),model.getFeeStartDate())+1);
		}
		
		
		etopBill.setAmount(new BigDecimal(amount));
		
		model.setAmount(amount);
		
		etopBill.setDeadline(DateUtil.toTodayNight(Util.str2Date(model.getPaymentDate())));
		
		etopBill.setBillType(BillType.INCOME.value);
		
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		
		etopBill.setAuditStatus(EtopBill.AuditStatus.UNCHECK.value);
		
		etopBill.setAuditLevel(2);
		
		etopBill.setCompanyId(contract.getRefCompanyId());
		
		etopBill.setCompanyName(contract.getCompanyName());
		
		if(BillSource.SUBCONTRACT_CONTRACT.value==contract.getContractCategory())
		{
			etopBill.setBillType(BillType.OUTLAY.value);
		}
		
		etopBill.setDescription(model.itemDesc());		
		
		etopBill.setOverdueRate(new BigDecimal(this.etopThresholdService.getValue(contract.getRefParkId(), "overdue_rate")));
	
		
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(new BigDecimal(0));
		etopBill.setAccountRemission(new BigDecimal(0));
		etopBill.setOverdueFine(new BigDecimal(0));
		etopBill.setOverdueRemission(new BigDecimal(0));

		r=this.etopBillDao.add(etopBill);
		
		
		//TODO:update item's lastFeedate
		
		//contractItem.setLastFeeDate(Util.str2Date(model.getFeeEndDate()));
		
		//this.contractItemDao.updateContractItemlastFeeDate(contractItem);
		contract.setLastBalanceDate(Util.str2Date(model.getFeeEndDate()));
		
		this.contractDao.updateContract(contract);
		
		Gson gson = new Gson();
		
		String billStr=gson.toJson(etopBill);
		
		LoggerUtil.info(String.format("[generateBillByContractItemBillModel]bill:%s, and update contract's last balance date.", billStr));
		
//		if(contract.getContractCategory()==BillSource.LEASE_CONTRACT.value)
//		{
//			
//		}
		
		
		
		}
		catch(Exception e)
		{
			//System.out.println(e.toString());
			LoggerUtil.error(e);
		}
		
		
		return r;
	}

	@Override
	public ResultType generateBillByPark(String refParkId,String today) {

		//List<String> parkIds=new ArrayList<String>();
		ResultType result=null;
		
		int successResult=0;
		
		int errorResult=0;
		
		try
		{
			if(Util.isNullOrEmpty(refParkId))
			{
				
				LoggerUtil.warn("park id is null or empty.");
				
				return ResultType.getFail("park id is null or empty.");
			}				
			
			
			//先取回符合条件的合同列表
			//正常合同，最新结算日为空，或最新结算日<结束日
			//终止合同在终止时直接出帐
			List<Contract> contractList=this.contractDao.getContractBillListByPark(refParkId,today);
			
			//对每个合同进行分期判断，需要出帐的，直接生成帐单
			for(Contract contract:contractList)
			{
				Map<String,Integer> c=this.generateBillByContract(contract,today);
								
				successResult+=c.get("success");
				
				errorResult+=c.get("failed");				
				
			}
			
			if(errorResult>0)
			{
				LoggerUtil.info(String.format("园区合同帐单出错数量(%d)", errorResult));
			}
			
			Map<String,Integer> map=new HashMap<String, Integer>();
			
			map.put("success", successResult);
			
			map.put("failed", errorResult);
			
//			List<ContractItemBillModel> list=this.contractItemService.getContractItemBillModelByPark(refParkId,today);
			
			//System.out.println("园区ID:"+refParkId+",需要出帐的合同数量："+list.size());
			
//			for(ContractItemBillModel m:list)
//			{
//				result=result+this.generateBillByContractItemBillModel(m);
//			}			
			
//			String content=String.format("园区合同已出帐，请审核");
//				
//			this.remindService.remind(refParkId, Role.QX_YZSP, "合同出帐", content);	
					
			
			result=ResultType.getSuccess(map);
			
			return result;
					
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			
			LoggerUtil.error(e.toString());
			
			//出错后，发消息，若为空就不用发
		
//			String content=String.format("园区合同自动出帐失败，请管理员查看。");
//				
//			this.remindService.remind(refParkId, Role.QX_YQTX, "合同出帐", content);	

			return ResultType.getException();
			
		}
		
		
		
	}

	

	@Override
	public void generateAutoBill(String today) {


		long start=System.currentTimeMillis();
		
		
		LoggerUtil.info("manual bill contract job start...");
		
		List<Park> parkList=this.parkDao.getParkNameList();	
		
		int success=0;
		
		int failed=0;
		
		for(Park park:parkList)
		{
			ResultType result=this.generateBillByPark(park.getId(),Util.formatDate(new Date()));
			
			@SuppressWarnings("unchecked")
			Map<String,Integer> map=(Map<String,Integer>)result.getData();
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("success")>0 )
			{
				String content=String.format("园区(%s)合同已出帐(%d)，请审核。",park.getParkName(),map.get("success"));
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "合同出帐", content);
				
				success+=map.get("success");
			}
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("failed")>0)
			{
				String content=String.format("园区(%s)合同自动出帐部分失败，数量(%d)，请管理员查看。",park.getParkName(),map.get("failed"));
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				failed+=map.get("failed");
			}
			
			if(result.getStatus()==SysStatus.EXCEPTION.getStatus())
			{
				String content=String.format("园区(%s)合同自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
			}
			
		}
		
		
		
		
		
		
		LoggerUtil.info(String.format("manual bill contract job finished. spend time %d, success:%d,failed:%d,by:%s",
				System.currentTimeMillis()-start,success,failed,Util.getOpInfo().getOper()));
		
	}
	@Override
	public List<Integer> delContract(OpInfoBean op, Contract contract,
			int delType) {
		
		List<Integer> result=new ArrayList<Integer>();
		
		
		//拒绝删除，恢复状态		 
		if(delType==0)
		{
			contract.setContractStatus(Contract.ContractStatus.PARKER_ACCEPTED.value);
			
			contract.setTerminateDate(null);
			
			contract.setTerminateReason(null);
			
			contract.updatedAt=new Date();
			
			contract.updatedBy=op.getOper();
			
			this.contractDao.updateContract(contract);
			
			result.add(0);
			
			LoggerUtil.info(String.format("[delContract]delete contract refused:%s", contract.toString()));
			
			
		}else
		{
			contract.setContractStatus(Contract.ContractStatus.DELETED.value);
			
			contract.setTerminateDate(contract.getTerminateDate());
			
			contract.setTerminateReason(contract.getTerminateReason());
			
			contract.updatedAt=new Date();
			
			contract.updatedBy=op.getOper();
			
			this.contractDao.updateContract(contract);
			
			result.add(1);
			
			LoggerUtil.info(String.format("[delContract]delete contract accepted:%s", contract.toString()));
			
		}
		
		
		
		
		return result;
	}
	
	
	
	@Override
	public List<Integer> terminateContract(OpInfoBean op, Contract contract,int terminateType) {
		
		//Contract contract=this.contractDao.getContractById(id);
		
		//if(contract==null) return -1;
		
		String today=Util.formatDate(new Date());
		
		List<Integer> result=new ArrayList<Integer>();
		
		int billCount=0;
		
		
		LoggerUtil.info(String.format("[terminateContract]id[%s]starting...",contract.getId()));
		
		//拒绝终止，恢复
		if(terminateType==0)
		{
			contract.setContractStatus(Contract.ContractStatus.PARKER_ACCEPTED.value);
			
			contract.setTerminateDate(null);
			
			contract.setTerminateReason(null);
			
			contract.updatedAt=new Date();
			
			contract.updatedBy=op.getOper();
			
			this.contractDao.updateContract(contract);
			
			result.add(0);
			
			result.add(0);
			
			LoggerUtil.info(String.format("[terminateContract]terminate contract refused:%s", contract.toString()));
			
			return result;
		}
		
		
		
		List<Contract> list=new ArrayList<Contract>();
				
		
		if(contract.getContractCategory()==BillSource.LEASE_CONTRACT.value)
		{
			//只要取回有效的合同
			list=this.contractDao.getValidContractListByLeaseId(contract.getId(),today);
			
			//同时解绑
			//12.16先要判断结束日期是否已超过今日，不超过，先不解绑
			//不要解绑
//			if(contract.getTerminateDate().getTime() <= new Date().getTime())
//			{
//				this.etopFloorService.unbindRoom(contract.getRefRoomId());
//			}
			
			//0103终止后，立刻 生成押金帐单
			if(contract.getDeposit().compareTo(new BigDecimal(0))>0 && (contract.getDepositBillStatus() & 2)==0)
			{
				int b=this.generateDepositBill(contract, BillType.OUTLAY.value);
				
				if(b>0)
				{
					contract.setDepositBillStatus(b);
				}
			}
			
			
		}
				
		list.add(contract);
		

		for(Contract c:list){

			//服务合同不需要批量终止
			if(c.getContractCategory()==BillSource.SERVICE_CONTRACT.value)
			{
				continue;
			}
			
			int originalStatus=c.getContractStatus();
			
			c.setTerminateDate(contract.getTerminateDate());
			
			c.setTerminateReason(contract.getTerminateReason());
			
			c.setContractStatus(Contract.ContractStatus.TERMINATED.value);
			
			c.updatedAt=new Date();
			
			c.updatedBy=op.getOper();
			
			this.contractDao.updateContract(c);
			
			LoggerUtil.info(String.format("[terminateContract]terminate contract:%s", c.toString()));
			
			//正常合同，出帐
			if(originalStatus==Contract.ContractStatus.PARKER_ACCEPTED.value
					|| originalStatus==Contract.ContractStatus.TERMINATE_AUDITING.value)
			{
				billCount+=this.generateContractBillByTerminate(c,today);
			}
			
		};
		
		result.add(list.size());
		
		result.add(billCount);
		
		LoggerUtil.info(String.format("[terminateContract]finished. contract count:%d, bill count:%d",list.size(), billCount));
		
		return result;
	}

	/**
	 * 生成终止合同的帐单
	 * @param contract
	 * @param today
	 */
	private int generateContractBillByTerminate(Contract contract,String today)
	{
		int result=0;
		
		List<ContractItem> list=this.contractItemDao.getContractItemList(contract.getId());
		
		List<ContractItemBillModel> billModelList=new ArrayList<ContractItemBillModel>();
		
		
		for(ContractItem item:list)
		{
			ContractItemBillModel billModel=this.getContractItemBillModelByTerminate(contract,item, today);
			
			if(billModel!=null)
			{	
				billModelList.add(billModel);
			}
		}
		
		for(ContractItemBillModel model:billModelList)
		{
			int r = this.generateBillByContractItemBillModel(model);
			
			if(r==1)
			{
				result+=1;
			}
			
//			System.out.println(model.itemDesc());
		}
		
		LoggerUtil.info(String.format("[generateContractBillByTerminate]finished, result:%d", result));
		
		return result;
		
	}
	
	/**
	 * 生成终止合同的合同帐单项
	 * @param contract
	 * @param item
	 * @param today
	 * @return
	 */
	private ContractItemBillModel getContractItemBillModelByTerminate(Contract contract,ContractItem item,String today)
	{
		
		//当有最后结算日时，直接判断结算日是否>=项目结束日期，是，不用出
		if(contract.getLastBalanceDate()!=null && 
			Util.dateDiff(Util.formatDate(contract.getLastBalanceDate()),
					Util.formatDate(item.getEndDate()))>=0)
		{
			return null;
		}
		
		String lastBalanceDate="";
		
		String feeStartDate="";
		
		String feeEndDate="";
		
		String currPaymentDate=Util.increaseDate(today, item.getPaymentDate());
		
		String terminateDate=Util.formatDate(contract.getTerminateDate());
		
		//先取出最后结算日
		if(contract.getLastBalanceDate()==null)
		{
			//为空时，最后结算日是分期开始日前一天
			lastBalanceDate=Util.increaseDate(Util.formatDate(item.getStartDate()),-1);									
		}
		else
		{
			//最后结算日在开始日与结束日之间的分期项
			lastBalanceDate=Util.formatDate(contract.getLastBalanceDate());
			//若分期开始日>最后结算日，也设置为分期开始前一天
			if(Util.dateDiff(Util.formatDate(item.getStartDate()), lastBalanceDate)>0)
			{
				lastBalanceDate=Util.increaseDate(Util.formatDate(item.getStartDate()),-1);
			}
			
		}
		
		feeStartDate=Util.increaseDate(lastBalanceDate, 1);
		
		feeEndDate=terminateDate;
		
		//若终止日大于分期结束日，此分期计费截止即为结束日
		if(Util.dateDiff(terminateDate, Util.formatDate(item.getEndDate()))>0)
		{
			feeEndDate=Util.formatDate(item.getEndDate());
		}
		
		//TODO:若计费开始日期>计费结束日期.
		if(Util.dateDiff(feeStartDate,feeEndDate)>0)
		{
			return null;
		}
		
		//生成billModel
		ContractItemBillModel model=new ContractItemBillModel();
		
		model.setStartDate(Util.formatDate(item.getStartDate()));
		
		model.setEndDate(Util.formatDate(item.getEndDate()));
		
		
		model.setRefContractId(item.getRefContractId());
		
		model.setRefContractItemId(item.getId());
		
		model.setCurrBillDate(today);
		
		model.setFeeStartDate(feeStartDate);
		
		model.setFeeEndDate(feeEndDate);
		
		model.setDailyUnitPrice(item.getDailyUnitPrice());
		
		model.setMonthlyUnitPrice(item.getMonthlyUnitPrice());
		
		model.setArea(item.getContent());
		
		model.setPaymentDate(currPaymentDate);
		
		//生成bill时设置
		float amount=0;
				//item.getDailyUnitPrice()*(Util.dateDiff(feeDateEnd,feeDateStart)+1);
		
		model.setAmount(amount);
		
		LoggerUtil.info(String.format("[getContractItemBillModelByTerminate]model:%s", model.toString()));
		
		
		return model;
		//result.add(model);
		
	}
	
	
	@Override
	public Contract getLastActiveContractByRefRoomId(String refRoomId,int contractCategory) {
		
		return this.contractDao.getLastActiveContractByRoom(refRoomId, contractCategory);

	}

//	@Override
//	public int generateBillByContract(Contract contract) {
//		
//		
//		List<ContractItemBillModel> list=this.contractItemService.getContractItemBillModelByContract(contract);
//		
//		
//		//直接生成帐单
//		
//		
//		
//		
//		list.forEach(m->{
//			this.generateBillByContractItemBillModel(m);
//		});
//		
//		return 0;
//	}
	
	@Override
	public Map<String,Integer> generateBillByContract(Contract contract,String today) {
		
		Map<String,Integer> result=new HashMap<String, Integer>();
		try
		{
		
		
		int successResult=0;
		
		int errorResult=0;
				
		List<ContractItemBillModel> list=this.getContractItemBillModelByContract(contract,today);
		
		//直接生成帐单
				
		for(ContractItemBillModel m:list){
			
			int c=this.generateBillByContractItemBillModel(m);
			
			if(c>0)
			{
				successResult+=c;
			}
			
			if(c<0)
			{
				errorResult+=1;
			}
			
			
		}
			//System.out.println(m.itemDesc());
		
		result.put("success", successResult);
		
		result.put("failed", errorResult);
		
		}
		catch(Exception e)
		{
			//System.out.println(e.toString());
			LoggerUtil.error(e);
		}
		
		return result;
	}
	
	/**
	 * 判断此合同可以生成帐单的分期，并返回列表
	 * @param contract
	 * @param today
	 * @return
	 */
	private List<ContractItemBillModel> getContractItemBillModelByContract(Contract contract,String today)
	{
		List<ContractItem> list=this.contractItemDao.getContractItemList(contract.getId());
		
		List<ContractItemBillModel> result=new ArrayList<ContractItemBillModel>();
		
		
		for(ContractItem item:list)
		{
			ContractItemBillModel billModel=this.getContractItemBillModel(contract,item, today);
			
			if(billModel!=null)
			{
				result.add(billModel);
			}
		}
		
		
		return result;
		
	}
	
	/**
	 * 判断合同项是否需要出帐，是，返回ContractItemBillModel
	 * @param item
	 * @return
	 */
	private ContractItemBillModel getContractItemBillModel(Contract contract,ContractItem item,String today)
	{
	
		
		//最后一次出帐日
		String lastBillDateStr="";
		//最后一次结算日
		String lastBalanceDate="";
		
		//此次出帐日
		String currentBillDateStr=today;
				//Util.formatDate(new Date());
		
		//计费开始日
		String feeDateStart="";
		//计费截止日
		String feeDateEnd="";
		
		//缴费截止日
		String currPaymentDate="";
		
		//缴费金额
		float amount=0;
		
		boolean needbill=false;	
		
		//当有最后结算日时，直接判断结算日是否>=项目结束日期，是，不用出
		if(contract.getLastBalanceDate()!=null && 
			Util.dateDiff(Util.formatDate(contract.getLastBalanceDate()),
					Util.formatDate(item.getEndDate()))>=0)
		{
			return null;
		}
		
		
		//自定义出帐日
		if(item.getBillType()==0)
		{
			//上次计费截止日>＝项目结束日
//			if(contract.getLastBalanceDate()!=null && Util.formatDate(item.getEndDate()).equals(Util.formatDate(contract.getLastBalanceDate())))
//			{
//				return null;
//			}
			
			
			String billDates=item.getBillDates();
			
			
			
			
			if(!Util.isNullOrEmpty(billDates))
			{
				currentBillDateStr=Util.getBillDate(billDates, today);
										
				//本次计费截止日
				feeDateEnd=this.generateCurrentFeeDate(currentBillDateStr, item.getBillPeriod());
				
				//今天大于等于出帐日，可以出帐，
				//先取出上次结算日lastBalanceDate，若为空，则为项目开始日前一天
				//进一步判断，此次计费截止日<=lastBalanceDate,不出
				
					if(Util.dateDiff(today,currentBillDateStr)>=0)
					//Util.dateDiff(feeDateEnd,Util.formatDate(item.getEndDate()))>=0
					{
						//先取出最后结算日
						if(contract.getLastBalanceDate()==null)
						{
							//为空时，最后结算日是分期开始日前一天
							lastBalanceDate=Util.increaseDate(Util.formatDate(contract.getContractStartDate()),-1);									
						}
						else
						{
							//最后结算日在开始日与结束日之间的分期项
							lastBalanceDate=Util.formatDate(contract.getLastBalanceDate());
							//若分期开始日>最后结算日，也设置为分期开始前一天
							if(Util.dateDiff(Util.formatDate(item.getStartDate()), lastBalanceDate)>0)
							{
								lastBalanceDate=Util.increaseDate(Util.formatDate(item.getStartDate()),-1);
							}
							
						}
						//本次计费截止日＝上次结算日，不需要生成
						
						//最后结算日lastBalanceDate>=此次计费截止日feeDateEnd,不出
//						if(Util.dateDiff(lastBalanceDate,feeDateEnd)>=-1)
//						{
//							continue;
//						}
						
						//计费开始日＝最后结算日+1
						feeDateStart=Util.increaseDate(lastBalanceDate,1);
					
						//比较计费截止日与分期结束日期
						int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));
		
						//若计费截止日已大于等于分期结束日，截止日即为分期结束日
						if(dateDiff>=0)
						{
							feeDateEnd=Util.formatDate(item.getEndDate());
						}
					
						

						//currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
						currPaymentDate=Util.increaseDate(Util.formatDate(new Date()), item.getPaymentDate());
						
						//TODO:若计费开始日期<=计费结束日期.
						if(Util.dateDiff(feeDateEnd, feeDateStart)>=0)
						{
							needbill=true;
						}
						
					
				}
				
			}
		}
		//统一规则
		if(item.getBillType()==1 && !Util.isNullOrEmpty(item.getBillDate()))
		{
			//出帐日
			String billDateStr=item.getBillDate();
						
			//出帐间隔月份
			int billIntervalMonth=Integer.valueOf(billDateStr.substring(0, billDateStr.indexOf("-")));
			//出帐日
			int billDate=Integer.valueOf(billDateStr.substring(billDateStr.indexOf("-")+1));
			
			//计费周期			
			String billPeriodStr=item.getBillPeriod();
			
			
			
			

			//首次，feeDateStart=startDate
			if(contract.getLastBalanceDate()==null)
			{
				//lastBillDateStr=Util.formatDate(item.getStartDate());
				
				//本次计费开始
				feeDateStart=Util.formatDate(item.getStartDate());
				//最后结算日
				lastBalanceDate=Util.increaseDate(feeDateStart, -1);
				//通过最后结算日，推出最后出帐日
				lastBillDateStr=this.getBillDateByFeeEndDate(lastBalanceDate, billDateStr, billPeriodStr);
				
			}
			else
			{
			
				//最后结算日
				lastBalanceDate=Util.formatDate(contract.getLastBalanceDate());
				
				//最后结算日<项目开始日
				if(Util.dateDiff(Util.formatDate(item.getStartDate()), lastBalanceDate)>0)
				{
					lastBalanceDate=Util.increaseDate(Util.formatDate(item.getStartDate()), -1);
				}
				
				//上次结算日加一天
				feeDateStart=Util.increaseDate(lastBalanceDate,1);
							
				
				//从上次结算截止日倒推上次出帐日
				lastBillDateStr=this.getBillDateByFeeEndDate(lastBalanceDate, billDateStr, billPeriodStr);
						
			}
				
//			//如果计费开始日已大于分期结束日，即本分期出帐完成，略过
//			if(Util.dateDiff(feeDateStart, Util.formatDate(item.getEndDate()))>0)
//			{
//				return null;
//			}				
			
			//生成这次出帐日
			currentBillDateStr=Util.getLastCurrentBillDate(lastBillDateStr, billDate, billIntervalMonth,today);
			
			//当取回的最后一次有效出帐日＝＝上次出帐日，不生成
			if(lastBillDateStr.equals(currentBillDateStr))
			{
				return null;
			}
			
			
			//计费截止日
			feeDateEnd=this.generateCurrentFeeDate(currentBillDateStr, billPeriodStr);	
			
			//假设今天为出帐日，推算出计费结束日
			//String tempFeeDateEnd=this.generateCurrentFeeDate(today, billPeriodStr);
			
			//若今天大于等于出帐日，出帐，今天 大于出帐日，即自动生成失败，需要再生成
			//或者假设计费截止日大于等于项目结束日期，即，本分期结束，也出帐
			//currentBillDateStr.equals(today) || 
			if(Util.dateDiff(today,currentBillDateStr)>=0)
					//|| Util.dateDiff(tempFeeDateEnd,Util.formatDate(item.getEndDate()))>=0)
			{
								
				//比较计费截止日与分期结束日期
				int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));

				//若计费截止日已大于等于分期结束日
				if(dateDiff>=0)
				{
					feeDateEnd=Util.formatDate(item.getEndDate());
				}
			
				//缴费日期应该从当前创建日期起算
		
				//currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
				currPaymentDate=Util.increaseDate(Util.formatDate(new Date()), item.getPaymentDate());
									
				//amount=item.getDailyUnitPrice()*Util.dateDiff(feeDateEnd,feeDateStart);
				
				if(Util.dateDiff(feeDateEnd, feeDateStart)>=0)
				{
					needbill=true;
				}
				
			}
						
			
		
		}
		
		
		
		if(needbill)
		{
			ContractItemBillModel model=new ContractItemBillModel();
			
			model.setStartDate(Util.formatDate(item.getStartDate()));
			
			model.setEndDate(Util.formatDate(item.getEndDate()));
			
			
			model.setRefContractId(item.getRefContractId());
			
			model.setRefContractItemId(item.getId());
			
			model.setCurrBillDate(currentBillDateStr);
			
			model.setFeeStartDate(feeDateStart);
			
			model.setFeeEndDate(feeDateEnd);
			
			model.setDailyUnitPrice(item.getDailyUnitPrice());
			
			model.setMonthlyUnitPrice(item.getMonthlyUnitPrice());
			
			model.setArea(item.getContent());
			
			model.setPaymentDate(currPaymentDate);
			
			model.setBalanceMonthly(item.getBalanceMonthly());
			
			//生成bill时设置
			amount=0;
					//item.getDailyUnitPrice()*(Util.dateDiff(feeDateEnd,feeDateStart)+1);
			
			model.setAmount(amount);
			
			return model;
			//result.add(model);					
			
		}
		
		
		return null;
		
	}
	
	
	
	
	
	
	/**
	 * 生成当前计费日期，即出帐日第几个几号
	 * @param currBillDate
	 * @param billPeriod
	 * @return
	 */
	private String generateCurrentFeeDate(String currBillDate,String billPeriod)
	{
		String result="";
		
		String[] arr=currBillDate.split("-");
		
		int y=Integer.valueOf(arr[0]);
		
		int m=Integer.valueOf(arr[1]);
		
		int d=Integer.valueOf(arr[2]);
		
		String[] parr=billPeriod.split("-");
		
		int pm=Integer.valueOf(parr[0]);
		
		int pd=Integer.valueOf(parr[1]);
		
		//判断出帐日与计费日
		//出帐日小于计费日，或者计费日为月末，则下一次计费日为当月开始计算，故需－1
//		if(d<pd || pd==0)
//		{
//			m=m+pm-1;
//		}else
//		{
//			m=m+pm;
//		}
		
		m=m+pm;
		
		if(m>12)
		{			
			int ym=(int)Math.floor(m/12);
			
			y+=ym;
			
			m=m-12*ym;
		}		
		
		
		if(pd==0)
		{
			m+=1;

			if(m>12)
			{
				y+=1;
				
				m-=12;
			}	
			
			String tmp=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", 1);
			
			result=Util.increaseDate(tmp,-1);
		}
		else
		{
			
			result=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", pd);
		}				
		
		return result;
	}
	
	
	
	
	/**
	 * 根据结算截止日、计费周期，得出出帐日
	 * @param feeEndDate
	 * @param billPeriod
	 * @return
	 */
	private String getBillDateByFeeEndDate(String feeEndDate,String billDate,String billPeriod)
	{
		String result="";
		
		String[] arr=feeEndDate.split("-");
		
		int y=Integer.valueOf(arr[0]);
		
		int m=Integer.valueOf(arr[1]);
		
		int d=Integer.valueOf(arr[2]);
		
		String[] barr=billDate.trim().split("-");
		
		int bm=Integer.valueOf(barr[0]);
		
		int bd=Integer.valueOf(barr[1]);
		
		String[] parr=billPeriod.trim().split("-");
		
		int pm=Integer.valueOf(parr[0]);
		
		int pd=Integer.valueOf(parr[1]);
		
		int im=0;
		
//		if((pd>bd || pd==0) && d>=pd)
//		{
//			//m=m-pm+1;
//			im=im-pm+1;
//		}
//		else
//		{
//			//m=m-pm;
//			im=im-pm;
//		}
		im=im-pm;
//		if(m<=0)
//		{
//			int ym=(int) Math.abs(Math.ceil(m/12));
//			
//			y=y-ym;
//			
//			m=m+12*ym;
//		}
		
		Calendar cal = Calendar.getInstance();  
        cal.setTime(Util.str2Date(y+"-"+String.format("%02d", m)+"-"+String.format("%02d", bd)));  
        cal.add(Calendar.MONTH, im);  
		
        return Util.formatDate(cal.getTime());
		
		
		//result=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", bd);
		
		
		
		
		//return result;
	}
	
	
	
	
	
	
	

	@Override
	public EtopPage<ContractListModel> ListbyCompanyId(
			Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit);
		return new EtopPage<ContractListModel>(contractDao.ListbyCompanyId(condition));

	}

	@Override
	public ContractModel getContractByNo(String contractNo) {

		return contractDao.getContractByNo(contractNo);
	}

	@Override
	public int auditContractByFinance(OpInfoBean op, String refContractId,int auditType) {
		
		int result=-1;
		
		Contract entity=this.contractDao.getContractById(refContractId);
		
		if(entity==null) return -2;
		
		EtopCompanyIntention intention = etopCompanyIntentionService.getCompIntentionInfoById(entity.getRefCompanyId());
		
		//if(intention==null) return -3;
		
		
		if(auditType==1)
		{
		
			//entity.setAuditStatus(ContractAuditStatus.FINANCE_ACCEPT.value);
			
			entity.setContractStatus(Contract.ContractStatus.PARKER_AUDITING.value);
			
			//12.22一通过合同，先绑定room
			if(entity.getContractCategory()==BillSource.LEASE_CONTRACT.value)
			{
				//bind room
				
				this.etopFloorService.bindRoom(entity.getRefRoomId(), entity.getRefCompanyId(), entity.getId());
				
			}
			
			//状态设置为审核中，园长审核不需要修改
			//intention.setReviewStatus(1);
			
		}
		else
		{
			//entity.setAuditStatus(ContractAuditStatus.FINANCE_REFUSE.value);
			
			entity.setContractStatus(Contract.ContractStatus.FINANCE_REFUSED.value);
			
			//合同设置为无效
			//entity.setContractStatus(-1);
			
			//1219租赁合同不通过，先解绑房间
			if(entity.getContractCategory()==BillSource.LEASE_CONTRACT.value)
			{
				this.etopFloorService.unbindRoom(entity.getRefRoomId());
			}
			//设置意向公司状态，财务不同意	
			if(intention!=null)
			{
				intention.setReviewStatus(3);
				
				this.etopCompanyIntentionService.updateById(intention);
			}
			
			
		}
		
		
		entity.setUpdatedAt(new Date());
		
		entity.setUpdatedBy(op.getOper());
		
		result=this.contractDao.updateContract(entity);
		
		LoggerUtil.info(String.format("[auditContractByFinance]contract:%s, approved:bind room ,refused:unbind room and set intention company's status to 3 if exists.", entity.toString()));
		
		return result;
	}

	@Override
	public int auditContractByParker(OpInfoBean op, String refContractId,int auditType) throws Exception {
		
		int result=0;

		Contract entity=this.contractDao.getContractById(refContractId);
		
		if(entity==null) return -2;
		
		EtopCompanyIntention intention = etopCompanyIntentionService.getCompIntentionInfoById(entity.getRefCompanyId());
		
		//if(intention==null) return -3;
		LoggerUtil.info(String.format("[auditContractByParker]id[%s]starting...", refContractId));
		//修改审核状态
		
		if(auditType==1)
		{
			
//			entity.setAuditStatus(ContractStatus.CHECK_ACCEPT.value);
			
			entity.setContractStatus(ContractStatus.PARKER_ACCEPTED.value);
			
			
			//园长审核完成后，
			//如果是租赁，同时生成押金帐单
			if(entity.getContractCategory()==BillSource.LEASE_CONTRACT.value)
			{	
							
				//同时转正公司，如果存在	
				if(intention!=null)
				{
					//添加正式公司信息
					etopCompanyService.addInfo(intention);

					//添加经营信息
					etopCompanyBusinessService.addInfo(intention.getId());

					//删除意向公司信息
					
					List<String> ids=new ArrayList<String>();
					
					ids.add(intention.getId());
					
					intention.setIds(ids);
					
					etopCompanyIntentionService.deleteById(intention);
					
					LoggerUtil.info(String.format("[auditContractByParker]parker accepted. move intention company[%s] to company.", intention.getId()));
					
				}
				
				int dbillStatus=0;
				
				//生成押金收入帐单
				//押金大于0，且未生成收入帐单
				if(entity.getDeposit().compareTo(new BigDecimal(0))>0 && (entity.getDepositBillStatus() & 1)==0)
				{

					dbillStatus=this.generateDepositBill(entity, BillType.INCOME.value);
					
				}
				
				if(dbillStatus>0)
				{
					entity.setDepositBillStatus(dbillStatus);
				}
				
			}
			
			//如果是能源合同，需要将合同中的记录录进energy_cost，录入日期为结算日期+1
			ContractEnergy ce=this.contractEnergyDao.getContractEnergyByRefContractId(refContractId);
			
			EtopFloorRoom room=null;
			//外包合同无房间
			if(!Util.isNullOrEmpty(entity.getRefRoomId()))
			{
				room=this.etopFloorService.getRoomInfo(entity.getRefRoomId());
			}
			
			//不为空，且为有效数据
			//2017.5.12录入能源之前，先要查找是否已存在当天数据。若存在，则判断数值是否相等，若相等，不操作。若不相等，则审核不通过
			if(ce!=null && ce.getPowerRecordDate()!=null && room !=null)
			{			
				List<EnergyCost> ecs=new ArrayList<EnergyCost>();
				
				/*EnergyCost ec=new EnergyCost();
				
				ec.setCreatedAt(new Date());
				
				ec.setCreatedBy(op.getOper());
				
				ec.setUpdatedAt(new Date());
				
				ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
				
				ec.setIsBilled(1);
				
				ec.setRefItemId(entity.getRefRoomId());
				
				ec.setRefBuildingId(room.getRefFloorId());*/
				
				
				String recordDateStr=Util.increaseDate(Util.formatDate(ce.getPowerRecordDate()),1);
				
				Criteria c=new Criteria();
				
				c.put("recordDate", recordDateStr);
				
				c.put("refItemId", entity.getRefRoomId());
				
				c.put("energyType", EnergyCost.EnergyType.POWER.value);
								
				EnergyCost oec=this.energyCostDao.getEnergyCost(c);
				
				if(oec!=null)
				{
					if (oec.getRecord()!=ce.getPowerRecord())
					{
						throw new ArithmeticException(String.format("(%s)电费读数已存在，且不同于合同读数(%f)",recordDateStr,oec.getRecord()));
					}
				}
				else
				{
				
				
				
										
				//power
				/*ec.setEnergyType(EnergyCost.EnergyType.POWER.value);
				
				ec.setRecord(ce.getPowerRecord());
				
				ec.setRecordDate(Util.str2Date(recordDateStr));
				
				ec.setId(UUID.randomUUID().toString());
								
				//this.energyCostDao.createEnergyCost(ec);
				
				ecs.add(ec);	*/
				
					ecs=this.setRoomEnergyCostList(entity, room, EnergyCost.EnergyType.POWER.value, recordDateStr, ce.getPowerRecord(), op);	
						
					this.energyCostService.createEnergyCostList(op, ecs, EnergyCost.EnergyCategory.ROOM.value, EnergyCost.EnergyType.POWER.value);
					
					LoggerUtil.info(String.format("[auditContractByParker]enter energy:%s", ecs.toString()));
				
				}
				
				
				/*ecs=new ArrayList<EnergyCost>();
				
				ec=new EnergyCost();
				
				ec.setCreatedAt(new Date());
				
				ec.setCreatedBy(op.getOper());
				
				ec.setUpdatedAt(new Date());
				
				ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
				
				ec.setIsBilled(1);
				
				ec.setRefItemId(entity.getRefRoomId());
				
				ec.setRefBuildingId(room.getRefFloorId());
				//water
				ec.setEnergyType(EnergyCost.EnergyType.WATER.value);
				
				ec.setRecord(ce.getWaterRecord());
				
				ec.setRecordDate(Util.str2Date(Util.increaseDate(Util.formatDate(ce.getWaterRecordDate()),1)));
				
				ec.setId(UUID.randomUUID().toString());
											
				//this.energyCostDao.createEnergyCost(ec);
				
				ecs.add(ec);*/
				
				c=new Criteria();
				
				c.put("recordDate", recordDateStr);
				
				c.put("refItemId", entity.getRefRoomId());
				
				c.put("energyType", EnergyCost.EnergyType.WATER.value);
								
				oec=this.energyCostDao.getEnergyCost(c);
				
				if(oec!=null)
				{
					if (oec.getRecord()!=ce.getWaterRecord())
					{
						throw new ArithmeticException(String.format("(%s)水费读数已存在，且不同于合同读数(%f)",recordDateStr,oec.getRecord()));
					}
				}else
				{
				
				
				ecs=this.setRoomEnergyCostList(entity, room, EnergyCost.EnergyType.WATER.value, recordDateStr, ce.getWaterRecord(), op);
				
				
				this.energyCostService.createEnergyCostList(op, ecs, EnergyCost.EnergyCategory.ROOM.value, EnergyCost.EnergyType.WATER.value);
				
				LoggerUtil.info(String.format("[auditContractByParker]enter energy:%s", ecs.toString()));
				}
				
				/*ecs=new ArrayList<EnergyCost>();
				
				ec=new EnergyCost();
				
				ec.setCreatedAt(new Date());
				
				ec.setCreatedBy(op.getOper());
				
				ec.setUpdatedAt(new Date());
				
				ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
				
				ec.setIsBilled(1);
				
				ec.setRefItemId(entity.getRefRoomId());
				
				ec.setRefBuildingId(room.getRefFloorId());
				//gas		
				
				ec.setEnergyType(EnergyCost.EnergyType.GAS.value);
				
				ec.setRecord(ce.getGasRecord());
				
				ec.setRecordDate(Util.str2Date(Util.increaseDate(Util.formatDate(ce.getGasRecordDate()),1)));
				
				ec.setId(UUID.randomUUID().toString());
				
//this.energyCostDao.createEnergyCost(ec);
				
				ecs.add(ec);*/
				
				c=new Criteria();
				
				c.put("recordDate", recordDateStr);
				
				c.put("refItemId", entity.getRefRoomId());
				
				c.put("energyType", EnergyCost.EnergyType.GAS.value);
								
				oec=this.energyCostDao.getEnergyCost(c);
				
				if(oec!=null)
				{
					if (oec.getRecord()!=ce.getGasRecord())
					{
						throw new ArithmeticException(String.format("(%s)燃气读数已存在，且不同于合同读数(%f)",recordDateStr,oec.getRecord()));
					}
				}else
				{
				
				
				ecs=this.setRoomEnergyCostList(entity, room, EnergyCost.EnergyType.GAS.value, recordDateStr, ce.getGasRecord(), op);
				
				
				this.energyCostService.createEnergyCostList(op, ecs, EnergyCost.EnergyCategory.ROOM.value, EnergyCost.EnergyType.GAS.value);
				
				LoggerUtil.info(String.format("[auditContractByParker]enter energy:%s", ecs.toString()));
				
				}
				
				/*ecs=new ArrayList<EnergyCost>();
				
				ec=new EnergyCost();
				
				ec.setCreatedAt(new Date());
				
				ec.setCreatedBy(op.getOper());
				
				ec.setUpdatedAt(new Date());
				
				ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
				
				ec.setIsBilled(1);
				
				ec.setRefItemId(entity.getRefRoomId());
				
				ec.setRefBuildingId(room.getRefFloorId());
				//ac
								
				ec.setEnergyType(EnergyCost.EnergyType.AC.value);
				
				ec.setRecord(ce.getAcRecord());
								
				ec.setRecordDate(Util.str2Date(Util.increaseDate(Util.formatDate(ce.getAcRecordDate()),1)));
				
				ec.setId(UUID.randomUUID().toString());
				
				
				//this.energyCostDao.createEnergyCost(ec);
				
				ecs.add(ec);*/
				
				c=new Criteria();
				
				c.put("recordDate", recordDateStr);
				
				c.put("refItemId", entity.getRefRoomId());
				
				c.put("energyType", EnergyCost.EnergyType.AC.value);
								
				oec=this.energyCostDao.getEnergyCost(c);
				
				if(oec!=null)
				{
					if (oec.getRecord()!=ce.getAcRecord())
					{
						throw new ArithmeticException(String.format("(%s)空调读数已存在，且不同于合同读数(%f)",recordDateStr,oec.getRecord()));
					}
				}else
				{
				
				
				ecs=this.setRoomEnergyCostList(entity, room, EnergyCost.EnergyType.AC.value, recordDateStr, ce.getAcRecord(), op);
				
				
				
				this.energyCostService.createEnergyCostList(op, ecs, EnergyCost.EnergyCategory.ROOM.value, EnergyCost.EnergyType.AC.value);
				
				LoggerUtil.info(String.format("[auditContractByParker]enter energy:%s", ecs.toString()));				
				
				}
			}
				
			
		}
		else
		{
			
//			entity.setAuditStatus(ContractAuditStatus.CHECK_REFUSE.value);
			//合同设置为无效
			entity.setContractStatus(ContractStatus.PARKER_REFUSED.value);
			
			//1219：如果租赁合同不通过，先解绑房间
			if(entity.getContractCategory()==BillSource.LEASE_CONTRACT.value)
			{
				this.etopFloorService.unbindRoom(entity.getRefRoomId());
			}
			//TODO:设置意向公司状态,园长不通过
			if(intention!=null)
			{
				intention.setReviewStatus(2);
				
				this.etopCompanyIntentionService.updateById(intention);
				
				LoggerUtil.info(String.format("[auditContractByParker]parker refused, set intention company[%s]'s status to 2", intention.getId()));
			}
	
		}
		
		entity.setUpdatedAt(new Date());
		
		entity.setUpdatedBy(op.getOper());
		
		result=this.contractDao.updateContract(entity);

		LoggerUtil.info(String.format("[auditContractByParker]finished. contract:%s", entity.toString()));
		
		return result;
	}

	/**
	 * 生成房间能源列表，专为审核时添加能源用
	 * @param c
	 * @param room
	 * @param energyType
	 * @param recordDate
	 * @param record
	 * @param oper
	 * @return
	 */
	private List<EnergyCost> setRoomEnergyCostList(Contract c,EtopFloorRoom room,int energyType,String recordDate,double record,OpInfoBean oper)
	{
		List<EnergyCost> ecs=new ArrayList<EnergyCost>();
		
		EnergyCost ec=new EnergyCost();
		
		ec.setCreatedAt(new Date());
		
		ec.setCreatedBy(oper.getOper());
		
		ec.setUpdatedAt(new Date());
		
		ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
		
		ec.setIsBilled(1);
		
		ec.setRefItemId(c.getRefRoomId());
		
		ec.setRefBuildingId(room.getRefFloorId());
		
		ec.setEnergyType(energyType);
		
		ec.setRecord(record);
		
		ec.setRecordDate(Util.str2Date(recordDate));
		
		ec.setId(UUID.randomUUID().toString());
						
		//this.energyCostDao.createEnergyCost(ec);
		
		ecs.add(ec);	
		
		return ecs;
		
		
	}
	
	
	
	/**
	 * 生成押金账单
	 * @param entity
	 * @param billType
	 * @return
	 */
	private int generateDepositBill(Contract entity,int billType)
	{
		Park park=this.parkDao.getParkInfo(entity.getRefParkId());
		
		Date endTime=entity.getContractEndDate();
		
		if(entity.getContractStatus()==Contract.ContractStatus.TERMINATED.value)
		{
			endTime=entity.getTerminateDate();
		}
		
		
		EtopBill etopBill=new EtopBill();
		
		etopBill.setParkId(entity.getRefParkId());
		
		etopBill.setCompanyId(entity.getRefCompanyId());
		
		etopBill.setBillId(etopSequenceService.getFormatId(park.getParkCode(), "zd"));
		
		
		etopBill.setStartTime(entity.getContractStartDate());
		
		etopBill.setEndTime(endTime);
		
		etopBill.setBillSource(BillSource.UNKNOWN.value);
							
		etopBill.setBillNoOut(entity.getContractNo());//null
							
		etopBill.setAmount(entity.getDeposit());
		
		etopBill.setDeadline(DateUtil.toTodayNight(new Date()));
		
		etopBill.setBillType(billType);
		
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		
		etopBill.setAuditStatus(EtopBill.AuditStatus.UNCHECK.value);
		
		etopBill.setAuditLevel(2);
		
		etopBill.setCompanyId(entity.getRefCompanyId());
		
		etopBill.setCompanyName(entity.getCompanyName());
						
		String desc="";
		
		int billStatus=0;
		
		if(BillType.INCOME.value==billType)
		{
			desc=String.format("租赁合同押金\r\n合同编号：%s,开始日期：%s,结束日期：%s,房间号：%s", 
				entity.contractNo,Util.formatDate(entity.getContractStartDate()),
				Util.formatDate(entity.getContractEndDate()),entity.getRoom());
			
			int outlay=entity.getDepositBillStatus() & 2;
			
			billStatus=outlay+1;
			
		}
		else
		{
			desc=String.format("退还租赁合同押金\r\n合同编号：%s,开始日期：%s,结束日期：%s,房间号：%s", 
					entity.contractNo,Util.formatDate(entity.getContractStartDate()),
					Util.formatDate(endTime),entity.getRoom());
			
			int income=entity.getDepositBillStatus() & 1;
			
			billStatus=2+income;
			
		}
		
		etopBill.setDescription(desc);		
		
		etopBill.setOverdueRate(new BigDecimal(0));
	
		
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(new BigDecimal(0));
		etopBill.setAccountRemission(new BigDecimal(0));
		etopBill.setOverdueFine(new BigDecimal(0));
		etopBill.setOverdueRemission(new BigDecimal(0));

		int r=this.etopBillDao.add(etopBill);
		
		if(r>0)
		{
			String content=String.format("园区(%s)公司(%s)已出押金帐单，请审核。",park.getParkName(),entity.getCompanyName());
		
			this.remindService.remind(park.getId(), Role.QX_YZSP, "合同出帐", content);
		}
		else
		{
			billStatus=0;
		}
		
		Gson gson = new Gson();
		
		String billStr=gson.toJson(etopBill);
		
		LoggerUtil.info(String.format("[generateDepositBill]bill:%s, and return  contract's deposit status:%d.", billStr,billStatus));
		
		
		
		//同时更新deposit_bill_status
		
//		entity=this.contractDao.getContractById(entity.getId());
//		
//		if(entity!=null)
//		{
//			entity.setDepositBillStatus(billStatus);
//			
//			this.contractDao.updateContract(entity);
//		}
		
		
		return billStatus;
	}
	
	
	@Override
	public List<Contract> getContractByCompanyAndCategoryAndRoom(String refCompanyId,String refRoomId,
			int contractCategory ,String todayStr) {
		 //|| Util.isNullOrEmpty(refRoomId)
		if(Util.isNullOrEmpty(refCompanyId))
		{
			return null;
		}
		
		return this.contractDao.getContractByCompanyAndCategoryAndRoom(refCompanyId,refRoomId, contractCategory,todayStr);
	}

	@Override
	public Contract query(Criteria criteria) {
		
		return this.contractDao.query(criteria);
	}

	@Override
	public List<Contract> getActiveContractsByCompanyAndCategory(
			String refCompanyId, int contractCategory, String todayStr) {
		
		return this.contractDao.getActiveContractsByCompanyAndCategory(refCompanyId, contractCategory, todayStr);
	}

	@Override
	public List<Contract> queryLeaseListByCompanyAndRoom(String refCompanyId,
			String refRoomId, int contractCategory, String contractStartDate,
			String contractEndDate) {
		
		Criteria c=new Criteria();
		
		//c.put("refCompanyId", refCompanyId);
		
		c.put("refRoomId", refRoomId);
		
		c.put("contractCategory", contractCategory);
		
		c.put("contractStartDate", contractStartDate);
		
		c.put("contractEndDate", contractEndDate);
		
		
		return this.contractDao.queryLeaseListByCompanyAndRoom(c);
	}

	@Override
	public List<Contract> getOtherContractByLease(List<String> ids, int category) {
		
		return this.contractDao.getOtherContractByLease(ids, category);
	}

	@Override
	public void generateManualDepositBill(String today) {
		
		long start=System.currentTimeMillis();
		
		
		LoggerUtil.info("manual deposit bill job start...");
		
		List<Park> parkList=this.parkDao.getParkNameList();	
		
		int success=0;
		
		int failed=0;
		
		for(Park park:parkList)
		{
			ResultType result=this.generateDepositBillByPark(park.getId(),Util.formatDate(new Date()));
			
			@SuppressWarnings("unchecked")
			Map<String,Integer> map=(Map<String,Integer>)result.getData();
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("success")>0 )
			{
				String content=String.format("园区(%s)合同已出押金帐单(%d)，请审核。",park.getParkName(),map.get("success"));
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "合同出帐", content);
				
				success+=map.get("success");
			}
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("failed")>0)
			{
				String content=String.format("园区(%s)合同自动出押金帐部分失败，数量(%d)，请管理员查看。",park.getParkName(),map.get("failed"));
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				failed+=map.get("failed");
			}
			
			if(result.getStatus()==SysStatus.EXCEPTION.getStatus())
			{
				String content=String.format("园区(%s)合同自动出押金帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
			}
			
		}
		
		
		
		
		
		
		LoggerUtil.info(String.format("manual deposit bill finished. spend time %d, success:%d,failed:%d,by:%s",
				System.currentTimeMillis()-start,success,failed,""));
		//Util.getOpInfo().getOper()
		
		
	}

	@Override
	public ResultType generateDepositBillByPark(String refParkId, String today) {

		//List<String> parkIds=new ArrayList<String>();
				ResultType result=null;
				
				int successResult=0;
				
				int errorResult=0;
				
				try
				{
					if(Util.isNullOrEmpty(refParkId))
					{
						
						LoggerUtil.warn("park id is null or empty.");
						
						return ResultType.getFail("park id is null or empty.");
					}				
					
					
					//先取回符合条件的合同列表
					//正常合同，开始日期>=今天&&结束日期<=今天,开始日期>今天
					//终止合同,终止日期<=今天
					List<Contract> contractList=this.contractDao.getContractDepositByPark(refParkId,today);
					
					//对每个合同进行判断，直接生成帐单
					for(Contract c:contractList)
					{
						int billType=BillType.INCOME.value;
						
						if(c.getContractStatus()==Contract.ContractStatus.PARKER_ACCEPTED.value)
						{
							//出收入账
							if(c.getDepositBillStatus()==0)
							{
								billType=BillType.INCOME.value;
							}
							//出支出账
							if(c.getDepositBillStatus()==1)
							{
								billType=BillType.OUTLAY.value;
							}
						}
						
						//出支出账
						if(c.getContractStatus()==Contract.ContractStatus.TERMINATED.value)
						{
							billType=BillType.OUTLAY.value;
						}
						
						int dbs=this.generateDepositBill(c, billType);
										
						if(dbs>0)
						{
							successResult+=1;
							
							c.setDepositBillStatus(dbs);
							
							this.contractDao.updateContract(c);
						}else
						{
							errorResult+=1;				
						}
					}
					
					if(errorResult>0)
					{
						LoggerUtil.info(String.format("园区合同押金帐单出错数量(%d)", errorResult));
					}
					
					Map<String,Integer> map=new HashMap<String, Integer>();
					
					map.put("success", successResult);
					
					map.put("failed", errorResult);
					
//					List<ContractItemBillModel> list=this.contractItemService.getContractItemBillModelByPark(refParkId,today);
					
					//System.out.println("园区ID:"+refParkId+",需要出帐的合同数量："+list.size());
					
//					for(ContractItemBillModel m:list)
//					{
//						result=result+this.generateBillByContractItemBillModel(m);
//					}			
					
//					String content=String.format("园区合同已出帐，请审核");
//						
//					this.remindService.remind(refParkId, Role.QX_YZSP, "合同出帐", content);	
							
					
					result=ResultType.getSuccess(map);
					
					return result;
							
				}
				catch(Exception e)
				{
					//e.printStackTrace();
					
					LoggerUtil.error(e.toString());
					
					//出错后，发消息，若为空就不用发
				
//					String content=String.format("园区合同自动出帐失败，请管理员查看。");
//						
//					this.remindService.remind(refParkId, Role.QX_YQTX, "合同出帐", content);	

					return ResultType.getException();
					
				}
				
		
		
	}







}
