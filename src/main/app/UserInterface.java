package main.app;

import java.math.BigDecimal;

import main.account.Account;
import main.account.ForeignCurrencyAccount;
import main.account.factory.AccountPara;
import main.bank.Bank;
import main.db.AccountList;
import main.exception.IOFunctionException;
import main.utility.Authentication;
import main.utility.InputHandler;
import main.constant.AccountType;
import main.constant.Currency;


/**
 *  Write a one-sentence summary of your robot task class here.
 *  Follow it with additional details about its purpose, how many
 *  robots it creates, and how to use it.
 */
public class UserInterface {
	private Bank bank;
	private InputHandler inputHandler;
	
	public UserInterface(Bank bank) {
		this.bank = bank;
		this.inputHandler = new InputHandler();
	}
	
	public Authentication login() {
		String accNo, password;
		
		// prompt account number and password
		try {
			accNo = inputHandler.promptAccNo();
			password = inputHandler.promptPassword();
		} catch(IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
			return null;
		}

		//check do the account exist 
		if(!AccountList.hasAccount(accNo)) {
			System.out.println("Account or Passwword Not Found");
			return null;
		}

		// check do the password equal
		Account existAccount = AccountList.findAccount(accNo);
		if(!password.equals(existAccount.getPassword())) {
			System.out.println("Account or Passwword Not Found");
			return null;
		}
		
		return new Authentication(accNo);
	}
	
	public int showMenu() {
		int input = 0;
		try {
			input = inputHandler.promptMenuOption();
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}
		
		return input;
	}
	
	public void showAccountInfo(Account acc) {
		System.out.println(acc.toString());
	}
	
	public boolean withdraw(Account acc) {
		String rawAmount;
		
		try {
			// prompt user and get input with verified format
			rawAmount = inputHandler.promptAmount("withdraw");
			BigDecimal amount = new BigDecimal(rawAmount);
			return bank.withdraw(acc, amount);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return false;
	}
	
	public boolean deposit(Account acc) {
		String rawAmount;
		
		try {
			// prompt user and get input with verified format
			rawAmount = inputHandler.promptAmount("deposit");
			BigDecimal amount = new BigDecimal(rawAmount);
			return bank.deposit(acc, amount);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return false;
	}
	
	public boolean transfer(Account acc) {
		String rawAmount;
		
		try {
			// prompt user and get input with verified format
			String targetAccNo = inputHandler.promptTransferAccNo();
			rawAmount = inputHandler.promptAmount("tranfer");
			BigDecimal amount = new BigDecimal(rawAmount);
			return bank.transfer(acc, targetAccNo, amount);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return false;
	}
	
	public boolean createAccount() {
		String owner, password, phoneNo;
		
		try {
			// prompt user and get input with verified format
			owner = inputHandler.promptName();
			password = inputHandler.promptPassword();
			phoneNo = inputHandler.promptPhoneNo();
			return bank.addAccount(new AccountPara(owner, password, phoneNo));
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return false;
	}

	public boolean loan(Account acc){
		try {
			String loanAmount = inputHandler.promptAmount("loan");
			BigDecimal amount = new BigDecimal(loanAmount);
			return bank.loan(acc, amount);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return false;
	}

	public boolean payBack(Account acc){
		try {
			String loanID = inputHandler.promptLoanID();
			return bank.payBack(acc, loanID);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return false;
	}

	public boolean foreignCurrencyExchange(Account acc){
		if (acc.getType()!=AccountType.FOREIGN_CURRENCY) {
			System.out.println("Only Foreign Currency Account can change the currency type");
			return false;
		}
		try {
			Currency targetCurrencyType = inputHandler.promptCurrencyType();
			return bank.currencyTypeExchange((ForeignCurrencyAccount)acc, targetCurrencyType);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return false;
	}
}
