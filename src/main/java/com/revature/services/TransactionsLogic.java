package com.revature.services;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransactionsLogic {

	
	
	
	public static String transactionTime() {
		Date dateObj = new Date();
		String dateFormat = "EEEE, MMM d, y      h:mm:ss a ";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		return simpleDF.format(dateObj);
	}
	
}
