package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.PhotoEntity;

public interface PhotoEntityDao {

	public PhotoEntity getPhotoEntityById(int id);
	public List<PhotoEntity> getAllPhotoEntitys();
	public List<PhotoEntity> getPhotoEntitysByParam(Map<String, Object> parameterMap);
	public PhotoEntity insertPhotoEntity(PhotoEntity PhotoEntity);
	public PhotoEntity updatePhotoEntity(PhotoEntity oldPhotoEntity,PhotoEntity newPhotoEntity);
	public void deletePhotoEntity(PhotoEntity PhotoEntity);
	
}
