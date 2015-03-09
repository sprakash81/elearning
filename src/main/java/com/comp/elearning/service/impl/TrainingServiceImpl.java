package com.comp.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comp.elearning.dao.AccountDao;
import com.comp.elearning.dao.TrainingDao;
import com.comp.elearning.entity.Account;
import com.comp.elearning.entity.Training;
import com.comp.elearning.exception.ApplicationException;
import com.comp.elearning.exception.EntityNotFoundException;
import com.comp.elearning.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TrainingDao dao;

	@Override
	@Transactional
	public Training create(Training entity) throws ApplicationException {
		return update(entity);
	}

	@Override
	@Transactional
	public List<Training> createOrUpdate(List<Training> trainings)
			throws ApplicationException {
		final List<Training> updatedList = new ArrayList<Training>();
		for (final Training training : trainings) {
			updatedList.add(update(training));
		}
		return updatedList;
	}

	@Override
	@Transactional
	public void delete(Long id) throws EntityNotFoundException {
		final Training entity = dao.get(id);
		if (null == entity) {
			throw new EntityNotFoundException(Training.class, id);
		}
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Training> findByAccount(long accountId)
			throws EntityNotFoundException {
		final Account account = accountDao.get(accountId);
		if (account == null) {
			throw new EntityNotFoundException(Account.class, accountId);
		}
		return dao.findByAccount(account);
	}

	@Override
	@Transactional(readOnly = true)
	public Training get(Long id) {
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Training> list() {
		return dao.listAll();
	}

	@Override
	@Transactional
	public Training update(Training entity) throws ApplicationException {
		validateMandatoryFields(entity);
		validateCrossFields(entity);
		final Long id = dao.saveOrUpdate(entity);
		return get(id);
	}

	private void validateCrossFields(Training entity)
			throws ApplicationException {
		if ((null != entity.getEndDate()) && entity.getGrade().isEmpty())
			throw new ApplicationException(this.getClass(),
					"Grade is required if training is completed.");
	}

	private void validateMandatoryFields(Training entity)
			throws ApplicationException {
		if (null == entity.getAccount())
			throw new ApplicationException(this.getClass(),
					"Account detail is required in training.");
		if (null == entity.getCourse())
			throw new ApplicationException(this.getClass(),
					"Course detail is required in training.");
		if (null == entity.getStartDate())
			throw new ApplicationException(this.getClass(),
					"Start date is required in training.");
	}

}
