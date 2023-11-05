package main.account;

import main.account.factory.ForeignCurrencyAccountPara;
import main.constant.AccountType;
import main.constant.Currency;

public class ForeignCurrencyAccount extends Account {
	protected final AccountType accountType = AccountType.FOREIGN_CURRENCY;		// account type = foreign currency
	private Currency currencyType;									// currency type
	
	public ForeignCurrencyAccount(String accNo, ForeignCurrencyAccountPara para) {
        super(accNo, para);
        this.currencyType = para.currencyType;
    }

	public Currency getCurrencyType() {
        return currencyType;
    }
	
    public void setCurrencyType(Currency currencyType) {
        this.currencyType = currencyType;
    }

	// basic information of the foreign currency account.
    @Override
    public String toString(){
        return super.toString() + String.format(" | Currency Type: %s\n", currencyType.getName());
    }

}
