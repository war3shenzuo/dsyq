package com.etop.management.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopGoodsDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopGoods;
import com.etop.management.service.EtopGoodsService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
/**
 * 
 * <br>
 * <b>功能：</b>EtopGoodsController<br>
 *   <br>
 */ 
@Controller
@RequestMapping(value="/goods")
public class EtopGoodsController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopGoodsController.class);
	@Resource 
	private EtopGoodsService etopGoodsService; 

	@Resource
	private ParkDao parkDao;
	
	@Resource
	private EtopGoodsDao etopGoodDao;
	
	@RequestMapping("/index.do")
	public String  index(Model model){
		//model.addAttribute("readonly",isReadOnly("/goods/index.do"));
		EtopUser  user=UserInfoUtil.getUserInfo();
		String parkGroupId="";
		String page="";
		if("4".equals(user.getUserType())){//园区组管理员
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			if(list.size()>0){
				model.addAttribute("defaultParkId", list.get(0).getId());
			}
			model.addAttribute("parks", list);
			page="etopGoods/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="etopGoods/index";
		}
		return page;
	}

	@RequestMapping("/addPage.do")
	public String  addPage(){
		return "etopGoods/addEtopGoods";
	}
	
	@ResponseBody
	@RequestMapping(value="/getEtopGoodsList.do")
	public EtopPage<EtopGoods> getEtopGoodsList(EtopGoods EtopGoods,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit){
		
		EtopPage<EtopGoods> page = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){
				EtopGoods.setParkId(user.getParkId());
			}else{
				if(EtopGoods.getParkId()==null|| EtopGoods.getParkId().length()==0){
					EtopGoods.setParkId("111111");
				}
			}
			page = etopGoodsService.getEtopGoodsList(EtopGoods,offset,limit);
		} catch (Exception e) {
			LOGGER.error("查询商品报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getEtopGoodsListGroup.do")
	public EtopPage<EtopGoods> getEtopGoodsListGroup(EtopGoods EtopGoods,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit){
		
		EtopPage<EtopGoods> page = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){
				EtopGoods.setParkId(user.getParkId());
			}else{
				if(EtopGoods.getParkId()==null|| EtopGoods.getParkId().length()==0){
					EtopGoods.setParkId("111111");
				}
			}
			page = etopGoodsService.getEtopGoodsListGroup(EtopGoods,offset,limit);
		} catch (Exception e) {
			LOGGER.error("查询商品报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	

	@ResponseBody
	@RequestMapping(value="/getEtopGoodsList2.do")
	public ResultType  getEtopGoodsList2(EtopGoods EtopGoods){
		
		EtopPage<EtopGoods> page = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			EtopGoods.setParkId(user.getParkId());
			return ResultType.getSuccess(etopGoodDao.queryByList(EtopGoods.getCriteria()));
			
		} catch (Exception e) {
			LOGGER.error("查询商品报错");
			e.printStackTrace();
			return ResultType.getError("查询失败");
		}
		
	}
	
	
	@RequestMapping(value="/getEtopGoodsInfo.do")
	public String getEtopGoodsInfo(String EtopGoodsId,Model model,String readonly){
		try{
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("4".equals(user.getUserType())){//园区组管理员
				model.addAttribute("read", 1);
			}else{
				model.addAttribute("read", 0);
			}
			model.addAttribute("readonly", readonly);
			model.addAttribute("goods",etopGoodsService.getEtopGoodsInfo(EtopGoodsId));
		} catch (Exception e) {
			
			LOGGER.error("查询商品详细信息");
			
			e.printStackTrace();
			
		}
		
		return "etopGoods/EtopGoodsInfo"; 
		
		
	}
	@ResponseBody
	@RequestMapping(value="/getEtopGoodsInfo2.do")
	public ResultType getEtopGoodsInfo2(String EtopGoodsId,Model model,String readonly){
		try{
			
			return ResultType.getSuccess(etopGoodsService.getEtopGoodsInfo(EtopGoodsId));
		} catch (Exception e) {
			
			LOGGER.error("查询商品详细信息");
			
			e.printStackTrace();
			return ResultType.getError("查询失败");
			
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addEtopGoods.do")
	public ResultType addEtopGoods(EtopGoods param){
		
		ResultType result = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			param.setParkId(user.getParkId());
			etopGoodsService.addEtopGoods(param);
			
			result = ResultType.getSuccess("添加商品成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("添加商品失败");
			
			LOGGER.error("添加商品失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateEtopGoods.do")
	public ResultType updateEtopGoods(EtopGoods EtopGoods) {

		ResultType result = null;

		try {

			etopGoodsService.updateEtopGoods(EtopGoods);

			result = ResultType.getSuccess("更新商品信息");

		} catch (Exception e) {

			LOGGER.error("更新商品失败");

			result = ResultType.getFail("更新商品失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/activeOrClose.do")
	public ResultType activeOrClose(EtopGoods EtopGoods) {

		ResultType result = null;

		try {

			etopGoodsService.activeOrClose(EtopGoods);

			result = ResultType.getSuccess("激活/关闭商品");

		} catch (Exception e) {

			LOGGER.error("激活/关闭商品失败");

			result = ResultType.getFail("激活/关闭商品失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/categoryList.do")
	public List<EtopGoods> categoryList(String parkId) {
		return etopGoodsService.categoryList(parkId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCategory.do")
	public List<EtopGoods> getCategory(EtopGoods etopGoods) throws Exception{
		
//		Map<String, String> info =  serviceQuotationService.getQuotation(quotationName);
//		model.addAttribute("info",info);
		etopGoods.setParkId(UserInfoUtil.getUserInfo().getParkId());
		return etopGoodsService.getCategory(etopGoods);
		
	}
	
	@ResponseBody
	@RequestMapping("/proveGoodName.do")
	public ResultType proveGoodName(EtopGoods etopGoods,String goodName){
		ResultType result= null;
		etopGoods.setGoodName(goodName);
		etopGoods.setParkId(UserInfoUtil.getUserInfo().getParkId());
		if("1".equals(etopGoodsService.proveGoodName(etopGoods))){
			result =ResultType.getSuccess("验证成功！");
		}
		else{
			result = ResultType.getFail("验证失败！");
		}
		return result;
		
	}
}
