package com.etop.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.bean.ParkDynamic;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.ParkDynamicService;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/28
 */
@Service
public class ParkDynamicServiceImpl implements ParkDynamicService {

    @Autowired
    private EtopCompanyDao etopCompanyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private EtopServiceDao etopServiceDao;

    @Autowired
    private EtopFloorRoomDao etopFloorRoomDao;

    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<ParkDynamic> getParkDynamicList(List<String> parkId) {

        Page<ParkDynamic> list = new Page<ParkDynamic>();
        ParkDynamic parkDynamic = new ParkDynamic();

        for(int i=0;i<parkId.size();i++){
            parkDynamic = new ParkDynamic();
            Park park = parkDao.getParkInfo(parkId.get(i));
            parkDynamic.setParkName(park.getParkName());
            parkDynamic.setParkId(parkId.get(i));
            parkDynamic.setUserNum(userDao.getUserNum(parkId.get(i)).getUserNum());
            parkDynamic.setCompanyNum(etopCompanyDao.getCompanyNum(parkId.get(i)).getCompanyNum());
            parkDynamic.setContractNum(contractDao.getContractNum(parkId.get(i)).getContractNum());
            parkDynamic.setServiceNum(etopServiceDao.getServiceNum(parkId.get(i)).getServiceNum());
            parkDynamic.setServiceCompleteNum(etopServiceDao.getServiceCompleteNum(parkId.get(i)).getServiceCompleteNum());
            parkDynamic.setRoomsNum(etopFloorRoomDao.getRoomsNum(parkId.get(i)).getRoomsNum());
            list.add(parkDynamic);
        }
        return new EtopPage<ParkDynamic>(list);

    }
}
