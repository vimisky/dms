package com.vimisky.dms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vimisky.dms.controller.validation.NewEntity;
import com.vimisky.dms.controller.validation.PatchEntity;
import com.vimisky.dms.controller.validation.UpdateEntity;


public class SkyEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4494105044692096657L;
	
	@NotNull(groups={UpdateEntity.class},message="{com.vimisky.dms.backend.restful.id.notnullerror}")
	@Min(groups={UpdateEntity.class,PatchEntity.class},value=1,message="{com.vimisky.dms.backend.restful.id.minerror}")
	@Max(groups={UpdateEntity.class,PatchEntity.class},value=10000000,message="{com.vimisky.dms.backend.restful.id.maxerror}")//本处验证不严谨	
	private int id;
	@NotNull(groups={NewEntity.class, UpdateEntity.class}, message="{com.vimisky.dms.backend.restful.name.notnullerror}")
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=1,max=255,message="{com.vimisky.dms.backend.restful.name.lengtherror}")
	private String name;
	@Size(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},min=0,max=10,message="{com.vimisky.dms.backend.restful.secondaryname.lengtherror}")
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
	 * @return the secondaryName
	 */
	public String getSecondaryName() {
		return secondaryName;
	}
	/**
	 * @param secondaryName the secondaryName to set
	 */
	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
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
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	/**
	 * @param thumbnailUrl the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	/**
	 * @return the thumbnailUri
	 */
	public String getThumbnailUri() {
		return thumbnailUri;
	}
	/**
	 * @param thumbnailUri the thumbnailUri to set
	 */
	public void setThumbnailUri(String thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}
	
	/**
	 * @return the thumbnailIcon
	 */
	public String getThumbnailIcon() {
		return thumbnailIcon;
	}
	/**
	 * @param thumbnailIcon the thumbnailIcon to set
	 */
	public void setThumbnailIcon(String thumbnailIcon) {
		this.thumbnailIcon = thumbnailIcon;
	}
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
	 * @return the createTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the lastModifyTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	/**
	 * @param lastModifyTime the lastModifyTime to set
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{name:%s,secondaryName:%s,description:%s,thumbnailUrl:%s,thumbnailIcon:%s," +
				"code:%s, createTime:%s,lastModifyTime:%s}",name,secondaryName,description,thumbnailUrl,
				thumbnailIcon,code,createTime == null?null:createTime.toString(),lastModifyTime == null?null:lastModifyTime.toString());
	}
	
	


}
