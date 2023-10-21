package test;

import account.Account;
import account.AccountList;
import account.ForeignCurrencyAccount;
import account.SavingAccount;
import bank.Bank;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

public class TestAccountDeletion {
	@Test
	public void testAccountDeletion_1() { // correct account deletion
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.deleteAccount("0001", "123456");
		assertEquals(true, result);
	}

	@Test
	public void testAccountDeletion_2() { // incorrect accNo, accNo not exist
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.deleteAccount("0002", "123456");
		assertEquals(false, result);
	}

	@Test
	public void testAccountDeletion_3() { // incorrect password
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.deleteAccount("0001", "1234567");
		assertEquals(false, result);
	}
	
	@Test
	public void testAccountDeletion_4() { // incorrect accNo format
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.deleteAccount("++0001", "1234567");
		assertEquals(false, result);
	}
	
	@Test
	public void testAccountDeletion_5() { // incorrect password format
		Bank bank = new Bank();
		bank.addAccount("Jacky", "10000", "123456");
		boolean result = bank.deleteAccount("0001", "+++++1234567");
		assertEquals(false, result);
	}
}
