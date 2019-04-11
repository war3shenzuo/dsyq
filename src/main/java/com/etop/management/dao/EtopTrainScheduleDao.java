package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopTrainSchedule;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/11/14
 */
public interface EtopTrainScheduleDao {

    Page<EtopTrainSchedule> select(EtopTrainSchedule etopTrainSchedule);

    void insert(EtopTrainSchedule etopTrainSchedule);

    void deleteByIds(@Param("ids") List<String> ids);

    EtopTrainSchedule getEtopTrainScheduleInfoById(@Param("id") String id);

    void update(EtopTrainSchedule etopTrainSchedule);

    Integer selectNum(String id);
}
