package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopTrainApplication;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainApplicationDao<br>
 */
public interface EtopTrainApplicationDao {

	EtopTrainApplication select(@Param("trainingId") String trainingId);

	Page<EtopTrainApplication> selectList(EtopTrainApplication etopTrainApplication);

	void insert(EtopTrainApplication etopTrainApplication);

	EtopTrainApplication getTrainApplicationInfoById(@Param("id") String id);

	void updateTrainApplication(EtopTrainApplication etopTrainApplication);

	void deleteById(@Param("ids") List<String> ids);
}
