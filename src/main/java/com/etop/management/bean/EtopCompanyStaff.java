package com.etop.management.bean;

import java.util.List;

/**
 * Created by Alan.
 *
 * 公司员工关系
 * @author 何利庭
 * @DATE 2016/8/28
 */
public class EtopCompanyStaff extends TrackableBean {

    private static final long serialVersionUID = 5870502482420102946L;

    private String companyId;// 公司id
    private String userId;// 员工id

    @NotDatabaseField
    private List<String> userIds;

    @NotDatabaseField
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
