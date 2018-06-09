package com.bapan.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.bapan.dao.RegistrationDao;
import com.bapan.entity.Registration;
import com.bapan.entity.RegistrationPk;
import com.bapan.exceptions.DuplicateEmailExistsException;
import com.bapan.exceptions.DuplicatePhoneExistsException;
import com.bapan.model.RegistrationModel;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	Registration registration;
	
	@Autowired
	RegistrationPk registrationPk;
	
	@Autowired
	RegistrationDao registrationDao;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	RegistrationModel regdModel;
	
	
	public String saveUserDetails(RegistrationModel regdModel) throws DuplicateEmailExistsException, DuplicatePhoneExistsException {
		boolean emailStatus=registrationDao.checkEmailExists(regdModel.getEmail());
		if(emailStatus) {
			throw new DuplicateEmailExistsException("Duplicate Email");
		}
		boolean phnNumber=registrationDao.checkPhoneNumberExists(regdModel.getPhoneNumber());
		if(phnNumber) {
			throw new DuplicatePhoneExistsException("Duplicate Contact Number");
		}
		registration.setEmail(regdModel.getEmail());
		registration.setName(regdModel.getName());
		registration.setPassword(encryptPassword(regdModel.getPassword()));
		registrationPk.setRegistrationId(generateId());
		registrationPk.setPhoneNumber(regdModel.getPhoneNumber());
		registration.setRegistrationPK(registrationPk);
		registrationPk = registrationDao.saveUserDetails(registration);
		return registrationPk.getRegistrationId();
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
	
	private String encryptPassword(String pswd) {
		String generatedPassword = null;
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pswd.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return generatedPassword;
	}


	
	public List<RegistrationModel> searchDetails(RegistrationModel regdModel) {
		Registration regdBean=(Registration) context.getBean("registration");
		RegistrationPk regdPkBean = (RegistrationPk) context.getBean("registrationPk");
		regdPkBean.setPhoneNumber(regdModel.getPhoneNumber());
		regdBean.setPassword(encryptPassword(regdModel.getPassword()));
		regdBean.setRegistrationPK(regdPkBean);
		List<RegistrationModel> regdModels=new ArrayList<RegistrationModel>();
		List<Registration> registrationEntitys=registrationDao.searchDetails(regdBean);
		for(Registration regdEntity: registrationEntitys) {
			//SampleModel model=(SampleModel) context.getBean("sampleModel");
			regdModel=entityToModel(regdEntity);
			regdModels.add(regdModel);
		}
		return regdModels;
	}
	
	
	private RegistrationModel entityToModel(Registration regd) {
		RegistrationModel regdModel=(RegistrationModel) context.getBean("regdModel");
		regdModel.setRegIdr(regd.getRegistrationPK().getRegistrationId());
		regdModel.setEmail(regd.getEmail());
		regdModel.setName(regd.getName());
		regdModel.setPhoneNumber(regd.getRegistrationPK().getPhoneNumber());
		return regdModel;
	}
}
