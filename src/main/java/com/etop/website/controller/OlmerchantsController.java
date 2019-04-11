package com.etop.website.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.website.bean.Floor;
import com.etop.website.bean.Webpark;
import com.etop.website.service.FloorService;
import com.etop.website.service.FloorStoreyService;
import com.etop.website.service.OlmerchantsService;



@Controller
@RequestMapping("/webolmerchants")
public class OlmerchantsController   {
	Logger logger = LoggerFactory.getLogger(InparkController.class);
	
	@Resource
	OlmerchantsService  olmerchantsService ;
	
	@Resource
	FloorService floorservice;
	
	@Resource
	FloorStoreyService  floorStoreyService;
	
	//招商首页
	@RequestMapping(value = "/index.do")
	public String olmerchants(Model model){
	
		return "olmerchants_site/index";
		
	}
	
	//选房首页
	@RequestMapping(value = "/roomList.do")
	public String theroomList(Model model, String id, String refStoreyId){
		
		Webpark webpark = olmerchantsService.searchInfo(id);
		model.addAttribute("webpark", webpark);

		return "olmerchants_site/theroom";
		
	}
	
	
	//选房详情页
	@RequestMapping(value = "/roomInfo.do")
	public String theroom(Model model, String roomid){
	
		Floor floor = floorservice.searchImg(roomid);
		model.addAttribute("roomImg",floor.getRoomImg());
		return "olmerchants_site/roominfo";
		
	}
	@ResponseBody
	@RequestMapping(value = "/searchImg.do")
	public Floor searchImg(Model model, String roomid){
	
		return floorservice.searchImg(roomid);
	
		 
		
	}
	//获取园区列表
	@ResponseBody
	@RequestMapping("/getWebpark.do")
	public List<Webpark> searchwebpark(Webpark webpark) {
		try{
			return olmerchantsService.searchWebpark(webpark);
		}catch (Exception e) {
			logger.error("显示异常", e);
			e.printStackTrace();
		}		
		return null;
	}
	

	//城市筛选
	@ResponseBody
	@RequestMapping(value = "/getCity.do")
	public List<Webpark> getCity(Model model, String city,Webpark webpark) throws Exception {

		
		    return olmerchantsService.getCity(webpark);
		
	}
	
	//获取选房列表
		@ResponseBody
		@RequestMapping("/getFloor.do")
		public List<Floor> searchfloor(Floor floor) {
			try{
				return floorservice.searchFloor(floor);
			}catch (Exception e) {
				logger.error("显示异常", e);
				e.printStackTrace();
			}		
			return null;
		}
		
		//筛选楼号
		@ResponseBody
		@RequestMapping("/searchFloorName.do")
		public List<Floor> searchFloorName( String refFloorId) {
			
			return floorservice.searchFloorName(refFloorId);

		}
		//筛选区
		@ResponseBody
		@RequestMapping("/getRoom.do")
		public List<Floor> getAreaId(String refAreaId) {
			
			return floorservice.searchRoom( refAreaId);

		}
		
		//筛选得到楼号
		@ResponseBody
		@RequestMapping("/searchFloorId.do")
		public List<Floor> searchFloorId( String id) {
			
			return floorStoreyService.searchFloorId(id);

		}
		
		
		//筛选得到层号
			@ResponseBody
			@RequestMapping("/searchStoreyId.do")
			public List<Floor> searchStoreyId( String refFloorId) {
				
				return floorStoreyService.searchStoreyId(refFloorId);

			}
		
		
			//筛选得到区号
			@ResponseBody
			@RequestMapping("/searchAreaId.do")
			public List<Floor> searchAreaId( String refStoreyId) {
				List<Floor> Floor =floorStoreyService.searchAreaId(refStoreyId);
				return  Floor;

			}
		
		
}
