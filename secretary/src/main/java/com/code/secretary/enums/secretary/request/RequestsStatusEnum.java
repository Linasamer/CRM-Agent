package com.code.secretary.enums.secretary.request;

public enum RequestsStatusEnum {

	UNDER_PROCESSING(1),
	ACCEPTED(2),
	REJECTED(3),
	NO_ACTION(4);

	private int code;

	private RequestsStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
