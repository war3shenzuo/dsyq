package com.etop.management.bean;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public class EtopCompanyBusiness extends TrackableBean {

    private static final long serialVersionUID = 3758887360048829574L;

    private String companyId;// 公司id
    private String businessPractice;//   招商方式
    private String electronicRetailing;//   网商
    private String physicalStore;//   实体店
    private String investmentJoin;//   招商加盟
    private String agency;//   代理
    private String other;//   其他

    @NotDatabaseField
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusinessPractice() {
        return businessPractice;
    }

    public void setBusinessPractice(String businessPractice) {
        this.businessPractice = businessPractice;
    }

    public String getElectronicRetailing() {
        return electronicRetailing;
    }

    public void setElectronicRetailing(String electronicRetailing) {
        this.electronicRetailing = electronicRetailing;
    }

    public String getPhysicalStore() {
        return physicalStore;
    }

    public void setPhysicalStore(String physicalStore) {
        this.physicalStore = physicalStore;
    }

    public String getInvestmentJoin() {
        return investmentJoin;
    }

    public void setInvestmentJoin(String investmentJoin) {
        this.investmentJoin = investmentJoin;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
