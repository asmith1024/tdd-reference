package com.myob.reference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LedgerTest {

    private final BigDecimal TWO = new BigDecimal(2);

    private Ledger ledger;


    @Mock
    private Calculator calculator;

    @Before
    public void setUp() {
        ledger = new Ledger(calculator);
    }

    @Test
    public void shouldCalculateAddition() {
        when(calculator.add(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(TWO);

        assertThat(ledger.calculate("1 + 1"), is(TWO));

        verify(calculator, times(1)).add(any(), any());
    }


    @Test
    public void shouldCalculateSubtraction() {

        when(calculator.subtract(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(ZERO);

        assertThat(ledger.calculate("1 - 1"), is(ZERO));

        verify(calculator, times(1)).subtract(any(), any());


    }

    @Test
    public void shouldCalculateMultiplication() {

        when(calculator.multiply(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(ONE);

        assertThat(ledger.calculate("1 * 1"), is(ONE));

        verify(calculator, times(1)).multiply(any(), any());
    }

    @Test
    public void shouldCalculateDivision() {
        when(calculator.divide(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(ONE);

        assertThat(ledger.calculate("1 / 1"), is(ONE));

        verify(calculator, times(1)).divide(any(), any());
    }

}