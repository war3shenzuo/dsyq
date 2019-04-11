package com.etop.management.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.BalanceStatistics;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.dao.BalanceStatisticsDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.BalanceStatisticsService;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 * 欠款统计
 * @author 何利庭
 * @DATE 2016/11/28
 */
@Service
public class BalanceStatisticsServiceImpl implements BalanceStatisticsService {

    @Autowired
    private BalanceStatisticsDao balanceStatisticsDao;

    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<BalanceStatistics> search(BalanceStatistics balanceStatistics, String type) {

        if(balanceStatistics.getEnd() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(balanceStatistics.getEnd());
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            Date date = calendar.getTime();
            balanceStatistics.setEnd(date);
        }

        if(balanceStatistics.getEndTime() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(balanceStatistics.getEndTime());
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            Date date = calendar.getTime();
            balanceStatistics.setEndTime(date);
        }
        Page<BalanceStatistics> page = new Page<>();
        List<BalanceStatistics> arrayList = null;
        if(("all").equals(type)){
            for(int i=0;i<balanceStatistics.getParkIds().size();i++){
                balanceStatistics.setParkId(balanceStatistics.getParkIds().get(i));
                arrayList = this.typeData(balanceStatistics);
                page.addAll(arrayList);
            }
            Double leaseAmount = 0.0;
            Double serviceAmount = 0.0;
            Double energyAmount = 0.0;
            Double propertyAmount = 0.0;
            Double parkServiceAmount = 0.0;
            Double otherAmount = 0.0;
            Double amount = 0.0;
            Double amountPaid = 0.0;
            Double arrears = 0.0;
            Double overdueFine = 0.0;
            for(BalanceStatistics statistics : page){
                leaseAmount+=statistics.getLeaseAmount();
                serviceAmount+=statistics.getServiceAmount();
                energyAmount+=statistics.getEnergyAmount();
                propertyAmount+=statistics.getPropertyAmount();
                parkServiceAmount+=statistics.getParkServiceAmount();
                otherAmount+=statistics.getOtherAmount();
                amount+=statistics.getAmount();
                amountPaid+=statistics.getAmountPaid();
                arrears+=statistics.getArrears();
                overdueFine+=statistics.getOverdueFine();
            }
            BalanceStatistics statistics = new BalanceStatistics();
            statistics.setLeaseAmount(leaseAmount);
            statistics.setServiceAmount(serviceAmount);
            statistics.setEnergyAmount(energyAmount);
            statistics.setPropertyAmount(propertyAmount);;
            statistics.setParkServiceAmount(parkServiceAmount);
            statistics.setOtherAmount(otherAmount);
            statistics.setAmount(amount + overdueFine);
            statistics.setAmountPaid(amountPaid);
            statistics.setArrears(arrears + overdueFine);
            statistics.setOverdueFine(overdueFine);
            statistics.setParkName("合计");
            page.add(statistics);
        }else {
            Park park = parkDao.getParkInfo(balanceStatistics.getParkId());
            balanceStatistics.setParkName(park.getParkName());
            balanceStatistics.setIsAllPark(1);//是否园区组
            arrayList = this.typeData(balanceStatistics);
            //所有客户合计
            BalanceStatistics bill = balanceStatisticsDao.getTatalAmount(balanceStatistics);
            List<BalanceStatistics> statisticsList = balanceStatisticsDao.getDiffBillAmount(balanceStatistics);//不同账单来源欠款
            Double deposit = 0.0;
            for(BalanceStatistics statistics : statisticsList){
                if(statistics.getBillSource() == 1){//租赁
                    bill.setLeaseAmount(statistics.getAmount());
                }else if(statistics.getBillSource() == 3){//能源
                    bill.setEnergyAmount(statistics.getAmount());
                }else if(statistics.getBillSource() == 4){//物业
                    bill.setPropertyAmount(statistics.getAmount());
                }else if((statistics.getBillSource() == 5)){//服务
                    bill.setServiceAmount(statistics.getAmount());
                }else if((statistics.getBillSource() == 6)){//园区服务
                    bill.setParkServiceAmount(statistics.getAmount());
                }else if((statistics.getBillSource() == 0)){//押金
                	bill.setOtherAmount(statistics.getAmount());
                	deposit =statistics.getAmount();
                }else if((statistics.getBillSource() == 9)){//其他
                	bill.setOtherAmount(statistics.getAmount() + deposit);
                }
            }
            bill.setAmount(bill.getAmount() + bill.getOverdueFine());
            bill.setArrears(bill.getArrears() + bill.getOverdueFine());
            bill.setParkName("合计");
            page.addAll(arrayList);
            page.add(bill);
        }

        return new EtopPage<BalanceStatistics>(page);
    }

    private List<BalanceStatistics> typeData(BalanceStatistics balanceStatistics){
        List<BalanceStatistics> arrayList = new Page<>();
        DecimalFormat dfs = new DecimalFormat("#.00");
        List<BalanceStatistics> list = balanceStatisticsDao.selectBill(balanceStatistics);
        Park park = parkDao.getParkInfo(balanceStatistics.getParkId());
        balanceStatistics.setParkName(park.getParkName());
        if(list != null && list.size() > 0){
            for(BalanceStatistics vo : list){
                vo.setLeaseAmount(0.0);
                vo.setEnergyAmount(0.0);
                vo.setPropertyAmount(0.0);
                vo.setServiceAmount(0.0);
                vo.setParkServiceAmount(0.0);
                vo.setOtherAmount(0.0);
                Double deposit = 0.0;
                for(BalanceStatistics entity : list){

                    if(vo.getCompanyId().equals(entity.getCompanyId())){
                        if(entity.getBillSource() == 1){//租赁
                            vo.setLeaseAmount(entity.getAmount());
                        }else if(entity.getBillSource() == 3){//能源
                            vo.setEnergyAmount(entity.getAmount());
                        }else if(entity.getBillSource() == 4){//物业
                            vo.setPropertyAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 5)){//服务
                            vo.setServiceAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 6)){//园区服务
                            vo.setParkServiceAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 0)){//押金
                        	vo.setOtherAmount(entity.getAmount());
                        	deposit =entity.getAmount();
                        }else if((entity.getBillSource() == 9)){//其他
                            vo.setOtherAmount(entity.getAmount() + deposit);
                        }
                    }
                	
                }
            }
        }

        arrayList = balanceStatisticsDao.selectTotalBill(balanceStatistics);

        
        if(arrayList != null && list != null && arrayList.size() > 0 && list.size() > 0){
            for(BalanceStatistics bo : arrayList){
            	bo.setAmount(Double.valueOf(dfs.format(bo.getAmount() + bo.getOverdueFine())));
            	bo.setArrears(Double.valueOf(dfs.format(bo.getArrears() +  bo.getOverdueFine())));
                for(BalanceStatistics balance: list){
                    if(balance.getCompanyId().equals(bo.getCompanyId())){
                        bo.setLeaseAmount(balance.getLeaseAmount());
                        bo.setEnergyAmount(balance.getEnergyAmount());
                        bo.setPropertyAmount(balance.getPropertyAmount());
                        bo.setServiceAmount(balance.getServiceAmount());
                        bo.setParkServiceAmount(balance.getParkServiceAmount());
                        bo.setOtherAmount(balance.getOtherAmount());
//                        bo.setAmount(balance.getAmount() + balance.getOverdueFine());
//                        bo.setArrears(balance.getArrears() + balance.getOverdueFine());
                    }
                }
            }
        }

        return arrayList;
    }
}
