package com.etop.management.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.etop.management.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TrackableModel extends IdentifiableModel {

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date updatedAt;
	
	public String createdBy;
	
	public String updatedBy;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedAtStr()
	{
		return DateUtil.formatDateTime(this.updatedAt);		
	}
	
	public String getCreatedAtStr()
	{
		return DateUtil.formatDateTime(this.createdAt);
	}
}
