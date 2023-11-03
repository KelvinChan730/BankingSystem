package main.account.factory;

import main.account.Account;
import main.account.ForeignCurrencyAccount;
import main.account.SavingAccount;
import main.db.Sequenced;

public class AccountFactory extends Sequenced {
	private static AccountFactory instance = null;
	
	private AccountFactory() {}
	
	public static AccountFactory getInstance() {
		if (instance == null)
			instance = new AccountFactory();
		return instance;
	}
	
	public Account createAccount(AccountPara para) {
		String accNo = getSequence();
		return new Account(accNo, para);
	}
	
	public SavingAccount createSavingAccount(SavingAccountPara para) {
		String accNo = getSequence();
		return new SavingAccount(accNo, para);
	}
	
	public ForeignCurrencyAccount createForeignCurrencyAccount(ForeignCurrencyAccountPara para) {
		String accNo = getSequence();
		return new ForeignCurrencyAccount(accNo, para);
	}
}
