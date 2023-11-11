package test;

import main.account.Account;
import main.account.BaseAccount;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.bank.BankAPI;
import main.db.AccountList;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawAccountTest {
	@Test
	public void testWithdrawAccount_NormalCase() {
        // add account
        AccountInfo accInfo = new AccountInfo("Jacky", "123456", "12345678");
        String accNo = AccountListSequencer.getInstance().getSequence();
        Account account = TestHelper.createTestAccount(accInfo);

        // set balance
        BigDecimal originalBalance = new BigDecimal("10000");
        account.setBalance(originalBalance);

        // withdraw
        BigDecimal withdrawAmount = new BigDecimal("1000");
        assertTrue(Bank.withdraw(account, withdrawAmount));
        assertEquals(account.getBalance(), originalBalance.subtract(withdrawAmount));
	}
}
