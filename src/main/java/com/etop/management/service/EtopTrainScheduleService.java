package com.etop.management.service;


import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainSchedule;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainScheduleService<br>
 */
public interface EtopTrainScheduleService{

    EtopPage<EtopTrainSchedule> search(EtopTrainSchedule etopTrainSchedule, Integer offset, Integer limit);

    void add(EtopTrainSchedule etopTrainSchedule);

    void deleteById(EtopTrainSchedule etopTrainSchedule);

    EtopTrainSchedule getEtopTrainScheduleInfoById(String id);

    void updateTrainSchedule(EtopTrainSchedule etopTrainSchedule);
}
