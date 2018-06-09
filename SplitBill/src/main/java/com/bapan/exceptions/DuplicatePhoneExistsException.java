package com.bapan.exceptions;

public class DuplicatePhoneExistsException extends Exception{
	/**
	 * 
	 */
	private String detail;
	/**
	 * 
	 * @param s
	 */
	public DuplicatePhoneExistsException(String s) {
		this.detail=s;
	}
	public String toString() {
		return "DuplicateContactNumberExistsException --> "+detail;
	}
}
