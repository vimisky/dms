package com.vimisky.dms.entity;

public class ContentProperty extends IdEntity{

	private ContentBase contentBase;
	private String pKey;
	private String pValue;
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
	 * @return the pKey
	 */
	public String getpKey() {
		return pKey;
	}
	/**
	 * @param pKey the pKey to set
	 */
	public void setpKey(String pKey) {
		this.pKey = pKey;
	}
	/**
	 * @return the pValue
	 */
	public String getpValue() {
		return pValue;
	}
	/**
	 * @param pValue the pValue to set
	 */
	public void setpValue(String pValue) {
		this.pValue = pValue;
	}
	
	
}
