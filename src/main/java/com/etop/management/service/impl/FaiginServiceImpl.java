package com.etop.management.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.ContractReport;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.dao.ContractReportDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.FaiginService;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 * 费金回收
 * @author 何利庭
 * @DATE 2016/10/12
 */
@Service
public class FaiginServiceImpl implements FaiginService {

    @Autowired
    private ContractReportDao contractReportDao;

    @Autowired
    private ParkDao parkDao;

    @Override
    public EtopPage<ContractReport> search(ContractReport contractReport, String type) {

        Page<ContractReport> list = new Page<ContractReport>();

        if(contractReport.getEnd() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(contractReport.getEnd());
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            Date date = calendar.getTime();
            contractReport.setEnd(date);
        }

        if(contractReport.getEndTime() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(contractReport.getEndTime());
            calendar.add(Calendar.DAY_OF_MONTH, +1);
            Date date = calendar.getTime();
            contractReport.setEndTime(date);
        }

        if(("all").equals(type)){
            for(int i=0;i<contractReport.getParkIds().size();i++){
                contractReport.setParkId(contractReport.getParkIds().get(i));
                ContractReport entity = contractReportDao.search(contractReport);//园区
                if(entity.getParkName() == null){
                    Park park = parkDao.getParkInfo(contractReport.getParkId());
                    entity.setParkName(park.getParkName());
                }
                List<ContractReport> contractReports = contractReportDao.searchAmount(contractReport);//不同账单金额
                ContractReport vo = this.sameCode(entity, contractReports);
                list.add(vo);
            }
        }else {
            ContractReport entity = contractReportDao.search(contractReport);//园区
            if(entity.getParkName() == null){
                Park park = parkDao.getParkInfo(contractReport.getParkId());
                entity.setParkName(park.getParkName());
            }
            List<ContractReport> contractReports = contractReportDao.searchAmount(contractReport);//不同账单金额
            ContractReport vo = this.sameCode(entity, contractReports);
            list.add(vo);
            //楼
            Page<ContractReport> floorList = contractReportDao.searchFloor(contractReport);
            List<ContractReport> entityFloor = contractReportDao.searchAmountFloor(contractReport);
            List<ContractReport> arrayFloor = this.typeData(floorList, entityFloor);
            list.addAll(arrayFloor);
            //层
            Page<ContractReport> storeyList = contractReportDao.searchStorey(contractReport);
            List<ContractReport> entityStorey = contractReportDao.searchAmountStorey(contractReport);
            List<ContractReport> arrayStorey = this.typeData(storeyList, entityStorey);
            list.addAll(arrayStorey);
            //区
            Page<ContractReport> areaList = contractReportDao.searchArea(contractReport);
            List<ContractReport> entityArea = contractReportDao.searchAmountArea(contractReport);
            List<ContractReport> arrayArea = this.typeData(areaList, entityArea);
            list.addAll(arrayArea);
        }
        return new EtopPage<ContractReport>(list);
    }

    private List<ContractReport> typeData(Page<ContractReport> contractReports, List<ContractReport> list){
        DecimalFormat df = new DecimalFormat("#.00%");
        DecimalFormat dfs = new DecimalFormat("#.00");
        Double deposit = 0.0;
        Double remission = 0.0;
        Double accountRemission = 0.0;
        Double overdueRemission = 0.0;
        if(contractReports != null && list != null){
            for(ContractReport vo : contractReports){
            	remission += vo.getAccountRemission() + vo.getOverdueRemission();
//            	accountRemission += vo.getAccountRemission();
//            	overdueRemission += vo.getOverdueRemission();
                vo.setLeaseAmount(0.0);
                vo.setEnergyAmount(0.0);
                vo.setPropertyAmount(0.0);
                vo.setServiceAmount(0.0);
                vo.setParkServiceAmount(0.0);
                vo.setOtherAmount(0.0);
                for(ContractReport entity : list){
                    if(entity.getId().equals(vo.getId())){
                        if(entity.getBillSource() == 1 ){
                            vo.setLeaseAmount(entity.getAmount());
                        }else if(entity.getBillSource() == 3){
                            vo.setEnergyAmount(entity.getAmount());
                        }else if(entity.getBillSource() == 4){
                            vo.setPropertyAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 5)){
                            vo.setServiceAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 6)){
                            vo.setParkServiceAmount(entity.getAmount());
                        }else if((entity.getBillSource() == 0)){//押金
                        	vo.setOtherAmount(entity.getAmount());
                        	deposit =entity.getAmount();
                        }else if((entity.getBillSource() == 9)){//其他
                        	vo.setOtherAmount(entity.getAmount() + deposit);
                        }else if((vo.getAccountRemission() != 0.0)){//本金减免
                        	vo.setAccountRemission(accountRemission);
                        }else if((vo.getOverdueRemission() != 0.0)){//滞纳金金减免
                        	vo.setOverdueRemission(overdueRemission);
                        }
                    }
                }
                if( vo.getOtherAmount() != null){
                	vo.setAmount(Double.valueOf(dfs.format(vo.getAmount()+vo.getOverdueFine())));
                }else{
                	vo.setAmount(Double.valueOf(dfs.format(vo.getAmount()+ vo.getOverdueFine())));
                }
//                vo.setArrearsAmount(Double.valueOf(dfs.format(vo.getAmount() - vo.getAmountPaid())));
                Double recoveryRate = 0.0;
                if(vo.getAmount() != 0.0 || vo.getAmount() != null){
                    recoveryRate = vo.getAmountPaid() / vo.getAmount();
                }
                if(vo.getAmountPaid() == 0.0){
                    vo.setRecoveryRate("0");
                }else {
                    vo.setRecoveryRate(df.format(recoveryRate));
                }
            }
        }
        return contractReports;
    }

    private ContractReport sameCode(ContractReport contractReport, List<ContractReport> list){
        DecimalFormat df = new DecimalFormat("#.00%");
        DecimalFormat dfs = new DecimalFormat("#.00");
        Double deposit = 0.0;
        Double remission = 0.0;
        Double accountRemission = 0.0;
        Double overdueRemission = 0.0;
        if(contractReport != null){
            for(ContractReport entity : list){
            	remission += entity.getAccountRemission() + entity.getOverdueRemission();
            	accountRemission += entity.getAccountRemission();
            	overdueRemission += entity.getOverdueRemission();
//            	contractReport.setAccountRemission(entity.getAccountRemission());
//            	contractReport.setOverdueRemission(entity.getOverdueRemission());
                if(entity.getBillSource() == 1 ){
                    contractReport.setLeaseAmount(entity.getAmount());
                    contractReport.setEnergyAmount(0.0);
                    contractReport.setPropertyAmount(0.0);
                    contractReport.setServiceAmount(0.0);
                }else if(entity.getBillSource() == 3){
                    contractReport.setEnergyAmount(entity.getAmount());
                }else if(entity.getBillSource() == 4){
                    contractReport.setPropertyAmount(entity.getAmount());
                }else if((entity.getBillSource() == 5)){
                    contractReport.setServiceAmount(entity.getAmount());
                }else if((entity.getBillSource() == 6)){
                    contractReport.setParkServiceAmount(entity.getAmount());
                }else if((entity.getBillSource() == 0)){//押金
                	contractReport.setOtherAmount(entity.getAmount());
                	deposit =entity.getAmount();
                }else if((entity.getBillSource() == 9)){//其他
                	contractReport.setOtherAmount(entity.getAmount() + deposit);
                }
            }
        }
        Double recoveryRate = 0.0;
        if(contractReport.getOtherAmount() != null){
        	contractReport.setAmount(Double.valueOf(dfs.format(contractReport.getAmount() + contractReport.getOverdueFine())));
        }else{
        	contractReport.setAmount(Double.valueOf(dfs.format(contractReport.getAmount()+ contractReport.getOverdueFine())));
        }
        contractReport.setArrearsAmount(Double.valueOf(dfs.format(contractReport.getAmount() - contractReport.getAmountPaid()-remission)));
//        contractReport.setArrearsAmount(Double.valueOf(dfs.format(contractReport.getArrearsAmount()-remission)));
        contractReport.setAccountRemission(accountRemission);
        contractReport.setOverdueRemission(overdueRemission);
        if(contractReport.getAmount() != 0.0 || contractReport.getAmount() != null){
            recoveryRate = contractReport.getAmountPaid() / contractReport.getAmount();
        }
        if(contractReport.getAmountPaid() == 0.0){
            contractReport.setRecoveryRate("0");
        }else {
            contractReport.setRecoveryRate(df.format(recoveryRate));
        }
        return contractReport;
    }

}
