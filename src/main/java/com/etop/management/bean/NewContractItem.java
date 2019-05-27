package com.etop.management.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Intellij IDEA
 *
 * @author: shixianjie
 * Date: 2019/5/21
 * Time: 10:57 AM
 */
public class NewContractItem extends TrackableBean {

    /**
     *
     */
    public static final long serialVersionUID = -1563738426014833359L;
    private String refContractId;//   关联合同ID
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;//   开始日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;//   结束日期
    private double unitPrice;//   日单价

    private double totalAmount;//   总价
    private String unitType;//租金类型（m:月：d是按日）,
    private double wyPrice;//   物业费
    private String isBill;//是否出账;


    public String getRefContractId() {
        return refContractId;
    }

    public void setRefContractId(String refContractId) {
        this.refContractId = refContractId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public double getWyPrice() {
        return wyPrice;
    }

    public void setWyPrice(double wyPrice) {
        this.wyPrice = wyPrice;
    }

    public String getIsBill() {
        return isBill;
    }

    public void setIsBill(String isBill) {
        this.isBill = isBill;
    }
}
