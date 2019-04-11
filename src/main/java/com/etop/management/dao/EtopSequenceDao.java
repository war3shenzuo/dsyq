package com.etop.management.dao;

import com.etop.management.entity.EtopSequence;

public interface EtopSequenceDao {

	EtopSequence findByParkId(String parkId);
	
	int add(EtopSequence etopSequence);
	
	int increaseCount(String parkId);
	
	int clearCount(String parkId);
	
	int nextSeq(String parkCode);
}
