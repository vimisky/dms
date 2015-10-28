package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.FileObject;

public interface FileObjectDao {

	public FileObject getFileObjectById(int id);
	public List<FileObject> getAllFileObjects();
	public List<FileObject> getFileObjectsByParam(Map<String, Object> parameterMap);
	public FileObject insertFileObject(FileObject FileObject);
	public FileObject updateFileObject(FileObject oldFileObject,FileObject newFileObject);
	public void deleteFileObject(FileObject FileObject);
	
}
