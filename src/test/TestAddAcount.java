package test;

import bank.Bank;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import account.factory.AccountPara;

public class TestAddAcount {
	@Test
	public void testAddAccount_NormalCase() {
		Bank bank = new Bank();
		AccountPara para = new AccountPara("Jacky", "123456", "12345678");
		boolean result = bank.addAccount(para);
		assertTrue(result);
	}
}
