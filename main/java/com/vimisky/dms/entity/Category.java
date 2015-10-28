package com.vimisky.dms.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.vimisky.dms.controller.validation.NewEntity;
import com.vimisky.dms.controller.validation.PatchEntity;
import com.vimisky.dms.controller.validation.UpdateEntity;

public class Category extends SkyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2312611282816569161L;
	
	@NotNull(groups={NewEntity.class,UpdateEntity.class},message="{com.vimisky.dms.backend.restful.parentid.notnullerror}")
	@Min(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=1,message="{com.vimisky.dms.backend.restful.parentid.minerror}")
	@Max(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=10000000,message="{com.vimisky.dms.backend.restful.parentid.maxerror}")//本处验证不严谨	
	private int parentCategoryId;
	@Min(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=0,message="{com.vimisky.dms.backend.restful.usestate.minerror}")
	@Max(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=3,message="{com.vimisky.dms.backend.restful.usestate.maxerror}")//本处验证不严谨	
	private int useState;
	@NotNull(groups={NewEntity.class,UpdateEntity.class},message="{com.vimisky.dms.backend.restful.categorytypeid.notnullerror}")
	@Min(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=1,message="{com.vimisky.dms.backend.restful.categorytypeid.minerror}")
	@Max(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=10000000,message="{com.vimisky.dms.backend.restful.categorytypeid.maxerror}")//本处验证不严谨	
	private int categoryTypeId;
	@Min(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=0,message="{com.vimisky.dms.backend.restful.priority.minerror}")
	@Max(groups={NewEntity.class,UpdateEntity.class,PatchEntity.class},value=10000000,message="{com.vimisky.dms.backend.restful.priority.maxerror}")//本处验证不严谨	
	private int priority;

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

	public int getUseState() {
		return useState;
	}
	
	public void setUseState(int useState) {
		this.useState = useState;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/* (non-Javadoc)
	 * @see com.vimisky.dms.entity.SkyEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	/* (non-Javadoc)
	 * @see com.vimisky.dms.entity.SkyEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see com.vimisky.dms.entity.SkyEntity#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"parentCategoryId:"+parentCategoryId+",useState:"+useState+","+",categoryTypeId"+categoryTypeId+",priority:"+priority;
	}
}
