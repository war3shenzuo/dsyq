package com.etop.management.controller;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;
import com.etop.management.bean.ResultType;
import com.etop.management.service.NewsService;

@Controller
@RequestMapping(value="/news")
public class NewsController extends BaseAppController{
	
	@Resource
	NewsService newsService;
	
	private  final static Logger LOGGER =Logger.getLogger(NewsController.class);

	@RequestMapping("/index.do")
	public String  index(Model model){
		model.addAttribute("readonly",isReadOnly("/news/index.do"));
		return "news/index";
	}

	@RequestMapping("/addPage.do")
	public String  addPage(){
		return "news/addNews";
	}
	
	@ResponseBody
	@RequestMapping(value="/getNewsList.do")
	public EtopPage<News> getNewsList(News news){
		
		EtopPage<News> page = null;
		
		try {
			page = newsService.getNewsList(news);
		} catch (Exception e) {
			LOGGER.error("查询新闻资讯报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	
	@RequestMapping(value="/getNewsInfo.do")
	public String getNewsInfo(String newsId,Model model,String readonly){
		try{
			model.addAttribute("readonly", readonly);
			model.addAttribute("news",newsService.getNewsInfo(newsId));
		} catch (Exception e) {
			
			LOGGER.error("查询新闻资讯详细信息");
			
			e.printStackTrace();
			
		}
		
		return "news/NewsInfo"; 
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addNews.do")
	public ResultType addNews(News param){
		
		ResultType result = null;
		
		try {
			
			newsService.addNews(param);
			
			result = ResultType.getSuccess("添加新闻资讯成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("添加新闻资讯失败");
			
			LOGGER.error("添加新闻资讯失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateNews.do")
	public ResultType updateNews(News news) {

		ResultType result = null;

		try {

			newsService.updateNews(news);

			result = ResultType.getSuccess("更新新闻资讯信息");

		} catch (Exception e) {

			LOGGER.error("更新新闻资讯失败");

			result = ResultType.getFail("更新新闻资讯失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/activeOrCloseNews.do")
	public ResultType activeOrClosePark(News news) {

		ResultType result = null;

		try {

			newsService.activeOrCloseNews(news);

			result = ResultType.getSuccess("激活/关闭新闻");

		} catch (Exception e) {

			LOGGER.error("激活/关闭新闻失败");

			result = ResultType.getFail("激活/关闭新闻失败！");

			e.printStackTrace();
		}

		return result;

	}


	@ResponseBody
	@RequestMapping(value = "/deleteNews.do")
	public ResultType deleteNews(News news) {

		ResultType result = null;

		try {

			Integer size = news.getIds().size();
			newsService.deleteByIds(news);

			result = ResultType.getSuccess("删除新闻成功! ", size);

		} catch (Exception e) {

			LOGGER.error("删除新闻失败! ");

			result = ResultType.getFail("删除新闻失败! ");

			e.printStackTrace();
		}

		return result;

	}
	
	
	
}
