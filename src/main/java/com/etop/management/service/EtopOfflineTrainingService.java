package com.etop.management.service;


import java.util.List;
import java.util.Map;

import com.etop.management.bean.EtopOfflineTraining;
import com.etop.management.bean.EtopPage;

/**
 * 
 * <br>
 * <b>功能：</b>EtopOfflineTrainingService<br>
 */
public interface EtopOfflineTrainingService{

    EtopPage<EtopOfflineTraining> demand(EtopOfflineTraining etopOfflineTraining, Integer offset, Integer limit);
    
    EtopPage<EtopOfflineTraining> searchPrograms(EtopOfflineTraining etopOfflineTraining, Integer offset, Integer limit);

    List<EtopOfflineTraining> selectEtopOfflinePrograms(EtopOfflineTraining etopOfflineTraining);

    void add(EtopOfflineTraining etopOfflineTraining);

    void unpublish(String id, Integer type);

    void deleteById(EtopOfflineTraining etopOfflineTraining);

    EtopOfflineTraining getOfflineProgramsInfoById(String id);

    void updateOfflinePrograms(EtopOfflineTraining etopOfflineTraining);

    List<EtopOfflineTraining> selectProgramsInfo(String target, String courseType, Integer offlineType);

    List<EtopOfflineTraining> selectSeries();
    
//    List<EtopOfflineTraining> selectCity();
    public Map<String, Object> selectCity(Map<String, String> m);
    
    int delete(String id);
    
    String proveContent(String id);
}

