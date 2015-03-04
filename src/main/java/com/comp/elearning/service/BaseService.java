package com.comp.elearning.service;

import java.util.List;

public interface BaseService<T> {

	public T get(Long id);

	public List<T> list();

}
