package com.etop.management.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;



@Repository
public interface BusinessServiceDao {

    Page<ServiceQuotation> makeBusinessService(Map<String, Object> condition);
    
    Page<ServiceQuotation> makeBusinessServiceGroup(Map<String, Object> condition);

    int addBusinessService (ServiceQuotation serviceQuotation);
    
    Page<Etopservice> getBusinessService(Map<String, Object> condition);
    
    Etopservice getBusinessServiceInfoById(String serviceId);
    
    Page<ServiceDispatch> dispatchList(Map<String, Object> condition);
 }
