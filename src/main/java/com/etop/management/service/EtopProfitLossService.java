package com.etop.management.service;


import java.text.ParseException;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ProfitLoss;
import com.etop.management.bean.ResultType;

/**
 * 
 * <br>
 * <b>功能：</b>EtopProfitLossService<br>
 */
public interface EtopProfitLossService{

    void add(ProfitLoss profitLoss) throws ParseException;

    EtopPage<ProfitLoss> search(ProfitLoss profitLoss);
    
    EtopPage<ProfitLoss> newSearch(ProfitLoss profitLoss);

    ResultType addFine(ProfitLoss profitLoss);
    
    ResultType addFine2(ProfitLoss profitLoss);
    
    EtopPage<ProfitLoss> realTime(ProfitLoss profitLoss);
}
