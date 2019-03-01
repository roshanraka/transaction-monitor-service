package com.dunya.orion.transaction.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
	@JsonProperty("error_code")
	private int errorCode;
	@JsonProperty("error_msg")
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}

	public static ErrorResponse newSuccessResponse() {
		ErrorResponse e = new ErrorResponse();
		e.setErrorCode(0);
		e.setErrorMessage("");
		return e;
	}

	public static ErrorResponse newErrorResponse(int code, String msg)  {
		ErrorResponse e = new ErrorResponse();
		e.setErrorCode(code);
		e.setErrorMessage(msg);
		return e;
	}
}