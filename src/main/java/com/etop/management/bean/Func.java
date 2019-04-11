package com.etop.management.bean;
/**
 * 
 * <br>
 * <b>功能：</b>EtopFuncEntity<br>
 */
public class Func{
	
	
	public static final String READ = "0";
	public static final String WRITE = "1";
	
	private java.lang.String id;//   
	private java.lang.String funcName;//   
	private java.lang.String funcCode;//   
	private java.lang.String funcDescribe;//   
	private java.lang.String activated;//   
	private java.lang.String loadUrl;//   
	private java.lang.String parentId;//   
	private java.lang.String sortId;//
	private String isRead;//
	private String icon;
	
	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getFuncName() {
	    return this.funcName;
	}
	public void setFuncName(java.lang.String funcName) {
	    this.funcName=funcName;
	}
	public java.lang.String getFuncCode() {
	    return this.funcCode;
	}
	public void setFuncCode(java.lang.String funcCode) {
	    this.funcCode=funcCode;
	}
	public java.lang.String getFuncDescribe() {
	    return this.funcDescribe;
	}
	public void setFuncDescribe(java.lang.String funcDescribe) {
	    this.funcDescribe=funcDescribe;
	}
	public java.lang.String getActivated() {
	    return this.activated;
	}
	public void setActivated(java.lang.String activated) {
	    this.activated=activated;
	}
	public java.lang.String getLoadUrl() {
	    return this.loadUrl;
	}
	public void setLoadUrl(java.lang.String loadUrl) {
	    this.loadUrl=loadUrl;
	}
	public java.lang.String getParentId() {
	    return this.parentId;
	}
	public void setParentId(java.lang.String parentId) {
	    this.parentId=parentId;
	}
	public java.lang.String getSortId() {
	    return this.sortId;
	}
	public void setSortId(java.lang.String sortId) {
	    this.sortId=sortId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
	
	
}

