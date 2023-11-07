package main.bank.operation;

public interface OperationFactory {

	public Operation constructOperation(Class<? extends Operation> type);
}
