package com.etop.management.bean;

import com.etop.management.entity.EtopBillRelief;
import com.etop.management.util.DateUtil;

public class BillReliefWithUser extends EtopBillRelief {

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
		sb.append("\n\n减免编号：").append(getReliefId())
			.append("；本金减免：").append(getAmountRemission())
			.append("元；滞纳金减免：").append(getAmountRemission())
			.append("元\n申请人：").append(applicantName)
			.append("；申请时间：").append(DateUtil.formatDateTime(getApplyTime()))
			.append("\n减免理由：").append(getReason());
		return sb.toString();
	}
}
