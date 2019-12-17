package com.revature.repositories;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public interface TransactionsDAO {

	public boolean addTransaction(String time, int actNo, String descrip);
	
	public ArrayBlockingQueue<String> getTransactions(int actno);
}
