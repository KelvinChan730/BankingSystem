package account;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class UserLoanList {
	private static HashMap<String, Loan> loadRecordList = new HashMap<>();
	protected static int seq = 0;

	public static HashMap<String, Loan> getList() {
		return loadRecordList;
	}

	// add loan record
	public static void addLoanRecord(Loan loan) {
		String loanId = String.format(new DecimalFormat("0000").format(++seq));
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
