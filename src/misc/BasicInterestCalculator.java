package misc;

import java.math.BigDecimal;

public class BasicInterestCalculator implements InterestCalculator{
    @Override
    public BigDecimal getInterest(BigDecimal balance) {
        BigDecimal interest;

        // return interest rates for deposit accounts
        if(balance.compareTo(BigDecimal.valueOf(1000000))>=0)
            interest = BigDecimal.valueOf(0.05);
        else if(balance.compareTo(BigDecimal.valueOf(500000))>=0)
            interest = BigDecimal.valueOf(0.04);
        else if(balance.compareTo(BigDecimal.valueOf(100000))>=0)
            interest = BigDecimal.valueOf(0.03);
        else if(balance.compareTo(BigDecimal.valueOf(10000))>=0)
            interest = BigDecimal.valueOf(0.02);
        else
            interest = BigDecimal.valueOf(0.01);
        return balance.multiply(interest);
    }
}
