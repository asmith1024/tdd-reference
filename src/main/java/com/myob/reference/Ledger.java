package com.myob.reference;


import java.util.Optional;

public class Ledger {


    public StatementResult record(Optional<Statement> statement) {
        // we'd want to record it in an actual ledger eh?
        return statement.map(s -> s.execute()).orElse(new StatementResult(statement, null));
    }



}
