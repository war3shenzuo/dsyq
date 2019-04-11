package com.etop.management.service;


import java.util.List;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopQuestionBank;

/**
 * 
 * <br>
 * <b>功能：</b>EtopQuestionBankService<br>
 */
public interface EtopQuestionBankService{

    EtopPage<EtopQuestionBank> search(EtopQuestionBank etopQuestionBank, Integer offset, Integer limit);

    EtopQuestionBank getEtopQuestionBankById(EtopQuestionBank etopQuestionBank);

    void updateById(EtopQuestionBank etopQuestionBank);

    void add(EtopQuestionBank etopQuestionBank);

    void deleteById(EtopQuestionBank etopQuestionBank);

    List<EtopQuestionBank> getEtopQuestionBank(EtopQuestionBank etopQuestionBank);

    Integer calculateScore(String titleId, String ids);
}
