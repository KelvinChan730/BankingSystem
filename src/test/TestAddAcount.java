package test;

import static org.junit.Assert.*;
import org.junit.Test;

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
}
