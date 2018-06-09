package com.bapan.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.dao.FriendDao;
import com.bapan.entity.Friends;
import com.bapan.entity.FriendsPk;
import com.bapan.entity.Registration;
import com.bapan.exceptions.AlreadyFriendException;
import com.bapan.exceptions.UserNotRegisteredException;
import com.bapan.model.RegistrationModel;
import com.bapan.util.IdGenerator;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	FriendDao friendDao;
	
	@Autowired
	Friends friends;
	
	@Autowired
	FriendsPk friendPk;
	
	@Autowired
	WebApplicationContext context;

	
	


	
	public FriendsPk addFriendToContact(RegistrationModel model, String friendOfId)
			throws UserNotRegisteredException, AlreadyFriendException {
		String friendIdr=friendDao.hasFriendRegistered(model);
		if(friendIdr != null) {
			boolean isFriend = friendDao.isFriend(friendIdr, friendOfId);
			if(isFriend) {
				throw new AlreadyFriendException("Friend Added previously");
			} else {
				friendPk.setFriendIdr(friendIdr);
				friendPk.setFriendOf(friendOfId);
				friends.setFriendPk(friendPk);
				return friendDao.saveFriendShip(friends);
			}
		} else {
			throw new UserNotRegisteredException("Friend Not Registered to SplitBill");
		}
	}





	
	public List<RegistrationModel> showAllFriends(String regIdr) {
		List<Registration> friends = friendDao.showAllFriends(regIdr);
		List<RegistrationModel> friendModels=new ArrayList<RegistrationModel>();
		for(Registration friend : friends) {
			RegistrationModel friendModel = (RegistrationModel) context.getBean("regdModel");
			friendModel.setEmail(friend.getEmail());
			friendModel.setName(friend.getName());
			friendModel.setPhoneNumber(friend.getRegistrationPK().getPhoneNumber());
			friendModel.setRegIdr(friend.getRegistrationPK().getRegistrationId());
			friendModels.add(friendModel);
		}
		return friendModels;
	}


}
