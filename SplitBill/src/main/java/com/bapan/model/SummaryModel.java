package com.bapan.model;

import org.springframework.stereotype.Component;

@Component
public class SummaryModel {
	/**
	 * 
	 */
	private String owe;
	/**
	 * 
	 */
	private String owed;
	/**
	 * 
	 */
	private String regIdr;
	/**
	 * 
	 */
	private String phoneNumber;

	/**
	 * @return the owe
	 */
	public String getOwe() {
		return owe;
	}
	/**
	 * @param owe the owe to set
	 */
	public void setOwe(String owe) {
		this.owe = owe;
	}
	/**
	 * @return the owed
	 */
	public String getOwed() {
		return owed;
	}
	/**
	 * @param owed the owed to set
	 */
	public void setOwed(String owed) {
		this.owed = owed;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SummaryModel [owe=" + owe + ", owed=" + owed + ", regIdr="
				+ regIdr + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
