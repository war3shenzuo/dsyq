package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;
import com.etop.management.dao.NewsDao;
import com.etop.management.service.NewsService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class NewsServiceImpl implements NewsService {
	
	@Resource
	NewsDao newDao;
	
	@Override
	public EtopPage<News> getNewsList(News news) throws Exception{
		
		EtopPage<News> page = new EtopPage<News>();
		
		int BTablePageNum = (news.getOffset()/news.getLimit());
		
		//设置分页
		PageHelper.startPage(BTablePageNum+1, news.getLimit());
		
		List<News> list =  new ArrayList<News>();
		
		list = newDao.getNewsList(news);
		
		PageInfo<News> table =new PageInfo<News>(list);
		
		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public News getNewsInfo(String newsId) throws Exception{
		
		return newDao.getNewsInfo(newsId);
	}

	@Override
	public void addNews(News news) throws Exception{
		news.setCreatedBy(UserInfoUtil.getUserInfo().getUserName());
		news.setCreatedAt(new Date());
		news.setTotalType("2");
		news.setNewsType("资讯");
		newDao.addNews(news);
	}

	@Override
	public void updateNews(News news) throws Exception{
		
		newDao.updateNews(news);
		
	}

	@Override
	public void activeOrCloseNews(News news) {
		
		newDao.activeOrCloseNews(news);
	}

	@Override
	public void deleteByIds(News news) {
		newDao.deleteByIds(news.getIds());
	}

}
