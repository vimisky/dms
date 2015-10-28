package com.vimisky.dms.entity;

public class ContentTag extends IdEntity{

	private ContentBase contentBase;
	private String tag;
	/**
	 * @return the contentBase
	 */
	public ContentBase getContentBase() {
		return contentBase;
	}
	/**
	 * @param contentBase the contentBase to set
	 */
	public void setContentBase(ContentBase contentBase) {
		this.contentBase = contentBase;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
