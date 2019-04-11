package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopVote;

/**
 * Created by Alan.
 *
 * @author 吕錫威
 * @DATE 2017/03/14
 */
public interface EtopVoteService {

    Integer add(String etopVote);
    
    void updateBynoticeId(EtopVote etopVote);
    
    List<EtopVote> selectOption(String noticeId,String addressee);

}
