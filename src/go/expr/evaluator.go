// Package expr supports the evaluation of mathematical expressions.
package expr

// Evaluate returns the result of a mathematical expression.
// All numerical components of the expression are parsed as floats.
// An error is set if an invalid expression is encountered.
// Operators and operands must be separated by whitespace.
// Parenthetical expressions are allowed and need not be delimited by whitespace.
func Evaluate(expression string) (float64, error) {
	tree, err := parse(expression)
	if err != nil {
		return 0, err
	}
	return tree.evaluate()
}
