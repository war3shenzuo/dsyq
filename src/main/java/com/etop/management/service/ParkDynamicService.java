package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ParkDynamic;

/**
 * Created by Alan.
 * 园区动态统计数据
 *
 * @author 何利庭
 * @DATE 2016/9/28
 */
public interface ParkDynamicService {

    EtopPage<ParkDynamic> getParkDynamicList(List<String> parkId);

}
