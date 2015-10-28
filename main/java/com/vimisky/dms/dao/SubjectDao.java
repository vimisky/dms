package com.vimisky.dms.dao;

import java.util.List;

import com.vimisky.dms.entity.Subject;

public interface SubjectDao {

	public Subject getSubjectById(int id);
	public List<Subject> getRootSubjects();
	public List<Subject> getSubjectsByParentSubjectId(int pid);
	public List<Subject> getRootSubjectsBySubjectTypeId(int stid);
	public List<Subject> getSubjectsBySubjectTypeId(int stid);
	public List<Subject> getAllSubjects();
	public Subject insertSubject(Subject Subject);
	public Subject updateSubject(Subject oldSubject,Subject newSubject);
	public void deleteSubject(Subject Subject);
	
}
