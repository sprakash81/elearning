package com.comp.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comp.elearning.dao.CourseDao;
import com.comp.elearning.entity.Course;
import com.comp.elearning.exception.ApplicationException;
import com.comp.elearning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao dao;

	@Override
	@Transactional
	public Course create(Course entity) throws ApplicationException {
		return update(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Course get(Long id) {
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> list() {
		return dao.listAll();
	}

	@Override
	@Transactional
	public Course update(Course entity) throws ApplicationException {
		validateMandatoryFields(entity);
		final Long id = dao.saveOrUpdate(entity);
		return get(id);
	}

	private void validateMandatoryFields(Course entity)
			throws ApplicationException {
		if (null == entity.getCode())
			throw new ApplicationException(this.getClass(),
					"Course code is mandatory.");
		if (null == entity.getTitle())
			throw new ApplicationException(this.getClass(),
					"Course title is mandatory.");
	}

}
