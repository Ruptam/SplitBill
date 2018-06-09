package com.bapan.model;

import org.springframework.stereotype.Component;

@Component
public class RegistrationModel {
	/**
	 * 
	 */
	private String regIdr;
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
	private String phoneNumber;
	/**
	 * 
	 */
	private String password;
	
	/**
	 * @return the regIdr
	 */
	public String getRegIdr() {
		return regIdr;
	}
	/**
	 * @param regIdr the regIdr to set
	 */
	public void setRegIdr(String regIdr) {
		this.regIdr = regIdr;
	}
	/**
	 * @return the name
	 */
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the password
	 */
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
