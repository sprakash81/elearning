package com.comp.elearning.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comp.elearning.dao.AddressDao;
import com.comp.elearning.entity.Address;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long count() {
		return (long) listAll().size();
	}

	@Override
	public void delete(Long id) {
		final Address entity = (Address) sessionFactory.getCurrentSession()
				.get(Address.class, id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public Address get(Long id) {
		return (Address) sessionFactory.getCurrentSession().get(Address.class,
				id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> listAll() {
		return sessionFactory.getCurrentSession().createCriteria(Address.class)
				.list();
	}

	@Override
	public Long saveOrUpdate(Address entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity.getId();
	}

}
