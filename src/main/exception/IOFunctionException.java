package main.exception;

public class IOFunctionException extends Exception {
	public IOFunctionException(IOErrorCode err) {
        super(String.format("[%s] %s", err.getCode(), err.getErrMsg()));
    }
}