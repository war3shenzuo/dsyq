package com.etop.management.service;


import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopParticipant;

/**
 * 
 * <br>
 * <b>功能：</b>EtopParticipantService<br>
 */
public interface EtopParticipantService{

    EtopPage<EtopParticipant> search(EtopParticipant etopParticipant, Integer offset, Integer limit);

    EtopParticipant getEtopParticipantById(EtopParticipant etopParticipant);
    
    EtopPage<EtopParticipant> getAvg(EtopParticipant etopParticipant, Integer offset, Integer limit);
	
	void updateFeedback(EtopParticipant etopParticipant);
}
