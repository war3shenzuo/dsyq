package com.etop.management.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.constant.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/13
 */
public class ProfitLoss implements Serializable {

    private static final long serialVersionUID = 2345012859782928150L;

    private java.lang.String id;//
    private Integer reportType;//类型
    @DateTimeFormat(pattern = Constants.DATE_FMT_PATTERN_YM)
    @JsonFormat(pattern = Constants.DATE_FMT_PATTERN_YM, timezone = "GMT+8")
    private java.util.Date periods;//   期数
    private java.util.Date moreperiods;//   期数

    private Integer items;//   大项
    private Integer fine;//   细项
    private Double currentLimit;//   本期额度
    private Double lastCurrent;//   去年同期
    private Double previousLimit;//   上期额度
    private java.lang.String yearGrowth;//   同比增长
    private java.lang.String momGrowth;//   环比增长
    private Double yearCumulative;//   年累计
    private Double lastyearPeriod;//   去年同期
    private java.lang.String lastyearGrowth;//   同比增长
    private Double backTotal;//   倒推12月合计
    private java.util.Date createdAt;//
    private java.lang.String createdBy;//
    private java.util.Date updatedAt;//
    private java.lang.String updatedBy;//
    private String parkId;
    private Integer balanceMonthly;//   是否按月结算
    @NotDatabaseField
    private Integer multiPeriod;//期数

    @NotDatabaseField
    private String periodsStr;

    @NotDatabaseField
    private List<String> parkIds;

    @NotDatabaseField
    private String refContractId;

    @NotDatabaseField
    private Date startDate;//开始时间

    @NotDatabaseField
    private Date endDate;//结束时间

    @NotDatabaseField
    private Double dailyUnitPrice;//日单价

    @NotDatabaseField
    private Double monthlyUnitPrice;//月单价

    @NotDatabaseField
    private Double totalAmount;//总价

    @NotDatabaseField
    private Double content;//面积

    @NotDatabaseField
    private Date lastMonth;//上月同期

    @NotDatabaseField
    private Date lastYear;//去年同期

    @NotDatabaseField
    private Integer start;

    @NotDatabaseField
    private Integer end;

    @NotDatabaseField
    private Date pushMonths;
    
    @NotDatabaseField
    private Date yearFirst;
    
    @NotDatabaseField
    private Date lastyearFirst;
    
    public Integer getMultiPeriod() {
        return multiPeriod;
    }

    public void setMultiPeriod(Integer multiPeriod) {
        this.multiPeriod = multiPeriod;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getPeriodsStr() {
        return periodsStr;
    }

    public void setPeriodsStr(String periodsStr) {
        this.periodsStr = periodsStr;
    }

    public Date getPushMonths() {
        return pushMonths;
    }

    public void setPushMonths(Date pushMonths) {
        this.pushMonths = pushMonths;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Date getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(Date lastMonth) {
        this.lastMonth = lastMonth;
    }

    public Date getLastYear() {
        return lastYear;
    }

    public void setLastYear(Date lastYear) {
        this.lastYear = lastYear;
    }

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

    public Double getDailyUnitPrice() {
        return dailyUnitPrice;
    }

    public void setDailyUnitPrice(Double dailyUnitPrice) {
        this.dailyUnitPrice = dailyUnitPrice;
    }

    public Double getMonthlyUnitPrice() {
        return monthlyUnitPrice;
    }

    public void setMonthlyUnitPrice(Double monthlyUnitPrice) {
        this.monthlyUnitPrice = monthlyUnitPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getContent() {
        return content;
    }

    public void setContent(Double content) {
        this.content = content;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPeriods() {
        return periods;
    }

    public void setPeriods(Date periods) {
        this.periods = periods;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public Double getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(Double currentLimit) {
        this.currentLimit = currentLimit;
    }

    public Double getLastCurrent() {
        return lastCurrent;
    }

    public void setLastCurrent(Double lastCurrent) {
        this.lastCurrent = lastCurrent;
    }

    public Double getPreviousLimit() {
        return previousLimit;
    }

    public void setPreviousLimit(Double previousLimit) {
        this.previousLimit = previousLimit;
    }

    public String getYearGrowth() {
        return yearGrowth;
    }

    public void setYearGrowth(String yearGrowth) {
        this.yearGrowth = yearGrowth;
    }

    public String getMomGrowth() {
        return momGrowth;
    }

    public void setMomGrowth(String momGrowth) {
        this.momGrowth = momGrowth;
    }

    public Double getYearCumulative() {
        return yearCumulative;
    }

    public void setYearCumulative(Double yearCumulative) {
        this.yearCumulative = yearCumulative;
    }

    public Double getLastyearPeriod() {
        return lastyearPeriod;
    }

    public void setLastyearPeriod(Double lastyearPeriod) {
        this.lastyearPeriod = lastyearPeriod;
    }

    public Double getBackTotal() {
        return backTotal;
    }

    public void setBackTotal(Double backTotal) {
        this.backTotal = backTotal;
    }

    public String getLastyearGrowth() {
        return lastyearGrowth;
    }

    public void setLastyearGrowth(String lastyearGrowth) {
        this.lastyearGrowth = lastyearGrowth;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

	public Date getYearFirst() {
		return yearFirst;
	}

	public void setYearFirst(Date yearFirst) {
		this.yearFirst = yearFirst;
	}

	public Date getLastyearFirst() {
		return lastyearFirst;
	}

	public void setLastyearFirst(Date lastyearFirst) {
		this.lastyearFirst = lastyearFirst;
	}

	public Integer getBalanceMonthly() {
		return balanceMonthly;
	}

	public void setBalanceMonthly(Integer balanceMonthly) {
		this.balanceMonthly = balanceMonthly;
	}

	public java.util.Date getMoreperiods() {
		return moreperiods;
	}

	public void setMoreperiods(java.util.Date moreperiods) {
		this.moreperiods = moreperiods;
	}
}
