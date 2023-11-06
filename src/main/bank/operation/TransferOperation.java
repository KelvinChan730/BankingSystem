package main.bank.operation;

import main.utility.Authentication;

import java.math.BigDecimal;

public class TransferOperation extends TransactionOperation {
    public String targetAccNo;
    public TransferOperation(String targetAccNo, BigDecimal amount) {
        super(amount);
        this.targetAccNo = targetAccNo;
    }

}
