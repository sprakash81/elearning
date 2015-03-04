package com.comp.elearning.service;

import com.comp.elearning.entity.Course;
import com.comp.elearning.exception.ApplicationException;

public interface CourseService extends BaseService<Course> {

	public Course create(Course entity) throws ApplicationException;

	public Course update(Course entity) throws ApplicationException;

}
