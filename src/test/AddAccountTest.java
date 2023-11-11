package test;

import main.account.BaseAccount;
import main.account.ForeignCurrencyAccount;
import main.account.SavingAccount;
import main.account.factory.AccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.Bank;
import main.constant.Currency;
import main.db.AccountList;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddAccountTest {
	@Test
	// test normal account
	public void testAddAccount01() {
        // add account
        AccountInfo accInfo = new AccountInfo("Jacky", "123456", "12345678");
        TestHelper.createTestAccount(accInfo);
	}
	
	@Test
	// test normal account
	public void testAddAccount02() {
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
		assertFalse(Bank.addAccount((AccountInfo)null));

        assertFalse(AccountList.hasAccount(expectedSeq)); // if cannot add account, then it will be false

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertNull(createdAcc);
	}

    @Test
    // test saving account
    public void testAddAccount03() {
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";
        BigDecimal targetAmt = new BigDecimal("50000");

        SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
        boolean result = Bank.addAccount(para);
        assertTrue(result);

        assertTrue(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof SavingAccount);
        assertEquals(createdAcc.getOwner(), username);
        assertEquals(createdAcc.getPassword(), password);
        assertEquals(createdAcc.getPhoneNo(), phoneNo);
        assertEquals(((SavingAccount) createdAcc).getTargetAmount(), targetAmt);
    }
    
    @Test
    // test saving account
    public void testAddAccount04() {
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        assertFalse(Bank.addAccount((SavingAccountInfo)null));

        assertFalse(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertNull(createdAcc);
    }

    @Test
    // test foreign currency account
    public void testAddAccount05() {
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";
        Currency currency = Currency.CAD;

        ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
        boolean result = Bank.addAccount(para);
        assertTrue(result);

        assertTrue(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof ForeignCurrencyAccount);
        assertEquals(createdAcc.getOwner(), username);
        assertEquals(createdAcc.getPassword(), password);
        assertEquals(createdAcc.getPhoneNo(), phoneNo);
        assertEquals(((ForeignCurrencyAccount) createdAcc).getCurrencyType(), currency);
    }
    
    @Test
    // test foreign currency account
    public void testAddAccount06() {
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        assertFalse(Bank.addAccount((ForeignCurrencyAccountInfo) null));

        assertFalse(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertNull(createdAcc);
    }
}
