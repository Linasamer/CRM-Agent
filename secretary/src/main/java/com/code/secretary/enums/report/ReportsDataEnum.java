package com.code.secretary.enums.report;

public enum ReportsDataEnum {

	REPORT_NAME("/Reports/SubFolder/JASPER_REPORT_NAME.jrxml", "generated_file_name"),

	INTERVIEWS_CALLS_REQUESTS("/Reports/Requests/INTERVIEWS_CALLS_REQUESTS.jrxml", "InterviewsAndCallsRequests"),
	MEETINGS_APPOINTMENTS_REQUESTS("/Reports/Requests/MEETINGS_APPOINTMENTS_REQUESTS.jrxml", "MeetingsAndAppointmentsRequests");

	private String path;
	private String fileName;

	private ReportsDataEnum(String path, String fileName) {
		this.path = path;
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public String getFileName() {
		return fileName;
	}
}
