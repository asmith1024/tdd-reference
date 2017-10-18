package com.myob.reference;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static org.junit.Assert.assertThat;


public class LedgerTest {

    private final static String VALID_INPUT = "1 + 1";
    private final static String INVALID_INPUT = "1 = 2";

    private final static BigDecimal TWO = new BigDecimal(2);

    private final Statement VALID_STATEMENT = new TwoNumberStatement(ONE, Operations::add, ONE);

    private final StatementResult EXPECTED_VALID_RESULT = new StatementResult(Optional.of(VALID_STATEMENT), TWO);
    private final StatementResult EXPECTED_INVALID_RESULT = new StatementResult(Optional.empty(), null);


    @Test
    public void shouldHandleValidInput() {

        Ledger ledger = new Ledger();
        StatementResult result = ledger.record(Optional.of(VALID_STATEMENT));

        assertThat(result, isResult(EXPECTED_VALID_RESULT));
    }

    @Test
    public void shouldHandleInvalidInput() {
        Ledger ledger = new Ledger();
        StatementResult result = ledger.record(Optional.empty());

        assertThat(result, isResult(EXPECTED_INVALID_RESULT));
    }



    private Matcher<StatementResult> isResult(StatementResult result) {
        return new TypeSafeMatcher<StatementResult>() {
            @Override
            public void describeTo(final Description description) {
                description.appendText("expected ").appendValue(result.getStatement().toString() + " : " + result.getOutcome());
            }

            @Override
            protected void describeMismatchSafely(final StatementResult item, final
            Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendValue(item.getStatement().toString() + " : " + result.getOutcome());
            }

            @Override
            protected boolean matchesSafely(final StatementResult item) {
                return result.getStatement().equals(item.getStatement())
                    && ((result.getOutcome() == null && item.getOutcome() == null)
                        || result.getOutcome().equals(item.getOutcome()));
            }
        };
    }


}