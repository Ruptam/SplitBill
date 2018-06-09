package com.bapan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bapan.exceptions.AlreadyFriendException;
import com.bapan.exceptions.UserNotRegisteredException;
import com.bapan.model.RegistrationModel;
import com.bapan.service.FriendService;
import com.google.gson.Gson;


@Controller
public class FriendController {
	
	@Autowired
	RegistrationModel regdModel;
	
	
	
	@Autowired
	FriendService friendService;
	
	@Autowired
	Gson gson;
	
	@RequestMapping(value="addFriendtoContact",method=RequestMethod.POST)
	public @ResponseBody String addFriend(@RequestBody String c, HttpSession session) {
		final String status="success";
		final String invalid="Invalid";
		final String alreadyRegistered="Registered";
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		System.out.println(c);
		regdModel.setPhoneNumber(c);
		try {
			friendService.addFriendToContact(regdModel, regdSessionModel.getRegIdr());
			return "{\"status\":\""+status+"\"}";
		} catch (UserNotRegisteredException e) {
			e.printStackTrace();
			return "{\"status\":\""+invalid+"\"}";
		} catch (AlreadyFriendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{\"status\":\""+alreadyRegistered+"\"}";
		}
		
	}
	
	@RequestMapping(value="showAllFriends",method=RequestMethod.GET)
	public @ResponseBody String showAllFriends(HttpSession session) {
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		List<RegistrationModel> friendModels=friendService.showAllFriends(regdSessionModel.getRegIdr());
		String listOfFriends=gson.toJson(friendModels);
		System.out.println(listOfFriends);
		return listOfFriends;
	}
}
