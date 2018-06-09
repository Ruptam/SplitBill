package com.bapan.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.dao.ExpenseDao;
import com.bapan.entity.Expense;
import com.bapan.entity.ExpenseEntity;
import com.bapan.entity.Transaction;

import com.bapan.model.ExpencesModel;
import com.bapan.model.ExpenseDetailsModel;
import com.bapan.model.RegistrationModel;
import com.bapan.model.SummaryDetails;
import com.bapan.model.SummaryModel;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Autowired
	Expense expense;
	
	
	
	public boolean processExpense(String usrId, ExpencesModel expenseModel) {
		//Double amnt=);
		double perPersonCosts=Double.parseDouble(expenseModel.getAmount())/(expenseModel.getRegIdr().length+1);
		expense.setExpenseId(generateId());
		expense.setDate(new java.util.Date());
		expense.setAmount(Double.parseDouble(expenseModel.getAmount()));
		expense.setRemarks(expenseModel.getRemarks());
		expense.setUserIdr(usrId);
		List<Transaction> transactions=new ArrayList<Transaction>();
		for(String s : expenseModel.getRegIdr()){
			Transaction transaction = (Transaction) context.getBean("transaction");
			transaction.setReferalUserId(s);
			transaction.setShare(perPersonCosts);
			transactions.add(transaction);
		}
		expense.setTransactions(transactions);
		return expenseDao.saveExpense(expense);
	}
	
	
	
	
	private String generateId() {
		Calendar now = Calendar.getInstance();
		
		Formatter dd=new Formatter();
		dd.format("%td", now);
		
		Formatter mm=new Formatter();
		mm.format("%tm", now);
		
		Formatter hour = new Formatter();
		hour.format("%tH", now);

		Formatter min = new Formatter();
		min.format("%tM", now);

		Formatter sec = new Formatter();
		sec.format("%tS", now);
		
		return dd.toString()+mm.toString()+hour.toString()+min.toString()+sec.toString();
		
	}




	
	public List<ExpencesModel> retrieveAllExpenses(RegistrationModel regdModel) {
		List<Expense> expenses = expenseDao.retrieveAllExpenses(regdModel);
		List<ExpencesModel> expensesModels = new ArrayList<ExpencesModel>();
		for(Expense expense : expenses) {
			ExpencesModel expencesModel = (ExpencesModel) context.getBean("expenseModel");
			expencesModel.setAmount(String.valueOf(expense.getAmount()));
			expencesModel.setRemarks(expense.getRemarks());
			expencesModel.setDate(expense.getDate());
			expencesModel.setExpenseId(expense.getExpenseId());
			expencesModel.setUserIdr(expense.getUserIdr());
			expensesModels.add(expencesModel);
		}
		return expensesModels;
	}




	
	public SummaryModel calculateSummary(String regIdr) {
		SummaryModel summaryModel = expenseDao.calculateSummary(regIdr);
		if(summaryModel.getOwe() == "null") {
			summaryModel.setOwe("0");
		}
		if(summaryModel.getOwed() == "null") {
			summaryModel.setOwed("0");
		}
		return summaryModel;
	}




	
	public List<ExpenseDetailsModel> viewExpenseDetails(String userId) {
		return expenseDao.viewExpenseDetails(userId);
	}




	
	public List<SummaryDetails> owedSummaryDetails(String userId) {
		return expenseDao.owedSummaryDetails(userId);
	}




	
	public List<SummaryDetails> owedTotalSummaryDetails(String userId) {
		return expenseDao.owedTotalSummaryDetails(userId);
	}

}
