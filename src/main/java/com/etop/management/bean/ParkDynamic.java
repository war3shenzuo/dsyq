package com.etop.management.bean;

import java.io.Serializable;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/28
 */
public class ParkDynamic implements Serializable{

    private static final long serialVersionUID = 3415884811360139393L;

    private String parkId; //园区id

    private String parkName;

    private Integer userNum; //管理账户数

    private Integer companyNum; //企业用户数

    private Integer contractNum; //合同数

    private Integer serviceNum; //服务申请数

    private Integer serviceCompleteNum; //服务完成数

    private Integer roomsNum;//房间数量

    private Integer type;

    public Integer getRoomsNum() {
        return roomsNum;
    }

    public void setRoomsNum(Integer roomsNum) {
        this.roomsNum = roomsNum;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Integer companyNum) {
        this.companyNum = companyNum;
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    public Integer getServiceCompleteNum() {
        return serviceCompleteNum;
    }

    public void setServiceCompleteNum(Integer serviceCompleteNum) {
        this.serviceCompleteNum = serviceCompleteNum;
    }
}
