package com.br.compass.dto;

public class ErrorFormDto {
	
	private Integer status_code;
	private String message;
	
	public ErrorFormDto(Integer status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}
	
	public Integer getStatus_code() {
		return status_code;
	}
	
	public void setStatus_code(Integer field) {
		this.status_code = field;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
