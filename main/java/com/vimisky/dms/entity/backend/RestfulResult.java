package com.vimisky.dms.entity.backend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * jackson官方文档地址<br/>
 * <a href="https://github.com/FasterXML/jackson-docs">https://github.com/FasterXML/jackson-docs</a>
 * */
/**
 * 单向，服务器端响应客户端请求，主要是POST上传请求，需要响应一个成功或者失败的结果
 * */
public class RestfulResult {

	private boolean success;
	private String errorCode;
	
	public RestfulResult() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RestfulResult(boolean success) {
		super();
		this.success = success;
	}
	
	
	
	public RestfulResult(boolean success, String errorCode) {
		super();
		this.success = success;
		this.errorCode = errorCode;
	}
	public RestfulResult(OperationServiceResult operationServiceResult) {
		super();
		this.success = operationServiceResult.isSuccess();
		this.errorCode = operationServiceResult.getErrorCode();
	}


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
