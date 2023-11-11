package test;

import main.account.Account;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.bank.BankAPI;
import main.bank.operation.TransferOperation;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferTest {
    @Test
    public void testDeposit_NormalCase() {
        // add account
        String accNo1 = AccountListSequencer.getInstance().getSequence();
        AccountInfo accInfo1 = new AccountInfo("Jacky1", "123456", "12345678");
        Account acc1 = TestHelper.createTestAccount(accInfo1);

        String accNo2 = AccountListSequencer.getInstance().getSequence();
        AccountInfo accInfo2 = new AccountInfo("Jacky2", "483752", "97328231");
        Account acc2 = TestHelper.createTestAccount(accInfo2);

        // set balance
        BigDecimal amount = new BigDecimal("10000");
        acc1.setBalance(amount);
        assertEquals(amount, acc1.getBalance());

        // transfer amount to account 2
        TransferOperation transfer = new TransferOperation(accNo2, amount);
        assertTrue(Bank.transfer(acc1, transfer));
        assertEquals(BigDecimal.ZERO, acc1.getBalance());
        assertEquals(amount, acc2.getBalance());
    }
}
