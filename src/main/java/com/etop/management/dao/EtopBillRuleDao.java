package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.entity.EtopBillRule;

public interface EtopBillRuleDao {

	List<EtopBillRule> getBillRuleList(@Param("refParkId")String refParkId);
	
	int createBillRule(EtopBillRule entity);
	
	int updateBillRule(EtopBillRule entity);
	
	int deleteBillRule(@Param("id")String id);
	
	EtopBillRule getBillRuleById(@Param("id")String id);
	
}
