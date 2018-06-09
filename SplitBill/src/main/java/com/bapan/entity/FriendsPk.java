package com.bapan.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FriendsPk implements Serializable {
	/**
	 * 
	 */
	private String friendIdr;
	/**
	 * 
	 */
	private String friendOf;
	/**
	 * @return the friendshipIdr
	 */
	public String getFriendIdr() {
		return friendIdr;
	}
	/**
	 * @param friendshipIdr the friendshipIdr to set
	 */
	public void setFriendIdr(String friendIdr) {
		this.friendIdr = friendIdr;
	}
	/**
	 * @return the friendOf
	 */
	public String getFriendOf() {
		return friendOf;
	}
	/**
	 * @param friendOf the friendOf to set
	 */
	public void setFriendOf(String friendOf) {
		this.friendOf = friendOf;
	}
	
}
