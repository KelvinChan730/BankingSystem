package main.account;

import main.account.factory.SavingAccountInfo;
import main.constant.AccountType;
import main.utility.Formatter;

import java.math.BigDecimal;

// SavingAccount inherits from Account.
// It is a special type of account
public class SavingAccount extends BaseAccount implements IAccount {
	protected final AccountType type = AccountType.SAVING;		// account type = saving
    private BigDecimal targetAmount = new BigDecimal(100000);	// target amount
    private SavingAccountInfo accountInfo;
    
    public SavingAccount(String accNo, SavingAccountInfo accInfo) {
    	this.accNo = accNo;
    	this.accountInfo.owner = accInfo.owner;
        this.balance = BigDecimal.ZERO;
        this.accountInfo.password = accInfo.password;
        this.accountInfo.phoneNo = accInfo.phoneNo;
        this.targetAmount = accInfo.targetAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

	// basic information of the saving account.
    @Override
    public String toString(){
        return super.toString() + String.format(" | Target Amount: %s\n", Formatter.formatBalance(targetAmount));
    }
}