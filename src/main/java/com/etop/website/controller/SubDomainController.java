package com.etop.website.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.controller.BaseAppController;
import com.etop.website.bean.EtopSubDomain;
import com.etop.website.service.EtopSubDomainService;

@Controller
@RequestMapping("/subdomain")
public class SubDomainController extends BaseAppController {
	
	@Inject
	private EtopSubDomainService etopSubDomainService;

	@RequestMapping("/forward.do")
	public String forward(String hostName, ModelMap model) {
		EtopSubDomain subdomain = etopSubDomainService.findById(hostName);
		if(subdomain == null)
			return null;
		String parkGroupId = subdomain.getParkGroupId();
		return "forward:/webinpark/inparkInfo.do?parkGroupId=" + parkGroupId;
	}
	
	@RequestMapping("/editview.do")
	public String editView() {
		return "subdomain/edit";
	}
	
	@RequestMapping("/addview.do")
	public String addView() {
		return "subdomain/add";
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public EtopPage<EtopSubDomain> list(@RequestParam(defaultValue="0")int offset,
			@RequestParam(defaultValue="10")int limit) {
		return etopSubDomainService.list(offset, limit);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public ResultType add(EtopSubDomain etopSubDomain) {
		if(etopSubDomainService.add(etopSubDomain) > 0) {
			return ResultType.getSuccess();
		}
		else {
			return ResultType.getFail("添加失败");
		}
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultType delete(String[] hostNames) {
		etopSubDomainService.deletes(hostNames);
		return ResultType.getSuccess();
	}
}
