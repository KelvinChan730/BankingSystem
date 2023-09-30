package account;

import bank.Bank;
import exceptions.funcException.DepositException;
import exceptions.funcException.TransferException;
import exceptions.funcException.WithdrawException;

import java.math.BigDecimal;

public class Account {
    // The general account class has five attributes:
    protected String category;			// account type (N: deposit account, S: savings account)
    protected String accNo;				// account number
    protected String owner;				// owner
    protected BigDecimal balance;		// balance
    protected boolean isActive;			// activation status

    // Sets whether normal accounts are enabled to True
	// and the account type to "N" (meaning NORMAL).
    public Account() {
        isActive = true;
        category = "N";
    }
    public Account(String accNo, String owner, BigDecimal balance) {
        this();
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
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

	// Print the basic information of the account.
    public void getAccountInfo(){
        System.out.printf("Account type: %s | Account number: %s | Account holder: %s | Balance: %s원\n",
                category, accNo, owner, Bank.df.format(balance));
    }
    

	// Withdraw an amount of money
    public BigDecimal withdraw(BigDecimal amount) throws WithdrawException {
        if(this.balance.compareTo(amount) < 0){
            throw new WithdrawException("잔액이 모자랍니다.");
        }else{
            this.balance = this.balance.subtract(amount);
        }
        return amount;
    }

	// Deposit an amount of money
    public BigDecimal deposit(BigDecimal amount) throws DepositException {
        try {
            this.balance = this.balance.add(amount);
        }catch (Exception e){
            throw new DepositException(e.getMessage());
        }
            return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accNo == null) {
            if (other.accNo != null)
                return false;
        } else if (!accNo.equals(other.accNo)) {
            return false;
        }
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner)) {
            return false;
        }
        return true;
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
