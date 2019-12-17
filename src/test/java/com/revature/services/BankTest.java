package com.revature.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	
	
	/**
	 * ******************USERNAMEISACCEPTABLE**************
	 */
	@Test
	public void userNameShort() {
		Bank bank = new Bank();
		assertFalse(bank.userNameIsAcceptable("T"));
	}
	@Test
	public void userNameLong() {
		Bank bank = new Bank();
		assertFalse(bank.userNameIsAcceptable("1111111111111TTTTTTTTTTTTTTTTT"
				+ "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrTTTTTTTTTTTTTTTTTT" +
		"TTTTTTTTTTTTTTTTTTTTTvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
		+ "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"));
	}
	@Test
	public void userNameGood() {
		Bank bank = new Bank();
		assertTrue(bank.userNameIsAcceptable("TaTaTaTaTa"));
	}
	@Test
	public void userNameGoodNumbers() {
		Bank bank = new Bank();
		assertTrue(bank.userNameIsAcceptable("4839388f"));
	}
	@Test
	public void userNameEmpty() {
		Bank bank = new Bank();
		assertFalse(bank.userNameIsAcceptable(""));
	}

	
	/**
	 * ***********************USERPASSWORDISACCEPTABLE****
	 */
	@Test
	public void userPasswordIsAcceptableShort() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable("1nF"));
	}
	@Test
	public void userPasswordIsAcceptableLong() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable("1nFFFFFFFFF"
				+ "jfskdlfjskdfjkdsfljskfjskdfjsklfjdkfljskdfjsl"
				+ "123212674637463274263748263478267811227897128"
				+ "FJISDFJSLDJFSKDLFJISLJFISDJFIEJILDSKSJLJD"
				+ "hjkhf7438ghuirhgh7438hruihfduifh48hsuihusidhf8"));
	}
	@Test
	public void userPasswordIsAcceptableNoNums() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable("GoodStuff"));
	}
	@Test
	public void userPasswordIsAcceptableNoCaps() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable("123fourfivesix"));
	}
	@Test
	public void userPasswordIsAcceptableNoLower() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable("123FOURFIVESIX"));
	}
	@Test
	public void userPasswordIsAcceptableGood() {
		Bank bank = new Bank();
		assertTrue(bank.userPasswordIsAcceptable("123FOURFIvesix"));
	}
	@Test
	public void userPasswordIsAcceptableEmpty() {
		Bank bank = new Bank();
		assertFalse(bank.userPasswordIsAcceptable(""));
	}
	
	

}
