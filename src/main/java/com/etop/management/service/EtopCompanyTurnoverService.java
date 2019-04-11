package com.etop.management.service;


import com.etop.management.bean.EtopCompanyTurnover;
import com.etop.management.bean.EtopPage;

/**
 * 
 * <br>
 * <b>功能：</b>EtopCompanyTurnoverService<br>
 */
public interface EtopCompanyTurnoverService{

    EtopPage<EtopCompanyTurnover> search(EtopCompanyTurnover etopCompanyTurnover, Integer offset, Integer limit);

    void deleteById(EtopCompanyTurnover etopCompanyTurnover);

    void add(EtopCompanyTurnover etopCompanyTurnover);

    EtopCompanyTurnover getTurnoverInfo(EtopCompanyTurnover etopCompanyTurnover);

    void updateById(EtopCompanyTurnover etopCompanyTurnover);

    EtopCompanyTurnover selectTurnover(EtopCompanyTurnover etopCompanyTurnover);
}
