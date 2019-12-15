package com.revature.repositories;

import java.util.Queue;

public interface TransactionsDAO {

	public boolean addTransaction(String time, int actNo, String descrip);
	
	public Queue<String> getTransactions(int actno);
}
