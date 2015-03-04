package com.comp.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comp.elearning.dao.AccountDao;
import com.comp.elearning.entity.Account;
import com.comp.elearning.exception.ApplicationException;
import com.comp.elearning.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao dao;

	@Override
	@Transactional
	public Account create(Account entity) throws ApplicationException {
		return update(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Account get(Long id) {
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> list() {
		return dao.listAll();
	}

	@Override
	@Transactional
	public Account update(Account entity) throws ApplicationException {
		validateMandatoryFields(entity);
		final Long id = dao.saveOrUpdate(entity);
		return get(id);
	}

	private void validateMandatoryFields(Account entity)
			throws ApplicationException {
		if (null == entity.getFirstName())
			throw new ApplicationException(this.getClass(),
					"First name is mandatory.");
		if (null == entity.getLastName())
			throw new ApplicationException(this.getClass(),
					"Last name is mandatory.");
		if (null == entity.getEmailId())
			throw new ApplicationException(this.getClass(),
					"Email id is mandatory.");

	}

}
