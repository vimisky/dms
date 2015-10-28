package com.vimisky.dms.entity.backend;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vimisky.dms.entity.Category;

public class CategoryBrief {

	private int id;
	private String name;
	private String thumbnailUrl;
	private String thumbnailIcon;
	private Date lastModifyTime;
	private int parentCategoryId;
	private String parentCategoryName;
	private int useState;
	private int priority;
	private int categoryTypeId;
	private String categoryTypeName;
	private String categoryTypeThumbnailUrl;
	private String categoryTypeThumbnailIcon;	
	private List<CategoryBrief> sonCategoryBriefs;
	
	public CategoryBrief() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getThumbnailUrl() {
		return thumbnailUrl;
	}



	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}



	public String getThumbnailIcon() {
		return thumbnailIcon;
	}



	public void setThumbnailIcon(String thumbnailIcon) {
		this.thumbnailIcon = thumbnailIcon;
	}



	public Date getLastModifyTime() {
		return lastModifyTime;
	}



	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}



	public int getParentCategoryId() {
		return parentCategoryId;
	}



	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}	
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public int getUseState() {
		return useState;
	}

	public void setUseState(int useState) {
		this.useState = useState;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}



	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}



	public String getCategoryTypeName() {
		return categoryTypeName;
	}



	public void setCategoryTypeName(String categoryTypeName) {
		this.categoryTypeName = categoryTypeName;
	}



	public String getCategoryTypeThumbnailUrl() {
		return categoryTypeThumbnailUrl;
	}



	public void setCategoryTypeThumbnailUrl(String categoryTypeThumbnailUrl) {
		this.categoryTypeThumbnailUrl = categoryTypeThumbnailUrl;
	}



	public String getCategoryTypeThumbnailIcon() {
		return categoryTypeThumbnailIcon;
	}



	public void setCategoryTypeThumbnailIcon(String categoryTypeThumbnailIcon) {
		this.categoryTypeThumbnailIcon = categoryTypeThumbnailIcon;
	}



	@JsonInclude(Include.NON_EMPTY)
	public List<CategoryBrief> getSonCategoryBriefs() {
		return sonCategoryBriefs;
	}

	public void setSonCategoryBriefs(List<CategoryBrief> sonCategoryBriefs) {
		this.sonCategoryBriefs = sonCategoryBriefs;
	}
	@JsonProperty(value="isCollapsed")
	public boolean isCollapsed(){
		return false;
	}
	
	@JsonProperty(value="hasSons")
	public boolean isHasSons() {
		return sonCategoryBriefs != null && sonCategoryBriefs.size() > 0 ? true : false;
	}
	@JsonProperty(value="numberOfSons")
	@JsonInclude(Include.NON_EMPTY)
	public int getNumberOfSons() {
		return sonCategoryBriefs != null && sonCategoryBriefs.size() > 0 ? sonCategoryBriefs.size() : 0;
	}

	
}
