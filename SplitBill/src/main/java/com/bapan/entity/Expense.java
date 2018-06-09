package com.bapan.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="USREXP")
public class Expense {
	/**
	 * 
	 */
	private String expenseId;
	/**
	 * 
	 */
	private Date date;
	/**
	 * 
	 */
	private double amount;
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
	private List<Transaction> transactions;
	/**
	 * @return the expenseId
	 */
	@Id
	@Column(name="EXPIDR")
	public String getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the date
	 */
	@Column(name="EXPDAT")
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
	 * @return the amount
	 */
	@Column(name="EXPAMT")
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
	 * @return the remarks
	 */
	@Column(name="RMK")
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
	@Column(name="USRIDR")
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
	 * @return the transactions
	 */
	@OneToMany(cascade=CascadeType.ALL)
	public List<Transaction> getTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", date=" + date
				+ ", amount=" + amount + ", remarks=" + remarks + "]";
	}
	
}
