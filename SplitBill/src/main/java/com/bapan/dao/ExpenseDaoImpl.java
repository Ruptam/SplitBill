package com.bapan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.entity.Expense;
import com.bapan.entity.ExpenseEntity;
import com.bapan.entity.Registration;
import com.bapan.entity.RegistrationPk;
import com.bapan.entity.Transaction;

import com.bapan.model.ExpencesModel;
import com.bapan.model.ExpenseDetailsModel;
import com.bapan.model.RegistrationModel;
import com.bapan.model.SummaryDetails;
import com.bapan.model.SummaryModel;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	WebApplicationContext context;
	
	@Transactional
	public boolean saveExpense(List<ExpenseEntity> expenseEntitys) {
		for(ExpenseEntity expenseEntity:expenseEntitys) {
			sessionFactory.getCurrentSession().save(expenseEntity);
		}
		return true;
	}

	
	public List<Expense> retrieveAllExpenses(RegistrationModel regdSessionModel) {
		String sql= " SELECT * FROM USREXP WHERE USRIDR = ? ORDER BY EXPDAT DESC ";
		List<Expense> expenses = template.query(sql,new Object[]{regdSessionModel.getRegIdr()},
				new RowMapper<Expense>() {

						
						public Expense mapRow(ResultSet rs, int arg1)
								throws SQLException {
							Expense expense = (Expense) context.getBean("expense");
							
							expense.setAmount(rs.getDouble("EXPAMT"));
							expense.setDate(rs.getDate("EXPDAT"));
							expense.setRemarks(rs.getString("RMK"));
							expense.setUserIdr(rs.getString("USRIDR"));
							expense.setExpenseId(rs.getString("EXPIDR"));
							return expense;
						}
					});
		return expenses;
	}

	
	@Transactional
	public SummaryModel calculateSummary(String regIdr) {
		String sql	= " SELECT SUM(TRN.SHR) FROM EXPTRN TRN, USREXP EXP, USREXP_EXPTRN USRTRN WHERE EXP.EXPIDR=USRTRN.USREXP_EXPIDR " +
					  " AND USRTRN.TRANSACTIONS_TXNIDR=TRN.TXNIDR AND EXP.USRIDR = ? ";
		String amountOwed = String.valueOf(template.queryForObject(sql, Double.class, regIdr));
		String sqlForOwe="SELECT SUM(SHR) FROM EXPTRN WHERE REFUSRIDR = ? ";
		String amountOwe = String.valueOf(template.queryForObject(sqlForOwe, Double.class, regIdr));
		SummaryModel summaryModel = (SummaryModel) context.getBean("summaryModel");
		summaryModel.setOwe(amountOwe);
		summaryModel.setOwed(amountOwed);
		return summaryModel;
	}

	
	@Transactional
	public boolean saveExpense(Expense expense) {
		
		sessionFactory.getCurrentSession().persist(expense);
		Expense expense1= (Expense) sessionFactory.getCurrentSession().get(Expense.class, "2404165727");
		return true;
	}
	
	public List<ExpenseDetailsModel> viewExpenseDetails(String userId) {
		String sql=" SELECT C.PHNNUM,C.REGIDR,C.EML,C.USRNAM,SHR FROM CUSDTL C, USREXP_EXPTRN UE, EXPTRN TRN " +
					" WHERE UE.TRANSACTIONS_TXNIDR=TRN.TXNIDR AND TRN.REFUSRIDR=C.REGIDR AND USREXP_EXPIDR = ? ";
		
		List<ExpenseDetailsModel> expensesModels = template.query(sql,new Object[]{userId},
				new RowMapper<ExpenseDetailsModel>() {

						
						public ExpenseDetailsModel mapRow(ResultSet rs, int arg1)
								throws SQLException {
							ExpenseDetailsModel expencesModel = (ExpenseDetailsModel) context.getBean("expenseDetailsModel");
							
							expencesModel.setShare(String.valueOf(rs.getDouble("SHR")));
							expencesModel.setEmail(rs.getString("EML"));
							expencesModel.setPhoneNumber(rs.getString("PHNNUM"));
							expencesModel.setRegistrationId(rs.getString("REGIDR"));
							expencesModel.setUserName(rs.getString("USRNAM"));
							return expencesModel;
						}
					});
		return expensesModels;
		
	}

	
	public List<SummaryDetails> owedSummaryDetails(String userId) {
		String owedSummaryDetails=" SELECT TRN.REFUSRIDR,CUS.USRNAM,CUS.PHNNUM,CUS.EML,ROUND(TRN.SHR,2) SHR FROM EXPTRN TRN, USREXP EXP, " +
									" USREXP_EXPTRN USRTRN, CUSDTL CUS WHERE EXP.EXPIDR=USRTRN.USREXP_EXPIDR " +
									" AND USRTRN.TRANSACTIONS_TXNIDR=TRN.TXNIDR AND TRN.REFUSRIDR = CUS.REGIDR " + 
									" AND EXP.USRIDR = ? ";
		
		List<SummaryDetails> summaryDetails = template.query(owedSummaryDetails,new Object[]{userId},
				new RowMapper<SummaryDetails>() {

						
						public SummaryDetails mapRow(ResultSet rs, int arg1)
								throws SQLException {
							SummaryDetails summaryDetail = (SummaryDetails) context.getBean("summaryDetail");
							
							summaryDetail.setShare(String.valueOf(rs.getDouble("SHR")));
							summaryDetail.setEmail(rs.getString("EML"));
							summaryDetail.setPhoneNumber(rs.getString("PHNNUM"));
							summaryDetail.setReferalUserIdr(rs.getString("REFUSRIDR"));
							summaryDetail.setUserName(rs.getString("USRNAM"));
							return summaryDetail;
						}
					});
		return summaryDetails;
	}

	
	public List<SummaryDetails> owedTotalSummaryDetails(String userId) {
		String owedSummaryDetails=" SELECT TRN.REFUSRIDR,CUS.USRNAM,CUS.PHNNUM,CUS.EML,ROUND(SUM(TRN.SHR),2) SHR FROM EXPTRN TRN, USREXP EXP, " +
								  " USREXP_EXPTRN USRTRN, CUSDTL CUS WHERE EXP.EXPIDR=USRTRN.USREXP_EXPIDR " + 
								  " AND USRTRN.TRANSACTIONS_TXNIDR=TRN.TXNIDR AND TRN.REFUSRIDR = CUS.REGIDR " +  
								  " AND EXP.USRIDR = ? GROUP BY  CUS.USRNAM,CUS.PHNNUM,TRN.REFUSRIDR,CUS.EML ";

			List<SummaryDetails> summaryDetails = template.query(owedSummaryDetails,new Object[]{userId},
			new RowMapper<SummaryDetails>() {
			
				
				public SummaryDetails mapRow(ResultSet rs, int arg1)
						throws SQLException {
					SummaryDetails summaryDetail = (SummaryDetails) context.getBean("summaryDetail");
					
					summaryDetail.setShare(String.valueOf(rs.getDouble("SHR")));
					summaryDetail.setEmail(rs.getString("EML"));
					summaryDetail.setPhoneNumber(rs.getString("PHNNUM"));
					summaryDetail.setReferalUserIdr(rs.getString("REFUSRIDR"));
					summaryDetail.setUserName(rs.getString("USRNAM"));
					return summaryDetail;
				}
			});
			return summaryDetails;
	}

}
