package com.vimisky.dms.entity;

import java.io.Serializable;

public class TextEntity extends ContentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4200188324026976143L;
	private int id;
	private int contentId;
	private String headline;
	private String byline;
	private String body;
	private String bodyType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getByline() {
		return byline;
	}
	public void setByline(String byline) {
		this.byline = byline;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
}
