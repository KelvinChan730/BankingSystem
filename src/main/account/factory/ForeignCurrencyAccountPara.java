package main.account.factory;

import java.math.BigDecimal;

import main.account.ForeignCurrencyAccount;
import main.constant.Currency;

public class ForeignCurrencyAccountPara extends AbstractAccountPara implements IAccountPara<ForeignCurrencyAccount> {
	public Currency currencyType;						// currency type
    
	public ForeignCurrencyAccountPara(String owner, String password, String phoneNo, Currency currencyType) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
		this.currencyType = currencyType;
	}
}