package com.etop.website.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.News;
import com.etop.website.dao.WebNewsDao;
import com.etop.website.service.NewsService;



@Controller
@RequestMapping("/webnews")

public class WebNewsController{
	

	Logger logger = LoggerFactory.getLogger(InparkController.class);
	@Resource
	NewsService newsService;
	@Resource
	WebNewsDao webNewsDao;
	
	@RequestMapping(value = "/newsList.do")
	public String newsList(){

		return "news_site/index";
		
	}
	
    //新闻列表
	@ResponseBody
	@RequestMapping("/getNews.do")
	public List<News> searchNews(News news) {
		try{
			return newsService.searchNews(news);
		}catch (Exception e) {
			logger.error("获取资讯列表失败",e);
			e.printStackTrace();
		}		
		return null;
	}
	
	//新闻列表
	@ResponseBody
	@RequestMapping("/getNews2.do")
	public List<News> searchNews2(String parkGroupId) {
		try{
			return newsService.searchNews2(parkGroupId);
		}catch (Exception e) {
			logger.error("获取资讯列表失败",e);
			e.printStackTrace();
		}		
		return null;
	}
	
	//资讯列表
	@ResponseBody
	@RequestMapping("/getInfo.do")
	public List<News> searchInfo(News news) {
		try{
			return newsService.searchInfo(news);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	//获取详情页
	@RequestMapping(value = "/newsPage.do")
	public String newsPage(Model model, String id) throws Exception {

			News news = newsService.searchNewsInfo(id);
			model.addAttribute("news", news);
			model.addAttribute("parkName",webNewsDao.getparkName(news.getParkId()));
			model.addAttribute("author", news.getCreatedBy());
			model.addAttribute("title", news.getTitle());
			model.addAttribute("CreatedAt",news.getCreatedAt());
			model.addAttribute("content",news.getContent());
			model.addAttribute("total_type",news.getTotalType());
			model.addAttribute("newType",news.getNewsType());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String Date=sdf.format(news.getCreatedAt());
			model.addAttribute("date",Date);
		    return "news_site/info";
		
	}
	

	
}