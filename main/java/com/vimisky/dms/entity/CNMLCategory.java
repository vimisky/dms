package com.vimisky.dms.entity;

public class CNMLCategory extends IdEntity{

	private String code;
	private String simplifiedChineseName;
	private String traditionalChineseName;
	private String englishName;
	private String franchName;
	private String spanishName;
	private String portugueseName;
	private String russianName;
	private String arabicName ;
	private String version = "0";
	private String cnmlWordTableName = null;
	private int levelId = 0;
	private CNMLCategory parent = null;
	private int valid = 0;
	
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}



	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @return the simplifiedChineseName
	 */
	public String getSimplifiedChineseName() {
		return simplifiedChineseName;
	}



	/**
	 * @param simplifiedChineseName the simplifiedChineseName to set
	 */
	public void setSimplifiedChineseName(String simplifiedChineseName) {
		this.simplifiedChineseName = simplifiedChineseName;
	}



	/**
	 * @return the traditionalChineseName
	 */
	public String getTraditionalChineseName() {
		return traditionalChineseName;
	}



	/**
	 * @param traditionalChineseName the traditionalChineseName to set
	 */
	public void setTraditionalChineseName(String traditionalChineseName) {
		this.traditionalChineseName = traditionalChineseName;
	}



	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}



	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}



	/**
	 * @return the franchName
	 */
	public String getFranchName() {
		return franchName;
	}



	/**
	 * @param franchName the franchName to set
	 */
	public void setFranchName(String franchName) {
		this.franchName = franchName;
	}



	/**
	 * @return the spanishName
	 */
	public String getSpanishName() {
		return spanishName;
	}



	/**
	 * @param spanishName the spanishName to set
	 */
	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}



	/**
	 * @return the portugueseName
	 */
	public String getPortugueseName() {
		return portugueseName;
	}



	/**
	 * @param portugueseName the portugueseName to set
	 */
	public void setPortugueseName(String portugueseName) {
		this.portugueseName = portugueseName;
	}



	/**
	 * @return the russianName
	 */
	public String getRussianName() {
		return russianName;
	}



	/**
	 * @param russianName the russianName to set
	 */
	public void setRussianName(String russianName) {
		this.russianName = russianName;
	}



	/**
	 * @return the arabicName
	 */
	public String getArabicName() {
		return arabicName;
	}



	/**
	 * @param arabicName the arabicName to set
	 */
	public void setArabicName(String arabicName) {
		this.arabicName = arabicName;
	}



	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}



	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}



	/**
	 * @return the cnmlWordTableName
	 */
	public String getCnmlWordTableName() {
		return cnmlWordTableName;
	}



	/**
	 * @param cnmlWordTableName the cnmlWordTableName to set
	 */
	public void setCnmlWordTableName(String cnmlWordTableName) {
		this.cnmlWordTableName = cnmlWordTableName;
	}



	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}



	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}



	/**
	 * @return the parent
	 */
	public CNMLCategory getParent() {
		return parent;
	}



	/**
	 * @param parent the parent to set
	 */
	public void setParent(CNMLCategory parent) {
		this.parent = parent;
	}



	/**
	 * @return the valid
	 */
	public int getValid() {
		return valid;
	}



	/**
	 * @param valid the valid to set
	 */
	public void setValid(int valid) {
		this.valid = valid;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
