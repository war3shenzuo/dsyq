package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyMaintain;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopCompanyIntentionDao<br>
 */
public interface EtopCompanyMaintainDao {

	Page<EtopCompanyMaintain> selectIntention(EtopCompanyMaintain etopCompanyMaintain);

	Page<EtopCompanyMaintain> select(EtopCompanyMaintain etopCompanyMaintain);

	Integer insert(EtopCompanyMaintain etopCompanyMaintain);

	Integer deleteById(@Param("ids") List<String> ids);

	Integer updateById(EtopCompanyMaintain etopCompanyMaintain);

	EtopCompanyMaintain getMaintainInfoById(@Param("id") String id, @Param("companyId") String companyId);

	EtopCompanyMaintain getMaintainInfoByIntentionId(@Param("id") String id, @Param("companyId") String companyId);

	List<EtopCompanyMaintain> getMaintainList(@Param("remindAt") String remindAt);
	
	int count(String companyId);
}
