package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyTurnover;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/20
 */
public interface EtopCompanyTurnoverDao {

    Page<EtopCompanyTurnover> select(EtopCompanyTurnover etopCompanyTurnover);

    void deleteById(@Param("ids") List<String> ids);

    void insert(EtopCompanyTurnover etopCompanyTurnover);

    EtopCompanyTurnover getTurnoverInfoById(@Param("id") String id);

    void updateById(EtopCompanyTurnover etopCompanyTurnover);

    EtopCompanyTurnover selectTurnover(EtopCompanyTurnover etopCompanyTurnover);
    
    EtopCompanyTurnover SumTurnover(String companyId);
}
