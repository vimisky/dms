package com.vimisky.dms.entity;

public class ContentVSCategory extends IdEntity {

	private ContentBase contentBase;
	private VSCategory vsCategory;
	
	
	
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
	 * @return the vsCategory
	 */
	public VSCategory getVsCategory() {
		return vsCategory;
	}



	/**
	 * @param vsCategory the vsCategory to set
	 */
	public void setVsCategory(VSCategory vsCategory) {
		this.vsCategory = vsCategory;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
