# TDD exercise.

This repo contains a naive implementation of a ledger and calculator, whose input is a literal
string representation of some calculation to be performed.

    1 + 1
    7 * 5

etc

the standard + - * / operators are implemented.

There are tests for the existing classes implemented with Mockito.

## Challenge.

### Add a modulus operator

First up take the existing code and add the modulo operator `%`

Observe the tests and classes that need to change.


### Refactor/Rewrite with TDD and without Mockito/other mocking framework

Aim to re-implement the initial 4 operators (+-/*) by using a TDD approach. 

There will undoubtedly be more classes, and the only constraint is the input string literals.

Then, again add the modulo operator.

Observe the tests and classes that need to change.
