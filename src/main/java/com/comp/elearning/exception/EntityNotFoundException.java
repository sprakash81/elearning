package com.comp.elearning.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 3253355149028986059L;

	public EntityNotFoundException(@SuppressWarnings("rawtypes") Class clazz,
			Long id) {
		super(clazz.toString() + " not found, id = " + id);
	}

	public EntityNotFoundException(@SuppressWarnings("rawtypes") Class clazz,
			String id) {
		super(clazz.toString() + " not found, id = " + id);
	}

}
