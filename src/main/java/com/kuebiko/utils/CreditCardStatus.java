package com.kuebiko.utils;

public enum CreditCardStatus {
	PENDING("Pending"), REVIEW("Review"), APPROVED("Approved"), PROCESSED("Processed"), READY("Ready");

	private String value;

	private CreditCardStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
