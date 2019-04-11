package com.etop.management.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.bean.ServiceReport;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ServiceDao;
import com.etop.management.service.ServiceService;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/8
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<ServiceReport> search(ServiceReport serviceReport, String type) {

        Page<ServiceReport> page = new Page<ServiceReport>();

        if(serviceReport.getEnd() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(serviceReport.getEnd());
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            Date date = calendar.getTime();
            serviceReport.setEnd(date);
        }

        if(("all").equals(type)){
            for(int i=0;i<serviceReport.getParkIds().size();i++){
                serviceReport.setParkId(serviceReport.getParkIds().get(i));
                DecimalFormat df = new DecimalFormat("#.00%");
                List<ServiceReport> list = serviceDao.search(serviceReport);
                ServiceReport vo = new ServiceReport();
                vo.setRevocationNum((double) 0);
                vo.setApprovalNum((double) 0);
                vo.setWorkersNum((double) 0);
                vo.setCompletedNum((double) 0);
                vo.setConfirmCompletedNum((double) 0);
                vo.setRefuseNum((double) 0);
                Double totalApplications = serviceDao.getTotalApplications(serviceReport);
                if(list != null && list.size() > 0 ){
                    for(ServiceReport entity : list){
                        vo.setParkName(entity.getParkName());
                        vo.setApplicationsNum(totalApplications);
                        if(entity.getServiceStatus() == 201){//已撤销
                            vo.setRevocationNum(entity.getStatusNum());
                        }else if(entity.getServiceStatus() == 202){//已审批
                            vo.setApprovalNum(entity.getStatusNum());
                        }else if(entity.getServiceStatus() == 203){//已派工
                            vo.setWorkersNum(entity.getStatusNum());
                        }else if(entity.getServiceStatus() == 204){//已完工
                            vo.setCompletedNum(entity.getStatusNum());
                        }else if(entity.getServiceStatus() == 300){//确认完工
                            vo.setConfirmCompletedNum(entity.getStatusNum());
                        }else if(entity.getServiceStatus() == 301){//拒绝
                            vo.setRefuseNum(entity.getStatusNum());
                        }
                        if(totalApplications == 0){
                            vo.setApprovalProportion("0");
                            vo.setWorkersProportion("0");
                            vo.setCompletedProportion("0");
                        }
                    }
                    //已审批
                    vo.setApprovalNum(vo.getApprovalNum()+vo.getWorkersNum()+vo.getCompletedNum()+vo.getConfirmCompletedNum());//已审核+已派工+完工+确认完工
                    //已派工
                    vo.setWorkersNum(vo.getWorkersNum()+vo.getCompletedNum()+vo.getConfirmCompletedNum());//已派工+完工+确认完工
                    //已完工
                    vo.setCompletedNum(vo.getCompletedNum()+vo.getConfirmCompletedNum());//完工+确认完工

                    if(totalApplications == 0){
                        vo.setApprovalProportion("0");
                    }else {
                        vo.setApprovalProportion(df.format(vo.getApprovalNum() / totalApplications));//审批比例  (已审批/总数)
                    }

                    if(vo.getApprovalNum().equals("0")){
                        vo.setWorkersProportion("0");
                    }else {
                        vo.setWorkersProportion(df.format(vo.getWorkersNum() / vo.getApprovalNum()));//派工比例  (已派工/已审批)
                    }

                    if(vo.getWorkersNum().equals("0")){
                        vo.setCompletedProportion("0");
                    }else {
                        vo.setCompletedProportion(df.format(vo.getConfirmCompletedNum() / vo.getWorkersNum()));//完工比例  (确认完工/已派工)
                    }
                    page.add(vo);
                }else {
                    Park park = parkDao.getParkInfo(serviceReport.getParkId());
                    vo.setParkName(park.getParkName());
                    page.add(vo);
                }
            }
        }else {
            //园区
            DecimalFormat df = new DecimalFormat("#.00%");
            List<ServiceReport> list = serviceDao.search(serviceReport);
            ServiceReport vo = new ServiceReport();
            vo.setRevocationNum((double) 0);
            vo.setApprovalNum((double) 0);
            vo.setWorkersNum((double) 0);
            vo.setCompletedNum((double) 0);
            vo.setConfirmCompletedNum((double) 0);
            vo.setRefuseNum((double) 0);
            Double totalApplications = serviceDao.getTotalApplications(serviceReport);
            if(list != null && list.size() > 0 ){
                for(ServiceReport entity : list){
                    vo.setParkName(entity.getParkName());
                    vo.setApplicationsNum(totalApplications);
                    if(entity.getServiceStatus() == 201){//已撤销
                        vo.setRevocationNum(entity.getStatusNum());
                    }else if(entity.getServiceStatus() == 202){//已审批
                        vo.setApprovalNum(entity.getStatusNum());
                    }else if(entity.getServiceStatus() == 203){//已派工
                        vo.setWorkersNum(entity.getStatusNum());
                    }else if(entity.getServiceStatus() == 204){//已完工
                        vo.setCompletedNum(entity.getStatusNum());
                    }else if(entity.getServiceStatus() == 300){//确认完工
                        vo.setConfirmCompletedNum(entity.getStatusNum());
                    }else if(entity.getServiceStatus() == 301){//拒绝
                        vo.setRefuseNum(entity.getStatusNum());
                    }
                    if(totalApplications == 0){
                        vo.setApprovalProportion("0");
                        vo.setWorkersProportion("0");
                        vo.setCompletedProportion("0");
                    }
                }
                //已审批
                vo.setApprovalNum(vo.getApprovalNum()+vo.getWorkersNum()+vo.getCompletedNum()+vo.getConfirmCompletedNum());//已审核+已派工+完工+确认完工
                //已派工
                vo.setWorkersNum(vo.getWorkersNum()+vo.getCompletedNum()+vo.getConfirmCompletedNum());//已派工+完工+确认完工
                //已完工
                vo.setCompletedNum(vo.getCompletedNum()+vo.getConfirmCompletedNum());//完工+确认完工
                if(totalApplications == 0){
                    vo.setApprovalProportion("0");
                }else {
                    vo.setApprovalProportion(df.format(vo.getApprovalNum() / totalApplications));//审批比例  (已审批/总数)
                }

                if(vo.getApprovalNum().equals("0")){
                    vo.setWorkersProportion("0");
                }else {
                    vo.setWorkersProportion(df.format(vo.getWorkersNum() / vo.getApprovalNum()));//派工比例  (已派工/已审批)
                }

                if(vo.getWorkersNum().equals("0")){
                    vo.setCompletedProportion("0");
                }else {
                    vo.setCompletedProportion(df.format(vo.getConfirmCompletedNum() / vo.getWorkersNum()));//完工比例  (确认完工/已派工)
                }

                page.add(vo);
            }

            //楼
            List<ServiceReport> floorList = serviceDao.searchFloor(serviceReport);
            List<ServiceReport> floorNumList = serviceDao.getTotalApplicationsByFloor(serviceReport);
            List<ServiceReport> floorReportList = this.typeData(floorList, floorNumList);
            for(ServiceReport report : floorReportList){
                page.add(report);
            }

            //层
            List<ServiceReport> storeyList = serviceDao.searchStorey(serviceReport);
            List<ServiceReport> storeyNumList = serviceDao.getTotalApplicationsByStorey(serviceReport);
            List<ServiceReport> storeyReportList = this.typeData(storeyList, storeyNumList);
            for(ServiceReport report : storeyReportList){
                page.add(report);
            }

            //区
            List<ServiceReport> areaList = serviceDao.searchArea(serviceReport);
            List<ServiceReport> areaNumList = serviceDao.getTotalApplicationsByArea(serviceReport);
            List<ServiceReport> areaReportList = this.typeData(areaList, areaNumList);
            for(ServiceReport report : areaReportList){
                page.add(report);
            }

        }
        return new EtopPage<ServiceReport>(page);
    }

    private List<ServiceReport> typeData(List<ServiceReport> vo, List<ServiceReport> bo){
        DecimalFormat df = new DecimalFormat("#.00%");
        Page<ServiceReport> page = new Page<ServiceReport>();
        if(vo != null && bo != null){
            for(ServiceReport service : bo){

                service.setRevocationNum((double) 0);
                service.setApprovalNum((double) 0);
                service.setWorkersNum((double) 0);
                service.setCompletedNum((double) 0);
                service.setConfirmCompletedNum((double) 0);
                service.setRefuseNum((double) 0);
                service.setApplicationsNum(service.getTotalApplications());
                /*if(service.getTotalApplications() == 0){
                    service.setApprovalProportion("0");
                    service.setWorkersProportion("0");
                    service.setCompletedProportion("0");
                }*/
                for(ServiceReport report : vo){
                    if(service.getId().equals(report.getId())){
                        if(report.getServiceStatus() == 201){//待回执
                            service.setRevocationNum(report.getStatusNum());
                        }else if(report.getServiceStatus() == 202){//已审批
                            service.setApprovalNum(report.getStatusNum());
                        }else if(report.getServiceStatus() == 203){//已派工
                            service.setWorkersNum(report.getStatusNum());
                        }else if(report.getServiceStatus() == 204){//已完工
                            service.setCompletedNum(report.getStatusNum());
                        }else if(report.getServiceStatus() == 300){//确认完工
                            service.setConfirmCompletedNum(report.getStatusNum());
                        }else if(report.getServiceStatus() == 301){//拒绝
                            service.setRefuseNum(report.getStatusNum());
                        }
                    }
                }
                //已审批
                service.setApprovalNum(service.getApprovalNum()+service.getWorkersNum()+service.getCompletedNum()+service.getConfirmCompletedNum());//已审核+已派工+完工+确认完工
                //已派工
                service.setWorkersNum(service.getWorkersNum()+service.getCompletedNum()+service.getConfirmCompletedNum());//已派工+完工+确认完工
                //已完工
                service.setCompletedNum(service.getCompletedNum()+service.getConfirmCompletedNum());//完工+确认完工

                if(service.getApplicationsNum() == 0){
                    service.setApprovalProportion("0");
                }else {
                    service.setApprovalProportion(df.format(service.getApprovalNum() / service.getApplicationsNum()));//审批比例  (已审批/总数)
                }

                if(service.getApprovalNum().equals("0") || service.getWorkersNum() == 0.0){
                    service.setWorkersProportion("0");
                }else {
                    service.setWorkersProportion(df.format(service.getWorkersNum() / service.getApprovalNum()));//派工比例  (已派工/已审批)
                }

                if(service.getWorkersNum().equals("0") || service.getConfirmCompletedNum()== 0.0){
                    service.setCompletedProportion("0");
                }else {
                    service.setCompletedProportion(df.format(service.getConfirmCompletedNum() / service.getWorkersNum()));//完工比例  (确认完工/已派工)
                }


            }
        }
        return bo;
    }
}
