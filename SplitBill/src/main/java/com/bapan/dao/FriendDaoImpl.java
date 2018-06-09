package com.bapan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.entity.Friends;
import com.bapan.entity.FriendsPk;
import com.bapan.entity.Registration;
import com.bapan.entity.RegistrationPk;
import com.bapan.model.RegistrationModel;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	WebApplicationContext context;

	
	public String hasFriendRegistered(RegistrationModel model) {
		
		String sql=" SELECT REGIDR FROM CUSDTL WHERE PHNNUM = '" + model.getPhoneNumber()+ "' ";
		/*String friendStatus = template.queryForObject(sql, String.class, model.getPhoneNumber());
		if(friendStatus != null) {
			return friendStatus;
		} else {
			return null;
		}*/
		String friendStatus=template.query(sql, new ResultSetExtractor<String>() {
			
			public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				return rs.next() ? rs.getString("REGIDR") : null;
			}
	    });
		return friendStatus;
	}

	
	public FriendsPk saveFriendShip(Friends friends) {
		return (FriendsPk) sessionFactory.getCurrentSession().save(friends) ;
	}

	
	public List<Registration> showAllFriends(String regIdr) {
		String sql="SELECT USRNAM,EML,PHNNUM,REGIDR FROM CUSDTL C, FRNLST F WHERE C.REGIDR=F.FRNIDR and FRNDOFIDR = ? ";
		
		List<Registration> regds = template.query(sql,new Object[]{regIdr},
				new RowMapper<Registration>() {

						
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

	
	public boolean isFriend(String regIdr,String friendOfId) {
		String sql="SELECT COUNT(*) FROM FRNLST WHERE FRNIDR = '"+regIdr+"'"+" AND FRNDOFIDR = ? ";
		Integer noOfFriends = template.queryForObject(sql, Integer.class,friendOfId);
		if (noOfFriends == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
