package com.etop.management.servicenew;


import com.etop.management.bean.NewContractItem;
import com.etop.management.model.NewContractModel;

import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @author: shixianjie
 * Date: 2019/5/16
 * Time: 2:43 PM
 */
public interface NewContractService {

    Map addContract(NewContractModel model);

    Map updateContract(NewContractModel model);

    void saveItem(NewContractItem item);

    //删除分期
    void delItem(String itemId,String contractId);

    void updateItem(NewContractItem item);

    void submitContract(NewContractModel model);

    void yzCheck(String id, String param);

    void cwCheck(String id, String param);
}
