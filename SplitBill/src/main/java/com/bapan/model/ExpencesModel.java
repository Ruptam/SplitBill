package com.bapan.model;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ExpencesModel {
	/**
	 * 
	 */
	private String amount;
	/**
	 * 
	 */
	private String[] regIdr;
	/**
	 * 
	 */
	private String remarks;
	/**
	 * 
	 */
	private String userIdr;
	/**
	 * 
	 */
	private String referenceUserIdr;
	/**
	 * 
	 */
	private Date date;
	/**
	 * 
	 */
	private String expenseId;
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the regIdr
	 */
	public String[] getRegIdr() {
		return regIdr;
	}

	/**
	 * @param regIdr the regIdr to set
	 */
	public void setRegIdr(String[] regIdr) {
		this.regIdr = regIdr;
	}

	
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	/**
	 * @return the userIdr
	 */
	public String getUserIdr() {
		return userIdr;
	}

	/**
	 * @param userIdr the userIdr to set
	 */
	public void setUserIdr(String userIdr) {
		this.userIdr = userIdr;
	}

	/**
	 * @return the referenceUserIdr
	 */
	public String getReferenceUserIdr() {
		return referenceUserIdr;
	}

	/**
	 * @param referenceUserIdr the referenceUserIdr to set
	 */
	public void setReferenceUserIdr(String referenceUserIdr) {
		this.referenceUserIdr = referenceUserIdr;
	}

	

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the expenseId
	 */
	public String getExpenseId() {
		return expenseId;
	}

	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpencesModel [amount=" + amount + ", regIdr="
				+ Arrays.toString(regIdr) + "]";
	}

}
