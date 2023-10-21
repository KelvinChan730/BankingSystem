package test;

import static org.junit.Assert.*;
import org.junit.Test;

import account.AccountList;
import bank.Bank;

public class TestAddAcount {
	@Test
	public void testAddAccount_1() {
		Bank bank = new Bank();
		boolean result = bank.addAccount("Jacky", "10000", "123456");
		assertTrue(result);
	}
	@Test
	public void testAddAccount_2() {
		Bank bank = new Bank();
		boolean result = bank.addAccount("Jacky+", "10000", "123456");
		assertEquals(false,result);
	}
	
	@Test
	public void testAddAccount_3() {
		Bank bank = new Bank();
		boolean result = bank.addAccount("Jacky", "string", "123456");
		assertEquals(false,result);
	}
	
	@Test
	public void testAddAccount_4() {
		Bank bank = new Bank();
		boolean result = bank.addAccount("Jacky", "10000", "123456|||");
		assertEquals(false,result);
	}
	
	@Test
	public void testAccountWithdraw_1() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0001", "123456", "10000");
		assertEquals(true,result);
	}
	@Test
	public void testAccountWithdraw_2() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0001", "123456", "100000");
		assertEquals(false,result);
	}
	@Test
	public void testAccountWithdraw_3() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0001", "123456", "p100000");
		assertEquals(false,result);
	}
	@Test
	public void testAccountWithdraw_4() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("+0001", "123456", "10000");
		assertEquals(false,result);
	}
	@Test
	public void testAccountWithdraw_5() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0001", "()123456", "10000");
		assertEquals(false,result);
	}
	@Test
	public void testAccountWithdraw_6() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0001", "1234567", "10000");
		assertEquals(false,result);
	}
	@Test
	public void testAccountWithdraw_7() {
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.withdraw("0002", "1234567", "10000");
		assertEquals(false,result);
	}
}
