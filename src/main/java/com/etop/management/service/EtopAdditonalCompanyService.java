package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopCompanyInformation;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
public interface EtopAdditonalCompanyService {

    void addInfo(EtopCompanyInformation etopCompanyInformation, String etopCompanyBusiness);

    EtopCompanyInformation getAdditionCompInfo(String id);

    void updateAdditionalById(EtopCompanyInformation etopCompanyInformation, String etopCompanyBusiness);

    void deleteById(List<String> companyIds);
}
