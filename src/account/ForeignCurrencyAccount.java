package account;

import bank.Bank;
import constant.Currency;

public class ForeignCurrencyAccount extends Account {
	private final Currency currencyType;
	
	public ForeignCurrencyAccount(Currency currency) {
        super("F");
        this.currencyType = currency;
    }
	
	public Currency getCurrencyType() {
        return currencyType;
    }
	
	// basic information of the foreign currency account.
    @Override
    public String toString(){
        return String.format("Account type: %s | Account number: %s | Account holder: %s | Balance: %s | Currency Type: %s\n",
                category, accNo, owner, Bank.df.format(balance), currencyType.getName());
    }
}
