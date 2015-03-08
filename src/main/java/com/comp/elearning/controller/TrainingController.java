package com.comp.elearning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.comp.elearning.entity.Training;
import com.comp.elearning.service.TrainingService;

@Controller
@RequestMapping("/trainings")
public class TrainingController extends BaseController {

	@Autowired
	private TrainingService trainingSerivce;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "training";
	}

	@RequestMapping(value = "/list", params = "id", method = RequestMethod.GET)
	public @ResponseBody List<Training> listTrainingForUser(
			@RequestParam Integer id) {
		try {
			return trainingSerivce.findByAccount(Long.valueOf(id));
		} catch (final Exception e) {
			handleException(e, true, HttpStatus.NOT_FOUND);
		}
		return null;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody Training training) {
		try {
			training = trainingSerivce.create(training);
		} catch (final Exception e) {
			handleUnknownExceptions(e);
		}
		final Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("account", training);

		return "account";
	}

}
