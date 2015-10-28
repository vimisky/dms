package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.ContentEntity;

public interface ContentEntityDao {

	public ContentEntity getContentEntityById(int id);
	public List<ContentEntity> getAllContentEntitys();
	public List<ContentEntity> getContentEntitysByParam(Map<String, Object> parameterMap);
	public ContentEntity insertContentEntity(ContentEntity ContentEntity);
	public ContentEntity updateContentEntity(ContentEntity oldContentEntity,ContentEntity newContentEntity);
	public void deleteContentEntity(ContentEntity ContentEntity);
	
}
