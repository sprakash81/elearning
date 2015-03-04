package com.comp.elearning.dao;

import java.util.List;

import com.comp.elearning.entity.Account;

public interface AccountDao extends BaseDao<Account> {

	public List<Account> findByFullName(String name);

}
