package com.revature.services;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class TransactionsLogic {

	
	public void transDisplay(ArrayBlockingQueue<String> qu) {
		System.out.println("Recent Transactions:");
		System.out.println("--------------------");
		for(String trans : qu) {
			System.out.println("*  " + trans);
			System.out.println("------");
		}
		System.out.println("--------------------");
	}
	
	public static String transactionTime() {
		Date dateObj = new Date();
		String dateFormat = "EEEE, MMM d, y      h:mm:ss a ";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		return simpleDF.format(dateObj);
	}
	
}
