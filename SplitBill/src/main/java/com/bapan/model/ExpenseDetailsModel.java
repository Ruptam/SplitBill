package com.bapan.model;

import org.springframework.stereotype.Component;

@Component
public class ExpenseDetailsModel {
	/**
	 * 
	 */
	private String phoneNumber;
	/**
	 * 
	 */
	private String registrationId;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String share;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the share
	 */
	public String getShare() {
		return share;
	}
	/**
	 * @param share the share to set
	 */
	public void setShare(String share) {
		this.share = share;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpenseDetailsModel [phoneNumber=" + phoneNumber
				+ ", registrationId=" + registrationId + ", email=" + email
				+ ", userName=" + userName + ", share=" + share + "]";
	}
	
}
