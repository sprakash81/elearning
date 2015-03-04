package com.comp.elearning.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comp.elearning.dao.AccountDao;
import com.comp.elearning.entity.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		return (long) listAll().size();
	}

	@Override
	public void delete(Long id) {
		final Account entity = (Account) sessionFactory.getCurrentSession()
				.get(Account.class, id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findByFullName(String name) {
		name = name.trim().toUpperCase();
		if (!name.isEmpty()) {
			final Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("searchByFullName");
			query.setString("firstName", name);
			query.setString("lastName", name);
			return query.list();
		}
		return null;
	}

	@Override
	public Account get(Long id) {
		return (Account) sessionFactory.getCurrentSession().get(Account.class,
				id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> listAll() {
		return sessionFactory.getCurrentSession().createCriteria(Account.class)
				.list();
	}

	@Override
	public Long saveOrUpdate(Account entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity.getId();
	}

}
