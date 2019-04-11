package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopOnlineTraining;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopParticipant;
import com.etop.management.bean.EtopQuestionBank;
import com.etop.management.constant.Constants;
import com.etop.management.dao.EtopOnlineTrainingDao;
import com.etop.management.dao.EtopParticipantDao;
import com.etop.management.dao.EtopQuestionBankDao;
import com.etop.management.service.EtopQuestionBankService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopQuestionBankService<br>
 */
@Service
public class  EtopQuestionBankServiceImpl  implements EtopQuestionBankService {

    @Autowired
    private EtopQuestionBankDao etopQuestionBankDao;

    @Autowired
    private EtopOnlineTrainingDao etopOnlineTrainingDao;

    @Autowired
    private EtopParticipantDao etopParticipantDao;

    @Override
    public EtopPage<EtopQuestionBank> search(EtopQuestionBank etopQuestionBank, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        Page<EtopQuestionBank> page = etopQuestionBankDao.select(etopQuestionBank);
        return new EtopPage<EtopQuestionBank>(page);
    }

    @Override
    public EtopQuestionBank getEtopQuestionBankById(EtopQuestionBank etopQuestionBank) {
        EtopQuestionBank newQuestionBank = etopQuestionBankDao.getEtopQuestionBankById(etopQuestionBank.getId());
        EtopOnlineTraining etopOnlineTraining = etopOnlineTrainingDao.getOnlineTrainingInfoById(etopQuestionBank.getTitleId());
        if(etopOnlineTraining != null){
            newQuestionBank.setPlatform(etopOnlineTraining.getPlatform());
        }
        return newQuestionBank;
    }

    @Override
    public void updateById(EtopQuestionBank etopQuestionBank) {
        etopQuestionBank.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopQuestionBank.setUpdatedAt(new Date());
        etopQuestionBankDao.updateById(etopQuestionBank);
    }

    @Override
    public void add(EtopQuestionBank etopQuestionBank) {
        EtopOnlineTraining etopOnlineTraining = etopOnlineTrainingDao.getOnlineTrainingInfoById(etopQuestionBank.getTitleId());
        if(etopOnlineTraining != null){
            etopQuestionBank.setPlatform(etopOnlineTraining.getPlatform());
        }
        etopQuestionBank.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopQuestionBankDao.insert(etopQuestionBank);
    }

    @Override
    public void deleteById(EtopQuestionBank etopQuestionBank) {
        etopQuestionBankDao.deleteById(etopQuestionBank.getIds());
    }

    @Override
    public List<EtopQuestionBank> getEtopQuestionBank(EtopQuestionBank etopQuestionBank) {
        List<EtopQuestionBank> list = etopQuestionBankDao.selectEtopQuestionBank(etopQuestionBank.getTitleId());
        if(list.size() <= Constants.ETOP_QUESTION_BANK_NUM){
            return list;
        }else {
            Set set = new HashSet();
            int k;
            while (set.size() < Constants.ETOP_QUESTION_BANK_NUM){
                k = new Random().nextInt(list.size());
                set.add(list.get(k));
            }
            List<EtopQuestionBank> arrayList = new ArrayList<EtopQuestionBank>(set);
            return arrayList;
        }

    }

    @Override
    public Integer calculateScore(String titleId, String ids) {
        List<String> array = new ArrayList<>();
        Integer baseScore = 0;
        Integer score = 0;
        Integer count = etopOnlineTrainingDao.getCount(titleId);
        if(count > 0){
            baseScore = Constants.EXAMINATION_SCORE / count;
            String[] strings = ids.split(",");
            for(String str : strings){
                EtopQuestionBank etopQuestionBank = etopQuestionBankDao.getEtopQuestionBankById(str.split(":")[0]);
                if(etopQuestionBank.getId().equals(str.split(":")[0]) && etopQuestionBank.getCorrectAnswer().equalsIgnoreCase(str.split(":")[1])){
                    score = score + baseScore;
                }
            }
            EtopParticipant etopParticipant = new EtopParticipant();
            etopParticipant.setId(UUID.randomUUID().toString());
            etopParticipant.setUserId(UserInfoUtil.getUserInfo().getId());
            etopParticipant.setCourseId(titleId);
            etopParticipant.setScore(score);
            etopParticipant.setAnswerAt(new Date());
            etopParticipantDao.insert(etopParticipant);
            
            etopParticipant.setParticipantId(etopParticipant.getId());
            etopParticipant.setUserId(UserInfoUtil.getUserInfo().getId());
            etopParticipant.setCourseId(titleId);
            etopParticipantDao.insertFeed(etopParticipant);

            /*List<EtopQuestionBank> list = etopQuestionBankDao.getEtopQuestionBank(array);
            if(list != null && list.size() > 0){
                for(EtopQuestionBank questionBank : list){
                    for(String str : strings){
                        if(questionBank.getId().equals(str.split(":")[0]) && questionBank.getCorrectAnswer().equalsIgnoreCase(str.split(":")[1])){
                            score = score + baseScore;
                            break;
                        }
                    }
                }
            }*/
        }
        return score;
    }

}
