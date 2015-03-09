package com.comp.elearning.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "training")
@NamedQueries({ @NamedQuery(name = "searchByAccount", query = "from Training t where t.account.id = :id") })
public class Training extends BasePersistentObject {

	@ManyToOne
	private Account account;

	@Column(name = "comments", length = 400)
	private String comments;

	@ManyToOne
	private Course course;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "grade", length = 2)
	private String grade;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	public Account getAccount() {
		return account;
	}

	public String getComments() {
		return comments;
	}

	public Course getCourse() {
		return course;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getGrade() {
		return grade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
