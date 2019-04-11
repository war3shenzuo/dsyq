package com.etop.management.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ResultType;
import com.etop.management.properties.ImgProperties;
import com.etop.management.util.UploadUtil;
@Controller
@RequestMapping("/common")
public class CommonController {
	//上传咨询图片
    @ResponseBody
	@RequestMapping("/uploadNewsImg.do")
	public ResultType uploadNewsImg(HttpServletRequest request){
    	
    	return UploadUtil.upLoad(request, "", "");
    	
	}
    
   //上传咨询图片
    @ResponseBody
	@RequestMapping("/uploadNewsImg2.do")
	public void uploadNewsImg2(HttpServletRequest request,HttpServletResponse response){
    	
    	response.setCharacterEncoding("utf-8");
    	String callback = request.getParameter("CKEditorFuncNum"); 
    	try {
    		Map<String,Object> map=(Map<String,Object>)UploadUtil.upLoad(request, "", "").getData();
    		
    		String orginalFileUrl= ImgProperties.LOAD_PATH+map.get("path");
    		
    		String msg="<script type=\"text/javascript\">"+
    			       "window.parent.CKEDITOR.tools.callFunction(" + callback
    				   + ",'" +orginalFileUrl + "','')"+"</script>";
    		
    		response.getWriter().println(msg);
    	} catch ( IOException e) {
    		e.printStackTrace();
    	}
    	
    	
	}
    
    
    
    
    @RequestMapping("/test.do")
	public String uploadImg(){
    	
    	return "test";
    	
	}
}
