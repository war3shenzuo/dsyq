package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ServiceReport;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/8
 */
public interface ServiceService {

    EtopPage<ServiceReport> search(ServiceReport serviceReport, String type);

}
