package com.bapan.util;

import java.util.Calendar;
import java.util.Formatter;

public class IdGenerator {
	
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
	
	public String generateFriendshipIdr() {
		return "FRI"+generateId();
	}

}
