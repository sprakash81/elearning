package com.comp.elearning.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 3215167122260210852L;

	public ApplicationException(@SuppressWarnings("rawtypes") Class clazz,
			String message) {
		super(clazz.toString() + " : " + message);
	}

}
