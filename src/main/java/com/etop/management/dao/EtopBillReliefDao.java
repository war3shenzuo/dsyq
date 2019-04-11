package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.bean.BillReliefBean;
import com.etop.management.bean.BillReliefWithUser;
import com.etop.management.entity.EtopBillRelief;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillReliefDao<br>
 */
public interface EtopBillReliefDao {
	/**添加*/
	public int add(EtopBillRelief etopBillRelief);
	/**批量添加*/
	public int adds(List<EtopBillRelief> list);
	/**全修改*/
	public int update(EtopBillRelief etopBillRelief);
	/**条件修改*/
	public int updateSelective(EtopBillRelief etopBillRelief); 	
	/**根据Id删除*/
	public int delete(String reliefId);
	public int deletes(String[] reliefIds);
	/**根据Id查询*/
	public EtopBillRelief findById(String id);
	/**根据具体条件批量查询*/
	public Page<EtopBillRelief> list(Map<String, Object> map);
	
	public Page<BillReliefWithUser> listWithUser(Map<String, Object> map);
	
	public List<EtopBillRelief> listByIds(String[] reliefIds);
	
	public int setAuditByIds(Map<String, Object> map);
	
	public Page<EtopBillRelief> listByParkId(Map<String, Object> map);
	
	public Page<BillReliefWithUser> listWithUserByParkId(Map<String, Object> map);
	
	public Page<BillReliefBean> listWithBill(Map<String, Object> map);
}
