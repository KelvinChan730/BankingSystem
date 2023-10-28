package test;

import bank.Bank;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestAddAcount {
	@Test
	public void testAddAccount_1() {
		Bank bank = new Bank();
		boolean result = bank.addAccount("Jacky", "10000", "123456");
		assertTrue(result);
	}
	@Test
	public void testAddAccount_2() {
//		Bank bank = new Bank();
//		boolean result = bank.addAccount("Jacky+", "10000", "123456");
//		assertFalse(result);
	}
	
	@Test
	public void testAddAccount_3() {
//		Bank bank = new Bank();
//		boolean result = bank.addAccount("Jacky", "string", "123456");
//		assertFalse(result);
	}
	
	@Test
	public void testAddAccount_4() {
//		Bank bank = new Bank();
//		boolean result = bank.addAccount("Jacky", "10000", "123456|||");
//		assertFalse(result);
	}
}
