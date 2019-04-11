package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopServiceRecruitment;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.Etopservice;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceRecruitmentDao<br>
 */
public interface EtopServiceRecruitmentDao {
	/**添加*/
	public int add(EtopServiceRecruitment etopServiceRecruitment);
	/**批量添加*/
	public int adds(List<EtopServiceRecruitment> list);
	/**全修改*/
	public int update(EtopServiceRecruitment etopServiceRecruitment);
	/**条件修改*/
	public int updateBySelective(EtopServiceRecruitment etopServiceRecruitment); 	
	/**根据Id删除*/
	public int delete(EtopServiceRecruitment etopServiceRecruitment);
	/**根据Id查询*/
	public EtopServiceRecruitment queryById(String id);
	/**根据类条件查询*/
	public EtopServiceRecruitment queryObject(EtopServiceRecruitment etopServiceRecruitment);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopServiceRecruitment> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopServiceRecruitment> myDefinedSelect(Criteria criteria);
	
	public EtopServiceRecruitment queryByServiceId(String serviceId);
	
    Page<ServiceQuotation> makeRecruitment(Map<String, Object> condition);
    
    Page<ServiceQuotation> makeRecruitmentGroup(Map<String, Object> condition);
    
	public Etopservice querysByServiceId(String serviceId);
}
