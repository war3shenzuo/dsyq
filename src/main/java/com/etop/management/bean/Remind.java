package com.etop.management.bean;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * @author lvxiwei 

 * @time   2016年9月18日 上午9:56:15 
 */
public class Remind {
	
	 public static final String QX_YZSP="tz"; //"园长审批权限";
	 public static final String QX_CWSP="cw";// "财务审批权限";
	 public static final String QX_ZCWSP="zcw";// "园区组财务审批权限";
	 public static final String BILL= "zd";	//"账单提醒"
	 public static final String VISIT= "bf"; //"拜访提醒"
	 public static final String CONTRACT= "ht";//"合同提醒"	
	 public static final String CUSTOMER="kh";// "客户提醒";
	 public static final String EXAMINE="sp";// "审批提醒";
	 public static final String APPLY="qs";// "申请提醒";
	 public static final String PARK="yqtx";// "园区提醒";
	 public static final String NOTICE="tzs";// "通知提醒";
	 
	private java.lang.String id;//  
	private java.lang.String remindType;// 提醒类别 （0=账单，1=拜访、2=合同）
	private java.lang.String title;
	private java.lang.String content;//   内容
	private java.lang.String status;//   提醒状态
	private java.lang.String target;//   提醒对象
	private java.lang.String  createTime;//   创建时间
	private java.lang.String updateTime;//   更新时间
	private java.lang.String beforeTime;//距离现在多久时间
	private java.lang.String top;//置顶
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getRemindType() {
		return remindType;
	}
	public void setRemindType(java.lang.String remindType) {
		this.remindType = remindType;
	}	
	public java.lang.String getTitle() {
		return title;
	}
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getContent() {
		return content;
	}
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getTarget() {
		return target;
	}
	public void setTarget(java.lang.String target) {
		this.target = target;
	}
	public java.lang.String getBeforeTime() {
		return beforeTime;
	}
	public java.lang.String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.lang.String updateTime) {
		this.updateTime = updateTime;
	}
	public void setBeforeTime(java.lang.String beforeTime) {
		this.beforeTime = beforeTime;
	}
	public java.lang.String getTop() {
		return top;
	}
	public String setTop(java.lang.String top) {
		return this.top = top;
	}


	
}
