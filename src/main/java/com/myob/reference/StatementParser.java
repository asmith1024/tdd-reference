package com.myob.reference;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatementParser implements InputParser {

    private final static Pattern PATTERN = Pattern.compile("(\\d+) ([\\+\\-\\*/]) (\\d+)");


    protected final static Operation ADD = Operations::add;
    protected final static Operation SUBTRACT = Operations::subtract;
    protected final static Operation MULTIPLY = Operations::multiply;
    protected final static Operation DIVIDE = Operations::divide;


    public Optional<Statement> parse(String input) {

        Matcher m = PATTERN.matcher(input);

        if(m.matches()) {
            BigDecimal left = new BigDecimal(m.group(1));
            BigDecimal right = new BigDecimal(m.group(3));

            switch (m.group(2)) {
                case "+":
                    return Optional.of(new TwoNumberStatement(left, ADD, right));
                case "-":
                    return Optional.of(new TwoNumberStatement(left, SUBTRACT, right));
                case "*":
                    return Optional.of(new TwoNumberStatement(left, MULTIPLY, right));
                case "/":
                    return Optional.of(new TwoNumberStatement(left, DIVIDE, right));


            }
        }
        return Optional.empty();
    }
}
