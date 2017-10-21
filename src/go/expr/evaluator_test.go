package expr

import (
	"fmt"
	"testing"
)

func ExampleEvaluate() {
	value, _ := Evaluate("(1 + 3) * 100 / (32 + 8)")
	fmt.Println(value)
	// Output: 10
}

const (
	IdentityMultiply       = "1 * 1"
	IdentityAdd            = "1 + 0"
	IdentityAddCommutative = "0 + 1"
	IdentitySubtract       = "1 - 0"
	IdentityDivide         = "1 / 1"
)

func TestIdentities(t *testing.T) {
	value, err := Evaluate(IdentityMultiply)
	PrintOnFail(t, IdentityMultiply, value, 1, err)

	value, err = Evaluate(IdentityAdd)
	PrintOnFail(t, IdentityAdd, value, 1, err)

	value, err = Evaluate(IdentityAddCommutative)
	PrintOnFail(t, IdentityAddCommutative, value, 1, err)

	value, err = Evaluate(IdentitySubtract)
	PrintOnFail(t, IdentitySubtract, value, 1, err)

	value, err = Evaluate(IdentityDivide)
	PrintOnFail(t, IdentityDivide, value, 1, err)
}

func PrintOnFail(t *testing.T, expression string, value, expected float64, err error) {
	if err != nil {
		t.Error("Expected no error for", expression, "got", err)
	}
	if value != expected {
		t.Error("Expected", expected, "for", expression, "got", value)
	}
}

const (
	P1Equals6 = "2 * (1 + 2)"
	P2Equals6 = "(1 + 2) * 2"
	P3Equals6 = "10 * (6 - 3) / (2 + 3)"
	P4Equals6 = "(6 + 4) * 3 / (100 - 95)"
	P5Equals6 = "(2 * (2 + 1)) / 1"
	P6Equals6 = "((2 + 1) * 10) / (4 + 1)"
)

func TestParentheses(t *testing.T) {
	value, err := Evaluate(P1Equals6)
	PrintOnFail(t, P1Equals6, value, 6, err)

	value, err = Evaluate(P2Equals6)
	PrintOnFail(t, P2Equals6, value, 6, err)

	value, err = Evaluate(P3Equals6)
	PrintOnFail(t, P3Equals6, value, 6, err)

	value, err = Evaluate(P4Equals6)
	PrintOnFail(t, P4Equals6, value, 6, err)

	value, err = Evaluate(P5Equals6)
	PrintOnFail(t, P5Equals6, value, 6, err)

	value, err = Evaluate(P2Equals6)
	PrintOnFail(t, P2Equals6, value, 6, err)

	value, err = Evaluate(P6Equals6)
	PrintOnFail(t, P6Equals6, value, 6, err)
}

const (
	Bad1 = "Bwahaha hahaha ha"
	Bad2 = "1 + fubar"
	Bad3 = "1 & 2"
	Bad4 = "(((((((("
	Bad5 = "1 + (2 +"
	Bad6 = "(2 + 3 * 1"
	Bad7 = "	   "
)

const (
	Error1 = "Unknown operator hahaha"
	Error2 = "strconv.ParseFloat: parsing \"fubar\": invalid syntax"
	Error3 = "Unknown operator &"
	Error4 = "Invalid parenthetical expression: ((((((("
	Error5 = "Invalid parenthetical expression: 2 +"
	Error6 = "Invalid parenthetical expression: 2 + 3 * 1"
	Error7 = "No token in expression"
)

// we are implicitly testing panics
func TestHandlingBadThings(t *testing.T) {
	_, err := Evaluate(Bad1)
	if err.Error() != Error1 {
		t.Error("Expected", Error1, "got", err.Error())
	}

	_, err = Evaluate(Bad2)
	if err.Error() != Error2 {
		t.Error("Expected", Error2, "got", err.Error())
	}

	_, err = Evaluate(Bad3)
	if err.Error() != Error3 {
		t.Error("Expected", Error3, "got", err.Error())
	}

	_, err = Evaluate(Bad4)
	if err.Error() != Error4 {
		t.Error("Expected", Error4, "got", err.Error())
	}

	_, err = Evaluate(Bad5)
	if err.Error() != Error5 {
		t.Error("Expected", Error5, "got", err.Error())
	}

	_, err = Evaluate(Bad6)
	if err.Error() != Error6 {
		t.Error("Expected", Error6, "got", err.Error())
	}

	_, err = Evaluate(Bad7)
	if err.Error() != Error7 {
		t.Error("Expected", Error7, "got", err.Error())
	}
}

const (
	M1Equals0  = "1 % 1"
	M2Equals0  = "212 % 212"
	M1Equals1  = "1 % 2"
	M2Equals1  = "1 % 212"
	M1Equals53 = "53 % 106"
	M2Equals53 = "53 % 54"
)

func TestModulo(t *testing.T) {
	value, err := Evaluate(M1Equals0)
	PrintOnFail(t, M1Equals0, value, 0, err)

	value, err = Evaluate(M2Equals0)
	PrintOnFail(t, M2Equals0, value, 0, err)

	value, err = Evaluate(M1Equals1)
	PrintOnFail(t, M1Equals1, value, 1, err)

	value, err = Evaluate(M2Equals1)
	PrintOnFail(t, M2Equals1, value, 1, err)

	value, err = Evaluate(M1Equals53)
	PrintOnFail(t, M1Equals53, value, 53, err)

	value, err = Evaluate(M2Equals53)
	PrintOnFail(t, M2Equals53, value, 53, err)
}
