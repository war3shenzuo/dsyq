package com.etop.management.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 角色模型
 * @author shixianjie
 * 下午3:18:42
 */
public class Role  extends PageParam implements Serializable{
	
	 public static final String TYPE_SYSTEM= "0";	
	 public static final String TYPE_GROUP= "1";
	 public static final String TYPE_PARK= "2";
	 
	 public static final String QX_YZSP="tz"; //"园长审批权限";
	 public static final String QX_CWSP="cw";// "财务审批权限";
	 public static final String QX_ZCWSP="zcw";// "园区组财务审批权限";
	 
	 public static final String QX_KHTX="kh";// "客户提醒";
	 public static final String QX_HTTX="ht";// "合同提醒";
	 public static final String QX_SPTX="sp";// "审批提醒";
	 public static final String QX_QSTX="qs";// "申请提醒";
	 public static final String QX_YQTX="yqtx";// "园区提醒";
	 public static final String QX_RWTX="rw";// "任务提醒";
	 
	 public static final String QX_JMSQ="jmsq";// "减免申请";
	 public static final String QX_YQSQ="yqsq";// "延期申请";
	 public static final String QX_ZF="zf";// "支付";
	 public static final String QX_HR="hr";// "HR";
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private java.lang.String id;         // 主键ID  
		private java.lang.String roleCode;   //角色编号   
		private java.lang.String roleName;   //角色名称   
		private java.lang.String roleEscribe;//角色介绍   
		private java.lang.String roleType;   //0平台管理，1园区组，2园区
		private java.lang.String activated;  //是否激活  
		private java.lang.String parentId;   //父ID
		private Date createAt;               //创建时间   
		private java.lang.String defFunc;    //默认功能
		private java.lang.String parkId;     //园区ID
		private java.lang.String roleApproval;     //园区权限
		
		private java.lang.String parkName;     //园区ID
		private java.lang.String parkGroupId;     //园区组ID
		
		
		public java.lang.String getId() {
			return id;
		}
		public void setId(java.lang.String id) {
			this.id = id;
		}
		public void setCreateAt(Date createAt) {
			this.createAt = createAt;
		}
		public java.lang.String getRoleCode() {
		    return this.roleCode;
		}
		public void setRoleCode(java.lang.String roleCode) {
		    this.roleCode=roleCode;
		}
		public java.lang.String getRoleName() {
		    return this.roleName;
		}
		public void setRoleName(java.lang.String roleName) {
		    this.roleName=roleName;
		}
		public java.lang.String getRoleEscribe() {
		    return this.roleEscribe;
		}
		public void setRoleEscribe(java.lang.String roleEscribe) {
		    this.roleEscribe=roleEscribe;
		}
		public java.lang.String getRoleType() {
		    return this.roleType;
		}
		public void setRoleType(java.lang.String roleType) {
		    this.roleType=roleType;
		}
		public java.lang.String getActivated() {
		    return this.activated;
		}
		public void setActivated(java.lang.String activated) {
		    this.activated=activated;
		}
		public java.lang.String getParentId() {
		    return this.parentId;
		}
		public void setParentId(java.lang.String parentId) {
		    this.parentId=parentId;
		}
		public java.lang.String getDefFunc() {
		    return this.defFunc;
		}
		public void setDefFunc(java.lang.String defFunc) {
		    this.defFunc=defFunc;
		}
		public java.lang.String getParkId() {
		    return this.parkId;
		}
		public void setParkId(java.lang.String parkId) {
		    this.parkId=parkId;
		}
		public Date getCreateAt() {
			return createAt;
		}
		public java.lang.String getParkGroupId() {
			return parkGroupId;
		}
		public void setParkGroupId(java.lang.String parkGroupId) {
			this.parkGroupId = parkGroupId;
		}
		public java.lang.String getParkName() {
			return parkName;
		}
		public void setParkName(java.lang.String parkName) {
			this.parkName = parkName;
		}
		public java.lang.String getRoleApproval() {
			return roleApproval;
		}
		public void setRoleApproval(java.lang.String roleApproval) {
			this.roleApproval = roleApproval;
		}
		
		
		
}
