package test;

import main.account.Account;
import main.account.BaseAccount;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.db.AccountList;
import main.db.AccountListSequencer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawAccountTest {
	@Test
	public void testWithdrawAccount_NormalCase() {
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

        // withdraw
        BigDecimal originalBalance = new BigDecimal("10000");
        BigDecimal withdrawAmount = new BigDecimal("1000");
        createdAcc.setBalance(originalBalance);
        assertTrue(Bank.withdraw(createdAcc, withdrawAmount));
        assertEquals(createdAcc.getBalance(), originalBalance.subtract(withdrawAmount));
	}
//	@Test
//	public void testAccountWithdraw_2() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("0001", "123456", "100000");
//		assertFalse(result);
//	}
//	@Test
//	public void testAccountWithdraw_3() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("0001", "123456", "p100000");
//		assertFalse(result);
//	}
//	@Test
//	public void testAccountWithdraw_4() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("+0001", "123456", "10000");
//		assertFalse(result);
//	}
//	@Test
//	public void testAccountWithdraw_5() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("0001", "()123456", "10000");
//		assertFalse(result);
//	}
//	@Test
//	public void testAccountWithdraw_6() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("0001", "1234567", "10000");
//		assertFalse(result);
//	}
//	@Test
//	public void testAccountWithdraw_7() {
//		Bank bank = new Bank();
//		Bank.addAccount("Jacky", "10000", "123456");
//		boolean result = Bank.withdraw("0002", "1234567", "10000");
//		assertFalse(result);
//	}
}
