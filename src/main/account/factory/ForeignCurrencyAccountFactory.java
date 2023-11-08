package main.account.factory;

import main.account.ForeignCurrencyAccount;

public class ForeignCurrencyAccountFactory extends AbstractAccountFactory<ForeignCurrencyAccount, ForeignCurrencyAccountInfo> {
	private static ForeignCurrencyAccountFactory instance = null;

	private ForeignCurrencyAccountFactory() {
	}

	public static ForeignCurrencyAccountFactory getInstance() {
		if (instance == null)
			instance = new ForeignCurrencyAccountFactory();
		return instance;
	}

	@Override
	public ForeignCurrencyAccount createAccount(ForeignCurrencyAccountInfo accInfo) {
		String accNo = this.getSequence();
		return new ForeignCurrencyAccount(accNo, accInfo);
	}
}