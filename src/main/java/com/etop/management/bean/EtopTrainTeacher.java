package com.etop.management.bean;import java.io.Serializable;import java.util.List;/** *  * <br> * <b>功能：</b>EtopTrainTeacherEntity<br> */public class EtopTrainTeacher implements Serializable{	private static final long serialVersionUID = 5110232456599392278L;	private String id;//	private String name;//   姓名	private String photo;//   照片	private String profile;//   简介	private String courseId;//	@NotDatabaseField	private List<String> ids;	public List<String> getIds() {		return ids;	}	public void setIds(List<String> ids) {		this.ids = ids;	}	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getPhoto() {	    return this.photo;	}	public void setPhoto(String photo) {	    this.photo=photo;	}	public String getProfile() {	    return this.profile;	}	public void setProfile(String profile) {	    this.profile=profile;	}	public String getCourseId() {		return courseId;	}	public void setCourseId(String courseId) {		this.courseId = courseId;	}}