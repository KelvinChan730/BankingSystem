package main.account;

import main.constant.AccountType;
import main.account.factory.AccountInfo;
import main.utility.Formatter;

import java.math.BigDecimal;

public class Account extends BaseAccount implements IAccount {
	private final AccountType accountType = AccountType.NORMAL; // account type normal
	private AccountInfo accountInfo; // account info

	public Account(String accNo, AccountInfo accInfo) {
		this.accNo = accNo;
		this.balance = BigDecimal.ZERO;
		this.accountInfo = accInfo;
	}

	public String getOwner() {
		return accountInfo.owner;
	}

	public void setOwner(String owner) {
		this.accountInfo.owner = owner;
	}

	@Override
	public String getPassword() {
		return accountInfo.password;
	}

	public void setPassword(String password) {
		this.accountInfo.password = password;
	}

	// basic information of the account.
	@Override
	public String toString() {
		return String.format("Account type: %s | Account number: %s | Account holder: %s | Balance: %s",
				accountType.name(), accNo, accountInfo.owner, Formatter.formatBalance(balance));
	}

}
