package com.vimisky.dms.dao;

import java.util.List;
import java.util.Map;

import com.vimisky.dms.entity.SpecialSubject;

public interface SpecialSubjectDao {

	public SpecialSubject getSpecialSubjectById(int id);
	public List<SpecialSubject> getAllSpecialSubjects();
	public List<SpecialSubject> getSpecialSubjectsByParam(Map<String, Object> parameterMap);
	public SpecialSubject insertSpecialSubject(SpecialSubject SpecialSubject);
	public SpecialSubject updateSpecialSubject(SpecialSubject oldSpecialSubject,SpecialSubject newSpecialSubject);
	public void deleteSpecialSubject(SpecialSubject SpecialSubject);
	
}
