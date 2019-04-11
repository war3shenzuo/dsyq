package com.etop.management.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopOfflineTraining;
import com.etop.management.bean.Park;
import com.etop.management.bean.ProfitLoss;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.constant.SysStatus;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.dao.EtopOfflineTrainingDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.ContractService;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyMaintainService;
import com.etop.management.service.EtopProfitLossService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServicePurchaseService;
import com.etop.management.service.EtopServiceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.service.EtopTrainPlanService;
import com.etop.management.service.RemindService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;
import com.github.pagehelper.StringUtil;


@Component("scheduledTaskManager") 
public class UpdateOverdueJob {

	private final static Logger LOGGER = Logger.getLogger(UpdateOverdueJob.class);
	
	
	@Inject
	private EtopBillService etopBillService;
	
	@Inject
	ContractService contractService;
	
	@Inject
	ContractEnergyService contractEnergyService;
	
	@Inject 
	EnergyCostService energyCostService;
	
	@Inject
	ParkDao parkDao;
	
	@Inject
	RemindService remindService;

	@Inject
	private EtopCompanyMaintainService etopCompanyMaintainService;

	@Inject
	private EtopCompanyIntentionService etopCompanyIntentionService;

	@Autowired
	private EtopTrainPlanService etopTrainPlanService;

	@Autowired
	private EtopProfitLossService profitLossService;

	@Resource
	private EtopServiceDao etopServiceDao;
	@Resource
	private EtopThresholdService etopThresholdService;
	
	@Resource
	private EtopSequenceService etopSequenceSevice;
	
	@Resource
	private EtopServiceService etopServiceService;

	@Resource 
	private EtopServicePurchaseService etopServicePurchaseService;
	
	@Resource 
	private ContractDao contractDao; 
	
	@Resource 
	private EtopCompanyDao etopCompanyDao; 
	@Resource
	private EtopFloorRoomDao etopFloorRoomDao;
	@Resource
	private EtopOfflineTrainingDao etopOfflineTrainingDao;

	
//	@Scheduled(cron="0 5 10 * * ?")
	public void execute() {
		System.out.println("update overduefine!");
		etopBillService.updateOverdueFine();
	}


	
//	@Scheduled(cron="0 10 10 * * ?")
//	public void etopCompanyMaintainRemind() {
//		etopCompanyMaintainService.getMaintainList();
//	}

//	@Scheduled(cron="0 11 10 * * ?")
	public void etopCompanyIntentionRemind() {
		etopCompanyIntentionService.getComIntentionByParkId();
	}

//	@Scheduled(cron="0 12 10 * * ?")
	public void etopTrainPlanRemind() {
		etopTrainPlanService.getEtopTrainPlanRemind();
	}

	//报表数据（每个月1号）
//	@Scheduled(cron="0 0 0 * * ?")
	public void profitLossRemind() throws ParseException {
		Calendar cale= Calendar.getInstance();
		if(Calendar.DAY_OF_MONTH == 1){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			ProfitLoss profitLoss = new ProfitLoss();
			profitLoss.setPeriodsStr(sf.format(new Date()));
			profitLossService.add(profitLoss);
		}
	}

	protected String getParkId(String parkId) {
		if(StringUtil.isEmpty(parkId))
			return UserInfoUtil.getUserParkInfo().getId();
		else
			return parkId;
	}
	
	//服务完工3天后自动出账
//	@Scheduled(cron="0 13 10 * * ?")
	public void addBill() {
		
		System.out.println("service addBill status job start...");
		
		List<EtopService> serviceList=etopServiceDao.queryObject();
		
		
		for(EtopService etopService : serviceList)
		{
			
			if( etopService.getCompleteTime()!=null &etopService.getServiceStatus()==204){
				Date date = new Date();  
		        long times = date.getTime();
				long befores =etopService.getCompleteTime().getTime();
			if(times - befores>=259200000)
			{
				if("GGCG".equals(etopService.getServiceType())){
				etopServiceService.cronaddBill(etopService.getServiceId(), null, null);
				}
				else{
					etopServiceService.addBill(etopService.getServiceId(), null, null);
				}
			}
		}
	}

	}
	
	//过期预约自动拒绝
//	@Scheduled(cron="0 14 10 * * ?")
	public void FacilityStatus(){

		System.out.println("Facility status job start...");
//		Calendar.getInstance().getTimeInMillis()


		List<EtopService> serviceList=etopServiceDao.selectFacilityServer(System.currentTimeMillis());
		
		for(EtopService etopService : serviceList)
		{
			if("1".equals(etopService.getExpirationTime())&101==etopService.getServiceStatus())
			{
				etopService.setServiceStatus(301);
				etopServiceDao.updateBySelective(etopService);
			}
		}
	
		
	}
	
	//合同过期自动终止
//	@Scheduled(cron="0 15 10 * * ?")
	public void OutStatusOfCompany(){

		System.out.println("OutStatusOfCompany job start...");

		List<Contract> contract = contractDao.getNormalCompany(DateUtil.formatDate(new Date()));
		
		List<Contract> contractTwo = contractDao.getNormalCompanyTwo(DateUtil.formatDate(new Date()));
		
		List<String> list = new ArrayList<String>();
		for(Contract c :contract){
			list.add(c.getRefCompanyId());
		}
		
		for(Contract c2 :contractTwo){
			list.add(c2.getRefCompanyId());
		}
		
		for(int i = 0; i < list.size(); i++){
			Contract  map =contractDao.chaeckNormalCompany(list.get(i));
			if(map != null){
				String mapCompanyId = map.getRefCompanyId();
				if(list.contains(mapCompanyId)){
					list.add(mapCompanyId);
				}
			}
		}
		
		List<EtopCompany> etopCompany = etopCompanyDao.selectCompanyList();

			  for(EtopCompany etopCompanyList : etopCompany){

					if(! list.contains(etopCompanyList.getCompanyId())){

						etopCompanyDao.updateBycompanyId(etopCompanyList.getCompanyId());
					}
			  	}
	}
	
	//根据合同改变房间状态
//	@Scheduled(cron="0 16 10 * * ?")
	public void FloorStatus(){

		System.out.println("FloorStatus job start...");

		String date =DateUtil.formatDate(new Date());
		
		List<Contract> contract = contractDao.getNormalCompany(date);
		
		List<Contract> contractTwo = contractDao.getNormalCompanyTwo(date);
		
		List<String> list = new ArrayList<String>();
		List<String> companyIdList = new ArrayList<String>();
		for(Contract c :contract){
			list.add(c.getRefRoomId());
			companyIdList.add(c.getRefCompanyId());
		}
		
		for(Contract c2 :contractTwo){
			list.add(c2.getRefRoomId());
			companyIdList.add(c2.getRefCompanyId());
		}
		
/*		for(int i = 0; i < companyIdList.size(); i++){
			Contract  map =contractDao.chaeckNormalCompany(companyIdList.get(i));
			if(map != null){
				String mapCompanyId = map.getRefCompanyId();
				if(list.contains(mapCompanyId)){
					list.add(map.getRefRoomId());
				}
			}
		}*/
		
		List<EtopFloorRoom> etopFloorRoom = etopFloorRoomDao.selectRoomsList();

		  for(EtopFloorRoom etopFloorRoomList : etopFloorRoom){

				if(list.contains(etopFloorRoomList.getId())){
					//0待租（无人）   2预留中
					if( "0".equals(etopFloorRoomList.getFloorStatus())  ||  "2".equals(etopFloorRoomList.getFloorStatus()) ){
						etopFloorRoomDao.updateForLet(etopFloorRoomList.getId());  //1已出租
					}
				}else{
					// 1已出租  3 待租（有人）
					if( "1".equals(etopFloorRoomList.getFloorStatus())  ||  "3".equals(etopFloorRoomList.getFloorStatus()) ){
						etopFloorRoomDao.updateToLet(etopFloorRoomList.getId()); // 0待租（无人）
					}
				}
		  	}
	}
	
	//合同出账
	public void billForAllContract()
	{
		//int count=0;
		
		long start=System.currentTimeMillis();
		
		//System.out.println("bill contract job start...");
		
		LoggerUtil.info("bill contract job start...");
		
		List<Park> parkList=this.parkDao.getParkNameList();	
		
		int success=0;
		
		int failed=0;
		
		for(Park park:parkList)
		{
			ResultType result=this.contractService.generateBillByPark(park.getId(),Util.formatDate(new Date()));
			
			@SuppressWarnings("unchecked")
			Map<String,Integer> map=(Map<String,Integer>)result.getData();
			
			String content="";
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("success")>0 )
			{
				content=String.format("园区(%s)合同已出帐(%d)，请审核。",park.getParkName(),map.get("success"));
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "合同出帐", content);
				
				success+=map.get("success");
				
				//LoggerUtil.info(content);
			}
			
			if(result.getStatus()==SysStatus.SUCCESS.getStatus() && map.get("failed")>0)
			{
				content=String.format("园区(%s)合同自动出帐部分失败，数量(%d)，请管理员查看。",park.getParkName(),map.get("failed"));
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				failed+=map.get("failed");
				
				//LoggerUtil.info(content);
			}
			
			if(result.getStatus()==SysStatus.EXCEPTION.getStatus())
			{
				content=String.format("园区(%s)合同自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "合同出帐", content);
				
				
			}
			
			LoggerUtil.info(content);
			
		}
		
		
		LoggerUtil.info(String.format("bill contract job finished. spend time %d, success:%d,failed:%d",
				System.currentTimeMillis()-start,success,failed));
//		System.out.println(String.format("bill contract job finished. spend time %d, success:%d,failed:%d",
//				System.currentTimeMillis()-start,success,failed));
		
	}
	
	//能源出帐与能源提醒
	public void billForEnergy()
	{
		
		long start=System.currentTimeMillis();
		
		int count=0;
		
		List<Park> parkList=this.parkDao.getParkNameList();
				
		LoggerUtil.info("bill energy job start...");
		
		
		for(Park park : parkList)
		{
			if("0".equals(park.getActivated()))
			{
				continue;
			}
			
			count=this.contractEnergyService.generateBillByPark(park.getId(), Util.formatDate(new Date()));
			
			String content=String.format("园区(%s)能源已出帐(%d)。",park.getParkName(),count);;
			
			if(count>0)
			{
				content=String.format("园区(%s)能源已出帐(%d)，请审核。",park.getParkName(),count);
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "能源出帐", content);
				
			}
			if(count==-3)
			{
				content=String.format("园区(%s)能源自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "能源出帐", content);
				
				
			}
			
			LoggerUtil.info(content);
			
		}
		
		
		LoggerUtil.info(String.format("bill energy job finished. spend time %d,count:%d",
				System.currentTimeMillis()-start
				,count)			
				);
		
		start=System.currentTimeMillis();
		
		LoggerUtil.info("energy enter remind job start...");
		//TODO：提醒各园区能源录入
		for(Park park : parkList)
		{
			if("0".equals(park.getActivated()))
			{
				continue;
			}
			
			List<EtopFloor> floors=this.energyCostService.getNeedEnterEnergyFloors(park.getId(), Util.formatDate(new Date()));
			
			if(floors!=null && floors.size()>0)
			{
				String floorNames=floors.stream().map(f->f.getBuildName()).collect(Collectors.toList()).toString();
				
				String content=String.format("今天需要抄表楼:%s(水表/电表/燃气/空调)读数,请及时处理。",floorNames);
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "能源录入提醒", content);
				
				LoggerUtil.info(String.format("需录入能源园区：%s,楼：%s", park.getParkName(),floorNames));
				
			}			
			
		}
		
		
		
		LoggerUtil.info(String.format("energy enter remind finished. spend time %d",
				System.currentTimeMillis()-start)			
				);
		
		
		
		
		
	}
	
	/**
	 * 自动押金 出账
	 */
	public void billForContractDeposit()
	{
		long start=System.currentTimeMillis();
		
		
		LoggerUtil.info("bill contract deposit job start...");
		
		List<Park> parkList=this.parkDao.getParkNameList();	
		
		int success=0;
		
		int failed=0;
		
		for(Park park:parkList)
		{
			ResultType result=this.contractService.generateDepositBillByPark(park.getId(),Util.formatDate(new Date()));
			
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
			
		LoggerUtil.info(String.format("bill contract deposit finished. spend time %d, success:%d,failed:%d",
				System.currentTimeMillis()-start,success,failed));
		
	}
	
	
	//培训状态

	public void TrainStatus(){

		System.out.println("Training status job start...");
//		Calendar.getInstance().getTimeInMillis()


		 List<EtopOfflineTraining> TrainingList=etopOfflineTrainingDao.getAll();
		
			for(EtopOfflineTraining etopOfflineTraining : TrainingList)
			{

				if( 0 == etopOfflineTraining.getCourseStatus()  && (new Date().getTime()) > etopOfflineTraining.getCloseTime().getTime())
				{
					etopOfflineTrainingDao.updateStatus(1, etopOfflineTraining.getId());
				}
			}
	
		
	}
}
