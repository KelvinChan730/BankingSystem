package bank;

import java.math.BigDecimal;

public class BasicInterestCalculator implements InterestCalculator{
    @Override
    public BigDecimal getInterest(BigDecimal balance) {
        BigDecimal interest;

        // For deposit accounts, the interest rate is 50% for balances over 10 million won,
        // 7% for 500 million won, 4% for 100 million won, 2% for 1 million won, and 1% for everything else.
        if(balance.compareTo(BigDecimal.valueOf(10000000))>=0)
            interest = BigDecimal.valueOf(0.5);
        else if(balance.compareTo(BigDecimal.valueOf(5000000))>=0)
            interest = BigDecimal.valueOf(0.07);
        else if(balance.compareTo(BigDecimal.valueOf(1000000))>=0)
            interest = BigDecimal.valueOf(0.04);
        else if(balance.compareTo(BigDecimal.valueOf(10000))>=0)
            interest = BigDecimal.valueOf(0.02);
        else
            interest = BigDecimal.valueOf(0.01);
        return balance.multiply(interest);
    }
}
