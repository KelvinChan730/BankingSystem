package main.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.constant.AccountType;
import main.constant.Currency;
import main.utility.Formatter;

public abstract class BaseAccount {
	protected String accNo; // account number
	protected BigDecimal balance; // balance
	protected List<String> loanRecordId = new ArrayList<>();

	abstract public AccountType getType();

	public Currency getCurrencyType() {
		return Currency.HKD;
	}
	
	public String getAccNo() {
		return accNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void addLoanRecordId(String loanId) {
		loanRecordId.add(loanId);
	}

	public List<String> getLoanRecordId() {
		return loanRecordId;
	}

	abstract public String getOwner();

	abstract public void setOwner(String owner);

	abstract public String getPassword();

	abstract public void setPassword(String password);

	abstract public String getPhoneNo();

	abstract public void setPhoneNo(String phoneNo);
	
	@Override
	public String toString() {
		return String.format("Account type: %s | Account number: %s | Account holder: %s | Balance: %s",
				getType().name(), getAccNo(), getOwner(), Formatter.formatBalance(getBalance()));
	}
}
