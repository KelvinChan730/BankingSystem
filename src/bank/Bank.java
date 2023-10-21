package bank;

import account.Account;
import account.AccountList;
import account.ForeignCurrencyAccount;
import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	protected static Scanner scanner = new Scanner(System.in);
	protected int seq = 0;
	public static DecimalFormat df = new DecimalFormat("#,###");
	private HashMap<String, InterestCalculator> interestCalculators = new HashMap<>();

	public Bank() {
		interestCalculators.put("N", new BasicInterestCalculator());
		interestCalculators.put("S", new SavingInterestCalculator());
	}

	public static boolean validateStringName(String input) {
		// Match English letters and numbers using regular expressions.
		String pattern = "^[a-zA-Z0-9]+$";
		return input.matches(pattern);
	}

	public static boolean validateBigDecimalFormat(String input) {
		String pattern = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
		return input.matches(pattern);

	}

	public static boolean validateBigDecimal(String parameter) {
		// Check if the parameter is of type BigDecimal.
		if (validateBigDecimalFormat(parameter)) {
			BigDecimal number = new BigDecimal(parameter);
			BigDecimal zero = BigDecimal.ZERO;
			return number.compareTo(zero) >= 0;
		}
		return false;
	}

	public static boolean validateStringPassword(String input) {
		// Match English letters, numbers, and underscores using regular expressions.
		String pattern = "^[a-zA-Z0-9_]+$";
		return input.matches(pattern);
	}

	public static boolean containsOnlyDigits(String str) {
	    return str.matches("\\d+");
	}

	public boolean login(String accNo, String password) {
		//check the accNo format
		if(!containsOnlyDigits(accNo)) {
			return false;
		}
		//check do the account exist 
		if(!AccountList.hasAccount(accNo)) {
			return false;
		}
		// check the password format
		if(!validateStringPassword(password)) {
			return false;
		}
		// check do the password equal
		Account existAccount = AccountList.findAccount(accNo);
		if(!password.equals(existAccount.getAccountPassword())) {
			return false;
		}
		return true;
	}
	
	public boolean withdraw(String accNo, String password, String money) {
		// check do user login successfully
		if(!login(accNo, password)) {
			return false;
		}
		//check the money format
		if(!containsOnlyDigits(money)) {
			return false;
		}
		//check do balance >= money
		int compare = new BigDecimal(money).compareTo(AccountList.findAccount(accNo).getBalance());
		if(compare > 0) {
			return false;
		}
		BigDecimal afterWithdraw = AccountList.findAccount(accNo).getBalance().subtract(new BigDecimal(money));
		Account currentAccount = AccountList.findAccount(accNo);
		currentAccount.setBalance(afterWithdraw);
		return true;
	}

	public boolean addAccount(String name, String amount, String password) {
		String accNo = String.format(new DecimalFormat("0000").format(++seq));

		if (!validateStringName(name)) {
			return false;
		}

		if (!validateBigDecimal(amount)) {
			return false;
		}

		if (!validateStringPassword(password)) {
			return false;
		}
		
		BigDecimal parsedAmount = new BigDecimal(Double.parseDouble(amount));
		AccountList.addAccount(new Account(accNo, name, parsedAmount, password));
		return true;
	}
	
	// delete bank account
	public boolean deleteAccount(String accNo, String password) {
		if(!login(accNo, password)) {
			return false;
		}
		AccountList.deleteAccount(accNo, password);
		return !AccountList.hasAccount(accNo);
	}
	
	// transfer amount of account currency to target account
	public boolean transfer(Account transferAcc, Account targetAcc, BigDecimal amount) {
		BigDecimal transferAccBalance = transferAcc.getBalance();
		if (transferAccBalance.compareTo(amount) < 0 || amount.compareTo(BigDecimal.ZERO) <= 0)
			return false;
		
		BigDecimal reducedBalance = transferAccBalance.subtract(amount);

		// check if saving account eligible for transfer
		if (transferAcc.getCategory() == "S" && ((SavingAccount)transferAcc).getTargetAmount().compareTo(reducedBalance) > 0) {
			return false;
		}
		
		transferAcc.setBalance(transferAccBalance.subtract(amount));
		BigDecimal amountHKD = amount.multiply(transferAcc.getCurrencyType().getExchangeRate());
		BigDecimal amountTargetCurrency = amountHKD.divide(targetAcc.getCurrencyType().getExchangeRate());
		BigDecimal targetBalance = targetAcc.getBalance().add(amountTargetCurrency);
		targetAcc.setBalance(targetBalance);
		return true;
	}
}
