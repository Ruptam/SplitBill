package com.bapan.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import javax.persistence.Table;


@Entity
@Table(name="CUSDTL")
public class Registration {
	/**
	 * 
	 */
	private RegistrationPk registrationPK;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String email;
	
	/**
	 * 
	 */
	private String password;

	/**
	 * @return the registrationPK
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="registrationId",column=@Column(name="REGIDR",length=20)),
		@AttributeOverride(name="phoneNumber", column=@Column(name="PHNNUM",length=10))
	})
	public RegistrationPk getRegistrationPK() {
		return registrationPK;
	}
	/**
	 * @param registrationPK the registrationPK to set
	 */
	public void setRegistrationPK(RegistrationPk registrationPK) {
		this.registrationPK = registrationPK;
	}
	/**
	 * @return the name
	 */
	@Column(name="USRNAM",length=20)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	@Column(name="EML",length=20)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	@Column(name="PWD",length=40)
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

}
