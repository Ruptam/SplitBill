package com.bapan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EXPTRK")
public class ExpenseEntity {
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String referenceUserIdr;
	/**
	 * 
	 */
	private double amount;
	/**
	 * 
	 */
	private String date;
	/**
	 * 
	 */
	private int expenseId;
	/**
	 * 
	 */
	private String remarks;
	/**
	 * @return the userId
	 */
	@Column(name="USRIDR")
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the referenceUserIdr
	 */
	@Column(name="REFUSRIDR")
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
	 * @return the amount
	 */
	@Column(name="AMT")
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	@Column(name="EXPDAT")
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the expenseId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EXPIDR")
	public int getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the remarks
	 */
	@Column(name="REMRK")
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpenseEntity [userId=" + userId + ", referenceUserIdr="
				+ referenceUserIdr + ", amount=" + amount + ", date=" + date
				+ ", expenseId=" + expenseId + ", remarks=" + remarks + "]";
	}
	
}
