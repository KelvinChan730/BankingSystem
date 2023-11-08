package main.account.factory;

import main.account.IAccount;
import main.db.SequencedRecordCreator;

public abstract class AbstractAccountFactory<T extends IAccount, P extends IAccountPara<T>> extends SequencedRecordCreator {
	
	public abstract T createAccount(P accInfo);
}
