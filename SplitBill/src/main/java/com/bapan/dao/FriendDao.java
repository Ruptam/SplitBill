package com.bapan.dao;

import java.util.List;

import com.bapan.entity.Friends;
import com.bapan.entity.FriendsPk;
import com.bapan.entity.Registration;
import com.bapan.model.RegistrationModel;

public interface FriendDao {
	/**
	 * 
	 * @param model
	 * @return
	 */
	String hasFriendRegistered(RegistrationModel model);
	/**
	 * 
	 * @param friends
	 * @return
	 */
	FriendsPk saveFriendShip(Friends friends);
	/**
	 * 
	 * @param regIdr
	 * @return
	 */
	List<Registration> showAllFriends(String regIdr);
	/**
	 * 
	 * @param regIdr
	 * @return
	 */
	boolean isFriend(String regIdr,String friendOfId);
}
