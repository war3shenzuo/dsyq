package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Alan.
 *
 * 拜访信息
 * @author 何利庭
 * @DATE 2016/8/28
 */
public class EtopCompanyMaintain extends TrackableBean {

    private static final long serialVersionUID = 4266534441029068457L;

    private String companyId;//   公司ID
    private java.lang.String visitors;//   拜访者
    private String receiver;// 接待人
    private java.lang.String position;//   职位
    private java.lang.String mobile;//   联系方式
    private java.lang.String content;//  内容
    private java.lang.String cause;//   拜访原因

    @DateTimeFormat(pattern = Constants.DATE_FMT_PATTERN_YMD)
    @JsonFormat(pattern = Constants.DATE_FMT_PATTERN_YMD, timezone = "GMT+8")
    private java.util.Date remindAt;//   提醒时间

    @NotDatabaseField
    private List<String> ids;

    @NotDatabaseField
    private List<String> parkIds;

    @NotDatabaseField
    private List<String> companyIds;

    @NotDatabaseField
    private String parkName;

    @NotDatabaseField
    private  String companyName;

    @NotDatabaseField
    private Integer companyType;//公司类型  0:意向公司 1:正式公司

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getVisitors() {
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getRemindAt() {
        return remindAt;
    }

    public void setRemindAt(Date remindAt) {
        this.remindAt = remindAt;
    }
}
