package main.app;

import main.account.BaseAccount;
import main.account.ForeignCurrencyAccount;
import main.account.factory.AccountInfo;
import main.bank.Bank;
import main.bank.operation.TransferOperation;
import main.constant.Currency;
import main.db.AccountList;
import main.utility.Authentication;

import java.math.BigDecimal;

public class ApplicationModel {
	private Bank bank = new Bank();
	private ApplicationController controller = new ApplicationController();
	
	public ApplicationModel() {
	}
	
	// function to simulate loading existing records
	public void loadRecords() {
		AccountInfo para1 = new AccountInfo("John", "135791", "12345678");
		bank.addAccount(para1);
		AccountInfo para2 = new AccountInfo("Jane", "123456", "98765432");
		bank.addAccount(para2);
	}
	
	// setup application
	public ApplicationModel setup() {
		loadRecords();
		return this;
	}
	
	public void run() {
		Authentication auth = null;
		do {
			auth = controller.login();
		} while (auth == null);
		
		String userAccNo = auth.getAccNo();
		if (!AccountList.hasAccount(userAccNo))
			return; // System error - End program
		BaseAccount userAcc = AccountList.findAccount(userAccNo);
		
		boolean isContinue = true;
		while (isContinue) {
			int input = controller.showMenu();
			switch(input) {
			case 1:
				bank.showAccountInfo(userAcc);
				break;
			case 2:
				BigDecimal withdrawAmount = controller.withdraw(userAcc);
				bank.withdraw(userAcc, withdrawAmount);
				break;
			case 3:
				BigDecimal depositAmount = controller.deposit(userAcc);
				bank.deposit(userAcc, depositAmount);
				break;
			case 4:
				TransferOperation transferOp = controller.transfer(userAcc);
				bank.transfer(userAcc, transferOp);
				break;
			case 5:
				BigDecimal loanAmount = controller.loan();
				bank.loan(userAcc, loanAmount);
				break;
			case 6:
				String loanID = controller.payBack();
				bank.payBack(userAcc, loanID);
				break;
			case 7:
				Currency currency = controller.foreignCurrencyExchange(userAcc);
				bank.currencyTypeExchange((ForeignCurrencyAccount) userAcc, currency);
				break;
			case 8:
				isContinue = false;
				break;
			default:
				isContinue = false;
				break;
			}
        }
		System.out.println("logout successfully!");
	}
	
	public static void main(String[] args) {
		ApplicationModel application = new ApplicationModel();
		application.setup().run();
	}
}
