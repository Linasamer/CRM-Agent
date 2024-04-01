package com.code.secretary.entity.dto.setup;

import java.io.Serializable;
import java.util.Date;

public class HijriDate implements Serializable {
	private Date date;
	private String dateString;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
}
