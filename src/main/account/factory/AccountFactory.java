package main.account.factory;

import main.account.Account;
import main.account.ForeignCurrencyAccount;
import main.account.SavingAccount;
import main.db.SequencedRecordCreator;

public class AccountFactory extends SequencedRecordCreator {
	private static AccountFactory instance = null;

	private AccountFactory() {
	}

	public static AccountFactory getInstance() {
		if (instance == null)
			instance = new AccountFactory();
		return instance;
	}

	public Account createAccount(AccountInfo accInfo) {
		String accNo = getSequence();
		return new Account(accNo, accInfo);
	}

	public SavingAccount createAccount(SavingAccountInfo accInfo) {
		String accNo = getSequence();
		return new SavingAccount(accNo, accInfo);
	}

	public ForeignCurrencyAccount createAccount(ForeignCurrencyAccountInfo accInfo) {
		String accNo = getSequence();
		return new ForeignCurrencyAccount(accNo, accInfo);
	}
}
