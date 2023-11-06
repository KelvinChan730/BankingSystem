package main.bank.operation;

import java.math.BigDecimal;

public class TransactionOperation extends Operation {
	public BigDecimal amount;
	public TransactionOperation(BigDecimal amount) {
		this.amount = amount;
	}
}
