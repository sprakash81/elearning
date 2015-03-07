package com.comp.elearning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comp.elearning.entity.Course;
import com.comp.elearning.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addCourse(@RequestBody Course course) {
		// @ModelAttribute("newCourse") Course course
		// @ModelAttribute Course course, Map<String, Object> map
		// @RequestParam String code, @RequestParam String title
		// @RequestParam Object obj
		try {
			course = courseService.create(course);
		} catch (final Exception e) {
			handleUnknownExceptions(e);
		}
		final Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("newCourse", course);

		return "courses";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "courses";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Course> listCourses() {
		try {
			return courseService.list();
		} catch (final Exception e) {
			handleUnknownExceptions(e);
		}
		return null;
	}

}
