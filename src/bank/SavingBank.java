package bank;

import account.SavingAccount;
import constant.ErrCode;
import exceptions.funcException.WithdrawException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class SavingBank extends Bank {

    public void withdraw(SavingAccount account) throws WithdrawException{
        // Account’s withdrawal method is implemented a little differently.
        BigDecimal goalAmount = account.getTargetAmount();
        if(account.getBalance().compareTo(goalAmount) < 0){
            throw new WithdrawException(String.format(ErrCode.E104.getErrMsg(), df.format(goalAmount)));
        }
    }
    @Override
    public SavingAccount createAccount() throws InputMismatchException {
        try{
        	// Account number
            // The account number is a number in the format "0000" + incremented seq.
            String accNo = String.format(new DecimalFormat("0000").format(++seq));
            String owner = askInput("\nPlease enter the owner's name.", "");
            BigDecimal amount = askInput("\nPlease enter your initial deposit amount.", BigDecimal.ZERO);
            BigDecimal goalAmount = askInput("\nPlease enter a target amount.", BigDecimal.ZERO);
            SavingAccount account = new SavingAccount(accNo, owner, amount, goalAmount);
            CentralBank.getInstance().getAccountList().add(new SavingAccount(accNo, owner, amount, goalAmount));
            System.out.printf("Your account has been issued for %s.", owner);
            return account;
        }catch (InputMismatchException ime){
            if(seq > 0) seq--;
            throw ime;
        }catch (Exception e){
            throw e;
        }
    }
}