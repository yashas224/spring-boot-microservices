package com.microservice.trial.moviecatalogservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
	@JsonProperty("error-message")
	private String msg;
	private String statusCode;

	@JsonIgnore
	private int val;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ErrorResponse(String msg, String statusCode, int val) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
		this.val = val;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public ErrorResponse(String msg, String statusCode) {
		super();
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public ErrorResponse() {
		super();
	}

}
