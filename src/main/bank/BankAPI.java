package main.bank;
import java.math.BigDecimal;
import java.util.UUID;

import main.account.BaseAccount;
import main.app.ApplicationController;
import main.account.ForeignCurrencyAccount;
import main.account.factory.AccountInfo;
import main.account.factory.BaseAccountInfo;
import main.account.factory.ForeignCurrencyAccountInfo;
import main.account.factory.SavingAccountInfo;
import main.bank.operation.TransferOperation;
import main.constant.AccountType;
import main.constant.Currency;
import main.constant.OperationType;

public class BankAPI {
    private String APIKey;
    private ApplicationController controller = new ApplicationController();
    public BankAPI (){
        String APIKey = UUID.randomUUID().toString().replaceAll("_", "");
    }

    public BaseAccount selectOperation(OperationType Type){
        BaseAccount ac = null;
        switch (Type){
            case LOGIN:
                ac = controller.keepLogin();
                break;
            case ACCOUNT_CREATION: 
                AccountType type = controller.showAccountOption();
                BaseAccountInfo info = controller.createAccountInfo(type);
                switch (type) { // need improvement
                    case FOREIGN_CURRENCY:
                        addAccount((ForeignCurrencyAccountInfo)info);
                        break;
                    case NORMAL:
                        addAccount((AccountInfo)info);
                        break;
                    case SAVING:
                        addAccount((SavingAccountInfo)info);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        return ac;
    }

    public boolean selectOperation(OperationType Type, BaseAccount ac){
        switch (Type){
            case NONE:
                break;
            case ACCOUNT_DELETION:
                deleteAccount(ac);
                break;
            case DEPOSIT:
            	BigDecimal depositAmount = controller.deposit(ac);
				deposit(ac, depositAmount);
                break;
            case LOAN:
                BigDecimal loanAmount = controller.loan();
                loan(ac, loanAmount);
                break;
            case PAYBACK:
            	String loanID = controller.payBack();
				payBack(ac, loanID);
                break;
            case SHOWACCOUNTDETAIL:
                getAccountDetail(ac);
                break;
            case TRANSFER:
                TransferOperation transferOp = controller.transfer(ac);
                transfer(ac, transferOp);
                break;
            case WITHDRAW:
                BigDecimal withdrawAmount = controller.withdraw(ac);
                withdraw(ac, withdrawAmount);
                break;
            default:
                break;
        }
        return true;
    }

    private String getAccountDetail(BaseAccount acc) {
		return Bank.getAccountDetail(acc);
	}

    private boolean addAccount(AccountInfo para) {
		return Bank.addAccount(para);
	}

    private boolean addAccount(SavingAccountInfo para) {
 	    return Bank.addAccount(para);       
    }

    private boolean addAccount(ForeignCurrencyAccountInfo para) {
 	    return Bank.addAccount(para);       
    }

    private boolean deleteAccount(BaseAccount ac) {
        String accNo = ac.getAccNo();
        String password = ac.getPassword();
		return Bank.deleteAccount(accNo, password);
	}

	private boolean withdraw(BaseAccount account, BigDecimal amount) {
        return Bank.withdraw(account, amount);
	}

	private boolean deposit(BaseAccount account, BigDecimal amount) {
        return Bank.deposit(account, amount);
    }

	// transfer amount of account currency to target account
	private boolean transfer(BaseAccount transferAcc, TransferOperation transferOp) {
        return Bank.transfer(transferAcc, transferOp);
	}

	private boolean loan(BaseAccount acc, BigDecimal userExpectLoan) {
        return Bank.loan(acc, userExpectLoan);
	}

	private boolean payBack(BaseAccount acc, String loanId) {
        return Bank.payBack(acc, loanId);
    }

	private  boolean currencyTypeExchange(ForeignCurrencyAccount account, Currency targetCurrencyType) {
        return Bank.currencyTypeExchange(account, targetCurrencyType);
	}
}
