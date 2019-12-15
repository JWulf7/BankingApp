package com.revature.repositories;

import java.util.Queue;

import org.apache.log4j.Logger;

public class TransactionsDAOImpl implements TransactionsDAO{

	private static Logger logger = Logger.getLogger(TransactionsDAOImpl.class);

	@Override
	public boolean addTransaction(String time, int actNo, String descrip) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Queue<String> getTransactions(int actno) {
		// TODO Auto-generated method stub
		return null;
	}
}
