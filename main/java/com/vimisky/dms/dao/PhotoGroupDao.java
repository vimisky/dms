package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.PhotoGroup;

public interface PhotoGroupDao {

	public PhotoGroup getPhotoGroupById(int id);
	public List<PhotoGroup> getAllPhotoGroups();
	public List<PhotoGroup> getPhotoGroupsByParam(Map<String, Object> parameterMap);
	public PhotoGroup insertPhotoGroup(PhotoGroup PhotoGroup);
	public PhotoGroup updatePhotoGroup(PhotoGroup oldPhotoGroup,PhotoGroup newPhotoGroup);
	public void deletePhotoGroup(PhotoGroup PhotoGroup);
	
}
