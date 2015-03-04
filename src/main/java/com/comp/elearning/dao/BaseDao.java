package com.comp.elearning.dao;

import java.util.List;

public interface BaseDao<T> {

	public Long count();

	public void delete(Long id);

	public T get(Long id);

	public List<T> listAll();

	public Long saveOrUpdate(T entity);

}
