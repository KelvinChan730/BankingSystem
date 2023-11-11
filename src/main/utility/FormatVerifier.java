package main.utility;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class FormatVerifier {
	private static Pattern stringOnlyDigitsPattern = null;
	private static Pattern stringNamePattern = null;
	private static Pattern stringPasswordPattern = null;
	private static Pattern bigDecimalFormatPattern = null;

	public static boolean containsOnlyDigits(String input) {
		if (stringOnlyDigitsPattern == null)
			stringOnlyDigitsPattern = Pattern.compile("\\d+");
		return stringOnlyDigitsPattern.matcher(input).matches();
	}

	// Check string only contain English letters and numbers using regular
	// expressions.
	public static boolean validateStringName(String input) {
		if (stringNamePattern == null)
			stringNamePattern = Pattern.compile("^[a-zA-Z0-9]+$");
		return stringNamePattern.matcher(input).matches();
	}

	// Check if string only contains English letters, numbers, and underscores using
	// regular expressions.
	public static boolean validateStringPassword(String input) {
		if (stringPasswordPattern == null)
			stringPasswordPattern = Pattern.compile("^[a-zA-Z0-9_]+$");
		return stringPasswordPattern.matcher(input).matches();
	}

	// Check if match BigDecimal format using regular expressions.
	public static boolean validateBigDecimalFormat(String input) {
		if (bigDecimalFormatPattern == null)
			bigDecimalFormatPattern = Pattern.compile("^(?:-?)(?:0|(?:[1-9][0-9]*))(?:\\.[0-9]+)?$");
		return bigDecimalFormatPattern.matcher(input).matches();
	}

	// Check if the parameter is of type BigDecimal and non-zero.
	public static boolean validateBigDecimal(String parameter) {
		if (validateBigDecimalFormat(parameter)) {
			try {
				BigDecimal number = new BigDecimal(parameter);
				return number.compareTo(BigDecimal.ZERO) >= 0;
			} catch (NumberFormatException ex) { // never get into here
				return false;
			}
		}
		return false;
	}
}
