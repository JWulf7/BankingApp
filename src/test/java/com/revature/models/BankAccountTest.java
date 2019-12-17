package com.revature.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountTest {

	BankAccount account;
	BankAccount accountTwo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		account = new BankAccount(7000, 7000);
		accountTwo = new BankAccount(9000, 7000);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testDepositGood() {
		assertEquals(300, account.deposit(300), 0);
	}
	@Test
	public void testDepositBad() {
		assertEquals(0, account.deposit(-150), 0);
	}

	@Test
	public void testWithdrawBad() {
		assertEquals(0, account.withdraw(9000),0);
	}
	@Test
	public void testWithdrawGood() {
		assertEquals(300, account.withdraw(300),300);
	}

	@Test
	public void testTransferBad() {
		assertEquals(0, account.transfer(7000, accountTwo),0);
	}

	@Test
	public void testTransferGood() {
		assertEquals(2500, account.transfer(2500, accountTwo),2500);
	}
}
