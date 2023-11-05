package main.utility;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.exception.IOErrorCode;
import main.exception.IOFunctionException;
import main.constant.Currency;

public class InputHandler {
	Scanner scanner = new Scanner(System.in);

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
		System.out.println("Please enter your account number: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		// check account number format
		if(!containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		return input;
	}
	
	public String promptTransferAccNo() throws IOFunctionException {
		System.out.println("Please enter the account number you wish to transfer to: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		// check account number format
		if(!containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		return input;
	}
	
	public String promptPassword() throws IOFunctionException {
		System.out.println("Please enter your password: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1020);
		}
		
		// check password format
		if(!validateStringPassword(input)) {
			throw new IOFunctionException(IOErrorCode.E1020);
		}
		
		return input;
	}
	
	public String promptName() throws IOFunctionException {
		System.out.println("Please enter your name: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1030);
		}
		
		// check name format
		if(!validateStringName(input)) {
			throw new IOFunctionException(IOErrorCode.E1030);
		}
		
		return input;
	}
	
	public String promptPhoneNo() throws IOFunctionException {
		System.out.println("Please enter your phone number: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1060);
		}
		
		// check name format
		if(!validateStringName(input)) {
			throw new IOFunctionException(IOErrorCode.E1060);
		}
		
		return input;
	}
	
	public String promptAmount(String verb) throws IOFunctionException {
		System.out.printf("Please enter amount you wish to %s: ", verb);
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1040);
		}
		
		// check withdraw amount format
		if(!validateBigDecimal(input)) {
			throw new IOFunctionException(IOErrorCode.E1040);
		}
		
		return input;
	}
	
	public int promptAccountOption() throws IOFunctionException {
		System.out.println( "\nSelect an account type to create\n"
							+ "1. normal account\n"
							+ "2. saving account\n"
							+ "3. foreign currency account\n"
							+ "4. exit");
		int input = 0;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextInt();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}
		
		// check withdraw amount format
		if(input < 1 || input > 4) {
			throw new IOFunctionException(IOErrorCode.E1200);
		}
		
		return input;
	}
	
	public int promptMenuOption() throws IOFunctionException {
		System.out.println( "\nSelect an option\n"
							+ "Menu:\n"
							+ "1. show account info\n"
							+ "2. withdrawal\n"
							+ "3. deposit\n"
							+ "4. transfer\n"
							+ "5. loan\n"
							+ "6. payback loan\n"
							+ "7. Change Account Currency Type"
							+ "8. exit");
		int input = 0;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextInt();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}
		
		// check withdraw amount format
		if(input < 1 || input > 8) {
			throw new IOFunctionException(IOErrorCode.E1210);
		}
		
		return input;
	}

		public String promptLoanID() throws IOFunctionException {
		System.out.println("Please enter your Loan ID: ");
		
		String input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextLine();
		} catch(InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		// Load ID should only contain digits
		if(!containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}
		
		return input;
	}

	public Currency promptCurrencyType() throws IOFunctionException{
		System.out.println("\nSelect an Foreign Currency Type\n");
		int i;
		for (i=1; i<=Currency.values().length; i++){
			System.out.println(i + ". " + Currency.values()[i-1]);
		}
		int input;
		try /*(Scanner scanner = new Scanner(System.in))*/ {
			input = scanner.nextInt();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}
		if (input-1 < 0 || input-1 >= Currency.values().length) {
			throw new IOFunctionException(IOErrorCode.E1220);
		}
		return Currency.values()[input];
	}
}
