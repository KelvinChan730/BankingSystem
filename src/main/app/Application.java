package main.app;

import main.account.Account;
import main.account.AccountList;
import main.account.factory.AccountPara;
import main.bank.Bank;
import main.utility.Authentication;

public class Application {
	private static Bank bank = new Bank();
	private static UserInterface ui = new UserInterface(bank);
	
	public Application() {
	}
	
	// function to simulate loading existing records
	public static void loadRecords() {
		AccountPara para1 = new AccountPara("John", "135791", "12345678");
		bank.addAccount(para1);
		AccountPara para2 = new AccountPara("Jane", "123456", "98765432");
		bank.addAccount(para2);
	}
	
	// setup application
	public static void setup() {
		loadRecords();
	}
	
	public static void run() {
		Authentication auth = null;
		do {
			auth = ui.login();
		} while (auth == null);
		
		String userAccNo = auth.getAccNo();
		if (!AccountList.hasAccount(userAccNo))
			return; // System error - End program
		Account userAcc = AccountList.findAccount(userAccNo);
		
		boolean isContinue = true;
		while (isContinue) {
			int input = ui.showMenu();
			switch(input) {
			case 1:
				ui.showAccountInfo(userAcc);
				break;
			case 2:
				ui.withdraw(userAcc);
				break;
			case 3:
				ui.deposit(userAcc);
				break;
			case 4:
				ui.transfer(userAcc);
				break;
			case 5:
				// todo
				break;
			case 6:
				// todo
				break;
			case 7:
				isContinue = false;
				break;
			default:
				isContinue = false;
				break;
			}
        }
	}
	
	public static void main(String[] args) {
		setup();
		run();
	}
}
