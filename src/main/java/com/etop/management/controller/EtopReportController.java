package com.etop.management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.BalanceStatistics;
import com.etop.management.bean.ContractReport;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ParkDynamic;
import com.etop.management.bean.ProfitLoss;
import com.etop.management.bean.Rents;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.ServiceReport;
import com.etop.management.dao.EtopProfitLossDao;
import com.etop.management.service.BalanceStatisticsService;
import com.etop.management.service.EtopProfitLossService;
import com.etop.management.service.FaiginService;
import com.etop.management.service.ParkDynamicService;
import com.etop.management.service.ParkService;
import com.etop.management.service.RentsService;
import com.etop.management.service.ServiceService;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/28
 */
@Controller
@RequestMapping("/etopReport")
public class EtopReportController extends BaseAppController{

    private final static Logger LOGGER= Logger.getLogger(EtopReportController.class);

    @Autowired
    private ParkDynamicService parkDynamicService;

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
    private ParkService parkService;

    @Autowired
    private EtopProfitLossDao etopProfitLossDao;
    
    @RequestMapping("/addProfitLossPage.do")
    public String addPage(Model model) {
        if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
            model.addAttribute("userType", 4);
        }
        model.addAttribute("parkId", UserInfoUtil.getUserParkInfo().getId());
        return "etopReport/addProfitLoss";
    }

    @RequestMapping("/etopReportList.do")
    public Object etopNoticeList(Model model){
        if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
            model.addAttribute("userType", 4);
            model.addAttribute("parks", parkService.getParkName(getParkIds()));
        }else {
            model.addAttribute("parks", UserInfoUtil.getUserParkInfo().getParkName());
            model.addAttribute("parkId", UserInfoUtil.getUserParkInfo().getId());
        }
        return "etopReport/etopReportList";
    }


    /**
     * 园区动态
     *
     * @param parkId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getParkDynamicList.do")
    private Object getParkDynamicList(String parkId){
        List<String> list = new ArrayList<>();
        EtopPage<ParkDynamic> page = null;
        list.add(parkId);
        if(parkId.equals("0")){
            List<String> parkIds = parkService.getAllParkId();
            page = parkDynamicService.getParkDynamicList(parkIds);
        }else {
            page = parkDynamicService.getParkDynamicList(list);
        }
        return page;

    }

    /**
     * 出租率
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRentsList.do")
    public Object getRentsList(Rents rents){
        EtopPage<Rents> page = null;
        if(rents.getParkId().equals("0")){
            rents.setParkIds(parkService.getAllParkId());
            page = rentsService.search(rents,"all");
        }else {
            page = rentsService.search(rents,"");
        }

        return page;
    }

    /**
     * 欠款统计
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBalanceStaticList.do")
    public Object getFaiginList(BalanceStatistics balanceStatistics){
        EtopPage<BalanceStatistics> page = null;
        if(balanceStatistics.getParkId().equals("0")){
            balanceStatistics.setParkIds(parkService.getAllParkId());
            page = balanceStatisticsService.search(balanceStatistics, "all");
        }else {
            page = balanceStatisticsService.search(balanceStatistics, "");
        }
        return page;
    }

    /**
     * 费金回收
     *
     * @param contractReport
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFaiginList.do")
    public Object getFaiginList(ContractReport contractReport){
        EtopPage<ContractReport> page = null;
        if(contractReport.getParkId().equals("0")){
            contractReport.setParkIds(parkService.getAllParkId());
            page = faiginService.search(contractReport, "all");
        }else {
            page = faiginService.search(contractReport, "");
        }
        return page;
    }

    /**
     * 收支报表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProfitLossList.do")
    public Object getProfitLossList(ProfitLoss profitLoss){
	   	 EtopPage<ProfitLoss> page = null;
	     if(profitLoss.getParkId().equals("0")){
	    	 profitLoss.setParkId(null);
	     	  profitLoss.setParkIds(parkService.getAllParkId()) ;
	          page = etopProfitLossService.search(profitLoss);
	     }else{
	    	  page = etopProfitLossService.search(profitLoss);
	     }
	//    EtopPage<ProfitLoss> page = etopProfitLossService.newSearch(profitLoss);
	
	    return page;
    }
    @ResponseBody
    @RequestMapping("/getProfitLossList2.do")
	public Object profitLossRemind(ProfitLoss profitLoss){

    	 EtopPage<ProfitLoss> page = etopProfitLossService.realTime(profitLoss);
    	 return page;
	}
   /* @ResponseBody
    @RequestMapping("/testList.do")
    public void testList(ProfitLoss profitLoss) throws ParseException {
        etopProfitLossService.add(profitLoss);
    }*/

    /**
     * 新增
     *
     * @param profitLoss
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addProfitLoss.do")
    public ResultType addProfitLoss(ProfitLoss profitLoss) {

        ResultType result = null;

        try {

            result = etopProfitLossService.addFine(profitLoss);

            //result = ResultType.getSuccess("新增成功! ");

        } catch (Exception e) {

            result = ResultType.getFail("新增失败! ");

            LOGGER.error("新增失败! ");

            e.printStackTrace();
        }

        return result;

    }
    /**
     * 删除原有数据并新增
     *
     * @param profitLoss
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addProfitLoss2.do")
    public ResultType addProfitLoss2(ProfitLoss profitLoss) {
    	
    	ResultType result = null;
    	
    	try {
    		
    		result = etopProfitLossService.addFine2(profitLoss);
    		
    		//result = ResultType.getSuccess("新增成功! ");
    		
    	} catch (Exception e) {
    		
    		result = ResultType.getFail("覆盖失败! ");
    		
    		LOGGER.error("覆盖失败! ");
    		
    		e.printStackTrace();
    	}
    	
    	return result;
    	
    }
    @ResponseBody
    @RequestMapping(value = "/check.do")
    public ResultType check(ProfitLoss profitLoss) {
    	
    	ResultType result = null;
    	
    	ProfitLoss loss = etopProfitLossDao.searchProfitLoss(profitLoss);
        if(loss != null){
        	result = ResultType.getFail("该分期已有此数据，是否覆盖？");
        }else {
            result = ResultType.getSuccess("该分期不存在该类型数据，是否新增？");
        }
        return result;
    	
    }

    /**
     * 服务统计
     *
     * @param serviceReport
     * @return
     */
    @ResponseBody
    @RequestMapping("/getServiceList.do")
    public Object getServiceList(ServiceReport serviceReport){
        EtopPage<ServiceReport> page = null;
        if(serviceReport.getParkId().equals("0")){
            serviceReport.setParkIds(parkService.getAllParkId());
            page = serviceService.search(serviceReport, "all");
        }else {
            page = serviceService.search(serviceReport, "");
        }
        return page;
    }

}
