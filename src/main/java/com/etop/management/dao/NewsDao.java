package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.etop.management.bean.News;

@Repository
public interface NewsDao {
	
	News getNewsInfo(String newsId);

	void addNews(News news);

	void updateNews(News news);

	List<News> getNewsList(News news);
	
	void activeOrCloseNews(News news);

	void deleteByIds(@Param("ids") List<String> ids);
}
