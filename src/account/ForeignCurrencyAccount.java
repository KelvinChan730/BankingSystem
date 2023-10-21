package account;

import java.math.BigDecimal;

import bank.Bank;
import constant.ForeignCurrency;

public class ForeignCurrencyAccount extends Account {
	private final ForeignCurrency currencyType;
	
	public ForeignCurrencyAccount(ForeignCurrency currency) {
        super("F");
        this.currencyType = currency;
    }
	
	public ForeignCurrency getCurrencyType() {
        return currencyType;
    }
	
	public boolean transfer(ForeignCurrencyAccount targetAccount, BigDecimal amount) {
		if (balance.compareTo(amount) < 0 || amount.compareTo(new BigDecimal(0)) <= 0)
			return false;
		
		balance.subtract(amount);
		BigDecimal amountHKD = amount.multiply(currencyType.getExchangeRate());
		BigDecimal amountTargetCurrency = amountHKD.divide(targetAccount.getCurrencyType().getExchangeRate());
		BigDecimal targetBalance = targetAccount.getBalance().add(amountTargetCurrency);
		targetAccount.setBalance(targetBalance);
		return true;
	}
	
	public boolean transfer(Account targetAccount, BigDecimal amount) {
		if (balance.compareTo(amount) < 0 || amount.compareTo(new BigDecimal(0)) <= 0)
			return false;
		
		balance.subtract(amount);
		BigDecimal amountHKD = amount.multiply(currencyType.getExchangeRate());
		BigDecimal newTargetBalance = targetAccount.getBalance().add(amountHKD);
		targetAccount.setBalance(newTargetBalance);
		return true;
	}
	
	// Print the basic information of the foreign currency account.
    @Override
    public void getAccountInfo() {
        System.out.printf("Account type: %s | Account number: %s | Account holder: %s | Balance: %s | Currency Type: %s\n",
                category, accNo, owner, Bank.df.format(balance), currencyType.getName());
    }
}
