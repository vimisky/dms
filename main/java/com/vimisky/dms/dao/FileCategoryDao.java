package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.FileCategory;

public interface FileCategoryDao {

	public FileCategory getFileCategoryById(int id);
	public List<FileCategory> getAllFileCategorys();
	public List<FileCategory> getFileCategorysByParam(Map<String, Object> parameterMap);
	public FileCategory insertFileCategory(FileCategory FileCategory);
	public FileCategory updateFileCategory(FileCategory oldFileCategory,FileCategory newFileCategory);
	public void deleteFileCategory(FileCategory FileCategory);
	
}
