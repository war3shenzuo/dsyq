package com.etop.management.dao;
import java.util.List;
import com.etop.management.entity.EtopResume;
import com.etop.management.bean.Criteria;

/**
 * 
 * <br>
 * <b>功能：</b>EtopResumeDao<br>
 */
public interface EtopResumeDao {
	/**添加*/
	public int add(EtopResume etopResume);
	/**批量添加*/
	public int adds(List<EtopResume> list);
	/**全修改*/
	public int update(EtopResume etopResume);
	/**条件修改*/
	public int updateBySelective(EtopResume etopResume); 	
	/**根据Id删除*/
	public int delete(EtopResume etopResume);
	/**根据Id查询*/
	public EtopResume queryById(String id);
	/**根据类条件查询*/
	public EtopResume queryObject(EtopResume etopResume);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopResume> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopResume> myDefinedSelect(Criteria criteria);
	
	/**根据serviceId查询*/
	public EtopResume queryByServiceId(String serviceId);
	
}
