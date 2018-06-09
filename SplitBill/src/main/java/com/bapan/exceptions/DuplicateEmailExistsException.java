package com.bapan.exceptions;

public class DuplicateEmailExistsException extends Exception{
	/**
	 * 
	 */
	private String detail;
	/**
	 * 
	 * @param s
	 */
	public DuplicateEmailExistsException(String s) {
		this.detail=s;
	}
	
	public String toString() {
		return "DuplicateEmailExistsException --> "+detail;
	}
	
}
