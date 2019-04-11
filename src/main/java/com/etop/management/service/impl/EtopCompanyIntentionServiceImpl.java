package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopCompanyIntentionDao;
import com.etop.management.dao.EtopCompanyMaintainDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.ParkService;
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
public class EtopCompanyIntentionServiceImpl implements EtopCompanyIntentionService {

    @Autowired
    private EtopCompanyIntentionDao etopCompanyIntentionDao;

    @Autowired
    private ParkService parkService;

    @Autowired
    private EtopFloorRoomDao etopFloorRoomDao;

    @Autowired
    private RemindService remindService;
    
    @Autowired
    private EtopCompanyMaintainDao etopCompanyMaintainDao;

    @Override
    public EtopPage<EtopCompanyIntention> search(EtopCompanyIntention etopCompanyIntention, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "created_at DESC");
        Page<EtopCompanyIntention> list = etopCompanyIntentionDao.select(etopCompanyIntention);
        for(EtopCompanyIntention Intention :list){
        	Intention.setTimes(etopCompanyMaintainDao.count(Intention.getId()));
        }
        return new EtopPage<EtopCompanyIntention>(list);
    }

    @Override
    public void add(EtopCompanyIntention newEtopCompanyIntention) {

        newEtopCompanyIntention.setId(UUID.randomUUID().toString());

        Date now = new Date();
        newEtopCompanyIntention.setCreatedAt(now);
        newEtopCompanyIntention.setUpdatedAt(now);
        if(UserInfoUtil.getUserInfo() != null){
            newEtopCompanyIntention.setCreatedBy(UserInfoUtil.getUserInfo().getId());
            newEtopCompanyIntention.setParkId(UserInfoUtil.getUserInfo().getParkId());
        }
        newEtopCompanyIntention.setReviewStatus(0);
        newEtopCompanyIntention.setCompanyStatus(1);
        etopCompanyIntentionDao.insert(newEtopCompanyIntention);
        
        
        
        Remind remind = new Remind();
        List<EtopUser> list = remindService.getJurisdiction(newEtopCompanyIntention.getParkId(), Role.QX_KHTX);
        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
        remind.setRemindType(Role.QX_KHTX);	
        remind.setTitle("公司入驻提醒");
        remind.setContent(" 有新的来访客户（" + newEtopCompanyIntention.getCompanyName() + "）生成，请及时联系！");
        remind.setTarget(list.get(i).getId());
        remindService.addRemind(remind);
            }
        }

    }

    @Override
    public void deleteById(EtopCompanyIntention etopCompanyIntention) {
        List<String> ids = etopCompanyIntention.getIds();
        etopCompanyIntentionDao.deleteById(ids);
    }

    @Override
    public void updateById(EtopCompanyIntention etopCompanyIntention) {
        Date now = new Date();
        etopCompanyIntention.setUpdatedAt(now);
        etopCompanyIntention.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyIntention.setCompanyStatus(1);//0 未读、1 已读
        etopCompanyIntentionDao.updateById(etopCompanyIntention);
    }

    @Override
    public EtopCompanyIntention getCompInfoById(List<String> id) {
        EtopCompanyIntention etopCompanyIntention = etopCompanyIntentionDao.getCompInfoById(id);
        return etopCompanyIntention;
    }

    @Override
    public EtopCompanyIntention getCompIntentionInfoById(String companyId) {
        return etopCompanyIntentionDao.getCompIntention(companyId);
    }

    @Override
    public void getComIntentionByParkId() {
        Remind remind = new Remind();
        List<String> parkIds = parkService.getParkIdList();
//        List<EtopCompanyIntention> etopCompanyIntentionList = etopCompanyIntentionDao.getCompInfoByParkId(parkIds);//园区下所有意向公司
//        List<EtopFloorRoom> etopFloorRoomList = etopFloorRoomDao.getFloorRoomsList();//所有未出租的房间
        for(String parkId : parkIds){
        List<EtopCompanyIntention> etopCompanyIntentionList = etopCompanyIntentionDao.getCompanyInfoByParkId(parkId);//园区下所有意向公司
        List<EtopFloorRoom> etopFloorRoomList = etopFloorRoomDao.getFloorRoomsListByparkId(parkId);//所有未出租的房间
        for(EtopCompanyIntention intention : etopCompanyIntentionList){
            for (EtopFloorRoom etopFloorRoom : etopFloorRoomList){

                //面积
                Double area = intention.getSmallArea();
                Double area1 = intention.getArea();
                Double area2 = etopFloorRoom.getBuildArea();
                Boolean aBoolean = area2 >= area && area2 <= area1;

                //价格
                Double price = intention.getLowPrice();
                Double price1 = intention.getPrice();
                Double price2 = Double.valueOf(etopFloorRoom.getMonthPrice()).doubleValue();
                Boolean bBoolean = price2 >= price && price2 <= price1;

                //层高
                Double layerHigh = intention.getLowLayerHigh();
                Double layerHigh1 = intention.getLayerHigh();
                Double layerHigh2 = Double.valueOf(etopFloorRoom.getLayerHigh()).doubleValue();
                Boolean cBoolean = layerHigh2 >= layerHigh && layerHigh2 <= layerHigh1;

                if( aBoolean || bBoolean || cBoolean || intention.getOrientation().equals(etopFloorRoom.getOrientation())){

                    List<EtopUser> list = remindService.getJurisdiction(intention.getParkId(), Role.QX_KHTX);
                    if(list != null && list.size() > 0){
                        for(int i=0;i<list.size();i++){
                            remind.setRemindType(Role.QX_KHTX);	
                            remind.setTitle("公司入驻提醒");
//                            remind.setContent("现有“" + intention.getCompanyName() + "”企业符合" + etopFloorRoom.getRoomNum() + "房间");
                            remind.setContent(" 已发现有房间“" + etopFloorRoom.getRoomNum() + "”适合来访客户" + intention.getCompanyName() + "，请及时联系！");
                            remind.setTarget(list.get(i).getId());
                            remindService.addRemind(remind);
                        }
                    }
                }

            }
        }
        }
    }

    @Override
    public EtopCompanyIntention selectCompanyIntention(String companyName, String id, String parkId) {
        return etopCompanyIntentionDao.selectCompanyIntention(companyName, id, parkId);
    }

	@Override
	public String proveIntentionCompanyName(String companyName, String parkId) {
		return etopCompanyIntentionDao.proveIntentionCompanyName(companyName,parkId);
	}
	
	@Override
	public String proveCompanyName(String companyName, String parkId) {
		return etopCompanyIntentionDao.proveCompanyName(companyName,parkId);
	}

}
