package com.bapan.model;

import org.springframework.stereotype.Component;

@Component
public class SummaryDetails {
	/**
	 * 
	 */
	private String referalUserIdr;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String phoneNumber;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String share;
	/**
	 * @return the referalUserIdr
	 */
	public String getReferalUserIdr() {
		return referalUserIdr;
	}
	/**
	 * @param referalUserIdr the referalUserIdr to set
	 */
	public void setReferalUserIdr(String referalUserIdr) {
		this.referalUserIdr = referalUserIdr;
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
		return "SummaryDetails [referalUserIdr=" + referalUserIdr
				+ ", userName=" + userName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", share=" + share + "]";
	}
	
}
