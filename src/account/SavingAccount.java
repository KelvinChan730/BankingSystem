package account;

import bank.Bank;

import java.math.BigDecimal;

// SavingAccount inherits from Account.
// It is a special type of account
public class SavingAccount extends Account{
	// The savings account class has an additional attribute called "target amount".
    private BigDecimal targetAmount = new BigDecimal(100000);

    public SavingAccount(){
        super();
        this.category = "S";
    }
    
	// The category of the savings account is set to "S".
    public SavingAccount(String accNo, String owner, BigDecimal balance, BigDecimal goalAmount) {
        super(accNo, owner, balance);
        this.targetAmount = goalAmount;
        this.category = "S";
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

	// Print the basic information of the saving account.
    @Override
    public void getAccountInfo() {
        System.out.printf("Account type: %s | Account number: %s | Account holder: %s | Balance: %s | Target Amount: %s\n",
                category, accNo, owner, Bank.df.format(balance), Bank.df.format(targetAmount));
    }
}
