package com.vimisky.dms.entity.backend;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vimisky.dms.controller.validation.NewEntity;
import com.vimisky.dms.controller.validation.PatchEntity;
import com.vimisky.dms.controller.validation.UpdateEntity;
import com.vimisky.dms.entity.Category;
import com.vimisky.dms.entity.CategoryType;

public class CategoryDetail{

	private int id;
	private String name;
	private String secondaryName;
	private String description;
	private String language;
	private String thumbnailUrl;
	private String thumbnailUri;
	private String thumbnailIcon;
	private String code;
	private Date createTime;
	private Date lastModifyTime;
	private int parentCategoryId;
	private String parentCategoryName;
	private int useState;
	private int priority;
	private CategoryType categoryType;	

	public CategoryDetail(){
		super();
	}

	public Category convert2Category(){
		Category category = new Category();
		category.setId(this.id);
		category.setName(this.name);
		category.setSecondaryName(this.secondaryName);
		category.setDescription(this.description);
		category.setLanguage(this.language);
		category.setThumbnailUrl(this.thumbnailUrl);
		category.setThumbnailUri(this.thumbnailUri);
		category.setThumbnailIcon(this.thumbnailIcon);
		category.setCode(this.code);
		category.setCreateTime(this.createTime);
		category.setLastModifyTime(this.lastModifyTime);
		category.setParentCategoryId(this.parentCategoryId);
		category.setCategoryTypeId(this.categoryType.getId());
		return category;
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
	public String getSecondaryName() {
		return secondaryName;
	}
	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getThumbnailUri() {
		return thumbnailUri;
	}
	public void setThumbnailUri(String thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}
	public String getThumbnailIcon() {
		return thumbnailIcon;
	}
	public void setThumbnailIcon(String thumbnailIcon) {
		this.thumbnailIcon = thumbnailIcon;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	public int getUseState() {
		return useState;
	}
	public void setUseState(int useState) {
		this.useState = useState;
	}
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}		
	
}
