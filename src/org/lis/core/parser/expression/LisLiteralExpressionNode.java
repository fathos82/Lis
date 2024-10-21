package org.lis.core.parser.expression;

import org.lis.core.scanner.Token;

public class LisLiteralExpressionNode extends LisExpressionNode {
    public final Token literal;

    public LisLiteralExpressionNode(Token literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Literal("+ literal.literal().toString()+")";
    }
}
