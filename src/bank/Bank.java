package bank;

import account.Account;
import account.AccountList;
import account.SavingAccount;
import exceptions.funcException.DepositException;
import exceptions.funcException.TransferException;
import exceptions.funcException.UnknownException;
import exceptions.funcException.WithdrawException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
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

	// Features of the banking system
	public void withdraw() throws WithdrawException {
		// Enter your account number
		Account account = null;
		while (true) {
			String accNo = askInput("\nEnter the account number you want to withdraw from.", "");
			try {
				account = findAccount(accNo);
				if (account.getCategory().equals("S")) {
					new SavingBank().withdraw((SavingAccount) account);
				}
				break;
			} catch (UnknownException | WithdrawException ee) {
				System.out.println(ee.getMessage());
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		// Process withdrawal
		while (true) {
			try {
				BigDecimal amount = askInput("\nEnter the amount you want to withdraw.", BigDecimal.ZERO);
				BigDecimal result;
				BigDecimal interest = interestCalculators.get(account.getCategory()).getInterest(account.getBalance());
				result = account.withdraw(amount);
				System.out.printf("\nThe withdrawal amount %s and interest %s were successfully withdrawn.\n",
						df.format(result), df.format(interest));
				break;
			} catch (WithdrawException we) {
				throw we;
			} catch (Exception e) {
				throw new WithdrawException(e.getMessage());
			}
		}
	}

	public void deposit() throws DepositException, UnknownException {
		// Enter your account number
		Account account;
		while (true) {
			String accNo = askInput("\nEnter the account number you'd like to deposit.", "");
			account = findAccount(accNo);
			if (account != null) {
				break;
			}
		}

		// Process deposit
		BigDecimal amount = askInput("\nEnter the amount you want to deposit.", BigDecimal.ZERO);
		BigDecimal result;
		try {
			result = account.deposit(amount);
			System.out.printf("\nThe deposit amount %s was successfully deposited.", df.format(result));
		} catch (DepositException de) {
			throw de;
		} catch (Exception e) {
			throw new DepositException(e.getMessage());
		}
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

	// Method to find and return a list of accounts
	public Account findAccount(String accNo) throws UnknownException {
		Account account = null;
		for (Account value : CentralBank.getInstance().getAccountList().getList()) {
			if (value.getAccNo().equals(accNo) && value.isActive()) {
				account = value;
			}
		}
		if (account == null)
			throw new UnknownException("The account does not exist.");
		return account;
	}

	public void transfer() throws InputMismatchException, TransferException, UnknownException {
		
		Account accountFrom;
		Account accountTo;
		boolean isFromSavingAccount = false;
		while (true) {
			try {
				accountFrom = findAccount(
						askInput("\nPlease enter the account number to which you wish to transfer funds.", ""));
				if (accountFrom == null) {

				} else if (accountFrom.getCategory().equals("S")) {
//                	If transfers are not supported from a savings account, please uncomment the following code:
//                	System.out.println("\nTo make a transfer from a savings account, please use the withdrawal method.");
//                	continue;
					isFromSavingAccount = true;
				}
				accountTo = findAccount(askInput("\nWhich account number would you like to send the funds to?", ""));
				if (accountTo.equals(accountFrom)) {
					System.out.println("\nTo transfer funds to your own account, please use the deposit method.");
					continue;
				} else if (accountTo.getCategory().equals("S")) {
					System.out.println("\n적You can't send money to a savings account.");
					continue;
				} else {
					break;
				}
			} catch (InputMismatchException ime) {
				throw ime;
			} catch (Exception e) {
				throw e;
			}
		}
		try {
			BigDecimal amount = askInput("\nEnter the amount you want to transfer.", BigDecimal.ZERO);
			// If you want to transfer funds from a savings account, a check is required.
			if (isFromSavingAccount)
				new SavingBank().withdraw((SavingAccount) accountFrom);
			accountFrom.withdraw(amount);
			accountTo.deposit(amount);
		} catch (WithdrawException | DepositException wde) {
			throw new TransferException(wde.getMessage());
		} catch (Exception e) {
			throw new TransferException(e.getMessage());
		}
	}

	public <T extends Object> T askInput(String msg, T obj) {
		Object input;
		while (true) {
			try {
				System.out.println(msg);
				if (obj instanceof BigDecimal) {
					input = scanner.nextBigDecimal();
				} else {
					input = scanner.next();
				}
			} catch (InputMismatchException e) {
				scanner.next();
				System.out.println(obj.getClass().toString() + " Please follow the format.");
				continue;
			}
			// If it's a BigDecimal, check if it's a positive integer
			if (input instanceof BigDecimal
					&& (((BigDecimal) input).scale() > 0 || ((BigDecimal) input).signum() < 0)) {
				System.out.println("양의 정수만 입력해주세요.");
				continue;
			}
			return (T) input;
		}
	}
}
