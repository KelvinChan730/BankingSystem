package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import account.AccountList;
import account.Loan;
import account.UserLoanList;
import bank.Bank;

// for checking
//Bank bank = new Bank();
//bank.addAccount("Jacky", "1000000", "123456");
//bank.loan("0001", "123456", "10000");
//System.out.println("origal balance: " + AccountList.findAccount("0001").getBalance());
//
//ArrayList<Loan> check0 = UserLoanList.findUserAllLoanRecord("0001");
//for (Loan element : check0) {
//	System.out.println(element.getLoanAmount() + " " + element.getPayBack());
//}
//System.out.println("------------------------After payback----------------------------");
//boolean result = bank.payBack("0001", "123456", "0001");
//
//check0 = UserLoanList.findUserAllLoanRecord("0001");
//for (Loan element : check0) {
//	System.out.println(element.getLoanAmount() + " " + element.getPayBack());
//}
//System.out.println("new balance: " + AccountList.findAccount("0001").getBalance());
//
//assertTrue(result);

public class TestPayback {
//	@Test
//	public void testLoan_1() { // wrong loanId
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "1000000", "123456");
//		bank.loan("0001", "123456", "10000");
//		boolean result = bank.payBack("0001", "123456", "0002");
//		assertFalse(result);
//	}
	
//	@Test
//	public void testLoan_2() { //payback = 1100000
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000000", "123456");
//		bank.loan("0001", "123456", "1000000");
//		boolean result = bank.payBack("0001", "123456", "0001");
//		assertTrue(result);
//	}
	
//	@Test
//	public void testLoan_3() { //payback = 600000
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000000", "123456");
//		bank.loan("0001", "123456", "500000");
//		boolean result = bank.payBack("0001", "123456", "0001");
//		assertTrue(result);
//	}
	
//	@Test
//	public void testLoan_3() { //payback = 130000
//		Bank bank = new Bank();
//		bank.addAccount("Jacky", "10000000", "123456");
//		bank.loan("0001", "123456", "10000");
//		boolean result = bank.payBack("0001", "123456", "0001");
//		assertTrue(result);
//	}
	
	@Test
	public void testLoan_4() { //payback = 1100000
		Bank bank = new Bank();
		bank.addAccount("Jacky", "1000000", "123456");
		bank.loan("0001", "123456", "500000");
		AccountList.findAccount("0001").setBalance(new BigDecimal("100000"));
		boolean result = bank.payBack("0001", "123456", "0001");
		assertFalse(result);
	}
}
