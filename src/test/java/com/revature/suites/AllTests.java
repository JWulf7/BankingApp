package com.revature.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.revature.models.BankAccountTest;
import com.revature.services.*;



@RunWith(Suite.class)
@SuiteClasses({BankAccountTest.class, AccountsLogicTest.class, BankTest.class, CustomerLogicTest.class, EmployeeAdminLogicTest.class, TransactionsLogicTest.class})
public class AllTests {

}