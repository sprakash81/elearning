package com.comp.elearning.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "account")
@NamedQueries({ @NamedQuery(name = "searchByFullName", query = "from Account a where upper(a.firstName) like :firstName or upper(a.lastName) like :lastName") })
public class Account extends BasePersistentObject {

	@Column(name = "active_ind", nullable = false)
	private Boolean activeIndicator = true;

	// @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch =
	// FetchType.LAZY)
	// private Collection<Address> addresses;

	@Column(name = "email_id", nullable = false, length = 60)
	@NotEmpty
	@NotBlank
	@Size(max = 60)
	private String emailId;

	@Column(name = "first_name", nullable = false, length = 60)
	@NotEmpty
	@NotBlank
	@Size(max = 60)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 60)
	@NotEmpty
	@NotBlank
	@Size(max = 60)
	private String lastName;

	public Boolean getActiveIndicator() {
		return activeIndicator;
	}

	// public Collection<Address> getAddresses() {
	// return addresses;
	// }

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

	// public void setAddresses(Collection<Address> addresses) {
	// this.addresses = addresses;
	// }

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
