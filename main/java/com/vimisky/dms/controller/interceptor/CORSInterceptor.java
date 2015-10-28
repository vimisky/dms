package com.vimisky.dms.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CORSInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(getClass());
	private String responseAccessControlAllowOrigin;
	private String responseAccessControlAllowCredentials;
	private String responseAccessControlExposeHeaders;
	private String responseAccessControlMaxAge;
	private String responseAccessControlAllowMethods;
	private String responseAccessControlAllowHeaders;
	
	public CORSInterceptor() {
		super();
		// TODO Auto-generated constructor stub
		this.responseAccessControlAllowOrigin = "*";
		this.responseAccessControlAllowMethods = "PUT,GET,POST,PATCH,DELETE";
	}

	/**
	 * @return the responseAccessControlAllowOrigin
	 */
	public String getResponseAccessControlAllowOrigin() {
		return responseAccessControlAllowOrigin;
	}

	/**
	 * @param responseAccessControlAllowOrigin the responseAccessControlAllowOrigin to set
	 */
	public void setResponseAccessControlAllowOrigin(
			String responseAccessControlAllowOrigin) {
		this.responseAccessControlAllowOrigin = responseAccessControlAllowOrigin;
	}

	/**
	 * @return the responseAccessControlAllowCredentials
	 */
	public String getResponseAccessControlAllowCredentials() {
		return responseAccessControlAllowCredentials;
	}

	/**
	 * @param responseAccessControlAllowCredentials the responseAccessControlAllowCredentials to set
	 */
	public void setResponseAccessControlAllowCredentials(
			String responseAccessControlAllowCredentials) {
		this.responseAccessControlAllowCredentials = responseAccessControlAllowCredentials;
	}

	/**
	 * @return the responseAccessControlExposeHeaders
	 */
	public String getResponseAccessControlExposeHeaders() {
		return responseAccessControlExposeHeaders;
	}

	/**
	 * @param responseAccessControlExposeHeaders the responseAccessControlExposeHeaders to set
	 */
	public void setResponseAccessControlExposeHeaders(
			String responseAccessControlExposeHeaders) {
		this.responseAccessControlExposeHeaders = responseAccessControlExposeHeaders;
	}

	/**
	 * @return the responseAccessControlMaxAge
	 */
	public String getResponseAccessControlMaxAge() {
		return responseAccessControlMaxAge;
	}

	/**
	 * @param responseAccessControlMaxAge the responseAccessControlMaxAge to set
	 */
	public void setResponseAccessControlMaxAge(String responseAccessControlMaxAge) {
		this.responseAccessControlMaxAge = responseAccessControlMaxAge;
	}

	/**
	 * @return the responseAccessControlAllowMethods
	 */
	public String getResponseAccessControlAllowMethods() {
		return responseAccessControlAllowMethods;
	}

	/**
	 * @param responseAccessControlAllowMethods the responseAccessControlAllowMethods to set
	 */
	public void setResponseAccessControlAllowMethods(
			String responseAccessControlAllowMethods) {
		this.responseAccessControlAllowMethods = responseAccessControlAllowMethods;
	}

	/**
	 * @return the responseAccessControlAllowHeaders
	 */
	public String getResponseAccessControlAllowHeaders() {
		return responseAccessControlAllowHeaders;
	}

	/**
	 * @param responseAccessControlAllowHeaders the responseAccessControlAllowHeaders to set
	 */
	public void setResponseAccessControlAllowHeaders(
			String responseAccessControlAllowHeaders) {
		this.responseAccessControlAllowHeaders = responseAccessControlAllowHeaders;
	}

	/***
	 * HTTP Request Header:
	 * Origin
	 * Access-Control-Request-Method
	 * Access-Control-Request-Headers
	 * 
	 * HTTP Response Header:
	 * Access-Control-Allow-Origin
	 * Access-Control-Allow-Credentials
	 * Access-Control-Expose-Headers
	 * Access-Control-Max-Age
	 * Access-Control-Allow-Methods
	 * Access-Control-Allow-Headers
	 * 
	 * 多个值用逗号,隔离
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		arg1.setHeader("Access-Control-Allow-Origin", this.getResponseAccessControlAllowOrigin());
		arg1.setHeader("Access-Control-Allow-Methods",this.getResponseAccessControlAllowMethods());
		if(this.getResponseAccessControlAllowCredentials()!=null)
			arg1.setHeader("Access-Control-Allow-Credentials",this.getResponseAccessControlAllowCredentials());
		if(this.getResponseAccessControlExposeHeaders()!=null)
			arg1.setHeader("Access-Control-Expose-Headers",this.getResponseAccessControlExposeHeaders());
		if(this.getResponseAccessControlMaxAge()!=null)
			arg1.setHeader("Access-Control-Max-Age",this.getResponseAccessControlMaxAge());
		if(this.getResponseAccessControlAllowMethods()!=null)
			arg1.setHeader("Access-Control-Allow-Methods",this.getResponseAccessControlAllowMethods());
		if(this.getResponseAccessControlExposeHeaders()!=null)
			arg1.setHeader("Access-Control-Allow-Headers",this.getResponseAccessControlExposeHeaders());
		return true;
	}

}
