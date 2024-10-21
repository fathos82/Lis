package org.lis.core.parser.expression;

import org.lis.core.scanner.Token;

public class LisLogicExpressionNode extends LisExpressionNode{
    private final LisExpressionNode left;
    private final Token logicalOperator;
    private final LisExpressionNode right;

    public LisLogicExpressionNode(LisExpressionNode left, Token logicalOperator, LisExpressionNode right) {
        this.left = left;
        this.logicalOperator = logicalOperator;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Logic("+left.toString()+", "+logicalOperator.lexeme()+", "+right.toString()+")";
    }
}