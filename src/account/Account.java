package account;

import bank.Bank;
import constant.AccountType;
import constant.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;

import account.factory.AbstractAccountPara;

public class Account implements IAccount{
	protected final AccountType type = AccountType.NORMAL;
    protected String accNo;							// account number
    protected String owner;							// owner
    protected BigDecimal balance;					// balance
    protected String password;						// password
    protected String phoneNo;						// phone number
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
        return type;
    }
    
    public Currency getCurrencyType() {
        return Currency.HKD;
    }
    
    public String getAccountPassword() {
    	return password;
    }
    
    public void setAccountPassword(String password) {
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
                type.name(), accNo, owner, Bank.df.format(balance));
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
