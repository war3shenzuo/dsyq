package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopOnlineTraining;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/26
 */
public interface EtopOnlineTrainingDao {

    Page<EtopOnlineTraining> select(EtopOnlineTraining etopOnlineTraining);

    void insert(EtopOnlineTraining etopOnlineTraining);

    EtopOnlineTraining getOnlineTrainingInfoById(@Param("id") String id);

    void updateOnlineTraining(EtopOnlineTraining etopOnlineTraining);

    void deleteByIds(@Param("ids") List<String> ids);

    List<EtopOnlineTraining> selectOnlineTraining(EtopOnlineTraining etopOnlineTraining);

    Integer getCount(@Param("titleId") String titleId);

    List<EtopOnlineTraining> selectOnlineInfo(@Param("target") String target, @Param("courseType") String courseType);
    
    int CountQuestionBank(String id);
    
    int CountTestNum(String id);
    
    EtopOnlineTraining getAvgScore(String id);
}
