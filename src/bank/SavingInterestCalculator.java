package bank;

import java.math.BigDecimal;

public class SavingInterestCalculator implements InterestCalculator{
    @Override
    public BigDecimal getInterest(BigDecimal balance) {
        BigDecimal interest;

        // For a savings account, the interest rate is 50% 
        // for balances over 1 million won and 1% otherwise.
        if(balance.compareTo(BigDecimal.valueOf(1000000)) >= 0)
            interest = BigDecimal.valueOf(0.5);
        else
            interest = BigDecimal.valueOf(0.01);
        return balance.multiply(interest);
    }
}
