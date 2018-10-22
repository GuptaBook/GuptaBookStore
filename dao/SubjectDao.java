package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.models.Subject;

public interface SubjectDao {
	public int insertSubject(Subject subject);

	public List<Subject> getSubject();

}
