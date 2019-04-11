package com.etop.management.dao;

import java.util.List;

import com.etop.management.bean.ProfitLoss;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/13
 */
public interface EtopProfitLossDao {

    List<ProfitLoss> search(ProfitLoss profitLoss);//租赁、物业
    
    List<ProfitLoss> searchLastMonth(ProfitLoss profitLoss);//租赁、物业
    
    List<ProfitLoss> searchLastYearMonth(ProfitLoss profitLoss);//租赁、物业

    List<ProfitLoss> searchContract(ProfitLoss profitLoss);//服务、外包
    
    List<ProfitLoss> searchContractLastMonth(ProfitLoss profitLoss);//服务、外包
    
    List<ProfitLoss> searchContractLastYearMonth(ProfitLoss profitLoss);//服务、外包


    List<ProfitLoss> searchService(ProfitLoss profitLoss);//能源、园区服务

    Double getLastMonthMoney(ProfitLoss profitLoss);//上期

    Double getLastYearMoney(ProfitLoss profitLoss);//去年同期

    Double getSumYear(ProfitLoss profitLoss);//年累计

    Double getLastyearPeriod(ProfitLoss profitLoss);//去年年累计

    Double getPushMonths(ProfitLoss profitLoss);//倒推12个月

    Integer insert(ProfitLoss profitLoss);

    Page<ProfitLoss> getProfitLoss(ProfitLoss profitLoss);//收入支出
    
    Page<ProfitLoss> getProfit(ProfitLoss profitLoss);//收入

    Page<ProfitLoss> getLoss(ProfitLoss profitLoss);//支出

    ProfitLoss searchProfitLoss(ProfitLoss profitLoss);

    ProfitLoss getProfitLossList(ProfitLoss profitLoss);
    
    Double getProfitLastLossList(ProfitLoss profitLoss);//收支去年
    
    Double getProfitMonthLossList(ProfitLoss profitLoss);//收支上期
    
    Double getSumAllYear(ProfitLoss profitLoss);//年累计
    
    Double getSumYearAllYear(ProfitLoss profitLoss);//收支年累计
    
    Double getSumYearLastYear(ProfitLoss profitLoss);//收支上一年年累计
    
    Double getSumLastAllYear(ProfitLoss profitLoss);//上一年年累计
    
    Double getYearPushMonths(ProfitLoss profitLoss);//年倒退12个月
    
    int deleteExist(ProfitLoss profitLoss);

}
