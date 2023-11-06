package test;

import main.account.factory.AccountInfo;
import main.bank.Bank;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TestAddAcount {
	@Test
	public void testAddAccount_NormalCase() {
		Bank bank = new Bank();
		AccountInfo para = new AccountInfo("Jacky", "123456", "12345678");
		boolean result = bank.addAccount(para);
		assertTrue(result);
	}
}
