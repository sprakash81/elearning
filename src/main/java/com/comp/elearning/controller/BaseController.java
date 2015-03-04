package com.comp.elearning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	protected ResponseEntity<String> handleException(Exception e,
			boolean logError, HttpStatus httpStatus) {
		if (logError) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<String>("Oops, our bad, unexpected error ",
				httpStatus);
	}

	@ExceptionHandler({ Exception.class })
	ResponseEntity<String> handleUnknownExceptions(Exception e) {
		return handleException(e, true, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
