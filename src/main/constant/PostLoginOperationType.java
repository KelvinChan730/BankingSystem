package main.constant;

public enum PostLoginOperationType implements IOperationType {
    WITHDRAW,
    DEPOSIT,
    TRANSFER,
    LOAN,
    PAYBACK,
    ACCOUNT_DELETION,
    SHOW_DETAIL;
}
