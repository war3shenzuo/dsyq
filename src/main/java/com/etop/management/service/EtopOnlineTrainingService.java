package com.etop.management.service;


import java.util.List;

import com.etop.management.bean.EtopOnlineTraining;
import com.etop.management.bean.EtopPage;

/**
 * 
 * <br>
 * <b>功能：</b>EtopOnlineTrainingService<br>
 */
public interface EtopOnlineTrainingService{

    EtopPage<EtopOnlineTraining> search(EtopOnlineTraining etopOnlineTraining, Integer offset, Integer limit);

    void add(EtopOnlineTraining etopOnlineTraining);

    EtopOnlineTraining getOnlineTrainingInfoById(String id);

    void updateOnlineTraining(EtopOnlineTraining etopOnlineTraining);

    void deleteById(EtopOnlineTraining etopOnlineTraining);

    List<EtopOnlineTraining> selectOnlineTraining(EtopOnlineTraining etopOnlineTraining);

    List<EtopOnlineTraining> selectOnlineInfo(String choosePark, String courseType);
}
