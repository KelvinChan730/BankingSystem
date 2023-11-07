package main.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Formatter {
	private static DecimalFormat sequenceFormat = new DecimalFormat("0000");
	public static DecimalFormat balanceFormat = new DecimalFormat("#.###");

	public static String formatSequence(int seq) {
		return sequenceFormat.format(seq);
	}

	public static String formatBalance(BigDecimal balance) {
		return balanceFormat.format(balance);
	}
}
