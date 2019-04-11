package com.etop.management.service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.PersonalInfomation;
import com.etop.management.bean.Workexperience;
import com.github.pagehelper.Page;

/**
 * Created by lvxiwei.
 *
 * @author lvxiwei
 * @DATE 2016/10/17
 */
public interface  PersonalUserService {

    EtopPage<Workexperience> getExperienceByemployeesId(String employeesId, Integer offset, Integer limit);
	 
    public void updateUserInfo(PersonalInfomation infomation);
    
    int add(Workexperience workexperience);
    
    public void updateUserExperience(Workexperience workexperience);
    
    Workexperience getExperienceInfoById(String id);
    
    int deleteExperience(String id);
}
