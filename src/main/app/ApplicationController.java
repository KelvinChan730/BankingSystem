package main.app;

import java.math.BigDecimal;

import main.account.BaseAccount;
import main.account.Account;
import main.account.factory.AccountInfo;
import main.bank.operation.TransferOperation;
import main.constant.AccountType;
import main.constant.Currency;
import main.db.AccountList;
import main.exception.IOFunctionException;
import main.utility.Authentication;

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
		this.inputHandler = new InputHandler(view);
	}

	public Authentication login() {
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
		Account existAccount = AccountList.findAccount(accNo);
		if (!password.equals(existAccount.getPassword())) {
			view.display("Account or Password Not Found");
			return null;
		}

		return new Authentication(accNo);
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

	public AccountInfo createAccount() {
		String owner, password, phoneNo;

		try {
			// prompt user and get input with verified format
			owner = inputHandler.promptName();
			password = inputHandler.promptPassword();
			phoneNo = inputHandler.promptPhoneNo();
			return new AccountInfo(owner, password, phoneNo);
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
}
