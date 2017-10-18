package com.myob.reference;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class StatementParserTest {

    private final static String ADD_STRING = "1 + 1";
    private final static String BAD_STRING = "1 = 2";

    StatementParser parser;

    @Before
    public void setUp() {
        parser = new StatementParser();
    }

    @Test
    public void shouldParseAddition() throws Exception {
        Optional<Statement> s = parser.parse(ADD_STRING);

        assertTrue(s.isPresent());
    }

    @Test
    public void shouldNotParseUnknownOperator() throws Exception {
        Optional<Statement> s = parser.parse(BAD_STRING);

        assertFalse(s.isPresent());

    }

}