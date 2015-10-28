package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.VideoEntity;

public interface VideoEntityDao {

	public VideoEntity getVideoEntityById(int id);
	public List<VideoEntity> getAllVideoEntitys();
	public List<VideoEntity> getVideoEntitysByParam(Map<String, Object> parameterMap);
	public VideoEntity insertVideoEntity(VideoEntity VideoEntity);
	public VideoEntity updateVideoEntity(VideoEntity oldVideoEntity,VideoEntity newVideoEntity);
	public void deleteVideoEntity(VideoEntity VideoEntity);
	
}
