package com.code.secretary.entity.dto.util;

public class PushNotification {

	private String messageHeader;
	private String message;
	private Integer type;

	public PushNotification(String messageHeader, String message, Integer type) {
		this.messageHeader = messageHeader;
		this.message = message;
		this.type = type;
	}

	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}