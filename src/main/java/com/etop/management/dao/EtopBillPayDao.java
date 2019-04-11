package com.etop.management.dao;
import java.util.List;
import com.etop.management.entity.EtopBillPay;
import com.github.pagehelper.Page;
import com.etop.management.bean.BillPayWithUser;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillPayDao<br>
 */
public interface EtopBillPayDao {
	/**添加*/
	public int add(EtopBillPay etopBillPay);
	/**批量添加*/
	public int adds(List<EtopBillPay> list);
	/**全修改*/
	public int update(EtopBillPay etopBillPay);
	/**条件修改*/
	public int updateBySelective(EtopBillPay etopBillPay); 	
	/**根据Id删除*/
	public int delete(String payId);
	/** 根据id批量删除 */
	public int deletes(String[] payIds);
	/** 根据id查询 */
	public EtopBillPay findById(String payId);
	/**根据Id查询*/
	public Page<EtopBillPay> listByBillId(String billId);
	/**根据Id查询, 与用户表联查*/
	public Page<BillPayWithUser> listWithUserByBillId(String billId);
}
