package com.etop.management.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.etop.management.bean.ResultType;
import com.etop.management.util.DateUtil;

@Controller
@RequestMapping("/file")
public class FileController extends BaseAppController {
	
	@Value("#{configProperties['savePath']}")
	private String savePath;

	@RequestMapping("/upload.do")
	@ResponseBody
	public ResultType addUser(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) {
		List<Object> res = new ArrayList<>();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String myFileName = file.getOriginalFilename();
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String filePath = String.format("/%s/%s/%s/", getParkCode(),
								DateUtil.getFormatDate(), UUID.randomUUID().toString());
						File forder = new File(savePath + filePath);
						if(!forder.exists() || !forder.isDirectory())
							forder.mkdirs();
						filePath = filePath + myFileName;
						File localFile = new File(savePath + filePath);
						file.transferTo(localFile);
						Map<String, Object> fm = new HashMap<>();
						fm.put("original", myFileName);
						fm.put("path", filePath);
						res.add(fm);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ResultType.getFail();
				}
			}
		}
		return ResultType.getSuccess(res);
	}
}
