package com.vimisky.dms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ContentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7012780874819365729L;
	private int contentid;
	private String headline;
	private String subtitle;
	private boolean hasThumbnail;
	private String thumbnailUrl;
	private String summary;
	private String keywords;
	private String geoPoint;
	private String geoName;
	private Date createTime;
	private Date lastModifyTime;
	private Date landTime;
	private Date publishTime;
	private int nodeid;
	private boolean islink;
	private int linkTargetId;
	private int genreTypeId;
	private int itemTypeId;
	private String sourceUrl;
	private String sourceInfo;
	private String creators;
	private String editors;
	private String signers;
	private int authInfo;
	private String language;
	
	
	/**
	 * @return the contentid
	 */
	public int getContentid() {
		return contentid;
	}



	/**
	 * @param contentid the contentid to set
	 */
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}



	/**
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}



	/**
	 * @param headline the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}



	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}



	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}



	/**
	 * @return the hasThumbnail
	 */
	public boolean isHasThumbnail() {
		return hasThumbnail;
	}



	/**
	 * @param hasThumbnail the hasThumbnail to set
	 */
	public void setHasThumbnail(boolean hasThumbnail) {
		this.hasThumbnail = hasThumbnail;
	}



	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}



	/**
	 * @param thumbnailUrl the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}



	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}



	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}



	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}



	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}



	/**
	 * @return the geoPoint
	 */
	public String getGeoPoint() {
		return geoPoint;
	}



	/**
	 * @param geoPoint the geoPoint to set
	 */
	public void setGeoPoint(String geoPoint) {
		this.geoPoint = geoPoint;
	}



	/**
	 * @return the geoName
	 */
	public String getGeoName() {
		return geoName;
	}



	/**
	 * @param geoName the geoName to set
	 */
	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}



	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}



	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	/**
	 * @return the lastModifyTime
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}



	/**
	 * @param lastModifyTime the lastModifyTime to set
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}



	/**
	 * @return the landTime
	 */
	public Date getLandTime() {
		return landTime;
	}



	/**
	 * @param landTime the landTime to set
	 */
	public void setLandTime(Date landTime) {
		this.landTime = landTime;
	}



	/**
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}



	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}



	/**
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}



	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}



	/**
	 * @return the islink
	 */
	public boolean isIslink() {
		return islink;
	}



	/**
	 * @param islink the islink to set
	 */
	public void setIslink(boolean islink) {
		this.islink = islink;
	}



	/**
	 * @return the linkTargetId
	 */
	public int getLinkTargetId() {
		return linkTargetId;
	}



	/**
	 * @param linkTargetId the linkTargetId to set
	 */
	public void setLinkTargetId(int linkTargetId) {
		this.linkTargetId = linkTargetId;
	}



	public int getGenreTypeId() {
		return genreTypeId;
	}



	public void setGenreTypeId(int genreTypeId) {
		this.genreTypeId = genreTypeId;
	}



	public int getItemTypeId() {
		return itemTypeId;
	}



	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}



	/**
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}



	/**
	 * @param sourceUrl the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}



	/**
	 * @return the sourceInfo
	 */
	public String getSourceInfo() {
		return sourceInfo;
	}



	/**
	 * @param sourceInfo the sourceInfo to set
	 */
	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}



	/**
	 * @return the creators
	 */
	public String getCreators() {
		return creators;
	}



	/**
	 * @param creators the creators to set
	 */
	public void setCreators(String creators) {
		this.creators = creators;
	}



	/**
	 * @return the editors
	 */
	public String getEditors() {
		return editors;
	}



	/**
	 * @param editors the editors to set
	 */
	public void setEditors(String editors) {
		this.editors = editors;
	}



	/**
	 * @return the signers
	 */
	public String getSigners() {
		return signers;
	}



	/**
	 * @param signers the signers to set
	 */
	public void setSigners(String signers) {
		this.signers = signers;
	}



	/**
	 * @return the authInfo
	 */
	public int getAuthInfo() {
		return authInfo;
	}



	/**
	 * @param authInfo the authInfo to set
	 */
	public void setAuthInfo(int authInfo) {
		this.authInfo = authInfo;
	}



	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}



	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
