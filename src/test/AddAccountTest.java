import main.account.Account;
import main.account.BaseAccount;
import main.account.ForeignCurrencyAccount;
import main.account.SavingAccount;
import main.account.factory.AccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.BankAPI;
import main.constant.Currency;
import main.db.AccountList;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddAccountTest {
	@Test
	public void testAddAccount_NormalCase() {
		BankAPI bank = new BankAPI();
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";

		AccountInfo para = new AccountInfo(username, password, phoneNo);
		boolean result = bank.addAccount(para);
		assertTrue(result);

        assertTrue(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof Account);
        assertTrue(createdAcc.getOwner() == username);
        assertTrue(createdAcc.getPassword() == password);
        assertTrue(createdAcc.getPhoneNo() == phoneNo);
	}

    @Test
    public void testAddSavingAccount_NormalCase() {
        BankAPI bank = new BankAPI();
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";
        BigDecimal targetAmt = new BigDecimal("50000");

        SavingAccountInfo para = new SavingAccountInfo(username, password, phoneNo, targetAmt);
        boolean result = bank.addAccount(para);
        assertTrue(result);

        assertTrue(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof SavingAccount);
        assertTrue(createdAcc.getOwner() == username);
        assertTrue(createdAcc.getPassword() == password);
        assertTrue(createdAcc.getPhoneNo() == phoneNo);
        assertTrue(((SavingAccount) createdAcc).getTargetAmount() == targetAmt);
    }

    @Test
    public void testAddForeignCurrencyAccount_NormalCase() {
        BankAPI bank = new BankAPI();
        // get next sequence
        String expectedSeq = AccountListSequencer.getInstance().getSequence();
        String username = "Jacky";
        String password = "123456";
        String phoneNo = "12345678";
        Currency currency = Currency.CAD;

        ForeignCurrencyAccountInfo para = new ForeignCurrencyAccountInfo(username, password, phoneNo, currency);
        boolean result = bank.addAccount(para);
        assertTrue(result);

        assertTrue(AccountList.hasAccount(expectedSeq));

        BaseAccount createdAcc = AccountList.findAccount(expectedSeq);
        assertTrue(createdAcc instanceof ForeignCurrencyAccount);
        assertTrue(createdAcc.getOwner() == username);
        assertTrue(createdAcc.getPassword() == password);
        assertTrue(createdAcc.getPhoneNo() == phoneNo);
        assertTrue(((ForeignCurrencyAccount) createdAcc).getCurrencyType() == currency);
    }
}
