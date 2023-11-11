package main.bank.operation;

public class DetailedOperationReturn extends OperationReturn {
	public String detail;
	
	public DetailedOperationReturn(boolean success, String detail) {
		super(success);
		this.detail = detail;
	}
}
