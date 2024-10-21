package org.lis.core.parser.expression;

import org.lis.core.scanner.Token;

public class LisUnaryExpressionNode extends LisExpressionNode {
    public final Token operator;

    public LisUnaryExpressionNode(Token operator) {
        this.operator = operator;
    }
}
