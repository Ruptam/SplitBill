package com.bapan.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RegistrationPk implements Serializable{
	/**
	 * 
	 */
	private String registrationId;
	/**
	 * 
	 */
	private String phoneNumber;
	/**
	 * @return the registrationId
	 */
	public String getRegistrationId() {
		return registrationId;
	}
	/**
	 * @param registrationId the registrationId to set
	 */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
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
	

}
