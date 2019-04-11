package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;
import com.etop.management.dao.PressDao;
import com.etop.management.service.PressService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/19
 */
@Service
public class PressServiceImpl implements PressService {

    @Autowired
    private PressDao pressDao;

    @Override
    public EtopPage<News> getPressList(News news, Integer offset, Integer limit) {

        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        news.setParkId(UserInfoUtil.getUserInfo().getParkId());
        news.setTotalType("1");
        Page<News> page = pressDao.select(news);
        return new EtopPage<News>(page);
    }

    @Override
    public News getPressInfoById(News news) {
        return pressDao.getPressInfoById(news.getId());
    }

    @Override
    public void add(News news) {
        news.setParkId(UserInfoUtil.getUserInfo().getParkId());
        news.setCreatedBy(UserInfoUtil.getUserInfo().getUserName());
        news.setNewsType("园区新闻");
        news.setTotalType("1");
        pressDao.insert(news);
    }

    @Override
    public void updatePressById(News news) {
        news.setUpdatedBy(UserInfoUtil.getUserInfo().getUserName());
        pressDao.updatePressById(news);
    }

    @Override
    public void activeOrCloseNews(News news) {
        pressDao.updateState(news);
    }
}
