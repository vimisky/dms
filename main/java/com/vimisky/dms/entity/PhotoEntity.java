package com.vimisky.dms.entity;

import java.io.Serializable;

public class PhotoEntity extends ContentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5623942073601148999L;
	
	private int id;
	private int contentId;
	private String headline;
	private String description;
	private String version;
	private String href;
	private String imageType;
	private int size;
	private String rendition;
	private int width;
	private int height;
	private int orientation;
	private String layout_orientation;
	private String colorSpace;
	private String colorDepth;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getRendition() {
		return rendition;
	}
	public void setRendition(String rendition) {
		this.rendition = rendition;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getLayout_orientation() {
		return layout_orientation;
	}
	public void setLayout_orientation(String layout_orientation) {
		this.layout_orientation = layout_orientation;
	}
	public String getColorSpace() {
		return colorSpace;
	}
	public void setColorSpace(String colorSpace) {
		this.colorSpace = colorSpace;
	}
	public String getColorDepth() {
		return colorDepth;
	}
	public void setColorDepth(String colorDepth) {
		this.colorDepth = colorDepth;
	}
}
