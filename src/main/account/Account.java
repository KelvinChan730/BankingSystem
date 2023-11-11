package main.account;

import main.constant.AccountType;
import main.account.factory.AccountInfo;

import java.math.BigDecimal;

public class Account extends BaseAccount implements IAccount {
	private AccountInfo accountInfo; // account info

	public Account(String accNo, AccountInfo accInfo) {
		this.accNo = accNo;
		this.balance = BigDecimal.ZERO;
		this.accountInfo = accInfo;
	}

	@Override
	public AccountType getType() {
		return AccountType.NORMAL;
	}

	@Override
	public String getOwner() {
		return accountInfo.owner;
	}

	@Override
	public void setOwner(String owner) {
		this.accountInfo.owner = owner;
	}

	@Override
	public String getPassword() {
		return accountInfo.password;
	}

	@Override
	public void setPassword(String password) {
		this.accountInfo.password = password;
	}

	@Override
	public String getPhoneNo() {
		return accountInfo.phoneNo;
	}

	@Override
	public void setPhoneNo(String phoneNo) {
		this.accountInfo.phoneNo = phoneNo;
	}

	// basic information of the account.
	@Override
	public String toString() {
		return super.toString();
	}

}
