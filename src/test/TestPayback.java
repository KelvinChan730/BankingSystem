package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import account.AccountList;
import bank.Bank;

public class TestPayback {
	@Test
	public void testPayback_1() { // wrong loanId
		Bank bank = new Bank();
		bank.addAccount("Jacky", "1000000", "123456");
		bank.loan("0001", "123456", "10000");
		boolean result = bank.payBack("0001", "123456", "0002");
		assertFalse(result);
	}
	
	@Test
	public void testPayback_2() { //payback = 1100000
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000000", "123456");
		bank.loan("0001", "123456", "1000000");
		boolean result = bank.payBack("0001", "123456", "0001");
		assertTrue(result);
	}
	
	@Test
	public void testPayback_3() { //payback = 600000
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000000", "123456");
		bank.loan("0001", "123456", "500000");
		boolean result = bank.payBack("0001", "123456", "0001");
		assertTrue(result);
	}
	
	@Test
	public void testPayback_4() { //payback = 130000
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000000", "123456");
		bank.loan("0001", "123456", "10000");
		boolean result = bank.payBack("0001", "123456", "0001");
		assertTrue(result);
	}
	
	@Test
	public void testPayback_5() { //payback = 1100000
		Bank bank = new Bank();
		bank.addAccount("Jacky", "1000000", "123456");
		bank.loan("0001", "123456", "500000");
		AccountList.findAccount("0001").setBalance(new BigDecimal("100000"));
		boolean result = bank.payBack("0001", "123456", "0005");
		System.out.println(result);
		assertFalse(result);
	}
}
