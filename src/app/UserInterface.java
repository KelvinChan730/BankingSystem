package app;

import java.math.BigDecimal;

import account.Account;
import account.AccountList;
import bank.Bank;
import exception.IOFunctionException;
import utility.Authentication;
import utility.InputHandler;

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
			return null;
		}

		// check do the password equal
		Account existAccount = AccountList.findAccount(accNo);
		if(!password.equals(existAccount.getAccountPassword())) {
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
		String accNo, password, amount;
		
		try {
			// prompt user and get input with verified format
			accNo = inputHandler.promptAccNo();
			password = inputHandler.promptPassword();
			amount = inputHandler.promptAmount("withdraw");
			return bank.addAccount(accNo, amount, password);
		} catch (IOFunctionException ioex) {
			System.out.println("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return false;
	}
}
