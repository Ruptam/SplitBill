package com.bapan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bapan.model.RegistrationModel;
import com.google.gson.Gson;

@Controller
public class BaseController {
	
	@Autowired
	Gson gson;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView displayHomePage() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	
	@RequestMapping(value="sessionValidation",method=RequestMethod.GET)
	public @ResponseBody String sessionValidation(HttpSession session) {
		try {
			RegistrationModel regdModel = (RegistrationModel) session.getAttribute("user");
			if(regdModel != null) {	
				return gson.toJson(regdModel);
			}
			else{
				return "";
			}
		}catch (Exception e){
			System.out.println("Error in Session" + e);
			return "";
		}
	}
	
	@RequestMapping(value="sessionInvalidate", method=RequestMethod.GET)
	public @ResponseBody String sessionInvalidate(HttpSession session) {
		session.invalidate();
		return null;
	}
}
