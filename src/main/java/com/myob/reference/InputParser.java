package com.myob.reference;

import java.util.Optional;

@FunctionalInterface
public interface InputParser {

    Optional<Statement> parse(String input);
}
