package account;

import bank.Bank;
import constant.Currency;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
    // The general account class has five attributes:
    protected String category;			// account type (N: deposit account, S: savings account)
    protected String accNo;				// account number
    protected String owner;				// owner
    protected BigDecimal balance;		// balance
    protected boolean isActive;			// activation status
    protected String password;			// new add
    protected ArrayList<String> loanRecordId = new ArrayList<String>();

    // Sets whether normal accounts are enabled to True
	// and the account type to "N" (meaning NORMAL).
    public Account() {
        isActive = true;
        category = "N";
    }

	protected Account(String category) {
        isActive = true;
        this.category = category;
    }
	
	public Account(String accNo, String owner, BigDecimal balance, String password) {
        this();
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
        this.password = password;
    }
	
	
    protected Account(String category, String accNo, String owner, BigDecimal balance, String password) {
        this(category);
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
        this.password = password;
    }

    // Getter/setter for each property of the generic account class.
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
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

    public boolean isActive(){
        return isActive;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public Currency getCurrencyType() {
        return Currency.HKD;
    }
    
    public String getAccountPassword() {
    	return password;
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
                category, accNo, owner, Bank.df.format(balance));
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
