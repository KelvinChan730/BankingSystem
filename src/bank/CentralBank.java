package bank;

import account.Account;

import java.util.ArrayList;

public class CentralBank {
	// A central bank class that manages accounts in a banking system.
    // Design with the Singleton pattern.
    // accountList(ArrayList of Accounts)
    // BANK_NAME(Bank name)
    private static CentralBank instance = new CentralBank();

    private static String BANK_NAME = "Central Bank";
    private ArrayList<Account> accountList = new ArrayList<>();

    private CentralBank(){}

    // getInstance function
    public static CentralBank getInstance(){
        if(instance == null)
            instance = new CentralBank();
        return instance;
    }

    // accountList getter/setter
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }
}
