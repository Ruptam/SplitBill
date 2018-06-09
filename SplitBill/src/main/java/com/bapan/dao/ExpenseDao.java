package com.bapan.dao;

import java.util.List;

import com.bapan.entity.Expense;
import com.bapan.entity.ExpenseEntity;

import com.bapan.model.ExpenseDetailsModel;
import com.bapan.model.RegistrationModel;
import com.bapan.model.SummaryDetails;
import com.bapan.model.SummaryModel;

public interface ExpenseDao {
	/**
	 * 
	 * @param expenseEntity
	 */
	boolean saveExpense(List<ExpenseEntity> expenseEntity);
	/**
	 * 
	 * @param regdSessionModel
	 * @return
	 */
	List<Expense> retrieveAllExpenses(RegistrationModel regdSessionModel);
	/**
	 * 
	 * @param regIdr
	 * @return
	 */
	SummaryModel calculateSummary(String regIdr);
	/**
	 * 
	 * @param expenseEntity
	 * @return
	 */
	boolean saveExpense(Expense expense);
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
