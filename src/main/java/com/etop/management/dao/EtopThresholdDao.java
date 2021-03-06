package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopThreshold;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopThresholdDao<br>
 */
public interface EtopThresholdDao {
	/** 添加 */
	int add(EtopThreshold threshold);

	/** 批量添加 */
	int adds(List<EtopThreshold> list);

	/** 全修改 */
	int update(EtopThreshold threshold);

	/** 根据Id删除 */
	int delete(String thresholdId);
	
	/** 批量删除数据 */
	int deletes(String[] thresholdIds);

	/** 根据Id查询 */
	EtopThreshold findById(String thresholdId);
	
	/** 条件查询 */
	Page<EtopThreshold> list(Map<String, Object> condition);
	
	EtopThreshold find(Map<String, Object> condition);

}
