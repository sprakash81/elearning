package com.comp.elearning.dao;

import java.util.List;

import com.comp.elearning.entity.Account;
import com.comp.elearning.entity.Training;

public interface TrainingDao extends BaseDao<Training> {

	public List<Training> findByAccount(Account entity);

}
