package main.account.factory;

import main.account.ForeignCurrencyAccount;
import main.constant.Currency;

public class ForeignCurrencyAccountInfo extends BaseAccountInfo implements IAccountPara<ForeignCurrencyAccount> {
	public Currency currencyType;						// currency type
    
	public ForeignCurrencyAccountInfo(String owner, String password, String phoneNo, Currency currencyType) {
		this.owner = owner;
		this.password = password;
		this.phoneNo = phoneNo;
		this.currencyType = currencyType;
	}
}