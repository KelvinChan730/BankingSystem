package constant;

public enum ErrCode {
    /**
     * Defining error codes
     */
    // Unexpected Error
    E000("E000", "Unexpected error"),

    // Custom Error
    E101("E101", "Error processing a withdrawal"),
    E102("E102", "Error processing a deposit"),
    E103("E103", "Error processing a transfer"),
    E104("E104", "Savings account balances can only be withdrawn if the balance is above the target amount (%s won)."),

    // IO Error
    E201("E201", "Input value error occurred");

    public final String code;
    public final String errMsg;

    ErrCode(String code, String errMsg){
        this.code = code;
        this.errMsg = errMsg;
    }

    public String getCode(){
        return code;
    }

    public String getErrMsg(){
        return errMsg;
    }
}
