package com.code.secretary.enums.util;

public enum PrayTimesFormatEnum {

	HOUR_24(0),
	HOURS_12(1);

	private int code;

	private PrayTimesFormatEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
