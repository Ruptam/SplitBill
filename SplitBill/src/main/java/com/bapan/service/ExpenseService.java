package com.bapan.service;

import java.util.List;

import com.bapan.entity.Expense;

import com.bapan.model.ExpencesModel;
import com.bapan.model.ExpenseDetailsModel;
import com.bapan.model.RegistrationModel;
import com.bapan.model.SummaryDetails;
import com.bapan.model.SummaryModel;

public interface ExpenseService {
	/**
	 * 
	 * @param usrId
	 * @param expenseModel
	 */
	boolean processExpense(String usrId, ExpencesModel expenseModel);
	/**
	 * 
	 * @param regdModel
	 */
	List<ExpencesModel> retrieveAllExpenses(RegistrationModel regdModel);
	/**
	 * 
	 * @param regIdr
	 * @return
	 */
	SummaryModel calculateSummary(String regIdr);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<ExpenseDetailsModel> viewExpenseDetails(String userId);
	/**
	 * 
	 * @param usrIdr
	 * @return
	 */
	List<SummaryDetails> owedSummaryDetails(String userId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<SummaryDetails> owedTotalSummaryDetails(String userId);
}
