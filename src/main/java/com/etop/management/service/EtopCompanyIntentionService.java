package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;

/**
 * Created by Alex.
 *
 * @author 何利庭
 * @DATE 2016/8/23
 */
public interface EtopCompanyIntentionService {

    EtopPage<EtopCompanyIntention> search(EtopCompanyIntention etopCompanyIntention, Integer offset, Integer limit);

    void add(EtopCompanyIntention newEtopCompanyIntention);

    void deleteById(EtopCompanyIntention etopCompanyIntention);

    void updateById(EtopCompanyIntention etopCompanyIntention);

    EtopCompanyIntention getCompInfoById(List<String> id);

    EtopCompanyIntention getCompIntentionInfoById(String companyId);

    void getComIntentionByParkId();

    EtopCompanyIntention selectCompanyIntention(String companyName,String Id, String parkId);
    
    String proveIntentionCompanyName(String companyName, String parkId);
    
    String proveCompanyName(String companyName, String parkId);
}
