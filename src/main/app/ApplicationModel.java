package main.app;

import main.account.BaseAccount;
// import main.account.factory.AccountInfo;
// import main.bank.Bank;
import main.bank.BankAPI;
import main.constant.OperationType;

public class ApplicationModel {
	private BankAPI bank = new BankAPI();
	private ApplicationController controller = new ApplicationController();

	public ApplicationModel() {

	}

	// function to simulate loading existing records
	// public void loadRecords() {
	// 	AccountInfo para1 = new AccountInfo("John", "135791", "12345678");
	// 	Bank.addAccount(para1);
	// 	AccountInfo para2 = new AccountInfo("Jane", "123456", "98765432");
	// 	Bank.addAccount(para2);
	// }

	// setup application
	// public ApplicationModel setup() {
	// 	loadRecords();
	// 	return this;
	// }

	public void run() {
		boolean isContinue = true;
		while(isContinue){
			int input = controller.showInitialMenu();
			switch (input) {
				case 1:
				boolean isContinue2 = true;
				BaseAccount userAcc = bank.selectOperation(OperationType.LOGIN);
				while (isContinue2) {
					int input2 = controller.showMenu();
					switch (input2) {
					case 1:
						bank.selectOperation(OperationType.SHOWACCOUNTDETAIL, userAcc);
						break;
					case 2:
						bank.selectOperation(OperationType.WITHDRAW, userAcc);
						break;
					case 3:
						bank.selectOperation(OperationType.DEPOSIT, userAcc);
						break;
					case 4:
						bank.selectOperation(OperationType.TRANSFER, userAcc);
						break;
					case 5:
						bank.selectOperation(OperationType.LOAN, userAcc);
						break;
					case 6:
						bank.selectOperation(OperationType.PAYBACK, userAcc);
						break;
					case 7:
						bank.selectOperation(OperationType.ACCOUNT_DELETION, userAcc);
						break;
					case 8:
						isContinue2 = false;
						break;
					default:
						isContinue2 = false;
						break;
					}
				}
					break;
				case 2:
					bank.selectOperation(OperationType.ACCOUNT_CREATION);
					break;
				case 3:
					isContinue = false;
					break;
				default:
					isContinue = false;
					break;
			}
		}
	}

	public static void main(String[] args) {
		ApplicationModel application = new ApplicationModel();
		application/*setup()*/.run();
	}
}
