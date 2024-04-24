package com.code.secretary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.code.secretary.enums.FlagsEnum;

//for repeated things done while using the services like checking for empty string to use '-1' or -1 when null
@Component
public class QueryUtilities {
	private QueryUtilities() {
	}

	public static String returnStringForLikeQuery(String string) {
		if (string == null || string.trim().isEmpty())
			return "" + FlagsEnum.ALL.getCode();

		return "%" + string.trim() + "%";
	}

	public static String returnStringForExactQuery(String string) {
		if (string == null || string.trim().isEmpty())
			return "" + FlagsEnum.ALL.getCode();

		return string.trim();
	}

	public static Integer returnIntegerForQuery(Integer integer) {
		if (integer == null)
			return FlagsEnum.ALL.getCode();

		return integer;
	}

	public static Integer returnBooleanForQuery(Boolean bool) {
		if (bool == null)
			return FlagsEnum.ALL.getCode();

		if (bool)
			return FlagsEnum.ON.getCode();

		return FlagsEnum.OFF.getCode();
	}

	public static Long returnLongForQuery(Long l) {
		if (l == null)
			return (long) FlagsEnum.ALL.getCode();

		return l;
	}

	public static Date returnDateForQuery(Date date, boolean fromDateFlag) throws ParseException {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return date == null ? null : dateTimeFormat.parse(dateFormat.format(date) + (fromDateFlag ? " 00:00:00" : " 23:59:59"));
	}

}
