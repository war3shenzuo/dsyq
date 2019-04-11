package com.etop.management.bean;

import com.etop.management.entity.EtopBillDelay;
import com.etop.management.util.DateUtil;

public class BillDelayWithUser extends EtopBillDelay {

	private String applicantName;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\n延期编号：").append(getDelayId())
			.append("；延迟时间：").append(DateUtil.formatDateTime(getDelayTime()))
			.append("\n申请人：").append(applicantName)
			.append("；申请时间：").append(DateUtil.formatDateTime(getApplyTime()))
			.append("\n审批人：").append(getAuditor())
			.append("；审批时间：").append(DateUtil.formatDateTime(getAuditTime()))
			.append("\n延迟理由：").append(getReason());
		return sb.toString();
	}
}
