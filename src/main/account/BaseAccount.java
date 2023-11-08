package main.account;

import java.math.BigDecimal;
import java.util.ArrayList;

import main.constant.AccountType;
import main.constant.Currency;

public abstract class BaseAccount {
	protected final AccountType accountType = null; // account type
	protected final Currency currencytype = Currency.HKD; // currency type HKD
	protected String accNo; // account number
	protected BigDecimal balance; // balance
	protected ArrayList<String> loanRecordId = new ArrayList<String>();

	public String getAccNo() {
		return accNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return accountType;
	}

	public Currency getCurrencyType() {
		return currencytype;
	}

	public void addLoanRecordId(String loanId) {
		loanRecordId.add(loanId);
	}

	public ArrayList<String> getLoanRecordId() {
		return loanRecordId;
	}

	public abstract String getPassword();
}
