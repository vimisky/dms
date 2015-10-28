package com.vimisky.dms.entity;

import java.util.Date;

public class ContentBase{

	private Long contentId;
	private String title;
	private String subTitle;
	private String cLanguage;
	private String author;
	private String summary;
	private String sourceURL;
	private String sourceName;
	private Date createTime;
	private Date landTime;
	private Date modifyTime;
	private Date publishTime;
	private ContentTag[] contentTags;
	
	
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getcLanguage() {
		return cLanguage;
	}
	public void setcLanguage(String cLanguage) {
		this.cLanguage = cLanguage;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSourceURL() {
		return sourceURL;
	}
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLandTime() {
		return landTime;
	}
	public void setLandTime(Date landTime) {
		this.landTime = landTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the contentTags
	 */
	public ContentTag[] getContentTags() {
		return contentTags;
	}
	/**
	 * @param contentTags the contentTags to set
	 */
	public void setContentTags(ContentTag[] contentTags) {
		this.contentTags = contentTags;
	}

	
}
