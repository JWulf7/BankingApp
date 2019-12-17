package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransactionsLogicTest {



	@Test
	public void testTransactionTime() {
		TransactionsLogic tLogic = new TransactionsLogic();
		assertNotNull(tLogic.transactionTime());
	}

}
