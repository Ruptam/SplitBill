package com.bapan.exceptions;

public class UserNotRegisteredException extends Exception {
	/**
	 * 
	 */
	private String detail;
	/**
	 * 
	 * @param s
	 */
	public UserNotRegisteredException(String s) {
		this.detail=s;
	}
	/***
	 * 
	 */
	public String toString() {
		return "UserNotRegisteredException --> "+detail;
	}

}
