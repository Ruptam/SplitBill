package com.bapan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bapan.exceptions.DuplicateEmailExistsException;
import com.bapan.exceptions.DuplicatePhoneExistsException;
import com.bapan.model.RegistrationModel;
import com.bapan.service.RegistrationService;
import com.google.gson.Gson;

@Controller
public class RegistrationController {
	
	@Autowired
	RegistrationService regdService;
	
	@Autowired
	Gson gson;
	
	@Autowired
	RegistrationModel regdModel;
	
	@RequestMapping(value="saveUserDetails",method=RequestMethod.POST)
	public @ResponseBody String processForm(@RequestBody String c) {
		regdModel=gson.fromJson(c, RegistrationModel.class);
		String registrationId=null;
		try {
			registrationId = regdService.saveUserDetails(regdModel);
			return "{\"RegistratonId\":\""+registrationId+"\"}";
		} catch (DuplicateEmailExistsException e) {
			System.out.println(e);
			return "{\"ValidEmail\":\""+null+"\"}";
		} catch (DuplicatePhoneExistsException p) {
			System.out.println(p);
			return "{\"ValidPhoneNumber\":\""+null+"\"}";
		}
		
	}
	
	
	@RequestMapping(value="authenticateUser",method=RequestMethod.POST)
	public @ResponseBody String searchDetails(@RequestBody String c, HttpSession session){
		regdModel=gson.fromJson(c, RegistrationModel.class);
		List<RegistrationModel> regdModels=regdService.searchDetails(regdModel);
		if (regdModels != null) {
			for(RegistrationModel regdmodel: regdModels) {
				if (regdmodel != null) {
					session.setAttribute("user", regdmodel);
					return gson.toJson(regdmodel);	
				} else {
					return "";
				}
			}
		}
		return null;
	}
}
