package com.etop.management.dao;

import java.util.List;

import com.etop.management.bean.BalanceStatistics;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/28
 */
public interface BalanceStatisticsDao {

    BalanceStatistics select(BalanceStatistics balanceStatistics);//园区

    Page<BalanceStatistics> searchByFloor(BalanceStatistics balanceStatistics);//楼

    Page<BalanceStatistics> searchByStorey(BalanceStatistics balanceStatistics);//层

    Page<BalanceStatistics> searchByArea(BalanceStatistics balanceStatistics);//区

    List<BalanceStatistics> selectBill(BalanceStatistics balanceStatistics);//不同来源账单信息

    Page<BalanceStatistics> selectTotalBill(BalanceStatistics balanceStatistics);//账单合计

    BalanceStatistics getTatalAmount(BalanceStatistics balanceStatistics);//所有账单合计

    List<BalanceStatistics> getDiffBillAmount(BalanceStatistics balanceStatistics);//不同账单来源欠款合计
}
