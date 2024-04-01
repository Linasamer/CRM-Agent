package com.code.secretary.enums.secretary.request;

public enum MeetingAppointmentReqTypesEnum {

	MEETING(1),

	APPOINTMENT(2);

	private int code;

	private MeetingAppointmentReqTypesEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
