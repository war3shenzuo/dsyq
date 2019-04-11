package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyIntention;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopCompanyIntentionDao<br>
 */
public interface EtopCompanyIntentionDao {

	Page<EtopCompanyIntention> select(EtopCompanyIntention etopCompanyIntention);

	Integer insert(EtopCompanyIntention etopCompanyIntention);

	Integer deleteById(@Param("ids") List<String> ids);

	Integer updateById(EtopCompanyIntention etopCompanyIntention);

	EtopCompanyIntention getCompInfoById(@Param("id") List<String> id);

	EtopCompanyIntention getCompIntention(@Param("companyId")String companyId);

	List<EtopCompanyIntention> getCompanyInfoByParkId(@Param("parkId") String parkId);
	
	List<EtopCompanyIntention> getCompInfoByParkId(@Param("parkIds") List<String> parkIds);

	EtopCompanyIntention selectCompanyIntention(@Param("companyName") String companyName, @Param("id") String id, @Param("parkId") String parkId);
	
	String proveIntentionCompanyName(@Param("companyName") String companyName, @Param("parkId") String parkId);
	
	String proveCompanyName(@Param("companyName") String companyName, @Param("parkId") String parkId);
}
