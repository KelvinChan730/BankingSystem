package main.account;

import java.util.ArrayList;
import java.util.HashMap;

import main.utility.Formatter;

public class UserLoanList {
	private static HashMap<String, Loan> loadRecordList = new HashMap<>();
	protected static int sequence = 1;

	public static HashMap<String, Loan> getList() {
		return loadRecordList;
	}

	// add loan record
	public static void addLoanRecord(Loan loan) {
		String loanId = Formatter.formatSequence(sequence++);
		loadRecordList.put(loanId, loan);
		Account user = AccountList.findAccount(loan.getAccountId());
		user.addLoanRecord(loanId);
	}

	// return if account loan exist
	public static boolean hasLoan(String accNo) {
		return loadRecordList.containsKey(accNo);
	}
	
	// find account by account number
		public static Loan findLoadRecord(String loanId) {
			if (loadRecordList.containsKey(loanId))
				return loadRecordList.get(loanId);
			return null;
		}

	// find account by account number
	public static ArrayList<Loan> findUserAllLoanRecord(String accNo) {
		if (AccountList.hasAccount(accNo)) {
			Account user = AccountList.findAccount(accNo);
			ArrayList<Loan> userLoanList = new ArrayList<>();
			for (String loanId: user.getLoanRecordId())
				userLoanList.add(findLoadRecord(loanId));
			return userLoanList;
		}
		return null;
	}
}
