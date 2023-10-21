package bank;

import java.math.BigDecimal;

public class SavingInterestCalculator implements InterestCalculator{
    @Override
    public BigDecimal getInterest(BigDecimal balance) {
        BigDecimal interest;

        // // return interest rates for saving accounts
        if(balance.compareTo(BigDecimal.valueOf(1000000)) >= 0)
            interest = BigDecimal.valueOf(0.06);
        else
            interest = BigDecimal.valueOf(0.055);
        return balance.multiply(interest);
    }
}
