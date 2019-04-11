package com.etop.management.service;


import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainTeacher;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainTeacherService<br>
 */
public interface EtopTrainTeacherService{

    EtopPage<EtopTrainTeacher> search(EtopTrainTeacher etopTrainTeacher, Integer offset, Integer limit);

    void add(EtopTrainTeacher etopTrainTeacher);

    void updateEtopTrainTeacher(EtopTrainTeacher etopTrainTeacher);

    void deleteById(EtopTrainTeacher etopTrainTeacher);

    EtopTrainTeacher getEtopTrainTeacherInfoById(String id);
}
