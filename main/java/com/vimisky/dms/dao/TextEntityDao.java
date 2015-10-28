package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.TextEntity;

public interface TextEntityDao {

	public TextEntity getTextEntityById(int id);
	public List<TextEntity> getAllTextEntitys();
	public List<TextEntity> getTextEntitysByParam(Map<String, Object> parameterMap);
	public TextEntity insertTextEntity(TextEntity TextEntity);
	public TextEntity updateTextEntity(TextEntity oldTextEntity,TextEntity newTextEntity);
	public void deleteTextEntity(TextEntity TextEntity);
	
}
