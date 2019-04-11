package com.etop.website.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class News{

    private String id;
    
    private String parkId;
    
    private String title;
    
    private String createdBy;
    
    private String newsImg;
    

    
    private String content;
    
    private String state;
    
    private String newsType;
    

    
    private String totalType;
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date createdAt; 
    
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getNewsImg() {
		return newsImg;
	}

	public void setNews_img(String newsImg) {
		this.newsImg = newsImg;
	}

	public java.util.Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public java.lang.String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}


	public String getTotalType() {
		return totalType;
	}
	public void setTotalType(String totalType) {
		this.totalType = totalType;
	}

	

}
