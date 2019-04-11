package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompanyInformation;
import com.etop.management.dao.EtopAdditionalCompanyDao;
import com.etop.management.service.EtopAdditonalCompanyService;
import com.etop.management.service.EtopCompanyBusinessService;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/31
 */
@Service
public class EtopAdditonalCompanyServiceImpl implements EtopAdditonalCompanyService {

    @Autowired
    private EtopAdditionalCompanyDao etopAdditionalCompanyDao;

    @Autowired
    private EtopCompanyBusinessService etopCompanyBusinessService;

    @Override
    public void addInfo(EtopCompanyInformation etopCompanyInformation, String etopCompanyBusiness) {
        Date now = new Date();
        etopCompanyInformation.setId(UUID.randomUUID().toString());
        etopCompanyInformation.setCreatedAt(now);
        etopCompanyInformation.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyInformation.setUpdatedAt(now);
        etopAdditionalCompanyDao.insertInfo(etopCompanyInformation);

        etopCompanyBusinessService.add(etopCompanyBusiness); //添加经营信息

    }

    @Override
    public EtopCompanyInformation getAdditionCompInfo(String id) {
        return etopAdditionalCompanyDao.getAdditionCompInfo(id);
    }

    @Override
    public void updateAdditionalById(EtopCompanyInformation etopCompanyInformation, String etopCompanyBusiness) {
        Date now = new Date();
        etopCompanyInformation.setUpdatedAt(now);
        etopCompanyInformation.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopAdditionalCompanyDao.updateAdditionalById(etopCompanyInformation);
        etopCompanyBusinessService.deleteById(etopCompanyInformation.getCompanyId());
        etopCompanyBusinessService.add(etopCompanyBusiness);
    }

    @Override
    public void deleteById(List<String> companyIds) {
        etopAdditionalCompanyDao.deleteById(companyIds);
    }
}
