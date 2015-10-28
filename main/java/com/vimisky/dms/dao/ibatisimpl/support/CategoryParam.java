package com.vimisky.dms.dao.ibatisimpl.support;

public class CategoryParam {

	private int parentCategoryId;
	private int categoryTypeId;
	private int ancesterCategoryId;
	private String name;
	private String value;
	private int pkid;
	/**
	 * @return the parentCategoryId
	 */
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	/**
	 * @param parentCategoryId the parentCategoryId to set
	 */
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	/**
	 * @return the categoryTypeId
	 */
	public int getCategoryTypeId() {
		return categoryTypeId;
	}
	/**
	 * @param categoryTypeId the categoryTypeId to set
	 */
	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	/**
	 * @return the ancesterCategoryId
	 */
	public int getAncesterCategoryId() {
		return ancesterCategoryId;
	}
	/**
	 * @param ancesterCategoryId the ancesterCategoryId to set
	 */
	public void setAncesterCategoryId(int ancesterCategoryId) {
		this.ancesterCategoryId = ancesterCategoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPkid() {
		return pkid;
	}
	public void setPkid(int pkid) {
		this.pkid = pkid;
	}
	
	
	
	
}
