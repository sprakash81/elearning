package com.comp.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comp.elearning.dao.TrainingDao;
import com.comp.elearning.entity.Training;
import com.comp.elearning.exception.ApplicationException;
import com.comp.elearning.exception.EntityNotFoundException;
import com.comp.elearning.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDao dao;

	@Override
	@Transactional
	public Training create(Training entity) throws ApplicationException {
		return update(entity);
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		final Training entity = dao.get(id);
		if (null == entity) {
			throw new EntityNotFoundException(Training.class, id);
		}
		dao.delete(id);
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
		final Long id = dao.saveOrUpdate(entity);
		return get(id);
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
		if ((null != entity.getEndDate()) && entity.getGrade().isEmpty())
			throw new ApplicationException(this.getClass(),
					"Grade is required if training is completed.");
	}

}
