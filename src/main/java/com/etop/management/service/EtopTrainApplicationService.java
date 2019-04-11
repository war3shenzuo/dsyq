package com.etop.management.service;


import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainApplication;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainApplicationService<br>
 */
public interface EtopTrainApplicationService{

    EtopPage<EtopTrainApplication> search(EtopTrainApplication etopTrainApplication, Integer offset, Integer limit);

    void add(EtopTrainApplication etopTrainApplication);

    EtopTrainApplication getTrainApplicationInfoById(String id);

    void updateTrainApplication(EtopTrainApplication etopTrainApplication);

    void deleteById(EtopTrainApplication etopTrainApplication);

}
