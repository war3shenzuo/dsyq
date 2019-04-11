package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopBillIncome;
import com.github.pagehelper.Page;

/**
 * <b>功能：</b>EtopBillIncomeDao<br>
 */
public interface EtopBillIncomeDao {
	/**添加*/
	public int add(EtopBillIncome etopBillIncome);
	/**批量添加*/
	public int adds(List<EtopBillIncome> list);
	/**全修改*/
	public int update(EtopBillIncome etopBillIncome);
	/**条件修改*/
	public int updateBySelective(EtopBillIncome etopBillIncome); 	
	/**根据Id删除*/
	public int delete(String incomeId);
	/** 根据id批量删除 */
	public int deletes(String[] incomeIds);
	/** 根据id查询 */
	public EtopBillIncome findById(String incomeId);
	/**根据Id查询*/
	public Page<EtopBillIncome> list(Map<String, Object> map);
}
