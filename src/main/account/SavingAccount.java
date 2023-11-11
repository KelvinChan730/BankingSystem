package main.account;

import main.account.factory.SavingAccountInfo;
import main.constant.AccountType;
import main.utility.Formatter;

import java.math.BigDecimal;

// SavingAccount inherits from Account.
// It is a special type of account
public class SavingAccount extends BaseAccount implements IAccount {
	private BigDecimal targetAmount = new BigDecimal(100000); // target amount
	private SavingAccountInfo accountInfo;

	public SavingAccount(String accNo, SavingAccountInfo accInfo) {
		this.accNo = accNo;
		this.balance = BigDecimal.ZERO;
		this.accountInfo = accInfo;
		this.targetAmount = accInfo.targetAmount;
	}

	@Override
	public AccountType getType() {
		return AccountType.SAVING;
	}

	@Override
	public String getOwner() {
		return accountInfo.owner;
	}

	@Override
	public void setOwner(String owner) {
		this.accountInfo.owner = owner;
	}

	@Override
	public String getPassword() {
		return accountInfo.password;
	}

	@Override
	public void setPassword(String password) {
		this.accountInfo.password = password;
	}

	@Override
	public String getPhoneNo() {
		return accountInfo.phoneNo;
	}

	@Override
	public void setPhoneNo(String phoneNo) {
		this.accountInfo.phoneNo = phoneNo;
	}

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	// basic information of the saving account.
	@Override
	public String toString() {
		return super.toString() + String.format(" | Target Amount: %s\n", Formatter.formatBalance(getTargetAmount()));
	}
}