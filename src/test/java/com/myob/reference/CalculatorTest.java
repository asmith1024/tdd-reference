package com.myob.reference;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private final BigDecimal TWO = new BigDecimal(2);


    private Calculator calculator;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
    }

    @Test
    public void shouldAddCorrectly() {
        assertThat(calculator.add(ONE, ONE), is(TWO));
    }


    @Test
    public void shouldSubtractCorrectly() {
        assertThat(calculator.subtract(ONE, ONE), is(ZERO));
    }


    @Test
    public void shouldMultiplyCorrectly() {
        assertThat(calculator.multiply(ONE, ONE), is(ONE));

    }

    @Test
    public void shouldDivideCorrectly() {
        assertThat(calculator.divide(ONE, ONE), is(ONE));
    }
}
