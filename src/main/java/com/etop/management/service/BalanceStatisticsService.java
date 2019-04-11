package com.etop.management.service;

import com.etop.management.bean.BalanceStatistics;
import com.etop.management.bean.EtopPage;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/28
 */
public interface BalanceStatisticsService {

    EtopPage<BalanceStatistics> search(BalanceStatistics balanceStatistics, String type);

}
