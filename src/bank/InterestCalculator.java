package bank;

import java.math.BigDecimal;

public interface InterestCalculator {
    // Declare a method (BigDecimal getInterest(balance)) that returns the amount of interest on the balance.
    BigDecimal getInterest(BigDecimal balance);
}
