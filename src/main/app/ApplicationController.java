package main.app;

import java.math.BigDecimal;
import java.util.Scanner;

import main.account.BaseAccount;
import main.account.factory.AccountInfo;
import main.account.factory.BaseAccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.operation.TransferOperation;
import main.constant.AccountType;
import main.constant.Currency;
import main.db.AccountList;
import main.exception.IOFunctionException;

/**
 * Write a one-sentence summary of your robot task class here. Follow it with
 * additional details about its purpose, how many robots it creates, and how to
 * use it.
 */
public class ApplicationController {
	private ApplicationView view;
	private InputHandler inputHandler;

	public ApplicationController() {
		this.view = new ApplicationView();
		this.inputHandler = new InputHandler(view, new Scanner(System.in));
	}
	

	public BaseAccount login() {
		String accNo, password;

		// prompt account number and password
		try {
			accNo = inputHandler.promptAccNo();
			password = inputHandler.promptPassword();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
			return null;
		}

		// check do the account exist
		if (!AccountList.hasAccount(accNo)) {
			view.display("Account or Password Not Found");
			return null;
		}

		// check do the password equal
		BaseAccount existAccount = AccountList.findAccount(accNo);
		if (!password.equals(existAccount.getPassword())) {
			view.display("Account or Password Not Found");
			return null;
		}

		return AccountList.findAccount(accNo);
	}

	public BaseAccount attempLogin(){
		BaseAccount ac = null;
		int attemp = 0;
		do {
			ac = login();
			attemp++;
		} while (ac == null && attemp < 3);
		return ac;
	}

	public int showMenu() {
		int input = 0;
		try {
			input = inputHandler.promptMenuOption();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return input;
	}

	
	public int showPreLoginMenu() {
		int input = 0;
		try {
			input = inputHandler.promptPreLoginOption();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return input;
	}

	public AccountType showAccountOption() {
		int input = 0;
		try {
			input = inputHandler.promptAccountOption();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return AccountType.values()[input];
	}



	public BigDecimal withdraw(BaseAccount acc) {
		String rawAmount;

		try {
			// prompt user and get input with verified format
			rawAmount = inputHandler.promptAmount("withdraw");
			return new BigDecimal(rawAmount);
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return null;
	}

	public BigDecimal deposit(BaseAccount acc) {
		String rawAmount;

		try {
			// prompt user and get input with verified format
			rawAmount = inputHandler.promptAmount("deposit");
			return new BigDecimal(rawAmount);
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return null;
	}

	public TransferOperation transfer(BaseAccount acc) {
		try {
			// prompt user and get input with verified format
			String targetAccNo = inputHandler.promptTransferAccNo();
			String rawAmount = inputHandler.promptAmount("tranfer");
			return new TransferOperation(targetAccNo, new BigDecimal(rawAmount));
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return null;
	}

	public BaseAccountInfo createAccountInfo(AccountType type) {
		String owner, password, phoneNo;
		try {
			owner = inputHandler.promptName();
			password = inputHandler.promptPassword();
			phoneNo = inputHandler.promptPhoneNo();
		switch (type){
			case FOREIGN_CURRENCY:
				Currency cType = inputHandler.promptCurrencyType();
				return new ForeignCurrencyAccountInfo(owner, password, phoneNo, cType);
			case NORMAL:
				return new AccountInfo(owner, password, phoneNo);
			case SAVING:
				BigDecimal targetAmount = new BigDecimal(inputHandler.promptAmount("target saving amount"));
				return new SavingAccountInfo(owner, password, phoneNo, targetAmount);
			default:
				break;
			}
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return null;
	}

	public BigDecimal loan() {
		try {
			String loanAmount = inputHandler.promptAmount("loan");
			return new BigDecimal(loanAmount);
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return null;
	}

	public String payBack() {
		try {
			return inputHandler.promptLoanID();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}
		return null;
	}

	public Currency foreignCurrencyExchange(BaseAccount acc) {
		if (acc.getType() != AccountType.FOREIGN_CURRENCY) {
			view.display("Only Foreign Currency Account can change the currency type");
			return null;
		}

		try {
			return inputHandler.promptCurrencyType();
		} catch (IOFunctionException ioex) {
			view.display("IOFunctionException thrown  :" + ioex.getMessage());
		}

		return null;
	}
	
	public void display(String message) {
		view.display(message);
	}
}
