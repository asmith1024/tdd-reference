package com.myob.reference;


import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ledger {


    private final static Pattern PATTERN = Pattern.compile("(\\d+) ([\\+\\-\\*/]) (\\d+)");

    private final Calculator calculator;

    public Ledger(Calculator calculator) {

        this.calculator = calculator;
    }

    public BigDecimal calculate(String statement) {

        Matcher m = PATTERN.matcher(statement);

        if(m.matches()) {

            switch (m.group(2)) {
                case "+":
                    return add(new BigDecimal(m.group(1)), new BigDecimal(m.group(3)));
                case "-":
                    return subtract(new BigDecimal(m.group(1)), new BigDecimal(m.group(3)));
                case "*":
                    return multiply(new BigDecimal(m.group(1)), new BigDecimal(m.group(3)));
                case "/":
                    return divide(new BigDecimal(m.group(1)), new BigDecimal(m.group(3)));


            }
        }

        throw new IllegalArgumentException("unable to calculate statement " + statement);

    }

    private BigDecimal add(BigDecimal a, BigDecimal b) {
        return calculator.add(a, b);
    }

    private BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return calculator.subtract(a, b);
    }

    private BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return calculator.multiply(a, b);
    }

    private BigDecimal divide(BigDecimal a, BigDecimal b) {
        return calculator.divide(a, b);
    }



}
