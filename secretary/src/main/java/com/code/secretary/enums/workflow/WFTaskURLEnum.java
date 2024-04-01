package com.code.secretary.enums.workflow;

public enum WFTaskURLEnum {

	ATTENDANCE_REQUEST("/manager/attendance-request"),
	
	HOSPITALITY_RESPONSIBLE_REQUEST("/secretary/hospitality-responsible-request"),
	
	HOSPITALITY_REQUEST("/secretary/hospitality-request"),

	CALL_REQUEST("/secretary/interview-call-request/1"),
	INTERVIEW_REQUEST("/secretary/interview-call-request/2"),

	NOTIFICATIONS("/workflow/notification"),

	APPOINTMENT_REQUEST("/secretary/meeting-appointment-request/1"),

	MEETING_REQUEST("/secretary/meeting-appointment-request/2");
	
	private String code;

	private WFTaskURLEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
