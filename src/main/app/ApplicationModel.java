package main.app;

import main.account.BaseAccount;
// import main.account.factory.AccountInfo;
// import main.bank.Bank;
import main.bank.BankAPI;
import main.bank.operation.AccountCreationOperationReturn;
import main.bank.operation.LoginOperationReturn;
import main.bank.operation.OperationReturn;
import main.constant.PostLoginOperationType;
import main.constant.PreLoginOperationType;

public class ApplicationModel {
	private BankAPI bank = new BankAPI();
	private ApplicationController controller = new ApplicationController();

	public ApplicationModel() {

	}

	public void run() {
		boolean isContinue = true;
		while (isContinue) {
			OperationReturn preLoginOpReturn;
			int preloginOp = controller.showPreLoginMenu();
			switch (preloginOp) {
			case 1:
				preLoginOpReturn = bank.operatePreLogin(PreLoginOperationType.LOGIN);
				BaseAccount ac = ((LoginOperationReturn)preLoginOpReturn).ac;
				// while is logged in
				while (preLoginOpReturn.success && ac != null) {
					int postloginOp = controller.showMenu();
					OperationReturn opReturn;
					switch (postloginOp) {
					case 1:
						opReturn = bank.operatePostLogin(PostLoginOperationType.SHOW_DETAIL, ac);
						break;
					case 2:
						opReturn = bank.operatePostLogin(PostLoginOperationType.WITHDRAW, ac);
						break;
					case 3:
						opReturn = bank.operatePostLogin(PostLoginOperationType.DEPOSIT, ac);
						break;
					case 4:
						opReturn = bank.operatePostLogin(PostLoginOperationType.TRANSFER, ac);
						break;
					case 5:
						opReturn = bank.operatePostLogin(PostLoginOperationType.LOAN, ac);
						break;
					case 6:
						opReturn = bank.operatePostLogin(PostLoginOperationType.PAYBACK, ac);
						break;
					case 7:
						opReturn = bank.operatePostLogin(PostLoginOperationType.ACCOUNT_DELETION, ac);
						break;
					case 8:
					default:
						opReturn = null;
						ac = null; // log out
						break;
					}
					if (opReturn.success) {
						controller.display(String.format("The %d operation is successfull.", postloginOp)); // need operation name
					} else {
						controller.display("Operation failed.");
					}
				}
				break;
			case 2:
				AccountCreationOperationReturn acCreateReturn = (AccountCreationOperationReturn) bank.operatePreLogin(PreLoginOperationType.ACCOUNT_CREATION);
				
				break;
			case 3:
			default:
				isContinue = false;
				break;
			}
		}
	}

	public static void main(String[] args) {
		ApplicationModel application = new ApplicationModel();
		application.run();
	}
}
