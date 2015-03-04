package com.comp.elearning.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BasePersistentObject implements PersistentObject {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Version
	@Column(name = "version")
	private Long version;

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
