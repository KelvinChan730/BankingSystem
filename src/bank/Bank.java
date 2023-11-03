package bank;

import account.Account;
import account.AccountList;
import account.Loan;
import account.SavingAccount;
import account.UserLoanList;
import account.factory.AccountAbstractFactory;
import account.factory.AccountPara;
import constant.AccountType;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Bank {
	public static DecimalFormat df = new DecimalFormat("#,###");
	
	public boolean withdraw(Account account, BigDecimal amount) {
		// check if account has enough balance
		BigDecimal accountBalance = account.getBalance();
		int compare = amount.compareTo(accountBalance);
		if(compare > 0) {
			return false;
		}
		BigDecimal afterWithdraw = accountBalance.subtract(amount);
		account.setBalance(afterWithdraw);
		return true;
	}
	public boolean deposit(Account account, BigDecimal amount) {
		BigDecimal accountBalance = account.getBalance();

		BigDecimal afterDeposit = accountBalance.add(amount);
		account.setBalance(afterDeposit);
		return true;
	}

	public boolean addAccount(AccountPara para) {
		AccountList.addAccount(AccountAbstractFactory.createAccount(para));
		return true;
	}

	// delete bank account
	public boolean deleteAccount(String accNo, String password) {
		AccountList.deleteAccount(accNo, password);
		return !AccountList.hasAccount(accNo);
	}
	
	// transfer amount of account currency to target account
	public boolean transfer(Account transferAcc, String targetAccNo, BigDecimal amount) {
		// check if transfer account exists
		if (!AccountList.hasAccount(targetAccNo)) {
			return false;
		}
		Account targetAcc = AccountList.findAccount(targetAccNo);
		
		BigDecimal transferAccBalance = transferAcc.getBalance();
		if (transferAccBalance.compareTo(amount) < 0 || amount.compareTo(BigDecimal.ZERO) <= 0)
			return false;
		
		BigDecimal reducedBalance = transferAccBalance.subtract(amount);

		// check if saving account eligible for transfer
		if (transferAcc.getType() == AccountType.SAVING && transferAcc instanceof SavingAccount) {
			if (((SavingAccount)transferAcc).getTargetAmount().compareTo(reducedBalance) > 0) {
				return false;
			}
		}
		
		transferAcc.setBalance(transferAccBalance.subtract(amount));
		BigDecimal amountHKD = amount.multiply(transferAcc.getCurrencyType().getExchangeRate());
		BigDecimal amountTargetCurrency = amountHKD.divide(targetAcc.getCurrencyType().getExchangeRate());
		BigDecimal targetBalance = targetAcc.getBalance().add(amountTargetCurrency);
		targetAcc.setBalance(targetBalance);
		return true;
	}

	public boolean loan(String accNo, String password, String userExpectLoan) {
//		// check do user login successfully
//		if (!login(accNo, password)) {
//			return false;
//		}
//		// check the money format
//		if (!containsOnlyDigits(userExpectLoan)) {
//			return false;
//		}
		// if user expect loan equal to 0, return false
		if (new BigDecimal(userExpectLoan).compareTo(BigDecimal.ZERO) == 0) {
			return false;
		}
		// get the user account
		Account userAccount = AccountList.findAccount(accNo);
		// get the user balance
		BigDecimal userBalance = userAccount.getBalance();
		// check do the user balance > than 1 million
		int comparisonResult0 = userBalance.compareTo(new BigDecimal("1000000"));
		// check do the user balance > than 500 thousand
		int comparisonResult1 = userBalance.compareTo(new BigDecimal("500000"));
		BigDecimal maxLoan; // the maximum loan of the user
		boolean result;
		// if a user has equal or more than 1 million balance, then he can borrow
		// balance * 0.5
		if (comparisonResult0 >= 0) {
			maxLoan = userBalance.multiply(new BigDecimal("0.5"));
			// if a user has equal or more than 500 thousand balance but less than 1
			// million, then he can borrow balance * 0.3
		} else if (comparisonResult1 >= 0) {
			maxLoan = userBalance.multiply(new BigDecimal("0.3"));
		} else { // if a user's balance less than 500 thousand, then he can not borrow
			maxLoan = new BigDecimal("0");
		}
		// check is the user except loan available
		int checkLoanAvailable = maxLoan.compareTo(new BigDecimal(userExpectLoan));
		if (checkLoanAvailable < 0) {
			result = false;
		} else {
			result = true;
			UserLoanList.addLoanRecord(new Loan(accNo, new BigDecimal(userExpectLoan)));
		}
		return result;
	}

	public boolean payBack(String accNo, String password, String loanId) {
//		// check do user login successfully
//		if (!login(accNo, password)) {
//			return false;
//		}
//		// check the LoanId format
//		if (!containsOnlyDigits(loanId)) {
//			return false;
//		}
		// get the user account
		Account userAccount = AccountList.findAccount(accNo);
		// get the user balance
		BigDecimal userBalance = userAccount.getBalance();
		Loan record = UserLoanList.findLoadRecord(loanId);
		if (record == null) {
			return false;
		}
		BigDecimal payBackAmount = record.getLoanAmount();
		// interest for the loan
		BigDecimal interest;
		int compareMillion = payBackAmount.compareTo(new BigDecimal("1000000"));
		int compare500Thousand = payBackAmount.compareTo(new BigDecimal("500000"));
		if (compareMillion >= 0) {
			interest = payBackAmount.multiply(new BigDecimal("0.1"));
		} else if (compare500Thousand >= 0) {
			interest = payBackAmount.multiply(new BigDecimal("0.2"));
		} else {
			interest = payBackAmount.multiply(new BigDecimal("0.3"));
		}
		payBackAmount = payBackAmount.add(interest);

		//check do user has enough balance
		int enoughBalance = userBalance.compareTo(payBackAmount);
		
		//check is payback is successful
		boolean result;
		if (enoughBalance>=0) {
			BigDecimal currentBalance = userBalance.subtract(payBackAmount);
			userAccount.setBalance(currentBalance);
			record.payBackSuccessful();
			result = true;
			
		}else {
			result = false;
		}
			return result;
	}
}
