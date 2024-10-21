package org.lis.core.parser.expression;

import org.lis.core.scanner.Token;

public class LisUnaryExpressionNode extends LisExpressionNode {
    public final Token operator;
    public final LisExpressionNode expression;

    public LisUnaryExpressionNode(Token operator, LisExpressionNode expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Unary(" + operator.lexeme() + ", " + expression.toString() + ")";
    }
}
