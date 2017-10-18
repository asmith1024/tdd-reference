package com.myob.reference;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class StatementParserTest {

    private final static String ADD_STRING = "1 + 1";
    private final static String SUBTRACT_STRING = "1 - 1";
    private final static String MULTIPLY_STRING = "1 * 1";
    private final static String DIVIDE_STRING = "1 / 1";
    private final static String BAD_STRING = "1 = 2";

    private TwoNumberStatement EXPECTED_ADDITION_STATEMENT = new TwoNumberStatement(BigDecimal.ONE, StatementParser.ADD, BigDecimal.ONE);
    private TwoNumberStatement EXPECTED_SUBTRACTION_STATEMENT = new TwoNumberStatement(BigDecimal.ONE, StatementParser.SUBTRACT, BigDecimal.ONE);
    private TwoNumberStatement EXPECTED_MULTIPLICATION_STATEMENT = new TwoNumberStatement(BigDecimal.ONE, StatementParser.MULTIPLY, BigDecimal.ONE);
    private TwoNumberStatement EXPECTED_DIVISION_STATEMENT = new TwoNumberStatement(BigDecimal.ONE, StatementParser.DIVIDE, BigDecimal.ONE);

    StatementParser parser;

    @Before
    public void setUp() {
        parser = new StatementParser();
    }

    @Test
    public void shouldParseAddition() throws Exception {
        assertThat(parser.parse(ADD_STRING).get(), isStatement(EXPECTED_ADDITION_STATEMENT));
    }


    @Test
    public void shouldParseSubtraction() throws Exception{
        assertThat(parser.parse(SUBTRACT_STRING).get(), isStatement(EXPECTED_SUBTRACTION_STATEMENT));
    }


    @Test
    public void shoudParseMultiplication() throws Exception {
        assertThat(parser.parse(MULTIPLY_STRING).get(), isStatement(EXPECTED_MULTIPLICATION_STATEMENT));
    }

    @Test
    public void shouldParseDivision() throws Exception {
        assertThat(parser.parse(DIVIDE_STRING).get(), isStatement(EXPECTED_DIVISION_STATEMENT));
    }

    @Test
    public void shouldNotParseUnknownOperator() throws Exception {
        Optional<Statement> s = parser.parse(BAD_STRING);

        assertFalse(s.isPresent());
    }


    private Matcher isStatement(TwoNumberStatement statement) {
        return new TypeSafeMatcher<TwoNumberStatement>() {


            @Override
            public void describeTo(Description description) {
                description.appendText("expected " + statement);

            }

            @Override
            protected boolean matchesSafely(TwoNumberStatement expected) {
                return statement.getLeft().equals(expected.getLeft())
                        && statement.getRight().equals(expected.getRight())
                        && statement.getOperation().equals(expected.getOperation());
            }
        };
    }


}