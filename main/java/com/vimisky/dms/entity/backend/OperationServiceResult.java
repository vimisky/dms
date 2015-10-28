package com.vimisky.dms.entity.backend;

public class OperationServiceResult {
	private boolean success;
	private String errorCode;

	public OperationServiceResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OperationServiceResult(boolean success) {
		super();
		this.success = success;
	}
	public OperationServiceResult(boolean success, String errorCode) {
		super();
		this.success = success;
		this.errorCode = errorCode;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}	
}
