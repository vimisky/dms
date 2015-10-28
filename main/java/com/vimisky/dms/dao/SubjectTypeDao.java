package com.vimisky.dms.dao;

import java.util.List;

import com.vimisky.dms.entity.SubjectType;

public interface SubjectTypeDao {

	public SubjectType getSubjectTypeById(int id);
	public List<SubjectType> getAllSubjectTypes();
	
}
