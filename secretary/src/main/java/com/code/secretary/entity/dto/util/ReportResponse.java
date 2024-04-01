package com.code.secretary.entity.dto.util;

public class ReportResponse {
	private byte[] reportBytes;
	private String fileName;
	private String type;

	public ReportResponse(byte[] reportBytes, String fileName, String type) {
		this.reportBytes = reportBytes;
		this.fileName = fileName;
		this.type = type;
	}

	public byte[] getReportBytes() {
		return reportBytes;
	}

	public void setReportBytes(byte[] reportBytes) {
		this.reportBytes = reportBytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}