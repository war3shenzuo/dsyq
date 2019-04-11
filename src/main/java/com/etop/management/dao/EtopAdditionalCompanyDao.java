package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyInformation;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public interface EtopAdditionalCompanyDao {

    Integer insertInfo(EtopCompanyInformation etopCompanyInformation);

    EtopCompanyInformation getAdditionCompInfo(String id);

    Integer updateAdditionalById(EtopCompanyInformation etopCompanyInformation);

    Integer deleteById(@Param("additionalCompanyIds") List<String> additionalCompanyIds);
}