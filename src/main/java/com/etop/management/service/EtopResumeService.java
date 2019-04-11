package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopResume;


/**
 * 
 * <br>
 * <b>功能：</b>EtopResumeService<br>
 */
public interface EtopResumeService{

	EtopPage<EtopResume> getEtopResumeList(EtopResume etopResume);

	EtopResume getEtopResumeInfo(String etopResumeId);

	void addEtopResume(EtopResume param);

	void updateEtopResume(EtopResume etopResume);

	void deleteEtopResumeInfo(EtopResume etopResume);

}
