package com.comp.elearning.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.comp.elearning.dto.TrainingDto;
import com.comp.elearning.entity.Training;
import com.comp.elearning.service.AccountService;
import com.comp.elearning.service.CourseService;
import com.comp.elearning.service.TrainingService;

@Controller
@RequestMapping("/trainings")
public class TrainingController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TrainingService trainingSerivce;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "training";
	}

	@RequestMapping(value = "/list", params = "id", method = RequestMethod.GET)
	public @ResponseBody List<TrainingDto> listTrainingForUser(
			@RequestParam Integer id) {
		try {
			final List<Training> trainings = trainingSerivce.findByAccount(Long
					.valueOf(id));
			return mapEntityToDto(trainings);
		} catch (final Exception e) {
			handleException(e, true, HttpStatus.NOT_FOUND);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private List<Training> mapDtoToEntity(List<LinkedHashMap> trainingDtos,
			List<Training> trainings) throws ParseException {
		for (final LinkedHashMap trainingDto : trainingDtos) {
			Training training;
			final Long id = (long) ((Integer) trainingDto.get("id")).intValue();
			final Long accountId = (long) ((Integer) trainingDto
					.get("accountId")).intValue();
			final Long courseId = (long) ((Integer) trainingDto.get("courseId"))
					.intValue();
			final String grade = String.valueOf(trainingDto.get("courseGrade"));
			final String courseComments = String.valueOf(trainingDto
					.get("courseComments"));
			final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			final Date courseStartDate = sf.parse(trainingDto.get(
					"courseStartDate").toString());

			if (id != 0) {
				training = trainingSerivce.get(id);
			} else {
				training = new Training();
			}
			training.setAccount(accountService.get(accountId));
			training.setCourse(courseService.get(courseId));
			training.setStartDate(courseStartDate);
			if (trainingDto.get("courseCompletionDate") != null) {
				final Date courseCompletionDate = sf.parse(trainingDto.get(
						"courseCompletionDate").toString());
				training.setEndDate(courseCompletionDate);
			}
			training.setGrade(grade);
			training.setComments(courseComments);
			trainings.add(training);
		}
		return trainings;

	}

	private List<TrainingDto> mapEntityToDto(List<Training> trainings) {
		final List<TrainingDto> dtos = new ArrayList<TrainingDto>();
		for (final Training entity : trainings) {
			final TrainingDto dto = new TrainingDto();
			dto.setAccountId(entity.getAccount().getId());
			dto.setCourseComments(entity.getComments());
			// final SimpleDateFormat dateFormat = new SimpleDateFormat(
			// "dd-mm-yyyy");
			if (null != entity.getEndDate())
				dto.setCourseCompletionDate(entity.getEndDate());
			if (null != entity.getStartDate())
				dto.setCourseStartDate(entity.getStartDate());
			dto.setCourseGrade(entity.getGrade());
			dto.setCourseId(entity.getCourse().getId());
			dto.setId(entity.getId());
			dto.setCourseCode(entity.getCourse().getCode());
			dto.setCourseTitle(entity.getCourse().getTitle());
			dtos.add(dto);
		}
		return dtos;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody List<LinkedHashMap> trainingDtos) {
		List<Training> trainings = new ArrayList<Training>();
		try {
			trainings = mapDtoToEntity(trainingDtos, trainings);
			trainings = trainingSerivce.createOrUpdate(trainings);
		} catch (final Exception e) {
			handleUnknownExceptions(e);
		}
		final Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("account", trainings);
		return "account";
	}

}
