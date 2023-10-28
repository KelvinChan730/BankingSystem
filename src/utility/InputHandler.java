package utility;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.IOErrorCode;
import exception.IOFunctionException;

public class InputHandler {
	
	public boolean validateStringName(String input) {
		// Match English letters and numbers using regular expressions.
		String pattern = "^[a-zA-Z0-9]+$";
		return input.matches(pattern);
	}

	public boolean validateBigDecimalFormat(String input) {
		String pattern = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
		return input.matches(pattern);

	}

	public boolean validateBigDecimal(String parameter) {
		// Check if the parameter is of type BigDecimal.
		if (validateBigDecimalFormat(parameter)) {
			BigDecimal number = new BigDecimal(parameter);
			return number.compareTo(BigDecimal.ZERO) >= 0;
		}
		return false;
	}

	public boolean validateStringPassword(String input) {
		// Match English letters, numbers, and underscores using regular expressions.
		String pattern = "^[a-zA-Z0-9_]+$";
		return input.matches(pattern);
	}

	public boolean containsOnlyDigits(String str) {
	    return str.matches("\\d+");
	}
	
	public String promptAccNo() throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your account number: ");
		String input = scanner.nextLine();
		
		// check account number format
		if(!containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E101);
		}
		
		return input;
	}
	
	public String promptTransferAccNo() throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the account number you wish to transfer to: ");
		String input = scanner.nextLine();
		
		// check account number format
		if(!containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E101);
		}
		
		return input;
	}
	
	public String promptPassword() throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your password: ");
		String input = scanner.nextLine();
		
		// check password format
		if(!validateStringPassword(input)) {
			throw new IOFunctionException(IOErrorCode.E102);
		}
		
		return input;
	}
	
	public String promptName() throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		String input = scanner.nextLine();
		
		// check name format
		if(!validateStringName(input)) {
			throw new IOFunctionException(IOErrorCode.E103);
		}
		
		return input;
	}
	
	public String promptAmount(String verb) throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.printf("Please enter amount you wish to %s: ", verb);
		String input = scanner.nextLine();
		
		// check withdraw amount format
		if(!validateBigDecimal(input)) {
			throw new IOFunctionException(IOErrorCode.E104);
		}
		
		return input;
	}
	
	public int promptMenuOption() throws IOFunctionException {
		Scanner scanner = new Scanner(System.in);
		System.out.println( "\n1. show account info\n"
							+ "2. withdrawal\n"
							+ "3. deposit\n"
							+ "4. transfer\n"
							+ "5. loan\n"
							+ "6. payback loan\n"
							+ "7. exit");
		int input = 0;
		try {
			input = scanner.nextInt();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E105);
		}
		
		// check withdraw amount format
		if(input < 1 || input > 7) {
			throw new IOFunctionException(IOErrorCode.E120);
		}
		
		return input;
	}
}
