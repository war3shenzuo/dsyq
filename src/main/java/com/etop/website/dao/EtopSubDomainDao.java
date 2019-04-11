package com.etop.website.dao;

import java.util.Map;

import com.etop.website.bean.EtopSubDomain;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceDao<br>
 */
public interface EtopSubDomainDao {
	/**添加*/
	public int add(EtopSubDomain etopSubDomain);
	/**批量添加*/
	public Page<EtopSubDomain> list(Map<String, Object> map);
	/**根据Id删除*/
	public int delete(String hostName);
	public int deletes(String[] hostNames);
	/**根据Id查询*/
	public EtopSubDomain findById(String hostName);
}
