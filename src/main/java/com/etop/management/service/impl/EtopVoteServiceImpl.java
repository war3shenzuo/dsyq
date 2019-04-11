package com.etop.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.etop.management.bean.EtopVote;
import com.etop.management.dao.EtopVoteDao;
import com.etop.management.service.EtopVoteService;

/**
 * Created by Alan.
 *
 * @author 吕錫威
 * @DATE 2017/03/14
 */
@Service
public class EtopVoteServiceImpl implements EtopVoteService {

    @Autowired
    private EtopVoteDao etopVoteDao;

    
    public Integer add(String etopVote) {

        List<EtopVote> etopVotes = JSONObject.parseObject(etopVote, List.class);

        return etopVoteDao.insert(etopVotes);
    }


	@Override
	public List<EtopVote> selectOption(String noticeId,String addressee) {
		List<EtopVote> etopVotes = etopVoteDao.selectOption(noticeId,addressee);
		return etopVotes;
	}


	@Override
	public void updateBynoticeId(EtopVote etopVote) {
		int num =etopVoteDao.selectById(etopVote.getId()).getNumber();
		etopVote.setNumber(num+1);
		 etopVoteDao.updateBynoticeId(etopVote);
		
	}



}
