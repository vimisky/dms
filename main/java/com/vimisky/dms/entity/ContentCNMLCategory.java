package com.vimisky.dms.entity;

public class ContentCNMLCategory extends IdEntity {

	private ContentBase contentBase;
	private String cnmlFileName;
	private CNMLCategory geoCategory;
	private CNMLCategory productCategory;
	private CNMLCategory columnCategory;
	private CNMLCategory internationalInternalCategory;
	private CNMLCategory knowledgeCategory;
	private CNMLCategory departmentCategory;
	
	
	
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
	 * @return the cnmlFileName
	 */
	public String getCnmlFileName() {
		return cnmlFileName;
	}



	/**
	 * @param cnmlFileName the cnmlFileName to set
	 */
	public void setCnmlFileName(String cnmlFileName) {
		this.cnmlFileName = cnmlFileName;
	}



	/**
	 * @return the geoCategory
	 */
	public CNMLCategory getGeoCategory() {
		return geoCategory;
	}



	/**
	 * @param geoCategory the geoCategory to set
	 */
	public void setGeoCategory(CNMLCategory geoCategory) {
		this.geoCategory = geoCategory;
	}



	/**
	 * @return the productCategory
	 */
	public CNMLCategory getProductCategory() {
		return productCategory;
	}



	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(CNMLCategory productCategory) {
		this.productCategory = productCategory;
	}



	/**
	 * @return the columnCategory
	 */
	public CNMLCategory getColumnCategory() {
		return columnCategory;
	}



	/**
	 * @param columnCategory the columnCategory to set
	 */
	public void setColumnCategory(CNMLCategory columnCategory) {
		this.columnCategory = columnCategory;
	}



	/**
	 * @return the internationalInternalCategory
	 */
	public CNMLCategory getInternationalInternalCategory() {
		return internationalInternalCategory;
	}



	/**
	 * @param internationalInternalCategory the internationalInternalCategory to set
	 */
	public void setInternationalInternalCategory(
			CNMLCategory internationalInternalCategory) {
		this.internationalInternalCategory = internationalInternalCategory;
	}



	/**
	 * @return the knowledgeCategory
	 */
	public CNMLCategory getKnowledgeCategory() {
		return knowledgeCategory;
	}



	/**
	 * @param knowledgeCategory the knowledgeCategory to set
	 */
	public void setKnowledgeCategory(CNMLCategory knowledgeCategory) {
		this.knowledgeCategory = knowledgeCategory;
	}



	/**
	 * @return the departmentCategory
	 */
	public CNMLCategory getDepartmentCategory() {
		return departmentCategory;
	}



	/**
	 * @param departmentCategory the departmentCategory to set
	 */
	public void setDepartmentCategory(CNMLCategory departmentCategory) {
		this.departmentCategory = departmentCategory;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
