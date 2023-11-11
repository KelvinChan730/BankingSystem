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
		// add account
		String password = "123456";
		AccountInfo accInfo = new AccountInfo("Jacky", password, "12345678");
		String accNo = AccountListSequencer.getInstance().getSequence();
		TestHelper.createTestAccount(accInfo);

		// delete account
		boolean result2 = Bank.deleteAccount(accNo, password);
		assertTrue(result2);
		assertFalse(AccountList.hasAccount(accNo));
	}
}
