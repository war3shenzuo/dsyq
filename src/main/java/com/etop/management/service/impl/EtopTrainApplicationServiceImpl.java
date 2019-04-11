package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainApplication;
import com.etop.management.dao.EtopTrainApplicationDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.EtopTrainApplicationService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainApplicationService<br>
 */
@Service
public class  EtopTrainApplicationServiceImpl  implements EtopTrainApplicationService {

    @Autowired
    private EtopTrainApplicationDao etopTrainApplicationDao;
    
    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<EtopTrainApplication> search(EtopTrainApplication etopTrainApplication, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t1.id DESC");
        Page<EtopTrainApplication> list = etopTrainApplicationDao.selectList(etopTrainApplication);
        return new EtopPage<EtopTrainApplication>(list);
    }

    @Override
    public void add(EtopTrainApplication etopTrainApplication) {
    	etopTrainApplication.setUserId(UserInfoUtil.getUserInfo().getId());
    	etopTrainApplication.setParkTitle(parkDao.getParkTitle(UserInfoUtil.getUserInfo().getId()));
    	etopTrainApplication.setParkGroupCode(parkDao.getGroupCode(UserInfoUtil.getUserInfo().getId()));
        etopTrainApplicationDao.insert(etopTrainApplication);
    }

    @Override
    public EtopTrainApplication getTrainApplicationInfoById(String id) {
        return etopTrainApplicationDao.getTrainApplicationInfoById(id);
    }

    @Override
    public void updateTrainApplication(EtopTrainApplication etopTrainApplication) {
        etopTrainApplicationDao.updateTrainApplication(etopTrainApplication);
    }

    @Override
    public void deleteById(EtopTrainApplication etopTrainApplication) {
        etopTrainApplicationDao.deleteById(etopTrainApplication.getIds());
    }

}
