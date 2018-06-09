package com.bapan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.entity.Expense;

import com.bapan.model.ExpencesModel;
import com.bapan.model.RegistrationModel;
import com.bapan.model.SummaryDetails;
import com.bapan.model.SummaryModel;
import com.bapan.service.ExpenseService;
import com.google.gson.Gson;

@Controller
public class ExpenseController {
	
	@Autowired
	Gson gson;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	RegistrationModel regdModel;
	
	private List<SummaryDetails> summaryDtls;
	
	@RequestMapping(value="processExpense",method=RequestMethod.POST)
	public @ResponseBody String processExpense(@RequestBody String c, HttpSession session) {
		System.out.println(c);
		ExpencesModel expencesModel =gson.fromJson(c, ExpencesModel.class);
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		boolean status = expenseService.processExpense(regdSessionModel.getRegIdr(), expencesModel);
		if(status) {
			return "{\"status\":\""+status+"\"}";
		} else {
			return "{\"status\":\"Failed\"}";
		}
			
	}
	
	@RequestMapping(value="showRecentActivities",method=RequestMethod.GET)
	public @ResponseBody String showRecentActivities(HttpSession session) {
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		List<ExpencesModel> expensesModels=expenseService.retrieveAllExpenses(regdSessionModel);
		String c = gson.toJson(expensesModels);
		System.out.println(c);
		return c;
	}
	
	@RequestMapping(value="showSummary",method=RequestMethod.GET)
	public @ResponseBody String calculateSummary(HttpSession session) {
		regdModel = (RegistrationModel) session.getAttribute("user");
		SummaryModel summaryModel=expenseService.calculateSummary(regdModel.getRegIdr());
		String c=gson.toJson(summaryModel);
		return c;
	}
	
	@RequestMapping(value="viewExpenseDetails",method=RequestMethod.POST)
	public @ResponseBody String viewExpenseDetails(@RequestBody String expenceIdr) {
		//regdModel = (RegistrationModel) session.getAttribute("user");
		String c = gson.toJson(expenseService.viewExpenseDetails(expenceIdr));
		System.out.println(c);
		return c;
	}
	
	@RequestMapping(value="owedSummaryDetails",method=RequestMethod.GET)
	public @ResponseBody String owedSummaryDetails(HttpSession session) {
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		List<SummaryDetails> summaryDetails = expenseService.owedSummaryDetails(regdSessionModel.getRegIdr());
		String c = gson.toJson(summaryDetails);
		System.out.println(c);
		return c;
	}
	@RequestMapping(value="calculateOwedSummaryTotal",method=RequestMethod.GET)
	public @ResponseBody String calculateOwedSummaryTotal(HttpSession session) {
		RegistrationModel regdSessionModel = (RegistrationModel) session.getAttribute("user");
		return gson.toJson(expenseService.owedTotalSummaryDetails(regdSessionModel.getRegIdr()));
	}
}
