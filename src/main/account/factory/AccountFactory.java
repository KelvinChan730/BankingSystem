package main.account.factory;

import main.account.Account;

public class AccountFactory extends AbstractAccountFactory<Account, AccountInfo> {
	private static AccountFactory instance = null;

	private AccountFactory() {
	}

	public static AccountFactory getInstance() {
		if (instance == null)
			instance = new AccountFactory();
		return instance;
	}

	@Override
	public Account createAccount(AccountInfo accInfo) {
		String accNo = this.getSequence();
		return new Account(accNo, accInfo);
	}
}