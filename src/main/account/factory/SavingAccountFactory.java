package main.account.factory;

import main.account.SavingAccount;

public class SavingAccountFactory extends AbstractAccountFactory<SavingAccount, SavingAccountInfo> {
	private static SavingAccountFactory instance = null;

	private SavingAccountFactory() {
	}

	public static SavingAccountFactory getInstance() {
		if (instance == null)
			instance = new SavingAccountFactory();
		return instance;
	}

	@Override
	public SavingAccount createAccount(SavingAccountInfo accInfo) {
		String accNo = this.getSequence();
		return new SavingAccount(accNo, accInfo);
	}
}