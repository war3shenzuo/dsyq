package com.etop.management.util;

import java.util.ArrayList;
import java.util.List;

import com.etop.management.bean.Func;
import com.etop.management.bean.Park;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.Role;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.model.FuncModel;
import com.etop.management.model.TreeModel;

/**
 * 
 * 树数据处理工具类
 * 
 * @author shixianjie 下午3:59:14
 */
public class TreeDataHandle {

	public static List<TreeModel> HandleFunData(List<Func> list) {

		List<TreeModel> funcs = new ArrayList<TreeModel>();

		for (Func func : list) {
			if (func.getParentId()==null || "".equals(func.getParentId())) {
				TreeModel model = new TreeModel();
				model.setId(func.getId());
				model.setText(func.getFuncName());
				if ("0".equals(func.getActivated())) {
					model.setIcon("../img/cancel-on.png");
				}else{
					model.setIcon("../img/tree1.png");
				}

				funcs.add(model);
			}
		}

		for (TreeModel f : funcs) {
			List<TreeModel> children = new ArrayList<TreeModel>();
			for (Func func1 : list) {
				if (func1.getParentId()!=null && !"".equals(func1.getParentId())) {
					if (f.getId().equals(func1.getParentId())) {
						TreeModel model = new TreeModel();
						model.setId(func1.getId());
						model.setText(func1.getFuncName());
						if ("0".equals(func1.getActivated())) {
							model.setIcon("../img/cancel-on.png");
						}else{
							model.setIcon("../img/tree.png");
						}
						children.add(model);
					}
				}
			}
			f.setChildren(children);
		}

		return funcs;

	}
	
	public static List<FuncModel> HandleIndexFunc(List<Func> list) {

		List<FuncModel> funcs = new ArrayList<FuncModel>();

		for (Func func : list) {
			if (func.getParentId()==null || "".equals(func.getParentId())) {
				FuncModel model = new FuncModel();
				model.setId(func.getId());
				model.setFuncName(func.getFuncName());
				model.setLoadUrl(func.getLoadUrl());
				model.setIcon(func.getIcon());

				funcs.add(model);
			}
		}

		for (FuncModel f : funcs) {
			List<FuncModel> children = new ArrayList<FuncModel>();
			for (Func func1 : list) {
				if (func1.getParentId()!=null && !"".equals(func1.getParentId())) {
					if (f.getId().equals(func1.getParentId())) {
						FuncModel model = new FuncModel();
						model.setFuncName(func1.getFuncName());
						model.setLoadUrl(func1.getLoadUrl());
						children.add(model);
					}
				}
			}
			f.setChildren(children);
		}

		return funcs;

	}

	public static List<TreeModel> HandleRoleData(List<Role> roleList) {

		List<TreeModel> list = new ArrayList<TreeModel>();

		for (Role roleInfo : roleList) {

			if (roleInfo.getParentId() == null || "".equals(roleInfo.getParentId())) {
				TreeModel roleModel = new TreeModel();
				roleModel.setId(roleInfo.getId());
				roleModel.setText(roleInfo.getRoleName());
				if ("0".equals(roleInfo.getActivated())) {
					roleModel.setIcon("../img/cancel-on.png");
				}else{
					roleModel.setIcon("../img/tree1.png");
				}

				list.add(roleModel);
			}

		}

		for (TreeModel role : list) {
			List<TreeModel> children = new ArrayList<TreeModel>();
			for (Role roleInfo : roleList) {
				if (roleInfo.getParentId() != null) {
					if (role.getId().equals(roleInfo.getParentId())) {
						TreeModel roleModel = new TreeModel();
						roleModel.setId(roleInfo.getId());
						if ("0".equals(roleInfo.getActivated())) {
							roleModel.setIcon("../img/cancel-on.png");
						}else{
							roleModel.setIcon("../img/tree.png");
						}
						roleModel.setText(roleInfo.getRoleName());

						children.add(roleModel);
					}
				}
			}

			role.setChildren(children);

		}

		return list;
	}

	public static List<TreeModel> HandleParkData(List<Park> parkList) {

		List<TreeModel> list = new ArrayList<TreeModel>();

		for (Park park : parkList) {

			TreeModel roleModel = new TreeModel();
			roleModel.setId(park.getId());
			roleModel.setText(park.getParkName());
			if ("0".equals(park.getActivated())) {
				roleModel.setIcon("../img/cancel-on.png");
			}else{
				roleModel.setIcon("../img/tree1.png");
			}

			list.add(roleModel);

		}

		return list;
	}


	public static List<TreeModel> HandleFloorData(List<EtopFloor> floorList) {

		List<TreeModel> list = new ArrayList<TreeModel>();

		for (EtopFloor floorInfo : floorList) {

			if (floorInfo.getParentId() == null || "".equals(floorInfo.getParentId())) {
				TreeModel floorModel = new TreeModel();
				floorModel.setId(floorInfo.getId());
				floorModel.setText(floorInfo.getBuildName());
				if ("0".equals(floorInfo.getStatus())) {
					floorModel.setIcon("../img/cancel-on.png");
				}else{
					if ("floor".equals(floorInfo.getBuildType())) {
						floorModel.setIcon("../img/lou0001.png");
					}else if("storey".equals(floorInfo.getBuildType())){
						floorModel.setIcon("../img/lou0000.png");
					}else{
						floorModel.setIcon("../img/lou0002.png");
					}
				}

				list.add(floorModel);
			}

		}

		for (TreeModel floor : list) {
			List<TreeModel> children = new ArrayList<TreeModel>();
			for (EtopFloor floorInfo : floorList) {
				if (floorInfo.getParentId() != null) {
					if (floor.getId().equals(floorInfo.getParentId())) {
						TreeModel floorModel = new TreeModel();
						floorModel.setId(floorInfo.getId());
						floorModel.setText(floorInfo.getBuildName());
						if ("0".equals(floorInfo.getStatus())) {
							floorModel.setIcon("../img/cancel-on.png");
						}else{
							if ("floor".equals(floorInfo.getBuildType())) {
								floorModel.setIcon("../img/lou0001.png");
							}else if("storey".equals(floorInfo.getBuildType())){
								floorModel.setIcon("../img/lou0000.png");
							}else{
								floorModel.setIcon("../img/lou0002.png");
							}
						}
						
						children.add(floorModel);
					}
				}
				
			}
			
			for (TreeModel floorChild : children) {
				List<TreeModel> cchildren = new ArrayList<TreeModel>();
				for (EtopFloor floorChildInfo : floorList) {
					if (floorChildInfo.getParentId() != null&& !floorChildInfo.getId().equals(floorChild.getId())) {
						if (floorChild.getId().equals(floorChildInfo.getParentId())) {
							TreeModel childModel = new TreeModel();
							childModel.setId(floorChildInfo.getId());
							childModel.setText(floorChildInfo.getBuildName());
							if ("0".equals(floorChildInfo.getStatus())) {
								childModel.setIcon("../img/cancel-on.png");
							}else{
								if ("floor".equals(floorChildInfo.getBuildType())) {
									childModel.setIcon("../img/lou0001.png");
								}else if("storey".equals(floorChildInfo.getBuildType())){
									childModel.setIcon("../img/lou0000.png");
								}else{
									childModel.setIcon("../img/lou0002.png");
								}
							}
							cchildren.add(childModel);
						}
					}
				}
				floorChild.setChildren(cchildren);
			}
			floor.setChildren(children);
		}

		return list;
	}

	public static List<TreeModel> HandleReportFloorData(List<EtopFloor> floorList, List<EtopFloorRoom> roomList) {

		List<TreeModel> list = new ArrayList<TreeModel>();

		for (EtopFloor floorInfo : floorList) {

			if (floorInfo.getParentId() == null || "".equals(floorInfo.getParentId())) {
				TreeModel floorModel = new TreeModel();
				floorModel.setId(floorInfo.getId());
				floorModel.setText(floorInfo.getBuildName());
				if ("floor".equals(floorInfo.getBuildType())) {
					floorModel.setIcon("../img/lou0001.png");
				}else if("storey".equals(floorInfo.getBuildType())){
					floorModel.setIcon("../img/lou0000.png");
				}else{
					floorModel.setIcon("../img/lou0002.png");
				}

				list.add(floorModel);
			}

		}

		for (TreeModel floor : list) {
			List<TreeModel> children = new ArrayList<TreeModel>();
			for (EtopFloor floorInfo : floorList) {
				if (floorInfo.getParentId() != null) {
					if (floor.getId().equals(floorInfo.getParentId())) {
						TreeModel floorModel = new TreeModel();
						floorModel.setId(floorInfo.getId());
						floorModel.setText(floorInfo.getBuildName());
						if ("floor".equals(floorInfo.getBuildType())) {
							floorModel.setIcon("../img/lou0001.png");
						}else if("storey".equals(floorInfo.getBuildType())){
							floorModel.setIcon("../img/lou0000.png");
						}else{
							floorModel.setIcon("../img/lou0002.png");
						}
						children.add(floorModel);
					}
				}

			}

			for (TreeModel floorChild : children) {
				List<TreeModel> cchildren = new ArrayList<TreeModel>();
				for (EtopFloor floorChildInfo : floorList) {
					if (floorChildInfo.getParentId() != null&& !floorChildInfo.getId().equals(floorChild.getId())) {
						if (floorChild.getId().equals(floorChildInfo.getParentId())) {
							TreeModel childModel = new TreeModel();
							childModel.setId(floorChildInfo.getId());
							childModel.setText(floorChildInfo.getBuildName());
							if ("floor".equals(floorChildInfo.getBuildType())) {
								childModel.setIcon("../img/lou0001.png");
							}else if("storey".equals(floorChildInfo.getBuildType())){
								childModel.setIcon("../img/lou0000.png");
							}else{
								childModel.setIcon("../img/lou0002.png");
							}
							cchildren.add(childModel);
						}
					}
				}

				for(TreeModel room : cchildren){
					List<TreeModel> treeModels = new ArrayList<>();
					for(EtopFloorRoom etopFloorRoom : roomList){
						if(etopFloorRoom.getRefAreaId().equals(room.getId())){
							TreeModel treeModel = new TreeModel();
							treeModel.setId(etopFloorRoom.getId());
							treeModel.setText(etopFloorRoom.getRoomNum().toString());
							treeModel.setIcon("../img/lou0005.png");
							treeModel.setType("room");
							treeModels.add(treeModel);
						}
					}
					room.setChildren(treeModels);
				}
				floorChild.setChildren(cchildren);
			}
			floor.setChildren(children);
		}
		return list;
	}


	public static List<TreeModel> HandleParkGData(List<ParkGroup> plist) {
		List<TreeModel> list = new ArrayList<TreeModel>();
		for (ParkGroup pg : plist) {
			TreeModel model = new TreeModel();
			model.setId(pg.getId());
			model.setText(pg.getParkGroupName());
			model.setIcon("../img/lou0002.png");
			list.add(model);
		}
		return list;
	}
	
	public static List<TreeModel> HandleParkData2(List<Park> plist) {
		List<TreeModel> list = new ArrayList<TreeModel>();
		for (Park p : plist) {
			TreeModel model = new TreeModel();
			model.setId(p.getId());
			model.setText(p.getParkName());
			model.setIcon("../img/lou0002.png");
			list.add(model);
		}
		return list;
	}

}
