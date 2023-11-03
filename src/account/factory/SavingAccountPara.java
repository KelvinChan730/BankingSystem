package account.factory;

import java.math.BigDecimal;

import account.SavingAccount;

public class SavingAccountPara extends AbstractAccountPara implements IAccountPara<SavingAccount> {
    public BigDecimal targetAmount = new BigDecimal(100000);	// target amount
    
	public SavingAccountPara(String owner, String password, String phoneNo, BigDecimal targetAmount) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
		this.targetAmount = targetAmount;
	}
}