package com.myob.reference;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OperationsTest {

    private final BigDecimal TWO = new BigDecimal(2);


    private Operations operations;

    @Before
    public void setUp() {
        this.operations = new Operations();
    }

    @Test
    public void shouldAddCorrectly() {
        assertThat(operations.add(ONE, ONE), is(TWO));
    }


    @Test
    public void shouldSubtractCorrectly() {
        assertThat(operations.subtract(ONE, ONE), is(ZERO));
    }


    @Test
    public void shouldMultiplyCorrectly() {
        assertThat(operations.multiply(ONE, ONE), is(ONE));

    }

    @Test
    public void shouldDivideCorrectly() {
        assertThat(operations.divide(ONE, ONE), is(ONE));
    }


}



