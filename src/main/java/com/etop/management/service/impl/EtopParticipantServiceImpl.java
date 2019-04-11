package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopParticipant;
import com.etop.management.dao.EtopParticipantDao;
import com.etop.management.service.EtopParticipantService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopParticipantService<br>
 */
@Service
public class  EtopParticipantServiceImpl  implements EtopParticipantService {

    @Autowired
    private EtopParticipantDao etopParticipantDao;

    @Override
    public EtopPage<EtopParticipant> search(EtopParticipant etopParticipant, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "score DESC");
        Page<EtopParticipant> page = etopParticipantDao.select(etopParticipant);
        return new EtopPage<EtopParticipant>(page);
    }

    @Override
    public EtopParticipant getEtopParticipantById(EtopParticipant etopParticipant) {

        return etopParticipantDao.getEtopParticipantById(etopParticipant.getId());
    }

	@Override
	public EtopPage<EtopParticipant> getAvg(EtopParticipant etopParticipant,
			Integer offset, Integer limit) {
		Page<EtopParticipant> page = etopParticipantDao.getAvg(etopParticipant);
        return new EtopPage<EtopParticipant>(page);
	}

	@Override
	public void updateFeedback(EtopParticipant etopParticipant) {
		 etopParticipantDao.updateFeedback(etopParticipant);
		
	}


}
