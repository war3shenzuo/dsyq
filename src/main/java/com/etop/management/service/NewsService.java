package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;

public interface NewsService {

	EtopPage<News> getNewsList(News news) throws Exception;

	News getNewsInfo(String newsId) throws Exception;

	void addNews(News param) throws Exception;

	void updateNews(News news) throws Exception;

	void activeOrCloseNews(News news);

	void deleteByIds(News news);

}
