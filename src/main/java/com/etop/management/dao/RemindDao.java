package com.etop.management.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.management.bean.Remind;

/** 
 * @author lvxiwei 

 * @time   2016年9月19日 下午4:09:17 
 */
@Repository
public interface RemindDao {

	
	List<Remind> searchbytarget(String target);
	
	void addRemind(Remind remind);
	
	Integer insert(List<Remind> list);
	
	Remind searchbyId (String id);
	
	void upadteStatus (String id);
	
	void upadteTop (Remind remind);
	
	void finalTop(Remind remind);

}
