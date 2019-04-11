package com.etop.management.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyInformation;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.model.CompanyModel;
import com.etop.management.service.EtopAdditonalCompanyService;
import com.etop.management.service.EtopCompanyBusinessService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.ParkService;
import com.etop.management.service.UserService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
@Controller
@RequestMapping("/etopCompany")
public class EtopCompanyController extends BaseAppController{

	private final static Logger LOGGER = Logger.getLogger(EtopCompanyController.class);

	@Autowired
	private EtopCompanyService etopCompanyService;

	@Autowired
	private ParkService parkService;

	@Autowired
    private EtopAdditonalCompanyService etopAdditonalCompanyService;

	@Autowired
	private EtopCompanyBusinessService etopCompanyBusinessService;

	@Autowired
	private UserService userService;

	@Resource 
	private ContractDao contractDao; 
	
	@Resource 
	private EtopCompanyDao etopCompanyDao; 
	
	@RequestMapping("/etopCompanyList.do")
	public Object etopCompanyList(Model model) {
		if(("4").equals(UserInfoUtil.getUserInfo().getUserType())){
			model.addAttribute("userType", 4);
			model.addAttribute("parks", parkService.getParkName(getParkIds()));
		}
		model.addAttribute("readonly",isReadOnly("/etopCompany/etopCompanyList.do"));
		return "etopCompany/etopCompanyList";
	}

	@RequestMapping("/addPage.do")
	public Object addPage(Model model) {
		model.addAttribute("companyId", UUID.randomUUID().toString());
		return "etopCompany/addEtopCompanyInfo";
	}

	@ResponseBody
	@RequestMapping("/getEtopCompanyList.do")
	public Object getEtopCompanyList(EtopCompany etopCompany,
			@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
			@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit, String genre) {
		if(etopCompany.getParkId() == null){
			etopCompany.setParkId(UserInfoUtil.getUserInfo().getParkId());
		}
		etopCompany.setCompanyStatus2("!=5");// 赋值5并没有意义，只是为了包含退园客户
		EtopPage<EtopCompany> etopPage = etopCompanyService.search(etopCompany, offset, limit, genre);
		return etopPage;
	}
	
	@ResponseBody
	@RequestMapping("/getEtopCompanyList2.do")
	public Object getEtopCompanyList2(EtopCompany etopCompany,
			@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
			@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit, String genre) {
		if(etopCompany.getParkId() == null){
			etopCompany.setParkId(UserInfoUtil.getUserInfo().getParkId());
		}
		etopCompany.setCompanyStatus2("!=5");// 赋值5并没有意义，只是为了包含退园客户
		EtopPage<EtopCompany> etopPage = etopCompanyService.search2(etopCompany, offset, limit, genre);
		return etopPage;
	}

	/**
	 * 获取当前园区下所有公司列表
	 *
	 * @param etopCompany
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCompanyByParkId.do")
	public Object getCompanyByParkId(EtopCompany etopCompany,
									 @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
									 @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit) {
		EtopPage<EtopCompany> etopPage = etopCompanyService.getCompanyByParkId(etopCompany, offset, limit);
		return etopPage;
	}

	/**
	 * 需要从来访与正式客户中查找
	 * @param parkId
	 * @param searchValue
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/searchCompanyInPark.do")
	public ResultType searchCompanyInPark(String parkId,String searchValue)
	{
		ResultType result = null;

		try {

			List<CompanyModel> list=this.etopCompanyService.searchCompany(parkId, searchValue);

			result = ResultType.getSuccess(list);

		} catch (Exception e) {

			result = ResultType.getFail("失败");

			LOGGER.error("失败");

			e.printStackTrace();
		}

		return result;
	}
	
	
	/**
	 * 添加公司基本信息
	 * 
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEtopCompanyInfo.do")
	public Object add(EtopCompany etopCompany) {

		ResultType result = null;

		try {

			result = etopCompanyService.add(etopCompany);

			//result = ResultType.getSuccess("添加公司信息成功", etopCompany.getCompanyId());

		} catch (Exception e) {

			result = ResultType.getFail("添加公司信息失败");

			LOGGER.error("添加公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 添加公司附加信息
	 * 
	 * @param etopCompanyInformation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addAdditionalEtopCompanyInfo.do")
	public Object add(EtopCompanyInformation etopCompanyInformation) {

		ResultType result = null;

		try {

			EtopCompany etopCompany = etopCompanyService.getCompInfoById(etopCompanyInformation.getCompanyId());
			if(etopCompany == null){
				result = new ResultType(9999,"请先保存公司基本信息","");
				return result;
			}
            etopAdditonalCompanyService.addInfo(etopCompanyInformation, etopCompanyInformation.getEtopCompanyBusinesses());

			result = ResultType.getSuccess("添加公司信息成功");

		} catch (Exception e) {

			result = ResultType.getFail("添加公司信息失败");

			LOGGER.error("添加公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 删除公司信息
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEtopCompanyInfo.do")
	public Object delete(EtopCompany etopCompany) {

		ResultType result = null;

		try {

			Integer size = etopCompany.getCompanyIds().size();
			etopCompanyService.deleteById(etopCompany,etopCompany.getCompanyIds());

			result = ResultType.getSuccess("删除公司信息成功",size);

		} catch (Exception e) {

			result = ResultType.getFail("删除公司信息失败");

			LOGGER.error("删除公司信息失败");

			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = "getCompInfoById.do")
    public Object getCompInfoById(String companyId, Model model, String readonly){

        try{
			model.addAttribute("readonly",readonly);
            model.addAttribute("compInfo",etopCompanyService.getCompInfoById(companyId));
            model.addAttribute("additionCompInfo",etopAdditonalCompanyService.getAdditionCompInfo(companyId));
			model.addAttribute("business", etopCompanyBusinessService.getCompBusiness(companyId));
			model.addAttribute("permissions", userService.getUserPer());
			model.addAttribute("companyId",companyId);
			model.addAttribute("companyType",1);//区分意向公司、正式公司
        } catch (Exception e) {

            LOGGER.error("查询公司详细信息失败");

            e.printStackTrace();

        }
        return "etopCompany/updateEtopCompanyInfo";
    }

	/**
	 * 修改公司基本信息
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateEtopCompanyInfo.do")
	public Object update(EtopCompany etopCompany, String genre){

		ResultType result = null;

		try {

			result = etopCompanyService.updateById(etopCompany, genre);;

			//result = ResultType.getSuccess("修改公司信息成功");

		} catch (Exception e) {

			result = ResultType.getFail("修改公司信息失败");

			LOGGER.error("修改公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 修改公司附加信息
	 * @param etopCompanyInformation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateAdditionalEtopCompInfo.do")
	public Object updateAdditional(EtopCompanyInformation etopCompanyInformation){

		ResultType result = null;

		try {

			etopAdditonalCompanyService.updateAdditionalById(etopCompanyInformation, etopCompanyInformation.getEtopCompanyBusinesses());

			result = ResultType.getSuccess("修改公司信息成功");

		} catch (Exception e) {

			result = ResultType.getFail("修改公司信息失败");

			LOGGER.error("修改公司信息失败");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 退园
	 *
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/backPark.do")
	private Object backPark(EtopCompany etopCompany){
		ResultType resultType = null;
		try {

			etopCompanyService.updateStatus(etopCompany);

			resultType = ResultType.getSuccess("退园成功");

		} catch (Exception e) {

			resultType = ResultType.getFail("退园失败");

			LOGGER.error("退园失败");

			e.printStackTrace();
		}

		return resultType;

	}

	/**
	 * 退园公司入驻
	 *
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/enter.do")
	private Object enter(EtopCompany etopCompany, String genre){
		ResultType resultType = null;
		try {

			etopCompanyService.updateById(etopCompany, genre);


			resultType = ResultType.getSuccess("入驻成功");

		} catch (Exception e) {

			resultType = ResultType.getFail("入驻失败");

			LOGGER.error("入驻失败");

			e.printStackTrace();
		}

		return resultType;

	}

	/**
	 * 生成账号
	 *
	 * @param etopCompany
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/generatedAccount.do")
	public Object generatedAccount(EtopCompany etopCompany){

		ResultType resultType = null;

		resultType = etopCompanyService.generatedAccount(etopCompany.getCompanyId());

		return resultType;
	}


	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request,String companyId, String parkId) {
		EtopCompany etopCompany = etopCompanyService.getCompInfoById(companyId);
		ResultType result = null;
		if(etopCompany == null){
			result = new ResultType(9999,"请先保存公司基本信息","");
			return result;
		}
		return UploadUtil.upLoad(request, companyId, "");

	}

	@ResponseBody
	@RequestMapping("/OutStatusOfCompany.do")
	public void OutStatusOfCompany(){

		System.out.println("OutStatusOfCompany job start...");

		List<Contract> contract = contractDao.getNormalCompany(DateUtil.formatDate(new Date()));
		
		List<Contract> contractTwo = contractDao.getNormalCompanyTwo(DateUtil.formatDate(new Date()));
		
		List<String> list = new ArrayList<String>();
		for(Contract c :contract){
			list.add(c.getRefCompanyId());
		}
		
		for(Contract c2 :contractTwo){
			list.add(c2.getRefCompanyId());
		}
		
/*		for(int i = 0; i < list.size(); i++){
			Contract  map =contractDao.chaeckNormalCompany(list.get(i));
			if(map != null){
				String mapCompanyId = map.getRefCompanyId();
				if(list.contains(mapCompanyId)){
					list.remove(mapCompanyId);
				}
			}
		}*/
		List<EtopCompany> etopCompany = etopCompanyDao.selectCompanyList();

			  for(EtopCompany etopCompanyList : etopCompany){

					if(! list.contains(etopCompanyList.getCompanyId())){

						etopCompanyDao.updateBycompanyId(etopCompanyList.getCompanyId());
					}
			  	}
	}
}
