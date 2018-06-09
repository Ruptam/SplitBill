package com.bapan.service;

import java.util.List;

import com.bapan.entity.RegistrationPk;
import com.bapan.exceptions.DuplicateEmailExistsException;
import com.bapan.exceptions.DuplicatePhoneExistsException;
import com.bapan.model.RegistrationModel;

public interface RegistrationService {
	/**
	 * 
	 * @param regdModel
	 * @return
	 * @throws DuplicateEmailExistsException
	 * @throws DuplicatePhoneExistsException
	 */
	String saveUserDetails(RegistrationModel regdModel) throws DuplicateEmailExistsException, DuplicatePhoneExistsException;
	/**
	 * 
	 * @param regdModel
	 * @return
	 */
	List<RegistrationModel> searchDetails(RegistrationModel regdModel);
}
