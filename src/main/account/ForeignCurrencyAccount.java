package main.account;

import java.math.BigDecimal;

import main.account.factory.ForeignCurrencyAccountInfo;
import main.constant.AccountType;
import main.constant.Currency;

public class ForeignCurrencyAccount extends BaseAccount implements IAccount {
	protected final AccountType accountType = AccountType.FOREIGN_CURRENCY; // account type = foreign currency
	private Currency currencyType; // currency type
	private ForeignCurrencyAccountInfo accountInfo;

	public ForeignCurrencyAccount(String accNo, ForeignCurrencyAccountInfo accInfo) {
		this.accNo = accNo;
		this.balance = BigDecimal.ZERO;
		this.accountInfo = accInfo;
	}

	public Currency getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Currency currencyType) {
		this.currencyType = currencyType;
	}

	// basic information of the foreign currency account.
	@Override
	public String toString() {
		return super.toString() + String.format(" | Currency Type: %s\n", currencyType.getName());
	}

}
