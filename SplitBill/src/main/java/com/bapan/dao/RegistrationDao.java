package com.bapan.dao;

import java.util.List;

import com.bapan.entity.Registration;
import com.bapan.entity.RegistrationPk;

public interface RegistrationDao {
	/**
	 * 
	 * @param regd
	 */
	RegistrationPk saveUserDetails(Registration regd);
	/**
	 * 
	 * @param email
	 * @return
	 */
	boolean checkEmailExists(String email);
	/**
	 * 
	 * @param phnno
	 * @return
	 */
	boolean checkPhoneNumberExists(String phnno);
	/**
	 * 
	 * @param regd
	 * @return
	 */
	List<Registration> searchDetails(Registration regd);
}
