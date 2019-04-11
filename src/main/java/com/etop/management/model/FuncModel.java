package com.etop.management.model;

import java.io.Serializable;
import java.util.List;

public class FuncModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//   
	private String funcName;//   
	private String loadUrl;//   
	private String parentId;//
	private String  icon;//图标
	
	List<FuncModel> children;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<FuncModel> getChildren() {
		return children;
	}

	public void setChildren(List<FuncModel> children) {
		this.children = children;
	}
	
	
}
