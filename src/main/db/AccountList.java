package main.db;

import java.util.HashMap;
import java.util.Map;

import main.account.BaseAccount;

public class AccountList {
	private static Map<String, BaseAccount> accountList = new HashMap<>();

	public static Map<String, BaseAccount> getList() {
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

	public static boolean deleteAccount(String accNo, String password) {
		if (accountList.containsKey(accNo)) {
			BaseAccount acc = accountList.get(accNo);
			if (acc.getPassword().equals(password)) {
				accountList.remove(accNo);
				return true;
			}
		}
		return false;
	}
}
