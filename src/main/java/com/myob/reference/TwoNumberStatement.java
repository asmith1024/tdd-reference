package com.myob.reference;

import java.math.BigDecimal;
import java.util.Optional;


public class TwoNumberStatement implements Statement {

    private final BigDecimal left;
    private final Operation operation;
    private final BigDecimal right;

    public TwoNumberStatement(BigDecimal left, Operation operation, BigDecimal right) {

        this.left = left;
        this.operation = operation;
        this.right = right;
    }


    public StatementResult execute() {
        return new StatementResult(Optional.of(this), this.operation.execute(left, right));
    }

    public BigDecimal getLeft() {
        return left;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getRight() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", left, operation, right);
    }
}
