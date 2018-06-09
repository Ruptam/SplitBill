package com.bapan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EXPTRN")
public class Transaction {
	/**
	 * 
	 */
	private int transactionId;
	/**
	 * 
	 */
	private double share;
	/**
	 * 
	 */
	private String referalUserId;
	/**
	 * @return the transactionId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TXNIDR")
	public int getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the share
	 */
	@Column(name="SHR")
	public double getShare() {
		return share;
	}
	/**
	 * @param share the share to set
	 */
	public void setShare(double share) {
		this.share = share;
	}
	/**
	 * @return the referalUserId
	 */
	@Column(name="REFUSRIDR")
	public String getReferalUserId() {
		return referalUserId;
	}
	/**
	 * @param referalUserId the referalUserId to set
	 */
	public void setReferalUserId(String referalUserId) {
		this.referalUserId = referalUserId;
	}
	
}
