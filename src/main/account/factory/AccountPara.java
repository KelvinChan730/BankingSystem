package main.account.factory;

import main.account.Account;

public class AccountPara extends AbstractAccountPara implements IAccountPara<Account> {
	public AccountPara(String owner, String password, String phoneNo) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
	}
}