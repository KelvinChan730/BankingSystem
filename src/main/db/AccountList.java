package main.db;

import java.util.HashMap;

import main.account.Account;

public class AccountList {
	private static HashMap<String, Account> accountList = new HashMap<>();

	public static HashMap<String, Account> getList() {
		return accountList;
	}

	// add account
	public static void addAccount(Account account) {
		accountList.put(account.getAccNo(), account);
	}

	// return if account exist
	public static boolean hasAccount(String accNo) {
		return accountList.containsKey(accNo);
	}

	// find account by account number
	public static Account findAccount(String accNo) {
		if (accountList.containsKey(accNo))
			return accountList.get(accNo);
		return null;
	}

	public static void deleteAccount(String accNo, String password) {
		String key = accNo;
		accountList.remove(key);
	}
}
