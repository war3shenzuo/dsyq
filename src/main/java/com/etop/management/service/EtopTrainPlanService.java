package com.etop.management.service;


import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainPlan;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainPlanService<br>
 */
public interface EtopTrainPlanService{

    EtopPage<EtopTrainPlan> search(EtopTrainPlan etopTrainPlan, Integer offset, Integer limit);

    void add(EtopTrainPlan etopTrainPlan);

    void deleteById(EtopTrainPlan etopTrainPlan);

    EtopTrainPlan getEtopTrainPlanInfoById(String id);

    void updateEtopTrainPlan(EtopTrainPlan etopTrainPlan);

    void getEtopTrainPlanRemind();

}
