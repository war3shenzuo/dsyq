package com.etop.website.dao;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Settled;



@Repository
public interface SettledDao {

	public void addApply(Settled settled);
	
	public void find(Settled settled);
}
