package org.lis.core.parser.expression;

import org.lis.core.scanner.Token;

public class LisBinaryExpressionNode extends LisExpressionNode {

    public final LisExpressionNode left;
    public final Token operator;
    public final LisExpressionNode right;

    public LisBinaryExpressionNode(LisExpressionNode left, Token operator, LisExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Binary(" + left.toString() + ", " + operator.lexeme() + ", " + right.toString() + ")";
    }
}
