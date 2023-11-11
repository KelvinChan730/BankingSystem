package test;

import main.account.Account;
import main.account.BaseAccount;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.db.AccountList;
import main.db.AccountListSequencer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHelper {
    public static Account createTestAccount(AccountInfo accInfo) {
        // add normal account
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        boolean result = Bank.addAccount(accInfo);

        // check account added
        assertTrue(result);
        assertTrue(AccountList.hasAccount(expectedSeq));

        // check added account content
        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof Account);
        assertEquals(createdAcc.getOwner(), accInfo.owner);
        assertEquals(createdAcc.getPassword(), accInfo.password);
        assertEquals(createdAcc.getPhoneNo(), accInfo.phoneNo);

        // return
        return (Account)createdAcc;
    }
}
