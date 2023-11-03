package account.factory;

import java.text.DecimalFormat;

import account.Account;
import account.ForeignCurrencyAccount;
import account.SavingAccount;

public abstract class AccountFactory {
	private static int sequence = 1;
	
	private static String getSequence() {
		return String.format(new DecimalFormat("0000").format(sequence++));
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
