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
import main.bank.operation.AccountCreationOperationReturn;
import main.bank.operation.DetailedOperationReturn;
import main.bank.operation.LoginOperationReturn;
import main.bank.operation.OperationReturn;
import main.bank.operation.TransferOperation;
import main.constant.AccountType;
import main.constant.Currency;
import main.constant.PostLoginOperationType;
import main.constant.PreLoginOperationType;
import main.db.AccountListSequencer;

public class BankAPI {
    private String APIKey;
    private ApplicationController controller = new ApplicationController();
    public BankAPI (){
        String APIKey = UUID.randomUUID().toString().replaceAll("_", "");
    }

    public OperationReturn operatePreLogin(PreLoginOperationType Type){
        OperationReturn opReturn = new OperationReturn(false);
        switch (Type){
            case LOGIN:
                BaseAccount ac = controller.attempLogin();
                opReturn = new LoginOperationReturn(ac != null, ac);
                break;
            case ACCOUNT_CREATION: 
                AccountType type = controller.showAccountOption();
                BaseAccountInfo info = controller.createAccountInfo(type);
                String acNo = AccountListSequencer.getInstance().getSequence();
                boolean result = false;
                switch (type) { // need improvement
                    case FOREIGN_CURRENCY:
                    	result = addAccount((ForeignCurrencyAccountInfo)info);
                        break;
                    case NORMAL:
                    	result = addAccount((AccountInfo)info);
                        break;
                    case SAVING:
                    	result = addAccount((SavingAccountInfo)info);
                        break;
                    default:
                        break;
                }
                opReturn = new AccountCreationOperationReturn(result, acNo);
            default:
                break;
        }
        return opReturn;
    }

    public OperationReturn operatePostLogin(PostLoginOperationType Type, BaseAccount ac){
        OperationReturn opReturn = new OperationReturn(false);
        switch (Type){
            case ACCOUNT_DELETION:
                return new OperationReturn(deleteAccount(ac));
            case DEPOSIT:
            	BigDecimal depositAmount = controller.deposit(ac);
				return new OperationReturn(deposit(ac, depositAmount));
            case LOAN:
                BigDecimal loanAmount = controller.loan();
                return new OperationReturn(loan(ac, loanAmount));
            case PAYBACK:
            	String loanID = controller.payBack();
				return new OperationReturn(payBack(ac, loanID));
            case SHOW_DETAIL:
            	String detail = getAccountDetail(ac);
            	boolean success = (detail != null && !detail.isBlank());
                return new DetailedOperationReturn(success, detail);
            case TRANSFER:
                TransferOperation transferOp = controller.transfer(ac);
                return new OperationReturn(transfer(ac, transferOp));
            case WITHDRAW:
                BigDecimal withdrawAmount = controller.withdraw(ac);
                return new OperationReturn(withdraw(ac, withdrawAmount));
            default:
            	break;
        }
        return opReturn;
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
