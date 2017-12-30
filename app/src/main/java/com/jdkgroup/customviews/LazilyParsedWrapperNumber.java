package com.jdkgroup.customviews;

import java.math.BigDecimal;

/**
 * Created by kamlesh on 8/18/2017.
 */

public class LazilyParsedWrapperNumber {

    private final String value;

    public LazilyParsedWrapperNumber(String value) {
        this.value = value;
    }

    public int intValue() {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(value);
            } catch (NumberFormatException nfe) {
                return new BigDecimal(value).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return new BigDecimal(value).longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(value);
    }

    public double doubleValue() {
        return Double.parseDouble(value);
    }

}
