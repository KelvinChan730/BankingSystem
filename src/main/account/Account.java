package main.account;

import main.account.factory.AbstractAccountPara;
import main.constant.AccountType;
import main.constant.Currency;
import main.utility.Formatter;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account implements IAccount{
	protected final AccountType accountType = AccountType.NORMAL;
    protected String accNo;							// account number
    protected String owner;							// owner
    protected BigDecimal balance;					// balance
    protected String password;						// password
    protected String phoneNo;						// phone number
    protected final Currency currencytype = Currency.HKD;
    protected ArrayList<String> loanRecordId = new ArrayList<String>();
    
    public Account(String accNo, AbstractAccountPara para) {
    	this.accNo = accNo;
        this.owner = para.owner;
        this.balance = BigDecimal.ZERO;
        this.password = para.password;
        this.phoneNo = para.phoneNo;
    }

    // Getter/setter for each property of the generic account class.
    public String getAccNo() {
        return accNo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void addLoanRecord(String loanId) {
    	loanRecordId.add(loanId);
    }
    
    public ArrayList<String> getLoanRecordId(){
    	return loanRecordId;
    }

	// basic information of the account.
    @Override
    public String toString(){
        return String.format("Account type: %s | Account number: %s | Account holder: %s | Balance: %s",
                accountType.name(), accNo, owner, Formatter.formatBalance(balance));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accNo == null) ? 0 : accNo.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        return result;
    }
    
}
