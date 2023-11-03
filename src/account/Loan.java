package account;

import java.math.BigDecimal;

public class Loan {
	protected String accountId;
	protected BigDecimal loanAmount;
	protected boolean payBack;
	
	public Loan(String accountId, BigDecimal loanAmount) {
		this.accountId = accountId;
		this.loanAmount = loanAmount;
		payBack = false;
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