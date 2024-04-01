package com.code.secretary.enums.util;

public enum PrayTimeJobNamesEnum {
	FAJR_TIME_REMINDER("FAJR_REMINDER"),
	FAJR_TIME_ACTUAL("FAJR_ACTUAL"),

	DHUHR_TIME_REMINDER("DHUHR_REMINDER"),
	DHUHR_TIME_ACTUAL("DHUHR_ACTUAL"),

	ASR_TIME_REMINDER("ASR_REMINDER"),
	ASR_TIME_ACTUAL("ASR_ACTUAL"),

	MAGHRIB_TIME_REMINDER("MAGHRIB_REMINDER"),
	MAGHRIB_TIME_ACTUAL("MAGHRIB_ACTUAL"),

	ISHA_TIME_REMINDER("ISHA_REMINDER"),
	ISHA_TIME_ACTUAL("ISHA_ACTUAL"),

	REMIND_ME("REMIND_ME");

	private String code;

	private PrayTimeJobNamesEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
