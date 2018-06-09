package com.bapan.exceptions;

public class AlreadyFriendException extends Exception {
	/**
	 * 
	 */
	private String detail;
	/**
	 * 
	 * @param s
	 */
	public AlreadyFriendException(String s) {
		this.detail=s;
	}
	
	public String toString() {
		return "AlreadyAddedAsFriendInList --> "+detail;
	}

}
