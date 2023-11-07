package main.account.factory;

import java.math.BigDecimal;

import main.account.SavingAccount;

public class SavingAccountInfo extends BaseAccountInfo implements IAccountPara<SavingAccount> {
	public BigDecimal targetAmount = new BigDecimal(100000); // target amount

	public SavingAccountInfo(String owner, String password, String phoneNo, BigDecimal targetAmount) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
		this.targetAmount = targetAmount;
	}
}