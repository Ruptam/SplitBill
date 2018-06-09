package com.bapan.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bapan.entity.FriendsPk;
import com.bapan.entity.Registration;
import com.bapan.exceptions.AlreadyFriendException;
import com.bapan.exceptions.UserNotRegisteredException;
import com.bapan.model.RegistrationModel;

public interface FriendService {
	/**
	 * 
	 * @param model
	 * @param friendOdIdr
	 * @return
	 * @throws UserNotRegisteredException
	 */
	FriendsPk addFriendToContact(RegistrationModel model, String friendOdIdr) throws UserNotRegisteredException,  AlreadyFriendException;
	/**
	 * 
	 * @param regIdr
	 * @return
	 */
	List<RegistrationModel> showAllFriends(String regIdr);
	
}
