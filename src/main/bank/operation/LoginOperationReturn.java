package main.bank.operation;

import main.account.BaseAccount;

public class LoginOperationReturn extends OperationReturn {
	public BaseAccount ac;
	
	public LoginOperationReturn(boolean success, BaseAccount ac) {
		super(success);
		this.ac = ac;
	}
}
