package com.code.secretary.enums.secretary.request;

public enum AttendanceRequestTypesEnum {
	ATTENDANCE(1), CALLING(2);

	private int code;

	private AttendanceRequestTypesEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
