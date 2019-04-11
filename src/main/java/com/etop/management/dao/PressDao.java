package com.etop.management.dao;

import com.etop.management.bean.News;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/19
 */
public interface PressDao {

    Page<News> select(News news);

    Integer insert(News news);

    News getPressInfoById(String id);

    Integer updatePressById(News news);

    Integer updateState(News news);
}
