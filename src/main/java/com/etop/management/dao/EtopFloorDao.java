package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopFloor;
import com.etop.management.bean.Criteria;

/**
 * 
 * <br>
 * <b>功能：</b>EtopFloorDao<br>
 */
public interface EtopFloorDao {
	/**添加*/
	public int add(EtopFloor etopFloor);
	/**批量添加*/
	public int adds(List<EtopFloor> list);
	/**全修改*/
	public int update(EtopFloor etopFloor);
	/**条件修改*/
	public int updateBySelective(EtopFloor etopFloor); 	
	/**根据Id删除*/
	public int delete(EtopFloor etopFloor);
	/**根据Id查询*/
	public EtopFloor queryById(String id);
	/**根据类条件查询*/
	public EtopFloor queryObject(EtopFloor etopFloor);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopFloor> queryByList(Criteria criteria);
	
	/**
	 * 取子类
	 * @param criteria
	 * @return
	 */
	public List<EtopFloor> queryListByParent(Criteria criteria);
	
	
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopFloor> myDefinedSelect(Criteria criteria);
	/** 添加楼能源出账信息 */
	public void addFloorEnergy(Map<String, String> m);
	/** 楼能源信息*/
	public List<Map<String, Object>> getFloorEnergyInfo(String floorId);
	/** 删除楼能源信息*/
	public void delFloorEnergy(String id);
	/** 修改楼能源信息*/
	public void updateFloorEnergy(Map<String, String> m);
	
	public void deleteAll(List<String> ids);
	
//	public EtopFloor queryByParentId(String parentId);
	public List<EtopFloor> queryByParentId(String parentId);
	
//	public EtopFloor updateByParentId(String id,String status);
	public int updateByParentId(EtopFloor floor);
	
}
