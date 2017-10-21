package expr

import "strconv"

type node struct {
	lhs   *node
	rhs   *node
	value string
}

func (n *node) evaluate() (float64, error) {
	if n.isOperator() {
		return evaluateOperator(n)
	}
	return strconv.ParseFloat(n.value, 64)
}

func (n *node) isOperator() bool {
	return n.lhs != nil
}

func parse(expression string) (*node, error) {
	remaining := expression
	var n *node
	var err error
	for remaining != "" {
		n, remaining, err = nextnode(remaining, n)
		if err != nil {
			break
		}
	}
	return n, err
}

func nextnode(expression string, tree *node) (*node, string, error) {
	token, remaining, err := token(expression)
	if err != nil {
		return tree, expression, err
	}
	if token == expression {
		return leaf(token, remaining, tree)
	}
	return branch(token, remaining, tree)
}

func leaf(token, remaining string, tree *node) (*node, string, error) {
	var n node
	n.value = token
	if tree == nil {
		return &n, remaining, nil
	}
	return update(tree, &n), remaining, nil
}

func update(tree, next *node) *node {
	if tree == nil {
		return next
	}
	if isLeftChild(tree) {
		next.lhs = tree
		return next
	}
	tree.rhs = next
	return tree
}

func isLeftChild(n *node) bool {
	return (n.lhs == nil && n.rhs == nil) || (n.lhs != nil && n.rhs != nil)
}

func branch(token, remaining string, tree *node) (*node, string, error) {
	var n *node
	var err error
	if tokenIsParenthetical(token) {
		n, err = parse(token)
		n = update(tree, n)
	} else {
		n, _, err = nextnode(token, tree)
	}
	return n, remaining, err
}
