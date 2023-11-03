package main.account.factory;

import main.account.Account;
import main.account.ForeignCurrencyAccount;
import main.account.SavingAccount;
import main.utility.Formatter;

public abstract class AccountFactory {
	private static int sequence = 1;
	
	private static String getSequence() {
		return Formatter.formatSequence(sequence++);
	}
	
	public static int getIntSequence() {
		return sequence;
	}
	
	public static Account createAccount(AccountPara para) {
		String accNo = getSequence();
		return new Account(accNo, para);
	}
	
	public static SavingAccount createSavingAccount(SavingAccountPara para) {
		String accNo = getSequence();
		return new SavingAccount(accNo, para);
	}
	
	public static ForeignCurrencyAccount createForeignCurrencyAccount(ForeignCurrencyAccountPara para) {
		String accNo = getSequence();
		return new ForeignCurrencyAccount(accNo, para);
	}
}
