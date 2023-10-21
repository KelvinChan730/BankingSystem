package account;

import java.util.ArrayList;

public class AccountList {
	private static ArrayList<Account> bankAccountList = new ArrayList<>();
	public static void addAccount(Account account) {
		bankAccountList.add(account);
	}
	public static ArrayList<Account> getList() {
		return bankAccountList;
	}
}
