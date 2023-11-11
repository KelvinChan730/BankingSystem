package main.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.constant.Currency;
import main.exception.IOErrorCode;
import main.exception.IOFunctionException;
import main.utility.FormatVerifier;

public class InputHandler {
	private Scanner scanner ;
	private ApplicationView view;
	private String input = "";

	public InputHandler(ApplicationView view, Scanner scanner) {
		this.view = view;
		this.scanner = scanner;
	}

	public String promptAccNo() throws IOFunctionException {
		view.display("Please enter your account number: ");

		try {
			input = scanner.nextLine();
		} catch (Exception ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		// check account number format
		if (!FormatVerifier.containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		return input;
	}

	public String promptTransferAccNo() throws IOFunctionException {
		view.display("Please enter the account number you wish to transfer to: ");

		try {
			input = scanner.nextLine();
		} catch (Exception ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		// check account number format
		if (!FormatVerifier.containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		return input;
	}

	public String promptPassword() throws IOFunctionException {
		view.display("Please enter your password: ");

		try {
			input = scanner.nextLine();
		} catch (Exception ex) {
			throw new IOFunctionException(IOErrorCode.E1020);
		}

		// check password format
		if (!FormatVerifier.validateStringPassword(input)) {
			throw new IOFunctionException(IOErrorCode.E1020);
		}

		return input;
	}

	public String promptName() throws IOFunctionException {
		view.display("Please enter your name: ");

		try {
			input = scanner.nextLine();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1030);
		}

		// check name format
		if (!FormatVerifier.validateStringName(input)) {
			throw new IOFunctionException(IOErrorCode.E1030);
		}

		return input;
	}

	public String promptPhoneNo() throws IOFunctionException {
		view.display("Please enter your phone number: ");

		try {
			input = scanner.nextLine();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1060);
		}

		// check Phone no format
		if (!FormatVerifier.containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1060);
		}

		return input;
	}

	public String promptAmount(String verb) throws IOFunctionException {
		view.display(String.format("Please enter amount you wish to %s: ", verb));

		try {
			input = scanner.nextLine();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1040);
		}

		// check withdraw amount format
		if (!FormatVerifier.validateBigDecimal(input)) {
			throw new IOFunctionException(IOErrorCode.E1040);
		}

		return input;
	}

	public int promptAccountOption() throws IOFunctionException {
		view.display( "\nSelect an account type to create\n"
					+ "1. normal account\n"
					+ "2. saving account\n"
					+ "3. foreign currency account\n"
					+ "4. exit");

		int intInput = 0;
		try {
			input = scanner.nextLine();
			intInput = Integer.parseInt(input);
		} catch (InputMismatchException | NumberFormatException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}

		// check withdraw amount format
		if (intInput < 1 || intInput > 4) {
			throw new IOFunctionException(IOErrorCode.E1200);
		}

		return intInput;
	}

	public int promptMenuOption() throws IOFunctionException {
		view.display( "\nSelect an option\n"
					+ "Menu:\n"
					+ "1. show account info\n"
					+ "2. withdrawal\n"
					+ "3. deposit\n"
					+ "4. transfer\n"
					+ "5. loan\n"
					+ "6. payback loan\n"
					+ "7. Change Account Currency Type\n"
					+ "8. exit\n");

		int intInput = 0;
		try {
			input = scanner.nextLine();
			intInput = Integer.parseInt(input);
		} catch (InputMismatchException | NumberFormatException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}

		// check withdraw amount format
		if (intInput < 1 || intInput > 8) {
			throw new IOFunctionException(IOErrorCode.E1210);
		}

		return intInput;
	}

	public String promptLoanID() throws IOFunctionException {
		view.display("Please enter your Loan ID: ");

		try {
			input = scanner.nextLine();
		} catch (InputMismatchException ex) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		// Load ID should only contain digits
		if (!FormatVerifier.containsOnlyDigits(input)) {
			throw new IOFunctionException(IOErrorCode.E1010);
		}

		return input;
	}

	public Currency promptCurrencyType() throws IOFunctionException {
		view.display("\nSelect an Foreign Currency Type\n");
		for (int i = 1; i <= Currency.values().length; i++) {
			view.display(i + ". " + Currency.values()[i - 1]);
		}

		int intInput = 0;
		try {
			input = scanner.nextLine();
			intInput = Integer.parseInt(input);
		} catch (InputMismatchException | NumberFormatException ex) {
			throw new IOFunctionException(IOErrorCode.E1050);
		}

		if (intInput < 1 || intInput > Currency.values().length) {
			throw new IOFunctionException(IOErrorCode.E1220);
		}
		return Currency.values()[intInput];
	}
}
