package main.account.factory;

import main.account.Account;

public class AccountInfo extends BaseAccountInfo implements IAccountPara<Account> {
	public AccountInfo(String owner, String password, String phoneNo) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
	}
}