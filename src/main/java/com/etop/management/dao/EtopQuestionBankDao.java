package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopQuestionBank;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/10/28
 */
public interface EtopQuestionBankDao {

    Page<EtopQuestionBank> select(EtopQuestionBank etopQuestionBank);

    EtopQuestionBank getEtopQuestionBankById(@Param("id") String id);

    void updateById(EtopQuestionBank etopQuestionBank);

    void insert(EtopQuestionBank etopQuestionBank);

    void deleteById(@Param("ids") List<String> ids);

    List<EtopQuestionBank> selectEtopQuestionBank(@Param("titleId") String titleId);

    List<EtopQuestionBank> getEtopQuestionBank(@Param("array") List<String> array);
}
