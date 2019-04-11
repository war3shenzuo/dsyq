package com.etop.management.dao;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopParticipant;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopParticipantDao<br>
 */
public interface EtopParticipantDao {

	Page<EtopParticipant> select(EtopParticipant etopParticipant);

	EtopParticipant getEtopParticipantById(@Param("id") String id);

	Integer selectTestNum(@Param("courseId") String courseId);

	void insert(EtopParticipant etopParticipant);
	
	void insertFeed(EtopParticipant etopParticipant);
	
	void updateFeedback(EtopParticipant etopParticipant);
	
	Page<EtopParticipant> getAvg(EtopParticipant etopParticipant);
}
