package com.comp.elearning.dao;

import java.util.List;

import com.comp.elearning.entity.Course;

public interface CourseDao extends BaseDao<Course> {

	public List<Course> findByCodeOrTitle(String searchParam);

}
