package com.comp.elearning.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comp.elearning.dao.TrainingDao;
import com.comp.elearning.entity.Account;
import com.comp.elearning.entity.Training;

@Repository
public class TrainingDaoImpl implements TrainingDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		return (long) listAll().size();
	}

	@Override
	public void delete(Long id) {
		final Training entity = (Training) sessionFactory.getCurrentSession()
				.get(Training.class, id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> findByAccount(Account entity) {
		if (entity != null) {
			final Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("searchByAccount");
			query.setLong("id", entity.getId());
			return query.list();
		}
		return null;
	}

	@Override
	public Training get(Long id) {
		return (Training) sessionFactory.getCurrentSession().get(
				Training.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Training> listAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Training.class).list();
	}

	@Override
	public Long saveOrUpdate(Training entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity.getId();
	}

}
