package com.comp.elearning.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comp.elearning.dao.CourseDao;
import com.comp.elearning.entity.Course;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		return (long) listAll().size();
	}

	@Override
	public void delete(Long id) {
		final Course entity = (Course) sessionFactory.getCurrentSession().get(
				Course.class, id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findByCodeOrTitle(String searchParam) {
		final Query query = sessionFactory.getCurrentSession().getNamedQuery(
				"searchByCodeOrTitle");
		searchParam = searchParam.trim().toUpperCase();
		if (!searchParam.isEmpty()) {
			query.setString("code", searchParam);
			query.setString("title", searchParam);
			return query.list();
		}
		return null;
	}

	@Override
	public Course get(Long id) {
		return (Course) sessionFactory.getCurrentSession()
				.get(Course.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listAll() {
		return sessionFactory.getCurrentSession().createCriteria(Course.class)
				.list();
	}

	@Override
	public Long saveOrUpdate(Course entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity.getId();
	}

}
