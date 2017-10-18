package com.myob.reference;

import java.math.BigDecimal;

public interface Statement {

    public BigDecimal execute(BigDecimal left, BigDecimal right);
}
