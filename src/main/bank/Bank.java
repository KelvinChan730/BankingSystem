package main.bank;

import main.account.*;
import main.account.factory.*;
import main.bank.operation.TransferOperation;
import main.constant.AccountType;
import main.constant.Currency;
import main.db.AccountList;
import main.db.LoanList;

import java.math.BigDecimal;

public class Bank {
    public static String getAccountDetail(BaseAccount acc) {
        return acc.toString();
    }

    // create normal account
    public static boolean addAccount(AccountInfo para) {
    	if(para == null) {
    		return false;
    	}
        AbstractAccountFactory<Account, AccountInfo> factory = AccountFactory.getInstance();
        AccountList.addAccount(factory.createAccount(para));
        return true;
    }

    // create saving account
    public static boolean addAccount(SavingAccountInfo para) {
    	if(para == null) {
    		return false;
    	}
        AbstractAccountFactory<SavingAccount, SavingAccountInfo> factory = SavingAccountFactory.getInstance();
        AccountList.addAccount(factory.createAccount(para));
        return true;
    }

    // create foreign currency account
    public static boolean addAccount(ForeignCurrencyAccountInfo para) {
    	if(para == null) {
    		return false;
    	}
        AbstractAccountFactory<ForeignCurrencyAccount, ForeignCurrencyAccountInfo> factory = ForeignCurrencyAccountFactory.getInstance();
        AccountList.addAccount(factory.createAccount(para));
        return true;
    }

    // delete bank account
    public static boolean deleteAccount(String accNo, String password) {
        return AccountList.deleteAccount(accNo, password);
        //return !AccountList.hasAccount(accNo);
    }

    public static boolean withdraw(BaseAccount account, BigDecimal amount) {
        // check if account has enough balance
        BigDecimal accountBalance = account.getBalance();
        int compare = amount.compareTo(accountBalance);
        if (compare > 0) {
            return false;
        }
        BigDecimal afterWithdraw = accountBalance.subtract(amount);
        account.setBalance(afterWithdraw);
        return true;
    }

    public static boolean deposit(BaseAccount account, BigDecimal amount) {
        BigDecimal accountBalance = account.getBalance();

        BigDecimal afterDeposit = accountBalance.add(amount);
        account.setBalance(afterDeposit);
        return true;
    }

    // transfer amount of account currency to target account
    public static boolean transfer(BaseAccount transferAcc, TransferOperation transferOp) {
        // check if transfer account exists
        if (!AccountList.hasAccount(transferOp.targetAccNo)) {
            return false;
        }
        BaseAccount targetAcc = AccountList.findAccount(transferOp.targetAccNo);

        BigDecimal transferAccBalance = transferAcc.getBalance();
        if (transferAccBalance.compareTo(transferOp.amount) < 0 || transferOp.amount.compareTo(BigDecimal.ZERO) <= 0)
            return false;

        BigDecimal reducedBalance = transferAccBalance.subtract(transferOp.amount);

        // check if saving account eligible for transfer
        if (transferAcc.getType() == AccountType.SAVING && transferAcc instanceof SavingAccount) {
            if (((SavingAccount) transferAcc).getTargetAmount().compareTo(reducedBalance) > 0) {
                return false;
            }
        }

        transferAcc.setBalance(transferAccBalance.subtract(transferOp.amount));
        BigDecimal amountHKD = transferOp.amount.multiply(transferAcc.getCurrencyType().getExchangeRate());
        BigDecimal amountTargetCurrency = amountHKD.divide(targetAcc.getCurrencyType().getExchangeRate());
        BigDecimal targetBalance = targetAcc.getBalance().add(amountTargetCurrency);
        targetAcc.setBalance(targetBalance);
        return true;
    }

    public static boolean loan(BaseAccount acc, BigDecimal userExpectLoan) {
        LoanList loadList = LoanList.getInstance();
//		// check do user login successfully
//		if (!login(accNo, password)) {
//			return false;
//		}
//		// check the money format
//		if (!containsOnlyDigits(userExpectLoan)) {
//			return false;
//		}
        // if user expect loan equal to 0, return false
        if (userExpectLoan.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        // get the user balance
        BigDecimal userBalance = acc.getBalance();
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
        int checkLoanAvailable = maxLoan.compareTo(userExpectLoan);
        if (checkLoanAvailable < 0) {
            result = false;
        } else {
            result = true;
            loadList.addLoanRecord(new Loan(acc.getAccNo(), userExpectLoan));
        }
        return result;
    }

    public static boolean payBack(BaseAccount acc, String loanId) {
        LoanList loadList = LoanList.getInstance();
//		// check do user login successfully
//		if (!login(accNo, password)) {
//			return false;
//		}
//		// check the LoanId format
//		if (!containsOnlyDigits(loanId)) {
//			return false;
//		}
        // get the user balance
        BigDecimal userBalance = acc.getBalance();
        Loan record = loadList.findLoadRecord(loanId);
        if (record == null) {
            return false;
        }
        if(record.getPayBack()) {
            return true;
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

        // check do user has enough balance
        int enoughBalance = userBalance.compareTo(payBackAmount);

        // check is payback is successful
        boolean result;
        if (enoughBalance >= 0) {
            BigDecimal currentBalance = userBalance.subtract(payBackAmount);
            acc.setBalance(currentBalance);
            record.payBackSuccessful();
            result = true;

        } else {
            result = false;
        }
        return result;
    }

    public static boolean currencyTypeExchange(ForeignCurrencyAccount account, Currency targetCurrencyType) {
        if (account.getCurrencyType() == targetCurrencyType) {
            return false;
        }
        account.setBalance(account.getBalance().multiply(targetCurrencyType.getExchangeRate()));
        return true;
    }
}
