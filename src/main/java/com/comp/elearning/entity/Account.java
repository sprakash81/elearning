package com.comp.elearning.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@NamedQueries({ @NamedQuery(name = "searchByFullName", query = "from Account a where upper(a.firstName) like :firstName or upper(a.lastName) like :lastName") })
public class Account extends BasePersistentObject {

	@Column(name = "active_ind", nullable = false)
	private Boolean activeIndicator = true;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Address> addresses;

	@Column(name = "email_id", nullable = false, length = 60)
	private String emailId;

	@Column(name = "first_name", nullable = false, length = 60)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 60)
	private String lastName;

	public Boolean getActiveIndicator() {
		return activeIndicator;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setActiveIndicator(Boolean activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
