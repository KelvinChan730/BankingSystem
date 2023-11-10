package main.account;

import java.math.BigDecimal;

import main.account.factory.ForeignCurrencyAccountInfo;
import main.constant.AccountType;
import main.constant.Currency;

public class ForeignCurrencyAccount extends BaseAccount implements IAccount {
	private ForeignCurrencyAccountInfo accountInfo;

	public ForeignCurrencyAccount(String accNo, ForeignCurrencyAccountInfo accInfo) {
		this.accNo = accNo;
		this.balance = BigDecimal.ZERO;
		this.accountInfo = accInfo;
	}

	@Override
	public AccountType getType() {
		return AccountType.FOREIGN_CURRENCY;
	}

	@Override
	public Currency getCurrencyType() {
		return accountInfo.currencyType;
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

	// basic information of the foreign currency account.
	@Override
	public String toString() {
		return super.toString() + String.format(" | Currency Type: %s\n", getCurrencyType());
	}
}
