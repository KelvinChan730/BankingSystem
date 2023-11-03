package exception;

public enum IOErrorCode implements IErrorCode{
    /**
     * Defining error codes
     */
    // Unexpected IO Error
    E1000("Unexpected IO Error"),
	
    // Input Format IO Error
    E1010("Invalid String Account Number Format"),
    E1020("Invalid String Password Format"),
    E1030("Invalid String Name Format"),
    E1040("Invalid String Currency Number Format"),
    E1050("Invalid Integer Number Format"),
    E1060("Invalid Phone Number Format"),
    
    // Input Value IO Error;
    E1200("Invalid Account Type Option"),
    E1210("Invalid Menu Option");
	
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
