package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Alan.
 *
 * 公司附加信息
 * @author 何利庭
 * @DATE 2016/8/28
 */
public class EtopCompanyInformation extends TrackableBean {

    private static final long serialVersionUID = 9151353408401999209L;

    private String companyId;//   公司ID
    private java.lang.String threePaper;//   三证
    private java.lang.String otherThreePaper;//   其他三证
    private java.lang.String otherPaper;//   其他资料
    private java.lang.String bankAccount;//   账户
    private java.lang.String openBank;//   开户银行
    private java.lang.String openName;//   开户名称
    private java.lang.String bankAccountSpare;//   备用帐号
    private java.lang.String openBankSpare;//   备用开户银行
    private java.lang.String openNameSpare;//   备用开户名称
    private java.lang.String legalPersonName;//   法人姓名
    private java.lang.String leaglPersonMobile;//   法人联系电话
    private java.lang.String leaglOtherContact;//   其他联系方式
    private java.lang.String leaglPersonCode;//   法人身份证
    private java.lang.String leaglPersonAddress;//   法人住址
    private java.lang.String leaglPersonDegrees;//   法人学历

    @DateTimeFormat(pattern = Constants.DATE_FMT_PATTERN_YMD)
    @JsonFormat(pattern = Constants.DATE_FMT_PATTERN_YMD, timezone = "GMT+8")
    private Date leaglPersonBirthday;//   法人生日
    private java.lang.String leaglPersonSchool;//   法人毕业学校

    @DateTimeFormat(pattern = Constants.DATE_FMT_PATTERN_YMD)
    @JsonFormat(pattern = Constants.DATE_FMT_PATTERN_YMD, timezone = "GMT+8")
    private Date leaglPersonGraduation;//   法人毕业时间

    private java.lang.String degreesImg;//   学位证
    private java.lang.String diplomaImg;//   毕业证
    private java.lang.String otherImg;//   其他证件
    private java.lang.String cardImg;//   身份证

    @NotDatabaseField
    private List<String> companyIds;

    @NotDatabaseField
    private String etopCompanyBusinesses;

    public String getEtopCompanyBusinesses() {
        return etopCompanyBusinesses;
    }

    public void setEtopCompanyBusinesses(String etopCompanyBusinesses) {
        this.etopCompanyBusinesses = etopCompanyBusinesses;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getThreePaper() {
        return threePaper;
    }

    public void setThreePaper(String threePaper) {
        this.threePaper = threePaper;
    }

    public String getOtherThreePaper() {
        return otherThreePaper;
    }

    public void setOtherThreePaper(String otherThreePaper) {
        this.otherThreePaper = otherThreePaper;
    }

    public String getOtherPaper() {
        return otherPaper;
    }

    public void setOtherPaper(String otherPaper) {
        this.otherPaper = otherPaper;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }

    public String getBankAccountSpare() {
        return bankAccountSpare;
    }

    public void setBankAccountSpare(String bankAccountSpare) {
        this.bankAccountSpare = bankAccountSpare;
    }

    public String getOpenBankSpare() {
        return openBankSpare;
    }

    public void setOpenBankSpare(String openBankSpare) {
        this.openBankSpare = openBankSpare;
    }

    public String getOpenNameSpare() {
        return openNameSpare;
    }

    public void setOpenNameSpare(String openNameSpare) {
        this.openNameSpare = openNameSpare;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLeaglPersonMobile() {
        return leaglPersonMobile;
    }

    public void setLeaglPersonMobile(String leaglPersonMobile) {
        this.leaglPersonMobile = leaglPersonMobile;
    }

    public String getLeaglOtherContact() {
        return leaglOtherContact;
    }

    public void setLeaglOtherContact(String leaglOtherContact) {
        this.leaglOtherContact = leaglOtherContact;
    }

    public String getLeaglPersonCode() {
        return leaglPersonCode;
    }

    public void setLeaglPersonCode(String leaglPersonCode) {
        this.leaglPersonCode = leaglPersonCode;
    }

    public String getLeaglPersonAddress() {
        return leaglPersonAddress;
    }

    public void setLeaglPersonAddress(String leaglPersonAddress) {
        this.leaglPersonAddress = leaglPersonAddress;
    }

    public String getLeaglPersonDegrees() {
        return leaglPersonDegrees;
    }

    public void setLeaglPersonDegrees(String leaglPersonDegrees) {
        this.leaglPersonDegrees = leaglPersonDegrees;
    }

    public String getLeaglPersonSchool() {
        return leaglPersonSchool;
    }

    public void setLeaglPersonSchool(String leaglPersonSchool) {
        this.leaglPersonSchool = leaglPersonSchool;
    }

    public Date getLeaglPersonBirthday() {
        return leaglPersonBirthday;
    }

    public void setLeaglPersonBirthday(Date leaglPersonBirthday) {
        this.leaglPersonBirthday = leaglPersonBirthday;
    }

    public Date getLeaglPersonGraduation() {
        return leaglPersonGraduation;
    }

    public void setLeaglPersonGraduation(Date leaglPersonGraduation) {
        this.leaglPersonGraduation = leaglPersonGraduation;
    }

    public String getDegreesImg() {
        return degreesImg;
    }

    public void setDegreesImg(String degreesImg) {
        this.degreesImg = degreesImg;
    }

    public String getDiplomaImg() {
        return diplomaImg;
    }

    public void setDiplomaImg(String diplomaImg) {
        this.diplomaImg = diplomaImg;
    }

    public String getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(String otherImg) {
        this.otherImg = otherImg;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }
}
