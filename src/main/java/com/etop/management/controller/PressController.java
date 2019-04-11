package com.etop.management.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.News;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.PressService;

/**
 * Created by Alan.
 * 新闻信息
 *
 * @author 何利庭
 * @DATE 2016/9/19
 */

@Controller
@RequestMapping(value="/press")
public class PressController extends BaseAppController {

	private final static Logger LOGGER = Logger.getLogger(PressController.class);

	@Autowired
	private PressService pressService;

	@RequestMapping("/pressList.do")
	public Object pressList(Model model) {
		model.addAttribute("readonly", isReadOnly("/press/pressList.do"));
		return "press/pressList";
	}

	@RequestMapping("/addPage.do")
	public String addPage() {
		return "press/addPress";
	}

	/**
	 * 新闻列表
	 *
	 * @param news
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPressList.do")
	public EtopPage<News> getPressList(News news,
		   @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
		   @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit) {
		EtopPage<News> page = pressService.getPressList(news, offset, limit);
		return page;

	}

	/**
	 * 新增新闻
	 *
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPress.do")
	public ResultType addPress(News news) {

		ResultType result = null;

		try {

			pressService.add(news);

			result = ResultType.getSuccess("添加新闻成功");

		} catch (Exception e) {

			result = ResultType.getFail("添加新闻失败");

			LOGGER.error("添加新闻失败");

			e.printStackTrace();
		}

		return result;

	}


	@RequestMapping(value = "/getPressInfo.do")
	public String getPressInfo(News news, Model model, String readonly) {
		try {
			model.addAttribute("readonly", readonly);
			model.addAttribute("news", pressService.getPressInfoById(news));
		} catch (Exception e) {

			LOGGER.error("查询新闻详细信息失败");

			e.printStackTrace();

		}

		return "press/pressInfo";

	}

	@ResponseBody
	@RequestMapping(value = "/updatePress.do")
	public ResultType updatePress(News news) {

		ResultType result = null;

		try {

			pressService.updatePressById(news);

			result = ResultType.getSuccess("更新新闻信息成功! ");

		} catch (Exception e) {

			LOGGER.error("更新新闻信息失败! ");

			result = ResultType.getFail("更新新闻信息失败! ");

			e.printStackTrace();
		}

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/activeOrCloseNews.do")
	public ResultType activeOrClosePark(News news) {

		ResultType result = null;

		try {

			pressService.activeOrCloseNews(news);

			result = ResultType.getSuccess("激活/关闭新闻成功! ");

		} catch (Exception e) {

			LOGGER.error("激活/关闭新闻失败! ");

			result = ResultType.getFail("激活/关闭新闻失败! ");

			e.printStackTrace();
		}

		return result;

	}

}