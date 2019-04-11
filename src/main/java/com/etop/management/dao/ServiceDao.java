package com.etop.management.dao;

import java.util.List;

import com.etop.management.bean.ServiceReport;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/8
 */
public interface ServiceDao {

    List<ServiceReport> search(ServiceReport serviceReport);

    Double getTotalApplications(ServiceReport serviceReport);

    List<ServiceReport> searchFloor(ServiceReport serviceReport);//楼

    List<ServiceReport> getTotalApplicationsByFloor(ServiceReport serviceReport);//各楼下服务总数

    List<ServiceReport> searchStorey(ServiceReport serviceReport);//层

    List<ServiceReport> getTotalApplicationsByStorey(ServiceReport serviceReport);//各层下服务总数

    List<ServiceReport> searchArea(ServiceReport serviceReport);//区

    List<ServiceReport> getTotalApplicationsByArea(ServiceReport serviceReport);//各区下服务总数

}
