package main.account;

import java.math.BigDecimal;

public class Loan {
	protected String accountId;
	protected BigDecimal loanAmount;
	protected boolean payBack = false;
	
	public Loan(String accountId, BigDecimal loanAmount) {
		this.accountId = accountId;
		this.loanAmount = loanAmount;
	}
	
	public void payBackSuccessful() {
		payBack = true;
	}
	
	public String getAccountId(){
		return accountId;
	}
	
	public BigDecimal getLoanAmount(){
		return loanAmount;
	}
	
	public boolean getPayBack() {
		return payBack;
	}
}
