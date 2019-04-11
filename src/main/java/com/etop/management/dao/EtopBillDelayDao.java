package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.bean.BillDelayBean;
import com.etop.management.bean.BillDelayWithUser;
import com.etop.management.entity.EtopBillDelay;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillDelayDao<br>
 */
public interface EtopBillDelayDao {
	/**添加*/
	public int add(EtopBillDelay etopBillDelay);
	/**批量添加*/
	public int adds(List<EtopBillDelay> list);
	/**全修改*/
	public int update(EtopBillDelay etopBillDelay);
	/**条件修改*/
	public int updateSelective(EtopBillDelay etopBillDelay); 	
	/**根据Id删除*/
	public int delete(String delayId);
	public int deletes(String[] delayIds);
	/**根据Id查询*/
	public EtopBillDelay findById(String id);
	/** 根据billId查询 */
	public Page<EtopBillDelay> listByBillId(String billId);
	
	public Page<EtopBillDelay> list(Map<String, Object> map);

	public Page<BillDelayWithUser> listWithUser(Map<String, Object> map);
	
	public Page<EtopBillDelay> listByParkId(Map<String, Object> map);

	public Page<BillDelayWithUser> listWithUserByParkId(Map<String, Object> map);

	public Page<BillDelayBean> listWithBill(Map<String, Object> map);
	
	public List<EtopBillDelay> listByIds(String[] ids);
	
	public int setAuditByIds(Map<String, Object> map);
}
