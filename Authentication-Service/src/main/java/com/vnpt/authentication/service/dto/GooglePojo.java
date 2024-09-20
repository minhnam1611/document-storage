package com.vnpt.authentication.service.dto;

public class GooglePojo {

	private String id;
	private String email;
	private boolean verified_email;
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified_email() {
		return this.verified_email;
	}

	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

}
