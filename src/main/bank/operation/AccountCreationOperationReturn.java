package main.bank.operation;

public class AccountCreationOperationReturn extends OperationReturn {
	public String acNo;
	
	public AccountCreationOperationReturn(boolean success, String acNo) {
		super(success);
		this.acNo = acNo;
	}
}
