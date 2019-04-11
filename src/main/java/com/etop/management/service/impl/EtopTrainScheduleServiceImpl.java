package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainSchedule;
import com.etop.management.dao.EtopTrainScheduleDao;
import com.etop.management.service.EtopTrainScheduleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainScheduleService<br>
 */
@Service("etopTrainScheduleService")
public class  EtopTrainScheduleServiceImpl  implements EtopTrainScheduleService {

    @Autowired
    private EtopTrainScheduleDao etopTrainScheduleDao;

    @Override
    public EtopPage<EtopTrainSchedule> search(EtopTrainSchedule etopTrainSchedule, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t1.id DESC");
        Page<EtopTrainSchedule> list = etopTrainScheduleDao.select(etopTrainSchedule);
        return new EtopPage<EtopTrainSchedule>(list);
    }

    @Override
    public void add(EtopTrainSchedule etopTrainSchedule) {
        etopTrainScheduleDao.insert(etopTrainSchedule);
    }

    @Override
    public void deleteById(EtopTrainSchedule etopTrainSchedule) {
        etopTrainScheduleDao.deleteByIds(etopTrainSchedule.getIds());
    }

    @Override
    public EtopTrainSchedule getEtopTrainScheduleInfoById(String id) {
        return etopTrainScheduleDao.getEtopTrainScheduleInfoById(id);
    }

    @Override
    public void updateTrainSchedule(EtopTrainSchedule etopTrainSchedule) {
        etopTrainScheduleDao.update(etopTrainSchedule);
    }
}
