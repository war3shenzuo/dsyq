package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.ContractItem;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ContractItemDao;
import com.etop.management.service.ContractItemService;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContractItemServiceImpl implements ContractItemService {

	@Resource
	ContractItemDao contractItemDao; 
	
	@Resource
	ContractDao contractDao;
	
	@Override
	public String save(OpInfoBean op, ContractItem entity) {
		
		String result=null;
		
		if(Util.isNullOrEmpty(entity.getId()))
		{
			entity.setId(UUID.randomUUID().toString());
			
			entity.createdAt=new Date();
			
			entity.updatedAt=new Date();
			
			entity.createdBy=op.getOper();
			
			entity.updatedBy=op.getOper();
			
			//entity.setLastBillDate(entity.getStartDate());
			
			this.contractItemDao.createContractItem(entity);
			
			result=entity.getId();
			
			
		}else
		{
			entity.updatedAt=new Date();
			
			entity.updatedBy=op.getOper();
			
			this.contractItemDao.updateContractItem(entity);
			
			result=entity.getId();
		}		
					
		this.contractItemDao.updateContractItemBillRule(entity);
		
		LoggerUtil.info(String.format("[contractItem.save]%s, and update other item's bill rule.", entity.toString()));
		
		return result;
	}

	@Override
	public int remove(OpInfoBean op, String id) {
		
		LoggerUtil.info(String.format("[contractItem.remove]id:%s,by %s.", id,op.getOper()));
		
		return this.contractItemDao.deleteContractItem(id);
	}

	@Override
	public List<ContractItem> getContractItemList(String contractId) {
		
		return this.contractItemDao.getContractItemList(contractId);
	}

	@Override
	public ContractItem getValueById(String id) {

		return this.contractItemDao.getContractItemById(id);
	}

//	@Override
//	public int updateContractItemBillRule(ContractItem entity) {
//				
//		return this.contractItemDao.updateContractItemBillRule(entity);
//	}

	@Override
	public ContractItem getOneOfContractItem(String refContractId) {
		
		return this.contractItemDao.getOneOfItem(refContractId);
	}

//	@Override
//	public List<ContractItemBillModel> getContractItemBillModelByPark(String refParkId,String today) {
//		
//		List<ContractItemBillModel> result=new ArrayList<ContractItemBillModel>();
//		
//		List<ContractItem> items=this.contractItemDao.getContractItemsForBill(today,refParkId);
//		
//		//System.out.println("园区:"+refParkId+",在合同期内的contractItem数量:"+items.size());
//		
//		
//		
//		//判断是否需要出帐，并生成model
//		for(ContractItem item : items)
//		{
//			
//			
//			ContractItemBillModel model=this.getContractItemBillModel(item,today);
//			
//			if(model!=null)
//			{
//				result.add(model);
//			}
//			
//		}
//		
//		
//		return result;
//	}
//	
//	
//	
//
//	@Override
//	public List<ContractItemBillModel> getContractItemBillModelByContract(
//			Contract contract) {
//		
//		String today=Util.formatDate(new Date());
//		
//		List<ContractItem> items=this.contractItemDao.getContractItemsByContractId(today, contract.getId());
//		
//		List<ContractItemBillModel> result=new ArrayList<ContractItemBillModel>();
//		
//		//判断是否需要出帐，并生成model
//		//计费结束日即为合同终止日
//		for(ContractItem item : items)
//		{
//			//ContractItemBillModel model=this.getContractItemBillModel(item,today);
//			
//			//项目结束日已小于最后结算日，说明已结束分期
//			if(contract.getLastBalanceDate()!=null && item.getEndDate().compareTo(contract.getLastBalanceDate())<=0)
//			{
//				continue;
//			}
//			
//			String currentBillDateStr=today;
//			
//			String feeDateStart="";
//			
//			String feeDateEnd=today;
//			
//			if(contract.getLastBalanceDate()==null)
//			{
//				feeDateStart=Util.formatDate(item.getStartDate());
//			}
//			else
//			{
//				feeDateStart=Util.increaseDate(Util.formatDate(contract.getLastBalanceDate()),1);
//			}
//			
//			//今天大于项目结束日
//			if(Util.dateDiff(today, Util.formatDate(item.getEndDate()))>0)
//			{
//				feeDateEnd=Util.formatDate(item.getEndDate());
//			}
//			
//			float amount=0;
//			
//			String currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
//			
//			ContractItemBillModel model=new ContractItemBillModel();
//			
//			model.setStartDate(Util.formatDate(item.getStartDate()));
//			
//			model.setEndDate(Util.formatDate(item.getEndDate()));
//			
//			
//			model.setRefContractId(item.getRefContractId());
//			
//			model.setRefContractItemId(item.getId());
//			
//			model.setCurrBillDate(currentBillDateStr);
//			
//			model.setFeeStartDate(feeDateStart);
//			
//			model.setFeeEndDate(feeDateEnd);
//			
//			model.setDailyUnitPrice(item.getDailyUnitPrice());
//			
//			model.setMonthlyUnitPrice(item.getMonthlyUnitPrice());
//			
//			model.setArea(item.getContent());
//			
//			model.setPaymentDate(currPaymentDate);
//			
//			//日单价为每平方
//			
//			Float area=1f;
//			
//			//租赁，物业需要面积
//			if(contract.getContractCategory()==BillSource.LEASE_CONTRACT.value || 
//					contract.getContractCategory()==BillSource.PROPERTY_CONTRACT.value)
//			{
//				area=Float.valueOf(model.getArea());
//			}
//			
//			 amount=model.getDailyUnitPrice()*area*(Util.dateDiff(model.getFeeEndDate(),model.getFeeStartDate())+1);
//			
//			//amount=item.getDailyUnitPrice()*Float.valueOf(model.getArea())*(Util.dateDiff(feeDateEnd,feeDateStart)+1);
//			
//			model.setAmount(amount);
//			
//			
//			
//			result.add(model);
//			
//			
//		}
//		
//		
//		return result;
//		
//	}
//	
//	
//	
//	
//	/**
//	 * 判断合同项是否需要出帐，是，返回ContractItemBillModel
//	 * @param item
//	 * @return
//	 */
//	private ContractItemBillModel getContractItemBillModel(ContractItem item,String today)
//	{
//		
//		Contract contract=this.contractDao.getContractById(item.getRefContractId());
//		
//		if(contract==null) return null;
//		
//		//最后一次出帐日
//		String lastBillDateStr="";
//		//最后一次结算日
//		String lastFeeDateStr="";
//		
//		//此次出帐日
//		String currentBillDateStr=today;
//				//Util.formatDate(new Date());
//		
//		//计费开始日
//		String feeDateStart="";
//		//计费截止日
//		String feeDateEnd="";
//		
//		//缴费截止日
//		String currPaymentDate="";
//		
//		//缴费金额
//		float amount=0;
//		
//		boolean needbill=false;	
//		//自定义出帐日
//		if(item.getBillType()==0)
//		{
//			//上次计费截止日＝项目结束日
//			if(contract.getLastBalanceDate()!=null && Util.formatDate(item.getEndDate()).equals(Util.formatDate(contract.getLastBalanceDate())))
//			{
//				return null;
//			}
//			
//			
//			String billDates=item.getBillDates();
//			
//			if(!Util.isNullOrEmpty(billDates))
//			{
//				List<String> billDatesArr=new ArrayList<String>(Arrays.asList(billDates.split(",")));
//				
//				//Date now=today;
//						//new Date();
//				
//				//先asc排序
//				Collections.sort(billDatesArr);
//				
//				//int count=0;
//				
//				for(String d:billDatesArr)
//				{
//					
//					String[] darr=today.split("-");
//					
//					//d=DateUtil.getYear()+"-"+d;
//					currentBillDateStr=darr[0]+"-"+d;
//										
//					//计费截止日
//					feeDateEnd=this.generateCurrentFeeDate(currentBillDateStr, item.getBillPeriod());
//					
//					//今天大于等于出帐日，出帐
//					//计费截止日>＝分期结束日，出帐
//					if(Util.dateDiff(today,currentBillDateStr)>=0 ||
//							Util.dateDiff(feeDateEnd,Util.formatDate(item.getEndDate()))>=0)
//					{
//						
//						if(contract.getLastBalanceDate()==null)
//						{
//							feeDateStart=Util.formatDate(item.getStartDate());
//						}
//						else
//						{
//							feeDateStart=Util.increaseDate(Util.formatDate(contract.getLastBalanceDate()),1);
//						}
//						
//						//计费开始日大于项目结束日，略过
////						if(Util.dateDiff(feeDateStart, Util.formatDate(item.getEndDate()))>0)
////						{
////							continue;
////						}						
//						
//						//比较计费截止日与结束日期
//						int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));
//		
//						//若计费截止日已大于等于分期结束日
//						if(dateDiff>=0)
//						{
//							feeDateEnd=Util.formatDate(item.getEndDate());
//						}
//					
//						currPaymentDate=Util.increaseDate(Util.formatDate(new Date()), item.getPaymentDate());
//						
//						
//						
//						//若计费开始日期大于计费结束日期
//						if(Util.dateDiff(feeDateEnd, feeDateStart)>=0)
//						{
//							needbill=true;
//						}
//						
//					}
//				}
//				
//				
//				
//				
//				
//			}
//		}
//		//统一规则
//		if(item.getBillType()==1 && !Util.isNullOrEmpty(item.getBillDate()))
//		{
//			//出帐日
//			String billDateStr=item.getBillDate();
//						
//			//出帐间隔月份
//			int billIntervalMonth=Integer.valueOf(billDateStr.substring(0, billDateStr.indexOf("-")));
//			//出帐日
//			int billDate=Integer.valueOf(billDateStr.substring(billDateStr.indexOf("-")+1));
//			
//			//计费周期			
//			String billPeriodStr=item.getBillPeriod();
//			
////			String[] parr=billPeriodStr.split("-");
//			
////			int billPeriodInterval=Integer.valueOf(parr[0]);
////			
////			int billPeriodDay=Integer.valueOf(parr[1]);
//
//			
//			
//			
//			//首次，feeDateStart=startDate
//			if(contract.getLastBalanceDate()==null)
//			{
//				lastBillDateStr=Util.formatDate(item.getStartDate());
//				
//				feeDateStart=Util.formatDate(item.getStartDate());
//			}
//			else
//			{
//				//lastBillDateStr=Util.formatDate(item.getLastBillDate());
//				
//				lastFeeDateStr=Util.formatDate(contract.getLastBalanceDate());
//				
//				//若上次结算日＝分期结束日，已结束本期,sql中已处理
//				if(lastFeeDateStr.equals(Util.formatDate(item.getEndDate())))
//				{
//					return null;
//				}
//				
//				//上次结算日加一天
//				feeDateStart=Util.increaseDate(lastFeeDateStr,1);
//				//从上次结算截止日倒推上次出帐日
//				lastBillDateStr=this.getBillDateByFeeEndDate(lastFeeDateStr, billDateStr, billPeriodStr);
//						
//			}
//				
////			//如果计费开始日已大于分期结束日，即本分期出帐完成，略过
////			if(Util.dateDiff(feeDateStart, Util.formatDate(item.getEndDate()))>0)
////			{
////				return null;
////			}				
//			
//			//生成这次出帐日
//			currentBillDateStr=Util.getLastCurrentBillDate(lastBillDateStr, billDate, billIntervalMonth,today);
//			
//			//当取回的最后一次有效出帐日＝＝上次出帐日，不生成
//			if(lastBillDateStr.equals(currentBillDateStr))
//			{
//				return null;
//			}
//			
//			
//			//计费截止日
//			feeDateEnd=this.generateCurrentFeeDate(currentBillDateStr, billPeriodStr);	
//			
//			//假设今天为出帐日，推算出计费结束日
//			String tempFeeDateEnd=this.generateCurrentFeeDate(today, billPeriodStr);
//			
//			//若今天大于等于出帐日，出帐，今天 大于出帐日，即自动生成失败，需要再生成
//			//或者假设计费截止日大于等于项目结束日期，即，本分期结束，也出帐
//			//currentBillDateStr.equals(today) || 
//			if(Util.dateDiff(today,currentBillDateStr)>=0
//					|| Util.dateDiff(tempFeeDateEnd,Util.formatDate(item.getEndDate()))>=0)
//			{
//								
//				//比较计费截止日与分期结束日期
//				int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));
//
//				//若计费截止日已大于等于分期结束日
//				if(dateDiff>=0)
//				{
//					feeDateEnd=Util.formatDate(item.getEndDate());
//				}
//			
//				//缴费日期应该从当前创建日期起算
//				//currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
//				currPaymentDate=Util.increaseDate(Util.formatDate(new Date()), item.getPaymentDate());
//									
//				//amount=item.getDailyUnitPrice()*Util.dateDiff(feeDateEnd,feeDateStart);
//				
//				if(Util.dateDiff(feeDateEnd, feeDateStart)>=0)
//				{
//					needbill=true;
//				}
//				
//			}
//						
//			
//		
//		}
//		
//		
//		
//		if(needbill)
//		{
//			ContractItemBillModel model=new ContractItemBillModel();
//			
//			model.setStartDate(Util.formatDate(item.getStartDate()));
//			
//			model.setEndDate(Util.formatDate(item.getEndDate()));
//			
//			
//			model.setRefContractId(item.getRefContractId());
//			
//			model.setRefContractItemId(item.getId());
//			
//			model.setCurrBillDate(currentBillDateStr);
//			
//			model.setFeeStartDate(feeDateStart);
//			
//			model.setFeeEndDate(feeDateEnd);
//			
//			model.setDailyUnitPrice(item.getDailyUnitPrice());
//			
//			model.setMonthlyUnitPrice(item.getMonthlyUnitPrice());
//			
//			model.setArea(item.getContent());
//			
//			model.setPaymentDate(currPaymentDate);
//			
//			//生成bill时设置
//			amount=0;
//					//item.getDailyUnitPrice()*(Util.dateDiff(feeDateEnd,feeDateStart)+1);
//			
//			model.setAmount(amount);
//			
//			return model;
//			//result.add(model);					
//			
//		}
//		
//		
//		return null;
//		
//	}
//
//	/**
//	 * 生成当前计费日期，即出帐日第几个月的几号
//	 * @param currBillDate
//	 * @param billPeriod
//	 * @return
//	 */
//	private String generateCurrentFeeDate(String currBillDate,String billPeriod)
//	{
//		String result="";
//		
//		String[] arr=currBillDate.split("-");
//		
//		int y=Integer.valueOf(arr[0]);
//		
//		int m=Integer.valueOf(arr[1]);
//		
//		int d=Integer.valueOf(arr[2]);
//		
//		String[] parr=billPeriod.split("-");
//		
//		int pm=Integer.valueOf(parr[0]);
//		
//		int pd=Integer.valueOf(parr[1]);
//		
//		//判断出帐日与计费日
//		//出帐日小于计费日，或者计费日为月末，则下一次计费日为当月，故需－1
//		if(d<pd || pd==0)
//		{
//			m=m+pm-1;
//		}else
//		{
//			m=m+pm;
//		}
//		
//		if(m>12)
//		{			
//			int ym=(int)Math.floor(m/12);
//			
//			y+=ym;
//			
//			m=m-12*ym;
//		}		
//		
//		
//		if(pd==0)
//		{
//			m+=1;
//
//			if(m>12)
//			{
//				y+=1;
//				
//				m-=12;
//			}	
//			
//			String tmp=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", 1);
//			
//			result=Util.increaseDate(tmp,-1);
//		}
//		else
//		{
//			
//			result=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", pd);
//		}				
//		
//		return result;
//	}
//	
//	
//	
//	
//	/**
//	 * 根据结算截止日、计费周期，得出出帐日
//	 * @param feeEndDate
//	 * @param billPeriod
//	 * @return
//	 */
//	private String getBillDateByFeeEndDate(String feeEndDate,String billDate,String billPeriod)
//	{
//		String result="";
//		
//		String[] arr=feeEndDate.split("-");
//		
//		int y=Integer.valueOf(arr[0]);
//		
//		int m=Integer.valueOf(arr[1]);
//		
//		int d=Integer.valueOf(arr[2]);
//		
//		String[] barr=billDate.split("-");
//		
//		int bm=Integer.valueOf(barr[0]);
//		
//		int bd=Integer.valueOf(barr[1]);
//		
//		String[] parr=billPeriod.split("-");
//		
//		int pm=Integer.valueOf(parr[0]);
//		
//		int pd=Integer.valueOf(parr[1]);
//		
//		if(pd>bd || pd==0)
//		{
//			m=m-pm+1;
//		}
//		else
//		{
//			m=m-pm;
//		}
//		
//		if(m<=0)
//		{
//			int ym=(int) Math.abs(Math.ceil(m/12));
//			
//			y=y-ym;
//			
//			m=m+12*ym;
//		}
//		
//		
//		result=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", bd);
//		
//		
//		
//		
//		return result;
//	}

}
