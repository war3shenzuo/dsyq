package com.etop.management.service;

import com.etop.management.bean.EtopCompanyMaintain;
import com.etop.management.bean.EtopPage;

/**
 * Created by Alex.
 *
 * @author 何利庭
 * @DATE 2016/8/23
 */
public interface EtopCompanyMaintainService {

    EtopPage<EtopCompanyMaintain> search(EtopCompanyMaintain etopCompanyMaintain, Integer offset, Integer limit);

    void deleteById(EtopCompanyMaintain etopCompanyMaintain);

    void add(EtopCompanyMaintain etopCompanyMaintain);

    void updateById(EtopCompanyMaintain etopCompanyMaintain);

    //正式公司拜访记录
    EtopCompanyMaintain getMaintainInfoById(EtopCompanyMaintain etopCompanyMaintain);

    //意向公司拜访记录
    EtopCompanyMaintain getMaintainInfoByIntentionId(EtopCompanyMaintain etopCompanyMaintain);

    void getMaintainList();
}
