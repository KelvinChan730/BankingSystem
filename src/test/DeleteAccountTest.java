package test;

import main.account.Account;
import main.account.BaseAccount;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.db.AccountList;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteAccountTest {
	@Test
	public void testDeleteAccount_01() {
		// add normal account
		//BankAPI bank = new BankAPI();//
		// get next sequence
		String expectedSeq = AccountListSequencer.getInstance().getSequence();
		String username = "Jacky";
		String password = "123456";
		String phoneNo = "12345678";

		// add account
		AccountInfo para = new AccountInfo(username, password, phoneNo);
		boolean result = Bank.addAccount(para);

		// check account added
		assertTrue(result);
		assertTrue(AccountList.hasAccount(expectedSeq));

		// check added account content
		BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
		assertTrue(createdAcc instanceof Account);
		assertTrue(createdAcc.getOwner() == username);
		assertTrue(createdAcc.getPassword() == password);
		assertTrue(createdAcc.getPhoneNo() == phoneNo);

		// delete account
		boolean result2 = Bank.deleteAccount(expectedSeq, password);
		assertTrue(result2);
		assertFalse(AccountList.hasAccount(expectedSeq));
	}
}
