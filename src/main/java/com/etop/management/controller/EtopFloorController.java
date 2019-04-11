package com.etop.management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.model.TreeModel;
import com.etop.management.service.EtopFloorService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.TreeDataHandle;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopFloorController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/floor")
public class EtopFloorController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopFloorController.class);
	@Resource
	private EtopFloorService etopFloorService; 
	@Resource
	private ParkDao parkDao;
	
	@Resource
	private EtopFloorRoomDao etopFloorRoomDao; 
	@Resource
	private ContractDao contractDao; 
	
	
	//首页
	@RequestMapping("/index.do")
	public String  index(Model model){
		EtopUser  user=UserInfoUtil.getUserInfo();
		if("4".equals(user.getUserType())){//园区组管理员
			String parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			model.addAttribute("parks", list);
		}
		model.addAttribute("readonly",isReadOnly("/floor/index.do"));
		model.addAttribute("userType", user.getUserType());
		
		return "floor/index";
		
	}
	
	//添加 楼 页面
	@RequestMapping("/roomList.do")
	public String  roomList(Model model){
		EtopUser  user=UserInfoUtil.getUserInfo();
		String page="";
		if("4".equals(user.getUserType())){//园区组管理员
			Park park =new Park();
			String parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			if(list.size()>0){
				model.addAttribute("defaultParkId", list.get(0).getId());
			}
			model.addAttribute("parks", list);
			page="floor/roomList2";
		}else{
			page="floor/roomList";
		}
		model.addAttribute("readonly",isReadOnly("/floor/index.do"));
		model.addAttribute("userType", user.getUserType());
		
		return page;
	}
	
	//添加 楼 页面
	@RequestMapping("/addPage.do")
	public String  addPage(String buildType,String parentId ,Model model){
		
		if(buildType!=null){
			model.addAttribute("buildType", buildType);
		}
		
		if(parentId!=null){
			model.addAttribute("parentId", parentId);
		}
		
		return "floor/addFloor";
	}
	

	//添加 楼 页面
	@RequestMapping("/addRoomPage.do")
	public String  addRoomPage(String refAreaId,Model model){
		
		model.addAttribute("refAreaId", refAreaId);
		
		return "floor/addRoom";
	}
	//获取楼列表
	@ResponseBody
	@RequestMapping(value="/getFloorList.do")
	public List<TreeModel> getFloorList(EtopFloor floor ){ 
		
		if("#".equals(floor.getId())){
			floor.setId(null);
		}
		
		List<TreeModel> tree = null;
		
		try {
			
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("4".equals(user.getUserType())){//园区组管理员
				
				if(floor.getParkId()!=null && !"".equals(floor.getParkId())){
					tree = TreeDataHandle.HandleFloorData(etopFloorService.getFloorList(floor));
				}else{
					tree = TreeDataHandle.HandleFloorData(new ArrayList<EtopFloor>() );
				}

			}else{
				String parkId=UserInfoUtil.getUserInfo().getParkId();
				
				
				if(parkId==null|| "".equals(parkId)){
					throw new RuntimeException("查询出错");
				}
				floor.setParkId(parkId);
				tree = TreeDataHandle.HandleFloorData(etopFloorService.getFloorList(floor));
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("查询楼列表失败");
			
			e.printStackTrace();
		}
		
		return tree;
		
	}
	//获取楼信息
	@ResponseBody
	@RequestMapping(value="/getFloorInfo.do")
	public ResultType getFloorInfo(String floorId){
		
		ResultType result = null;
		
		try {
			
			EtopFloor floor = etopFloorService.getFloorInfo(floorId);
			
			result = ResultType.getSuccess("查询楼详细信息成功", floor);
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色详细信息失败！");
			
			result = ResultType.getFail("查询楼详细信息失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}

	//获取楼列表(报表管理)
	@ResponseBody
	@RequestMapping(value="/getReportFloorList.do")
	public List<TreeModel> getReportFloorList(EtopFloor floor){

		if("#".equals(floor.getId())){
			floor.setId(null);
		}

		List<TreeModel> tree = null;
		List<EtopFloorRoom> roomList = null;
		List<EtopFloor> floorList = null;
		try {

			floorList = etopFloorService.getFloorList(floor);
			roomList = etopFloorService.getRoomsList(floor);

			tree = TreeDataHandle.HandleReportFloorData(floorList, roomList);


		} catch (Exception e) {

			LOGGER.error("查询楼列表失败");

			e.printStackTrace();
		}

		return tree;

	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFloor.do")
	public ResultType deleteFloor(EtopFloor floor){
		
		
		ResultType result = null;
		
		try {
			
			etopFloorService.deleteFloor(floor);
			
			result = ResultType.getSuccess("删除成功");
			
		} catch (Exception e) {
			
			LOGGER.error("删除失败！");
			
			result = ResultType.getFail(e.getMessage());
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	

	@ResponseBody
	@RequestMapping(value="/addFloor.do")
	public ResultType addFloor(EtopFloor floor){
		
		String parkId=UserInfoUtil.getUserInfo().getParkId();
		
		if(parkId==null|| "".equals(parkId)){
			return ResultType.getFail("添加楼失败！！");
		}
		
		floor.setParkId(parkId);
		
		ResultType result = null;
		
		try {
			
			etopFloorService.addFloor(floor);
			
			result = ResultType.getSuccess("添加楼成功");
			
		} catch (Exception e) {
			
			LOGGER.error("添加楼失败！");
			
			result = ResultType.getFail("添加楼失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateFloor.do")
	public ResultType updateFloor(EtopFloor floor){
		
		String parkId=UserInfoUtil.getUserInfo().getParkId();
		
		if(parkId==null|| "".equals(parkId)){
			return ResultType.getFail("更新楼失败！！");
		}
		
		floor.setParkId(parkId);
		
		ResultType result = null;
		
		try {
			
			etopFloorService.updateFloor(floor);
			
			result = ResultType.getSuccess("修改楼成功");
			
		} catch (Exception e) {
			
			LOGGER.error("修改角色失败！");
			
			result = ResultType.getFail(e.getMessage());
			
			e.printStackTrace();
		} finally {
		}
		
		return result;
		
	}
	
	//--- 楼层区end
	
	//--- 房间begin
	//获取楼列表
	@ResponseBody
	@RequestMapping(value = "/getRoomList.do")
	public EtopPage<EtopFloorRoom> getRoomList(EtopFloorRoom room) {

		EtopPage<EtopFloorRoom> page = null;

		try {

			page = etopFloorService.getRoomList(room);

		} catch (Exception e) {

			LOGGER.error("查询房间列表失败");

			e.printStackTrace();
		}

		return page;

	}
	
	//--- 楼层区end
	
	//--- 房间begin
	//获取楼列表
	@ResponseBody
	@RequestMapping(value = "/getRoomList2.do")
	public EtopPage<EtopFloorRoom> getRoomList2(EtopFloorRoom room) {

		EtopUser  user=UserInfoUtil.getUserInfo();
		if("4".equals(user.getUserType())){//园区组管理员
		}else{
			room.setParkId(user.getParkId());
		}

		EtopPage<EtopFloorRoom> page = null;

		try {

			page = etopFloorService.getRoomList2(room);

		} catch (Exception e) {

			LOGGER.error("查询房间列表失败");

			e.printStackTrace();
		}

		return page;

	}

	//获取楼信息
	@RequestMapping(value="/getRoomInfo.do")
	public String getRoomInfo(String roomId,String userType,Model model){
		
		ResultType result = null;
		
		try {
			
			EtopFloorRoom room = etopFloorService.getRoomInfo(roomId);
			
			String roomImg=room.getRoomImg();
			String img[];
			if(roomImg!=null){
				img=roomImg.split(",");
				room.setRoomImg(img[0]);
				if(img.length>1){
					room.setRoomImg2(img[1]);
				}
			}
			
			model.addAttribute("userType", userType);
			model.addAttribute("room", room);
			model.addAttribute("readonly",isReadOnly("/floor/index.do"));
			
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色详细信息失败！");
			
			result = ResultType.getFail("查询房间详细信息失败！");
			
			e.printStackTrace();
		}
		
		return "floor/roomInfo";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoom.do")
	public ResultType getRoom(String roomId){
		
		ResultType result = null;
		
		try {
			
			EtopFloorRoom room = etopFloorService.getRoomInfo(roomId);
			
			result = ResultType.getSuccess(room);
			
		} catch (Exception e) {
			
			LOGGER.error("获取房间失败！");
			
			result = ResultType.getFail("获取房间失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getRooms.do")
	public ResultType getRooms(String refBuildingId,String areaId,String floorStatus,String activated){
		
		ResultType result = null;
		
		try {
			
			List<EtopFloorRoom> rooms = etopFloorService.getRooms(refBuildingId,areaId,floorStatus,activated);
			
			result = ResultType.getSuccess(rooms);
			
		} catch (Exception e) {
			
			LOGGER.error("获取房间失败！");
			
			result = ResultType.getFail("获取房间失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addRoom.do")
	public ResultType addRoom(EtopFloorRoom room){
		
		ResultType result = null;
		
		try {
			
			etopFloorService.addRoom(room);
			
			result = ResultType.getSuccess("添加房间成功");
			
		} catch (Exception e) {
			
			LOGGER.error("添加房间失败！");
			
			result = ResultType.getFail(e.getMessage());
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateRoom.do")
	public ResultType updateRoom(EtopFloorRoom room){
		
		ResultType result = null;
		
		try {
			
			etopFloorService.updateRoom(room);
			
			result = ResultType.getSuccess("修改房间成功");
			
		} catch (Exception e) {
			
			LOGGER.error("修改房间失败！");
			
			result = ResultType.getFail("修改房间失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request) {
		
		String parkId=UserInfoUtil.getUserInfo().getParkId();
		
		if(parkId==null|| "".equals(parkId)){
			return ResultType.getFail("上传失败！！");
		}
		
		return UploadUtil.upLoad(request, "", parkId);

	}
	
	/*@ResponseBody
	@RequestMapping("/test.do")
	public boolean test(){
		return etopFloorService.unbindRoom("cae272a7-ca8e-498d-937d-b12219f649b8");
	}*/
	
	@ResponseBody
	@RequestMapping(value = "/activeOrClosePark.do")
	public ResultType activeOrClosePark(EtopFloorRoom room) {

		ResultType result = null;

		try {

			etopFloorService.activeOrCloseRoom(room);

			result = ResultType.getSuccess("激活/关闭房间成功");

		} catch (Exception e) {

			LOGGER.error("激活/关闭房间失败");

			result = ResultType.getFail(e.getMessage());

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRoom.do")
	public ResultType deleteRoom(EtopFloorRoom room) {

		ResultType result = null;

		try {

			etopFloorService.deleteRoom(room);

			result = ResultType.getSuccess("删除成功");

		} catch (Exception e) {

			LOGGER.error("删除失败");

			result = ResultType.getFail(e.getMessage());

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping("/FloorStatus.do")
	public void FloorStatus(){

		System.out.println("FloorStatus job start...");

		String date =DateUtil.formatDate(new Date());
		
		List<Contract> contract = contractDao.getNormalCompany(date);
		
		List<Contract> contractTwo = contractDao.getNormalCompanyTwo(date);
		
		List<String> list = new ArrayList<String>();
		List<String> companyIdList = new ArrayList<String>();
		for(Contract c :contract){
			list.add(c.getRefRoomId());
			companyIdList.add(c.getRefCompanyId());
		}
		
		for(Contract c2 :contractTwo){
			list.add(c2.getRefRoomId());
			companyIdList.add(c2.getRefCompanyId());
		}
		
/*		for(int i = 0; i < companyIdList.size(); i++){
			Contract  map =contractDao.chaeckNormalCompany(companyIdList.get(i));
			if(map != null){
				String mapCompanyId = map.getRefCompanyId();
				if(list.contains(mapCompanyId)){
					list.add(map.getRefRoomId());
				}
			}
		}*/
		
		List<EtopFloorRoom> etopFloorRoom = etopFloorRoomDao.selectRoomsList();

		  for(EtopFloorRoom etopFloorRoomList : etopFloorRoom){

				if(list.contains(etopFloorRoomList.getId())){
					//0待租（无人）   2预留中
					if( "0".equals(etopFloorRoomList.getFloorStatus())  ||  "2".equals(etopFloorRoomList.getFloorStatus()) ){
						etopFloorRoomDao.updateForLet(etopFloorRoomList.getId());  //1已出租
					}
				}else{
					// 1已出租  3 待租（有人）
					if( "1".equals(etopFloorRoomList.getFloorStatus())  ||  "3".equals(etopFloorRoomList.getFloorStatus()) ){
						etopFloorRoomDao.updateToLet(etopFloorRoomList.getId()); // 0待租（无人）
					}
				}
		  	}
	}
	
	//TODO:预设
	@ResponseBody
	@RequestMapping(value = "/getRoomListByParent.do")
	public ResultType getRoomListByParent(EtopFloorRoom room) {

		ResultType result=ResultType.getFail();
		
		List<EtopFloorRoom> list=null;

		try {

			list = etopFloorService.getFloorRoomListByParent(room);
			
			if(list!=null && list.size()>0)
			{
				result=ResultType.getSuccess(list);
			}
			{
				result=ResultType.getNoData();
			}

		} catch (Exception e) {

			LOGGER.error("查询房间列表失败");

			e.printStackTrace();
		}

		return result;

	}
	//TODO:预设
	@ResponseBody
	@RequestMapping(value = "/getFloorListByParent.do")
	public ResultType getFloorListByParent(EtopFloor floor) {

		ResultType result=ResultType.getFail();
		
		List<EtopFloor> list=null;

		try {

			list=this.etopFloorService.getFloorListByParent(floor.getParkId(), floor.getParentId(), floor.getStatus());
			

			if(list!=null && list.size()>0)
			{
				result=ResultType.getSuccess(list);
			}else
			{
				result=ResultType.getNoData();
			}	
			
			
		} catch (Exception e) {

			LOGGER.error("查询列表失败");

			e.printStackTrace();
		}

		return result;

	}
 
}
