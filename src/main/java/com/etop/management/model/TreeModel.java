package com.etop.management.model;

import java.util.List;

public class TreeModel {

	private String id ;

	private String text ;

	private String icon ;

	private state state ;

	private String type;
    
	private List<TreeModel> children;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TreeModel(){
		this.state=new state();
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public state getState() {
		return state;
	}

	public void setState(state state) {
		this.state = state;
	}

    public List<TreeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}




	public class state
    {
    	private Boolean opened ;

    	private Boolean disabled ;

    	private Boolean selected ;

    	
    	public state(){
    		opened=false;
    		disabled=false;
    		selected=false;
    	}
    	
    	
		public Boolean getOpened() {
			return opened;
		}

		public void setOpened(Boolean opened) {
			this.opened = opened;
		}

		public Boolean getDisabled() {
			return disabled;
		}

		public void setDisabled(Boolean disabled) {
			this.disabled = disabled;
		}

		public Boolean getSelected() {
			return selected;
		}

		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
		
		
    }
	
	
}
