package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyMaintain;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopCompanyMaintainDao;
import com.etop.management.service.EtopCompanyMaintainService;
import com.etop.management.service.RemindService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alex.
 *
 * @author 何利庭
 * @DATE 2016/8/23
 */
@Service
public class EtopCompanyMaintainServiceImpl implements EtopCompanyMaintainService {

    @Autowired
    private EtopCompanyMaintainDao etopCompanyMaintainDao;

    @Autowired
    private RemindService remindService;

    @Autowired
    private EtopCompanyDao etopCompanyDao;

    @Override
    public EtopPage<EtopCompanyMaintain> search(EtopCompanyMaintain etopCompanyMaintain, Integer offset, Integer limit) {

        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        Page<EtopCompanyMaintain> page = new Page<EtopCompanyMaintain>();

        if(etopCompanyMaintain.getCompanyType().equals(0)){
            page = etopCompanyMaintainDao.selectIntention(etopCompanyMaintain);//意向公司
        }else{
            page = etopCompanyMaintainDao.select(etopCompanyMaintain);//正式公司
        }
        return new EtopPage<EtopCompanyMaintain>(page);
    }

    @Override
    public void deleteById(EtopCompanyMaintain etopCompanyMaintain) {
        etopCompanyMaintainDao.deleteById(etopCompanyMaintain.getIds());
    }

    @Override
    public void add(EtopCompanyMaintain etopCompanyMaintain) {
        etopCompanyMaintain.setId(UUID.randomUUID().toString());
        Date now = new Date();
        etopCompanyMaintain.setCreatedAt(now);
        etopCompanyMaintain.setUpdatedAt(now);
        etopCompanyMaintain.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyMaintainDao.insert(etopCompanyMaintain);

    }

    @Override
    public void updateById(EtopCompanyMaintain etopCompanyMaintain) {
        Date now = new Date();
        etopCompanyMaintain.setUpdatedAt(now);
        etopCompanyMaintain.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyMaintainDao.updateById(etopCompanyMaintain);
    }

    @Override
    public EtopCompanyMaintain getMaintainInfoById(EtopCompanyMaintain etopCompanyMaintain) {
        return etopCompanyMaintainDao.getMaintainInfoById(etopCompanyMaintain.getId(),etopCompanyMaintain.getCompanyId());
    }

    @Override
    public EtopCompanyMaintain getMaintainInfoByIntentionId(EtopCompanyMaintain etopCompanyMaintain) {
        return etopCompanyMaintainDao.getMaintainInfoByIntentionId(etopCompanyMaintain.getId(),etopCompanyMaintain.getCompanyId());
    }

    @Override
    public void getMaintainList() {
        /*Date remindAt = null;
        DateTime now = DateTime.now().withTimeAtStartOfDay();
        remindAt = now.minusDays(3).toDate(); //往前推3天*/

        List<EtopCompanyMaintain> list = etopCompanyMaintainDao.getMaintainList(getNextDay(new Date()));

        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
                Remind remind = new Remind();
                List<EtopUser> etopUserList = null;
                EtopCompany etopCompany = etopCompanyDao.getCompInfoById(list.get(i).getCompanyId());

                if (etopCompany != null) {
                    etopUserList = remindService.getJurisdiction(etopCompany.getParkId(), Role.QX_KHTX);
                }

                if(etopUserList != null && etopUserList.size() > 0){
                    for(int j=0;j<list.size();j++){
                        remind.setRemindType(Role.QX_KHTX);
                        remind.setTitle("拜访提醒");
                        remind.setContent("“" + list.get(i).getCompanyName() + "”公司已到达设定拜访时间");
                        remind.setTarget(etopUserList.get(i).getId());
                        remindService.addRemind(remind);
                    }
                }

            }
        }
        /*list.forEach(m->{
            this.transform(m);
        });*/
    }

    public static String getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -3);//往前推3天
        date = calendar.getTime();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateStr;
    }
}
