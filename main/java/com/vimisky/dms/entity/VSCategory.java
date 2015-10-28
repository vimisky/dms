package com.vimisky.dms.entity;

public class VSCategory extends IdEntity {
	
	private String name;
	private String description;
	private int level;
	private VSCategory parent;

	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}




	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}




	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}




	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}




	/**
	 * @return the parent
	 */
	public VSCategory getParent() {
		return parent;
	}




	/**
	 * @param parent the parent to set
	 */
	public void setParent(VSCategory parent) {
		this.parent = parent;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
