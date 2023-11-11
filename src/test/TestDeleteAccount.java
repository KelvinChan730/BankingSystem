package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import main.account.factory.AccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.Bank;
import main.constant.Currency;
import main.db.AccountListSequencer;

public class TestDeleteAccount {

	@Test
	// test delete normal account
	public void testDeleteAccount01() {
		//BankAPI bank = new BankAPI();//
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		AccountInfo para = new AccountInfo(username, password, phoneNo);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, password);
		assertEquals(true, result);
	}

	@Test
	// test delete normal account
	public void testDeleteAccount02() {
		//BankAPI bank = new BankAPI();//
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		AccountInfo para = new AccountInfo(username, password, phoneNo);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, "kkkkk");
		assertEquals(false, result);
	}

	@Test
	// test delete normal account
	public void testDeleteAccount03() {
		//BankAPI bank = new BankAPI();//
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		AccountInfo para = new AccountInfo(username, password, phoneNo);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount("9999", password);
		assertEquals(false, result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount04() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, password);
		assertEquals(true, result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount05() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, "jjjj");
		assertEquals(false, result);
	}

	@Test
	// test delete saving account
	public void testDeleteAccount06() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		BigDecimal targetAmt = new BigDecimal("50000");

		SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount("9999", password);
		assertEquals(false, result);
	}

	@Test
	// test delete foreign currency account
	public void testDeleteAccount07() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, password);
		assertEquals(true, result);
	}

	@Test
	// test delete foreign currency account
	public void testDeleteAccount08() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount(expectedSeq, "jjjj");
		assertEquals(false, result);
	}
	
	@Test
	// test delete foreign currency account
	public void testDeleteAccount09() {
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";
		Currency currency = Currency.CAD;

		ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
		Bank.addAccount(para);
		boolean result = Bank.deleteAccount("9999", password);
		assertEquals(false, result);
	}
}
