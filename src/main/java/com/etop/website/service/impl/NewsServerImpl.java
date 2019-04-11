package com.etop.website.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.News;
import com.etop.website.dao.WebNewsDao;
import com.etop.website.service.NewsService;






@Named("NewsService")
@Service
public class NewsServerImpl implements NewsService {
	
	@Resource
	private WebNewsDao webNewsDao;


	
	public List<News> searchNews(News news) throws Exception {
	  List<News> list=webNewsDao.searchNews(news); 

          return list;
	}

	
	public List<News> searchInfo(News news) throws Exception {
		 List<News> list=webNewsDao.searchInfo(news);
		return list;
	}




	@Override
	public News searchNewsInfo(String id) {
		
		return webNewsDao.searchNewsInfo(id);
	}


	@Override
	public List<News> searchNews2(String parkGourpId) {
		List<News> list=webNewsDao.searchNews2(parkGourpId);
		return list;
	}


//	@Override
//	public List<News> listAll(News news,HttpServletRequest request) throws Exception {
//		PageModel pageModel = new PageModel();
//			int page =1 ;
//		// 获得当前页
//		   if (page != 0) {
//		    pageModel.setPage(page);
//		   }
//		   		   
//		   pageModel.setPageSize(10);// 设置页面显示最大 值	
//		   
//		   List<News> list = new ArrayList<>();
//		   
//		   list = newsDao.listAll(news);
//
//		   pageModel.setTotalCount(list.size()); // 数据总条数
//
//		   pageModel.setNum(5); // 设置当前页的前后距离,/**前后各显示5页**/
//		   // 通过当前页和	   
//		   List<News> aboutList=newsDao.listAll(news);
//		   
//		   pageModel.setItems(aboutList);
//		   request.setAttribute("count", aboutList.size());// 放置在request中
//		   request.setAttribute("pageModel", pageModel);
//		   request.setAttribute("page", pageModel.getPage());
//		   
//		return aboutList;	   
//		
//	}
//
//	
//

}