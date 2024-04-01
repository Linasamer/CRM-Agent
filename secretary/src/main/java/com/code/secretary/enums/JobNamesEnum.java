package com.code.secretary.enums;

public enum JobNamesEnum {

	MEETING_APPOINTMENT_REMINDER("MEETING_APPOINTMENT_REMINDER");

	private String code;

	private JobNamesEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
