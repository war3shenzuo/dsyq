package com.etop.management.bean;

import com.etop.management.entity.EtopBillPay;

public class BillPayWithUser extends EtopBillPay {

	private String recorderName;

	public String getRecorderName() {
		return recorderName;
	}

	public void setRecoderName(String recorderName) {
		this.recorderName = recorderName;
	}
}
