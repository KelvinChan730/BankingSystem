package bank;

import account.AccountList;

public class CentralBank {
	// A central bank class that manages accounts in a banking system.
    // Design with the Singleton pattern.
    // accountList(ArrayList of Accounts)
    // BANK_NAME(Bank name)
    private static CentralBank instance = new CentralBank();

	private static String BANK_NAME = "Central Bank of Hong Kong";
    private AccountList accountList = new AccountList();

    private CentralBank(){}

    // getInstance function
    public static CentralBank getInstance(){
        if(instance == null)
            instance = new CentralBank();
        return instance;
    }

    // accountList getter/setter
    public AccountList getAccountList() {
        return accountList;
    }
}
