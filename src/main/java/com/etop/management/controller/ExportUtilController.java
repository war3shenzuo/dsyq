package com.etop.management.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etop.management.bean.BalanceStatistics;
import com.etop.management.bean.ContractReport;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ParkDynamic;
import com.etop.management.bean.ProfitLoss;
import com.etop.management.bean.Rents;
import com.etop.management.bean.ServiceReport;
import com.etop.management.constant.Constants;
import com.etop.management.service.BalanceStatisticsService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.EtopProfitLossService;
import com.etop.management.service.FaiginService;
import com.etop.management.service.ParkDynamicService;
import com.etop.management.service.ParkService;
import com.etop.management.service.RentsService;
import com.etop.management.service.ServiceService;
import com.etop.management.util.ExcelUtil;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/12/25
 */
@Controller
@RequestMapping("/exportUtil")
public class ExportUtilController {

    private final static Logger LOGGER= Logger.getLogger(ExportUtilController.class);

    @Autowired
    private ParkDynamicService parkDynamicService;

    @Autowired
    private ParkService parkService;

    @Autowired
    private RentsService rentsService;

    @Autowired
    private FaiginService faiginService;

    @Autowired
    private BalanceStatisticsService balanceStatisticsService;

    @Autowired
    private EtopProfitLossService etopProfitLossService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
	private EtopCompanyService etopCompanyService;
    @RequestMapping(value="createXls.do")
    public void export(HttpServletResponse response,String parkId,Integer type,String periods,
                       String serviceype, String start,String end,String startTime,String endTime, EtopCompany etopCompany) throws IOException, ParseException {
        response.setContentType("java/json;charset=utf-8");
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<String> sbFildes = new ArrayList<>();
        List<String> SbContent = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(type == 1){
            List<String> list = new ArrayList<>();
            EtopPage<ParkDynamic> page = null;
            list.add(parkId);
            if(parkId.equals("0")){
                List<String> parkIds = parkService.getAllParkId();
                page = parkDynamicService.getParkDynamicList(parkIds);
            }else {
                page = parkDynamicService.getParkDynamicList(list);
            }
            mapList = this.createExcelParkDynamic(page.getRows());
            String columnNames[]={"园区名称","管理账号数","园区企业数","合同数","服务申请数","服务完成数"};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"parkName","userNum","companyNum","serviceNum","serviceCompleteNum","roomsNum"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 2){
            EtopPage<Rents> page = null;
            Rents rents = new Rents();
            rents.setParkId(parkId);
            if(rents.getParkId().equals("0")){
                rents.setParkIds(parkService.getAllParkId());
                page = rentsService.search(rents,"all");
            }else {
                page = rentsService.search(rents,"");
            }
            mapList = this.createExcelRents(page.getRows());
            String columnNames[]={"园区","楼号","楼层","分区","建筑面积","使用面积","出租面积","空置面积","得房率","出租率","空置率",};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"parkName","floor","storey","area","buildArea","userArea","rentArea","vacantArea","roomRate","rents","vacancyRate"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 3){
            EtopPage<BalanceStatistics> page = null;

            BalanceStatistics balanceStatistics = new BalanceStatistics();
            if(("").equals(startTime)){
                balanceStatistics.setStartTime(null);
            }else {
                balanceStatistics.setStartTime(sdf.parse(startTime));
            }
            if(("").equals(endTime)){
                balanceStatistics.setEndTime(null);
            }else {
                balanceStatistics.setEndTime(sdf.parse(endTime));
            }
            if(("").equals(start)){
                balanceStatistics.setStart(null);
            }else {
                balanceStatistics.setStart(sdf.parse(start));
            }
            if(("").equals(end)){
                balanceStatistics.setEnd(null);
            }else {
                balanceStatistics.setEnd(sdf.parse(end));
            }
            balanceStatistics.setParkId(parkId);
            if(balanceStatistics.getParkId().equals("0")){
                balanceStatistics.setParkIds(parkService.getAllParkId());
                page = balanceStatisticsService.search(balanceStatistics, "all");
            }else {
                page = balanceStatisticsService.search(balanceStatistics, "");
            }
            mapList = this.createExcelBalanceStatistics(page.getRows());
            String columnNames[]={"园区","客户","租赁合同","服务合同","能源合同","物业合同","园区服务","其他","滞纳金","实收","合计","欠款合计"};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"parkName","companyName","leaseAmount","serviceAmount","energyAmount","propertyAmount","parkServiceAmount","otherAmount","overdueFine","amountPaid","amount","arrears"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 4){
            EtopPage<ContractReport> page = null;
            ContractReport contractReport = new ContractReport();
            contractReport.setParkId(parkId);
            if(("").equals(startTime)){
                contractReport.setStartTime(null);
            }else {
                contractReport.setStartTime(sdf.parse(startTime));
            }
            if(("").equals(endTime)){
                contractReport.setEndTime(null);
            }else {
                contractReport.setEndTime(sdf.parse(endTime));
            }
            if(("").equals(start)){
                contractReport.setStart(null);
            }else {
                contractReport.setStart(sdf.parse(start));
            }
            if(("").equals(end)){
                contractReport.setEnd(null);
            }else {
                contractReport.setEnd(sdf.parse(end));
            }
            if(contractReport.getParkId().equals("0")){
                contractReport.setParkIds(parkService.getAllParkId());
                page = faiginService.search(contractReport,"all");
            }else {
                page = faiginService.search(contractReport,"");
            }
            mapList = this.createExcelContractReport(page.getRows());
            String columnNames[]={"园区","楼号","楼层","分区","租赁合同","服务合同","能源合同","物业合同","园区服务","其他","滞纳金","实收","合计","欠款合计","回收率"};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"parkName","building","floor","block","leaseAmount","serviceAmount","energyAmount","propertyAmount","parkServiceAmount","otherAmount","overdueFine","amountPaid","amount","arrearsAmount","recoveryRate"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 5){
            ProfitLoss profitLoss = new ProfitLoss();
            profitLoss.setParkId(parkId);
            if(("").equals(periods)){
                profitLoss.setPeriods(null);
            }else {
                profitLoss.setPeriods(sdf.parse(periods + "-01"));
            }
            EtopPage<ProfitLoss> page = etopProfitLossService.search(profitLoss);
            mapList = this.createExcelProfitLoss(page.getRows());
            String columnNames[]={"大项","细项","本期额度","去年同期","上期额度","同比增长","环比增长","年累计","去年同期","同比增长","倒推12月合计",};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"items","fine","currentLimit","lastCurrent","previousLimit","yearGrowth","momGrowth","yearCumulative","lastyearPeriod","lastyearGrowth","backTotal"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 6){
            EtopPage<ServiceReport> page = null;
            ServiceReport serviceReport = new ServiceReport();
            serviceReport.setParkId(parkId);
            serviceReport.setServiceype(serviceype);
            if(("").equals(start)){
                serviceReport.setStart(null);
            }else {
                serviceReport.setStart(sdf.parse(start));
            }
            if(("").equals(end)){
                serviceReport.setEnd(null);
            }else {
                serviceReport.setEnd(sdf.parse(end));
            }
            if(serviceReport.getParkId().equals("0")){
                serviceReport.setParkIds(parkService.getAllParkId());
                page = serviceService.search(serviceReport, "all");
            }else {
                page = serviceService.search(serviceReport, "");
            }
            mapList = this.createExcelServiceReport(page.getRows());
            String columnNames[]={"园区","楼号","楼层","分区","申请数量","已撤销","已审批","已拒绝","已派工","完工","确认完工","审批比例","派工比例","完工比例"};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"parkName","building","storey","zoneNo","applicationsNum","revocationNum","approvalNum","refuseNum","workersNum","completedNum","confirmCompletedNum","approvalProportion","workersProportion","completedProportion"};//map中的key
            SbContent = Arrays.asList(keys);
        }else if(type == 7){
        	EtopPage<EtopCompany> page = null;
        	int offset =0;
        	int limit =1000;
        	String genre = "";
        	if(etopCompany.getParkId() == ""){
    			etopCompany.setParkId(UserInfoUtil.getUserInfo().getParkId());
    		}
        	page = etopCompanyService.search(etopCompany, offset, limit, genre);
        	mapList = this.createExcelCompany(page.getRows());
            String columnNames[]={"公司名称","经营类目","公司电话","联系人","联系人号码","状态","迁入时间","备注"};//列名
            sbFildes = Arrays.asList(columnNames);
            String keys[]= {"companyName","businessType","companyMobile","contact","mobile","companyStatus","inAt","remarks"};//map中的key
            SbContent = Arrays.asList(keys);
        }

        ExcelUtil.createXlsFileWithListZbxb(mapList, SbContent, sbFildes, response, type);
    }

    //园区服务
    public  List<Map<String, Object>> createExcelCompany(List<EtopCompany> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        EtopCompany etopCompany = null;
        for (int j = 0; j < list.size(); j++) {
        	etopCompany = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("companyName", etopCompany.getCompanyName());
            mapValue.put("businessType", etopCompany.getBusinessType());
            mapValue.put("companyMobile", etopCompany.getCompanyMobile());
            mapValue.put("contact", etopCompany.getContact());
            mapValue.put("mobile", etopCompany.getMobile());
            mapValue.put("companyStatus", etopCompany.getCompanyStatus());
            mapValue.put("inAt", etopCompany.getInAt());
            mapValue.put("remarks", etopCompany.getRemarks());
            listmap.add(mapValue);
        }
        return listmap;
    }
    //园区服务
    public  List<Map<String, Object>> createExcelParkDynamic(List<ParkDynamic> list) {
    	List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
    	ParkDynamic parkDynamic = null;
    	for (int j = 0; j < list.size(); j++) {
    		parkDynamic = list.get(j);
    		Map<String, Object> mapValue = new HashMap<String, Object>();
    		mapValue.put("parkName", parkDynamic.getParkName());
    		mapValue.put("userNum", parkDynamic.getUserNum());
    		mapValue.put("companyNum", parkDynamic.getCompanyNum());
    		mapValue.put("serviceNum", parkDynamic.getServiceNum());
    		mapValue.put("serviceCompleteNum", parkDynamic.getServiceCompleteNum());
    		mapValue.put("roomsNum", parkDynamic.getRoomsNum());
    		listmap.add(mapValue);
    	}
    	return listmap;
    }

    //出租率
    public  List<Map<String, Object>> createExcelRents(List<Rents> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Rents rents = null;
        for (int j = 0; j < list.size(); j++) {
            rents = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("parkName", rents.getParkName());
            mapValue.put("floor", rents.getFloor());
            mapValue.put("storey", rents.getStorey());
            mapValue.put("area", rents.getArea());
            mapValue.put("buildArea", rents.getBuildArea());
            mapValue.put("userArea", rents.getUserArea());
            mapValue.put("rentArea", rents.getRentArea());
            mapValue.put("vacantArea", rents.getVacantArea());
            mapValue.put("roomRate", rents.getRoomRate());
            mapValue.put("rents", rents.getRents());
            mapValue.put("vacancyRate", rents.getVacancyRate());
            listmap.add(mapValue);
        }
        return listmap;
    }

    //欠款统计
    public  List<Map<String, Object>> createExcelBalanceStatistics(List<BalanceStatistics> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        BalanceStatistics balanceStatistics = null;
        for (int j = 0; j < list.size(); j++) {
            balanceStatistics = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("parkName", balanceStatistics.getParkName());
            mapValue.put("companyName", balanceStatistics.getCompanyName());
            mapValue.put("leaseAmount", balanceStatistics.getLeaseAmount());
            mapValue.put("serviceAmount", balanceStatistics.getServiceAmount());
            mapValue.put("energyAmount", balanceStatistics.getEnergyAmount());
            mapValue.put("propertyAmount", balanceStatistics.getPropertyAmount());
            mapValue.put("parkServiceAmount", balanceStatistics.getParkServiceAmount());
            mapValue.put("otherAmount", balanceStatistics.getOtherAmount());
            mapValue.put("overdueFine", balanceStatistics.getOverdueFine());
            mapValue.put("amountPaid", balanceStatistics.getAmountPaid());
            mapValue.put("amount", balanceStatistics.getAmount());
            mapValue.put("arrears", balanceStatistics.getArrears());
            listmap.add(mapValue);
        }
        return listmap;
    }

    //费金回收
    public  List<Map<String, Object>> createExcelContractReport(List<ContractReport> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        ContractReport contractReport = null;
        for (int j = 0; j < list.size(); j++) {
            contractReport = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("parkName", contractReport.getParkName());
            mapValue.put("building", contractReport.getBuilding());
            mapValue.put("floor", contractReport.getFloor());
            mapValue.put("block", contractReport.getBlock());
            mapValue.put("leaseAmount", contractReport.getLeaseAmount());
            mapValue.put("serviceAmount", contractReport.getServiceAmount());
            mapValue.put("energyAmount", contractReport.getEnergyAmount());
            mapValue.put("propertyAmount", contractReport.getPropertyAmount());
            mapValue.put("parkServiceAmount", contractReport.getParkServiceAmount());
            mapValue.put("otherAmount", contractReport.getOtherAmount());
            mapValue.put("overdueFine", contractReport.getOverdueFine());
            mapValue.put("amountPaid", contractReport.getAmountPaid());
            mapValue.put("amount", contractReport.getAmount());
            mapValue.put("arrearsAmount", contractReport.getArrearsAmount());
            mapValue.put("recoveryRate", contractReport.getRecoveryRate());
            listmap.add(mapValue);
        }
        return listmap;
    }

    //收支统计
    public  List<Map<String, Object>> createExcelProfitLoss(List<ProfitLoss> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        ProfitLoss profitLoss = null;
        for (int j = 0; j < list.size(); j++) {
            profitLoss = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("items", Constants.translateItems(profitLoss));
            mapValue.put("fine", Constants.translateFine(profitLoss));
            mapValue.put("currentLimit", profitLoss.getCurrentLimit());
            mapValue.put("lastCurrent", profitLoss.getLastCurrent());
            mapValue.put("previousLimit", profitLoss.getPreviousLimit());
            mapValue.put("yearGrowth", profitLoss.getYearGrowth());
            mapValue.put("momGrowth", profitLoss.getMomGrowth());
            mapValue.put("yearCumulative", profitLoss.getYearCumulative());
            mapValue.put("lastyearPeriod", profitLoss.getLastyearPeriod());
            mapValue.put("lastyearGrowth", profitLoss.getLastyearGrowth());
            mapValue.put("backTotal", profitLoss.getBackTotal());
            listmap.add(mapValue);
        }
        return listmap;
    }


    //服务统计
    public  List<Map<String, Object>> createExcelServiceReport(List<ServiceReport> list) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        ServiceReport serviceReport = null;
        for (int j = 0; j < list.size(); j++) {
            serviceReport = list.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("parkName", serviceReport.getParkName());
            mapValue.put("building", serviceReport.getBuilding());
            mapValue.put("storey", serviceReport.getStorey());
            mapValue.put("zoneNo", serviceReport.getZoneNo());
            mapValue.put("applicationsNum", serviceReport.getApplicationsNum());
            mapValue.put("revocationNum", serviceReport.getRevocationNum());
            mapValue.put("approvalNum", serviceReport.getApprovalNum());
            mapValue.put("refuseNum", serviceReport.getRefuseNum());
            mapValue.put("workersNum", serviceReport.getWorkersNum());
            mapValue.put("completedNum", serviceReport.getCompletedNum());
            mapValue.put("confirmCompletedNum", serviceReport.getConfirmCompletedNum());
            mapValue.put("approvalProportion", serviceReport.getApprovalProportion());
            mapValue.put("workersProportion", serviceReport.getWorkersProportion());
            mapValue.put("completedProportion", serviceReport.getCompletedProportion());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
