package com.code.secretary.entity.dto.security;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
	private String username;
	private String password;
	private String systemPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSystemPassword() {
		return systemPassword;
	}

	public void setSystemPassword(String systemPassword) {
		this.systemPassword = systemPassword;
	}
}
