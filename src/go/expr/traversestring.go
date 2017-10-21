package expr

import (
	"errors"
	"fmt"
	"unicode"
)

// tricky: tokens must be separated by whitespace
func token(expression string) (token, remaining string, err error) {
	foundtoken := false
	start := 0
	for i, chr := range expression {
		if chr == '(' {
			return parenthetical(expression[i+1:])
		} else if unicode.IsSpace(chr) {
			if foundtoken {
				token = expression[start:i]
				remaining = expression[i+1:]
				err = nil
				return
			}
		} else if !foundtoken {
			foundtoken = true
			start = i
		}
	}
	if foundtoken {
		token = expression[start:]
		remaining = ""
		err = nil
		return
	}
	err = errors.New("No token in expression")
	return
}

// tricky: source starts immediately after the opening parenthesis
func parenthetical(source string) (result, remaining string, err error) {
	var opened = 1
	for i, chr := range source {
		if chr == '(' {
			opened++
		} else if chr == ')' {
			opened--
		}
		if opened == 0 {
			result = source[:i]
			remaining = source[i+1:]
			err = nil
			return
		}
	}
	err = fmt.Errorf("Invalid parenthetical expression: %v", source)
	return
}

// tricky: dependent on token() and parenthetical() implementations
func tokenIsParenthetical(token string) bool {
	for _, chr := range token {
		if unicode.IsSpace(chr) {
			return true
		}
	}
	return false
}
