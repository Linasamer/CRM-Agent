package com.code.secretary.enums.workflow;

public enum WFProcessesEnum {

	ATTENDANCE_REQUEST(5),

	HOSPITALITY_RESPONSIBLE_REQUEST(10),
	HOSPITALITY_REQUEST(15),

	CALL_REQUEST(20),
	INTERVIEW_REQUEST(25),

	APPOINTMENT_REQUEST(30),
	MEETING_REQUEST(35),

	ALERT_SENDING(40),

	// notification
	NOTIFICATIONS(100);

	private long code;

	private WFProcessesEnum(long code) {
		this.code = code;
	}

	public long getCode() {
		return code;
	}
}
