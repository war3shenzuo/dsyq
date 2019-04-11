package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.etop.management.bean.EtopVote;

/**
 * Created by Alan.
 *
 * @author 吕錫威
 * @DATE 2017/03/14
 */
public interface EtopVoteDao {

	Integer insert(@Param("etopVote") List<EtopVote> etopVote);
	
	Integer updateBynoticeId(EtopVote etopVote);
	
    List<EtopVote> selectOption(@Param("noticeId") String noticeId,@Param("addressee") String addressee);
    
    EtopVote selectById(@Param("id") String id);
	
}
