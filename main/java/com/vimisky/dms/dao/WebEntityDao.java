package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.WebEntity;

public interface WebEntityDao {

	public WebEntity getWebEntityById(int id);
	public List<WebEntity> getAllWebEntitys();
	public List<WebEntity> getWebEntitysByParam(Map<String, Object> parameterMap);
	public WebEntity insertWebEntity(WebEntity WebEntity);
	public WebEntity updateWebEntity(WebEntity oldWebEntity,WebEntity newWebEntity);
	public void deleteWebEntity(WebEntity WebEntity);
	
}
