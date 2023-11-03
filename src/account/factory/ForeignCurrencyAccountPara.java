package account.factory;

import account.ForeignCurrencyAccount;
import constant.Currency;

public class ForeignCurrencyAccountPara extends AbstractAccountPara implements IAccountPara<ForeignCurrencyAccount> {
	public Currency currencyType;						// currency type
}