package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;

public interface PressService {

	EtopPage<News> getPressList(News news, Integer offset, Integer limit);

	News getPressInfoById(News news);

	void add(News news);

	void updatePressById(News news);

	void activeOrCloseNews(News news);
}
