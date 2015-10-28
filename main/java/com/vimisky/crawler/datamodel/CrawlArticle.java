package com.vimisky.crawler.datamodel;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CrawlArticle implements Serializable, BDBDataModelInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String subject;
	private Date pubtime;
	private Date crawltime;
	private String sourceSiteName;
	private String sourceSiteUrl;
	private String sourceArticleUrl;
	private String authors;
	private String tags;
	private String originWebPageContent;
	private String content;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the pubtime
	 */
	public Date getPubtime() {
		return pubtime;
	}
	/**
	 * @param pubtime the pubtime to set
	 */
	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}
	/**
	 * @return the crawltime
	 */
	public Date getCrawltime() {
		return crawltime;
	}
	/**
	 * @param crawltime the crawltime to set
	 */
	public void setCrawltime(Date crawltime) {
		this.crawltime = crawltime;
	}
	/**
	 * @return the originWebPageContent
	 */
	public String getOriginWebPageContent() {
		return originWebPageContent;
	}
	/**
	 * @param originWebPageContent the originWebPageContent to set
	 */
	public void setOriginWebPageContent(String originWebPageContent) {
		this.originWebPageContent = originWebPageContent;
	}
	/**
	 * @return the sourceName
	 */
	public String getSourceSiteName() {
		return sourceSiteName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceSiteName(String sourceSiteName) {
		this.sourceSiteName = sourceSiteName;
	}
	/**
	 * @return the sourceSiteUrl
	 */
	public String getSourceSiteUrl() {
		return sourceSiteUrl;
	}
	/**
	 * @param sourceSiteUrl the sourceSiteUrl to set
	 */
	public void setSourceSiteUrl(String sourceSiteUrl) {
		this.sourceSiteUrl = sourceSiteUrl;
	}
	/**
	 * @return the sourceArticleUrl
	 */
	public String getSourceArticleUrl() {
		return sourceArticleUrl;
	}
	/**
	 * @param sourceArticleUrl the sourceArticleUrl to set
	 */
	public void setSourceArticleUrl(String sourceArticleUrl) {
		this.sourceArticleUrl = sourceArticleUrl;
	}
	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@JsonIgnore
	public String getKeyString(){
		
		if (getSourceArticleUrl() != null) {
			return getSourceArticleUrl();
		}else if (getTitle()!=null&&getSourceSiteName()!=null) {
			return DigestUtils.md5Hex(getTitle()+getSourceSiteName());
		}else {
			return null;
		}
		
	}
	
}
