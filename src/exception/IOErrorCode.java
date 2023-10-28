package exception;

public enum IOErrorCode implements IErrorCode{
    /**
     * Defining error codes
     */
    // Unexpected IO Error
    E100("Unexpected IO Error"),
	
    // Input Format IO Error
    E101("Invalid String Account Number Format"),
    E102("Invalid String Password Format"),
    E103("Invalid String Name Format"),
    E104("Invalid String Currency Number Format"),
    E105("Invalid Integer Number Format"),
    
    // Input Value IO Error;
    E120("Invalid Menu Option");
	
	private String errMsg;
	
	IOErrorCode(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String getCode() {
		return this.name();
	}

	@Override
	public String getErrMsg() {
		return errMsg;
	}
}
