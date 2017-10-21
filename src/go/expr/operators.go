package expr

import (
	"fmt"
	"math"
)

func evaluateOperator(n *node) (float64, error) {
	if !n.isOperator() {
		return 0, fmt.Errorf("%v is not an operator", n.value)
	}
	switch n.value {
	case "+":
		return add(n.lhs, n.rhs)
	case "-":
		return subtract(n.lhs, n.rhs)
	case "*":
		return multiply(n.lhs, n.rhs)
	case "/":
		return divide(n.lhs, n.rhs)
	case "%":
		return modulo(n.lhs, n.rhs)
	}
	return 0, fmt.Errorf("Unknown operator %v", n.value)
}

func add(lhs, rhs *node) (float64, error) {
	lval, rval, err := nodevalues(lhs, rhs)
	if err != nil {
		return 0, err
	}
	return lval + rval, nil
}

func nodevalues(lhs, rhs *node) (lval, rval float64, err error) {
	lval, err = lhs.evaluate()
	if err != nil {
		return
	}
	rval, err = rhs.evaluate()
	return
}

func subtract(lhs, rhs *node) (float64, error) {
	lval, rval, err := nodevalues(lhs, rhs)
	if err != nil {
		return 0, err
	}
	return lval - rval, nil
}

func multiply(lhs, rhs *node) (float64, error) {
	lval, rval, err := nodevalues(lhs, rhs)
	if err != nil {
		return 0, err
	}
	return lval * rval, nil
}

func divide(lhs, rhs *node) (float64, error) {
	lval, rval, err := nodevalues(lhs, rhs)
	if err != nil {
		return 0, err
	}
	return lval / rval, nil
}

func modulo(lhs, rhs *node) (float64, error) {
	lval, rval, err := nodevalues(lhs, rhs)
	if err != nil {
		return 0, err
	}
	return math.Mod(lval, rval), nil
}
