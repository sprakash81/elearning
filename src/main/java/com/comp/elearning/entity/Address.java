package com.comp.elearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BasePersistentObject {

	@ManyToOne
	private Account account;

	@Column(name = "country", length = 60)
	private String country;

	@Column(name = "state", length = 60)
	private String state;

	@Column(name = "street_name", length = 255)
	private String streetName;

	@Column(name = "suburb", length = 60)
	private String suburb;

	@Column(name = "zip_code", length = 12)
	private String zipCode;

	public Account getAccount() {
		return account;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
