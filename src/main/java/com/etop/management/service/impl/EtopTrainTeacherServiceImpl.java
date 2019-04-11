package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainTeacher;
import com.etop.management.dao.EtopTrainTeacherDao;
import com.etop.management.service.EtopTrainTeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainTeacherService<br>
 */
@Service
public class  EtopTrainTeacherServiceImpl  implements EtopTrainTeacherService {

    @Autowired
    private EtopTrainTeacherDao etopTrainTeacherDao;

    @Override
    public EtopPage<EtopTrainTeacher> search(EtopTrainTeacher etopTrainTeacher, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t1.id DESC");
        Page<EtopTrainTeacher> list = etopTrainTeacherDao.select(etopTrainTeacher);
        return new EtopPage<EtopTrainTeacher>(list);
    }

    @Override
    public void add(EtopTrainTeacher etopTrainTeacher) {
        etopTrainTeacherDao.insert(etopTrainTeacher);
    }

    @Override
    public void updateEtopTrainTeacher(EtopTrainTeacher etopTrainTeacher) {
        etopTrainTeacherDao.update(etopTrainTeacher);
    }

    @Override
    public void deleteById(EtopTrainTeacher etopTrainTeacher) {
        etopTrainTeacherDao.deleteByIds(etopTrainTeacher.getIds());
    }

    @Override
    public EtopTrainTeacher getEtopTrainTeacherInfoById(String id) {
        return etopTrainTeacherDao.getEtopTrainTeacherInfoById(id);
    }
}
