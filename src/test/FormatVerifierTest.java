package test;

import main.utility.FormatVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatVerifierTest {

	@Test
	void containsOnlyDigits() {
		assertTrue(FormatVerifier.containsOnlyDigits("1234567890"), "Should return true with only integers");
		assertTrue(FormatVerifier.validateStringName("1"), "Should return true with 1 character");
		assertFalse(FormatVerifier.containsOnlyDigits("-100"), "Should return false with negative sign");
		assertFalse(FormatVerifier.containsOnlyDigits("abcdefghijklmnopqrstuvwxyz"),
				"Should return false with lowercase alphabets");
		assertFalse(FormatVerifier.containsOnlyDigits("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				"Should return false with uppercase alphabets");
		assertFalse(FormatVerifier.containsOnlyDigits("+-*/()"), "Should return false with symbols");
		assertFalse(FormatVerifier.containsOnlyDigits(""), "Should return false with empty string");
	}

	@Test
	void validateStringName() {
		assertTrue(FormatVerifier.validateStringName("abcdefghijklmnopqrstuvwxyz"),
				"Should return true with only lowercase alphabets");
		assertTrue(FormatVerifier.validateStringName("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				"Should return true with uppercase alphabets");
		assertTrue(FormatVerifier.validateStringName("1234567890"), "Should return true with only integers");
		assertTrue(FormatVerifier.validateStringName("1"), "Should return true with 1 character");
		assertFalse(FormatVerifier.validateStringName("+-*/()"), "Should return false with symbols");
		assertFalse(FormatVerifier.validateStringName(""), "Should return false with empty string");
	}

	@Test
	void validateStringPassword() {
		assertTrue(FormatVerifier.validateStringPassword("abcdefghijklmnopqrstuvwxyz"),
				"Should return true with only lowercase alphabets");
		assertTrue(FormatVerifier.validateStringPassword("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				"Should return true with uppercase alphabets");
		assertTrue(FormatVerifier.validateStringPassword("1234567890"), "Should return true with only integers");
		assertTrue(FormatVerifier.validateStringPassword("0_0"), "Should return true with underscore");
		assertTrue(FormatVerifier.validateStringPassword("0__0"), "Should return true with underscores");
		assertTrue(FormatVerifier.validateStringPassword("1"), "Should return true with 1 character");
		assertFalse(FormatVerifier.validateStringPassword("+-*/()"), "Should return false with symbols");
		assertFalse(FormatVerifier.validateStringPassword(""), "Should return false with empty string");
	}

	@Test
	void validateBigDecimalFormat() {
		assertTrue(FormatVerifier.validateBigDecimalFormat("0"), "Should return true with zero");
		assertTrue(FormatVerifier.validateBigDecimalFormat("1234567890"), "Should return true with only integers");
		assertTrue(FormatVerifier.validateBigDecimalFormat("-100"), "Should return true with negative number");
		assertTrue(FormatVerifier.validateBigDecimalFormat("100.123"), "Should return true with floating point value");
		assertTrue(FormatVerifier.validateBigDecimalFormat("-100.123"),
				"Should return true with negative floating point value");
		assertFalse(FormatVerifier.validateBigDecimalFormat("abcdefghijklmnopqrstuvwxyz"),
				"Should return false with lowercase alphabets");
		assertFalse(FormatVerifier.validateBigDecimalFormat("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				"Should return false with uppercase alphabets");
		assertFalse(FormatVerifier.validateBigDecimalFormat("+-*/()"), "Should return false with symbols");
		assertFalse(FormatVerifier.validateBigDecimalFormat(""), "Should return false with empty string");
	}

	@Test
	void validateBigDecimal() {
		assertTrue(FormatVerifier.validateBigDecimal("0"), "Should return true with zero");
		assertTrue(FormatVerifier.validateBigDecimal("1234567890"), "Should return true with only integers");
		assertTrue(FormatVerifier.validateBigDecimal("100.123"), "Should return true with floating point value");
		assertFalse(FormatVerifier.validateBigDecimal("00"), "Should return false with extra zero in front");
		assertFalse(FormatVerifier.validateBigDecimal("-100"), "Should return false with negative number");
		assertFalse(FormatVerifier.validateBigDecimal("-100.123"),
				"Should return false with negative floating point value");
		assertFalse(FormatVerifier.validateBigDecimal("abcdefghijklmnopqrstuvwxyz"),
				"Should return false with lowercase alphabets");
		assertFalse(FormatVerifier.validateBigDecimal("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
				"Should return false with uppercase alphabets");
		assertFalse(FormatVerifier.validateBigDecimal("+-*/()"), "Should return false with symbols");
		assertFalse(FormatVerifier.validateBigDecimal(""), "Should return false with empty string");
	}
}