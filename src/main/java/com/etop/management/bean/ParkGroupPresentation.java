package com.etop.management.bean;


/**
 * 
 * <br>
 * 园区组介绍<br>
 */
public class ParkGroupPresentation {

	private String id;
	private String parkGroupId;//园区组ID
	private int presentation;//介绍类别
	private String title;//标题
	private String content;//内容
	private String parkGroupPreimg;//介绍图片
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParkGroupId() {
		return parkGroupId;
	}
	public void setParkGroupId(String parkGroupId) {
		this.parkGroupId = parkGroupId;
	}
	public int getPresentation() {
		return presentation;
	}
	public void setPresentation(int presentation) {
		this.presentation = presentation;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getParkGroupPreimg() {
		return parkGroupPreimg;
	}
	public void setParkGroupPreimg(String parkGroupPreimg) {
		this.parkGroupPreimg = parkGroupPreimg;
	}

	
}
