package com.etop.management.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alan.
 * 出租率
 *
 * @author 何利庭
 * @DATE 2016/10/4
 */
public class Rents implements Serializable{

    private static final long serialVersionUID = -1271053463414588886L;

    private String id;
    private String parkId;
    private String parkName;
    private String floor;//楼号
    private String storey;//楼层
    private String area;//分区
    private Double buildArea;//建筑面积
    private Double userArea;//使用面积
    private Double rentArea;//出租面积
    private String rents;//出租率
    private Double vacantArea;//空置面积
    private String vacancyRate;//空置率
    private String roomRate;//得房率

    @NotDatabaseField
    private Integer totalNum;//总房间数

    @NotDatabaseField
    private Integer rentNum;//出租的房间数

    @NotDatabaseField
    private String  refFloorId;

    @NotDatabaseField
    private Double userAreaFloor;

    @NotDatabaseField
    private List<String> parkIds;

    public String getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(String roomRate) {
        this.roomRate = roomRate;
    }

    public List<String> getParkIds() {
        return parkIds;
    }

    public void setParkIds(List<String> parkIds) {
        this.parkIds = parkIds;
    }

    public Double getUserAreaFloor() {
        return userAreaFloor;
    }

    public void setUserAreaFloor(Double userAreaFloor) {
        this.userAreaFloor = userAreaFloor;
    }

    public String getRefFloorId() {
        return refFloorId;
    }

    public void setRefFloorId(String refFloorId) {
        this.refFloorId = refFloorId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getRentNum() {
        return rentNum;
    }

    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Double getUserArea() {
        return userArea;
    }

    public void setUserArea(Double userArea) {
        this.userArea = userArea;
    }

    public Double getRentArea() {
        return rentArea;
    }

    public void setRentArea(Double rentArea) {
        this.rentArea = rentArea;
    }

    public Double getVacantArea() {
        return vacantArea;
    }

    public void setVacantArea(Double vacantArea) {
        this.vacantArea = vacantArea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRents() {
        return rents;
    }

    public void setRents(String rents) {
        this.rents = rents;
    }

    public String getVacancyRate() {
        return vacancyRate;
    }

    public void setVacancyRate(String vacancyRate) {
        this.vacancyRate = vacancyRate;
    }
}
