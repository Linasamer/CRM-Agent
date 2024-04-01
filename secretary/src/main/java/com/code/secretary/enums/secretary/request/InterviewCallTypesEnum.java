package com.code.secretary.enums.secretary.request;

public enum InterviewCallTypesEnum {

	CALL(1), INTERVIEW(2);

	private int code;

	private InterviewCallTypesEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
