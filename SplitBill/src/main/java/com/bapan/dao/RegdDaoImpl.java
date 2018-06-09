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

import com.bapan.entity.Registration;
import com.bapan.entity.RegistrationPk;


@Repository
public class RegdDaoImpl implements RegistrationDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	RegistrationPk registrationPk;
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	WebApplicationContext context;
	

	@Transactional
	public RegistrationPk saveUserDetails(Registration regd) {
		return (RegistrationPk)sessionFactory.getCurrentSession().save(regd);
	}
	
	
	
	public boolean checkEmailExists(String email) {
		//String emailStatus = null;
		String sql="SELECT COUNT(*) FROM CUSDTL WHERE EML = ? ";
		Integer emailStatus = template.queryForObject(sql, Integer.class, email);
		if (emailStatus == 1) {
			return true;
		} else {
			return false;
		}
		
	}


	
	public boolean checkPhoneNumberExists(String phnno) {
		String sql="SELECT COUNT(*) FROM CUSDTL WHERE PHNNUM = ? ";
		Integer phnNumberStatus = template.queryForObject(sql, Integer.class, phnno);
		if (phnNumberStatus == 1) {
			return true;
		} else {
			return false;
		}
	}


	
	public List<Registration> searchDetails(Registration regd) {
		String sql="SELECT * FROM CUSDTL WHERE PHNNUM = ? AND PWD = ? ";
		
		List<Registration> regds = template.query(sql,new Object[]{regd.getRegistrationPK().getPhoneNumber(),regd.getPassword()},
				new RowMapper<Registration>() {

						/*
						public CustomerEntity mapRow(ResultSet rs, int rownum)
								throws SQLException {
							CustomerEntity csEnt = (CustomerEntity) context.getBean("cEntity");
							csEnt.setCustomerAddress(rs.getString("CUSADR"));
							csEnt.setCustomerEmail(rs.getString("CUSEML"));
							csEnt.setCustomerId(rs.getString("CUSIDR"));
							csEnt.setCustomerName(rs.getString("CUSNAM"));
							csEnt.setCustomerNumber(rs.getString("CNTNUM"));
							csEnt.setPassword(rs.getString("CUSPWD"));
							return csEnt;
						}*/

						
						public Registration mapRow(ResultSet rs, int arg1)
								throws SQLException {
							Registration regd = (Registration) context.getBean("registration");
							RegistrationPk regdPk = (RegistrationPk) context.getBean("registrationPk");
							regdPk.setPhoneNumber(rs.getString("PHNNUM"));
							regdPk.setRegistrationId(rs.getString("REGIDR"));
							regd.setEmail(rs.getString("EML"));
							regd.setName(rs.getString("USRNAM"));
							regd.setRegistrationPK(regdPk);
							return regd;
						}
					});
		
		return regds;
	}

}
