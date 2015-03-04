package com.comp.elearning.service;

import com.comp.elearning.entity.Account;
import com.comp.elearning.exception.ApplicationException;

public interface AccountService extends BaseService<Account> {

	public Account create(Account entity) throws ApplicationException;

	public Account update(Account entity) throws ApplicationException;

}
