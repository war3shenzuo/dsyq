package com.etop.website.service;

import java.util.List;

import com.etop.website.bean.News;



public interface NewsService {


    public List<News> searchNews(News news) throws Exception;
    
    public List<News> searchInfo(News news) throws Exception;

	public News searchNewsInfo(String id);

	public List<News> searchNews2(String parkGourpId);


//	List<News> listAll(News news, HttpServletRequest request) throws Exception;



}
