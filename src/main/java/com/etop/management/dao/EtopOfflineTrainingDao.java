package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopOfflineTraining;
import com.etop.management.bean.EtopUser;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/7
 */
public interface EtopOfflineTrainingDao {

    Page<EtopOfflineTraining> search(EtopOfflineTraining etopOfflineTraining);
    
    Page<EtopOfflineTraining> searchPrograms(EtopOfflineTraining etopOfflineTraining);

    List<EtopOfflineTraining> selectEtopOfflinePrograms(EtopOfflineTraining etopOfflineTraining);

    void insert(EtopOfflineTraining etopOfflineTraining);

    void update(EtopOfflineTraining etopOfflineTraining);

    void updateStatus(@Param("courseStatus") Integer courseStatus, @Param("id") String id);

    EtopOfflineTraining selectTime(String id);

    void deleteByIds(@Param("ids") List<String> ids);

    EtopOfflineTraining getOfflineProgramsInfoById(String id);

    void updateOfflinePrograms(EtopOfflineTraining etopOfflineTraining);

    List<EtopOfflineTraining> selectProgramsInfo(@Param("target") String target, @Param("courseType") String courseType, @Param("offlineType")Integer offlineType);

    List<EtopOfflineTraining> selectSeries();
    
//    List<EtopOfflineTraining> selectCity();
    List<Map<String,Object>> selectCity(Map<String, String> m);
    
//    int countPark(String id);
    Integer countPark(String createdBy);
    
    int delete(String id);
    
    String proveContent(String id);
    
    List<EtopOfflineTraining> getAll();
    
    EtopOfflineTraining getInfo(String id);
}
