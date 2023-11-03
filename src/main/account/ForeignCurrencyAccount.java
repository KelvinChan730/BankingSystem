package main.account;

import java.math.BigDecimal;

import main.account.factory.ForeignCurrencyAccountPara;
import main.bank.Bank;
import main.constant.AccountType;
import main.constant.Currency;

public class ForeignCurrencyAccount extends Account {
	protected final AccountType type = AccountType.FOREIGN_CURRENCY;		// account type = foreign currency
	private final Currency currencyType;									// currency type
	
	public ForeignCurrencyAccount(String accNo, ForeignCurrencyAccountPara para) {
        super(accNo, para);
        this.currencyType = para.currencyType;
    }
	
	public Currency getCurrencyType() {
        return currencyType;
    }
	
	// basic information of the foreign currency account.
    @Override
    public String toString(){
        return super.toString() + String.format(" | Currency Type: %s\n", currencyType.getName());
    }
}
