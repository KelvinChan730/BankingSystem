package account;

import bank.Bank;

import java.math.BigDecimal;

// SavingAccount inherits from Account.
// It is a special type of account
public class SavingAccount extends Account {
	// The savings account class has an additional attribute called "target amount".
    private BigDecimal targetAmount = new BigDecimal(100000);

    public SavingAccount(){
        super("S");
    }
    
    public SavingAccount(BigDecimal target){
        this();
        this.targetAmount = target;
    }
    
	// The category of the savings account is set to "S".
    public SavingAccount(String accNo, String owner, BigDecimal balance, String password, BigDecimal goalAmount) {
        super("S", accNo, owner, balance, password);
        this.targetAmount = goalAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

	// basic information of the saving account.
    @Override
    public String toString(){
        return String.format("Account type: %s | Account number: %s | Account holder: %s | Balance: %s | Target Amount: %s\n",
                category, accNo, owner, Bank.df.format(balance), Bank.df.format(targetAmount));
    }
}
