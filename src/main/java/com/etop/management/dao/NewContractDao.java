package com.etop.management.dao;

import com.etop.management.bean.NewContractItem;
import com.etop.management.model.NewContractModel;

/**
 * Created by Intellij IDEA
 *
 * @author: shixianjie
 * Date: 2019/5/21
 * Time: 9:51 AM
 */
public interface NewContractDao {

    void createContract(NewContractModel entity);

    void saveItem(NewContractItem item);

    //获取合同信息
    NewContractModel getContractInfoById(String refContractId);

    void delItem(String itemId);

    void updateItem(NewContractItem item);

    void updateContract(NewContractModel model);
}
