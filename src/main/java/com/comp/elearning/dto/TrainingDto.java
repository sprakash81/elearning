package com.comp.elearning.dto;

import java.io.Serializable;
import java.util.Date;

public class TrainingDto implements Serializable {

	private static final long serialVersionUID = -5179877026555412267L;

	private long accountId;

	private String courseCode;

	private String courseComments;

	private Date courseCompletionDate;

	private String courseGrade;

	private long courseId;

	private Date courseStartDate;

	private String courseTitle;

	private long id;

	public long getAccountId() {
		return accountId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseComments() {
		return courseComments;
	}

	public Date getCourseCompletionDate() {
		return courseCompletionDate;
	}

	public String getCourseGrade() {
		return courseGrade;
	}

	public long getCourseId() {
		return courseId;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public long getId() {
		return id;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setCourseComments(String courseComments) {
		this.courseComments = courseComments;
	}

	public void setCourseCompletionDate(Date courseCompletionDate) {
		this.courseCompletionDate = courseCompletionDate;
	}

	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void setId(long id) {
		this.id = id;
	}

}
