package com.comp.elearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "course")
@NamedQueries({ @NamedQuery(name = "searchByCodeOrTitle", query = "from Course c where upper(c.code) like :code or upper(c.title) like :title") })
public class Course extends BasePersistentObject {

	@Column(name = "active_ind", nullable = false)
	private boolean active_indicator = true;

	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "title", length = 255, nullable = false)
	private String title;

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public boolean isActive_indicator() {
		return active_indicator;
	}

	public void setActive_indicator(boolean active_indicator) {
		this.active_indicator = active_indicator;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
