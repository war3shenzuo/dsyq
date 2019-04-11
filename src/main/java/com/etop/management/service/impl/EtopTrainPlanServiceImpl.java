package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopTrainPlan;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopTrainPlanDao;
import com.etop.management.service.EtopTrainPlanService;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopTrainPlanService<br>
 */
@Service
public class  EtopTrainPlanServiceImpl  implements EtopTrainPlanService {

    @Autowired
    private EtopTrainPlanDao etopTrainPlanDao;

    @Autowired
    private RemindService remindService;

    @Autowired
    private RoleService roleService;

    @Override
    public EtopPage<EtopTrainPlan> search(EtopTrainPlan etopTrainPlan, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "t1.updated_at DESC");
        if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
            etopTrainPlan.setCreateType(1);
        }else {
            etopTrainPlan.setCreateType(0);
        }
        Page<EtopTrainPlan> list = etopTrainPlanDao.search(etopTrainPlan);
        for(EtopTrainPlan entity : list){
            if(entity.getCourseStatus() != null){
                entity.setStatus(entity.getCourseStatus());
            }
        }
        return new EtopPage<EtopTrainPlan>(list);
    }

    @Override
    public void add(EtopTrainPlan etopTrainPlan) {
        //创建类型(0 园区角色 1园区组角色)   用于区分不同角色创建的数据
        if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
            etopTrainPlan.setCreateType(1);
        }else {
            etopTrainPlan.setCreateType(0);
        }
        etopTrainPlan.setStatus(4);
        etopTrainPlan.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopTrainPlanDao.insert(etopTrainPlan);
    }

    @Override
    public void deleteById(EtopTrainPlan etopTrainPlan) {
        etopTrainPlanDao.deleteById(etopTrainPlan.getId());
    }

    @Override
    public EtopTrainPlan getEtopTrainPlanInfoById(String id) {
        return etopTrainPlanDao.getEtopTrainPlanInfoById(id);
    }

    @Override
    public void updateEtopTrainPlan(EtopTrainPlan etopTrainPlan) {
        etopTrainPlan.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopTrainPlan.setUpdatedAt(new Date());
        etopTrainPlanDao.update(etopTrainPlan);
    }

    @Override
    public void getEtopTrainPlanRemind() {
        List<EtopTrainPlan> list = etopTrainPlanDao.getList();
        Remind remind = new Remind();
        if(list.size() > 0 && list != null){
            for(int i=0;i<list.size();i++){
                List<EtopUser> etopUserList = remindService.getJurisdiction(list.get(i).getTarget(), Role.QX_RWTX);
                EtopTrainPlan etopTrainPlan = list.get(i);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(etopTrainPlan.getTrainDate());
                calendar.add(Calendar.DAY_OF_MONTH, -(etopTrainPlan.getCycle()));//往前推时间
                Date date = calendar.getTime();
                String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if(etopUserList != null && etopUserList.size() > 0){
                    for(int j=0;j<list.size();j++){
                        if((DateUtil.formatDate(new Date()).equals(dateStr))){
                            remind.setRemindType(Role.QX_RWTX);
                            remind.setTitle("任务提醒");
                            remind.setContent("培训主题为“" + list.get(i).getTitle() + "”的培训计划已到达设定的时间");
                            remind.setTarget(etopUserList.get(i).getId());
                            remindService.addRemind(remind);
                        }
                        if((DateUtil.formatDate(new Date()).equals(DateUtil.formatDate(list.get(i).getRemindDate())))){
                            remind.setRemindType(Role.QX_RWTX);
                            remind.setTitle("任务提醒");
                            remind.setContent("培训主题为“" + list.get(i).getTitle() + "”的培训计划已到达设定的提醒日期");
                            remind.setTarget(etopUserList.get(i).getId());
                            remindService.addRemind(remind);
                        }
                    }

                }

            }
        }
    }
}
