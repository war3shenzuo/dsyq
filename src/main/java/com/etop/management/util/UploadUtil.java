package com.etop.management.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.etop.management.bean.ResultType;
import com.etop.management.properties.ImgProperties;



/**
 * 
 * 文件上传 具体步骤： 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 2） 利用 request 获取 真实路径
 * ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 3）对 DiskFileItemFactory 对象设置一些 属性
 * 4）高水平的API文件上传处理 ServletFileUpload upload = new ServletFileUpload(factory);
 * 目的是调用 parseRequest（request）方法 获得 FileItem 集合list ，
 * 
 * 5）在 FileItem 对象中 获取信息， 遍历， 判断 表单提交过来的信息 是否是 普通文本信息 另做处理 6） 第一种. 用第三方 提供的
 * item.write( new File(path,filename) ); 直接写到磁盘上 第二种. 手动处理
 * 
 */
public class UploadUtil{
	private static final long serialVersionUID = 1L;
	// 保存文件的目录
	//private static String PATH_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_FOLDER = "";
	
	private static final String COMPANY_PATH="company"; 
	
	private static final String PARK_PATH="park"; 
	
	private static final String NEWS_PATH="news"; 
	
	private static final String PARK_GROUP_PATH="parkgroup";
	
	public static ResultType upLoad(HttpServletRequest request,String companyCode,String parkCode,String parkGroupCode){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 设置编码		
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		TEMP_FOLDER=request.getSession().getServletContext().getRealPath("/temp");
		File tempPath = new File(TEMP_FOLDER);
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
	    factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*10);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的上限 最大1M
	    //upload.setSizeMax(1024 * 200);
        //上传监听
	    upload.setProgressListener(new ProgressListener() {

		public void update(long pBytesRead, long pContentLength,int pItems) {
				/*System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
						+ pContentLength);*/
			}

		});		
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String fileName="";
			
			String orginalFileName=getUploadFileName(item);
			
			if(orginalFileName!=null&&orginalFileName.length()>0&&orginalFileName.lastIndexOf(".")>-1){
				 fileName= UUID.randomUUID()+orginalFileName.substring(orginalFileName.lastIndexOf("."));
			}
			
			String localPath=ImgProperties.SAVE_PATH;
			//System.out.println(new UploadUtil().getClass().getClassLoader().getResource("").getPath());
			//System.out.println(System.getProperty("catalina.home")+":"+System.getProperty("catalina.base"));
			StringBuilder builder=new StringBuilder();
			if(companyCode!=null && companyCode.length()>0){
				builder.append("/");
				builder.append(COMPANY_PATH);
				mkdir(localPath+builder.toString());
				builder.append("/");
				builder.append(companyCode);
				mkdir(localPath+builder.toString());
			}else if(parkCode!=null && parkCode.length()>0){
				builder.append("/");
				builder.append(PARK_PATH);
				mkdir(localPath+builder.toString());
				builder.append("/");
				builder.append(parkCode);
				mkdir(localPath+builder.toString());
			}else if(parkGroupCode!=null && parkGroupCode.length()>0){
				builder.append("/");
				builder.append(PARK_GROUP_PATH);
				mkdir(localPath+builder.toString());
				builder.append("/");
				builder.append(parkGroupCode);
				mkdir(localPath+builder.toString());
			}else{
				builder.append("/");
				builder.append(NEWS_PATH);
			}
			builder.append("/");
			builder.append(fileName);
			InputStream in=item.getInputStream();
			
			FileOutputStream fout=new FileOutputStream(new File(localPath+builder.toString()));
			byte[] b = new byte[1024];
	        int n;
	        while ((n = in.read(b)) != -1) {
	        	fout.write(b, 0, n);
	        }
	        in.close();
	        fout.close();
	        Map<String,String> map =new HashMap<String, String>();
	        
	        map.put("orginalFileName", orginalFileName);
	        map.put("path", builder.toString());
	        
			return ResultType.getSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultType.getError("上传失败");
		}
		 
	}

    /**
     * 上传图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * 
     * 返回状态码  如果成功返回文件名
     */
	public static ResultType upLoad(HttpServletRequest request,String companyCode,String parkCode){	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 设置编码		
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		TEMP_FOLDER=request.getSession().getServletContext().getRealPath("/temp");
		File tempPath = new File(TEMP_FOLDER);
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
	    factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*10);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的上限 最大1M
	    //upload.setSizeMax(1024 * 200);
        //上传监听
	    upload.setProgressListener(new ProgressListener() {

		public void update(long pBytesRead, long pContentLength,int pItems) {
				/*System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
						+ pContentLength);*/
			}

		});		
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String fileName="";
			
			String orginalFileName=getUploadFileName(item);
			
			if(orginalFileName!=null&&orginalFileName.length()>0&&orginalFileName.lastIndexOf(".")>-1){
				 fileName= UUID.randomUUID()+orginalFileName.substring(orginalFileName.lastIndexOf("."));
			}
			
			String localPath=ImgProperties.SAVE_PATH;
			//System.out.println(new UploadUtil().getClass().getClassLoader().getResource("").getPath());
			//System.out.println(System.getProperty("catalina.home")+":"+System.getProperty("catalina.base"));
			StringBuilder builder=new StringBuilder();
			if(companyCode!=null && companyCode.length()>0){
				builder.append("/");
				builder.append(COMPANY_PATH);
				mkdir(localPath+builder.toString());
				builder.append("/");
				builder.append(companyCode);
				mkdir(localPath+builder.toString());
			}else if(parkCode!=null && parkCode.length()>0){
				builder.append("/");
				builder.append(PARK_PATH);
				mkdir(localPath+builder.toString());
				builder.append("/");
				builder.append(parkCode);
				mkdir(localPath+builder.toString());
			}else{
				builder.append("/");
				builder.append(NEWS_PATH);
				mkdir(localPath+builder.toString());
			}
			builder.append("/");
			builder.append(fileName);
			InputStream in=item.getInputStream();
			
			FileOutputStream fout=new FileOutputStream(new File(localPath+builder.toString()));
			byte[] b = new byte[1024];
	        int n;
	        while ((n = in.read(b)) != -1) {
	        	fout.write(b, 0, n);
	        }
	        in.close();
	        fout.close();
	        Map<String,String> map =new HashMap<String, String>();
	        
	        map.put("orginalFileName", orginalFileName);
	        map.put("path", builder.toString());
	        
			return ResultType.getSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultType.getError("上传失败");
		}
		 

	}
	
	
	/**
     * 上传多张图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * 
     * 返回状态码  如果成功返回文件名
     */
	public static ResultType upLoads(HttpServletRequest request,String companyCode,String parkCode){	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 设置编码		
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		TEMP_FOLDER=request.getSession().getServletContext().getRealPath("/temp");
		File tempPath = new File(TEMP_FOLDER);
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
	    factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*10);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的上限 最大1M
	    //upload.setSizeMax(1024 * 200);
        //上传监听
	    upload.setProgressListener(new ProgressListener() {

		public void update(long pBytesRead, long pContentLength,int pItems) {
				/*System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
						+ pContentLength);*/
			}

		});		
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			
			List<FileItem> files=getUploadFileItems(list);
			
			List<String>  fileNames=new ArrayList<String>();
			
			for(FileItem item : files){
				// 获取文件名
				String fileName="";
				
				String orginalFileName=getUploadFileName(item);
				
				if(orginalFileName!=null&&orginalFileName.length()>0&&orginalFileName.lastIndexOf(".")>-1){
					 fileName= UUID.randomUUID()+orginalFileName.substring(orginalFileName.lastIndexOf("."));
				}
				
				String localPath=ImgProperties.SAVE_PATH;
				//System.out.println(new UploadUtil().getClass().getClassLoader().getResource("").getPath());
				//System.out.println(System.getProperty("catalina.home")+":"+System.getProperty("catalina.base"));
				StringBuilder builder=new StringBuilder();
				if(companyCode!=null && companyCode.length()>0){
					builder.append("/");
					builder.append(COMPANY_PATH);
					mkdir(localPath+builder.toString());
					builder.append("/");
					builder.append(companyCode);
					mkdir(localPath+builder.toString());
				}else if(parkCode!=null && parkCode.length()>0){
					builder.append("/");
					builder.append(PARK_PATH);
					mkdir(localPath+builder.toString());
					builder.append("/");
					builder.append(parkCode);
					mkdir(localPath+builder.toString());
				}else{
					builder.append("/");
					builder.append(PARK_GROUP_PATH);
					mkdir(localPath+builder.toString());
				}
				builder.append("/");
				builder.append(fileName);
				
				InputStream in=item.getInputStream();
				
				FileOutputStream fout=new FileOutputStream(new File(localPath+builder.toString()));
				byte[] b = new byte[1024];
		        int n;
		        while ((n = in.read(b)) != -1) {
		        	fout.write(b, 0, n);
		        }
		        in.close();
		        fout.close();
		        
		        fileNames.add(builder.toString());
	        
			}
			Map<String,Object> map =new HashMap<>();
	        map.put("paths",fileNames);
			return ResultType.getSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResultType.getError("上传失败");
		}
		 

	}
	
	
	/**
     * 上传多张图片
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * 
     * 返回状态码  如果成功返回文件名
     */
	public static ResultType upLoads2(HttpServletRequest request,String companyCode,String parkCode){	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		// 设置编码		
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		TEMP_FOLDER=request.getSession().getServletContext().getRealPath("/temp");
		File tempPath = new File(TEMP_FOLDER);
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
	    factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024*10);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的上限 最大1M
	    //upload.setSizeMax(1024 * 200);
        //上传监听
	    upload.setProgressListener(new ProgressListener() {

		public void update(long pBytesRead, long pContentLength,int pItems) {
				/*System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
						+ pContentLength);*/
			}

		});		
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			
			List<FileItem> files=getUploadFileItems(list);
			
			List<Object>  resfiles=new ArrayList<>();
			
			for(FileItem item : files){
				// 获取文件名
				String fileName="";
				
				String orginalFileName=getUploadFileName(item);
				
				if(orginalFileName!=null&&orginalFileName.length()>0&&orginalFileName.lastIndexOf(".")>-1){
					 fileName= UUID.randomUUID()+orginalFileName.substring(orginalFileName.lastIndexOf("."));
				}
				
				String localPath=ImgProperties.SAVE_PATH;
				//System.out.println(new UploadUtil().getClass().getClassLoader().getResource("").getPath());
				//System.out.println(System.getProperty("catalina.home")+":"+System.getProperty("catalina.base"));
				StringBuilder builder=new StringBuilder();
				if(companyCode!=null && companyCode.length()>0){
					builder.append("/");
					builder.append(COMPANY_PATH);
					mkdir(localPath+builder.toString());
					builder.append("/");
					builder.append(companyCode);
					mkdir(localPath+builder.toString());
				}else if(parkCode!=null && parkCode.length()>0){
					builder.append("/");
					builder.append(PARK_PATH);
					mkdir(localPath+builder.toString());
					builder.append("/");
					builder.append(parkCode);
					mkdir(localPath+builder.toString());
				}else{
					builder.append("/");
					builder.append(NEWS_PATH);
					mkdir(localPath+builder.toString());
				}
				builder.append("/");
				builder.append(fileName);
				
				InputStream in=item.getInputStream();
				
				FileOutputStream fout=new FileOutputStream(new File(localPath+builder.toString()));
				byte[] b = new byte[1024];
		        int n;
		        while ((n = in.read(b)) != -1) {
		        	fout.write(b, 0, n);
		        }
		        in.close();
		        fout.close();
		        Map<String,String> map =new HashMap<String, String>();
		        map.put("original", orginalFileName);
		        map.put("path", builder.toString());
		        resfiles.add(map);
	        
			}
			return ResultType.getSuccess(resfiles);
		}catch(Exception e){
			e.printStackTrace();
			return ResultType.getError("上传失败");
		}
		 

	}
	
	
	//获取上传文件
	private static FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}
	
	//获取多张上传文件
	private static List<FileItem> getUploadFileItems(List<FileItem> list) {
		List<FileItem> newlist =new ArrayList<FileItem>();
		
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				newlist.add(fileItem);
			}
		}
		return newlist;
	}
	//获取上传的文件名
	private static  String getUploadFileName(FileItem fileItem) {
		
		// 获取上传文件的名称 文件名称可能是(c:\xxxx\xxx\xx.jpg(IE浏览器) 或 xx.jpg(火狐浏览器));
		
		String fileName = fileItem.getName();
		
		int index = fileName.lastIndexOf("\\");
		
		if (index != -1) {
			
			fileName = fileName.substring(index + 1);
			
		}
		
		return fileName;
	}
	
	public static void mkdir(String name){
	    File file = new File(name);
        if (!file.exists()) {
          file.mkdirs();
        }
	}
	
	 /**
	  * 输入流转字节流
	  * */
	 public static  byte[] InputStreamToByte(InputStream is) throws IOException {
	  ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
	  byte[] buffer=new byte[1024];
	  int ch;
	  /**
	   * 
	   * */
	  while ((ch = is.read(buffer)) != -1) {
	   bytestream.write(buffer,0,ch);
	  }
	  byte data[] = bytestream.toByteArray();
	  bytestream.close();
	  return data;
	 }
}
