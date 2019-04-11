package com.etop.management.bean;

import java.io.Serializable;
import java.util.UUID;

public class IdentifiableBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6996319083696505088L;
	/**
	 * 
	 */


	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public IdentifiableBean()
	{
		super();
		
		this.id=UUID.randomUUID().toString();
	}
}
