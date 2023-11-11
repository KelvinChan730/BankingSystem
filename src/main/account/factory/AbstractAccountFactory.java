package main.account.factory;

import main.account.IAccount;
import main.db.AccountListSequencer;

public abstract class AbstractAccountFactory<T extends IAccount, P extends IAccountPara<T>> {
	protected AccountListSequencer sequencer = AccountListSequencer.getInstance();
	
	public abstract T createAccount(P accInfo);
}
