package com.xinfan.blueblue.request;


public class Response {
	private static final String DEV_ERROR = "dev_error";
	private static final String OK = "ok";
	private static final String SERVER_ERROR = "server_error";

	private String code;
	private String message;
	private Object value;

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		if (this.message == null)
			;
		for (String str = "";; str = this.message)
			return str;
	}

	boolean isDeveloperError() {
		return this.code.equals("dev_error");
	}

	public boolean isOK() {
		return this.code.equals("ok");
	}

	boolean isServerError() {
		return this.code.equals("server_error");
	}

	public void setCode(String paramString) {
		this.code = paramString;
	}

	public void setMessage(String paramString) {
		this.message = paramString;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}