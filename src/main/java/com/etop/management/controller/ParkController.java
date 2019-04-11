package com.etop.management.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.model.TreeModel;
import com.etop.management.service.ParkGroupService;
import com.etop.management.service.ParkService;
import com.etop.management.util.TreeDataHandle;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;

@Controller
@RequestMapping(value = "/park")
public class ParkController extends BaseAppController {

	private final static Logger LOGGER = Logger.getLogger(ParkController.class);

	@Resource
	ParkGroupService parkGroupService;

	@Autowired
	ParkService parkService;

	@RequestMapping("/index.do")

	public String index(Model model) {
		
		String parkGrouId = UserInfoUtil.getUserParkInfo().getParkGroupId();
		
		try {
			
			String parkCount = parkGroupService.getParkGroupInfo(parkGrouId).getParkCount();
			
			int parkList = mangerParkService.getAllParkId().size();
			
			if(parkList>=Integer.valueOf(parkCount)){
				
				model.addAttribute("isAdd", "0");
				
			}else{
				
				model.addAttribute("isAdd", "1");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "park/index";
	}

	@RequestMapping("/addPage.do")
	public String addPage(Model model) {
		model.addAttribute("parkGroupId", UserInfoUtil.getUserParkInfo().getParkGroupId());
		model.addAttribute("parkId", UUID.randomUUID().toString());
		return "park/addPark";
	}

	@RequestMapping("/parkInfo.do")
	public String parkInfo(Model model) {
		return "park/parkInfo";
	}
	
	@RequestMapping("/openUploadPage.do")
	public String openUploadPage(Model model) {
		return "park/parkImgUpload";
	}

	@ResponseBody
	@RequestMapping(value = "/getParkList.do")
	public EtopPage<Park> getParkList(Park Park) {

		EtopPage<Park> page = null;

		try {

			page = mangerParkService.getParkList(Park);

		} catch (Exception e) {

			LOGGER.error("查询园区列表失败");

			e.printStackTrace();
		}

		return page;

	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/getParkListForControl.do")
	public List<Map> getParkListForControl() {

		List<Map> list = null;

		try {

			list = mangerParkService.getParkListForControl();

		} catch (Exception e) {

			LOGGER.error("查询园区列表失败");

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = "/getParkInfo.do")
	public String getParkInfo(String parkId, Model model) {

		try {

			Park Park = mangerParkService.getParkInfo(parkId);
			model.addAttribute("parkGroups", parkGroupService.getParkGroupListForControl());
			model.addAttribute("park", Park);

		} catch (Exception e) {

			LOGGER.error("查询园区详细信息失败！");

			model.addAttribute("park", new Park());

			e.printStackTrace();
		}

		return "park/parkInfo";

	}

	@ResponseBody
	@RequestMapping(value = "/addPark.do")
	public ResultType addPark(Park Park) {

		ResultType result = null;

		try {
			
			List<Park> list = mangerParkService.getParkInfoByCode(Park.getParkCode());
			
			if(list!=null && list.size()>0){
				
				return ResultType.getFail("园区编号已存在");
			}
			
			mangerParkService.addPark(Park);

			result = ResultType.getSuccess("添加园区");
			

		} catch (Exception e) {

			LOGGER.error("添加园区失败！");

			result = ResultType.getFail("添加园区失败！");

			e.printStackTrace();
		}

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/updatePark.do")
	public ResultType updatePark(Park Park) {

		ResultType result = null;

		try {
			
			List<Park> parkList = mangerParkService.getParkInfoByCode(Park.getParkCode());
			
			for(Park p : parkList){
				if(!Park.getId().equals(p.getId())){
					return ResultType.getSuccess("园区编号已存在");
				}
			}
				
			mangerParkService.updatePark(Park);

			result = ResultType.getSuccess("更新园区详细信息");

		} catch (Exception e) {

			LOGGER.error("更新园区详细信息失败");

			result = ResultType.getFail("更新园区详细信息失败！");

			e.printStackTrace();
		}

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/activeOrClosePark.do")
	public ResultType activeOrClosePark(Park Park) {

		ResultType result = null;

		try {

			mangerParkService.activeOrClosePark(Park);

			result = ResultType.getSuccess("激活/关闭园区");

		} catch (Exception e) {

			LOGGER.error("激活/关闭园区失败");

			result = ResultType.getFail("激活/关闭园区失败！");

			e.printStackTrace();
		}

		return result;

	}

	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request, String parkId) {
		try {
			return UploadUtil.upLoads(request, "", parkId);
		} catch (Exception e) {

			LOGGER.error("查询园区名称失败");

			e.printStackTrace();
			
			return ResultType.getFail("激活/关闭园区失败！");
		}

	}

	@ResponseBody
	@RequestMapping(value="/getParkName.do")
	public List<TreeModel> getParkName(){

		List<TreeModel> tree = null;

		try {

			tree = TreeDataHandle.HandleParkData(parkService.getParkName(getParkIds()));

		} catch (Exception e) {

			LOGGER.error("查询园区名称失败");

			e.printStackTrace();
		}

		return tree;

	}

}
