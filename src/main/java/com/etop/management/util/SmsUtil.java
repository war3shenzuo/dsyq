//package com.etop.management.util;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.etop.management.bean.ResultType;
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//
//public class SmsUtil {
//	private Logger logger = LoggerFactory.getLogger(SmsUtil.class);
//	private static final String url = "http://gw.api.taobao.com/router/rest";
//	private static final String appkey = "23481148";
//	private static final String secret = "39257f26f3541ff4e0b4b67753a533d5";
//	private static final String registermodel = "SMS_17965041";
//	private static final String backmodel = "SMS_17965039";
//
//
//	/**
//	 * 发送注册接口
//	 * @param user
//	 * @param param "{\"code\":"+code+",\"product\":驿拓园区在线}"
//	 * @return
//	 */
//	public static ResultType sendRegisterSms(String mobile,String param){
//
//		return new SmsUtil().sendSms(mobile,registermodel,param);
//
//	}
//
//	/**
//	 * 找回密码
//	 * @param user
//	 * @param param "{\"code\":"+code+",\"product\":驿拓园区在线}"
//	 * @return
//	 */
//	public static ResultType sendBackSms(String mobile,String param){
//
//		return new SmsUtil().sendSms(mobile,backmodel,param);
//
//	}
//
//	@SuppressWarnings("null")
//	private ResultType sendSms(String mobile,String templateCode,String param){
//
//		ResultType result = null;
//
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("驿拓");
//		req.setSmsParam(param);
//		req.setRecNum(mobile);
//		req.setSmsTemplateCode(templateCode);
//
//		AlibabaAliqinFcSmsNumSendResponse rsp = null;
//
//		 try {
//			 	rsp = client.execute(req);
//
//	            if(rsp.isSuccess()){
//
//	            	 result = ResultType.getSuccess("发送成功");
//
//	            }else{
//
//	            	logger.error("短信发送失败", rsp.getMsg()+","+rsp.getErrorCode());
//
//	            	result = ResultType.getFail( rsp.getMsg()+","+rsp.getErrorCode());
//	            }
//
//	        } catch (ApiException e) {
//
//	        	logger.error("短信发送失败", rsp.getSubMsg()+","+rsp.getSubCode());
//
//	            e.printStackTrace();
//
//	        }
//
//		return result;
//
//	}
//
//	public static String createRandomVcode() {
//
//		int n = 0 ;
//
//		while(n < 100000){
//
//		n = (int)(Math.random()*1000000);
//
//		}
//
//		return String.valueOf(n);
//	}
//}
