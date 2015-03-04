package com.comp.elearning.service;

import com.comp.elearning.entity.Training;
import com.comp.elearning.exception.ApplicationException;
import com.comp.elearning.exception.EntityNotFoundException;

public interface TrainingService extends BaseService<Training> {

	public Training create(Training entity) throws ApplicationException;

	public void delete(Long id) throws EntityNotFoundException;

	public Training update(Training entity) throws ApplicationException;

}