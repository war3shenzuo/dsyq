package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyTurnover;
import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopCompanyTurnoverDao;
import com.etop.management.service.EtopCompanyTurnoverService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/20
 */
@Service
public class EtopCompanyTurnoverServiceImpl implements EtopCompanyTurnoverService {

    @Autowired
    private EtopCompanyTurnoverDao etopCompanyTurnoverDao;
    
    @Autowired
    private EtopCompanyDao etopCompanyDao;

    @Override
    public EtopPage<EtopCompanyTurnover> search(EtopCompanyTurnover etopCompanyTurnover, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        Page<EtopCompanyTurnover> page = etopCompanyTurnoverDao.select(etopCompanyTurnover);
        return new EtopPage<EtopCompanyTurnover>(page);
    }

    @Override
    public void deleteById(EtopCompanyTurnover etopCompanyTurnover) {
        etopCompanyTurnoverDao.deleteById(etopCompanyTurnover.getIds());
    }

    @Override
    public void add(EtopCompanyTurnover etopCompanyTurnover) {
        etopCompanyTurnover.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyTurnoverDao.insert(etopCompanyTurnover);
        
        EtopCompany com =new EtopCompany();
        Double SumTurnover	= etopCompanyTurnoverDao.SumTurnover(etopCompanyTurnover.getCompanyId()).getSumTurnover();
        int num = etopCompanyTurnoverDao.SumTurnover(etopCompanyTurnover.getCompanyId()).getCountTurnover();
        com.setCompanyId(etopCompanyTurnover.getCompanyId());
        com.setAveMonthTurnover(SumTurnover/num);
        etopCompanyDao.updateOfTurnover(com);
    }

    @Override
    public EtopCompanyTurnover getTurnoverInfo(EtopCompanyTurnover etopCompanyTurnover) {
        return etopCompanyTurnoverDao.getTurnoverInfoById(etopCompanyTurnover.getId());
    }

    @Override
    public void updateById(EtopCompanyTurnover etopCompanyTurnover) {
        etopCompanyTurnover.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyTurnoverDao.updateById(etopCompanyTurnover);
    }

    @Override
    public EtopCompanyTurnover selectTurnover(EtopCompanyTurnover etopCompanyTurnover) {
        return etopCompanyTurnoverDao.selectTurnover(etopCompanyTurnover);
    }

}
