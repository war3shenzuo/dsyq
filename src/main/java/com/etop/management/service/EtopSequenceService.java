package com.etop.management.service;

/**
 * @author lmc
 * 下午2:30:38
 */
public interface EtopSequenceService {

    /**
     * 获取计数并增加计数
     * @param parkId
     * @return
     */
    int getAndIncrease(String parkId);

    /**
     * 获取格式化id
     * @param parkId	园区id
     * @param type		表单类型
     * @return
     */
    String getFormatId(String parkId, String type);
    
    String getFormatId2(String type);
}
