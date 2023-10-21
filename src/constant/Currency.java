package constant;

import java.math.BigDecimal;

// Foreign currencies and exchange rates with real life data
public enum Currency {
	HKD("HKD", new BigDecimal(1.0000)),
	YMB("YMB", new BigDecimal(1.0823)),
	USD("USD", new BigDecimal(7.8770)),
	AUD("AUD", new BigDecimal(5.0250)),
	CAD("CAD", new BigDecimal(5.8170)),
	EUR("EUR", new BigDecimal(8.4140)),
	GBP("GBP", new BigDecimal(9.7400)),
	JPY("JPY", new BigDecimal(0.0531)),
	TWD("TWD", new BigDecimal(0.2570));
	
	public final String name;
    public final BigDecimal exchangeRate; // value of foreign currency in HKD
    
    Currency(String name, BigDecimal rate) {
		this.name = name;
		this.exchangeRate = rate;
	}
    
    public String getName() {
    	return name;
    }
    
    public BigDecimal getExchangeRate() {
    	return exchangeRate;
    }
}
