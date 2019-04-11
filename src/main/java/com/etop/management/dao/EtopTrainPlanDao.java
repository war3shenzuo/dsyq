package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopTrainPlan;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/20
 */
public interface EtopTrainPlanDao {

    Page<EtopTrainPlan> search(EtopTrainPlan etopTrainPlan);

    void insert(EtopTrainPlan etopTrainPlan);

    void deleteById(@Param("id") String id);

    EtopTrainPlan getEtopTrainPlanInfoById(String id);

    void update(EtopTrainPlan etopTrainPlan);

    List<EtopTrainPlan> getList();

}
