package com.myob.reference;

import java.math.BigDecimal;

@FunctionalInterface
public interface Operation {

    BigDecimal execute(BigDecimal left, BigDecimal right);
}
