package bank;

import account.Account;
import account.AccountList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	protected static Scanner scanner = new Scanner(System.in);
	protected static int seq = 0;
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
}
