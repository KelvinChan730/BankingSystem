package main.db;

import java.util.HashMap;

import main.account.BaseAccount;

public class AccountList {
	private static HashMap<String, BaseAccount> accountList = new HashMap<>();

	public static HashMap<String, BaseAccount> getList() {
		return accountList;
	}

	// add account
	public static void addAccount(BaseAccount account) {
		accountList.put(account.getAccNo(), account);
	}

	// return if account exist
	public static boolean hasAccount(String accNo) {
		return accountList.containsKey(accNo);
	}

	// find account by account number
	public static BaseAccount findAccount(String accNo) {
		if (accountList.containsKey(accNo))
			return accountList.get(accNo);
		return null;
	}

	public static void deleteAccount(String accNo, String password) {
		String key = accNo;
		accountList.remove(key);
	}
}
