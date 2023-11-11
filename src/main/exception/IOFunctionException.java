package main.exception;

public class IOFunctionException extends Exception {
	private static final long serialVersionUID = 1L;

	public IOFunctionException(IOErrorCode err) {
		super(String.format("[%s] %s", err.getCode(), err.getErrMsg()));
	}
}