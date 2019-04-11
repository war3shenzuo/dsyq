package com.etop.management.bean;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class News extends PageParam{


	private java.lang.String id;//   唯一标识
	private java.lang.String parkId;//   园区
	private java.lang.String title;//   新闻标题
	private java.lang.String createdBy;//   新闻作者
	private java.lang.String newsImg;//   图片
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createdAt;//   发布时间
	private java.lang.String content;//   新闻内容
	private java.lang.String state;//   新闻状态
	private java.lang.String newsType;//   新闻类别
	private String totalType;// 咨询类别 1.园区新闻 2.行业资讯

	private Date updatedAt;

	private String updatedBy;

	@NotDatabaseField
	private List<String> ids;

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

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	public java.lang.String getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(java.lang.String newsImg) {
		this.newsImg = newsImg;
	}
	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getNewsType() {
		return newsType;
	}
	public void setNewsType(java.lang.String newsType) {
		this.newsType = newsType;
	}
	public String getTotalType() {
		return totalType;
	}
	public void setTotalType(String totalType) {
		this.totalType = totalType;
	}
	
	

}
