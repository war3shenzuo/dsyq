package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopTrainSchedule;
import com.etop.management.bean.EtopTrainTeacher;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/15
 */
public interface EtopTrainTeacherDao {

    Page<EtopTrainTeacher> select(EtopTrainTeacher etopTrainTeacher);

    void insert(EtopTrainTeacher etopTrainTeacher);

    void deleteByIds(@Param("ids") List<String> ids);

    EtopTrainTeacher getEtopTrainTeacherInfoById(@Param("id") String id);

    void update(EtopTrainTeacher etopTrainTeacher);
    
    Integer selectNum(String id);
}
