package com.comp.elearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comp.elearning.dao.AddressDao;
import com.comp.elearning.entity.Address;
import com.comp.elearning.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao dao;

	@Override
	@Transactional(readOnly = true)
	public Address get(Long id) {
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> list() {
		return dao.listAll();
	}

}
