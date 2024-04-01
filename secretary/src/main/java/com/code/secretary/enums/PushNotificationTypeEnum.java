package com.code.secretary.enums;

public enum PushNotificationTypeEnum {

	REQUEST(0),
	ALERT(1),
	PRAYER_REMINDER(2),
	PRAYER(3),

	MEETING_APPOINTMENT_REMINDER(4);

	private int code;

	private PushNotificationTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
