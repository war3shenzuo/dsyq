package com.etop.management.servicenew.impl;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.NewContractItem;
import com.etop.management.dao.NewContractDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.model.NewContractModel;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.servicenew.NewContractService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Intellij IDEA
 *
 * @author: shixianjie
 * Date: 2019/5/16
 * Time: 2:50 PM
 */
@Service
public class NewContractServiceImpl implements NewContractService {

    @Autowired
    private EtopSequenceService etopSequenceService;
    @Autowired
    private NewContractDao newcontractDao;
    @Autowired
    private EtopCompanyIntentionService etopCompanyIntentionService;

    @Override
    public Map addContract(NewContractModel model) {
        model.setId(UUID.randomUUID().toString());
        model.setRefParkId(UserInfoUtil.getUserParkInfo().getParkCode());
        model.setContractNo(this.etopSequenceService.getFormatId(model.getRefParkId(), EtopBill.BillSource.valueOf(model.getContractCategory()).code));
        //新建合同，先置为无效，审核状态为初始
        model.setContractStatus(Contract.NewContractStatus.INIT.value);
        model.setContractType(0);
        model.createdAt = new Date();
        model.createdBy = UserInfoUtil.getUserInfo().getId();
        model.updatedAt = new Date();
        model.updatedBy = UserInfoUtil.getUserInfo().getId();
        //保存
        newcontractDao.createContract(model);
        //创建时，同时更新意向公司审核状态，如果存在
        EtopCompanyIntention intention = this.etopCompanyIntentionService.getCompIntentionInfoById(model.getRefCompanyId());

        if (intention != null) {
            //审核中
            intention.setReviewStatus(1);
            this.etopCompanyIntentionService.updateById(intention);
        }
        Map m = new HashMap();
        m.put("id", model.getId());
        m.put("contractNo", model.getContractNo());

        return m;
    }

    @Override
    public void submitContract(NewContractModel model) {
        model.setContractStatus(Contract.NewContractStatus.PARKER_AUDITING.value);
        updateContract(model);
    }

    @Override
    public void yzCheck(String id, String param) {
        NewContractModel model = new NewContractModel();
        if (Objects.equals("1", param)) {
            model.setContractStatus(Contract.NewContractStatus.FINANCE_AUDITING.value);
        } else if (Objects.equals("0", param)) {
            model.setContractStatus(Contract.NewContractStatus.NO_PARKER_AUDITING.value);
        } else {
            throw new RuntimeException("参数有问题");
        }
        model.setId(id);

        updateContract(model);
    }

    @Override
    public void cwCheck(String id, String param) {
        NewContractModel model = new NewContractModel();
        if (Objects.equals("1", param)) {
            model.setContractStatus(Contract.NewContractStatus.ACCEPTED.value);
        } else if (Objects.equals("0", param)) {
            model.setContractStatus(Contract.NewContractStatus.NO_FINANCE_AUDITING.value);
        } else {
            throw new RuntimeException("参数有问题");
        }
        model.setId(id);

        updateContract(model);
    }

    @Override
    public Map updateContract(NewContractModel model) {
        model.updatedAt = new Date();
        model.updatedBy = UserInfoUtil.getUserInfo().getId();
        newcontractDao.updateContract(model);

        Map m = new HashMap();
        m.put("id", model.getId());
        m.put("contractNo", model.getContractNo());

        return m;
    }


    @Override
    public void saveItem(NewContractItem item) {
        NewContractModel contractModel = newcontractDao.getContractInfoById(item.getRefContractId());

        item.setId(UUID.randomUUID().toString());
        item.createdAt = new Date();
        item.updatedAt = new Date();
        item.createdBy = UserInfoUtil.getUserInfo().getId();
        item.updatedBy = UserInfoUtil.getUserInfo().getId();
        item.setIsBill("0");
        if (Objects.equals(item.getUnitType(), "m")) {
            //如果按月算
        } else if (Objects.equals(item.getUnitType(), "d")) {
            //如果按日算
            double num = 0;
            try {
                num = DateUtil.getDateSpace(item.getStartDate(), item.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
                new RuntimeException("时间计算有问题");
            }
            //再算个总价
            double total = num * contractModel.getBuilarea() * item.getUnitPrice();
//            System.out.println("天数：" + num + " 面积：" + contractModel.getBuilarea() + " 单价：" + item.getUnitPrice());
            item.setTotalAmount(total);
        } else {
            throw new RuntimeException("传入租金类型不对");
        }

        newcontractDao.saveItem(item);
    }


    @Override
    public void delItem(String itemId, String contractId) {
        //判断合同是否有效
        contractIsPass(contractId);
        newcontractDao.delItem(itemId);
    }

    @Override
    public void updateItem(NewContractItem item) {

        //判断合同是否有效
        contractIsPass(item.getRefContractId());
        NewContractModel contractModel = newcontractDao.getContractInfoById(item.getRefContractId());
        //运算新的总价
        if (Objects.equals(item.getUnitType(), "m")) {
            //如果按月算
        } else if (Objects.equals(item.getUnitType(), "d")) {
            //如果按日算
            double num = 0;
            try {
                num = DateUtil.getDateSpace(item.getStartDate(), item.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
                new RuntimeException("时间计算有问题");
            }
            //再算个总价
            double total = num * contractModel.getBuilarea() * item.getUnitPrice();
//            System.out.println("天数：" + num + " 面积：" + contractModel.getBuilarea() + " 单价：" + item.getUnitPrice());
            item.setTotalAmount(total);
        } else {
            throw new RuntimeException("传入租金类型不对");
        }
        item.setUpdatedAt(new Date());
        item.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        newcontractDao.updateItem(item);
    }


    /**
     * //判断合同是否有效
     *
     * @param contractId
     */
    private void contractIsPass(String contractId) {
        NewContractModel contractModel = newcontractDao.getContractInfoById(contractId);
        if (contractModel == null) {
            throw new RuntimeException("合同id找不到对应的合同");
        }
        int state = contractModel.getContractStatus();
        if (Objects.equals(state, NewContractModel.DTJ) || Objects.equals(state, NewContractModel.CWWTG) || Objects.equals(state, NewContractModel.YZWTG)) {

        } else {
            throw new RuntimeException("合同已生效或已提交审核");
        }

    }
}
