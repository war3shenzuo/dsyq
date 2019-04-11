package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.etop.management.bean.EtopCompanyBusiness;
import com.etop.management.dao.EtopCompanyBusinessDao;
import com.etop.management.service.EtopCompanyBusinessService;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
@Service
public class EtopCompanyBusinessServiceImpl implements EtopCompanyBusinessService {

    @Autowired
    private EtopCompanyBusinessDao etopCompanyBusinessDao;

    @Override
    public Integer add(String etopCompanyBusiness) {

        List<EtopCompanyBusiness> etopBusinesses = JSONObject.parseObject(etopCompanyBusiness, List.class);

        return etopCompanyBusinessDao.insert(etopBusinesses);
    }

    @Override
    public List<EtopCompanyBusiness> getCompBusiness(String companyId) {

        return etopCompanyBusinessDao.getCompBusiness(companyId);
    }

    @Override
    public Integer deleteById(String companyId) {
        List<String> id = new ArrayList<>();
        id.add(companyId);
        return etopCompanyBusinessDao.deleteByIds(id);
    }

    @Override
    public Integer addInfo(String companyId) {
        EtopCompanyBusiness etopCompanyBusiness = new EtopCompanyBusiness();
        etopCompanyBusiness.setCompanyId(companyId);
        etopCompanyBusiness.setBusinessPractice("淘宝");
        etopCompanyBusiness.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        return etopCompanyBusinessDao.addInfo(etopCompanyBusiness);
    }
}
