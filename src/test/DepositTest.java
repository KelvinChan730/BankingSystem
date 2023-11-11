package test;

import main.account.Account;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositTest {
    @Test
    public void testDeposit_NormalCase() {
        // create account info
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";
        AccountInfo accInfo = new AccountInfo(username, password, phoneNo);

        // create test account
        Account account = TestHelper.createTestAccount(accInfo);

        // deposit
        BigDecimal amount = new BigDecimal("10000");
        assertTrue(Bank.deposit(account, amount));
        assertEquals(amount, account.getBalance());
    }
}
