package com.devender.blog.payloads;

import lombok.Getter;
import lombok.Setter;



public class ApiResponse {
	private String message;
	private boolean success;
	
	public ApiResponse(String message, boolean success) {
		this.message=message;
		this.success=success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiResponse [message=");
		builder.append(message);
		builder.append(", success=");
		builder.append(success);
		builder.append("]");
		return builder.toString();
	}

	public ApiResponse() {}

	
}
