package com.vimisky.dms.entity.backend;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vimisky.dms.controller.validation.NewEntity;
import com.vimisky.dms.controller.validation.PatchEntity;
import com.vimisky.dms.controller.validation.UpdateEntity;
import com.vimisky.dms.entity.CategoryType;

public class CategoryTypeDetail {
	@NotNull(groups={UpdateEntity.class},message="{com.vimisky.dms.backend.restful.id.notnullerror}")
	@Min(groups={UpdateEntity.class,PatchEntity.class},value=1,message="{com.vimisky.dms.backend.restful.id.minerror}")
	@Max(groups={UpdateEntity.class,PatchEntity.class},value=10000000,message="{com.vimisky.dms.backend.restful.id.maxerror}")//本处验证不严谨
	private int id;
	@NotNull(groups={NewEntity.class}, message="{com.vimisky.dms.backend.restful.name.notnullerror}")
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=1,max=255,message="{com.vimisky.dms.backend.restful.name.lengtherror}")
	private String name;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=10,message="{com.vimisky.dms.backend.restful.secondaryname.lengtherror}")
//	@Size(min=0,max=10,message="{com.vimisky.dms.backend.restful.secondaryname.lengtherror}")
	private String secondaryName;
	//如果没有定义分组，而在应用validated时，指定了分组，则不会使用该条验证约束
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=1024,message="{com.vimisky.dms.backend.restful.description.lengtherror}")
	private String description;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=255,message="{com.vimisky.dms.backend.restful.language.lengtherror}")
	private String language;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=255,message="{com.vimisky.dms.backend.restful.thumbnailurl.lengtherror}")
	private String thumbnailUrl;
	//如果没有注释不能为空，则为空时，不检验Size等
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=1,max=255,message="{com.vimisky.dms.backend.restful.thumbnailuri.lengtherror}")
	private String thumbnailUri;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=255,message="{com.vimisky.dms.backend.restful.thumbnailicon.lengtherror}")
	private String thumbnailIcon;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=255,message="{com.vimisky.dms.backend.restful.code.lengtherror}")
	private String code;
	@Null(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class}, message="{com.vimisky.dms.backend.restful.createtime.nullerror}")
	private Date createTime;
	@Null(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},message="{com.vimisky.dms.backend.restful.lastmodifytime.nullerror}")
	private Date lastModifyTime;
	
	public CategoryTypeDetail(){
		super();
	}

	public CategoryTypeDetail(CategoryType categoryType){
		super();
		this.id = categoryType.getId();
		this.name = categoryType.getName();
		this.secondaryName = categoryType.getSecondaryName();
		this.description = categoryType.getDescription();
		this.language = categoryType.getLanguage();
		this.code = categoryType.getCode();
		this.thumbnailUrl = categoryType.getThumbnailUrl();
		this.thumbnailUri = categoryType.getThumbnailUri();
		this.thumbnailIcon = categoryType.getThumbnailIcon();
		this.createTime = categoryType.getCreateTime();
		this.lastModifyTime = categoryType.getLastModifyTime();
	}
	
	public CategoryType convert2CategoryType(){
		CategoryType categoryType = new CategoryType();
		categoryType.setId(this.id);
		categoryType.setName(this.name);
		categoryType.setSecondaryName(this.secondaryName);
		categoryType.setDescription(this.description);
		categoryType.setLanguage(this.language);
		categoryType.setThumbnailUrl(this.thumbnailUrl);
		categoryType.setThumbnailUri(this.thumbnailUri);
		categoryType.setThumbnailIcon(this.thumbnailIcon);
		categoryType.setCode(this.code);
		categoryType.setCreateTime(this.createTime);
		categoryType.setLastModifyTime(this.lastModifyTime);
		return categoryType;
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

	@Override
	public String toString() {
		return "CategoryTypeDetail [id=" + id + ", name=" + name
				+ ", secondaryName=" + secondaryName + ", description="
				+ description + ", language=" + language + ", thumbnailUrl="
				+ thumbnailUrl + ", thumbnailUri=" + thumbnailUri
				+ ", thumbnailIcon=" + thumbnailIcon + ", code=" + code
				+ ", createTime=" + createTime + ", lastModifyTime="
				+ lastModifyTime + "]";
	}

}
