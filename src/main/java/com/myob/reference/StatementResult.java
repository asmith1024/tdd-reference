package com.myob.reference;

import java.math.BigDecimal;
import java.util.Optional;

public class StatementResult {

    private final Optional<Statement> statement;
    private final BigDecimal outcome;

    public StatementResult(Optional<Statement> statement, BigDecimal outcome) {

        this.statement = statement;
        this.outcome = outcome;
    }

    public Optional<Statement> getStatement() {
        return statement;
    }

    public BigDecimal getOutcome() {
        return outcome;
    }
}
