package com.etop.management.service.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.BalanceStatistics;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ProfitLoss;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopProfitLossDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.EtopProfitLossService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopProfitLossService<br>
 */
@Service
public class  EtopProfitLossServiceImpl  implements EtopProfitLossService {

    @Autowired
    private EtopProfitLossDao etopProfitLossDao;

    @Autowired
    private ParkDao parkDao;

	@Override
	public EtopPage<ProfitLoss> realTime(ProfitLoss profitLoss) {

        Page<ProfitLoss> page =new Page<>();
        ProfitLoss entity = null ;
        ProfitLoss entity1 =null ;
        ProfitLoss entity2 =null ;
        ProfitLoss entity3 =null ;
        ProfitLoss entity4 =null ;
        ProfitLoss entity5 =null ;
        Page<ProfitLoss> list = new Page<ProfitLoss>();
        profitLoss.setReportType(1);

            profitLoss.setItems(1);//合同收益
            profitLoss.setFine(1);//租赁合同
            entity = this.leaseDataList(profitLoss);
            entity.setFine(1);//租赁合同
            list.add(entity);
            
            profitLoss.setFine(4);//物业合同
            entity1 = this.leaseDataList(profitLoss);
            entity1.setFine(4);//物业合同
            list.add(entity1);
            
            profitLoss.setFine(5);//服务合同
            entity2 = this.contractDataList(profitLoss);
            entity2.setFine(5);//服务合同
            list.add(entity2);

            profitLoss.setFine(3);//能源合同
            entity3 = this.serviceDataList(profitLoss);
            entity3.setFine(3);//能源合同
            list.add(entity3);
            
            profitLoss.setItems(2);//服务收益
            profitLoss.setFine(6);//园区服务
            entity4 = this.serviceDataList(profitLoss);
            entity4.setItems(2);//服务收益
            entity4.setFine(6);//园区服务
            list.add(entity4);
            
            profitLoss.setItems(4);//合同支出
            profitLoss.setFine(2);//外包合同
            profitLoss.setReportType(2);
            entity5 = this.contractDataList(profitLoss);
            entity5.setItems(4);//合同支出
            entity5.setFine(2);//外包合同
            list.add(entity5);
		return new EtopPage<ProfitLoss>(list);
	}
	
    @Override
    public void add(ProfitLoss profitLoss) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date periods = sdf.parse(profitLoss.getPeriodsStr());
        profitLoss.setPeriods(periods);
        List<String> parkIdList = parkDao.getParkIdList();
        for(int i=0;i<parkIdList.size();i++){

            profitLoss.setReportType(1);
            profitLoss.setParkId(parkIdList.get(i));

            ProfitLoss entity = null;

            if(i>0){
                periods = profitLoss.getPeriods();
                profitLoss = new ProfitLoss();
                profitLoss.setPeriods(periods);
                profitLoss.setReportType(1);
                profitLoss.setParkId(parkIdList.get(i));
            }

            profitLoss.setItems(1);//合同收益
            profitLoss.setFine(1);//租赁合同
            entity = this.leaseData(profitLoss);

            entity.setFine(2);//服务合同
            entity = this.leaseData(entity);

            entity.setFine(3);//物业合同
            entity = this.leaseData(entity);

            entity.setFine(4);//能源合同
            entity = this.serviceData(entity);

            entity.setItems(2);//服务收益
            entity.setFine(1);//园区服务
            entity = this.serviceData(entity);

            entity.setItems(4);//合同支出
            entity.setFine(1);//外包合同
            entity.setReportType(2);
            this.contractData(entity);
        }

    }

    @Override
    public EtopPage<ProfitLoss> search (ProfitLoss profitLoss) {
    	 ProfitLoss entity = null ; ProfitLoss entity1 =null ;  ProfitLoss entity2 =null ; ProfitLoss entity3 =null ;
         ProfitLoss entity4 =null ; ProfitLoss entity5 =null ;  ProfitLoss entity6 =null ; ProfitLoss entity7 =null ; 
         ProfitLoss getAll =null ;
         ProfitLoss outAll =null ;
         ProfitLoss All =null ;
         DecimalFormat dfs = new DecimalFormat("#.00");
        profitLoss.setReportType(1);//收入数据
        Page<ProfitLoss> list = new Page<ProfitLoss>();
//        Page<ProfitLoss> list = etopProfitLossDao.getProfit(profitLoss);//收入列表
        Page<ProfitLoss> list2 = etopProfitLossDao.getProfit(profitLoss);//收入列表
        
        profitLoss.setItems(1);//合同收益
        profitLoss.setFine(1);//租赁合同
        entity = this.leaseDataList(profitLoss);
        entity.setFine(1);//租赁合同
        list.add(entity);
        
        profitLoss.setFine(4);//物业合同
        entity1 = this.leaseDataList(profitLoss);
        entity1.setFine(4);//物业合同
        list.add(entity1);
        
        profitLoss.setFine(5);//服务合同
        entity2 = this.contractDataList(profitLoss);
        entity2.setFine(5);//服务合同
        list.add(entity2);

        profitLoss.setFine(3);//能源合同
        entity3 = this.serviceDataList(profitLoss);
        entity3.setFine(3);//能源合同
        list.add(entity3);
        
        profitLoss.setItems(2);//服务收益
        profitLoss.setFine(6);//园区服务
        entity4 = this.serviceDataList(profitLoss);
        entity4.setItems(2);//服务收益
        entity4.setFine(6);//园区服务
        list.add(entity4);
        
        profitLoss.setItems(4);//合同支出
        profitLoss.setFine(2);//外包合同
        profitLoss.setReportType(2);
        entity5 = this.contractDataList(profitLoss);
        entity5.setItems(4);//合同支出
        entity5.setFine(2);//外包合同
//        list.add(entity5);
        
        String[] strArray={"3.1","3.2","3.3","3.4","3.5","3.6","5.1","5.2","5.3","5.4","5.5","6.1","6.2","6.3","6.4","6.5","6.6","6.7","6.8",
        		"7.1","7.2","7.3","7.4","8.1","8.2","8.3","8.4","8.5","8.6","8.7","8.8","8.9","8.10","8.11","8.12","8.13","9.1","9.2","9.3",
        		"10.1","10.2","10.3","10.4","10.5","11.1","11.2","11.3","11.4","12.1","12.2","12.3","12.4","12.5","12.6","12.7","100.0"};
//        String[] strArray={"3.1","3.2","3.3","3.4","3.5","3.6","5.1","5.2","5.3","5.4","5.5"};
        for(int i=0;i<strArray.length;i++){
        	Page<ProfitLoss> lists = etopProfitLossDao.getProfitLoss(profitLoss);
            for(ProfitLoss profit :lists){
            	
            	StringBuffer sb = new StringBuffer();
        			sb.append(profit.getItems());
        			sb.append(".");
        			sb.append(profit.getFine());
        			String name =sb.toString();
        			if(strArray[i].equals(name)){
        				for (int j = i+1; j < strArray.length; j++) {
        					strArray[i]=strArray[j];
        					}
        			}
            }
        }
        for(int i=0;i<strArray.length;i++){
        	String value = strArray[i];  
        	String[] names = value.split("\\.");  
        	
//        		System.out.println(names[0]);  
//        		System.out.println(names[1]);  
        		if(!"100".equals(names[0])){
	        		profitLoss.setItems(Integer.parseInt(names[0]));
	        	    profitLoss.setFine(Integer.parseInt(names[1]));
	        		profitLoss.setCurrentLimit(0.0);
	        		if("3".equals(profitLoss.getItems())){
	        			entity7 = this.searchFine(profitLoss);
	        			entity7.setItems(Integer.parseInt(names[0]));
	        			entity7.setFine(Integer.parseInt(names[1]));
		    	        list.add(entity7);
	        		}
	        		entity6 = this.searchFine(profitLoss);
	        		entity6.setItems(Integer.parseInt(names[0]));
	        		entity6.setFine(Integer.parseInt(names[1]));
	    	        list.add(entity6);
        		}

        }
        profitLoss.setReportType(1);
        ProfitLoss bo = etopProfitLossDao.getProfitLossList(profitLoss);//收入合计
        if( bo.getPeriods() == null){
               getAll =this.getData(bo,1);
        }else{
        getAll =this.getData2(bo,1);
        }
        DecimalFormat df = new DecimalFormat("#.00%");
        getAll.setCurrentLimit(Double.valueOf(dfs.format(getAll.getCurrentLimit() + entity.getCurrentLimit()+ entity1.getCurrentLimit()
        		+ entity2.getCurrentLimit()+ entity3.getCurrentLimit()+ entity4.getCurrentLimit())));
        getAll.setLastCurrent(Double.valueOf(dfs.format(getAll.getLastCurrent() + entity.getLastCurrent()+ entity1.getLastCurrent()
        		+ entity2.getLastCurrent()+ entity3.getLastCurrent()+ entity4.getLastCurrent())));
        getAll.setPreviousLimit(Double.valueOf(dfs.format(getAll.getPreviousLimit() + entity.getPreviousLimit()+ entity1.getPreviousLimit()
        		+ entity2.getPreviousLimit()+ entity3.getPreviousLimit()+ entity4.getPreviousLimit())));
        if(!getAll.getLastCurrent().equals(0.0) && getAll.getLastCurrent() != null){
        	String yearGrowth = df.format((getAll.getCurrentLimit() / getAll.getLastCurrent()) - 1);//同比增长
            getAll.setYearGrowth(yearGrowth); 
        }else {
        	getAll.setYearGrowth("0");
        }
        if(!getAll.getPreviousLimit().equals(0.0) && getAll.getPreviousLimit() != null){
        	String momGrowth = df.format((getAll.getCurrentLimit() / getAll.getPreviousLimit()) - 1);//环比增长
            getAll.setMomGrowth(momGrowth);
        }else {
        	getAll.setMomGrowth("0");
        }
        getAll.setYearCumulative(Double.valueOf(dfs.format(getAll.getYearCumulative() + entity.getYearCumulative()+ entity1.getYearCumulative()
        		+ entity2.getYearCumulative()+ entity3.getYearCumulative()+ entity4.getYearCumulative())));
        getAll.setLastyearPeriod(Double.valueOf(dfs.format(getAll.getLastyearPeriod() + entity.getLastyearPeriod()+ entity1.getLastyearPeriod()
        		+ entity2.getLastyearPeriod()+ entity3.getLastyearPeriod()+ entity4.getLastyearPeriod())));
        
        if(getAll.getLastyearPeriod() != null && !getAll.getLastyearPeriod().equals(0.0)){
        	String lastYearGrowth =  df.format((getAll.getYearCumulative() / getAll.getLastyearPeriod()) - 1);//同比增长
        	getAll.setLastyearGrowth(lastYearGrowth);
        }else {
        	getAll.setLastyearGrowth("0");
        }
        	
        getAll.setBackTotal(Double.valueOf(dfs.format(getAll.getBackTotal() + entity.getBackTotal()+ entity1.getBackTotal()
        		+ entity2.getBackTotal()+ entity3.getBackTotal()+ entity4.getBackTotal())));	
        profitLoss.setReportType(2);//支出数据
        Page<ProfitLoss> lossList = etopProfitLossDao.getLoss(profitLoss);//支出列表
//      list.addAll(lossList);
        list2.addAll(lossList);
        
        for(ProfitLoss fitLoss : list2){

 
	        Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(fitLoss));//上期
	        fitLoss.setPreviousLimit(previousLimit);
 
	        Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(fitLoss));//去年同期
	        fitLoss.setLastCurrent(lastCurrent);
//	        fitLoss.setPeriods(nextMonth(fitLoss).getPeriods());
	        if(fitLoss.getPreviousLimit() == null){
	        	fitLoss.setPreviousLimit(0.0);
	        }
	        
	        if(fitLoss.getLastCurrent() == null){
	        	fitLoss.setLastCurrent(0.0);
	        } 

	        if(!fitLoss.getLastCurrent().equals(0.0) && fitLoss.getLastCurrent() != null){
	            String yearGrowth = df.format((fitLoss.getCurrentLimit() / fitLoss.getLastCurrent()) - 1);//同比增长
	            fitLoss.setYearGrowth(yearGrowth);
	        }else {
	        	fitLoss.setYearGrowth("0");
	        }
	        if(!fitLoss.getPreviousLimit().equals(0.0) && fitLoss.getPreviousLimit() != null){
	            String momGrowth = df.format((fitLoss.getCurrentLimit() / fitLoss.getPreviousLimit()) - 1);//环比增长
	            fitLoss.setMomGrowth(momGrowth);
	        }else {
	        	fitLoss.setMomGrowth("0");
	        }
	        //上月
	        Calendar aCalendar = Calendar.getInstance();
//            aCalendar.setTime(fitLoss.getPeriods());
//            aCalendar.add(Calendar.MONTH, -1);
//            fitLoss.setPeriods(aCalendar.getTime());

        	
            Calendar currCal=Calendar.getInstance();   
            int year = Integer.valueOf(fitLoss.getPeriods().toString().substring(fitLoss.getPeriods().toString().length()-4)).intValue();
            currCal.set(Calendar.YEAR, year);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            currCal.set(Calendar.MILLISECOND, 0);
            fitLoss.setYearFirst(currCal.getTime());
            
            Double yearTotal = etopProfitLossDao.getSumAllYear(fitLoss);
//            Double yearTotal = etopProfitLossDao.getSumYear(fitLoss);
            Double yearCumulative = 0.0;
            if(yearTotal == null){
                yearCumulative  =  fitLoss.getCurrentLimit();//年累计
            }else {
                yearCumulative = etopProfitLossDao.getSumAllYear(fitLoss);//年累计
            }
            fitLoss.setYearCumulative(yearCumulative);
//	        }
            currCal.set(Calendar.YEAR, year-1);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            fitLoss.setYearFirst(currCal.getTime());
            
            Double lastyearTotal = etopProfitLossDao.getSumLastAllYear(lastYearTime(fitLoss));
            Double lastyearPeriod = 0.0;
            if(lastyearTotal == null){
            	lastyearPeriod  =  fitLoss.getCurrentLimit();//去年年累计
            }else {
            	lastyearPeriod = etopProfitLossDao.getSumLastAllYear(lastYearTime(fitLoss));//去年年累计
            }
            fitLoss.setLastyearPeriod(lastyearPeriod);

            aCalendar.setTime(fitLoss.getPeriods());
            aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
            fitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

            Double backTotal = etopProfitLossDao.getPushMonths(fitLoss);//倒推12月合计
            fitLoss.setBackTotal(backTotal);

            if(fitLoss.getLastyearPeriod() != null && !fitLoss.getLastyearPeriod().equals(0.0)){
                String lastYearGrowth =  df.format((fitLoss.getYearCumulative() / fitLoss.getLastyearPeriod()) - 1);//同比增长
                fitLoss.setLastyearGrowth(lastYearGrowth);
            }else {
            	fitLoss.setLastyearGrowth("0");
            }
            
	    }

        ProfitLoss vo = etopProfitLossDao.getProfitLossList(profitLoss);//支出合计
        if( vo.getPeriods() == null){
        	outAll= this.getData(vo,2);
        }else{
        outAll= this.getData2(vo,2);
        }
        outAll.setCurrentLimit(Double.valueOf(dfs.format(outAll.getCurrentLimit() + entity5.getCurrentLimit())));
        outAll.setLastCurrent(Double.valueOf(dfs.format(outAll.getLastCurrent() + entity5.getLastCurrent())));
        outAll.setPreviousLimit(Double.valueOf(dfs.format(outAll.getPreviousLimit() + entity5.getPreviousLimit())));
        if(!outAll.getLastCurrent().equals(0.0) && outAll.getLastCurrent() != null){
        	String yearGrowth = df.format((outAll.getCurrentLimit() / outAll.getLastCurrent()) - 1);//同比增长
        	outAll.setYearGrowth(yearGrowth); 
        }else {
        	outAll.setYearGrowth("0");
        }
        if(!outAll.getPreviousLimit().equals(0.0) && outAll.getPreviousLimit() != null){
        	String momGrowth = df.format((outAll.getCurrentLimit() / outAll.getPreviousLimit()) - 1);//环比增长
        	outAll.setMomGrowth(momGrowth);
        }else {
        	outAll.setMomGrowth("0");
        }
        outAll.setYearCumulative(Double.valueOf(dfs.format(outAll.getYearCumulative() + entity5.getYearCumulative())));
        outAll.setLastyearPeriod(Double.valueOf(dfs.format(outAll.getLastyearPeriod() + entity5.getLastyearPeriod())));
        
        if(outAll.getLastyearPeriod() != null && !outAll.getLastyearPeriod().equals(0.0)){
        	String lastYearGrowth =  df.format((outAll.getYearCumulative() / outAll.getLastyearPeriod()) - 1);//同比增长
        	outAll.setLastyearGrowth(lastYearGrowth);
        }else {
        	outAll.setLastyearGrowth("0");
        }
        outAll.setBackTotal(Double.valueOf(dfs.format(outAll.getBackTotal() + entity5.getBackTotal())));	
        list.add(entity5);
        list.addAll(list2);//收支细项
        list.add(getAll);//收入合计
        list.add(outAll);//支出合计
        
        All = this.getProfitLossData(bo, vo);
        All.setCurrentLimit(Double.valueOf(dfs.format(getAll.getCurrentLimit() - outAll.getCurrentLimit())));
        All.setLastCurrent(Double.valueOf(dfs.format(getAll.getLastCurrent() - outAll.getLastCurrent())));
        All.setPreviousLimit(Double.valueOf(dfs.format(getAll.getPreviousLimit() - outAll.getPreviousLimit())));
        if(!All.getLastCurrent().equals(0.0) && All.getLastCurrent() != null){
        	String yearGrowth = df.format((All.getCurrentLimit() / All.getLastCurrent()) - 1);//同比增长
        	All.setYearGrowth(yearGrowth); 
        }else {
        	All.setYearGrowth("0");
        }
        if(!All.getPreviousLimit().equals(0.0) && All.getPreviousLimit() != null){
        	String momGrowth = df.format((All.getCurrentLimit() / All.getPreviousLimit()) - 1);//环比增长
        	All.setMomGrowth(momGrowth);
        }else {
        	All.setMomGrowth("0");
        }
        All.setYearCumulative(Double.valueOf(dfs.format(getAll.getYearCumulative() - outAll.getYearCumulative())));
        All.setLastyearPeriod(Double.valueOf(dfs.format(getAll.getLastyearPeriod() - outAll.getLastyearPeriod())));
        
        if(All.getLastyearPeriod() != null && !All.getLastyearPeriod().equals(0.0)){
        	String lastYearGrowth =  df.format((All.getYearCumulative() / All.getLastyearPeriod()) - 1);//同比增长
        	All.setLastyearGrowth(lastYearGrowth);
        }else {
        	All.setLastyearGrowth("0");
        }
        All.setBackTotal(Double.valueOf(dfs.format(getAll.getBackTotal() - outAll.getBackTotal())));	
        list.add(All);//收支合计
        
//        List<String> listItem = new ArrayList<String>();
//        Page<ProfitLoss> listItem = new Page<ProfitLoss>();
        
        List list5=new ArrayList<>(list.stream().sorted((o,p)->{
        	return o.getItems()-p.getItems();
        }).collect(Collectors.toList()));
        
        list.clear();
        
        list.addAll(
        		list5
        		);
//        for(int i =0;i<=list.size();i++){
//        	Collections.sort(list.get(i).getItems());
//        }
        
        return new EtopPage<ProfitLoss>(list);
    }
    
    private ProfitLoss searchFine(ProfitLoss profitLoss){
       
            DecimalFormat df = new DecimalFormat("#.00%");
            DecimalFormat dfs = new DecimalFormat("#.00");
            Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(profitLoss));//上期
            Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(profitLoss));//去年同期
            if(previousLimit == null){
                previousLimit = 0.0;
            }
            if(lastCurrent == null){
                lastCurrent = 0.0;
            }

            if(!lastCurrent.equals(0.0) && lastCurrent != null){
                String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrent) - 1);//同比增长
                profitLoss.setYearGrowth(yearGrowth);
            }else {
                profitLoss.setYearGrowth("0");
            }
            if(!previousLimit.equals(0.0) && previousLimit != null){
                String momGrowth = df.format((profitLoss.getCurrentLimit() / previousLimit) - 1);//环比增长
                profitLoss.setMomGrowth(momGrowth);
            }else {
                profitLoss.setMomGrowth("0");
            }

            Calendar aCalendar = Calendar.getInstance();
//            aCalendar.setTime(profitLoss.getPeriods());
//            aCalendar.add(Calendar.MONTH, -1);
//            profitLoss.setPeriods(aCalendar.getTime());

/*            Double yearTotal = etopProfitLossDao.getSumYear(profitLoss);

            Double yearCumulative = 0.0;
            if(yearTotal == null){
                yearCumulative  =  profitLoss.getCurrentLimit();//年累计
            }else {
                yearCumulative = etopProfitLossDao.getSumYear(profitLoss) + profitLoss.getCurrentLimit();//年累计
            }

            aCalendar.setTime(profitLoss.getPeriods());
            aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
            profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

            Double lastYearPeriod = etopProfitLossDao.getLastyearPeriod(profitLoss);//去年年累计

            if(lastYearPeriod == null){
                lastYearPeriod = 0.0;
            }

            Double backTotal = etopProfitLossDao.getPushMonths(profitLoss) + profitLoss.getCurrentLimit();//倒推12月合计
*/
            Calendar currCal=Calendar.getInstance();   
            int year = Integer.valueOf(profitLoss.getPeriods().toString().substring(profitLoss.getPeriods().toString().length()-4)).intValue();
            currCal.set(Calendar.YEAR, year);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            currCal.set(Calendar.MILLISECOND, 0);
            profitLoss.setYearFirst(currCal.getTime());
            
            Double yearTotal = etopProfitLossDao.getSumAllYear(profitLoss);
//            Double yearTotal = etopProfitLossDao.getSumYear(fitLoss);
            Double yearCumulative = 0.0;
            if(yearTotal == null){
                yearCumulative  =  profitLoss.getCurrentLimit();//年累计
            }else {
                yearCumulative = etopProfitLossDao.getSumAllYear(profitLoss);//年累计
            }
            profitLoss.setYearCumulative(yearCumulative);
//	        }
            currCal.set(Calendar.YEAR, year-1);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            profitLoss.setYearFirst(currCal.getTime());
            
            Double lastyearTotal = etopProfitLossDao.getSumLastAllYear(lastYearTime(profitLoss));
            Double lastYearPeriod = 0.0;
            if(lastyearTotal == null){
            	lastYearPeriod  =  profitLoss.getCurrentLimit();//去年年累计
            }else {
            	lastYearPeriod = etopProfitLossDao.getSumLastAllYear(lastYearTime(profitLoss));//去年年累计
            }
            profitLoss.setLastyearPeriod(lastYearPeriod);

            aCalendar.setTime(profitLoss.getPeriods());
            aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
            profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

            Double backTotal = etopProfitLossDao.getPushMonths(profitLoss);//倒推12月合计
            profitLoss.setBackTotal(backTotal);

            if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
                String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
                profitLoss.setLastyearGrowth(lastYearGrowth);
            }else {
                profitLoss.setLastyearGrowth("0");
            }

            profitLoss.setLastCurrent(lastCurrent);
            profitLoss.setPreviousLimit(previousLimit);
            profitLoss.setYearCumulative(yearCumulative);
            profitLoss.setLastyearPeriod(lastYearPeriod);
            profitLoss.setBackTotal(backTotal);
//            profitLoss.setPeriods(nextMonth(profitLoss).getPeriods());
            profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());

            ProfitLoss entity = new ProfitLoss();
        	entity.setPeriods(profitLoss.getPeriods());
        	entity.setReportType(1);
        	entity.setParkId(profitLoss.getParkId());
        	entity.setItems(1);
        	entity.setCurrentLimit(Double.valueOf(dfs.format(profitLoss.getCurrentLimit())));
        	entity.setLastCurrent(Double.valueOf(dfs.format(profitLoss.getLastCurrent())));
        	entity.setPreviousLimit(Double.valueOf(dfs.format(profitLoss.getPreviousLimit())));
        	entity.setMomGrowth(profitLoss.getMomGrowth());
        	entity.setYearGrowth(profitLoss.getYearGrowth());
        	entity.setYearCumulative(Double.valueOf(dfs.format(profitLoss.getYearCumulative())));
        	entity.setLastyearPeriod(Double.valueOf(dfs.format(profitLoss.getLastyearPeriod())));
        	entity.setLastyearGrowth(profitLoss.getLastyearGrowth());
        	entity.setBackTotal(Double.valueOf(dfs.format(profitLoss.getBackTotal())));
        	entity.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        	return entity;
        
    }
	
    @Override
	public EtopPage<ProfitLoss> newSearch(ProfitLoss profitLoss) {
		 profitLoss.setReportType(1);//收入数据
	        Page<ProfitLoss> list = etopProfitLossDao.getProfit(profitLoss);//收入列表
	        ProfitLoss bo = etopProfitLossDao.getProfitLossList(profitLoss);//收入合计
	        if( bo.getPeriods() != null){
	        list.add(this.getData2(bo,1));
	        }
	        profitLoss.setReportType(2);//支出数据
	        Page<ProfitLoss> lossList = etopProfitLossDao.getLoss(profitLoss);//支出列表
	        ProfitLoss vo = etopProfitLossDao.getProfitLossList(profitLoss);//支出合计
	        list.addAll(lossList);
	        for(ProfitLoss fitLoss : list){

	        DecimalFormat df = new DecimalFormat("#.00%");
 
	        Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(fitLoss));//上期
	        fitLoss.setPreviousLimit(previousLimit);
 
	        Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(fitLoss));//去年同期
	        fitLoss.setLastCurrent(lastCurrent);
//	        fitLoss.setPeriods(nextMonth(fitLoss).getPeriods());
	        if(fitLoss.getPreviousLimit() == null){
	        	fitLoss.setPreviousLimit(0.0);
	        }
	        
	        if(fitLoss.getLastCurrent() == null){
	        	fitLoss.setLastCurrent(0.0);
	        } 

	        if(!fitLoss.getLastCurrent().equals(0.0) && fitLoss.getLastCurrent() != null){
	            String yearGrowth = df.format((fitLoss.getCurrentLimit() / fitLoss.getLastCurrent()) - 1);//同比增长
	            fitLoss.setYearGrowth(yearGrowth);
	        }else {
	        	fitLoss.setYearGrowth("0");
	        }
	        if(!fitLoss.getPreviousLimit().equals(0.0) && fitLoss.getPreviousLimit() != null){
	            String momGrowth = df.format((fitLoss.getCurrentLimit() / fitLoss.getPreviousLimit()) - 1);//环比增长
	            fitLoss.setMomGrowth(momGrowth);
	        }else {
	        	fitLoss.setMomGrowth("0");
	        }
	        //上月
	        Calendar aCalendar = Calendar.getInstance();
//            aCalendar.setTime(fitLoss.getPeriods());
//            aCalendar.add(Calendar.MONTH, -1);
//            fitLoss.setPeriods(aCalendar.getTime());

        	
            Calendar currCal=Calendar.getInstance();   
            int year = Integer.valueOf(fitLoss.getPeriods().toString().substring(fitLoss.getPeriods().toString().length()-4)).intValue();
            currCal.set(Calendar.YEAR, year);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            currCal.set(Calendar.MILLISECOND, 0);
            fitLoss.setYearFirst(currCal.getTime());
            
            Double yearTotal = etopProfitLossDao.getSumAllYear(fitLoss);
//            Double yearTotal = etopProfitLossDao.getSumYear(fitLoss);
            Double yearCumulative = 0.0;
            if(yearTotal == null){
                yearCumulative  =  fitLoss.getCurrentLimit();//年累计
            }else {
                yearCumulative = etopProfitLossDao.getSumAllYear(fitLoss);//年累计
            }
            fitLoss.setYearCumulative(yearCumulative);
//	        }
            currCal.set(Calendar.YEAR, year-1);  
            currCal.set(Calendar.MONTH, 0);
            currCal.set(Calendar.DAY_OF_MONTH, 1);
            currCal.set(Calendar.HOUR_OF_DAY, 0);
            currCal.set(Calendar.SECOND,0);
            currCal.set(Calendar.MINUTE,0);
            fitLoss.setYearFirst(currCal.getTime());
            
            Double lastyearTotal = etopProfitLossDao.getSumLastAllYear(lastYearTime(fitLoss));
            Double lastyearPeriod = 0.0;
            if(lastyearTotal == null){
            	lastyearPeriod  =  fitLoss.getCurrentLimit();//去年年累计
            }else {
            	lastyearPeriod = etopProfitLossDao.getSumLastAllYear(lastYearTime(fitLoss));//去年年累计
            }
            fitLoss.setLastyearPeriod(lastyearPeriod);

            aCalendar.setTime(fitLoss.getPeriods());
            aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
            fitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

            Double backTotal = etopProfitLossDao.getPushMonths(fitLoss);//倒推12月合计
            fitLoss.setBackTotal(backTotal);

            if(fitLoss.getLastyearPeriod() != null && !fitLoss.getLastyearPeriod().equals(0.0)){
                String lastYearGrowth =  df.format((fitLoss.getYearCumulative() / fitLoss.getLastyearPeriod()) - 1);//同比增长
                fitLoss.setLastyearGrowth(lastYearGrowth);
            }else {
            	fitLoss.setLastyearGrowth("0");
            }
            
	        }
	        if(vo.getPeriods() != null){
	        list.add(this.getData2(vo,2));
	        }
	        if(bo.getPeriods() != null && vo.getPeriods() != null){
	        list.add(this.getProfitLossData(bo, vo));//收支合计
	        }
	        return new EtopPage<ProfitLoss>(list);
	}
    
    @Override
    public ResultType addFine(ProfitLoss profitLoss) {
        ResultType resultType = null;
//        ProfitLoss loss = etopProfitLossDao.searchProfitLoss(profitLoss);
//        if(loss != null){
//            resultType = ResultType.getFail("所选类型在此期数已保存，请重新选择类型或者期数!");
//        }else {
            this.insertFine(profitLoss);
            resultType = ResultType.getSuccess("新增成功! ");
//        }
        return resultType;
    }
    
    @Override
    public ResultType addFine2(ProfitLoss profitLoss) {
    	ResultType resultType = null;
//        ProfitLoss loss = etopProfitLossDao.searchProfitLoss(profitLoss);
//        if(loss != null){
//            resultType = ResultType.getFail("所选类型在此期数已保存，请重新选择类型或者期数!");
//        }else {
//    	etopProfitLossDao.deleteExist(profitLoss);
    	ProfitLoss fitLoss =new ProfitLoss();
        fitLoss.setMoreperiods(profitLoss.getPeriods());
        fitLoss.setItems(profitLoss.getItems());
        fitLoss.setFine(profitLoss.getFine());
        fitLoss.setParkId(profitLoss.getParkId());
    	etopProfitLossDao.deleteExist(fitLoss);
    	this.insertFine(profitLoss);
    	resultType = ResultType.getSuccess("覆盖成功! ");
//        }
    	return resultType;
    }

    /**
     * 租赁合同、物业合同
     *
     * @param profitLoss
     */
    private ProfitLoss leaseData(ProfitLoss profitLoss){

        List<ProfitLoss> list  = etopProfitLossDao.search(profitLoss);
        double currentLimit = 0.0;
        if(list.size() > 0 && list != null){
            for(ProfitLoss entity : list){
                entity.setPeriods(profitLoss.getPeriods());
                if(isSameMonth(profitLoss.getPeriods(), entity.getStartDate())){//期数在开始月份内
                    int days = daysTime(profitLoss.getPeriods(), entity.getStartDate());
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
                }else if(isSameMonth(profitLoss.getPeriods(), entity.getEndDate())){//期数在结束月份内
                    int days = daysTime(profitLoss.getPeriods(), entity.getEndDate());
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
                }else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice() * entity.getContent();
                }
            }
        }
        profitLoss.setCurrentLimit(currentLimit);
        return (this.insertProfitLoss(profitLoss));

    }
  
    //上期
    private double lastleaseData(ProfitLoss profitLoss){
    	
//    		profitLoss.setPeriods(profitLoss.getLastMonth());
    	List<ProfitLoss> list  = etopProfitLossDao.searchLastMonth(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getLastMonth());
    			if(isSameMonth(profitLoss.getLastMonth(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
        				int days = daysTime(entity.getStartDate(), entity.getEndDate());
//        				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
        				int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
        			}else{//开始结束日期不同月份
        				Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
//    					currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
        				int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
        			}
    			}else if(isSameMonth(profitLoss.getLastMonth(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getLastMonth(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                   Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice() * entity.getContent();
    			}
    		}
    	}
    	  
//    		profitLoss.setPreviousLimit(currentLimit);
//    	return profitLoss;
    	return currentLimit;
    }
    
    private double lastleaseDatas(ProfitLoss profitLoss){
    	
//    	profitLoss.setPeriods(profitLoss.getLastMonth());
    	List<ProfitLoss> list  = etopProfitLossDao.searchLastMonth(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getLastMonth());
    			if(isSameMonth(profitLoss.getLastMonth(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
    					int days = daysTime(entity.getStartDate(), entity.getEndDate());
//    					currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
    				}else{//开始结束日期不同月份
    					Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
//    						currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
    				}
    			}else if(isSameMonth(profitLoss.getLastMonth(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getLastMonth(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice() * entity.getContent();
    			}
    		}
    	}
    	
    	return currentLimit;
    }
   //去年同期
    private double lastYearleaseData(ProfitLoss profitLoss){
    	
//    		profitLoss.setPeriods(profitLoss.getLastYear());
    	List<ProfitLoss> list  = etopProfitLossDao.searchLastYearMonth(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getLastYear());
    			if(isSameMonth(profitLoss.getLastYear(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
    					int days = daysTime(entity.getStartDate(), entity.getEndDate());
//    					currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
    				}else{//开始结束日期不同月份
    					Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
//    						currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
        				int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
    				}
    			}else if(isSameMonth(profitLoss.getLastYear(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getLastYear(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice() * entity.getContent();
    			}
    		}
    	}
    	
//    		profitLoss.setLastCurrent(currentLimit);
//    	return profitLoss;
    	return currentLimit;
    }
    
    private ProfitLoss leaseDataList(ProfitLoss profitLoss){
    	
    	List<ProfitLoss> list  = etopProfitLossDao.search(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getPeriods());
    			if(isSameMonth(profitLoss.getPeriods(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
    					int days = daysTime(entity.getStartDate(), entity.getEndDate());
//    					currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
    				}else{//开始结束日期不同月份
    					Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
        				int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
        				int day = dayTime(entity.getStartDate(), entity.getEndDate());
    					currentLimit += entity.getTotalAmount() / day * days;
//    						currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				}
    			}else if(isSameMonth(profitLoss.getPeriods(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getPeriods(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
    				Calendar aCalendar = Calendar.getInstance();
    				aCalendar.setTime(entity.getPeriods());
    				int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//    				currentLimit += days * entity.getDailyUnitPrice() * entity.getContent();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//    				currentLimit += entity.getMonthlyUnitPrice() * entity.getContent();
    			}
    		}
    	}
    	profitLoss.setCurrentLimit(currentLimit);
    	return (this.LeaseProfitLoss(profitLoss));
    }

    /**
     * 服务、外包合同（计算本期额度无需乘以面积）
     *
     * @param profitLoss
     */
    private ProfitLoss contractData(ProfitLoss profitLoss){
        List<ProfitLoss> list  = etopProfitLossDao.searchContract(profitLoss);
        double currentLimit = 0.0;
        if(list.size() > 0 && list != null){
            for(ProfitLoss entity : list){
                entity.setPeriods(profitLoss.getPeriods());
                if(isSameMonth(profitLoss.getPeriods(), entity.getStartDate())){//期数在开始月份内
                    int days = daysTime(profitLoss.getPeriods(), entity.getStartDate());
//                    currentLimit += days * entity.getDailyUnitPrice();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
                }else if(isSameMonth(profitLoss.getPeriods(), entity.getEndDate())){//期数在结束月份内
                    int days = daysTime(profitLoss.getPeriods(), entity.getEndDate());
//                    currentLimit += days * entity.getDailyUnitPrice();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
                }else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice();
                }
            }
        }
        profitLoss.setCurrentLimit(currentLimit);
        return (this.insertProfitLoss(profitLoss));
    }
    
    private ProfitLoss contractDataList(ProfitLoss profitLoss){ 
    	List<ProfitLoss> list  = etopProfitLossDao.searchContract(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getPeriods());
    			if(isSameMonth(profitLoss.getPeriods(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
	    				int days = daysTime(entity.getStartDate(), entity.getEndDate());
//	    				currentLimit += days * entity.getDailyUnitPrice();
	    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
	 					currentLimit += entity.getTotalAmount() / day * days;
	    			}else{//开始结束日期不同月份
	    				Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
	 					currentLimit += entity.getTotalAmount() / day * days;
	    			}
    			}else if(isSameMonth(profitLoss.getPeriods(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getPeriods(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
//                    currentLimit += days * entity.getDailyUnitPrice();
//                	currentLimit += entity.getMonthlyUnitPrice();
    			}
    		}
    	}
    	profitLoss.setCurrentLimit(currentLimit);
    	return (this.sourceProfitLoss(profitLoss));
    }
    
    //上期
    private double lastcontractData(ProfitLoss profitLoss){
    	
//    		profitLoss.setPeriods(profitLoss.getLastMonth());
    	List<ProfitLoss> list  = etopProfitLossDao.searchContractLastMonth(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getLastMonth());
    			if(isSameMonth(profitLoss.getLastMonth(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
        				int days = daysTime(entity.getStartDate(), entity.getEndDate());
//        				currentLimit += days * entity.getDailyUnitPrice();
        				int day = dayTime(entity.getStartDate(), entity.getEndDate());
     					currentLimit += entity.getTotalAmount() / day * days;
        			}else{//开始结束日期不同月份
        				Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
     					currentLimit += entity.getTotalAmount() / day * days;
    					
        			}
    			}else if(isSameMonth(profitLoss.getLastMonth(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getLastMonth(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice();
    			}
    		}
    	}
    	  
    	return currentLimit;
    }

    private double lastcontractDatas(ProfitLoss profitLoss){
    	
//    	profitLoss.setPeriods(profitLoss.getLastMonth());
    	List<ProfitLoss> list  = etopProfitLossDao.searchContractLastMonth(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			entity.setPeriods(profitLoss.getLastMonth());
    			if(isSameMonth(profitLoss.getLastMonth(), entity.getStartDate())){//期数在开始月份内
    				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
    					int days = daysTime(entity.getStartDate(), entity.getEndDate());
//    					currentLimit += days * entity.getDailyUnitPrice();
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
     					currentLimit += entity.getTotalAmount() / day * days;
    				}else{//开始结束日期不同月份
    					Calendar aCalendar = Calendar.getInstance();
        				aCalendar.setTime(entity.getPeriods());
    					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
    					int day = dayTime(entity.getStartDate(), entity.getEndDate());
     					currentLimit += entity.getTotalAmount() / day * days;
    				}
    			}else if(isSameMonth(profitLoss.getLastMonth(), entity.getEndDate())){//期数在结束月份内
    				int days = daysTime(profitLoss.getLastMonth(), entity.getEndDate());
//    				currentLimit += days * entity.getDailyUnitPrice();
    				int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
    			}else {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(entity.getPeriods());
                    int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    currentLimit += days * entity.getDailyUnitPrice();
                    int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
//                	currentLimit += entity.getMonthlyUnitPrice();
    			}
    		}
    	}
    	
    	return currentLimit;
    }
   //去年同期
    
    private double lastYearcontractData(ProfitLoss profitLoss){
    	
//		profitLoss.setPeriods(profitLoss.getLastYear());
	List<ProfitLoss> list  = etopProfitLossDao.searchContractLastYearMonth(profitLoss);
	double currentLimit = 0.0;
	if(list.size() > 0 && list != null){
		for(ProfitLoss entity : list){
			entity.setPeriods(profitLoss.getLastYear());
			if(isSameMonth(profitLoss.getLastYear(), entity.getStartDate())){//期数在开始月份内
				if(isSameMonth(entity.getStartDate(), entity.getEndDate())){//开始结束日期同月份
					int days = daysTime(entity.getStartDate(), entity.getEndDate());
//					currentLimit += days * entity.getDailyUnitPrice();
					int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
				}else{//开始结束日期不同月份
					Calendar aCalendar = Calendar.getInstance();
    				aCalendar.setTime(entity.getPeriods());
					int days = MonthOfdaysTime(profitLoss.getPeriods(), entity.getStartDate());
					int day = dayTime(entity.getStartDate(), entity.getEndDate());
 					currentLimit += entity.getTotalAmount() / day * days;
				}
			}else if(isSameMonth(profitLoss.getLastYear(), entity.getEndDate())){//期数在结束月份内
				int days = daysTime(profitLoss.getLastYear(), entity.getEndDate());
//				currentLimit += days * entity.getDailyUnitPrice();
				int day = dayTime(entity.getStartDate(), entity.getEndDate());
				currentLimit += entity.getTotalAmount() / day * days;
			}else {
                Calendar aCalendar = Calendar.getInstance();
                aCalendar.setTime(entity.getPeriods());
                int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                currentLimit += days * entity.getDailyUnitPrice();
                int day = dayTime(entity.getStartDate(), entity.getEndDate());
				currentLimit += entity.getTotalAmount() / day * days;
//            	currentLimit += entity.getMonthlyUnitPrice();
			}
		}
	}
	
	return currentLimit;
}
    /**
     * 能源、园区服务（bill表中拿）
     *
     * @param profitLoss
     */
    private ProfitLoss serviceData(ProfitLoss profitLoss){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        profitLoss.setPeriodsStr(df.format(profitLoss.getPeriods()));
        List<ProfitLoss> list  = etopProfitLossDao.searchService(profitLoss);
        double currentLimit = 0.0;
        if(list.size() > 0 && list != null){
            for(ProfitLoss entity : list){
                currentLimit += entity.getCurrentLimit();
            }
        }
        profitLoss.setCurrentLimit(currentLimit);
        return (this.insertProfitLoss(profitLoss));
    }
    
    private ProfitLoss serviceDataList(ProfitLoss profitLoss){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
    	profitLoss.setPeriodsStr(df.format(profitLoss.getPeriods()));
    	List<ProfitLoss> list  = etopProfitLossDao.searchService(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			currentLimit += entity.getCurrentLimit();
    		}
    	}
    	profitLoss.setCurrentLimit(currentLimit);
    	return (this.ServiceProfitLoss(profitLoss));
    }
    
    private double serviceLastMonth(ProfitLoss profitLoss){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
    	profitLoss.setPeriodsStr(df.format(profitLoss.getLastMonth()));
    	List<ProfitLoss> list  = etopProfitLossDao.searchService(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			currentLimit += entity.getCurrentLimit();
    		}
    	}
    	return currentLimit;
    }
    
    private double serviceLastYear(ProfitLoss profitLoss){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
    	profitLoss.setPeriodsStr(df.format(profitLoss.getLastYear()));
    	List<ProfitLoss> list  = etopProfitLossDao.searchService(profitLoss);
    	double currentLimit = 0.0;
    	if(list.size() > 0 && list != null){
    		for(ProfitLoss entity : list){
    			currentLimit += entity.getCurrentLimit();
    		}
    	}
    	return currentLimit;
    }

    private void insertFine(ProfitLoss profitLoss){
        if(profitLoss.getMultiPeriod() == null){
        	
            DecimalFormat df = new DecimalFormat("#.00%");
            Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(profitLoss));//上期
            Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(profitLoss));//去年同期
            if(previousLimit == null){
                previousLimit = 0.0;
            }
            if(lastCurrent == null){
                lastCurrent = 0.0;
            }

            if(!lastCurrent.equals(0.0) && lastCurrent != null){
                String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrent) - 1);//同比增长
                profitLoss.setYearGrowth(yearGrowth);
            }else {
                profitLoss.setYearGrowth("0");
            }
            if(!previousLimit.equals(0.0) && previousLimit != null){
                String momGrowth = df.format((profitLoss.getCurrentLimit() / previousLimit) - 1);//环比增长
                profitLoss.setMomGrowth(momGrowth);
            }else {
                profitLoss.setMomGrowth("0");
            }

            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(profitLoss.getPeriods());
            aCalendar.add(Calendar.MONTH, -1);
            profitLoss.setPeriods(aCalendar.getTime());

            Double yearTotal = etopProfitLossDao.getSumYear(profitLoss);

            Double yearCumulative = 0.0;
            if(yearTotal == null){
                yearCumulative  =  profitLoss.getCurrentLimit();//年累计
            }else {

                yearCumulative = etopProfitLossDao.getSumYear(profitLoss) + profitLoss.getCurrentLimit();//年累计
            }

            aCalendar.setTime(profitLoss.getPeriods());
            aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
            profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

            Double lastYearPeriod = etopProfitLossDao.getLastyearPeriod(profitLoss);//去年年累计

            if(lastYearPeriod == null){
                lastYearPeriod = 0.0;
            }

            Double backTotal = etopProfitLossDao.getPushMonths(profitLoss) + profitLoss.getCurrentLimit();//倒推12月合计

            if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
                String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
                profitLoss.setLastyearGrowth(lastYearGrowth);
            }else {
                profitLoss.setLastyearGrowth("0");
            }

            profitLoss.setLastCurrent(lastCurrent);
            profitLoss.setPreviousLimit(previousLimit);
            profitLoss.setYearCumulative(yearCumulative);
            profitLoss.setLastyearPeriod(lastYearPeriod);
            profitLoss.setBackTotal(backTotal);
            profitLoss.setPeriods(nextMonth(profitLoss).getPeriods());
            profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
            etopProfitLossDao.insert(profitLoss);
        }else {
            //期数分期情况
            List<Date> list = new ArrayList<>();
            list.add(profitLoss.getPeriods());
            Date periods = profitLoss.getPeriods();
            for(int i=1;i<profitLoss.getMultiPeriod();i++){
                int j = 1;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(periods); // 设置为当前时间
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + j); // 设置为下一个月
                periods = calendar.getTime();
                list.add(periods);
                ProfitLoss fitLoss =new ProfitLoss();
                fitLoss.setItems(profitLoss.getItems());
                fitLoss.setFine(profitLoss.getFine());
                fitLoss.setParkId(profitLoss.getParkId());
                fitLoss.setMoreperiods(periods);
                etopProfitLossDao.deleteExist(fitLoss);
            }
            for(int i=0;i<list.size();i++){
                profitLoss.setPeriods(list.get(i));
                if(i>0){
                    profitLoss.setCurrentLimit(profitLoss.getCurrentLimit());
                }else {
                    profitLoss.setCurrentLimit(profitLoss.getCurrentLimit() / profitLoss.getMultiPeriod());
                }
                DecimalFormat df = new DecimalFormat("#.00%");
                Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(profitLoss));//上期
                Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(profitLoss));//去年同期
                if(previousLimit == null){
                    previousLimit = 0.0;
                }
                if(lastCurrent == null){
                    lastCurrent = 0.0;
                }

                if(!lastCurrent.equals(0.0) && lastCurrent != null){
                    String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrent) - 1);//同比增长
                    profitLoss.setYearGrowth(yearGrowth);
                }else {
                    profitLoss.setYearGrowth("0");
                }
                if(!previousLimit.equals(0.0) && previousLimit != null){
                    String momGrowth = df.format((profitLoss.getCurrentLimit() / previousLimit) - 1);//环比增长
                    profitLoss.setMomGrowth(momGrowth);
                }else {
                    profitLoss.setMomGrowth("0");
                }

                Calendar aCalendar = Calendar.getInstance();
                aCalendar.setTime(profitLoss.getPeriods());
                aCalendar.add(Calendar.MONTH, -1);
                profitLoss.setPeriods(aCalendar.getTime());
                Double yearTotal = etopProfitLossDao.getSumYear(profitLoss);

                Double yearCumulative = 0.0;
                if(yearTotal == null){
                    yearCumulative  =  profitLoss.getCurrentLimit();//年累计
                }else {
                    yearCumulative = etopProfitLossDao.getSumYear(profitLoss) + profitLoss.getCurrentLimit();//年累计
                }

                aCalendar.setTime(profitLoss.getPeriods());
                aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
                profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

                Double lastYearPeriod = etopProfitLossDao.getLastyearPeriod(profitLoss);//去年年累计

                if(lastYearPeriod == null){
                    lastYearPeriod = 0.0;
                }

                Double backTotal = etopProfitLossDao.getPushMonths(profitLoss) + profitLoss.getCurrentLimit();//倒推12月合计

                if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
                    String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
                    profitLoss.setLastyearGrowth(lastYearGrowth);
                }else {
                    profitLoss.setLastyearGrowth("0");
                }

                profitLoss.setLastCurrent(lastCurrent);
                profitLoss.setPreviousLimit(previousLimit);
                profitLoss.setYearCumulative(yearCumulative);
                profitLoss.setLastyearPeriod(lastYearPeriod);
                profitLoss.setBackTotal(backTotal);
                profitLoss.setPeriods(nextMonth(profitLoss).getPeriods());
                profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
                etopProfitLossDao.insert(profitLoss);
            }
        }

    }

    private ProfitLoss insertProfitLoss(ProfitLoss profitLoss){
        Date periods = profitLoss.getPeriods();
/*        DecimalFormat df = new DecimalFormat("#.00%");
        Double previousLimit = etopProfitLossDao.getLastMonthMoney(daysTime(profitLoss));//上期
        Double lastCurrent = etopProfitLossDao.getLastYearMoney(lastYearTime(profitLoss));//去年同期
        if(previousLimit == null){
            previousLimit = 0.0;
        }
        if(lastCurrent == null){
            lastCurrent = 0.0;
        }

        if(!lastCurrent.equals(0.0) && lastCurrent != null){
            String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrent) - 1);//同比增长
            profitLoss.setYearGrowth(yearGrowth);
        }else {
            profitLoss.setYearGrowth("0");
        }
        if(!previousLimit.equals(0.0) && previousLimit != null){
            String momGrowth = df.format((profitLoss.getCurrentLimit() / previousLimit) - 1);//环比增长
            profitLoss.setMomGrowth(momGrowth);
        }else {
            profitLoss.setMomGrowth("0");
        }

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(profitLoss.getPeriods());
        aCalendar.add(Calendar.MONTH, -1);
        profitLoss.setPeriods(aCalendar.getTime());

        Double yearTotal = etopProfitLossDao.getSumYear(profitLoss);

        Double yearCumulative = 0.0;
        if(yearTotal == null){
            yearCumulative  =  profitLoss.getCurrentLimit();//年累计
        }else {

            yearCumulative = etopProfitLossDao.getSumYear(profitLoss) + profitLoss.getCurrentLimit();//年累计
        }

        aCalendar.setTime(profitLoss.getPeriods());
        aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
        profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月

        Double lastYearPeriod = etopProfitLossDao.getLastyearPeriod(profitLoss);//去年年累计

        if(lastYearPeriod == null){
            lastYearPeriod = 0.0;
        }

        Double backTotal = etopProfitLossDao.getPushMonths(profitLoss) + profitLoss.getCurrentLimit();//倒推12月合计

        if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
            String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
            profitLoss.setLastyearGrowth(lastYearGrowth);
        }else {
            profitLoss.setLastyearGrowth("0");
        }

        profitLoss.setPeriods(periods);
        profitLoss.setLastCurrent(lastCurrent);
        profitLoss.setPreviousLimit(previousLimit);
        profitLoss.setYearCumulative(yearCumulative);
        profitLoss.setLastyearPeriod(lastYearPeriod);
        profitLoss.setBackTotal(backTotal);*/
        profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopProfitLossDao.insert(profitLoss);

        ProfitLoss entity = new ProfitLoss();
        entity.setPeriods(profitLoss.getPeriods());
        entity.setReportType(1);
        entity.setParkId(profitLoss.getParkId());
        entity.setItems(1);
        entity.setPeriods(periods);
        return entity;
    }
   
    private ProfitLoss LeaseProfitLoss(ProfitLoss profitLoss){
    	Date periods = profitLoss.getPeriods();
    	DecimalFormat df = new DecimalFormat("#.00%");
    	DecimalFormat dfs = new DecimalFormat("#.00");
//    	if(profitLoss.getFine() == 1 && profitLoss.getItems() == 1){
        Double previous = this.lastleaseData(daysTime(profitLoss));//上期
        Double lastCurrentLimit = this.lastYearleaseData(lastYearTime(profitLoss));//去年同期
        if(previous == null){
        	previous = 0.0; 
        }
        if(lastCurrentLimit == null){
        	lastCurrentLimit = 0.0;
        }

        if(!lastCurrentLimit.equals(0.0) && lastCurrentLimit != null){
            String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrentLimit) - 1);//同比增长
            profitLoss.setYearGrowth(yearGrowth);
        }else {
            profitLoss.setYearGrowth("0");
        }
        if(!previous.equals(0.0) && previous != null){
            String momGrowth = df.format((profitLoss.getCurrentLimit() / previous) - 1);//环比增长
            profitLoss.setMomGrowth(momGrowth);
        }else {
            profitLoss.setMomGrowth("0");
        }
        
        Calendar aCalendar=Calendar.getInstance();   
        int year = Integer.valueOf(profitLoss.getPeriods().toString().substring(profitLoss.getPeriods().toString().length()-4)).intValue();
        aCalendar.set(Calendar.YEAR, year);  
        aCalendar.set(Calendar.MONTH, 0);
        aCalendar.set(Calendar.DAY_OF_MONTH, 1);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);
        aCalendar.set(Calendar.SECOND,0);
        aCalendar.set(Calendar.MINUTE,0);
        profitLoss.setYearFirst(aCalendar.getTime());
        
        aCalendar.set(Calendar.YEAR, year-1);  
        aCalendar.set(Calendar.MONTH, 0);
        aCalendar.set(Calendar.DAY_OF_MONTH, 1);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);
        aCalendar.set(Calendar.SECOND,0);
        aCalendar.set(Calendar.MINUTE,0);
        profitLoss.setLastyearFirst(aCalendar.getTime());
         
//       String formatDate = DateFormat.getDateInstance().format(profitLoss.getPeriods());
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd " );
        String formatDate = sdf.format(profitLoss.getPeriods());
       int month=Integer.valueOf(formatDate.substring(5,7)).intValue();
       Double yearCumulative =0.0;
       Double currentLimit =0.0;
       profitLoss.setLastMonth(profitLoss.getPeriods());
        	for(int i=1;i<=month-1;i++){
		        if(profitLoss.getYearFirst().compareTo(profitLoss.getLastMonth()) != 0){
		        	currentLimit = this.lastleaseDatas(daysTimeOflastMonth(profitLoss));
		        	 yearCumulative += currentLimit;
	        }
       }
         yearCumulative = yearCumulative + profitLoss.getCurrentLimit();//年累计
         
//         String format = DateFormat.getDateInstance().format(profitLoss.getLastYear());
         String format = sdf.format(profitLoss.getPeriods());
         int lastyearmonth=Integer.valueOf(format.substring(5,7)).intValue();
         profitLoss.setLastMonth(profitLoss.getLastYear());
         Double lastYearPeriod =this.lastleaseDatas(profitLoss);
         for(int i=1;i<=lastyearmonth-1;i++){
        	 if(profitLoss.getLastyearFirst().compareTo(profitLoss.getLastYear()) != 0){
        		 currentLimit = this.lastleaseDatas(daysTimeOflastMonth(profitLoss));
        		 lastYearPeriod += currentLimit;//去年年累计
        	 }
         }
         
         profitLoss.setLastMonth(profitLoss.getPeriods());
         Double backTotal =this.lastleaseDatas(profitLoss);
         for(int i=1;i<12;i++){
        	 if(profitLoss.getLastMonth().compareTo(profitLoss.getLastYear()) != 0){
        		 currentLimit = this.lastleaseDatas(daysTimeOflastMonth(profitLoss));
        		 backTotal += currentLimit;//倒推12月合计
        	 }
         }
        if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
            String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
            profitLoss.setLastyearGrowth(lastYearGrowth);
        }else {
            profitLoss.setLastyearGrowth("0");
        }

        profitLoss.setPeriods(periods);
        profitLoss.setLastCurrent(lastCurrentLimit);
        profitLoss.setPreviousLimit(previous);
        profitLoss.setYearCumulative(yearCumulative);
        profitLoss.setLastyearPeriod(lastYearPeriod);
        profitLoss.setBackTotal(Double.valueOf(dfs.format(backTotal)));
    	profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	
    	ProfitLoss entity = new ProfitLoss();
    	entity.setPeriods(profitLoss.getPeriods());
    	entity.setReportType(1);
    	entity.setParkId(profitLoss.getParkId());
    	entity.setItems(1);
    	entity.setPeriods(periods);
    	entity.setCurrentLimit(Double.valueOf(dfs.format(profitLoss.getCurrentLimit())));
    	entity.setLastCurrent(Double.valueOf(dfs.format(profitLoss.getLastCurrent())));
    	entity.setPreviousLimit(Double.valueOf(dfs.format(profitLoss.getPreviousLimit())));
    	entity.setMomGrowth(profitLoss.getMomGrowth());
    	entity.setYearGrowth(profitLoss.getYearGrowth());
    	entity.setYearCumulative(Double.valueOf(dfs.format(profitLoss.getYearCumulative())));
    	entity.setLastyearPeriod(Double.valueOf(dfs.format(profitLoss.getLastyearPeriod())));
    	entity.setLastyearGrowth(profitLoss.getLastyearGrowth());
    	entity.setBackTotal(Double.valueOf(dfs.format(profitLoss.getBackTotal())));
    	entity.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	return entity;
    }
   
    private ProfitLoss sourceProfitLoss(ProfitLoss profitLoss){
    	Date periods = profitLoss.getPeriods();
    	DecimalFormat df = new DecimalFormat("#.00%");
    	DecimalFormat dfs = new DecimalFormat("#.00");
//    	if(profitLoss.getFine() == 1 && profitLoss.getItems() == 2{
    	Double previous = this.lastcontractData(daysTime(profitLoss));//上期
    	Double lastCurrentLimit = this.lastYearcontractData(lastYearTime(profitLoss));//去年同期
    	if(previous == null){
    		previous = 0.0; 
    	}
    	if(lastCurrentLimit == null){
    		lastCurrentLimit = 0.0;
    	}
    	
    	if(!lastCurrentLimit.equals(0.0) && lastCurrentLimit != null){
    		String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrentLimit) - 1);//同比增长
    		profitLoss.setYearGrowth(yearGrowth);
    	}else {
    		profitLoss.setYearGrowth("0");
    	}
    	if(!previous.equals(0.0) && previous != null){
    		String momGrowth = df.format((profitLoss.getCurrentLimit() / previous) - 1);//环比增长
    		profitLoss.setMomGrowth(momGrowth);
    	}else {
    		profitLoss.setMomGrowth("0");
    	}
    	
    	Calendar aCalendar=Calendar.getInstance();   
    	int year = Integer.valueOf(profitLoss.getPeriods().toString().substring(profitLoss.getPeriods().toString().length()-4)).intValue();
    	aCalendar.set(Calendar.YEAR, year);  
    	aCalendar.set(Calendar.MONTH, 0);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	aCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	aCalendar.set(Calendar.SECOND,0);
    	aCalendar.set(Calendar.MINUTE,0);
    	profitLoss.setYearFirst(aCalendar.getTime());
    	
    	aCalendar.set(Calendar.YEAR, year-1);  
    	aCalendar.set(Calendar.MONTH, 0);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	aCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	aCalendar.set(Calendar.SECOND,0);
    	aCalendar.set(Calendar.MINUTE,0);
    	profitLoss.setLastyearFirst(aCalendar.getTime());
    	
        aCalendar.setTime(profitLoss.getPeriods());
        aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
        profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月
        
//    	String formatDate = DateFormat.getDateInstance().format(profitLoss.getPeriods());
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd " );
        String formatDate = sdf.format(profitLoss.getPeriods());
    	int month=Integer.valueOf(formatDate.substring(5,7)).intValue();
    	Double yearCumulative =0.0;
    	Double currentLimit =0.0;
    	profitLoss.setLastMonth(profitLoss.getPeriods());
    	for(int i=1;i<=month-1;i++){
    		if(profitLoss.getYearFirst().compareTo(profitLoss.getLastMonth()) != 0){
    			currentLimit = this.lastcontractDatas(daysTimeOflastMonth(profitLoss));
    			yearCumulative += currentLimit;
    		}
    	}
    	yearCumulative = yearCumulative + profitLoss.getCurrentLimit();//年累计
    	
//    	String format = DateFormat.getDateInstance().format(profitLoss.getLastYear());
        String format = sdf.format(profitLoss.getPeriods());
    	int lastyearmonth=Integer.valueOf(format.substring(5,7)).intValue();
    	profitLoss.setLastMonth(profitLoss.getLastYear());
    	Double lastYearPeriod =this.lastcontractDatas(profitLoss);
    	for(int i=1;i<=lastyearmonth-1;i++){
    		if(profitLoss.getLastyearFirst().compareTo(profitLoss.getLastYear()) != 0){
    			currentLimit = this.lastcontractDatas(daysTimeOflastMonth(profitLoss));
    			lastYearPeriod += currentLimit;//去年年累计
    		}
    	}
    	
    	profitLoss.setLastMonth(profitLoss.getPeriods());
    	Double backTotal =this.lastcontractDatas(profitLoss);
    	for(int i=1;i<12;i++){
    		if(profitLoss.getLastMonth().compareTo(profitLoss.getLastYear()) != 0){
    			currentLimit = this.lastcontractDatas(daysTimeOflastMonth(profitLoss));
    			backTotal += currentLimit;//倒推12月合计
    		}
    	}
    	
    	if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
    		String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
    		profitLoss.setLastyearGrowth(lastYearGrowth);
    	}else {
    		profitLoss.setLastyearGrowth("0");
    	}
    	
    	profitLoss.setPeriods(periods);
    	profitLoss.setLastCurrent(lastCurrentLimit);
    	profitLoss.setPreviousLimit(previous);
    	profitLoss.setYearCumulative(yearCumulative);
    	profitLoss.setLastyearPeriod(lastYearPeriod);
    	profitLoss.setBackTotal(backTotal);
    	profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	
    	ProfitLoss entity = new ProfitLoss();
    	entity.setPeriods(profitLoss.getPeriods());
    	entity.setReportType(1);
    	entity.setParkId(profitLoss.getParkId());
    	entity.setItems(1);
    	entity.setPeriods(periods);
    	entity.setCurrentLimit(Double.valueOf(dfs.format(profitLoss.getCurrentLimit())));
    	entity.setLastCurrent(Double.valueOf(dfs.format(profitLoss.getLastCurrent())));
    	entity.setPreviousLimit(Double.valueOf(dfs.format(profitLoss.getPreviousLimit())));
    	entity.setMomGrowth(profitLoss.getMomGrowth());
    	entity.setYearGrowth(profitLoss.getYearGrowth());
    	entity.setYearCumulative(Double.valueOf(dfs.format(profitLoss.getYearCumulative())));
    	entity.setLastyearPeriod(Double.valueOf(dfs.format(profitLoss.getLastyearPeriod())));
    	entity.setLastyearGrowth(profitLoss.getLastyearGrowth());
    	entity.setBackTotal(Double.valueOf(dfs.format(profitLoss.getBackTotal())));
    	entity.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	return entity;
    }
    
    private ProfitLoss ServiceProfitLoss(ProfitLoss profitLoss){
    	Date periods = profitLoss.getPeriods();
    	DecimalFormat df = new DecimalFormat("#.00%");
    	DecimalFormat dfs = new DecimalFormat("#.00");
    	
    	Double previous = this.serviceLastMonth(daysTime(profitLoss));//上期
    	Double lastCurrentLimit = this.serviceLastYear(lastYearTime(profitLoss));//去年同期
    	if(previous == null){
    		previous = 0.0; 
    	}
    	if(lastCurrentLimit == null){
    		lastCurrentLimit = 0.0;
    	}
    	
    	if(!lastCurrentLimit.equals(0.0) && lastCurrentLimit != null){
    		String yearGrowth = df.format((profitLoss.getCurrentLimit() / lastCurrentLimit) - 1);//同比增长
    		profitLoss.setYearGrowth(yearGrowth);
    	}else {
    		profitLoss.setYearGrowth("0");
    	}
    	if(!previous.equals(0.0) && previous != null){
    		String momGrowth = df.format((profitLoss.getCurrentLimit() / previous) - 1);//环比增长
    		profitLoss.setMomGrowth(momGrowth);
    	}else {
    		profitLoss.setMomGrowth("0");
    	}
    	
    	Calendar aCalendar=Calendar.getInstance();   
    	int year = Integer.valueOf(profitLoss.getPeriods().toString().substring(profitLoss.getPeriods().toString().length()-4)).intValue();
    	aCalendar.set(Calendar.YEAR, year);  
    	aCalendar.set(Calendar.MONTH, 0);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	aCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	aCalendar.set(Calendar.SECOND,0);
    	aCalendar.set(Calendar.MINUTE,0);
    	profitLoss.setYearFirst(aCalendar.getTime());
    	
    	aCalendar.set(Calendar.YEAR, year-1);  
    	aCalendar.set(Calendar.MONTH, 0);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	aCalendar.set(Calendar.HOUR_OF_DAY, 0);
    	aCalendar.set(Calendar.SECOND,0);
    	aCalendar.set(Calendar.MINUTE,0);
    	profitLoss.setLastyearFirst(aCalendar.getTime());
    	
    	aCalendar.setTime(profitLoss.getPeriods());
    	aCalendar.set(Calendar.YEAR,aCalendar.get(Calendar.YEAR)-1);
    	profitLoss.setPushMonths(aCalendar.getTime());//倒推12个月
    	
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd " );
        String formatDate = sdf.format(profitLoss.getPeriods());
//    	String formatDate = DateFormat.getDateInstance().format(profitLoss.getPeriods());
    	int month=Integer.valueOf(formatDate.substring(5,7)).intValue();
    	Double yearCumulative =0.0;
    	Double currentLimit =0.0;
    	profitLoss.setLastMonth(profitLoss.getPeriods());
    	for(int i=1;i<=month-1;i++){
    		if(profitLoss.getYearFirst().compareTo(profitLoss.getLastMonth()) != 0){
    			currentLimit = this.serviceLastMonth(daysTimeOflastMonth(profitLoss));
    			yearCumulative += currentLimit;
    		}
    	}
    	yearCumulative = yearCumulative + profitLoss.getCurrentLimit();//年累计
    	
//    	String format = DateFormat.getDateInstance().format(profitLoss.getLastYear());
        String format = sdf.format(profitLoss.getPeriods());
    	int lastyearmonth=Integer.valueOf(format.substring(5,7)).intValue();
    	profitLoss.setLastMonth(profitLoss.getLastYear());
    	Double lastYearPeriod =this.serviceLastMonth(profitLoss);
    	for(int i=1;i<=lastyearmonth-1;i++){
    		if(profitLoss.getLastyearFirst().compareTo(profitLoss.getLastYear()) != 0){
    			currentLimit = this.serviceLastMonth(daysTimeOflastMonth(profitLoss));
    			lastYearPeriod += currentLimit;//去年年累计
    		}
    	}
    	
    	profitLoss.setLastMonth(profitLoss.getPeriods());
    	Double backTotal =this.serviceLastMonth(profitLoss);
    	for(int i=1;i<12;i++){
    		if(profitLoss.getLastMonth().compareTo(profitLoss.getLastYear()) != 0){
    			currentLimit = this.serviceLastMonth(daysTimeOflastMonth(profitLoss));
    			backTotal += currentLimit;//倒推12月合计
    		}
    	}
    	
    	if(lastYearPeriod != null && !lastYearPeriod.equals(0.0)){
    		String lastYearGrowth =  df.format((yearCumulative / lastYearPeriod) - 1);//同比增长
    		profitLoss.setLastyearGrowth(lastYearGrowth);
    	}else {
    		profitLoss.setLastyearGrowth("0");
    	}
    	
    	profitLoss.setPeriods(periods);
    	profitLoss.setLastCurrent(lastCurrentLimit);
    	profitLoss.setPreviousLimit(previous);
    	profitLoss.setYearCumulative(yearCumulative);
    	profitLoss.setLastyearPeriod(lastYearPeriod);
    	profitLoss.setBackTotal(backTotal);
    	profitLoss.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	
    	ProfitLoss entity = new ProfitLoss();
    	entity.setPeriods(profitLoss.getPeriods());
    	entity.setReportType(1);
    	entity.setParkId(profitLoss.getParkId());
    	entity.setItems(1);
    	entity.setPeriods(periods);
    	entity.setCurrentLimit(Double.valueOf(dfs.format(profitLoss.getCurrentLimit())));
    	entity.setLastCurrent(Double.valueOf(dfs.format(profitLoss.getLastCurrent())));
    	entity.setPreviousLimit(Double.valueOf(dfs.format(profitLoss.getPreviousLimit())));
    	entity.setMomGrowth(profitLoss.getMomGrowth());
    	entity.setYearGrowth(profitLoss.getYearGrowth());
    	entity.setYearCumulative(Double.valueOf(dfs.format(profitLoss.getYearCumulative())));
    	entity.setLastyearPeriod(Double.valueOf(dfs.format(profitLoss.getLastyearPeriod())));
    	entity.setLastyearGrowth(profitLoss.getLastyearGrowth());
    	entity.setBackTotal(Double.valueOf(dfs.format(profitLoss.getBackTotal())));
    	entity.setCreatedBy(UserInfoUtil.getUserInfo().getId());
    	return entity;
    }

    private ProfitLoss getData(ProfitLoss entity,Integer type){
        DecimalFormat df = new DecimalFormat("#.00%");
        String yearGrowth = "0";
        String momGrowth = "0";
        String lastYearGrowth = "0";
        if(entity.getLastCurrent() != 0){
            yearGrowth = df.format((entity.getCurrentLimit() / entity.getLastCurrent()) - 1);//同比增长
            entity.setYearGrowth(yearGrowth);
        }else {
            entity.setYearGrowth("0");
        }
        if(entity.getPreviousLimit() != 0){
            momGrowth = df.format((entity.getCurrentLimit() / entity.getPreviousLimit()) - 1);//环比增长
            entity.setMomGrowth(momGrowth);
        }else {
            entity.setMomGrowth("0");
        }
        if(entity.getLastyearPeriod() != 0){
            lastYearGrowth =  df.format((entity.getYearCumulative()/ entity.getLastyearPeriod()) - 1);//同比增长
            entity.setLastyearGrowth(lastYearGrowth);
        }else {
            entity.setLastyearGrowth("0");
        }
        if(type == 1){
            entity.setItems(13);//收入合计
        }else if(type == 2){
            entity.setItems(14);//支出合计
        }
        entity.setCurrentLimit(entity.getCurrentLimit());
        entity.setLastCurrent(entity.getLastCurrent());
        entity.setPreviousLimit(entity.getPreviousLimit());
        entity.setYearCumulative(entity.getYearCumulative());
        entity.setLastyearPeriod(entity.getLastyearPeriod());
        entity.setBackTotal(entity.getBackTotal());
        return entity;
    }
   
    private ProfitLoss getData2(ProfitLoss entity,Integer type){
    	DecimalFormat df = new DecimalFormat("#.00%");
    	String yearGrowth = "0";
    	String momGrowth = "0";
    	Double yearCumulative =0.0;
    	Double lastCurrent =0.0;
    	Double lastMonthCumulative =0.0;
    	
        Double yearTotal = etopProfitLossDao.getProfitLastLossList(lastYearTime(entity));
        
	      if(yearTotal == null){
	    	  lastCurrent  =  entity.getCurrentLimit();//上一年年收支
	      }else {
	    	  lastCurrent = etopProfitLossDao.getProfitLastLossList(lastYearTime(entity));//上一年年收支
	      }
	      entity.setLastCurrent(lastCurrent);
	      
    	if(entity.getLastCurrent() != 0){
    		yearGrowth = df.format((entity.getCurrentLimit() / yearCumulative) - 1);//同比增长
    		entity.setYearGrowth(yearGrowth);
    	}else {
    		entity.setYearGrowth("0");
    	}
    	
        Double previousLimit = etopProfitLossDao.getProfitMonthLossList(daysTime(entity));
     
	      if(previousLimit == null){
	    	  lastMonthCumulative  =  0.0;//年上期收支
	      }else {
	    	  lastMonthCumulative = etopProfitLossDao.getProfitMonthLossList(daysTime(entity));//年上期收支
	      }
	      entity.setPreviousLimit(lastMonthCumulative);
	      
	      
	      Calendar currCal=Calendar.getInstance();   
          int year = Integer.valueOf(entity.getPeriods().toString().substring(entity.getPeriods().toString().length()-4)).intValue();
          currCal.set(Calendar.YEAR, year);  
          currCal.set(Calendar.MONTH, 0);
          currCal.set(Calendar.DAY_OF_MONTH, 1);
          currCal.set(Calendar.HOUR_OF_DAY, 0);
          currCal.set(Calendar.SECOND,0);
          currCal.set(Calendar.MINUTE,0);
          entity.setYearFirst(currCal.getTime());
          
          Double yearTotals = etopProfitLossDao.getSumYearAllYear(entity);
          
          if(yearTotals == null){
              yearCumulative  =  entity.getCurrentLimit();//年累计
          }else {
              yearCumulative = etopProfitLossDao.getSumYearAllYear(entity);//年累计
          }
          entity.setYearCumulative(yearCumulative);
	      
    	if(entity.getPreviousLimit() != 0 && !lastMonthCumulative.equals(0.0)){
    		momGrowth = df.format((entity.getCurrentLimit() / lastMonthCumulative) - 1);//环比增长
    		entity.setMomGrowth(momGrowth);
    	}else {
    		entity.setMomGrowth("0");
    	}
    	
    	currCal.set(Calendar.YEAR, year-1);  
        currCal.set(Calendar.MONTH, 0);
        currCal.set(Calendar.DAY_OF_MONTH, 1);
        currCal.set(Calendar.HOUR_OF_DAY, 0);
        currCal.set(Calendar.SECOND,0);
        currCal.set(Calendar.MINUTE,0);
        entity.setYearFirst(currCal.getTime());
        
        Double lastyearTotal = etopProfitLossDao.getSumYearLastYear(lastYearTime(entity));
        Double lastyearPeriod = 0.0;
        if(lastyearTotal == null){
        	lastyearPeriod  =  entity.getCurrentLimit();//去年年累计
        }else {
        	lastyearPeriod = etopProfitLossDao.getSumYearLastYear(lastYearTime(entity));//去年年累计
        }
        entity.setLastyearPeriod(lastyearPeriod);
        
        if(entity.getLastyearPeriod() != null && !entity.getLastyearPeriod().equals(0.0)){
            String lastYearGrowth =  df.format((entity.getYearCumulative() / entity.getLastyearPeriod()) - 1);//同比增长
            entity.setLastyearGrowth(lastYearGrowth);
        }else {
        	entity.setLastyearGrowth("0");
        }
        
        currCal.setTime(entity.getPeriods());
        currCal.set(Calendar.YEAR,currCal.get(Calendar.YEAR)-1);
        entity.setPushMonths(currCal.getTime());//倒推12个月

        Double backTotal = etopProfitLossDao.getYearPushMonths(entity);//倒推12月合计
        entity.setBackTotal(backTotal);

    	if(type == 1){
    		entity.setItems(13);//收入合计
    	}else if(type == 2){
    		entity.setItems(14);//支出合计
    	}
    	entity.setCurrentLimit(entity.getCurrentLimit());
//    	entity.setLastCurrent(entity.getLastCurrent());
//    	entity.setPreviousLimit(entity.getPreviousLimit());
//    	entity.setYearCumulative(entity.getYearCumulative());
//    	entity.setLastyearPeriod(entity.getLastyearPeriod());
//    	entity.setBackTotal(entity.getBackTotal());
    	return entity;
    }

    /**
     * 收支合计
     */
    private ProfitLoss getProfitLossData(ProfitLoss bo, ProfitLoss vo){
        DecimalFormat df = new DecimalFormat("#.00%");
        ProfitLoss profitLoss = new ProfitLoss();
        Double currentLimit = bo.getCurrentLimit() - vo.getCurrentLimit();//本期额度
        Double lastCurrent = bo.getLastCurrent() - vo.getLastCurrent();//去年同期
        Double previousLimit = bo.getPreviousLimit() - vo.getPreviousLimit();//上期额度
        Double yearCumulative = bo.getYearCumulative() - vo.getYearCumulative();//年累计
        Double lastyearPeriod = bo.getLastyearPeriod() - vo.getLastyearPeriod();//去年同期
        Double backTotal = bo.getBackTotal() - vo.getBackTotal();//倒推12月合计
        String yearGrowth = "0";
        String momGrowth = "0";
        String lastYearGrowth = "0";
        if(lastCurrent != 0){
            yearGrowth = df.format((currentLimit / lastCurrent) - 1);//同比增长
            profitLoss.setYearGrowth(yearGrowth);
        }else {
            profitLoss.setYearGrowth("0");
        }
        if(previousLimit != 0){
            momGrowth = df.format((currentLimit / previousLimit) - 1);//环比增长
            profitLoss.setMomGrowth(momGrowth);
        }else {
            profitLoss.setMomGrowth("0");
        }
        if(lastyearPeriod != null && !lastyearPeriod.equals(0.0)){
            lastYearGrowth =  df.format((yearCumulative / lastyearPeriod) - 1);//同比增长
            profitLoss.setLastyearGrowth(lastYearGrowth);
        }else {
            profitLoss.setLastyearGrowth("0");
        }
        profitLoss.setItems(15);//合计
        profitLoss.setCurrentLimit(currentLimit);
        profitLoss.setLastCurrent(lastCurrent);
        profitLoss.setPreviousLimit(previousLimit);
        profitLoss.setYearCumulative(yearCumulative);
        profitLoss.setLastyearPeriod(lastyearPeriod);
        profitLoss.setBackTotal(backTotal);
        return profitLoss;
    }

    //判断是否同月份
    private static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        return isSameMonth;
    }

    //计算天数
    /*private static int daysNum(Date date) {

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);

        int startDate = aCalendar.get(Calendar.DAY_OF_MONTH) - 1;
        int endDate = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return endDate - startDate;

    }*/
    //计算天数（期数结束日）
    public static int MonthOfdaysTime(Date endPeriods, Date date2){  
        Calendar aCalendar = Calendar.getInstance();  
        aCalendar.setTime(endPeriods);  
        int days = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
        aCalendar.setTime(date2);
        int day2 = aCalendar.get(Calendar.DAY_OF_MONTH)-1;
        return days - day2; 
}  
    
    
    //计算天数
    private static int daysTime(Date date1, Date date2){
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date1);
        int day1 = aCalendar.get(Calendar.DAY_OF_MONTH) - 1;
        aCalendar.setTime(date2);
        int day2 = aCalendar.get(Calendar.DAY_OF_MONTH);
        return day2 - day1;
    }
    
    //计算天数
    private static int dayTime(Date date1, Date date2){
    	int days = (int)((date2.getTime() - date1.getTime())/86400000);
    	return days;
    }

   
    //计算日期
    private static ProfitLoss daysTime(ProfitLoss profitLoss) {

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(profitLoss.getPeriods());

        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.set(Calendar.DAY_OF_MONTH, 1);
        profitLoss.setLastMonth(aCalendar.getTime());//上一期

        return profitLoss;

    }
    //计算日期
    private static ProfitLoss daysTimeOflastMonth(ProfitLoss profitLoss) {
    	
    	Calendar aCalendar = Calendar.getInstance();
    	aCalendar.setTime(profitLoss.getLastMonth());
    	
    	aCalendar.add(Calendar.MONTH, -1);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	profitLoss.setLastMonth(aCalendar.getTime());//上一期
    	
    	return profitLoss;
    	
    }


    private static ProfitLoss lastYearTime(ProfitLoss profitLoss) {

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(profitLoss.getPeriods());

        aCalendar.set(Calendar.YEAR, aCalendar.get(Calendar.YEAR) - 1);
        aCalendar.set(Calendar.DAY_OF_MONTH, 1);
        profitLoss.setLastYear(aCalendar.getTime());//去年同期

        return profitLoss;

    }
    //今年
    private static ProfitLoss thisYearTime(ProfitLoss profitLoss) {
    	
    	Calendar aCalendar = Calendar.getInstance();
    	aCalendar.setTime(profitLoss.getPeriods());
    	
    	aCalendar.set(Calendar.YEAR, aCalendar.get(Calendar.YEAR) + 1);
    	aCalendar.set(Calendar.DAY_OF_MONTH, 1);
    	profitLoss.setPeriods(aCalendar.getTime());
    	
    	return profitLoss;
    	
    }
    
    private static ProfitLoss nextMonth(ProfitLoss profitLoss) {
    	
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(profitLoss.getPeriods());

        aCalendar.add(Calendar.MONTH, 1);
        aCalendar.set(Calendar.DAY_OF_MONTH, 1);
        profitLoss.setPeriods(aCalendar.getTime());//下个月

        return profitLoss;
    	
    }


    /**
     *
     *
     * @param list
     */
   /* private void getData(Page<ProfitLoss> list){
        DecimalFormat df = new DecimalFormat("#.00%");
        ProfitLoss entity = new ProfitLoss();
        Double currentLimit = 0.0;
        Double lastCurrent = 0.0;
        Double previousLimit = 0.0;
        Double yearCumulative = 0.0;
        Double lastyearPeriod = 0.0;
        Double backTotal = 0.0;
        String yearGrowth = "0";
        String momGrowth = "0";
        String lastYearGrowth = "0";
        for(ProfitLoss vo : list){
            currentLimit += (vo.getCurrentLimit()==null)?0:vo.getCurrentLimit();
            lastCurrent += (vo.getLastCurrent()==null)?0:vo.getLastCurrent();
            previousLimit += (vo.getPreviousLimit()==null)?0:vo.getPreviousLimit();
            yearCumulative += (vo.getYearCumulative()==null)?0:vo.getYearCumulative();
            lastyearPeriod += (vo.getLastyearPeriod()==null)?0:vo.getLastyearPeriod();
            backTotal += (vo.getBackTotal()==null)?0:vo.getBackTotal();
        }
        if(!lastCurrent.equals(0.0) && lastCurrent != null){
            yearGrowth = df.format((currentLimit / lastCurrent) - 1);//同比增长
            entity.setYearGrowth(yearGrowth);
        }else {
            entity.setYearGrowth("0");
        }
        if(!previousLimit.equals(0.0) && previousLimit != null){
            momGrowth = df.format((currentLimit / previousLimit) - 1);//环比增长
            entity.setMomGrowth(momGrowth);
        }else {
            entity.setMomGrowth("0");
        }
        if(lastyearPeriod != null && !lastyearPeriod.equals(0.0)){
            lastYearGrowth =  df.format((yearCumulative / lastyearPeriod) - 1);//同比增长
            entity.setLastyearGrowth(lastYearGrowth);
        }else {
            entity.setLastyearGrowth("0");
        }
        entity.setItems(0);//合计
        entity.setCurrentLimit(currentLimit);
        entity.setLastCurrent(lastCurrent);
        entity.setPreviousLimit(previousLimit);
        entity.setYearCumulative(yearCumulative);
        entity.setLastyearPeriod(lastyearPeriod);
        entity.setBackTotal(backTotal);
        list.add(entity);
    }*/

}
