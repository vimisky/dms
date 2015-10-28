package com.vimisky.dms.controller.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CORSWebInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(getClass());
	private String responseAccessControlAllowOrigin;
	private String responseAccessControlAllowCredentials;
	private String responseAccessControlExposeHeaders;
	private String responseAccessControlMaxAge;
	private String responseAccessControlAllowMethods;
	private String responseAccessControlAllowHeaders;
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
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		logger.debug("结束CORS请求");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		Spring MVC框架居然已经默认支持了CORS OPTIONS Method
//		if("OPTIONS".equals(request.getMethod())){
//			logger.info("拦截CORS请求");
//			Enumeration<String> headerNames = request.getHeaderNames();
//			String headerName;
//			while((headerName = headerNames.nextElement()) != null){
//				logger.info("Header:"+headerName+" Value:"+request.getHeader(headerName));
//			}
//		}
			
		logger.debug("激活CORS拦截器，开始添加CORS Http Header");
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin", this.responseAccessControlAllowOrigin);
		if(this.responseAccessControlAllowCredentials!=null)
			response.addHeader("Access-Control-Allow-Credentials",this.responseAccessControlAllowCredentials);
		if(this.responseAccessControlExposeHeaders!=null)
			response.addHeader("Access-Control-Expose-Headers",this.responseAccessControlExposeHeaders);
		if(this.responseAccessControlMaxAge!=null)
			response.addHeader("Access-Control-Max-Age",this.responseAccessControlMaxAge);
		if(this.responseAccessControlAllowMethods!=null)
			response.addHeader("Access-Control-Allow-Methods",this.responseAccessControlAllowMethods);
		if(this.responseAccessControlExposeHeaders!=null)
			response.addHeader("Access-Control-Allow-Headers",this.responseAccessControlExposeHeaders);
		return super.preHandle(request, response, handler);
	}

}
