package test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import main.account.factory.AccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.Bank;
import main.constant.Currency;
import main.db.AccountListSequencer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDeleteAccount {

	@Test
	// test delete normal account
	public void testDeleteAccount01() {
		// add account
		String password = "123456";
		AccountInfo accInfo = new AccountInfo("Jacky", password, "12345678");
		String accNo = AccountListSequencer.getInstance().getSequence();
		TestHelper.createTestAccount(accInfo);

		boolean result = Bank.deleteAccount(accNo, "123456");
		assertTrue(result);
	}

	@Test
	// test delete normal account
	public void testDeleteAccount02() {
		// add account
		AccountInfo accInfo = new AccountInfo("Jacky", "123456", "12345678");
		String accNo = AccountListSequencer.getInstance().getSequence();
		TestHelper.createTestAccount(accInfo);

		boolean result = Bank.deleteAccount(accNo, "kkkkk");
		assertFalse(result);
	}

	@Test
	// test delete normal account
	public void testDeleteAccount03() {
		// add account
		String password = "123456";
		AccountInfo accInfo = new AccountInfo("Jacky", password, "12345678");
		TestHelper.createTestAccount(accInfo);

		boolean result = Bank.deleteAccount("9999", password);
		assertFalse(result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount04() {
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, password);
		assertTrue(result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount05() {
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, "jjjj");
		assertFalse(result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount06() {
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount("9999", password);
		assertFalse(result);
	}

	@Test
	// test delete foreign currency account
	public void testDeleteAccount07() {
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, password);
		assertTrue(result);
	}

	@Test
	// test delete foreign currency account
	public void testDeleteAccount08() {
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, "jjjj");
		assertFalse(result);
	}
	
	@Test
	// test delete foreign currency account
	public void testDeleteAccount09() {
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount("9999", password);
		assertFalse(result);
	}
}
