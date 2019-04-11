package com.etop.website.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.News;



@Repository
public interface WebNewsDao {


	 List<News> searchNews(News news);
	 
	 List<News> searchInfo(News news);

  	 News searchNewsInfo(String id);

	List<News> searchNews2(String parkGourpId);
	
	String getparkName(String id);
  	 
//  	 List<News> listAll(News news);


  	 


}
